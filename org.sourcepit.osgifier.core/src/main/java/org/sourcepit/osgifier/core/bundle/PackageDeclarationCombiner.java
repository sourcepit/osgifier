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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.DynamicPackageImport;
import org.sourcepit.common.manifest.osgi.PackagesDeclaration;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.manifest.osgi.ParameterType;

public class PackageDeclarationCombiner<T extends PackagesDeclaration>
{
   public static interface PackagesDeclarationFactory<T extends PackagesDeclaration>
   {
      T createPackagesDeclaration();
   }

   public static List<DynamicPackageImport> combineDynamicPackageImports(
      Collection<DynamicPackageImport> dynamicPackageImports)
   {
      return new PackageDeclarationCombiner<DynamicPackageImport>()
      {
         @Override
         protected List<DynamicPackageImport> combine(Collection<DynamicPackageImport> dynImports,
            PackagesDeclarationFactory<DynamicPackageImport> factory)
         {
            List<DynamicPackageImport> combine = super.combine(dynImports, factory);
            for (DynamicPackageImport dynamicPackageImport : combine)
            {
               final List<String> patterns = dynamicPackageImport.getPackageNames();
               final List<String> merged = mergeDynamicPackageImportPatterns(patterns);
               patterns.clear();
               patterns.addAll(merged);
            }
            return combine;
         }
      }.combine(dynamicPackageImports, new PackagesDeclarationFactory<DynamicPackageImport>()
      {
         @Override
         public DynamicPackageImport createPackagesDeclaration()
         {
            return BundleManifestFactory.eINSTANCE.createDynamicPackageImport();
         }
      });
   }

   static List<String> mergeDynamicPackageImportPatterns(Collection<String> patterns)
   {
      final List<String> merged = new ArrayList<String>(patterns.size());

      for (String pattern : patterns)
      {
         final boolean hasGlob = pattern.endsWith("*");
         if (hasGlob)
         {
            final String prefix = pattern.substring(0, pattern.length() - 1);
            addGlob(merged, prefix);
         }
         else
         {
            addValue(merged, pattern);
         }
      }

      final List<String> result = new ArrayList<String>(merged.size());
      for (String pattern : merged)
      {
         if (isPattern(pattern))
         {
            pattern += "*";
         }
         result.add(pattern);
      }

      return result;
   }

   private static void addValue(List<String> merged, String value)
   {
      final Iterator<String> it = merged.iterator();
      while (it.hasNext())
      {
         final String pattern = it.next();
         if (isPattern(pattern))
         {
            if (value.startsWith(pattern))
            {
               return;
            }
         }
      }

      if (!merged.contains(value))
      {
         merged.add(value);
      }
   }

   private static boolean isPattern(final String pattern)
   {
      return pattern.endsWith(".") || "".equals(pattern);
   }

   private static void addGlob(List<String> merged, String prefix)
   {
      final Iterator<String> it = merged.iterator();
      while (it.hasNext())
      {
         final String value = it.next();
         if (value.startsWith(prefix))
         {
            it.remove();
         }
         else if (isPattern(value) && prefix.startsWith(value))
         {
            return;
         }
      }
      merged.add(prefix);
   }

   protected List<T> combine(Collection<T> dynImports, PackagesDeclarationFactory<T> factory)
   {
      final Map<Set<ParameterKey>, Set<String>> parametersToPackageNames = new HashMap<Set<ParameterKey>, Set<String>>();
      for (T dynImport : dynImports)
      {
         final Set<ParameterKey> key = toKey(dynImport.getParameters());
         final List<String> packageNames = dynImport.getPackageNames();
         Set<String> set = parametersToPackageNames.get(key);
         if (set == null)
         {
            set = new HashSet<String>(packageNames.size());
            parametersToPackageNames.put(key, set);
         }
         set.addAll(packageNames);
      }

      final List<T> result = new ArrayList<T>(parametersToPackageNames.size());
      for (Entry<Set<ParameterKey>, Set<String>> entry : parametersToPackageNames.entrySet())
      {
         final List<String> packageNames = new ArrayList<String>(entry.getValue());
         sortPackageNames(packageNames);

         final T packageDeclaration = factory.createPackagesDeclaration();
         packageDeclaration.getPackageNames().addAll(packageNames);
         for (ParameterKey parameterKey : entry.getKey())
         {
            packageDeclaration.getParameters().add(EcoreUtil.copy(parameterKey.parameter));
         }
         result.add(packageDeclaration);
      }
      sortPackageDeclarations(result);
      return result;
   }

   protected void sortPackageDeclarations(final List<T> result)
   {
      Collections.sort(result, new PackagesDeclarationComparator());
   }

   protected void sortPackageNames(final List<String> packageNames)
   {
      Collections.sort(packageNames);
   }

   protected static Set<ParameterKey> toKey(Collection<Parameter> parameters)
   {
      final Set<ParameterKey> keys = new LinkedHashSet<ParameterKey>(parameters.size());
      for (Parameter parameter : parameters)
      {
         keys.add(new ParameterKey(parameter));
      }
      return keys;
   }

   public final static class PackagesDeclarationComparator implements Comparator<PackagesDeclaration>
   {
      @Override
      public int compare(PackagesDeclaration i1, PackagesDeclaration i2)
      {
         final int packageCompare = comparePackages(i1.getPackageNames(), i2.getPackageNames());
         if (packageCompare != 0)
         {
            return packageCompare;
         }
         return compareParameters(i1.getParameters(), i2.getParameters());
      }

      private int comparePackages(final List<String> p1, final List<String> p2)
      {
         if (p1.isEmpty() && !p2.isEmpty())
         {
            return -1;
         }
         if (!p1.isEmpty() && p2.isEmpty())
         {
            return 1;
         }
         final String package1 = p1.get(0);
         final String package2 = p2.get(0);
         return package1.compareTo(package2);
      }

      private int compareParameters(List<Parameter> p1, List<Parameter> p2)
      {
         if (p1.isEmpty() && !p2.isEmpty())
         {
            return -1;
         }
         if (!p1.isEmpty() && p2.isEmpty())
         {
            return 1;
         }
         final String package1 = p1.get(0).getName();
         final String package2 = p2.get(0).getName();
         return package1.compareTo(package2);
      }
   }

   public static class ParameterKey
   {
      final Parameter parameter;

      public ParameterKey(Parameter parameter)
      {
         this.parameter = parameter;
      }

      @Override
      public int hashCode()
      {
         final String name = parameter.getName();
         final String value = parameter.getValue();
         final ParameterType type = parameter.getType();

         final int prime = 31;
         int result = 1;
         result = prime * result + ((name == null) ? 0 : name.hashCode());
         result = prime * result + ((type == null) ? 0 : type.hashCode());
         result = prime * result + ((value == null) ? 0 : value.hashCode());
         return result;
      }

      @Override
      public boolean equals(Object obj)
      {
         final String name = parameter.getName();
         final String value = parameter.getValue();
         final ParameterType type = parameter.getType();

         if (this == obj)
            return true;
         if (obj == null)
            return false;
         if (getClass() != obj.getClass())
            return false;
         final ParameterKey other = (ParameterKey) obj;
         final String otherNname = other.parameter.getName();
         final String otherValue = other.parameter.getValue();
         final ParameterType otherType = other.parameter.getType();
         if (name == null)
         {
            if (otherNname != null)
               return false;
         }
         else if (!name.equals(otherNname))
            return false;
         if (type != otherType)
            return false;
         if (value == null)
         {
            if (otherValue != null)
               return false;
         }
         else if (!value.equals(otherValue))
            return false;
         return true;
      }


   }
}
