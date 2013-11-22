/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.utils.collections.CollectionUtils;
import org.sourcepit.common.utils.path.Path;


/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class DirectoryOperations
{
   private DirectoryOperations()
   {
      super();
   }

   public static Resource getResource(Directory dir, @NotNull String name)
   {
      final Path path = new Path(name);
      final Path parentPath = path.getParent();
      final Directory parentDir = parentPath == null ? dir : getDirectory(dir, parentPath, false);
      if (parentDir != null)
      {
         final String fileName = path.getLastSegment();
         return findResource(parentDir, fileName);
      }
      return null;
   }

   public static EList<Directory> getDirectories(Directory dir)
   {
      final EList<Directory> result = new BasicEList<Directory>();
      CollectionUtils.addAll(result, dir.getResources(), Directory.class);
      return ECollections.unmodifiableEList(result);
   }

   public static Directory getDirectory(Directory dir, @NotNull String name, boolean createOnDemand)
   {
      return getDirectory(dir, new Path(name), createOnDemand);
   }

   private static Directory getDirectory(Directory dir, Path path, boolean createOnDemand)
   {
      Directory currentDir = dir;
      for (String segment : path.getSegments())
      {
         currentDir = getChildDirectory(currentDir, segment, createOnDemand);
         if (currentDir == null)
         {
            break;
         }
      }
      return currentDir;
   }


   public static Directory getChildDirectory(Directory dir, String name, boolean createOnDemand)
   {
      for (Resource resource : dir.getResources())
      {
         if (resource instanceof Directory && name.equals(resource.getName()))
         {
            return (Directory) resource;
         }
      }
      if (createOnDemand)
      {
         final Directory file = JavaModelFactory.eINSTANCE.createDirectory();
         file.setName(name);
         dir.getResources().add(file);
         return file;
      }
      return null;
   }


   public static EList<File> getFiles(Directory dir)
   {
      final EList<File> result = new BasicEList<File>();
      CollectionUtils.addAll(result, dir.getResources(), File.class);
      return ECollections.unmodifiableEList(result);
   }

   public static File getFile(@NotNull Directory dir, @NotNull String name, boolean createOnDemand)
   {
      final Path path = new Path(name);
      final Path parentPath = path.getParent();
      final Directory parentDir = parentPath == null ? dir : getDirectory(dir, parentPath, createOnDemand);
      if (parentDir != null)
      {
         final String fileName = path.getLastSegment();
         File file = (File) findResource(parentDir, fileName);
         if (file == null && createOnDemand)
         {
            file = JavaModelFactory.eINSTANCE.createFile();
            file.setName(fileName);
            parentDir.getResources().add(file);
         }
         return file;
      }
      return null;
   }

   private static Resource findResource(Directory dir, String name)
   {
      for (Resource resource : dir.getResources())
      {
         if (resource instanceof File && name.equals(resource.getName()))
         {
            return resource;
         }
      }
      return null;
   }
}
