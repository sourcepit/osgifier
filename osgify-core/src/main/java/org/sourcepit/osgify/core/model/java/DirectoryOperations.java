/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.utils.collections.CollectionUtils;


/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class DirectoryOperations
{
   private DirectoryOperations()
   {
      super();
   }

   public static EList<Directory> getDirectories(Directory dir)
   {
      final EList<Directory> result = new BasicEList<Directory>();
      CollectionUtils.addAll(result, dir.getResources(), Directory.class);
      return ECollections.unmodifiableEList(result);
   }

   public static Directory getDirectory(Directory dir, String name, boolean createOnDemand)
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

   public static File getFile(Directory dir, String name, boolean createOnDemand)
   {
      for (Resource resource : dir.getResources())
      {
         if (resource instanceof File && name.equals(resource.getName()))
         {
            return (File) resource;
         }
      }
      if (createOnDemand)
      {
         final File file = JavaModelFactory.eINSTANCE.createFile();
         file.setName(name);
         dir.getResources().add(file);
         return file;
      }
      return null;
   }
}
