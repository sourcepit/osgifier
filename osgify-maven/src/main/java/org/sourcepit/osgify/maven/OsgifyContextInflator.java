/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.bundle.BundleManifestAppender;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.JavaContentAppender;
import org.sourcepit.osgify.core.resolve.JavaContentAppenderFilter;
import org.sourcepit.osgify.core.resolve.NativeManifestAppender;
import org.sourcepit.osgify.core.resolve.NativeManifestAppenderFilter;
import org.sourcepit.osgify.core.resolve.SymbolicNameAndVersionAppender;

@Named
public class OsgifyContextInflator
{
   @Inject
   private JavaContentAppender javaContentAppender;

   @Inject
   private SymbolicNameAndVersionAppender symbolicNameAndVersionAppender;

   @Inject
   private NativeManifestAppender nativeManifestAppender;

   @Inject
   private BundleManifestAppender manifestAppender;

   public void infalte(final ManifestGeneratorFilter generatorFilter, PropertiesSource options,
      final OsgifyContext osgifyModel, Date timestamp)
   {
      options = getOptions(options, timestamp);

      nativeManifestAppender.appendNativeManifests(osgifyModel, new NativeManifestAppenderFilter()
      {
         @Override
         public boolean isAppendNativeManifest(BundleCandidate bundle, BundleManifest manifest, PropertiesSource options)
         {
            return !generatorFilter.isOverrideNativeBundle(bundle, manifest, options);
         }
      }, options);

      // required to determine whether do scan java content
      javaContentAppender.appendContents(osgifyModel, JavaContentAppenderFilter.SKIP_NATIVE_AND_SOURCE, options);

      symbolicNameAndVersionAppender.appendSymbolicNamesAndVersion(osgifyModel, options);

      applyManifests(generatorFilter, options, osgifyModel);
   }

   private PropertiesSource getOptions(final PropertiesSource options, Date timestamp)
   {
      final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
      format.setTimeZone(TimeZone.getTimeZone("UTC"));
      final String ctxQualifier = format.format(timestamp);
      return new AbstractPropertiesSource()
      {
         @Override
         public String get(String key)
         {
            if ("osgifier.forceContextQualifier".equals(key))
            {
               return ctxQualifier;
            }
            return options == null ? null : options.get(key);
         }
      };
   }

   private void applyManifests(ManifestGeneratorFilter generatorFilter, PropertiesSource options,
      OsgifyContext osgifyModel)
   {
      manifestAppender.append(osgifyModel, options);

      for (BundleCandidate bundle : osgifyModel.getBundles())
      {
         if (!bundle.isNativeBundle())
         {
            appendMavenHeaders(bundle);
         }
      }
   }

   private void appendMavenHeaders(BundleCandidate bundle)
   {
      final MavenArtifact extension = bundle.getExtension(MavenArtifact.class);
      final BundleManifest manifest = bundle.getManifest();
      manifest.setHeader("Maven-GroupId", extension.getGroupId());
      manifest.setHeader("Maven-ArtifactId", extension.getArtifactId());
      manifest.setHeader("Maven-Type", extension.getType());
      final String classifier = extension.getClassifier();
      if (!isNullOrEmpty(classifier))
      {
         manifest.setHeader("Maven-Classifier", classifier);
      }
      manifest.setHeader("Maven-Version", extension.getVersion());
   }
}
