/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.PackageImport;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentImplementation;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;

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

   public void append(@NotNull BundleCandidate bundle, PropertiesSource options)
   {
      final boolean treatInheritedPackagesAsInternal = options.getBoolean("osgifier.treatInheritedPackagesAsInternal",
         false);

      final Map<String, PackageImport> packageToImport = new HashMap<String, PackageImport>();
      for (PackageResolutionResult result : packageResolver.resolveRequiredPackages(bundle,
         treatInheritedPackagesAsInternal))
      {
         final PackageImport packageImport = packageImportFactory.newPackageImport(bundle, result, options);
         if (packageImport != null)
         {
            packageToImport.put(result.getRequiredPackage(), packageImport);
         }
      }

      for (BundleCandidate embeddedBundle : BundleUtils.getEmbeddedBundles(bundle))
      {
         final List<PackageImport> importPackage = embeddedBundle.getManifest().getImportPackage();
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


}
