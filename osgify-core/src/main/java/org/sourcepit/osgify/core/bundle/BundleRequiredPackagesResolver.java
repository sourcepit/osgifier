/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static java.util.Collections.unmodifiableList;
import static org.sourcepit.osgify.core.ee.AccessRule.ACCESSIBLE;
import static org.sourcepit.osgify.core.ee.AccessRule.NON_ACCESSIBLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;

public class BundleRequiredPackagesResolver
{
   private final ExecutionEnvironmentService environmentService;

   private final BundleCandidate bundle;

   private final Map<String, List<PackageReference>> packageReferences = new HashMap<String, List<PackageReference>>();

   public BundleRequiredPackagesResolver(BundleCandidate bundle, ExecutionEnvironmentService environmentService)
   {
      this.environmentService = environmentService;
      this.bundle = bundle;
   }

   public void resolve(List<String> requiredPackages)
   {
      // resolve possible package references
      for (String packageName : requiredPackages)
      {
         packageReferences.put(packageName, unmodifiableList(resolvePackage(packageName)));
      }
   }

   public BundleCandidate getBundle()
   {
      return bundle;
   }

   public List<PackageReference> getPossiblePackageReferences(String requiredPackage)
   {
      final List<PackageReference> result = packageReferences.get(requiredPackage);
      return result == null ? Collections.<PackageReference> emptyList() : result;
   }

   private List<PackageReference> resolvePackage(String packageName)
   {
      final List<PackageReference> exportDescriptions = new ArrayList<PackageReference>();

      BundleManifest manifest = bundle.getManifest();
      
      PackageExport packageExport = resolvePackage(manifest.getExportPackage(), packageName);
      if (packageExport != null)
      {
         exportDescriptions.add(new PackageReference(bundle, packageName, null, bundle, packageExport, ACCESSIBLE));
      }

      final EList<String> executionEnvironments = bundle.getManifest().getBundleRequiredExecutionEnvironment();
      if (executionEnvironments != null)
      {
         final AccessRule accessRule = environmentService.getAccessRuleById(executionEnvironments, packageName);
         if (accessRule != NON_ACCESSIBLE)
         {
            exportDescriptions.add(new PackageReference(bundle, packageName, null, null, null, accessRule));
         }
      }
      
      for (BundleReference bundleReference : bundle.getDependencies())
      {
         final BundleCandidate target = bundleReference.getTarget();

         final BundleManifest targetManifest = target.getManifest();
         if (targetManifest == null) // HACK happens when we have cyclic references
         {
            continue;
         }

         packageExport = resolvePackage(targetManifest.getExportPackage(), packageName);
         if (packageExport != null)
         {
            exportDescriptions.add(new PackageReference(bundle, packageName, bundleReference, target, packageExport,
               ACCESSIBLE));
         }
      }
      
      return exportDescriptions;
   }

   private PackageExport resolvePackage(EList<PackageExport> exportPackage, String packageName)
   {
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            if (packageExport.getPackageNames().contains(packageName))
            {
               return packageExport;
            }
         }
      }
      return null;
   }
}