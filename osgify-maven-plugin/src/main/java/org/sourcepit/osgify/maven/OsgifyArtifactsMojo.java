/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelWriter;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.aether.artifact.Artifact;
import org.sourcepit.common.manifest.Header;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.aether.ArtifactFactory;
import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.packaging.Repackager;

@Mojo(name = "osgify-artifacts", defaultPhase = LifecyclePhase.PACKAGE)
public class OsgifyArtifactsMojo extends AbstractOsgifyMojo
{
   private final LegacySupport buildContext;

   private OsgifyModelBuilder modelBuilder;

   private final Repackager repackager;

   private final ArtifactFactory artifactFactory;

   @Parameter(required = true)
   private List<Dependency> artifacts;

   @Parameter(required = false)
   private Map<String, String> options;

   @Parameter(required = false)
   private String groupIdPrefix;

   @Parameter(defaultValue = "${project.build.directory}/osgified")
   private File workDir;

   @Inject
   public OsgifyArtifactsMojo(LegacySupport buildContext, OsgifyModelBuilder modelBuilder, Repackager repackager,
      ArtifactFactory artifactFactory)
   {
      this.buildContext = buildContext;
      this.modelBuilder = modelBuilder;
      this.repackager = repackager;
      this.artifactFactory = artifactFactory;
   }

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final ManifestGeneratorFilter generatorFilter = new ManifestGeneratorFilter();
      final PropertiesSource options = MojoUtils.getOptions(buildContext.getSession().getCurrentProject()
         .getProperties(), this.options);
      final Date startTime = buildContext.getSession().getStartTime();

      final OsgifyContext osgifyContext = modelBuilder.build(generatorFilter, options, artifacts, startTime);

      final Collection<BundleCandidate> bundles = new ArrayList<BundleCandidate>();
      for (BundleCandidate bundle : osgifyContext.getBundles())
      {
         if (!bundle.isNativeBundle() && !generatorFilter.isSourceBundle(bundle))
         {
            bundles.add(bundle);
         }
      }

      final DependencyModel dependencyModel = osgifyContext.getExtension(DependencyModel.class);

      for (BundleCandidate bundle : bundles)
      {
         adoptMavenCoordinates(dependencyModel, bundle);
         final BundleCandidate sourceBundle = bundle.getSourceBundle();
         if (sourceBundle != null)
         {
            adoptMavenCoordinates(dependencyModel, sourceBundle);
         }
      }

      for (BundleCandidate bundle : bundles)
      {
         injectManifest(dependencyModel, bundle);
         final BundleCandidate sourceBundle = bundle.getSourceBundle();
         if (sourceBundle != null)
         {
            injectManifest(dependencyModel, sourceBundle);
         }
      }

      ModelUtils.writeModel(getOsgifyContextFile(), osgifyContext);

      final Collection<Artifact> artifacts = new ArrayList<Artifact>();
      for (BundleCandidate bundle : bundles)
      {
         final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);
         final Artifact artifact = artifactFactory.createArtifact(bundleArtifact.getArtifactKey()).setFile(
            bundle.getLocation());
         artifacts.add(artifact);

         final BundleCandidate sourceBundle = bundle.getSourceBundle();
         if (sourceBundle != null)
         {
            final Artifact sourceArtifact = artifactFactory.createArtifact(artifact, "sources", "jar").setFile(
               sourceBundle.getLocation());
            artifacts.add(sourceArtifact);
         }

         final Model model = buildPom(bundle, dependencyModel);
         final File pomFile = new File(workDir, getBundleId(bundle) + ".xml");
         try
         {
            new DefaultModelWriter().write(pomFile, null, model);
         }
         catch (IOException e)
         {
            throw pipe(e);
         }

