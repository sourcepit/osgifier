/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaPackageBundle;
import org.sourcepit.osgify.core.model.java.JavaPackageRoot;
import org.sourcepit.osgify.core.model.java.JavaTypeRoot;

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
   public String resolveSymbolicName(BundleCandidate bundleCandidate)
   {
      JavaPackageBundle jContent = bundleCandidate.getContent();
      if (jContent != null)
      {
         return resolveSymbolicName(jContent);
      }
      return null;
   }

   private String resolveSymbolicName(JavaPackageBundle jContent)
   {
      List<JavaPackage> entryPackages = new ArrayList<JavaPackage>();
      for (JavaPackageRoot jPackageRoot : jContent.getPackageRoots())
      {
         collectFirstPackagesWithTypes(entryPackages, jPackageRoot.getRootPackages());
      }

      if (entryPackages.isEmpty())
      {
         return null;
      }

      if (entryPackages.size() == 1)
      {
         return entryPackages.get(0).getFullyQualifiedName();
      }
      int rate = -1;
      JavaPackage winnerPackage = null;
      for (JavaPackage entryPackage : entryPackages)
      {
         int current = ratePackage(entryPackage);
         if (current > rate)
         {
            rate = current;
            winnerPackage = entryPackage;
         }
      }
      return winnerPackage == null ? null : winnerPackage.getFullyQualifiedName();
   }

   private int ratePackage(JavaPackage entryPackage)
   {
      List<JavaPackage> subPackages = new ArrayList<JavaPackage>();
      List<JavaTypeRoot> types = new ArrayList<JavaTypeRoot>();
      collect(subPackages, types, entryPackage);
      return subPackages.size() + types.size();
   }

   private void collect(List<JavaPackage> subPackages, List<JavaTypeRoot> types, JavaPackage javaPackage)
   {
      if (!"internal".equals(javaPackage.getSimpleName()))
      {
         types.addAll(javaPackage.getTypeRoots());
         for (JavaPackage subPackage : javaPackage.getPackages())
         {
            subPackages.add(subPackage);
            collect(subPackages, types, subPackage);
         }
      }
   }

   private void collectFirstPackagesWithTypes(List<JavaPackage> resultBag, EList<JavaPackage> javaPackages)
   {
      for (JavaPackage javaPackage : javaPackages)
      {
         if (javaPackage.getTypeRoots().isEmpty())
         {
            collectFirstPackagesWithTypes(resultBag, javaPackage.getPackages());
         }
         else
         {
            resultBag.add(javaPackage);
         }
      }
   }

}
