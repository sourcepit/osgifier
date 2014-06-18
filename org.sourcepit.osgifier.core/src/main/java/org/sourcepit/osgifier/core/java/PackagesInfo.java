/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.java;

import java.util.Collection;
import java.util.Map;

public class PackagesInfo
{
   private final Collection<String> packages;

   private final RequiredPackages requiredPackages;

   private final Map<String, RequiredPackages> packageToRequiredPackagesMap;

   public PackagesInfo(Collection<String> packages, RequiredPackages referencedPackages,
      Map<String, RequiredPackages> packageToRequiredPackagesMap)
   {
      this.packages = packages;
      this.requiredPackages = referencedPackages;
      this.packageToRequiredPackagesMap = packageToRequiredPackagesMap;
   }

   public Collection<String> getContainedPackages()
   {
      return packages;
   }

   public RequiredPackages getRequiredPackages()
   {
      return requiredPackages;
   }

   public RequiredPackages getRequiredPackagesOf(String packageName)
   {
      return packageToRequiredPackagesMap.get(packageName);
   }
}
