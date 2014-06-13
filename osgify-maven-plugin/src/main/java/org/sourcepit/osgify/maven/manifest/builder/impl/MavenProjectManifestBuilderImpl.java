/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder.impl;

import static org.sourcepit.common.utils.props.PropertiesSources.chain;
import static org.sourcepit.common.utils.props.PropertiesSources.toPropertiesSource;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.RepositoryUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.merge.ManifestMerger;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.util.ManifestUtils;
import org.sourcepit.common.maven.aether.ArtifactFactory;
import org.sourcepit.common.maven.core.MavenCoreUtils;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSources;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.VersionRangeResolver;
import org.sourcepit.osgify.maven.DefaultOsgifyContextInflatorFilter;
import org.sourcepit.osgify.maven.OsgifyContextInflator;
import org.sourcepit.osgify.maven.OsgifyContextInflatorFilter;
import org.sourcepit.osgify.maven.manifest.builder.ManifestBuilderResult;
import org.sourcepit.osgify.maven.manifest.builder.MavenProjectManifestBuilder;

import com.google.common.base.Strings;

/**
 * 
 * @author DerGilb
 * @author Bernd
 */
@Named
public class MavenProjectManifestBuilderImpl implements MavenProjectManifestBuilder
{
   public static final String DEF_SOURCE_CLASSIFIER = "sources";

   private final ArtifactFactory artifactFactory;
   private final VersionRangeResolver versionRangeResolver;
   private final OsgifyContextInflator inflater;

   private MavenProject project;
   private String symbolicName;
   private Date timestamp;
   private boolean attachSourceBundle = true;
   private String sourceClassifier = DEF_SOURCE_CLASSIFIER;
   private Map<String, String> additionalOptions = new HashMap<String, String>();
   private Manifest mergeManifest;

   private boolean appendExecutionEnvironment = true;

   private boolean appendPackageExports = true;

   private boolean appendPackageImports = true;

   private boolean appendDynamicImports = true;

   @Inject
   public MavenProjectManifestBuilderImpl(ArtifactFactory artifactFactory, VersionRangeResolver versionRangeResolver,
      OsgifyContextInflator inflater)
   {
      this.artifactFactory = artifactFactory;
      this.versionRangeResolver = versionRangeResolver;
      this.inflater = inflater;
   }

