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

public interface BundleSelector
{
   BundleSelector ALL = new BundleSelector()
   {
      @Override
      public Collection<BundleCandidate> selectRootBundles(OsgifierContext bundleContext)
      {
         return bundleContext.getBundles();
      }

      @Override
      public boolean select(Stack<BundleCandidate> path, BundleReference reference)
      {
         return true;
      }
   };

   Collection<BundleCandidate> selectRootBundles(OsgifierContext bundleContext);

   boolean select(Stack<BundleCandidate> path, BundleReference reference);
}
