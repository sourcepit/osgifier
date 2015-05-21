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

package org.sourcepit.osgifier.maven;

import static org.sourcepit.common.utils.io.IO.buffOut;
import static org.sourcepit.common.utils.io.IO.fileOut;
import static org.sourcepit.common.utils.io.IO.write;

import java.io.File;
import java.io.OutputStream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.resource.BundleManifestResourceImpl;
import org.sourcepit.common.utils.io.Write.ToStream;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

public final class ModelUtils {
   private ModelUtils() {
      super();
   }

   public static void writeModel(File file, BundleManifest model) {
      final ToStream<EObject> toStream = new ToStream<EObject>() {
         @Override
         public void write(OutputStream out, EObject model) throws Exception {
            final BundleManifestResourceImpl resource = new BundleManifestResourceImpl();
            resource.getContents().add(model);
            resource.save(out, null);
         }
      };
      write(toStream, buffOut(fileOut(file, true)), model);
   }

   public static void writeModel(File file, OsgifierContext model) {
      final ToStream<EObject> toStream = new ToStream<EObject>() {
         @Override
         public void write(OutputStream out, EObject model) throws Exception {
            final XMLResourceImpl resource = new XMLResourceImpl();
            resource.getContents().add(model);
            resource.save(out, null);
         }
      };
      write(toStream, buffOut(fileOut(file, true)), model);
   }
}
