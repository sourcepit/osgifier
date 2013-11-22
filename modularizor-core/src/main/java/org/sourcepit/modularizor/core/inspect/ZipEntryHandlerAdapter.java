/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.inspect;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;

import org.sourcepit.common.utils.io.UnclosableInputStream;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.common.utils.zip.ZipEntryHandler;

/**
 * @author Bernd
 */
public class ZipEntryHandlerAdapter implements ZipEntryHandler
{
   private final ResourceVisitor visitor;

   public ZipEntryHandlerAdapter(ResourceVisitor visitor)
   {
      this.visitor = visitor;
   }

   public void handle(ZipEntry zipEntry, final InputStream content) throws IOException
   {
      final Path path = new Path(zipEntry.getName());
      final boolean isDirectory = zipEntry.isDirectory();
      visitor.visit(path, isDirectory, isDirectory ? null : UnclosableInputStream.wrap(content));
   }
}
