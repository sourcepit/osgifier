/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.util.OsgifyContextUtils;
import org.sourcepit.osgify.core.util.OsgifyContextUtils.BuildOrder;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class BundleManifestAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(BundleManifestAppender.class);

   @Inject
   private RequiredExecutionEnvironmentAppender environmentAppender;

   @Inject
   private PackageExportAppender packageExports;

   @Inject
   private DynamicPackageImportAppender dynamicImports;

   @Inject
   private PackageImportAppender packageImports;

   public void append(OsgifyContext context, BundleManifestAppenderFilter filter, PropertiesSource options)
   {
      final BuildOrder buildOrder = OsgifyContextUtils.computeBuildOrder(context);

      for (List<BundleCandidate> cycle : buildOrder.getCycles())
      {
         final StringBuilder sb = new StringBuilder();
         for (BundleCandidate cyclicBundle : cycle)
         {
            sb.append(getBundleKey(cyclicBundle));
            sb.append(" - ");
         }
         sb.deleteCharAt(sb.length() - 1);
         sb.deleteCharAt(sb.length() - 1);
         sb.deleteCharAt(sb.length() - 1);
         LOGGER.warn("Detected cycle {}", sb.toString());
      }

      final List<BundleCandidate> bundleCandidates = buildOrder.getOrderedBundles();
      for (BundleCandidate bundleCandidate : bundleCandidates)
      {
         if (bundleCandidate.isNativeBundle())
         {
            LOGGER.info("Skipping manifest creation for native bundle " + bundleCandidate.getSymbolicName() + "_"
               + bundleCandidate.getVersion());
         }
         else
         {
            LOGGER.info("Building manifest for bundle candidate " + bundleCandidate.getSymbolicName() + "_"
               + bundleCandidate.getVersion());
            append(bundleCandidate, filter, options);
         }
      }
   }

   private void append(BundleCandidate bundle, BundleManifestAppenderFilter filter, PropertiesSource options)
   {
      initManifest(bundle);

      if (bundle.getTargetBundle() != null) // source bundle
      {
         final BundleCandidate targetBundle = bundle.getTargetBundle();
         final Version version = targetBundle.getVersion();
         final BundleManifest manifest = bundle.getManifest();
         manifest.setHeader("Eclipse-SourceBundle", targetBundle.getSymbolicName() + ";version=\"" + version
            + "\";roots:=\".\"");
      }
      else
      {
         if (filter.isAppendExecutionEnvironment(bundle, options))
         {
            environmentAppender.append(bundle, options);
         }
         if (filter.isAppendPackageExports(bundle, options))
         {
            packageExports.append(options, bundle);
         }
         if (filter.isAppendPackageImports(bundle, options))
         {
            packageImports.append(bundle, options);
         }
         if (filter.isAppendDynamicImports(bundle, options))
         {
            dynamicImports.append(bundle);
         }
      }
   }

   private void initManifest(BundleCandidate bundle)
   {
      BundleManifest manifest = bundle.getManifest();
      if (manifest == null)
      {
         manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
         manifest.setBundleSymbolicName(bundle.getSymbolicName());
         manifest.setBundleVersion(bundle.getVersion());
         bundle.setManifest(manifest);
      }
   }

   private static String getBundleKey(BundleCandidate bundle)
   {
      return bundle.getSymbolicName() + "_" + bundle.getVersion().toString();
   }
}
