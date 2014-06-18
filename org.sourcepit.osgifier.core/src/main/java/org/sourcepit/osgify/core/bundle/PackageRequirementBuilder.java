/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import org.sourcepit.osgify.core.model.context.BundleCandidate;

public class PackageRequirementBuilder
{
   private BundleCandidate demandingBundle;

   private String requiredPackage;

   private boolean demandedByInternalPackages, demandedByPublicPackages;

   public void setDemandingBundle(BundleCandidate demandingBundle)
   {
      this.demandingBundle = demandingBundle;
   }

   public void setRequiredPackage(String requiredPackage)
   {
      this.requiredPackage = requiredPackage;
   }

   public void setDemandedByInternalPackages(boolean demandedByInternalPackages)
   {
      this.demandedByInternalPackages = demandedByInternalPackages;
   }

   public void setDemandedByPublicPackages(boolean demandedByPublicPackages)
   {
      this.demandedByPublicPackages = demandedByPublicPackages;
   }

   public PackageRequirement toPackageRequirement()
   {
      return new PackageRequirement(
         demandingBundle,
         requiredPackage,
         demandedByPublicPackages,
         demandedByInternalPackages);
   }
}