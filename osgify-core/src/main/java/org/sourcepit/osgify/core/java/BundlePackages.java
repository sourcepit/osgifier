/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java;

import java.util.Collection;
import java.util.Map;

public class BundlePackages
{
   private final Collection<String> packages;

   private final PackageReferences referencedPackages;

   private final Map<String, PackageReferences> packageToReferencedPackagesMap;

   public BundlePackages(Collection<String> packages, PackageReferences referencedPackages,
      Map<String, PackageReferences> packageToReferencedPackagesMap)
   {
      this.packages = packages;
      this.referencedPackages = referencedPackages;
      this.packageToReferencedPackagesMap = packageToReferencedPackagesMap;
   }

   public Collection<String> getPackages()
   {
      return packages;
   }

   public PackageReferences getReferencedPackages()
   {
      return referencedPackages;
   }
   
   public PackageReferences getPackagesReferencedBy(String packageName)
   {
      return packageToReferencedPackagesMap.get(packageName);
   }
}
