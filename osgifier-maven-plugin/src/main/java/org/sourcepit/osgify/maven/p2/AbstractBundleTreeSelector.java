/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.p2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.OsgifyContext;

public abstract class AbstractBundleTreeSelector implements BundleSelector
{
   @Override
   public Collection<BundleCandidate> selectRootBundles(OsgifyContext bundleContext)
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