   @Override
   public MavenProjectManifestBuilder project(MavenProject project)
   {
      this.project = project;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder withSourceBundleManifest(String sourceClassifier)
   {
      withSourceBundleManifest(true);
      this.sourceClassifier = sourceClassifier;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder withSourceBundleManifest(boolean withSources)
   {
      this.attachSourceBundle = withSources;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder withOption(String key, String value)
   {
      this.additionalOptions.put(key, value);
      return this;
   }

   @Override
   public MavenProjectManifestBuilder withOptions(Map<String, String> options)
   {
      this.additionalOptions.putAll(options);
      return this;
   }

   @Override
   public MavenProjectManifestBuilder setSymbolicName(String symbolicName)
   {
      this.symbolicName = symbolicName;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder setTimestamp(Date timestamp)
   {
      this.timestamp = timestamp;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder appendExecutionEnvironment(boolean append)
   {
      appendExecutionEnvironment = append;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder appendPackageExports(boolean append)
   {
      appendPackageExports = append;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder appendPackageImports(boolean append)
   {
      appendPackageImports = append;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder appendDynamicImports(boolean append)
   {
      appendDynamicImports = append;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder mergeWith(java.util.jar.Manifest manifest)
   {
      this.mergeManifest = ManifestUtils.toManifest(manifest);
      return this;
   }

   @Override
   public MavenProjectManifestBuilder mergeWith(Manifest manifest)
   {
      this.mergeManifest = manifest;
      return this;
   }

   @Override
   public ManifestBuilderResult build()
   {
      final OsgifyContext osgifyContext = buildOsgifyContext(project, versionRangeResolver);
      final BundleCandidate projectBundle = osgifyContext.getBundles().get(0);

      final OsgifyContextInflatorFilter inflatorFilter = newInflatorFilter(projectBundle);

      if (attachSourceBundle)
      {
         final MavenArtifact sourceArtifact = newProjectArtifact(artifactFactory, project, sourceClassifier, "jar");

         final BundleCandidate sourceBundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
         sourceBundle.setLocation(sourceArtifact.getFile());
         sourceBundle.addExtension(sourceArtifact);
         sourceBundle.setTargetBundle(projectBundle);
         projectBundle.setSourceBundle(sourceBundle);
         osgifyContext.getBundles().add(sourceBundle);
      }

      final String symbolicName = Strings.isNullOrEmpty(this.symbolicName)
         ? buildSymbolicName(project)
         : this.symbolicName;

      final PropertiesSource osgifyOptions = buildOsgifyOptions(project.getArtifact(), symbolicName,
         project.getProperties(), additionalOptions);

      final Date startTime = timestamp == null ? new Date() : timestamp;

      inflater.inflate(inflatorFilter, osgifyOptions, osgifyContext, startTime);

      final BundleManifest manifest = projectBundle.getManifest();

      if (mergeManifest != null)
      {
         new ManifestMerger().merge(manifest, mergeManifest);
      }

      final ManifestBuilderResultImpl result = new ManifestBuilderResultImpl(manifest);
      if (attachSourceBundle)
      {
         final BundleCandidate sourceBundle = projectBundle.getSourceBundle();
         final BundleManifest sourceBundleManifest = sourceBundle.getManifest();
         result.setSourceBundleManifest(sourceBundleManifest);
      }

      return result;
   }

   private static String buildSymbolicName(MavenProject project)
   {
      return project.getGroupId() + "." + project.getArtifactId();
   }

   private static OsgifyContext buildOsgifyContext(MavenProject project, VersionRangeResolver versionRangeResolver)
   {
      final BundleCandidate projectBundle = newBundleCandidate(project);

      final OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().add(projectBundle);

      for (Artifact artifact : project.getArtifacts())
      {
         final BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
         reference.addExtension(MavenCoreUtils.toMavenDependecy(artifact));
         reference.setOptional(artifact.isOptional());
         reference.setProvided(DefaultArtifact.SCOPE_PROVIDED.equals(artifact.getScope()));
         reference.setVersionRange(versionRangeResolver.resolveVersionRange(reference));

         final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
         bundle.setLocation(artifact.getFile());
         bundle.addExtension(MavenCoreUtils.toMavenDependecy(artifact));

         reference.setTarget(bundle);
         projectBundle.getDependencies().add(reference);
         context.getBundles().add(bundle);
      }

      return context;
   }

   private OsgifyContextInflatorFilter newInflatorFilter(final BundleCandidate projectBundle)
   {
      final OsgifyContextInflatorFilter inflatorFilter = new DefaultOsgifyContextInflatorFilter()
      {
         @Override
         public boolean isAppendPackageExports(BundleCandidate bundle, PropertiesSource options)
         {
            if (bundle.equals(projectBundle))
            {
               return appendPackageExports;
            }
            return true;
         }

         @Override
         public boolean isAppendExecutionEnvironment(BundleCandidate bundle, PropertiesSource options)
         {
            if (bundle.equals(projectBundle))
            {
               return appendExecutionEnvironment;
            }
            return false;
         }

         @Override
         public boolean isAppendPackageImports(BundleCandidate bundle, PropertiesSource options)
         {
            if (bundle.equals(projectBundle))
            {
               return appendPackageImports;
            }
            return false;
         }

         @Override
         public boolean isAppendDynamicImports(BundleCandidate bundle, PropertiesSource options)
         {
            if (bundle.equals(projectBundle))
            {
               return appendDynamicImports;
            }
            return false;
         }
      };
      return inflatorFilter;
   }

   private static PropertiesSource buildOsgifyOptions(Artifact artifact, String symbolicName,
      Properties projectProperties, Map<String, String> additionalOptions)
   {
      PropertiesSource options = chain(toPropertiesSource(projectProperties), toPropertiesSource(additionalOptions));

      final StringBuilder sb = new StringBuilder();
      sb.append(MavenCoreUtils.toArtifactKey(artifact));
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

   private static BundleCandidate newBundleCandidate(MavenProject project)
   {
      final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setLocation(project.getArtifact().getFile());
      bundle.addExtension(MavenCoreUtils.toMavenArtifact(project.getArtifact()));
      return bundle;
   }

   private static MavenArtifact newProjectArtifact(ArtifactFactory artifactFactory, MavenProject project,
      String classifier, String type)
   {
      org.eclipse.aether.artifact.Artifact artifact = artifactFactory.createArtifact(
         RepositoryUtils.toArtifact(project.getArtifact()), classifier, type);

      final String buildDir = project.getBuild().getDirectory();

      final String finalName = project.getBuild().getFinalName();

      final String sourceFinalName = finalName + "-" + artifact.getClassifier();

      final File file = new File(buildDir + "/" + sourceFinalName + "." + artifact.getExtension());

      artifact = artifact.setFile(file);

      return MavenCoreUtils.toMavenArtifact(artifact);
   }

}
