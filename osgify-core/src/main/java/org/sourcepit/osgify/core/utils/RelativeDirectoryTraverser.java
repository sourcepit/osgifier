/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.sourcepit.osgify.core.internal.utils.PathUtils;
import org.sourcepit.osgify.core.internal.utils.UnclosableInputStreamDelegate;

public class RelativeDirectoryTraverser implements IResourceTraverser
{
   private final File directory;

   public RelativeDirectoryTraverser(File directory)
   {
      this.directory = directory;
   }

   public void travers(IResourceVisitor visitor)
   {
      travers(directory, visitor);
   }

   private void travers(File directory, IResourceVisitor visitor)
   {
      accept(directory, directory.listFiles(), visitor);
   }

   private void accept(File rootDirectory, File[] filesToVisit, IResourceVisitor visitor)
   {
      if (filesToVisit == null)
      {
         return;
      }
      
      for (final File file : filesToVisit)
      {
         final boolean isDirectory = file.isDirectory();

         String path = PathUtils.getRelativePath(file, rootDirectory, "/");
         if (isDirectory && !path.endsWith("/"))
         {
            path = path + "/";
         }

         final UnclosableInputStreamDelegate streamDelegate;
         if (isDirectory)
         {
            streamDelegate = null;
         }
         else
         {
            streamDelegate = new UnclosableInputStreamDelegate()
            {
               @Override
               protected InputStream openInputStream() throws IOException
               {
                  return new FileInputStream(file);
               }
            };
         }

         final boolean visitChildren;
         try
         {
            visitChildren = visitor.visit(path, isDirectory, streamDelegate);
         }
         finally
         {
            if (streamDelegate != null)
            {
               streamDelegate.closeDelegate();
            }
         }

         if (visitChildren && isDirectory)
         {
            accept(rootDirectory, file.listFiles(), visitor);
         }
      }
   }
}
