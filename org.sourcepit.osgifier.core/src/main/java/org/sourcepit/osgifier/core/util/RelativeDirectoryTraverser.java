/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.sourcepit.common.utils.io.UnclosableInputStream;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.common.utils.path.PathUtils;
import org.sourcepit.osgifier.core.inspect.ResourceVisitor;

public class RelativeDirectoryTraverser implements IResourceTraverser
{
   private final File directory;

   public RelativeDirectoryTraverser(File directory)
   {
      this.directory = directory;
   }

   public void travers(ResourceVisitor visitor)
   {
      travers(directory, visitor);
   }

   private void travers(File directory, ResourceVisitor visitor)
   {
      accept(directory, directory.listFiles(), visitor);
   }

   private void accept(File rootDirectory, File[] filesToVisit, ResourceVisitor visitor)
   {
      if (filesToVisit == null)
      {
         return;
      }

      for (final File file : filesToVisit)
      {
         final boolean isDirectory = file.isDirectory();

         final Path path = new Path(PathUtils.getRelativePath(file, rootDirectory, Path.SEPARATOR));

         final UnclosableInputStream streamDelegate;
         if (isDirectory)
         {
            streamDelegate = null;
         }
         else
         {
            streamDelegate = new UnclosableInputStream()
            {
               @Override
               protected InputStream openInputStream() throws IOException
               {
                  return new FileInputStream(file);
               }
            };
         }

         try
         {
            visitor.visit(path, isDirectory, streamDelegate);
         }
         finally
         {
            if (streamDelegate != null)
            {
               streamDelegate.closeDelegate();
            }
         }

         if (isDirectory)
         {
            accept(rootDirectory, file.listFiles(), visitor);
         }
      }
   }
}
