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
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.ResourceVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JavaResourceBundleOperations {
   private JavaResourceBundleOperations() {
      super();
   }

   public static File getFile(@NotNull JavaResourceBundle bundle, @NotNull String rootName, @NotNull String name,
      boolean createOnDemand) {
      final JavaResourcesRoot resourcesRoot = getResourcesRoot(bundle, rootName, createOnDemand);
      if (resourcesRoot == null) {
         return null;
      }
      return resourcesRoot.getFile(name, createOnDemand);
   }

   public static JavaType getType(@NotNull JavaResourceBundle bundle, @NotNull String rootName, String packageName,
      @NotNull String typeName, boolean createOnDemand) {
      final JavaResourcesRoot resourcesRoot = getResourcesRoot(bundle, rootName, createOnDemand);
      if (resourcesRoot == null) {
         return null;
      }
      return resourcesRoot.getType(packageName, typeName, createOnDemand);
   }

   public static JavaResourcesRoot getResourcesRoot(@NotNull JavaResourceBundle bundle, @NotNull String rootName,
      boolean createOnDemand) {
      JavaResourcesRoot packageRoot = getResourcesRoot(bundle, rootName);
      if (packageRoot == null && createOnDemand) {
         packageRoot = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
         packageRoot.setName(rootName);
         bundle.getResourcesRoots().add(packageRoot);
      }
      return packageRoot;
   }

   private static JavaResourcesRoot getResourcesRoot(JavaResourceBundle bundle, String path) {
      for (JavaResourcesRoot packageRoot : bundle.getResourcesRoots()) {
         if (path.equals(packageRoot.getName())) {
            return packageRoot;
         }
      }
      return null;
   }

   public static JavaPackage getPackage(@NotNull JavaResourceBundle bundle, @NotNull String rootName,
      @NotNull String qualifiedName, boolean createOnDemand) {
      final JavaResourceDirectory resourcesRoot = getResourcesRoot(bundle, rootName, createOnDemand);
      if (resourcesRoot == null) {
         return null;
      }
      return resourcesRoot.getPackage(qualifiedName, createOnDemand);
   }

   public static void accept(@NotNull JavaResourceBundle bundle, @NotNull ResourceVisitor visitor) {
      for (JavaResourcesRoot resourcesRoot : bundle.getResourcesRoots()) {
         resourcesRoot.accept(visitor);
      }
   }
}
