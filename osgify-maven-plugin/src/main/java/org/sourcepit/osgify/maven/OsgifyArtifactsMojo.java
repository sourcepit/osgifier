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
import java.util.Properties;

import javax.inject.Inject;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Repository;
import org.apache.maven.model.io.DefaultModelWriter;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.deployment.DeployRequest;
import org.eclipse.aether.deployment.DeploymentException;
import org.eclipse.aether.installation.InstallRequest;
import org.eclipse.aether.installation.InstallationException;
import org.eclipse.aether.repository.RemoteRepository;
import org.sourcepit.common.manifest.Header;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.aether.ArtifactFactory;
import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.packaging.Repackager;

@Mojo(name = "osgify-artifacts")
public class OsgifyArtifactsMojo extends AbstractOsgifyMojo
{
   private final LegacySupport buildContext;

   private OsgifyModelBuilder modelBuilder;

   private final Repackager repackager;

   private final ArtifactFactory artifactFactory;

   private final RepositorySystem repositorySystem;

   @Parameter(required = true)
   private List<Dependency> artifacts;

   @Parameter(required = false)
   private Map<String, String> options;

   @Parameter(required = false)
   private String groupIdPrefix;

   @Parameter(required = false)
   private Repository repository;

   @Parameter(defaultValue = "${project.build.directory}/osgified")
   private File workDir;

   @Inject
   public OsgifyArtifactsMojo(LegacySupport buildContext, OsgifyModelBuilder modelBuilder, Repackager repackager,
      ArtifactFactory artifactFactory, RepositorySystem repositorySystem)
   {
      this.buildContext = buildContext;
      this.modelBuilder = modelBuilder;
      this.repackager = repackager;
      this.artifactFactory = artifactFactory;
      this.repositorySystem = repositorySystem;
   }

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final ManifestGeneratorFilter generatorFilter = new ManifestGeneratorFilter();
      final PropertiesSource options = getOptions();
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
         final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);
         final MavenArtifact mavenArtifact = dependencyModel.getArtifact(bundleArtifact.getArtifactKey());

         final BundleManifest manifest = bundle.getManifest();
         adoptGroupId(bundleArtifact, manifest);
         adoptArtifactId(bundleArtifact, manifest);
         adoptVersion(bundleArtifact, manifest);

         mavenArtifact.setGroupId(bundleArtifact.getGroupId());
         mavenArtifact.setArtifactId(bundleArtifact.getArtifactId());
         mavenArtifact.setVersion(bundleArtifact.getVersion());
      }

      final Collection<Artifact> artifacts = new ArrayList<Artifact>();

      for (BundleCandidate bundle : bundles)
      {
         final String bundleId = bundle.getSymbolicName() + "_" + bundle.getVersion().toFullString();
         // ModelUtils.writeModel(new File(workDir, bundleId + ".MF"), EcoreUtil.copy(bundle.getManifest()));

         final Collection<String> pathFilters = Arrays.asList("!META-INF/maven/**");

         final File destJarFile = new File(workDir, bundleId + ".jar");
         repackager.copyJarAndInjectManifest(bundle.getLocation(), destJarFile, bundle.getManifest(), pathFilters);
         updateBundleLocation(bundle, dependencyModel, destJarFile);

         final Model model = buildPom(bundle, dependencyModel);
         final File pomFile = new File(workDir, bundleId + ".xml");
         try
         {
            new DefaultModelWriter().write(pomFile, null, model);
         }
         catch (IOException e)
         {
            throw pipe(e);
         }


         final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);

         final Artifact artifact = artifactFactory.createArtifact(bundleArtifact.getArtifactKey()).setFile(
            bundle.getLocation());
         artifacts.add(artifact);

         final Artifact pomArtifact = artifactFactory.createArtifact(artifact, null, "pom").setFile(pomFile);
         artifacts.add(pomArtifact);
      }

      ModelUtils.writeModel(new File(workDir, "osgify-context.xml"), osgifyContext);

      distribute(artifacts);
   }

   private void distribute(Collection<Artifact> artifacts)
   {
      final RepositorySystemSession session = buildContext.getRepositorySession();

      install(session, artifacts);

      String id = repository.getId();
      String url = repository.getUrl();
      String layout = repository.getLayout();

      RemoteRepository repo = newRemoteRepository(session, id, url, layout);

      deploy(session, repo, artifacts);
   }

   private static RemoteRepository newRemoteRepository(final RepositorySystemSession session, String id, String url,
      String layout)
   {
      final RemoteRepository.Builder repoBuilder = new RemoteRepository.Builder(id, layout, url);
      RemoteRepository repo = repoBuilder.build();
      repoBuilder.setAuthentication(session.getAuthenticationSelector().getAuthentication(repo));
      repoBuilder.setProxy(session.getProxySelector().getProxy(repo));
      return repoBuilder.build();
   }

   private void install(RepositorySystemSession session, Collection<Artifact> artifacts)
   {
      final InstallRequest installRequest = new InstallRequest();
      installRequest.setArtifacts(artifacts);
      try
      {
         repositorySystem.install(session, installRequest);
      }
      catch (InstallationException e)
      {
         throw pipe(e);
      }
   }

   private void deploy(RepositorySystemSession session, RemoteRepository repository, Collection<Artifact> artifacts)
   {
      final DeployRequest request = new DeployRequest();
      request.setArtifacts(artifacts);
      request.setRepository(repository);
      try
      {
         repositorySystem.deploy(session, request);
      }
      catch (DeploymentException e)
      {
         throw pipe(e);
      }
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

   private void adoptArtifactId(final MavenArtifact mavenArtifact, final BundleManifest manifest)
   {
      final String artifactId = manifest.getBundleSymbolicName().getSymbolicName();
      mavenArtifact.setArtifactId(artifactId);
      final Header header = manifest.getHeader("Maven-ArtifactId");
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

   private PropertiesSource getOptions()
   {
      final Properties projectProperties = buildContext.getSession().getCurrentProject().getProperties();
      return new AbstractPropertiesSource()
      {

         @Override
         public String get(String key)
         {
            String value = projectProperties.getProperty(key);
            if (value == null)
            {
               value = options.get(key);
            }
            if (value == null && key.startsWith("osgifier."))
            {
               value = options.get(key.substring(9));
            }
            return value;
         }
      };
   }

}
