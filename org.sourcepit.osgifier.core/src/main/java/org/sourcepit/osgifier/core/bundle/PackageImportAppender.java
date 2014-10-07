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

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleRequirement;
import org.sourcepit.common.manifest.osgi.PackageImport;
import org.sourcepit.common.utils.path.PathMatcher;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.ee.ExecutionEnvironment;
import org.sourcepit.osgifier.core.ee.ExecutionEnvironmentImplementation;
import org.sourcepit.osgifier.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.util.OptionsUtils;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class PackageImportAppender
{
   private final PackageResolver packageResolver;

   private final ExecutionEnvironmentService environmentService;

   private final PackageImportFactory packageImportFactory;

   @Inject
   public PackageImportAppender(PackageResolver packageResolver, ExecutionEnvironmentService environmentService,
      PackageImportFactory packageImportFactory)
   {
      this.packageResolver = packageResolver;
      this.environmentService = environmentService;
      this.packageImportFactory = packageImportFactory;
   }

   private static final class RequireBundleInfo
   {
      AccessRestriction accessRestriction;

      boolean optional;

      void updateAccessRestriction(AccessRestriction accessRestriction)
      {
         if (this.accessRestriction == null || this.accessRestriction == AccessRestriction.NONE
            && accessRestriction == AccessRestriction.DISCOURAGED)
         {
            this.accessRestriction = accessRestriction;
         }
      }

      void updateOptional(boolean optional)
      {
         if (!this.optional && optional)
         {
            this.optional = optional;
         }
      }
   }

   public void append(@NotNull BundleCandidate bundle, PropertiesSource options)
   {
      final Map<BundleCandidate, RequireBundleInfo> bundleToRequireBundleInfo = new HashMap<BundleCandidate, RequireBundleInfo>();
      final Map<String, PackageImport> packageToImport = new HashMap<String, PackageImport>();
      collectRequirements(bundleToRequireBundleInfo, packageToImport, bundle, options);

      final Map<String, BundleRequirement> symbolicNameToBundleRequirement = new HashMap<String, BundleRequirement>(
         bundleToRequireBundleInfo.size());

      for (Entry<BundleCandidate, RequireBundleInfo> entry : bundleToRequireBundleInfo.entrySet())
      {
         final RequireBundleInfo info = entry.getValue();
         final BundleCandidate requiredBundle = entry.getKey();
         final BundleRequirement bundleRequirement = packageImportFactory.newRequireBundle(bundle, requiredBundle,
            info.optional, info.accessRestriction, options);
         if (bundleRequirement != null)
         {
            symbolicNameToBundleRequirement.put(requiredBundle.getSymbolicName(), bundleRequirement);
         }
      }

      collectRequirementsOfEmbeddedBundles(packageToImport, symbolicNameToBundleRequirement, bundle);

      if (!symbolicNameToBundleRequirement.isEmpty())
      {
         final List<String> symbolicNames = new ArrayList<String>(symbolicNameToBundleRequirement.keySet());
         Collections.sort(symbolicNames);

         final List<BundleRequirement> requireBundle = bundle.getManifest().getRequireBundle(true);
         for (String symbolicName : symbolicNames)
         {
            requireBundle.add(symbolicNameToBundleRequirement.get(symbolicName));
         }
      }

      if (!packageToImport.isEmpty())
      {
         final List<String> requiredPackages = new ArrayList<String>(packageToImport.keySet());
         Collections.sort(requiredPackages);

         final List<PackageImport> importPackage = bundle.getManifest().getImportPackage(true);
         for (String packageName : requiredPackages)
         {
            importPackage.add(packageToImport.get(packageName));
         }

         // erase ee and vendor package versions
         if (importPackage != null)
         {
            final Set<String> eePackages = new HashSet<String>();

            for (ExecutionEnvironment executionEnvironment : environmentService.getExecutionEnvironments())
            {
               eePackages.addAll(executionEnvironment.getPackages());
            }

            for (ExecutionEnvironmentImplementation vendors : environmentService
               .getExecutionEnvironmentImplementations())
            {
               eePackages.addAll(vendors.getVendorPackages());
            }

            for (PackageImport packageImport : importPackage)
            {
               for (String packageName : packageImport.getPackageNames())
               {
                  if (eePackages.contains(packageName))
                  {
                     packageImport.setVersion(null);
                     break;
                  }
               }
            }
         }
      }
   }

   private void collectRequirementsOfEmbeddedBundles(final Map<String, PackageImport> packageToImport,
      final Map<String, BundleRequirement> bundleToRequirement, BundleCandidate bundle)
   {
      for (BundleCandidate embeddedBundle : BundleUtils.getEmbeddedBundles(bundle))
      {
         final BundleManifest manifest = embeddedBundle.getManifest();

         final List<BundleRequirement> requireBundle = manifest.getRequireBundle();
         if (requireBundle != null)
         {
            for (BundleRequirement bundleRequirement : requireBundle)
            {
               for (String symbolicName : bundleRequirement.getSymbolicNames())
               {
                  if (!bundleToRequirement.containsKey(symbolicName))
                  {
                     final BundleRequirement copy = EcoreUtil.copy(bundleRequirement);
                     copy.getSymbolicNames().clear();
                     copy.getSymbolicNames().add(symbolicName);
                     bundleToRequirement.put(symbolicName, copy);
                  }
               }
            }
         }

         final List<PackageImport> importPackage = manifest.getImportPackage();
         if (importPackage != null)
         {
            for (PackageImport packageImport : importPackage)
            {
               for (String packageName : packageImport.getPackageNames())
               {
                  if (!packageToImport.containsKey(packageName))
                  {
                     final PackageImport copy = EcoreUtil.copy(packageImport);
                     copy.getPackageNames().clear();
                     copy.getPackageNames().add(packageName);
                     packageToImport.put(packageName, copy);
                  }
               }
            }
         }
      }
   }

   private void collectRequirements(final Map<BundleCandidate, RequireBundleInfo> bundleToRequireBundleInfo,
      final Map<String, PackageImport> packageToImport, BundleCandidate bundle, PropertiesSource options)
   {
      final boolean treatInheritedPackagesAsInternal = options.getBoolean("osgifier.treatInheritedPackagesAsInternal",
         false);
      for (PackageResolutionResult result : packageResolver.resolveRequiredPackages(bundle,
         treatInheritedPackagesAsInternal))
      {
         final BundleCandidate exportingBundle = getExportingBundle(result);
         if (exportingBundle != null && bundle != exportingBundle && isRequireBundle(exportingBundle, options))
         {
            processRequireBundle(bundleToRequireBundleInfo, bundle, result.getSelectedExporter().getBundleReference(),
               result.getAccessRestriction(), options);
         }
         else
         {
            processPackageImport(packageToImport, bundle, result, options);
         }
      }
   }

   private void processRequireBundle(Map<BundleCandidate, RequireBundleInfo> requiredBundleToAccessRestriction,
      BundleCandidate bundle, BundleReference reference, AccessRestriction accessRestriction, PropertiesSource options)
   {
      final BundleCandidate requiredBundle = reference.getTarget();
      RequireBundleInfo info = requiredBundleToAccessRestriction.get(requiredBundle);
      if (info == null)
      {
         info = new RequireBundleInfo();
         requiredBundleToAccessRestriction.put(requiredBundle, info);
      }
      info.updateAccessRestriction(accessRestriction);
      info.updateOptional(reference.isOptional());
   }

   private void processPackageImport(final Map<String, PackageImport> packageToImport, BundleCandidate bundle,
      PackageResolutionResult result, PropertiesSource options)
   {
      final PackageImport packageImport = packageImportFactory.newPackageImport(bundle, result, options);
      if (packageImport != null)
      {
         packageToImport.put(result.getRequiredPackage(), packageImport);
      }
   }

   private static boolean isRequireBundle(BundleCandidate bundle, PropertiesSource options)
   {
      final String string = options.get("osgifier.requireBundle");
      if (isNullOrEmpty(string))
      {
         return false;
      }

      final String symbolicName = bundle.getManifest().getBundleSymbolicName().getSymbolicName();

      final List<String> list = OptionsUtils.parseListValue(string);
      if (list.contains(symbolicName))
      {
         return true;
      }
      for (String pattern : list)
      {
         if (PathMatcher.parsePackagePatterns(pattern).isMatch(symbolicName))
         {
            return true;
         }
      }

      return false;
   }

   private static BundleCandidate getExportingBundle(PackageResolutionResult result)
   {
      final PackageExportDescription exporter = result.getSelectedExporter();
      if (exporter == null)
      {
         return null;
      }
      return exporter.getBundle();
   }


}
