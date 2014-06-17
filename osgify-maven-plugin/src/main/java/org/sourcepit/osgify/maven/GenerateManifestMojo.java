/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import org.apache.maven.RepositoryUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.artifact.ArtifactFactory;
import org.sourcepit.common.maven.artifact.MavenArtifactUtils;
import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSources;
import org.sourcepit.osgify.core.headermod.HeaderModifications;
import org.sourcepit.osgify.core.headermod.HeaderModifier;
import org.sourcepit.osgify.maven.manifest.builder.ManifestBuilderResult;
import org.sourcepit.osgify.maven.manifest.builder.MavenProjectManifestBuilder;

@Mojo(name = "generate-manifest", requiresProject = true, requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME, defaultPhase = LifecyclePhase.PROCESS_CLASSES)
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

   @Parameter(required = false)
   private HeaderModifications headerModifications;

   @Parameter(defaultValue = "false")
   private boolean pde;

   @Inject
   private LegacySupport buildContext;

   @Inject
   private ArtifactFactory artifactFactory;

   @Inject
   private HeaderModifier headerModifier;

   @Inject
   private MavenProjectManifestBuilder manifestBuilder;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final MavenProject project = buildContext.getSession().getCurrentProject();

      manifestBuilder.setTimestamp(buildContext.getSession().getStartTime());
      manifestBuilder.project(project.getArtifact());
      manifestBuilder.setSymbolicName(symbolicName);
      manifestBuilder.withProjectProperties(project.getProperties());
      manifestBuilder.withDependencies(project.getArtifacts());
      manifestBuilder.withOptions(getMojoConfigurationOptions());
      if (!skipSource)
      {
         manifestBuilder.withSourceArtifact(newProjectArtifact(project, sourceClassifier, "jar"));
      }
      
      final ManifestBuilderResult result = manifestBuilder.build();

      final BundleManifest manifest = result.getBundleManifest();
      if (headerModifications != null)
      {
         headerModifier.applyModifications(manifest, headerModifications);
      }
      ModelUtils.writeModel(manifestFile, manifest);
      project.setContextValue("osgifier.manifestFile", manifestFile);

      if (pde)
      {
         // set as derived
         try
         {
            copyFile(manifestFile, new File(project.getBasedir(), "META-INF/MANIFEST.MF"));
         }
         catch (IOException e)
         {
            throw pipe(e);
         }
      }

      final BundleManifest sourceManifest = result.getSourceBundleManifest();
      if (sourceManifest != null)
      {
         ModelUtils.writeModel(sourceManifestFile, sourceManifest);
         project.setContextValue("osgifier.sourceManifestFile", sourceManifestFile);
      }
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

   private Artifact newProjectArtifact(final MavenProject project, String classifier, String type)
   {
      org.eclipse.aether.artifact.Artifact artifact = artifactFactory.createArtifact(
         RepositoryUtils.toArtifact(project.getArtifact()), classifier, type);

      final String buildDir = project.getBuild().getDirectory();

      final String finalName = project.getBuild().getFinalName();

      final String sourceFinalName = finalName + "-" + artifact.getClassifier();

      final File file = new File(buildDir + "/" + sourceFinalName + "." + artifact.getExtension());

      artifact = artifact.setFile(file);

      return MavenArtifactUtils.toArtifact(artifact);
   }
}
