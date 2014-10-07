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
import java.io.IOException;

import org.sourcepit.common.utils.zip.FileZipInputStreamFactory;
import org.sourcepit.common.utils.zip.ZipProcessingRequest;
import org.sourcepit.common.utils.zip.ZipProcessor;
import org.sourcepit.osgifier.core.inspect.ResourceVisitor;
import org.sourcepit.osgifier.core.inspect.ZipEntryHandlerAdapter;

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
