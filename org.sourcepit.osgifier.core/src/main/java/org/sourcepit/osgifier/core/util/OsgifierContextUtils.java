/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.sourcepit.common.constraints.NotNull;
import org.eclipse.emf.common.util.EList;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class OsgifierContextUtils
{
   private OsgifierContextUtils()
   {
      super();
   }

   public static class BuildOrder
   {
      private final List<BundleCandidate> orderedBundles = new ArrayList<BundleCandidate>();

      private final List<List<BundleCandidate>> cycles = new ArrayList<List<BundleCandidate>>();

      public List<BundleCandidate> getOrderedBundles()
      {
         return orderedBundles;
      }

      public List<List<BundleCandidate>> getCycles()
      {
         return cycles;
      }
   }

   public static BuildOrder computeBuildOrder(@NotNull OsgifierContext context)
   {
      BuildOrder buildOrder = new BuildOrder();

      final EList<BundleCandidate> bundles = context.getBundles();
      final Stack<BundleCandidate> path = new Stack<BundleCandidate>();
      for (BundleCandidate bundle : bundles)
      {
         computeBuildOrder2(buildOrder, path, bundle);
      }
      return buildOrder;
   }

   private static void computeBuildOrder2(BuildOrder buildOrder, Stack<BundleCandidate> path, BundleCandidate bundle)
   {
      final List<BundleCandidate> orderedBundles = buildOrder.getOrderedBundles();
      if (!orderedBundles.contains(bundle))
      {
         if (path.contains(bundle)) // cycle
         {
            path.push(bundle);
            buildOrder.getCycles().add(new ArrayList<BundleCandidate>(path));
            path.pop();
            return;
         }

         path.push(bundle);
         for (BundleCandidate requiredBundle : getRequiredBundles(bundle))
         {
            computeBuildOrder2(buildOrder, path, requiredBundle);
         }
         orderedBundles.add(bundle);
         path.pop();
      }
   }

   private static List<BundleCandidate> getRequiredBundles(BundleCandidate bundle)
   {
      final EList<BundleReference> dependencies = bundle.getDependencies();
      final List<BundleCandidate> bundles = new ArrayList<BundleCandidate>(dependencies.size() + 1);
      for (BundleReference reference : dependencies)
      {
         final BundleCandidate referencedBundle = reference.getTarget();
         if (referencedBundle != null)
         {
            bundles.add(referencedBundle);
         }
      }
      final BundleCandidate sourceTarget = bundle.getTargetBundle();
      if (sourceTarget != null)
      {
         bundles.add(sourceTarget);
      }
      return bundles;
   }
}
