/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.maven;

import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelWriter;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.installation.InstallRequest;
import org.eclipse.aether.installation.InstallationException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.maven.artifact.ArtifactFactory;
import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.ArtifactKeyBuilder;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.packaging.Repackager;
import org.sourcepit.osgifier.maven.DefaultOsgifierContextInflatorFilter;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Mojo(name = "osgify-dependencies", requiresProject = true, requiresDependencyResolution = ResolutionScope.COMPILE)
public class OsgifyProjectDependenciesMojo extends AbstractOsgifierMojo
{
   @Parameter(defaultValue = "${project.build.directory}")
   private File targetDir;

   @Parameter(defaultValue = "${project}")
   private MavenProject project;

   @Parameter(defaultValue = "**.internal.**, **.internal, **.impl.**, **.impl")
   private String internalPackages;

   @Inject
   private OsgifierModelBuilder modelBuilder;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final PropertiesSource options = createOptions();

      final Date startTime = buildContext.getSession().getStartTime();

      final OsgifierContext bundleModel = modelBuilder.build(new DefaultOsgifierContextInflatorFilter(), options,
         project.getDependencies(), startTime);

      ModelUtils.writeModel(new File(targetDir, "osgifyModel.xml"), bundleModel);


      EList<BundleCandidate> bundles = bundleModel.getBundles();

      final Map<ArtifactKey, BundleCandidate> keyToBundle = new HashMap<ArtifactKey, BundleCandidate>();
      final Map<ArtifactKey, Model> keyToPom = new HashMap<ArtifactKey, Model>();
      final Map<ArtifactKey, ArtifactKey> keyToNewKey = new HashMap<ArtifactKey, ArtifactKey>();
      for (BundleCandidate bundle : bundles)
      {
         final MavenArtifact artifact = bundle.getExtension(MavenArtifact.class);
         final ArtifactKey key = artifact.getArtifactKey();
         keyToBundle.put(key, bundle);

         final Model pom = getPom(bundle);
         keyToPom.put(key, pom);

         if (!bundle.isNativeBundle())
         {
            final ArtifactKeyBuilder newKey = new ArtifactKeyBuilder();
            newKey.setGroupId(pom.getGroupId());
            newKey.setArtifactId(pom.getArtifactId());
            newKey.setType(key.getType());
            newKey.setClassifier(key.getClassifier());
            newKey.setVersion(pom.getVersion());
            keyToNewKey.put(key, newKey.toArtifactKey());
         }
         else
         {
            keyToNewKey.put(key, key);
         }
      }

      final DependencyModel dependencyModel = bundleModel.getExtension(DependencyModel.class);
      for (BundleCandidate bundle : bundles)
      {
         final ArtifactKey key = getArtifactKey(bundle);
         final Model pom = keyToPom.get(key);

         final DependencyTree dependencyTree = dependencyModel.getDependencyTree(key);
         if (dependencyTree != null)
         {
            for (DependencyNode dependencyNode : dependencyTree.getDependencyNodes())
            {
               if (dependencyNode.isSelected())
               {
                  pom.addDependency(toDependency(keyToNewKey.get(dependencyNode.getArtifact().getArtifactKey()),
                     dependencyNode));
               }
            }
         }
      }

