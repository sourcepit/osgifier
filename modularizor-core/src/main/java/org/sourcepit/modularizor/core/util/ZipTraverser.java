/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.util;

import java.io.File;
import java.io.IOException;

import org.sourcepit.common.utils.zip.FileZipInputStreamFactory;
import org.sourcepit.common.utils.zip.ZipProcessingRequest;
import org.sourcepit.common.utils.zip.ZipProcessor;
import org.sourcepit.modularizor.core.inspect.ResourceVisitor;
import org.sourcepit.modularizor.core.inspect.ZipEntryHandlerAdapter;

public class ZipTraverser implements IResourceTraverser
{
   private File zipFile;

   public ZipTraverser(File zipFile)
   {
      this.zipFile = zipFile;
   }

   public void travers(ResourceVisitor visitor)
   {
      final ZipProcessingRequest request = new ZipProcessingRequest();
      request.setStreamFactory(new FileZipInputStreamFactory(zipFile));
      request.setEntryHandler(new ZipEntryHandlerAdapter(visitor));
      try
      {
         new ZipProcessor().process(request);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
   }
}
