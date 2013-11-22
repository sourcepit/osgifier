/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import javax.validation.constraints.NotNull;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JavaResourceBundleOperations
{
   private JavaResourceBundleOperations()
   {
      super();
   }

   public static File getFile(@NotNull JavaResourceBundle bundle, @NotNull String rootName, @NotNull String name,
      boolean createOnDemand)
   {
      final JavaResourcesRoot resourcesRoot = getResourcesRoot(bundle, rootName, createOnDemand);
      if (resourcesRoot == null)
      {
         return null;
      }
      return resourcesRoot.getFile(name, createOnDemand);
   }

   public static JavaType getType(@NotNull JavaResourceBundle bundle, @NotNull String rootName, String packageName,
      @NotNull String typeName, boolean createOnDemand)
   {
      final JavaResourcesRoot resourcesRoot = getResourcesRoot(bundle, rootName, createOnDemand);
      if (resourcesRoot == null)
      {
         return null;
      }
      return resourcesRoot.getType(packageName, typeName, createOnDemand);
   }

   public static JavaResourcesRoot getResourcesRoot(@NotNull JavaResourceBundle bundle, @NotNull String rootName,
      boolean createOnDemand)
   {
      JavaResourcesRoot packageRoot = getResourcesRoot(bundle, rootName);
      if (packageRoot == null && createOnDemand)
      {
         packageRoot = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
         packageRoot.setName(rootName);
         bundle.getResourcesRoots().add(packageRoot);
      }
      return packageRoot;
   }

   private static JavaResourcesRoot getResourcesRoot(JavaResourceBundle bundle, String path)
   {
      for (JavaResourcesRoot packageRoot : bundle.getResourcesRoots())
      {
         if (path.equals(packageRoot.getName()))
         {
            return packageRoot;
         }
      }
      return null;
   }

   public static JavaPackage getPackage(@NotNull JavaResourceBundle bundle, @NotNull String rootName,
      @NotNull String qualifiedName, boolean createOnDemand)
   {
      final JavaResourceDirectory resourcesRoot = getResourcesRoot(bundle, rootName, createOnDemand);
      if (resourcesRoot == null)
      {
         return null;
      }
      return resourcesRoot.getPackage(qualifiedName, createOnDemand);
   }

   public static void accept(@NotNull JavaResourceBundle bundle, @NotNull ResourceVisitor visitor)
   {
      for (JavaResourcesRoot resourcesRoot : bundle.getResourcesRoots())
      {
         resourcesRoot.accept(visitor);
      }
   }
}
