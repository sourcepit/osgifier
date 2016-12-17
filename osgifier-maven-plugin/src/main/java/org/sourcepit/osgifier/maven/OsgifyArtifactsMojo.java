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

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.maven.RepositoryUtils;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelWriter;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.DefaultProjectBuildingRequest;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.maven.project.ProjectBuildingResult;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.ArtifactProperties;
import org.sourcepit.common.manifest.Header;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.artifact.ArtifactFactory;
import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.packaging.Repackager;

/**
 * The goal <i>osgify-artifacts</i> can be used to fetch a set of artifacts together with their transitive dependencies
 * from Maven repositories and to equip each with its own OSGi `MANIFES.MF`.<br>
 * <br>
 * <b>Note</b>: Use this goal together with the goals <i>install-osgified-artifacts</i> and
 * <i>deploy-osgified-artifacts</i> to install and deploy the osgified artifacts to a Maven repository.
 * 
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Mojo(name = "osgify-artifacts", defaultPhase = LifecyclePhase.PACKAGE)
public class OsgifyArtifactsMojo extends AbstractOsgifierMojo {
   private final LegacySupport buildContext;

   private final ProjectBuilder projectBuilder;

   private OsgifierModelBuilder modelBuilder;

   private final Repackager repackager;

   private final ArtifactFactory artifactFactory;

   @Parameter(required = true)
   private List<Dependency> artifacts;

   /**
    * Mapping between option name and value. These options will be passed to the OSGifier and are intended to customize
    * the OSGifiers default behavior to your needs.<br>
    * </br>
    * 
    * <pre>
    * &lt;options&gt;
    *   &lt;symbolicNameMappings&gt;
    *     xalan:xalan:jar=org.apache.xalan,
    *     stax:stax-api:jar=javax.xml.stream,
    *   &lt;/symbolicNameMappings&gt;
    *   &lt;overrideNativeBundles&gt;
    *     slf4j.api
    *   &lt;/overrideNativeBundles&gt;
    * &lt;/options&gt;
    * </pre>
    */
   @Parameter(required = false)
   private Map<String, String> options;

   /**
    * Optional string that will be prepended to the <i>groupId</i> of any osgified artifact. Use this to prevent
    * conflicts with the original artifacts.
    */
   @Parameter(required = false)
   private String groupIdPrefix;

   /**
    * Optional string that replaces the <i>groupId</i> of any osgified artifact. Use this to prevent conflicts with the
    * original artifacts.
    */
   @Parameter(required = false)
   private String groupId;

   /**
    * Directory the osgified artifacts will be stored in.
    */
   @Parameter(defaultValue = "${project.build.directory}/osgified")
   private File workDir;

   @Inject
   public OsgifyArtifactsMojo(LegacySupport buildContext, ProjectBuilder projectBuilder,
      OsgifierModelBuilder modelBuilder, Repackager repackager, ArtifactFactory artifactFactory) {
      this.buildContext = buildContext;
      this.projectBuilder = projectBuilder;
      this.modelBuilder = modelBuilder;
      this.repackager = repackager;
      this.artifactFactory = artifactFactory;
   }

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException {
      final PropertiesSource options = MojoUtils.getOptions(buildContext.getSession()
         .getCurrentProject()
         .getProperties(), this.options);
      final Date startTime = buildContext.getSession().getStartTime();

      final OsgifierContext osgifyContext = modelBuilder.build(new DefaultOsgifierContextInflatorFilter(), options,
         artifacts, startTime);

      final Collection<BundleCandidate> bundles = new ArrayList<BundleCandidate>();
      for (BundleCandidate bundle : osgifyContext.getBundles()) {
         if (!bundle.isNativeBundle() && bundle.getTargetBundle() == null) {
            bundles.add(bundle);
         }
      }

      final DependencyModel dependencyModel = osgifyContext.getExtension(DependencyModel.class);

      final Map<BundleCandidate, MavenProject> mavenProjectMapping = new HashMap<BundleCandidate, MavenProject>(
         bundles.size());

      for (BundleCandidate bundle : bundles) {
         final MavenArtifact artifact = bundle.getExtension(MavenArtifact.class);

         try {
            ProjectBuildingResult result = buildMavenProject(artifact);
            mavenProjectMapping.put(bundle, result.getProject());
         }
         catch (ProjectBuildingException e) {
            getLog().warn(String.format(
               "Failed to resolve original Maven model for artifact %s. Some information may note be available in genrated pom.",
               artifact), e);
         }

         adoptMavenCoordinates(dependencyModel, bundle);
         final BundleCandidate sourceBundle = bundle.getSourceBundle();
         if (sourceBundle != null) {
            adoptMavenCoordinates(dependencyModel, sourceBundle);
         }
      }

      for (BundleCandidate bundle : bundles) {
         injectManifest(dependencyModel, bundle);
         final BundleCandidate sourceBundle = bundle.getSourceBundle();
         if (sourceBundle != null) {
            injectManifest(dependencyModel, sourceBundle);
         }
      }

      ModelUtils.writeModel(getOsgifierContextFile(), osgifyContext);

      final Collection<Artifact> artifacts = new ArrayList<Artifact>();
      for (BundleCandidate bundle : bundles) {
         final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);
         final Artifact artifact = artifactFactory.createArtifact(bundleArtifact.getArtifactKey()).setFile(
            bundle.getLocation());
         artifacts.add(artifact);

         final BundleCandidate sourceBundle = bundle.getSourceBundle();
         if (sourceBundle != null) {
            final Artifact sourceArtifact = artifactFactory.createArtifact(artifact, "sources", "jar").setFile(
               sourceBundle.getLocation());
            artifacts.add(sourceArtifact);
         }

         final Model model = buildPom(bundle, dependencyModel);

         final MavenProject mavenProject = mavenProjectMapping.get(bundle);
         if (mavenProject != null) {
            final Model oriModel = mavenProject.getModel();

            model.setCiManagement(oriModel.getCiManagement());
            model.setContributors(oriModel.getContributors());
            model.setDescription(oriModel.getDescription());
            model.setDevelopers(oriModel.getDevelopers());
            model.setInceptionYear(oriModel.getInceptionYear());
            model.setLicenses(oriModel.getLicenses());
            model.setMailingLists(oriModel.getMailingLists());
            model.setName(oriModel.getName());
            model.setOrganization(oriModel.getOrganization());
            model.setScm(oriModel.getScm());
            model.setUrl(oriModel.getUrl());
         }

         final File pomFile = new File(workDir, getBundleId(bundle) + ".xml");
         try {
            new DefaultModelWriter().write(pomFile, null, model);
         }
         catch (IOException e) {
            throw pipe(e);
         }

         final Artifact pomArtifact = artifactFactory.createArtifact(artifact, null, "pom").setFile(pomFile);
         artifacts.add(pomArtifact);
      }

      MavenProject project = buildContext.getSession().getCurrentProject();
      project.setContextValue("osgified-artifacts", artifacts);

      printSummary(artifacts);
   }

   private ProjectBuildingResult buildMavenProject(final MavenArtifact artifact) throws ProjectBuildingException {
      final ProjectBuildingRequest request = new DefaultProjectBuildingRequest(buildContext.getSession()
         .getProjectBuildingRequest());
      request.setProject(null);
      request.setResolveDependencies(false);
      request.setProcessPlugins(false);

      final org.apache.maven.artifact.Artifact projectArtifact = RepositoryUtils.toArtifact(artifactFactory.createArtifact(
         artifactFactory.createArtifact(artifact.getArtifactKey()), null, "pom"));

      return projectBuilder.build(projectArtifact, true, request);
   }

   private void printSummary(Collection<Artifact> artifacts) {
      getLog().info("------------------------------------------------------------------------");
      getLog().info("Osgified artifacts:");
      getLog().info("");

      for (Artifact artifact : artifacts) {
         final String type = artifact.getProperty(ArtifactProperties.TYPE, artifact.getExtension());
         if ("pom".equals(type)) {
            continue;
         }

         getLog().info("<dependency>");
         getLog().info("   <groupId>" + artifact.getGroupId() + "</groupId>");
         getLog().info("   <artifactId>" + artifact.getArtifactId() + "</artifactId>");
         getLog().info("   <version>" + artifact.getVersion() + "</version>");
         final String classifier = artifact.getClassifier();
         if (!isNullOrEmpty(classifier)) {
            getLog().info("   <classifier>" + classifier + "</classifier>");
         }
         if (!"jar".equals(type)) {
            getLog().info("   <type>" + type + "</type>");
         }
         getLog().info("</dependency>");
         getLog().info("");
      }
   }

   private File getOsgifierContextFile() {
      return new File(workDir, "osgifier-context.xml");
   }

   private String getBundleId(BundleCandidate bundle) {
      return bundle.getSymbolicName() + "_" + bundle.getVersion().toFullString();
   }

   private void injectManifest(final DependencyModel dependencyModel, BundleCandidate bundle) {
      final String bundleId = getBundleId(bundle);
      // ModelUtils.writeModel(new File(workDir, bundleId + ".MF"), EcoreUtil.copy(bundle.getManifest()));

      final Collection<String> pathFilters = Arrays.asList("!META-INF/maven/**");

      final File destJarFile = new File(workDir, bundleId + ".jar");
      repackager.copyJarAndInjectManifest(bundle.getLocation(), destJarFile, bundle.getManifest(),
         bundle.getLocalization(), pathFilters);
      updateBundleLocation(bundle, dependencyModel, destJarFile);
   }

   private void adoptMavenCoordinates(final DependencyModel dependencyModel, BundleCandidate bundle) {
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
      final File destJarFile) {
      bundle.setLocation(destJarFile);
      final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);
      bundleArtifact.setFile(destJarFile);
      final MavenArtifact mavenArtifact = dependencyModel.getArtifact(bundleArtifact.getArtifactKey());
      mavenArtifact.setFile(destJarFile);
   }

   private static Model buildPom(BundleCandidate bundle, DependencyModel dependencyModel) {
      final MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);

      final Model model = new Model();
      model.setModelVersion("4.0.0");
      model.setGroupId(bundleArtifact.getGroupId());
      model.setArtifactId(bundleArtifact.getArtifactId());
      model.setVersion(bundleArtifact.getVersion());

      final DependencyTree dependencyTree = dependencyModel.getDependencyTree(bundleArtifact.getArtifactKey());
      if (dependencyTree != null) {
         for (DependencyNode dependencyNode : dependencyTree.getDependencyNodes()) {
            if (dependencyNode.isSelected()) {
               model.addDependency(toDependency(dependencyNode.getArtifact().getArtifactKey(), dependencyNode));
            }
         }
      }

      return model;
   }

   private static Dependency toDependency(ArtifactKey newKey, DependencyNode dependencyNode) {
      final MavenDependency declaredDependency = dependencyNode.getDeclaredDependency();
      final Dependency dependency = new Dependency();
      dependency.setGroupId(newKey.getGroupId());
      dependency.setArtifactId(newKey.getArtifactId());

      // TODO
      // dependency.setVersion(declaredDependency.getVersionConstraint());
      dependency.setVersion(newKey.getVersion());

      dependency.setClassifier(newKey.getClassifier());
      final String type = newKey.getType();
      if (!"jar".equals(type)) {
         dependency.setType(type);
      }
      final boolean optional = declaredDependency.isOptional();
      if (optional) {
         dependency.setOptional(optional);
      }
      final String scope = declaredDependency.getScope().toString();
      if (!"compile".equals(scope)) {
         dependency.setScope(scope);
      }
      return dependency;
   }

   private void adoptVersion(final MavenArtifact mavenArtifact, final BundleManifest manifest) {
      final String version = toMavenVersion(manifest.getBundleVersion());
      mavenArtifact.setVersion(version);
      final Header header = manifest.getHeader("Maven-Version");
      if (header != null) {
         header.setValue(version);
      }
   }

   private void adoptArtifactId(final MavenArtifact mavenArtifact, final BundleCandidate bundle) {
      final String artifactId;

      final BundleCandidate targetBundle = bundle.getTargetBundle();
      if (targetBundle == null) {
         artifactId = bundle.getManifest().getBundleSymbolicName().getSymbolicName();
      }
      else {
         artifactId = targetBundle.getManifest().getBundleSymbolicName().getSymbolicName();
      }

      mavenArtifact.setArtifactId(artifactId);

      final Header header = bundle.getManifest().getHeader("Maven-ArtifactId");
      if (header != null) {
         header.setValue(artifactId);
      }
   }

   private void adoptGroupId(final MavenArtifact mavenArtifact, final BundleManifest manifest) {
      final String customGroupId = getCustomGroupId(mavenArtifact);
      if (customGroupId != null) {
         mavenArtifact.setGroupId(customGroupId);
         final Header header = manifest.getHeader("Maven-GroupId");
         if (header != null) {
            header.setValue(customGroupId);
         }
      }
   }

   private String getCustomGroupId(final MavenArtifact mavenArtifact) {
      if (this.groupId != null) {
         return this.groupId;
      }
      if (groupIdPrefix != null) {
         return groupIdPrefix + mavenArtifact.getGroupId();
      }
      return null;
   }

   private static String toMavenVersion(Version version) {
      final StringBuilder sb = new StringBuilder();
      sb.append(version.getMajor());

      sb.append('.');
      sb.append(version.getMinor());

      sb.append('.');
      sb.append(version.getMicro());

      final String qualifier = version.getQualifier();
      if (!qualifier.isEmpty()) {
         sb.append('-');
         sb.append(qualifier);
      }

      return sb.toString();
   }
}
