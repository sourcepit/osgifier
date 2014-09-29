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

package org.sourcepit.osgifier.maven.p2;

import java.util.Collection;
import java.util.Stack;

import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

public final class BundleSelectorUtils
{
   private BundleSelectorUtils()
   {
      super();
   }

   public static void selectBundles(Collection<BundleCandidate> selectedBundles, final OsgifierContext bundleContext,
      BundleSelector bundleSelector)
   {
      final Stack<BundleCandidate> path = new Stack<BundleCandidate>();
      for (BundleCandidate root : bundleSelector.selectRootBundles(bundleContext))
      {
         selectBundles(selectedBundles, path, root, bundleSelector);
      }
   }

   private static void selectBundles(final Collection<BundleCandidate> selectedBundles, Stack<BundleCandidate> path,
      BundleCandidate bundle, BundleSelector bundleSelector)
   {
      if (!selectedBundles.contains(bundle) && !path.contains(bundle))
      {
         path.push(bundle);
         for (BundleReference reference : bundle.getDependencies())
         {
            if (reference.getTarget() != null && bundleSelector.select(path, reference))
            {
               selectBundles(selectedBundles, path, reference.getTarget(), bundleSelector);
            }
         }
         selectedBundles.add(bundle);
         BundleCandidate sourceBundle = bundle.getSourceBundle();
         if (sourceBundle != null)
         {
            selectedBundles.add(sourceBundle);
         }
         path.pop();
      }
   }
}
