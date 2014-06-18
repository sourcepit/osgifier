/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

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
import org.sourcepit.osgify.core.model.context.OsgifyContext;

public final class ModelUtils
{
   private ModelUtils()
   {
      super();
   }

   public static void writeModel(File file, BundleManifest model)
   {
      final ToStream<EObject> toStream = new ToStream<EObject>()
      {
         @Override
         public void write(OutputStream out, EObject model) throws Exception
         {
            final BundleManifestResourceImpl resource = new BundleManifestResourceImpl();
            resource.getContents().add(model);
            resource.save(out, null);
         }
      };
      write(toStream, buffOut(fileOut(file, true)), model);
   }

   public static void writeModel(File file, OsgifyContext model)
   {
      final ToStream<EObject> toStream = new ToStream<EObject>()
      {
         @Override
         public void write(OutputStream out, EObject model) throws Exception
         {
            final XMLResourceImpl resource = new XMLResourceImpl();
            resource.getContents().add(model);
            resource.save(out, null);
         }
      };
      write(toStream, buffOut(fileOut(file, true)), model);
   }
}
