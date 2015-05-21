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

package org.sourcepit.osgifier.core.inspect;

import static org.sourcepit.osgifier.core.inspect.JavaResourceType.FILE_OUTSIDE_PACKAGE;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;

import org.eclipse.emf.ecore.resource.Resource;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ManifestHandler extends AbstractJavaResourceHandler {
   private static final Path PATH_MANIFEST = new Path("META-INF/MANIFEST.MF");

   public boolean handle(JavaResourcesRoot jResources, JavaResourceType type, ReadWriteLock modelLock, Path path,
      InputStream content) {
      if (FILE_OUTSIDE_PACKAGE == type && PATH_MANIFEST.equals(path)) {
         final Resource resource = new GenericManifestResourceImpl();
         try {
            resource.load(content, null);
            final Manifest manifest = (Manifest) resource.getContents().get(0);
            final File mfFile = getFile(jResources, modelLock, path);
            mfFile.addExtension(manifest);
         }
         catch (IOException e) { // TODO log warning
         }
         return true;
      }
      return false;
   }
}
