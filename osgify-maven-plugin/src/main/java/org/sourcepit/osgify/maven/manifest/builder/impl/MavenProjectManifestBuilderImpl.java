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

import org.apache.maven.RepositoryUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.manifest.osgi.BundleManifest;
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
import org.sourcepit.osgify.maven.OsgifyContextInflator;
import org.sourcepit.osgify.maven.manifest.builder.ManifestBuilderResult;
import org.sourcepit.osgify.maven.manifest.builder.MavenProjectManifestBuilder;

import com.google.common.base.Strings;

/**
 * 
 * @author DerGilb
 *
 */
public class MavenProjectManifestBuilderImpl implements MavenProjectManifestBuilder
{
   public static final String DEF_SOURCE_CLASSIFIER = "sources";

   private final MavenProject project;
   private final OsgifyContext osgifyContext;
   private final BundleCandidate projectBundle;
   private final ConfigurableOsgifyContextInflatorFilter inflatorFilter;
   private String symbolicName;
   private Date timestamp;
   private boolean wasBuilt;
   private boolean attachSourceBundle = true;
   private String sourceClassifier = DEF_SOURCE_CLASSIFIER;
   private Map<String, String> additionalOptions = new HashMap<String, String>();

   private ArtifactFactory artifactFactory;
   private VersionRangeResolver versionRangeResolver;
   private OsgifyContextInflator inflater;

   public MavenProjectManifestBuilderImpl(MavenProject project, ArtifactFactory artifactFactory,
      VersionRangeResolver versionRangeResolver, OsgifyContextInflator inflater)
   {
      super();
      this.project = project;
      this.symbolicName = project.getArtifactId();
      this.artifactFactory = artifactFactory;
      this.versionRangeResolver = versionRangeResolver;
      this.inflater = inflater;
      this.osgifyContext = buildOsgifyContext();
      this.projectBundle = resolveProjectBundle(osgifyContext);
      this.inflatorFilter = buildInflatorFilter(projectBundle);
   }

   private OsgifyContext buildOsgifyContext()
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

   private BundleCandidate resolveProjectBundle(OsgifyContext context)
   {
      return context.getBundles().get(0);
   }

   private ConfigurableOsgifyContextInflatorFilter buildInflatorFilter(BundleCandidate projectBundle)
   {
      ConfigurableOsgifyContextInflatorFilter filter = new ConfigurableOsgifyContextInflatorFilter(projectBundle);
      return filter;
   }

   @Override
   public ManifestBuilderResult build()
   {
      if (wasBuilt())
      {
         // TODO can only be built once
         throw new IllegalStateException("The Manifest build can only be invoked once");
      }
      try
      {
         if (attachSourceBundle)
         {
            attachSourceBundle();
         }
         final BundleManifest manifest = buildManifest();

         // TODO: Q: headermodifications y/n
         // TODO: Q: project.setContextValue("osgifier.manifestFile", manifestFile); ?


         ManifestBuilderResultImpl result = new ManifestBuilderResultImpl(manifest);
         if (attachSourceBundle)
         {
            BundleCandidate sourceBundle = projectBundle.getSourceBundle();
            BundleManifest sourceBundleManifest = sourceBundle.getManifest();
            result.setSourceBundleManifest(sourceBundleManifest);
         }


         return result;
      }
      finally
      {
         this.wasBuilt = true;
      }
   }


   private BundleManifest buildManifest()
   {
      final PropertiesSource osgifyOptions = buildOsgifyOptions();
      final Date startTime = buildStartTime();

      inflater.inflate(inflatorFilter, osgifyOptions, osgifyContext, startTime);

      return projectBundle.getManifest();
   }

   private Date buildStartTime()
   {
      return timestamp != null ? timestamp : new Date();
   }

   private PropertiesSource buildOsgifyOptions()
   {
      Properties projectProperties = project.getProperties();

      PropertiesSource options = chain(toPropertiesSource(projectProperties), toPropertiesSource(additionalOptions));

      final StringBuilder sb = new StringBuilder();
      sb.append(MavenCoreUtils.toArtifactKey(project.getArtifact()));
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


   private void attachSourceBundle()
   {
      final MavenArtifact sourceArtifact = newProjectArtifact( sourceClassifier, "jar");

      BundleCandidate sourceBundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      sourceBundle.setLocation(sourceArtifact.getFile());
      sourceBundle.addExtension(sourceArtifact);

      osgifyContext.getBundles().add(sourceBundle);

      sourceBundle.setTargetBundle(projectBundle);
      projectBundle.setSourceBundle(sourceBundle);
   }


   private static BundleCandidate newBundleCandidate(MavenProject project)
   {
      final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setLocation(project.getArtifact().getFile());
      bundle.addExtension(MavenCoreUtils.toMavenArtifact(project.getArtifact()));
      return bundle;
   }

   private MavenArtifact newProjectArtifact( String classifier, String type)
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

   @Override
   public MavenProjectManifestBuilder attachSources(String sourceClassifier)
   {
      attachSourceBundle(true);
      this.sourceClassifier = sourceClassifier;

      return this;
   }


   @Override
   public MavenProject getProject()
   {
      return project;
   }

   @Override
   public MavenProjectManifestBuilder attachSourceBundle(boolean withSources)
   {
      this.attachSourceBundle = withSources;
      return this;
   }

   @Override
   public MavenProjectManifestBuilder attachOption(String key, String value)
   {
      this.additionalOptions.put(key, value);
      return this;
   }

   @Override
   public MavenProjectManifestBuilder attachOptions(Map<String, String> options)
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
   public boolean wasBuilt()
   {
      return wasBuilt;
   }

   @Override
   public MavenProjectManifestBuilder setAppendExecutionEnvironment(boolean append)
   {
      inflatorFilter.setAppendExecutionEnvironment(append);
      return this;
   }

   @Override
   public MavenProjectManifestBuilder setAppendPackageExports(boolean append)
   {
      inflatorFilter.setAppendPackageExports(append);
      return this;
   }

   @Override
   public MavenProjectManifestBuilder setAppendPackageImports(boolean append)
   {
      inflatorFilter.setAppendPackageImports(append);
      return this;
   }

   @Override
   public MavenProjectManifestBuilder setAppendDynamicImports(boolean append)
   {
      inflatorFilter.setAppendDynamicImports(append);
      return this;
   }

}
