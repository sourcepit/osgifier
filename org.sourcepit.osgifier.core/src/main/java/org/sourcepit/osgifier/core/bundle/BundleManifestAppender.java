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

package org.sourcepit.osgifier.core.bundle;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleLocalization;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.util.OsgifierContextUtils;
import org.sourcepit.osgifier.core.util.OsgifierContextUtils.BuildOrder;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class BundleManifestAppender {
   private static final Logger LOGGER = LoggerFactory.getLogger(BundleManifestAppender.class);

   @Inject
   private RequiredExecutionEnvironmentAppender environmentAppender;

   @Inject
   private PackageExportAppender packageExports;

   @Inject
   private DynamicPackageImportAppender dynamicImports;

   @Inject
   private PackageImportAppender packageImports;

   @Inject
   private List<BundleHeadersAppender> headersAppenders;

   @Inject
   private RecommendedImportPolicyAppender importPolicyAppender;

   public void append(OsgifierContext context, BundleManifestAppenderFilter filter, PropertiesSource options) {
      final BuildOrder buildOrder = OsgifierContextUtils.computeBuildOrder(context);

      for (List<BundleCandidate> cycle : buildOrder.getCycles()) {
         final StringBuilder sb = new StringBuilder();
         for (BundleCandidate cyclicBundle : cycle) {
            sb.append(getBundleKey(cyclicBundle));
            sb.append(" - ");
         }
         sb.deleteCharAt(sb.length() - 1);
         sb.deleteCharAt(sb.length() - 1);
         sb.deleteCharAt(sb.length() - 1);
         LOGGER.warn("Detected cycle {}", sb.toString());
      }

      final List<BundleCandidate> bundleCandidates = buildOrder.getOrderedBundles();
      for (BundleCandidate bundleCandidate : bundleCandidates) {
         if (bundleCandidate.isNativeBundle()) {
            LOGGER.info("Skipping manifest creation for native bundle " + bundleCandidate.getSymbolicName() + "_"
               + bundleCandidate.getVersion());
         }
         else {
            LOGGER.info("Building manifest for bundle candidate " + bundleCandidate.getSymbolicName() + "_"
               + bundleCandidate.getVersion());
            append(bundleCandidate, filter, options);

            for (BundleHeadersAppender headersAppender : headersAppenders) {
               headersAppender.append(bundleCandidate, filter, options);
            }
         }
      }
   }

   private void append(BundleCandidate bundle, BundleManifestAppenderFilter filter, PropertiesSource options) {
      initManifest(bundle);

      if (bundle.getTargetBundle() != null) // source bundle
      {
         final BundleCandidate targetBundle = bundle.getTargetBundle();
         final Version version = targetBundle.getVersion();
         final BundleManifest manifest = bundle.getManifest();
         manifest.setHeader("Eclipse-SourceBundle", targetBundle.getSymbolicName() + ";version=\"" + version
            + "\";roots:=\".\"");
      }
      else {
         if (filter.isAppendExecutionEnvironment(bundle, options)) {
            environmentAppender.append(bundle, options);
         }
         if (filter.isAppendPackageExports(bundle, options)) {
            packageExports.append(bundle, options);
         }
         if (filter.isAppendPackageImports(bundle, options)) {
            packageImports.append(bundle, options);
         }
         if (filter.isAppendDynamicImports(bundle, options)) {
            dynamicImports.append(bundle);
         }
         if (filter.isAppendRecommendedImportPolicy(bundle, options)) {
            importPolicyAppender.append(bundle, options);
         }
      }
   }

   private void initManifest(BundleCandidate bundle) {
      BundleManifest manifest = bundle.getManifest();
      if (manifest == null) {
         manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
         manifest.setBundleSymbolicName(bundle.getSymbolicName());
         manifest.setBundleVersion(bundle.getVersion());
         bundle.setManifest(manifest);
      }

      BundleLocalization localization = bundle.getLocalization();
      if (localization == null) {
         localization = ContextModelFactory.eINSTANCE.createBundleLocalization();
         bundle.setLocalization(localization);
      }
   }

   private static String getBundleKey(BundleCandidate bundle) {
      return bundle.getSymbolicName() + "_" + bundle.getVersion().toString();
   }
}
