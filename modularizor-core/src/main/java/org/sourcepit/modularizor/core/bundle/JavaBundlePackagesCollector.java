/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.bundle;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.java.JavaResourcesRoot;

public class JavaBundlePackagesCollector
{
   private final JavaResourceBundle javaBundle;

   private Set<String> bundlePackages;

   public JavaBundlePackagesCollector(JavaResourceBundle javaBundle)
   {
      this.javaBundle = javaBundle;
   }

   public JavaResourceBundle getJavaBundle()
   {
      return javaBundle;
   }

   public void collect()
   {
      bundlePackages = new HashSet<String>();
      for (JavaResourcesRoot resourcesRoot : javaBundle.getResourcesRoots())
      {
         for (JavaPackage jPackage : resourcesRoot.getPackages())
         {
            collectPackageNames(bundlePackages, jPackage);
         }
      }
      bundlePackages = unmodifiableSet(bundlePackages);
   }

   public Set<String> getBundlePackages()
   {
      return bundlePackages;
   }

   private static void collectPackageNames(Set<String> packageNames, JavaPackage jPackage)
   {
      if (!jPackage.getFiles().isEmpty())
      {
         packageNames.add(jPackage.getQualifiedName());
      }
      for (JavaPackage javaPackage : jPackage.getPackages())
      {
         collectPackageNames(packageNames, javaPackage);
      }
   }
}
