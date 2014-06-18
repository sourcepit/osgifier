/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.resolve;

import static org.sourcepit.osgifier.core.resolve.PackageCollector.firstWithTypesOrSubPackages;
import static org.sourcepit.osgifier.core.resolve.PackageCollector.getDepth;
import static org.sourcepit.osgifier.core.resolve.PackageCollector.getDepthOfFirstPackageWithTypes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;

/**
 * @author Bernd
 */
@Named("InspectJavaPackageNames")
public class InspectJavaPackageNames extends AbstractSymbolicNameResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.NORMAL;
   }

   @Override
   public String resolveSymbolicName(BundleCandidate bundleCandidate, PropertiesSource options)
   {
      JavaResourceBundle jBundle = bundleCandidate.getContent();
      if (jBundle != null)
      {
         return resolveSymbolicName(jBundle);
      }
      return null;
   }


   private String resolveSymbolicName(JavaResourceBundle jBundle)
   {
      final int depth = getDepthOfFirstPackageWithTypes(jBundle, 1, -1);

      final int minDepth = depth - 1;
      final int maxDepth = depth + 1;

      final List<JavaPackage> roots = firstWithTypesOrSubPackages(jBundle, minDepth, maxDepth);
      return roots.isEmpty() ? null : resolveSymbolicName(roots, depth);
   }

   private String resolveSymbolicName(final List<JavaPackage> roots, final int depth)
   {
      JavaPackage chosen = ratePackages(roots, roots.get(0), true);
      if (chosen.getJavaFiles().isEmpty())
      {
         int newDepth = getDepthOfFirstPackageWithTypes(chosen, getDepth(chosen), -1);
         if (newDepth > depth)
         {
            return resolveSymbolicName(firstWithTypesOrSubPackages(chosen, newDepth - 1, newDepth + 1), newDepth);
         }
         return byPackageRoots(Collections.singletonList(chosen));
      }
      return byTypeRoots(Collections.singletonList(chosen));
   }

   private String byTypeRoots(final List<JavaPackage> typeRoots)
   {
      final JavaPackage typeRoot = ratePackages(typeRoots, typeRoots.get(0), true);

      int typeCount = typeRoot.getJavaFiles().size();
      for (JavaPackage javaPackage : typeRoot.getPackages())
      {
         typeCount += getTypeCount(javaPackage);
      }

      final int typesPerPkg = Math.round(typeCount / (typeRoot.getPackages().size() + 1));

      if (typeRoot.getJavaFiles().size() * 1.8 >= typesPerPkg)
      {
         return typeRoot.getQualifiedName();
      }
      else
      {
         final JavaPackage fatSubPackage = getFatSubPackage(typeRoot);
         return fatSubPackage == null ? typeRoot.getQualifiedName() : fatSubPackage.getQualifiedName();
      }
   }

   private String byPackageRoots(final List<JavaPackage> pkgRoots)
   {
      final JavaPackage pkgRoot = ratePackages(pkgRoots, pkgRoots.get(0), true);
      final JavaPackage fatSubPackage = getFatSubPackage(pkgRoot);
      return fatSubPackage == null ? pkgRoot.getQualifiedName() : fatSubPackage.getQualifiedName();
   }

   private JavaPackage getFatSubPackage(JavaPackage jPackage)
   {
      final int typesPerPkg = Math.round(getTypeCount(jPackage) / jPackage.getPackages().size());
      final List<JavaPackage> fatSubPackages = new ArrayList<JavaPackage>();
      for (JavaPackage subPackage : jPackage.getPackages())
      {
         if (getTypeCount(subPackage) >= typesPerPkg * 1.2)
         {
            fatSubPackages.add(subPackage);
         }
      }
      return fatSubPackages.size() == 1 ? fatSubPackages.get(0) : null;
   }

   private int getTypeCount(JavaPackage javaPackage)
   {
      int typeCount = javaPackage.getJavaFiles().size();

      for (JavaPackage subPackage : javaPackage.getPackages())
      {
         typeCount += getTypeCount(subPackage);
      }

      return typeCount;
   }

   private JavaPackage ratePackages(List<JavaPackage> jPackages, JavaPackage fallback, boolean rateTypes)
   {
      boolean rateChanged = false;
      int rate = -1;
      JavaPackage winnerPackage = null;

      for (JavaPackage entryPackage : jPackages)
      {
         int current = ratePackage(entryPackage, true, rateTypes);
         if (rate != -1 && current != rate)
         {
            rateChanged = true;
         }
         if (current > rate)
         {
            rate = current;
            winnerPackage = entryPackage;
         }
      }

      return !rateChanged || winnerPackage == null ? fallback : winnerPackage;
   }

   private int ratePackage(JavaPackage entryPackage, boolean bySubPackages, boolean byTypes)
   {
      List<JavaPackage> subPackages = new ArrayList<JavaPackage>();
      List<JavaFile> types = new ArrayList<JavaFile>();
      collect(subPackages, types, entryPackage);

      if (bySubPackages && byTypes)
      {
         return subPackages.size() + types.size();
      }
      else if (bySubPackages)
      {
         return subPackages.size();
      }
      else if (byTypes)
      {
         return types.size();
      }
      return -1;
   }

   private void collect(List<JavaPackage> subPackages, List<JavaFile> types, JavaPackage javaPackage)
   {
      types.addAll(javaPackage.getJavaFiles());

      for (JavaPackage subPackage : javaPackage.getPackages())
      {

         if (!subPackage.getJavaFiles().isEmpty())
         {
            subPackages.add(subPackage);
         }
         collect(subPackages, types, subPackage);
      }
   }
}
