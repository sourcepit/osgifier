/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.inspect;

import static org.sourcepit.modularizor.core.inspect.JavaResourceType.CLASS_FILE;
import static org.sourcepit.modularizor.core.inspect.JavaResourceType.DIRECTORY_IN_PACKAGE;
import static org.sourcepit.modularizor.core.inspect.JavaResourceType.DIRECTORY_OUTSIDE_PACKAGE;
import static org.sourcepit.modularizor.core.inspect.JavaResourceType.FILE_IN_PACKAGE;
import static org.sourcepit.modularizor.core.inspect.JavaResourceType.FILE_OUTSIDE_PACKAGE;
import static org.sourcepit.modularizor.core.inspect.JavaResourceType.PACKAGE;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

import org.sourcepit.common.utils.path.Path;
import org.sourcepit.modularizor.core.java.util.JavaLangUtils;
import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.java.JavaResourcesRoot;
import org.sourcepit.modularizor.java.JavaType;

/**
 * @author Bernd
 */
public class JavaResourceVisitor implements ResourceVisitor
{
   private final List<JavaResourceHandler> resourceHandlers = new ArrayList<JavaResourceHandler>();

   private final JavaResourceBundle jBundle;
   private final ReadWriteLock modelLock;
   private final String rootName;

   private JavaResourcesRoot jRoot;

   public JavaResourceVisitor(JavaResourceBundle jResources, String rootName, ReadWriteLock modelLock)
   {
      this.jBundle = jResources;
      this.rootName = rootName;
      this.modelLock = modelLock;
   }

   public List<JavaResourceHandler> getResourceHandlers()
   {
      return resourceHandlers;
   }

   protected JavaResourcesRoot getPackageRoot(boolean createOnDemand)
   {
      if (jRoot == null)
      {
         modelLock.writeLock().lock();
         try
         {
            jRoot = jBundle.getResourcesRoot(rootName, createOnDemand);
         }
         finally
         {
            modelLock.writeLock().unlock();
         }
      }
      return jRoot;
   }

   protected JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand)
   {
      final JavaResourcesRoot root = getPackageRoot(createOnDemand);
      return root == null ? null : root.getPackage(fullyQualifiedName, createOnDemand);
   }

   protected JavaType getType(String packageName, String typeName, boolean createOnDemand)
   {
      final JavaPackage pkg = getPackage(packageName, createOnDemand);
      return pkg == null ? null : pkg.getType(typeName, createOnDemand);
   }

   public void visit(Path path, boolean isDirectory, InputStream content)
   {
      final JavaResourceType type;
      final boolean isJPackage = isDirectory && JavaLangUtils.isFullyQuallifiedPackageName(path);
      if (isJPackage)
      {
         type = PACKAGE;
      }
      else if (isInJPackage(path))
      {
         if (!isDirectory && isJClass(path))
         {
            type = CLASS_FILE;
         }
         else
         {
            type = isDirectory ? DIRECTORY_IN_PACKAGE : FILE_IN_PACKAGE;
         }
      }
      else
      {
         type = isDirectory ? DIRECTORY_OUTSIDE_PACKAGE : FILE_OUTSIDE_PACKAGE;
      }
      handleResource(type, path, content);
   }

   private void handleResource(JavaResourceType type, Path path, InputStream content)
   {
      for (JavaResourceHandler resourceHandler : getResourceHandlers())
      {
         if (resourceHandler.handle(getPackageRoot(true), type, modelLock, path, content))
         {
            break;
         }
      }
   }

   private boolean isInJPackage(Path path)
   {
      return path.getParent() == null || JavaLangUtils.isFullyQuallifiedPackageName(path.getParent());
   }

   private boolean isJClass(Path path)
   {
      return "class".equals(path.getFileExtension());
   }
}
