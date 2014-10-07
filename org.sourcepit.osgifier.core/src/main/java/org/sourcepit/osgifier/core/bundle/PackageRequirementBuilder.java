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

import org.sourcepit.osgifier.core.model.context.BundleCandidate;

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
      return new PackageRequirement(demandingBundle, requiredPackage, demandedByPublicPackages,
         demandedByInternalPackages);
   }
}