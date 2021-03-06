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

package org.sourcepit.osgifier.core.resolve;

import java.util.ArrayList;
import java.util.List;

import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;

public abstract class PackageCollector {
   private static class FirstPackagesWithTypes extends PackageCollector {
      @Override
      protected boolean select(JavaPackage jPackage) {
         return !jPackage.getJavaFiles().isEmpty();
      }
   }

   private static class FirstPackagesWithTypesOrSubPackages extends PackageCollector {
      @Override
      protected boolean select(JavaPackage jPackage) {
         if (jPackage.getPackages().size() > 1) {
            return true;
         }
         if (!jPackage.getJavaFiles().isEmpty()) {
            return true;
         }
         return false;
      }
   }

   private final static PackageCollector FIRST_WITH_TYPES = new FirstPackagesWithTypes();

   private final static PackageCollector FIRST_WITH_TYPES_OR_SUBPACKAGES = new FirstPackagesWithTypesOrSubPackages();

   public static List<JavaPackage> firstWithTypes(JavaResourceBundle jBundle, int minDepth, int maxDepth) {
      return FIRST_WITH_TYPES.collect(jBundle, minDepth, maxDepth);
   }

   public static List<JavaPackage> firstWithTypes(JavaPackage jPackage, int minDepth, int maxDepth) {
      final List<JavaPackage> packages = new ArrayList<JavaPackage>();
      FIRST_WITH_TYPES.collect(packages, jPackage, getDepth(jPackage), minDepth, maxDepth);
      return packages;
   }

   public static List<JavaPackage> firstWithTypesOrSubPackages(JavaResourceBundle jBundle, int minDepth, int maxDepth) {
      return FIRST_WITH_TYPES_OR_SUBPACKAGES.collect(jBundle, minDepth, maxDepth);
   }

   public static List<JavaPackage> firstWithTypesOrSubPackages(JavaPackage jPackage, int minDepth, int maxDepth) {
      final List<JavaPackage> packages = new ArrayList<JavaPackage>();
      FIRST_WITH_TYPES_OR_SUBPACKAGES.collect(packages, jPackage, getDepth(jPackage), minDepth, maxDepth);
      return packages;
   }

   public List<JavaPackage> collect(JavaResourceBundle jBundle, int minDepth, int maxDepth) {
      final List<JavaPackage> packages = new ArrayList<JavaPackage>();
      for (JavaResourcesRoot jRoot : jBundle.getResourcesRoots()) {
         collect(packages, jRoot, minDepth, maxDepth);
      }
      return packages;
   }

   protected void collect(final List<JavaPackage> packages, JavaResourcesRoot jRoot, int minDepth, int maxDepth) {
      for (JavaPackage jPackage : jRoot.getPackages()) {
         collect(packages, jPackage, 1, minDepth, maxDepth);
      }
   }

   protected void collect(List<JavaPackage> packages, JavaPackage jPackage, int depth, int minDepth, int maxDepth) {
      if (isInRange(minDepth, maxDepth, depth) && select(jPackage)) {
         packages.add(jPackage);
      }
      else if (maxDepth < 0 || depth <= maxDepth) {
         for (JavaPackage subPackage : jPackage.getPackages()) {
            collect(packages, subPackage, depth + 1, minDepth, maxDepth);
         }
      }
   }

   protected boolean isInRange(int minDepth, int maxDepth, int depth) {
      return depth >= minDepth && (maxDepth < 0 || depth <= maxDepth);
   }

   protected abstract boolean select(JavaPackage jPackage);


   public static int getDepthOfFirstPackageWithTypes(JavaResourceBundle jBundle, int minDepth, int maxDepth) {
      int depth = -1;

      for (JavaPackage jPackage : firstWithTypes(jBundle, minDepth, maxDepth)) {
         int pkgDepth = getDepth(jPackage);
         if (depth == -1) {
            depth = pkgDepth;
         }
         else {
            depth = Math.min(depth, pkgDepth);
         }
      }

      return depth;
   }

   public static int getDepthOfFirstPackageWithTypes(JavaPackage jPackage, int minDepth, int maxDepth) {
      int depth = -1;

      for (JavaPackage pkg : firstWithTypes(jPackage, minDepth, maxDepth)) {
         int pkgDepth = getDepth(pkg);
         if (depth == -1) {
            depth = pkgDepth;
         }
         else {
            depth = Math.min(depth, pkgDepth);
         }
      }

      return depth;
   }

   public static int getDepth(JavaPackage jPackage) {
      int depth = 1;
      JavaPackage parent = jPackage.getParentPackage();
      while (parent != null) {
         depth++;
         parent = parent.getParentPackage();
      }
      return depth;
   }
}