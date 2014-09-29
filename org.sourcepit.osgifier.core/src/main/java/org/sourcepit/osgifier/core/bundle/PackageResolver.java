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

import static org.sourcepit.osgifier.core.bundle.AccessRestriction.DISCOURAGED;
import static org.sourcepit.osgifier.core.bundle.AccessRestriction.NONE;
import static org.sourcepit.osgifier.core.bundle.PackageExporterType.OWN_BUNDLE;
import static org.sourcepit.osgifier.core.bundle.PackageExporterType.REQUIRED_BUNDLE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgifier.core.ee.ExecutionEnvironmentImplementation;
import org.sourcepit.osgifier.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgifier.core.java.PackagesInfo;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;

@Named
public class PackageResolver
{
   private final BundlePackagesService bundlePackages;

   private final ExecutionEnvironmentService environmentService;

   @Inject
   public PackageResolver(BundlePackagesService bundlePackages, ExecutionEnvironmentService environmentService)
   {
      this.bundlePackages = bundlePackages;
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
            final boolean hiddenBundlePackage = bundlePackages.getPackagesInfo(bundle).getContainedPackages()
               .contains(requiredPackage);
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
                  final boolean roleProvider = bundlePackages.getPackagesInfo(bundle).getRequiredPackages()
                     .getInherited().contains(requiredPackage);
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

      // ee or vendor
      final List<String> requiredEEs = bundle.getManifest().getBundleRequiredExecutionEnvironment();
      if (requiredEEs != null)
      {
         if (environmentService.getIntersectingPackagesByIds(requiredEEs).contains(requiredPackage))
         {
            exporters.add(PackageExportDescription.exportedByExecutionEnvironment());
         }

         for (ExecutionEnvironmentImplementation vendor : environmentService.getExecutionEnvironmentImplementations())
         {
            if (requiredEEs.contains(vendor.getExecutionEnvironmentId())
               && vendor.getVendorPackages().contains(requiredPackage))
            {
               exporters.add(PackageExportDescription.exportedByVendor());
               break;
            }
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

      return exporters;
   }

   private Collection<String> getReferencedPackages(BundleCandidate bundle)
   {
      final PackagesInfo packagesInfo = bundlePackages.getPackagesInfo(bundle);

      final Collection<String> requiredPackges = new ArrayList<String>(packagesInfo.getRequiredPackages().getAll());

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


}
