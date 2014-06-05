/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static java.util.Collections.unmodifiableCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PackageReferencesBuilder
{
   private final Set<String> implementedPackages = new HashSet<String>();

   private final Set<String> invokedPackages = new HashSet<String>();

   private final Set<String> allPackages = new HashSet<String>();

   public void addImplementedTypes(Collection<String> typeNames)
   {
      for (String typeName : typeNames)
      {
         addImplementedType(typeName);
      }
   }

   public void addImplementedPackages(Collection<String> packageNames)
   {
      for (String packageName : packageNames)
      {
         addImplementedPackage(packageName);
      }
   }

   public void addInvokedTypes(Collection<String> typeNames)
   {
      for (String typeName : typeNames)
      {
         addInvokedType(typeName);
      }
   }

   public void addInvokedPackages(Collection<String> packageNames)
   {
      for (String packageName : packageNames)
      {
         addInvokedPackage(packageName);
      }
   }

   public void addImplementedType(String typeName)
   {
      addImplementedPackage(extractPackageName(typeName));
   }

   public void addInvokedType(String typeName)
   {
      addInvokedPackage(extractPackageName(typeName));
   }

   public void addImplementedPackage(String packageName)
   {
      invokedPackages.remove(packageName);
      implementedPackages.add(packageName);
      allPackages.add(packageName);
   }

   public void addInvokedPackage(String packageName)
   {
      if (!implementedPackages.contains(packageName))
      {
         invokedPackages.add(packageName);
         allPackages.add(packageName);
      }
   }

   public PackageReferences build()
   {
      final List<String> implemented = new ArrayList<String>(implementedPackages);
      final List<String> invoked = new ArrayList<String>(invokedPackages);
      final List<String> all = new ArrayList<String>(allPackages);
      Collections.sort(implemented);
      Collections.sort(invoked);
      Collections.sort(all);
      return new PackageReferences(unmodifiableCollection(implemented), unmodifiableCollection(invoked),
         unmodifiableCollection(all));
   }

   private static String extractPackageName(String typeName)
   {
      final int idx = typeName.lastIndexOf('.');
      return idx > -1 ? typeName.substring(0, idx) : "";
   }
}
