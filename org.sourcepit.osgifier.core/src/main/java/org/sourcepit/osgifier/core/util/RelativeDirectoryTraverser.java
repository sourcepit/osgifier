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
