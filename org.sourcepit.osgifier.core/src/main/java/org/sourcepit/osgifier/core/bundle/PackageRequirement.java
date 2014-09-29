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

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

public class PackageRequirement implements Comparable<PackageRequirement>
{
   private final BundleCandidate demandingBundle;

   private final String requiredPackage;

   private final boolean demandedByInternalPackages, demandedByPublicPackages;

   public PackageRequirement(@NotNull BundleCandidate demandingBundle, @NotNull String requiredPackage,
      boolean demandedByPublicPackages, boolean demandedByInternalPackages)
   {
      this.demandingBundle = demandingBundle;
      this.requiredPackage = requiredPackage;
      this.demandedByInternalPackages = demandedByInternalPackages;
      this.demandedByPublicPackages = demandedByPublicPackages;
   }

   public BundleCandidate getDemandingBundle()
   {
      return demandingBundle;
   }

   public String getRequiredPackage()
   {
      return requiredPackage;
   }

   public boolean isDemandedByInternalPackages()
   {
      return demandedByInternalPackages;
   }

   public boolean isDemandedByPublicPackages()
   {
      return demandedByPublicPackages;
   }

   @Override
   public int compareTo(PackageRequirement o)
   {
      final BundleCandidate bundle1 = getDemandingBundle();
      final BundleCandidate bundle2 = o.getDemandingBundle();

      final String symbolicName1 = bundle1.getSymbolicName();
      final String symbolicName2 = bundle2.getSymbolicName();

      final int res = compareTo(symbolicName1, symbolicName2);
      if (res != 0)
      {
         return res;
      }
      return compareTo(getRequiredPackage(), o.getRequiredPackage());
   }

   private int compareTo(String str1, String str2)
   {
      if (str1 == null)
      {
         return str2 == null ? 0 : -1;
      }
      return str1.compareTo(str2);
   }

   @Override
   public String toString()
   {
      final StringBuilder builder = new StringBuilder();
      builder.append("PackageRequirement [demandingBundle=");
      builder.append(demandingBundle == null ? null : demandingBundle.getSymbolicName());
      builder.append(", requiredPackage=");
      builder.append(requiredPackage);
      builder.append(", demandedByInternalPackages=");
      builder.append(demandedByInternalPackages);
      builder.append(", demandedByPublicPackages=");
      builder.append(demandedByPublicPackages);
      builder.append("]");
      return builder.toString();
   }
}