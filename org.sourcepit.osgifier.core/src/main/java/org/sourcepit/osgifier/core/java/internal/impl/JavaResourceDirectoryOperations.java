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

package org.sourcepit.osgifier.core.java.internal.impl;

import org.sourcepit.common.constraints.NotNull;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.utils.collections.CollectionUtils;
import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.Resource;

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
