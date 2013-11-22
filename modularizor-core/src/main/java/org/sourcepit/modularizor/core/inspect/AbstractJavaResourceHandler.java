/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.inspect;

import java.util.concurrent.locks.ReadWriteLock;

import org.sourcepit.common.utils.path.Path;
import org.sourcepit.modularizor.core.java.util.JavaLangUtils;
import org.sourcepit.modularizor.java.Directory;
import org.sourcepit.modularizor.java.File;
import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResourceDirectory;
import org.sourcepit.modularizor.java.JavaResourcesRoot;
import org.sourcepit.modularizor.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class AbstractJavaResourceHandler implements JavaResourceHandler
{
   protected JavaPackage getJavaPackage(JavaResourcesRoot jResources, ReadWriteLock modelLock, Path path)
   {
      final String packageName = getJPackageName(path);
      JavaPackage jPackage = findJavaPackage(jResources, modelLock, packageName);
      if (jPackage == null)
      {
         modelLock.writeLock().lock();
         try
         {
            jPackage = jResources.getPackage(packageName, true);
         }
         finally
         {
            modelLock.writeLock().unlock();
         }
      }
      return jPackage;
   }

   protected JavaPackage findJavaPackage(JavaResourcesRoot jResources, ReadWriteLock modelLock, String packageName)
   {
      modelLock.readLock().lock();
      try
      {
         return jResources.getPackage(packageName);
      }
      finally
      {
         modelLock.readLock().unlock();
      }
   }

   protected JavaType getJavaType(JavaResourcesRoot jResources, ReadWriteLock modelLock, Path path)
   {
      final String packageName = getJPackageName(path.getParent());

      JavaResourceDirectory jDir;
      if (packageName == null)
      {
         jDir = jResources;
      }
      else
      {
         jDir = findJavaPackage(jResources, modelLock, packageName);
      }

      modelLock.writeLock().lock();
      try
      {
         if (jDir == null)
         {
            jDir = jResources.getPackage(packageName, true);
         }
         final String typeName = path.getFileName();
         return jDir.getType(typeName, true);
      }
      finally
      {
         modelLock.writeLock().unlock();
      }
   }

   protected Directory getDirectory(JavaResourcesRoot jResources, ReadWriteLock modelLock, Path path)
   {
      Directory dir = findDirectory(jResources, modelLock, path);
      if (dir == null)
      {
         modelLock.writeLock().lock();
         try
         {
            dir = jResources;
            for (String segment : path.getSegments())
            {
               dir = dir.getDirectory(segment, true);
            }
            return dir;
         }
         finally
         {
            modelLock.writeLock().unlock();
         }
      }
      return dir;
   }

   protected Directory findDirectory(JavaResourcesRoot jResources, ReadWriteLock modelLock, Path path)
   {
      modelLock.readLock().lock();
      try
      {
         Directory dir = jResources;
         for (String segment : path.getSegments())
         {
            dir = dir.getDirectory(segment);
            if (dir == null)
            {
               break;
            }
         }
         return dir;
      }
      finally
      {
         modelLock.readLock().unlock();
      }
   }

   protected File getFileInJavaPackage(JavaResourcesRoot jResources, ReadWriteLock modelLock, Path path)
   {
      final Path parentPath = path.getParent();

      Directory dir;
      if (parentPath == null)
      {
         dir = jResources;
      }
      else
      {
         dir = findDirectory(jResources, modelLock, parentPath);
      }

      modelLock.writeLock().lock();
      try
      {
         if (dir == null)
         {
            dir = getJavaPackage(jResources, modelLock, parentPath);
         }
         return dir.getFile(path.getLastSegment(), true);
      }
      finally
      {
         modelLock.writeLock().unlock();
      }
   }

   protected File getFile(JavaResourcesRoot jResources, ReadWriteLock modelLock, Path path)
   {
      final Path parentPath = path.getParent();

      Directory dir;
      if (parentPath == null)
      {
         dir = jResources;
      }
      else
      {
         dir = findDirectory(jResources, modelLock, parentPath);
      }

      modelLock.writeLock().lock();
      try
      {
         if (dir == null)
         {
            dir = getDirectory(jResources, modelLock, parentPath);
         }
         return dir.getFile(path.getLastSegment(), true);
      }
      finally
      {
         modelLock.writeLock().unlock();
      }
   }

   private String getJPackageName(Path path)
   {
      return path == null ? null : JavaLangUtils.toPackageName(path);
   }
}
