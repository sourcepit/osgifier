/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.inspect;

import static org.sourcepit.modularizor.core.inspect.JavaResourceType.FILE_OUTSIDE_PACKAGE;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;

import org.eclipse.emf.ecore.resource.Resource;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.modularizor.core.model.java.File;
import org.sourcepit.modularizor.core.model.java.JavaResourcesRoot;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ManifestHandler extends AbstractJavaResourceHandler
{
   private static final Path PATH_MANIFEST = new Path("META-INF/MANIFEST.MF");

   public boolean handle(JavaResourcesRoot jResources, JavaResourceType type, ReadWriteLock modelLock, Path path,
      InputStream content)
   {
      if (FILE_OUTSIDE_PACKAGE == type && PATH_MANIFEST.equals(path))
      {
         final Resource resource = new GenericManifestResourceImpl();
         try
         {
            resource.load(content, null);
            final Manifest manifest = (Manifest) resource.getContents().get(0);
            final File mfFile = getFile(jResources, modelLock, path);
            mfFile.addExtension(manifest);
         }
         catch (IOException e)
         { // TODO log warning
         }
         return true;
      }
      return false;
   }
}
