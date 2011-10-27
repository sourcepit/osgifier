/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.sourcepit.tools.osgifyme.core.internal.utils.UnclosableInputStreamDelegate;

public class ZipTraverser implements IResourceTraverser
{
   private final File zipFile;

   private final InputStream zipStream;

   public ZipTraverser(File zipFile)
   {
      this.zipFile = zipFile;
      this.zipStream = null;
   }

   public ZipTraverser(InputStream zipStream)
   {
      this.zipStream = zipStream;
      this.zipFile = null;
   }

   public void travers(IResourceVisitor visitor)
   {
      if (zipFile != null)
      {
         travers(zipFile, visitor);
      }
      else
      {
         travers(zipStream, visitor);
      }
   }

   private void travers(File zipFile, IResourceVisitor visitor)
   {
      final FileInputStream fileInputStream;
      try
      {
         fileInputStream = new FileInputStream(zipFile);
      }
      catch (FileNotFoundException e)
      {
         throw new IllegalArgumentException(e);
      }

      try
      {
         travers(new InputStream()
         {
            @Override
            public int read() throws IOException
            {
               return fileInputStream.read();
            }
         }, visitor);
      }
      finally
      {
         IOUtils.closeQuietly(fileInputStream);
      }
   }

   private void travers(InputStream zipStream, IResourceVisitor visitor)
   {
      final ZipInputStream zipIn = new ZipInputStream(zipStream);
      try
      {
         travers(zipIn, visitor);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      finally
      {
         IOUtils.closeQuietly(zipIn);
      }
   }

   private void travers(ZipInputStream zipIn, IResourceVisitor visitor) throws IOException
   {
      final Set<String> skipped = new HashSet<String>();
      ZipEntry entry = zipIn.getNextEntry();
      while (entry != null)
      {
         final String path = entry.getName();
         if (!isSkipped(skipped, path))
         {
            final boolean isDirectory = entry.isDirectory();
            final boolean visitChildren = travers(zipIn, path, isDirectory, visitor);
            if (!visitChildren && isDirectory)
            {
               skipped.add(path);
            }
         }
         zipIn.closeEntry();
         entry = zipIn.getNextEntry();
      }
   }

   private boolean isSkipped(Set<String> skipped, String path)
   {
      for (String skippedPath : skipped)
      {
         if (path.startsWith(skippedPath))
         {
            return true;
         }
      }
      return false;
   }

   private boolean travers(final ZipInputStream zipIn, String path, boolean isDirectory, IResourceVisitor visitor)
   {
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
               return zipIn;
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
         // zip input stream will closed by caller
         // if (streamDelegate != null)
         // {
         // streamDelegate.closeDelegate();
         // }
      }

      return visitChildren;
   }
}
