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
public class BundlePackagesService {
   private final WeakHashMap<BundleCandidate, PackagesInfo> cache = new WeakHashMap<BundleCandidate, PackagesInfo>();

   public PackagesInfo getPackagesInfo(BundleCandidate bundle) {
      PackagesInfo result = cache.get(bundle);
      if (result == null) {
         result = new PackagesInfoCollector().collect(bundle.getContent(), getClassPath(bundle));
         cache.put(bundle, result);
      }
      return result;
   }

   private static List<JavaResourceBundle> getClassPath(BundleCandidate bundle) {
      final List<BundleReference> dependencies = bundle.getDependencies();
      final List<JavaResourceBundle> classPath = new ArrayList<JavaResourceBundle>(dependencies.size() + 1);
      classPath.add(bundle.getContent());
      for (BundleReference bundleReference : dependencies) {
         final JavaResourceBundle jBundle = bundleReference.getTarget().getContent();
         if (jBundle != null) {
            classPath.add(jBundle);
         }
      }
      return classPath;
   }
}