      for (BundleCandidate bundle : bundles)
      {
         final ArtifactKey key = getArtifactKey(bundle);

         final File dir = getWorkDir(bundle);

         ModelUtils.writeModel(new File(dir, "MANIFEST.MF"), EcoreUtil.copy(bundle.getManifest()));

         final File pomFile = new File(dir, "pom.xml");
         try
         {
            new DefaultModelWriter().write(pomFile, null, keyToPom.get(key));
         }
         catch (IOException e)
         {
            throw pipe(e);
         }

         if (!bundle.isNativeBundle() && bundle.getTargetBundle() == null)
         {
            final File destJarFile = copyJarAndInjectManifest(bundle);

            InstallRequest request = new InstallRequest();

            Artifact artifact = artifactFactory.createArtifact(keyToNewKey.get(key));
            Artifact pomArtifact = artifactFactory.createArtifact(artifact, null, "pom");
            request.addArtifact(pomArtifact.setFile(pomFile));
            request.addArtifact(artifact.setFile(destJarFile));

            BundleCandidate sourceBundle = bundle.getSourceBundle();
            if (sourceBundle != null && sourceBundle.getLocation() != null)
            {
               final ArtifactKey sourceKey = getArtifactKey(sourceBundle);
               final Artifact sourceArtifact = artifactFactory.createArtifact(artifact, sourceKey.getClassifier(),
                  sourceKey.getType());
               final File sourceJar;
               if (!bundle.isNativeBundle())
               {
                  sourceJar = copyJarAndInjectManifest(sourceBundle);
               }
               else
               {
                  sourceJar = sourceBundle.getLocation();
               }
               request.addArtifact(sourceArtifact.setFile(sourceJar));
            }

            try
            {
               repositorySystem.install(buildContext.getRepositorySession(), request);
            }
            catch (InstallationException e)
            {
               throw pipe(e);
            }

         }
      }

   }

   private PropertiesSource createOptions()
   {
      final PropertiesMap properties = new LinkedPropertiesMap();
      properties.put("osgifier.internalPackages", internalPackages);

      final PropertiesSource options = new AbstractPropertiesSource()
      {
         @Override
         public String get(String key)
         {
            String value = properties.get(key);
            if (value == null)
            {
               value = project.getProperties().getProperty(key);
            }
            return value;

         }
      };
      return options;
   }

   private File copyJarAndInjectManifest(BundleCandidate bundle)
   {
      final File srcJarFile = bundle.getLocation();
      final File destJarFile = new File(getWorkDir(bundle), getBundleId(bundle) + ".jar");
      repackager.copyJarAndInjectManifest(srcJarFile, destJarFile, bundle.getManifest(), null);
      return destJarFile;
   }

   private File getWorkDir(BundleCandidate bundle)
   {
      final String bundleDir = (bundle.isNativeBundle() ? "native/" : "osgified/") + getBundleId(bundle);

      File dir = new File(targetDir, bundleDir);
      return dir;
   }

   private String getBundleId(BundleCandidate bundle)
   {
      return bundle.getSymbolicName() + "_" + bundle.getVersion();
   }

   @Inject
   private RepositorySystem repositorySystem;

   @Inject
   private Repackager repackager;

   @Inject
   private ArtifactFactory artifactFactory;

   @Inject
   private LegacySupport buildContext;


   private Dependency toDependency(ArtifactKey newKey, DependencyNode dependencyNode)
   {
      final MavenDependency declaredDependency = dependencyNode.getDeclaredDependency();
      final Dependency dependency = new Dependency();
      dependency.setGroupId(newKey.getGroupId());
      dependency.setArtifactId(newKey.getArtifactId());
      dependency.setVersion(declaredDependency.getVersionConstraint());
      dependency.setClassifier(newKey.getClassifier());
      final String type = newKey.getType();
      if (!"jar".equals(type))
      {
         dependency.setType(type);
      }
      final boolean optional = declaredDependency.isOptional();
      if (optional)
      {
         dependency.setOptional(optional);
      }
      final String scope = declaredDependency.getScope().toString();
      if (!"compile".equals(scope))
      {
         dependency.setScope(scope);
      }
      return dependency;
   }


   private ArtifactKey getArtifactKey(BundleCandidate bundle)
   {
      return bundle.getExtension(MavenArtifact.class).getArtifactKey();
   }


   private Model getPom(BundleCandidate bundle)
   {
      Model model = new Model();
      model.setModelVersion("4.0.0");
      // TODO
      model.setGroupId("srcpit.bundles");
      model.setArtifactId(bundle.getSymbolicName());
      // TODO
      // model.setVersion(bundle.getVersion().toMinimalString());
      model.setVersion(getArtifactKey(bundle).getVersion());

      return model;
   }
}
