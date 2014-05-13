/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.sourcepit.common.utils.props.PropertiesSources.chain;
import static org.sourcepit.common.utils.props.PropertiesSources.toPropertiesSource;

import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.maven.RepositoryUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.aether.ArtifactFactory;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.util.MavenModelUtils;
import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSources;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.VersionRangeResolver;

import com.google.common.base.Strings;

@Mojo(name = "generate-manifest", requiresProject = true, requiresDependencyResolution = ResolutionScope.RUNTIME_PLUS_SYSTEM, defaultPhase = LifecyclePhase.PROCESS_CLASSES)
public class GenerateManifestMojo extends AbstractOsgifyMojo
{
   @Parameter(required = false)
   private Map<String, String> options;

   @Parameter(required = false, property = "project.artifactId")
   private String symbolicName;

   @Parameter(defaultValue = "${project.build.directory}/${project.build.finalName}.MF")
   private File manifestFile;

   @Parameter(defaultValue = "${project.build.directory}/${project.build.finalName}-sources.MF")
   private File sourceManifestFile;

   @Parameter(property = "maven.source.classifier", defaultValue = "sources")
   private String sourceClassifier;

   @Parameter(property = "source.skip", defaultValue = "false")
   private boolean skipSource;

   @Inject
   private LegacySupport buildContext;

   @Inject
   private OsgifyContextInflator inflater;

   @Inject
   private VersionRangeResolver versionRangeResolver;

   @Inject
   private ArtifactFactory artifactFactory;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final MavenProject project = buildContext.getSession().getCurrentProject();

      final OsgifyContext context = buildOsgifyContext(project, skipSource, sourceClassifier);
      final BundleCandidate projectBundle = context.getBundles().get(0);

      final OsgifyContextInflatorFilter inflatorFilter = new DefaultOsgifyContextInflatorFilter()
      {
         @Override
         public boolean isAppendExecutionEnvironment(BundleCandidate bundle, PropertiesSource options)
         {
            return bundle.equals(projectBundle);
         }

         @Override
         public boolean isAppendPackageImports(BundleCandidate bundle, PropertiesSource options)
         {
            return bundle.equals(projectBundle);
         }

         @Override
         public boolean isAppendDynamicImports(BundleCandidate bundle, PropertiesSource options)
         {
            return bundle.equals(projectBundle);
         }
      };

      final PropertiesSource options = buildOsgifierOptions(project, symbolicName, getMojoConfigurationOptions());
      final Date startTime = buildContext.getSession().getStartTime();
      inflater.infalte(inflatorFilter, options, context, startTime);

      final BundleManifest manifest = projectBundle.getManifest();
      ModelUtils.writeModel(manifestFile, manifest);
      project.setContextValue("osgifier.manifestFile", manifestFile);

      final BundleCandidate sourceBundle = projectBundle.getSourceBundle();
      if (sourceBundle != null)
      {
         final BundleManifest sourceManifest = sourceBundle.getManifest();
         ModelUtils.writeModel(sourceManifestFile, sourceManifest);
         project.setContextValue("osgifier.sourceManifestFile", sourceManifestFile);
      }
   }

   private OsgifyContext buildOsgifyContext(MavenProject project, boolean skipSource, String sourceClassifier)
   {
      final BundleCandidate projectBundle = newBundleCandidate(project);

      final OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().add(projectBundle);

      final BundleCandidate sourceBundle;
      if (skipSource)
      {
         sourceBundle = null;
      }
      else
      {
         final MavenArtifact sourceArtifact = newProjectArtifact(project, sourceClassifier, "jar");

         sourceBundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
         sourceBundle.setLocation(sourceArtifact.getFile());
         sourceBundle.addExtension(sourceArtifact);

         context.getBundles().add(sourceBundle);

         sourceBundle.setTargetBundle(projectBundle);
         projectBundle.setSourceBundle(sourceBundle);
      }

      for (Artifact artifact : project.getArtifacts())
      {
         final BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
         reference.addExtension(MavenModelUtils.toMavenDependecy(artifact));
         reference.setOptional(artifact.isOptional());
         reference.setProvided(DefaultArtifact.SCOPE_PROVIDED.equals(artifact.getScope()));
         reference.setVersionRange(versionRangeResolver.resolveVersionRange(reference));

         final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
         bundle.setLocation(artifact.getFile());
         bundle.addExtension(MavenModelUtils.toMavenArtifact(artifact));

         reference.setTarget(bundle);
         projectBundle.getDependencies().add(reference);
         context.getBundles().add(bundle);
      }

      return context;
   }

   private static PropertiesSource buildOsgifierOptions(final MavenProject project, String symbolicName,
      PropertiesSource mojoConfigurationOptions)
   {
      Properties projectProperties = project.getProperties();

      PropertiesSource options = chain(toPropertiesSource(projectProperties), mojoConfigurationOptions);

      final StringBuilder sb = new StringBuilder();
      sb.append(MavenModelUtils.toArtifactKey(project.getArtifact()));
      sb.append('=');
      sb.append(symbolicName);

      String symbolicNameMappings = options.get("osgifier.symbolicNameMappings");
      if (!Strings.isNullOrEmpty(symbolicNameMappings))
      {
         sb.append(',');
         sb.append(symbolicNameMappings);
      }

      return chain(PropertiesSources.singletonPropertiesSource("osgifier.symbolicNameMappings", sb.toString()), options);
   }

   private PropertiesSource getMojoConfigurationOptions()
   {
      if (options == null)
      {
         return PropertiesSources.emptyPropertiesSource();
      }
      return trimKeyPrefix("osgifier.", PropertiesSources.toPropertiesSource(options));
   }

   private static PropertiesSource trimKeyPrefix(final String prefix, final PropertiesSource propertiesSource)
   {
      return new AbstractPropertiesSource()
      {
         @Override
         public String get(String key)
         {
            if (key.startsWith(prefix))
            {
               return propertiesSource.get(key.substring(9));
            }
            return null;
         }
      };
   }

   private MavenArtifact newProjectArtifact(final MavenProject project, String classifier, String type)
   {
      org.eclipse.aether.artifact.Artifact artifact = artifactFactory.createArtifact(
         RepositoryUtils.toArtifact(project.getArtifact()), classifier, type);

      final String buildDir = project.getBuild().getDirectory();

      final String finalName = project.getBuild().getFinalName();

      final String sourceFinalName = finalName + "-" + artifact.getClassifier();

      final File file = new File(buildDir + "/" + sourceFinalName + "." + artifact.getExtension());

      artifact = artifact.setFile(file);

      return MavenModelUtils.toMavenArtifact(artifact);
   }

   private static BundleCandidate newBundleCandidate(MavenProject project)
   {
      final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setLocation(project.getArtifact().getFile());
      bundle.addExtension(MavenModelUtils.toMavenArtifact(project.getArtifact()));
      return bundle;
   }
}
