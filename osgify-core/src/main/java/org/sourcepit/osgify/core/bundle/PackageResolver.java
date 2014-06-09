/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.osgify.core.bundle.AccessRestriction.DISCOURAGED;
import static org.sourcepit.osgify.core.bundle.AccessRestriction.NONE;
import static org.sourcepit.osgify.core.bundle.PackageExporterType.OWN_BUNDLE;
import static org.sourcepit.osgify.core.bundle.PackageExporterType.REQUIRED_BUNDLE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;

@Named
public class PackageResolver
{
   private ExecutionEnvironmentService environmentService;

   @Inject
   public PackageResolver(ExecutionEnvironmentService environmentService)
   {
      this.environmentService = environmentService;
   }

   public final List<PackageResolutionResult> resolveRequiredPackages(BundleCandidate bundle,
      boolean treatInheritedPackagesAsInternal)
   {
      final Collection<String> referencedPackages = getReferencedPackages(bundle);

      final Map<String, List<PackageExportDescription>> packageToExportDescriptions = new LinkedHashMap<String, List<PackageExportDescription>>(
         referencedPackages.size());
      for (String requiredPackage : referencedPackages)
      {
         final List<PackageExportDescription> exportes = resolve(bundle, requiredPackage);
         if (exportes.isEmpty())
         {
            final boolean hiddenBundlePackage = getBundlePackages(bundle).getPackages().contains(requiredPackage);
            if (!hiddenBundlePackage)
            {
               packageToExportDescriptions.put(requiredPackage, exportes);
            }
         }
         else
         {
            packageToExportDescriptions.put(requiredPackage, exportes);
         }
      }

      final Map<BundleCandidate, AccessRestriction> bundleToAccessRestriction = new HashMap<BundleCandidate, AccessRestriction>();

      for (Entry<String, List<PackageExportDescription>> entry : packageToExportDescriptions.entrySet())
      {
         if (!entry.getValue().isEmpty())
         {
            final String requiredPackage = entry.getKey();
            final PackageExportDescription selectedExporter = entry.getValue().get(0);
            updateBundleAccessRestriction(bundleToAccessRestriction, bundle, requiredPackage, selectedExporter,
               treatInheritedPackagesAsInternal);
         }
      }

      final List<PackageResolutionResult> result = new ArrayList<PackageResolutionResult>();

      for (Entry<String, List<PackageExportDescription>> entry : packageToExportDescriptions.entrySet())
      {
         final String requiredPackage = entry.getKey();
         final List<PackageExportDescription> exporters = entry.getValue();
         final PackageExportDescription selectedExporter = exporters.isEmpty() ? null : exporters.get(0);

         final AccessRestriction accessRestriction;
         if (selectedExporter == null)
         {
            accessRestriction = null;
         }
         else
         {
            switch (selectedExporter.getExporterType())
            {
               case OWN_BUNDLE :
               case REQUIRED_BUNDLE :
                  accessRestriction = bundleToAccessRestriction.get(selectedExporter.getBundle());
                  break;
               case EXECUTION_ENVIRONMENT :
                  accessRestriction = NONE;
                  break;
               case VENDOR :
                  accessRestriction = DISCOURAGED;
                  break;
               default :
                  throw new IllegalStateException();
            }
         }

         result.add(new PackageResolutionResult(requiredPackage, selectedExporter, accessRestriction, exporters));
      }

      return result;
   }

   private void updateBundleAccessRestriction(
      final Map<BundleCandidate, AccessRestriction> bundleToAccessRestrictionMap, BundleCandidate bundle,
      final String requiredPackage, final PackageExportDescription exportDescription,
      boolean treatInheritedPackagesAsInternal)
   {
      if (exportDescription != null)
      {
         final PackageExporterType exporterType = exportDescription.getExporterType();
         if (exporterType == REQUIRED_BUNDLE || exporterType == OWN_BUNDLE)
         {
            final BundleCandidate requiredBundle = exportDescription.getBundle();

            final AccessRestriction currentAccessRestriction = bundleToAccessRestrictionMap.get(requiredBundle);
            if (currentAccessRestriction == null || currentAccessRestriction == NONE)
            {
               final PackageExport packageExport = exportDescription.getPackageExport();

               AccessRestriction access = BundleUtils.isInternalPackage(packageExport) ? DISCOURAGED : NONE;

               if (access == NONE && treatInheritedPackagesAsInternal)
               {
                  // if our bundle implements classes from a public package we assume that we are providing an api
                  // implementation
                  final boolean roleProvider = getBundlePackages(bundle).getReferencedPackages().getInherited()
                     .contains(requiredPackage);
                  if (roleProvider)
                  {
                     access = DISCOURAGED;
                  }
               }
               bundleToAccessRestrictionMap.put(requiredBundle, access);
            }
         }
      }
   }