         final Artifact pomArtifact = artifactFactory.createArtifact(artifact, null, "pom").setFile(pomFile);
         artifacts.add(pomArtifact);
      }

      MavenProject project = buildContext.getSession().getCurrentProject();
      project.setContextValue("osgified-artifacts", artifacts);
   }

   private File getOsgifyContextFile()
   {
      return new File(workDir, "osgify-context.xml");
   }

   private String getBundleId(BundleCandidate bundle)
   {
      return bundle.getSymbolicName() + "_" + bundle.getVersion().toFullString();
   }

   private void injectManifest(final DependencyModel dependencyModel, BundleCandidate bundle)
   {
      final String bundleId = getBundleId(bundle);
      // ModelUtils.writeModel(new File(workDir, bundleId + ".MF"), EcoreUtil.copy(bundle.getManifest()));

      final Collection<String> pathFilters = Arrays.asList("!META-INF/maven/**");

      final File destJarFile = new File(workDir, bundleId + ".jar");
      repackager.copyJarAndInjectManifest(bundle.getLocation(), destJarFile, bundle.getManifest(), pathFilters);
      updateBundleLocation(bundle, dependencyModel, destJarFile);
   }

   private void adoptMavenCoordinates(final DependencyModel dependencyModel, BundleCandidate bundle)
   {
      final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);
      final MavenArtifact mavenArtifact = dependencyModel.getArtifact(bundleArtifact.getArtifactKey());

      final BundleManifest manifest = bundle.getManifest();
      adoptGroupId(bundleArtifact, manifest);
      adoptArtifactId(bundleArtifact, bundle);
      adoptVersion(bundleArtifact, manifest);

      mavenArtifact.setGroupId(bundleArtifact.getGroupId());
      mavenArtifact.setArtifactId(bundleArtifact.getArtifactId());
      mavenArtifact.setVersion(bundleArtifact.getVersion());
   }


   private static void updateBundleLocation(BundleCandidate bundle, final DependencyModel dependencyModel,
      final File destJarFile)
   {
      bundle.setLocation(destJarFile);
      final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);
      bundleArtifact.setFile(destJarFile);
      final MavenArtifact mavenArtifact = dependencyModel.getArtifact(bundleArtifact.getArtifactKey());
      mavenArtifact.setFile(destJarFile);
   }

   private static Model buildPom(BundleCandidate bundle, DependencyModel dependencyModel)
   {
      final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);

      final Model model = new Model();
      model.setModelVersion("4.0.0");
      model.setGroupId(bundleArtifact.getGroupId());
      model.setArtifactId(bundleArtifact.getArtifactId());
      model.setVersion(bundleArtifact.getVersion());

      final DependencyTree dependencyTree = dependencyModel.getDependencyTree(bundleArtifact.getArtifactKey());
      if (dependencyTree != null)
      {
         for (DependencyNode dependencyNode : dependencyTree.getDependencyNodes())
         {
            if (dependencyNode.isSelected())
            {
               model.addDependency(toDependency(dependencyNode.getArtifact().getArtifactKey(), dependencyNode));
            }
         }
      }

      return model;
   }

   private static Dependency toDependency(ArtifactKey newKey, DependencyNode dependencyNode)
   {
      final MavenDependency declaredDependency = dependencyNode.getDeclaredDependency();
      final Dependency dependency = new Dependency();
      dependency.setGroupId(newKey.getGroupId());
      dependency.setArtifactId(newKey.getArtifactId());

      // TODO
      // dependency.setVersion(declaredDependency.getVersionConstraint());
      dependency.setVersion(newKey.getVersion());

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

   private void adoptVersion(final MavenArtifact mavenArtifact, final BundleManifest manifest)
   {
      final String version = toMavenVersion(manifest.getBundleVersion());
      mavenArtifact.setVersion(version);
      final Header header = manifest.getHeader("Maven-Version");
      if (header != null)
      {
         header.setValue(version);
      }
   }

   private void adoptArtifactId(final MavenArtifact mavenArtifact, final BundleCandidate bundle)
   {
      final String artifactId;

      final BundleCandidate targetBundle = bundle.getTargetBundle();
      if (targetBundle == null)
      {
         artifactId = bundle.getManifest().getBundleSymbolicName().getSymbolicName();
      }
      else
      {
         artifactId = targetBundle.getManifest().getBundleSymbolicName().getSymbolicName();
      }

      mavenArtifact.setArtifactId(artifactId);

      final Header header = bundle.getManifest().getHeader("Maven-ArtifactId");
      if (header != null)
      {
         header.setValue(artifactId);
      }
   }

   private void adoptGroupId(final MavenArtifact mavenArtifact, final BundleManifest manifest)
   {
      if (groupIdPrefix != null)
      {
         final String groupId = groupIdPrefix + mavenArtifact.getGroupId();
         mavenArtifact.setGroupId(groupId);
         final Header header = manifest.getHeader("Maven-GroupId");
         if (header != null)
         {
            header.setValue(groupId);
         }
      }
   }

   private static String toMavenVersion(Version version)
   {
      final StringBuilder sb = new StringBuilder();
      sb.append(version.getMajor());

      sb.append('.');
      sb.append(version.getMinor());

      sb.append('.');
      sb.append(version.getMicro());

      final String qualifier = version.getQualifier();
      if (!qualifier.isEmpty())
      {
         sb.append('-');
         sb.append(qualifier);
      }

      return sb.toString();
   }
}
