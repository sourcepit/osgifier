/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven;

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
import org.sourcepit.osgifier.core.bundle.BundleManifestAppender;
import org.sourcepit.osgifier.core.bundle.BundleManifestAppenderFilter;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.resolve.JavaContentAppender;
import org.sourcepit.osgifier.core.resolve.JavaContentAppenderFilter;
import org.sourcepit.osgifier.core.resolve.NativeManifestAppender;
import org.sourcepit.osgifier.core.resolve.SymbolicNameAndVersionAppender;

@Named
public class OsgifierContextInflator
{
   @Inject
   private JavaContentAppender javaContentAppender;

   @Inject
   private SymbolicNameAndVersionAppender symbolicNameAndVersionAppender;

   @Inject
   private NativeManifestAppender nativeManifestAppender;

   @Inject
   private BundleManifestAppender manifestAppender;

   public void inflate(OsgifierContextInflatorFilter filter, PropertiesSource options, final OsgifierContext osgifierModel,
      Date timestamp)
   {
      options = getOptions(options, timestamp);

      nativeManifestAppender.appendNativeManifests(osgifierModel, filter, options);

      javaContentAppender.appendContents(osgifierModel, JavaContentAppenderFilter.SKIP_NATIVE_AND_SOURCE, options);

      symbolicNameAndVersionAppender.appendSymbolicNamesAndVersion(osgifierModel, options);

      applyManifests(filter, options, osgifierModel);
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

   private void applyManifests(BundleManifestAppenderFilter filter, PropertiesSource options, OsgifierContext osgifierModel)
   {
      manifestAppender.append(osgifierModel, filter, options);

      for (BundleCandidate bundle : osgifierModel.getBundles())
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
      if (extension != null)
      {
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
}
