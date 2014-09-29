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

package org.sourcepit.osgifier.core.resolve;

import static org.sourcepit.common.utils.io.IO.osgiIn;
import static org.sourcepit.common.utils.io.IO.read;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.eclipse.emf.ecore.resource.Resource;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.common.utils.io.Read.FromStream;
import org.sourcepit.common.utils.lang.PipedException;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

@Named
public class NativeManifestAppender
{
   public void appendNativeManifests(final OsgifierContext osgifierContext,
      NativeManifestAppenderFilter generatorFilter, PropertiesSource options)
   {
      final List<BundleCandidate> overriddenNativeBundles = new ArrayList<BundleCandidate>();
      for (BundleCandidate bundle : osgifierContext.getBundles())
      {
         if (bundle.getLocation() != null)
         {
            final FromStream<Manifest> fromStream = new FromStream<Manifest>()
            {
               @Override
               public Manifest read(InputStream inputStream) throws Exception
               {
                  final Resource resource = new GenericManifestResourceImpl();
                  resource.load(inputStream, null);
                  return (Manifest) resource.getContents().get(0);
               }
            };

            try
            {
               final Manifest m = read(fromStream, osgiIn(bundle.getLocation(), "META-INF/MANIFEST.MF"));
               if (m instanceof BundleManifest)
               {
                  BundleManifest manifest = (BundleManifest) m;
                  if (generatorFilter.isAppendNativeManifest(bundle, manifest, options))
                  {
                     bundle.setNativeBundle(true);
                     bundle.setManifest(manifest);

                     // TODO deprecate! replace with operation
                     bundle.setSymbolicName(manifest.getBundleSymbolicName().getSymbolicName());
                     bundle.setVersion(manifest.getBundleVersion());
                  }
                  else
                  {
                     overriddenNativeBundles.add(bundle);
                  }
               }
            }
            catch (PipedException e)
            {
               if (e.adapt(FileNotFoundException.class) == null)
               {
                  throw e;
               }
            }
         }
      }
   }
}
