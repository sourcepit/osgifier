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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

public abstract class AbstractBundleTreeSelector implements BundleSelector
{
   @Override
   public Collection<BundleCandidate> selectRootBundles(OsgifierContext bundleContext)
   {
      final List<BundleCandidate> rootBundles = new ArrayList<BundleCandidate>();
      for (BundleCandidate bundle : bundleContext.getBundles())
      {
         if (isRootBundle(bundle))
         {
            rootBundles.add(bundle);
         }
      }
      return rootBundles;
   }

   protected boolean isRootBundle(BundleCandidate bundle)
   {
      return select(bundle);
   }

   @Override
   public boolean select(Stack<BundleCandidate> path, BundleReference reference)
   {
      final BundleCandidate bundle = reference.getTarget();
      return bundle != null && select(bundle);
   }

   protected abstract boolean select(BundleCandidate bundle);

}
