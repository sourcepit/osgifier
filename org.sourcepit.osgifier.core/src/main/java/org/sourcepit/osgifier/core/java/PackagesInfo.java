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
