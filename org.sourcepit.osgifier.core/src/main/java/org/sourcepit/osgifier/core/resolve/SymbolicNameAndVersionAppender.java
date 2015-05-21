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

package org.sourcepit.osgifier.core.resolve;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

@Named
public class SymbolicNameAndVersionAppender {
   @Inject
   private SymbolicNameResolver symbolicNameResolver;

   @Inject
   private VersionResolver versionResolver;

   @Inject
   private SymbolicNameConflictResolver nameConflictResolver;

   public void appendSymbolicNamesAndVersion(OsgifierContext osgifierContext, PropertiesSource options) {
      final Map<String, BundleCandidate> keyToBundle = new HashMap<String, BundleCandidate>();
      final List<BundleCandidate> sourceBundles = new ArrayList<BundleCandidate>();

      final List<BundleCandidate> bundles = new ArrayList<BundleCandidate>();

      // register native keys we cannot override
      for (BundleCandidate bundle : osgifierContext.getBundles()) {
         if (bundle.isNativeBundle()) {
            keyToBundle.put(getBundleKey(bundle), bundle);
         }
         else {
            bundles.add(bundle);
         }
      }

      // for all non-native bundles
      for (BundleCandidate bundle : bundles) {
         if (bundle.getTargetBundle() != null) {
            sourceBundles.add(bundle);
         }
         else {
            final BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
            bundle.setManifest(manifest);

            final String symbolicName = symbolicNameResolver.resolveSymbolicName(bundle, options);
            checkState(symbolicName != null, "Failed to determine bundle symbolic name for %s", bundle.getLocation());
            manifest.setBundleSymbolicName(symbolicName);
            bundle.setSymbolicName(symbolicName);

            final Version version = versionResolver.resolveVersion(bundle, options);
            checkState(version != null, "Failed to determine bundle version for %s", bundle.getLocation());
            manifest.setBundleVersion(version);
            bundle.setVersion(version);

            final String bundleKey = getBundleKey(bundle);

            final BundleCandidate conflictBundle = keyToBundle.get(bundleKey);
            if (conflictBundle == null) {
               keyToBundle.put(bundleKey, bundle);
            }
            else {
               final List<String> conflictNames;
               if (conflictBundle.isNativeBundle()) {
                  conflictNames = Collections.singletonList(conflictBundle.getSymbolicName());
               }
               else {
                  conflictNames = symbolicNameResolver.resolveSymbolicNames(conflictBundle, options);
               }

               final List<String> names = symbolicNameResolver.resolveSymbolicNames(bundle, options);
               if (nameConflictResolver.resolveNameConflict(conflictBundle, conflictNames, bundle, names)) {
                  keyToBundle.remove(bundleKey);
                  keyToBundle.put(getBundleKey(conflictBundle), conflictBundle);
                  keyToBundle.put(getBundleKey(bundle), bundle);
               }
               else {
                  // TODO panic!
               }
            }
         }
      }

      for (BundleCandidate bundle : sourceBundles) {
         final BundleCandidate targetBundle = bundle.getTargetBundle();
         final String symbolicName = targetBundle.getSymbolicName();
         final Version version = targetBundle.getVersion();

         final BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
         bundle.setManifest(manifest);

         manifest.getBundleSymbolicName(true).setSymbolicName(symbolicName + ".source");
         bundle.setSymbolicName(symbolicName + ".source");

         manifest.setBundleVersion(version);
         bundle.setVersion(version);
      }
   }

   private static String getBundleKey(BundleCandidate bundle) {
      return bundle.getSymbolicName() + "_" + bundle.getVersion().toString();
   }

}
