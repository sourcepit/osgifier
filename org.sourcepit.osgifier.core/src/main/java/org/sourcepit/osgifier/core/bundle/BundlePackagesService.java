/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sourcepit.osgifier.core.java.PackagesInfo;
import org.sourcepit.osgifier.core.java.PackagesInfoCollector;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;

@Named
@Singleton
public class BundlePackagesService
{
   private final WeakHashMap<BundleCandidate, PackagesInfo> cache = new WeakHashMap<BundleCandidate, PackagesInfo>();

   public PackagesInfo getPackagesInfo(BundleCandidate bundle)
   {
      PackagesInfo result = cache.get(bundle);
      if (result == null)
      {
         result = new PackagesInfoCollector().collect(bundle.getContent(), getClassPath(bundle));
         cache.put(bundle, result);
      }
      return result;
   }

   private static List<JavaResourceBundle> getClassPath(BundleCandidate bundle)
   {
      final List<BundleReference> dependencies = bundle.getDependencies();
      final List<JavaResourceBundle> classPath = new ArrayList<JavaResourceBundle>(dependencies.size() + 1);
      classPath.add(bundle.getContent());
      for (BundleReference bundleReference : dependencies)
      {
         final JavaResourceBundle jBundle = bundleReference.getTarget().getContent();
         if (jBundle != null)
         {
            classPath.add(jBundle);
         }
      }
      return classPath;
   }
}
