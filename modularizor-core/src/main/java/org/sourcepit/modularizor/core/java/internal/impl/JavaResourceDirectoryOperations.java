/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.java.internal.impl;

import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.utils.collections.CollectionUtils;
import org.sourcepit.modularizor.core.model.java.JavaFile;
import org.sourcepit.modularizor.core.model.java.JavaModelFactory;
import org.sourcepit.modularizor.core.model.java.JavaPackage;
import org.sourcepit.modularizor.core.model.java.JavaResourceDirectory;
import org.sourcepit.modularizor.core.model.java.JavaType;
import org.sourcepit.modularizor.core.model.java.Resource;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaResourceDirectoryOperations
{
   public static EList<JavaPackage> getPackages(JavaResourceDirectory jResourcesContainer)
   {
      final EList<JavaPackage> result = new BasicEList<JavaPackage>();
      CollectionUtils.addAll(result, jResourcesContainer.getResources(), JavaPackage.class);
      return ECollections.unmodifiableEList(result);
   }

   public static JavaPackage getPackage(@NotNull JavaResourceDirectory jDir, @NotNull String qualifiedName,
      boolean createOnDemand)
   {
      final String[] names = qualifiedName.replace('/', '.').split("\\.");

      EList<Resource> pkgs = jDir.getResources();
      JavaPackage pkg = null;
      for (String name : names)
      {
         pkg = getPackage(pkgs, name, createOnDemand);
         if (pkg == null)
         {
            break;
         }
         pkgs = pkg.getResources();
      }
      return pkg;
   }

   private static JavaPackage getPackage(EList<Resource> pkgs, String name, boolean createOnDemand)
   {
      for (Resource pkg : pkgs)
      {
         if (pkg instanceof JavaPackage && name.equals(pkg.getName()))
         {
            return (JavaPackage) pkg;
         }
      }
      if (createOnDemand)
      {
         final JavaPackage pkg = JavaModelFactory.eINSTANCE.createJavaPackage();
         pkg.setName(name);
         pkgs.add(pkg);
         return pkg;
      }
      return null;
   }

   public static EList<JavaFile> getJavaFiles(JavaResourceDirectory jDir)
   {
      final EList<JavaFile> result = new BasicEList<JavaFile>();
      CollectionUtils.addAll(result, jDir.getResources(), JavaFile.class);
      return ECollections.unmodifiableEList(result);
   }

   public static JavaFile getJavaFile(JavaResourceDirectory jDir, String name, boolean createOnDemand)
   {
      JavaFile jFile = (JavaFile) jDir.getFile(name);
      if (jFile == null && createOnDemand)
      {
         switch (jDir.getResourcesType())
         {
            case BIN :
               jFile = JavaModelFactory.eINSTANCE.createJavaClass();
               break;
            case SRC :
               jFile = JavaModelFactory.eINSTANCE.createJavaCompilationUnit();
               break;
            default :
               throw new IllegalStateException();
         }
         jFile.setName(name);
         jDir.getResources().add(jFile);
      }
      return jFile;
   }

   public static JavaType getType(JavaResourceDirectory jDir, @NotNull String name, boolean createOnDemand)
   {
      final String[] names = name.replace('$', '.').split("\\.");
      JavaFile jFile = getJavaFile(jDir, names[0], createOnDemand);
      if (jFile == null)
      {
         return null;
      }

      JavaType jType = jFile.getType();
      JavaType outer = null;

      for (String typeName : names)
      {
         if (outer == null)
         {
            jType = getRootType(jFile, typeName, createOnDemand);
         }
         else
         {
            EList<JavaType> innerTypes = outer.getInnerTypes();
            for (JavaType innerType : innerTypes)
            {
               if (typeName.equals(innerType.getName()))
               {
                  jType = innerType;
               }
            }
            if (jType == null && createOnDemand)
            {
               jType = JavaModelFactory.eINSTANCE.createJavaType();
               jType.setName(typeName);
               innerTypes.add(jType);
            }
         }

         if (jType == null)
         {
            return null;
         }
         outer = jType;
         jType = null;
      }

      return outer;
   }

   private static JavaType getRootType(JavaFile jFile, String typeName, boolean createOnDemand)
   {
      JavaType jType = jFile.getType();
      if (createOnDemand)
      {
         if (jType == null)
         {
            jType = JavaModelFactory.eINSTANCE.createJavaType();
            jType.setName(typeName);
            jFile.setType(jType);
         }
         else if (!typeName.equals(jType.getName()))
         {
            throw new IllegalStateException("Cannot create type (" + typeName + ") because another type ("
               + jType.getName() + ") is already set in java file " + jFile.getName());
         }
      }
      return jType;
   }
}
