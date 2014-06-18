/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.java.internal.impl;

import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.Resource;

public final class JavaArchiveOperations
{
   private JavaArchiveOperations()
   {
      super();
   }

   public static JavaType getType(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand)
   {
      return JavaResourceBundleOperations.getType(bundle, "", packageName, typeName, createOnDemand);
   }

   public static JavaPackage getPackage(JavaArchive bundle, String fullyQualified, boolean createOnDemand)
   {
      return JavaResourceBundleOperations.getPackage(bundle, "", fullyQualified, createOnDemand);
   }

   public static Resource getResource(JavaArchive bundle, String name)
   {
      for (JavaResourcesRoot jRoot : bundle.getResourcesRoots())
      {
         final Resource resource = jRoot.getResource(name);
         if (resource != null)
         {
            return resource;
         }
      }
      return null;
   }

   public static File getFile(JavaArchive bundle, String name, boolean createOnDemand)
   {
      return JavaResourceBundleOperations.getFile(bundle, "", name, createOnDemand);
   }
}
