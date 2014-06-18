/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
public class SymbolicNameAndVersionAppender
{
   @Inject
   private SymbolicNameResolver symbolicNameResolver;

   @Inject
   private VersionResolver versionResolver;

   @Inject
   private SymbolicNameConflictResolver nameConflictResolver;

   public void appendSymbolicNamesAndVersion(OsgifierContext osgifierContext, PropertiesSource options)
   {
      final Map<String, BundleCandidate> keyToBundle = new HashMap<String, BundleCandidate>();
      final List<BundleCandidate> sourceBundles = new ArrayList<BundleCandidate>();

      final List<BundleCandidate> bundles = new ArrayList<BundleCandidate>();

      // register native keys we cannot override
      for (BundleCandidate bundle : osgifierContext.getBundles())
      {
         if (bundle.isNativeBundle())
         {
            keyToBundle.put(getBundleKey(bundle), bundle);
         }
         else
         {
            bundles.add(bundle);
         }
      }

      // for all non-native bundles
      for (BundleCandidate bundle : bundles)
      {
         if (bundle.getTargetBundle() != null)
         {
            sourceBundles.add(bundle);
         }
         else
         {
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
            if (conflictBundle == null)
            {
               keyToBundle.put(bundleKey, bundle);
            }
            else
            {
               final List<String> conflictNames;
               if (conflictBundle.isNativeBundle())
               {
                  conflictNames = Collections.singletonList(conflictBundle.getSymbolicName());
               }
               else
               {
                  conflictNames = symbolicNameResolver.resolveSymbolicNames(conflictBundle, options);
               }

               final List<String> names = symbolicNameResolver.resolveSymbolicNames(bundle, options);
               if (nameConflictResolver.resolveNameConflict(conflictBundle, conflictNames, bundle, names))
               {
                  keyToBundle.remove(bundleKey);
                  keyToBundle.put(getBundleKey(conflictBundle), conflictBundle);
                  keyToBundle.put(getBundleKey(bundle), bundle);
               }
               else
               {
                  // TODO panic!
               }
            }
         }
      }

      for (BundleCandidate bundle : sourceBundles)
      {
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

   private static String getBundleKey(BundleCandidate bundle)
   {
      return bundle.getSymbolicName() + "_" + bundle.getVersion().toString();
   }

}
