/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.inspect;

import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;

import org.sourcepit.common.utils.path.Path;
import org.sourcepit.modularizor.java.JavaResourcesRoot;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class DefaultFileHandler extends AbstractJavaResourceHandler
{
   private boolean createEmptyDirectories = true;

   private boolean createEmptyFiles = true;

   public boolean isCreateEmptyDirectories()
   {
      return createEmptyDirectories;
   }

   public void setCreateEmptyDirectories(boolean createEmptyDirectories)
   {
      this.createEmptyDirectories = createEmptyDirectories;
   }

   public boolean isCreateEmptyFiles()
   {
      return createEmptyFiles;
   }

   public void setCreateEmptyFiles(boolean createEmptyFiles)
   {
      this.createEmptyFiles = createEmptyFiles;
   }

   public boolean handle(JavaResourcesRoot jResources, JavaResourceType type, ReadWriteLock modelLock, Path path,
      InputStream content)
   {
      switch (type)
      {
         case DIRECTORY_IN_PACKAGE :
            if (isCreateEmptyDirectories())
            {
               getJavaPackage(jResources, modelLock, path);
            }
            return true;
         case DIRECTORY_OUTSIDE_PACKAGE :
            if (isCreateEmptyDirectories())
            {
               getDirectory(jResources, modelLock, path);
            }
            return true;
         case FILE_IN_PACKAGE :
            if (isCreateEmptyFiles())
            {
               getFileInJavaPackage(jResources, modelLock, path);
            }
            return true;
         case FILE_OUTSIDE_PACKAGE :
            if (isCreateEmptyFiles())
            {
               getFile(jResources, modelLock, path);
            }
            return true;
         default :
            return false;
      }
   }
}
