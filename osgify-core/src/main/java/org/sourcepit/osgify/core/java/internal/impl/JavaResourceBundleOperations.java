/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.internal.impl;

import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.Resource;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JavaResourceBundleOperations
{
   private JavaResourceBundleOperations()
   {
      super();
   }

   public static JavaType getType(@NotNull JavaResourceBundle jBundle, @NotNull String jRootName, String packageName,
      @NotNull String typeName, boolean createOnDemand)
   {
      final JavaResourceDirectory jResourcesRoot;
      if (packageName == null)
      {
         jResourcesRoot = jBundle.getResourcesRoot(jRootName, createOnDemand);
      }
      else
      {
         jResourcesRoot = getPackage(jBundle, jRootName, packageName, createOnDemand);
      }

      if (jResourcesRoot == null)
      {
         return null;
      }
      return jResourcesRoot.getType(typeName, createOnDemand);
   }

   public static JavaResourcesRoot getPackageRoot(@NotNull JavaResourceBundle bundle, @NotNull String path)
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

   public static EList<JavaPackage> getRootPackages(JavaResourceBundle bundle, String path)
   {
      final JavaResourcesRoot packageRoot = bundle.getResourcesRoot(path);
      if (packageRoot != null)
      {
         return new BasicEList<JavaPackage>(packageRoot.getPackages());
      }
      return new BasicEList<JavaPackage>();
   }

   public static JavaResourcesRoot getPackageRoot(JavaResourceBundle bundle, String path, boolean createOnDeamnd)
   {
      JavaResourcesRoot packageRoot = getPackageRoot(bundle, path);
      if (packageRoot == null && createOnDeamnd)
      {
         packageRoot = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
         packageRoot.setName(path);
         bundle.getResourcesRoots().add(packageRoot);
      }
      return packageRoot;
   }

   public static JavaPackage getPackage(@NotNull JavaResourceBundle bundle, @NotNull String path,
      @NotNull String qualifiedName, boolean createOnDemand)
   {
      final JavaResourceDirectory packageRoot = getPackageRoot(bundle, path, createOnDemand);
      if (packageRoot == null)
      {
         return null;
      }
      return packageRoot.getPackage(qualifiedName, createOnDemand);
   }

   public static EList<Resource> getResources(@NotNull JavaResourceBundle bundle, @NotNull String rootName)
   {
      final JavaResourcesRoot jResources = bundle.getResourcesRoot(rootName);
      if (jResources != null)
      {
         return jResources.getResources();
      }
      return null;
   }

}
