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
