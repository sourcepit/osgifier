/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

import static org.sourcepit.common.utils.props.PropertiesSources.chain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.merge.ManifestMerger;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.artifact.MavenArtifactUtils;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSources;
import org.sourcepit.osgifier.core.headermod.HeaderModifications;
import org.sourcepit.osgifier.core.headermod.HeaderModifier;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.OsgifyContext;
import org.sourcepit.osgifier.core.resolve.VersionRangeResolver;
import org.sourcepit.osgify.maven.ArtifactManifestBuilder;
import org.sourcepit.osgify.maven.ArtifactManifestBuilderRequest;
import org.sourcepit.osgify.maven.ArtifactManifestBuilderResult;
import org.sourcepit.osgify.maven.DefaultOsgifyContextInflatorFilter;
import org.sourcepit.osgify.maven.OsgifyContextInflator;
import org.sourcepit.osgify.maven.OsgifyContextInflatorFilter;

import com.google.common.base.Strings;

/**
 * 
 * @author DerGilb
 * @author Bernd
 */
@Named
public class ArtifactManifestBuilderImpl implements ArtifactManifestBuilder
{
   private final VersionRangeResolver versionRangeResolver;
   private final OsgifyContextInflator inflater;
   private final HeaderModifier headerModifier;

   @Inject
   public ArtifactManifestBuilderImpl(VersionRangeResolver versionRangeResolver, OsgifyContextInflator inflater,
      HeaderModifier headerModifier)
   {
      this.versionRangeResolver = versionRangeResolver;
      this.inflater = inflater;
      this.headerModifier = headerModifier;
   }

   @Override
   public ArtifactManifestBuilderResult buildManifest(ArtifactManifestBuilderRequest request)
   {
      final Artifact projectArtifact = request.getArtifact();
      final List<Artifact> projectDependencies = request.getDependencies();

      final OsgifyContext osgifyContext = buildOsgifyContext(projectArtifact, projectDependencies, versionRangeResolver);
      final BundleCandidate projectBundle = osgifyContext.getBundles().get(0);

      final OsgifyContextInflatorFilter inflatorFilter = newInflatorFilter(projectBundle);

      final Artifact sourceArtifact = request.getSourceArtifact();
      if (sourceArtifact != null)
      {
         final MavenArtifact source = MavenArtifactUtils.toMavenArtifact(sourceArtifact);

         final BundleCandidate sourceBundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
         sourceBundle.setLocation(source.getFile());
         sourceBundle.addExtension(source);
         sourceBundle.setTargetBundle(projectBundle);
         projectBundle.setSourceBundle(sourceBundle);
         osgifyContext.getBundles().add(sourceBundle);
      }

      String symbolicName = request.getSymbolicName();
      symbolicName = Strings.isNullOrEmpty(symbolicName) ? buildSymbolicName(projectArtifact) : symbolicName;

      final PropertiesSource osgifyOptions = buildOsgifyOptions(projectArtifact, symbolicName, request.getOptions());

      final Date timestamp = request.getTimestamp();

      final Date startTime = timestamp == null ? new Date() : timestamp;

      inflater.inflate(inflatorFilter, osgifyOptions, osgifyContext, startTime);

      final BundleManifest manifest = projectBundle.getManifest();

      final Manifest mergeManifest = request.getManifestToMerge();
      if (mergeManifest != null)
      {
         new ManifestMerger().merge(manifest, mergeManifest);
      }

      final HeaderModifications headerModifications = request.getHeaderModifications();
      if (headerModifications != null)
      {
         headerModifier.applyModifications(manifest, headerModifications);
      }

      final ArtifactManifestBuilderResult result = new ArtifactManifestBuilderResult();
      result.setBundleManifest(manifest);

      if (sourceArtifact != null)
      {
         final BundleCandidate sourceBundle = projectBundle.getSourceBundle();
         final BundleManifest sourceBundleManifest = sourceBundle.getManifest();
         result.setSourceBundleManifest(sourceBundleManifest);
      }

      return result;
   }

   private static String buildSymbolicName(Artifact project)
   {
      return project.getGroupId() + "." + project.getArtifactId();
   }

   private static OsgifyContext buildOsgifyContext(Artifact artifact, Collection<Artifact> dependencies,
      VersionRangeResolver versionRangeResolver)
   {
      final BundleCandidate projectBundle = newBundleCandidate(artifact);

      final OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().add(projectBundle);

      for (Artifact dependency : dependencies)
      {
         final BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
         reference.addExtension(MavenArtifactUtils.toMavenDependecy(dependency));
         reference.setOptional(dependency.isOptional());
         reference.setProvided(DefaultArtifact.SCOPE_PROVIDED.equals(dependency.getScope()));
         reference.setVersionRange(versionRangeResolver.resolveVersionRange(reference));

         final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
         bundle.setLocation(dependency.getFile());
         bundle.addExtension(MavenArtifactUtils.toMavenDependecy(dependency));

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
      return inflatorFilter;
   }

   private static PropertiesSource buildOsgifyOptions(Artifact artifact, String symbolicName, PropertiesSource options)
   {
      options = options == null ? PropertiesSources.emptyPropertiesSource() : options;

      final StringBuilder sb = new StringBuilder();
      sb.append(MavenArtifactUtils.toArtifactKey(artifact));
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

   private static BundleCandidate newBundleCandidate(Artifact artifact)
   {
      final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setLocation(artifact.getFile());
      bundle.addExtension(MavenArtifactUtils.toMavenArtifact(artifact));
      return bundle;
   }

}