   private List<PackageExportDescription> resolve(BundleCandidate bundle, String requiredPackage)
   {
      final List<PackageExportDescription> exporters = new ArrayList<PackageExportDescription>();

      // self
      {
         final PackageExport packageExport = getPackageExport(bundle, requiredPackage);
         if (packageExport != null)
         {
            exporters.add(PackageExportDescription.exportedByOwnBundle(bundle, packageExport));
         }
      }

      // dependencies
      for (BundleReference bundleReference : bundle.getDependencies())
      {
         final BundleCandidate requiredBundle = bundleReference.getTarget();

         final PackageExport packageExport = getPackageExport(requiredBundle, requiredPackage);
         if (packageExport != null)
         {
            exporters.add(PackageExportDescription.exportedByRequiredBundle(bundleReference, packageExport));
         }
      }

      // ee or vendor
      switch (getAccessRule(bundle, requiredPackage))
      {
         case ACCESSIBLE : // ee
            exporters.add(PackageExportDescription.exportedByExecutionEnvironment());
            break;
         case DISCOURAGED : // vendor
            exporters.add(PackageExportDescription.exportedByVendor());
            break;
         case NON_ACCESSIBLE : // neither nor
            break;
         default :
            throw new IllegalStateException();
      }

      return exporters;
   }

   private Collection<String> getReferencedPackages(BundleCandidate bundle)
   {
      final BundlePackages bundlePackages = getBundlePackages(bundle);

      final Collection<String> requiredPackges = new ArrayList<String>(bundlePackages.getReferencedPackages().getAll());
      removeEEPackages(bundle, requiredPackges);

      // add self imports after removing ee packages to add ee packages contributed by our bundle also
      final List<PackageExport> exportPackage = bundle.getManifest().getExportPackage();
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            requiredPackges.addAll(packageExport.getPackageNames());
         }
      }

      return requiredPackges;
   }

   private final WeakHashMap<BundleCandidate, BundlePackages> cache = new WeakHashMap<BundleCandidate, BundlePackages>();

   private BundlePackages getBundlePackages(BundleCandidate bundle)
   {
      BundlePackages result = cache.get(bundle);
      if (result == null)
      {
         result = new BundlePackagesCollector().collect(bundle);
         cache.put(bundle, result);
      }
      return result;
   }

   private void removeEEPackages(BundleCandidate bundle, final Collection<String> requiredPackges)
   {
      final List<String> requiredEEs = bundle.getManifest().getBundleRequiredExecutionEnvironment();
      if (requiredEEs != null)
      {
         for (String ee : requiredEEs)
         {
            requiredPackges.removeAll(environmentService.getExecutionEnvironment(ee).getPackages());
         }
      }
   }

   private static final PackageExport getPackageExport(BundleCandidate requiredBundle, String requiredPackage)
   {
      final List<PackageExport> exportPackage = requiredBundle.getManifest().getExportPackage();
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            if (packageExport.getPackageNames().contains(requiredPackage))
            {
               return packageExport;
            }
         }
      }
      return null;
   }

   private AccessRule getAccessRule(BundleCandidate bundle, String requiredPackage)
   {
      final List<String> requiredEEs = bundle.getManifest().getBundleRequiredExecutionEnvironment();
      if (requiredEEs != null)
      {
         return environmentService.getAccessRuleById(requiredEEs, requiredPackage);
      }
      return AccessRule.NON_ACCESSIBLE;
   }

}
