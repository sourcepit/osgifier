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

import static java.util.Collections.unmodifiableCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RequiredPackageBuilder
{
   private final Set<String> inheritedPackages = new HashSet<String>();

   private final Set<String> invokedPackages = new HashSet<String>();

   private final Set<String> allPackages = new HashSet<String>();

   public void addInheritedTypes(Collection<String> typeNames)
   {
      for (String typeName : typeNames)
      {
         addInheritedType(typeName);
      }
   }

   public void addInheritedPackages(Collection<String> packageNames)
   {
      for (String packageName : packageNames)
      {
         addInheritedPackage(packageName);
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

   public void addInheritedType(String typeName)
   {
      addInheritedPackage(extractPackageName(typeName));
   }

   public void addInvokedType(String typeName)
   {
      addInvokedPackage(extractPackageName(typeName));
   }

   public void addInheritedPackage(String packageName)
   {
      invokedPackages.remove(packageName);
      inheritedPackages.add(packageName);
      allPackages.add(packageName);
   }

   public void addInvokedPackage(String packageName)
   {
      if (!inheritedPackages.contains(packageName))
      {
         invokedPackages.add(packageName);
         allPackages.add(packageName);
      }
   }

   public RequiredPackages build()
   {
      final List<String> inherited = new ArrayList<String>(inheritedPackages);
      final List<String> invoked = new ArrayList<String>(invokedPackages);
      final List<String> all = new ArrayList<String>(allPackages);
      Collections.sort(inherited);
      Collections.sort(invoked);
      Collections.sort(all);
      return new RequiredPackages(unmodifiableCollection(inherited), unmodifiableCollection(invoked),
         unmodifiableCollection(all));
   }

   private static String extractPackageName(String typeName)
   {
      final int idx = typeName.lastIndexOf('.');
      return idx > -1 ? typeName.substring(0, idx) : "";
   }
}
