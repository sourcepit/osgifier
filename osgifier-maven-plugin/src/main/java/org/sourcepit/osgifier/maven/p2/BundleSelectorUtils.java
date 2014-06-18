/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
