/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.OsgifyContext;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class OsgifyContextUtils
{
   private OsgifyContextUtils()
   {
      super();
   }

   public static List<BundleCandidate> computeBuildOrder(@NotNull OsgifyContext context)
   {
      final List<BundleCandidate> buildOrder = new ArrayList<BundleCandidate>();
      final EList<BundleCandidate> bundleCandidates = context.getBundles();
      for (BundleCandidate bundleCandidate : bundleCandidates)
      {
         computeBuildOrder(buildOrder, bundleCandidate, new ArrayList<BundleCandidate>());
      }
      return buildOrder;
   }

   private static void computeBuildOrder(final List<BundleCandidate> buildOrder, BundleCandidate bundleCandidate,
      List<BundleCandidate> dependencyTrail)
   {
      if (dependencyTrail.contains(bundleCandidate))
      {
         dependencyTrail.add(bundleCandidate);
         handleCycle(dependencyTrail);
      }
      else
      {
         dependencyTrail.add(bundleCandidate);
      }

      final EList<BundleReference> bundleReferences = bundleCandidate.getDependencies();
      for (BundleReference bundleReference : bundleReferences)
      {
         final BundleCandidate target = bundleReference.getTarget();
         if (target != null)
         {
            computeBuildOrder(buildOrder, target, new ArrayList<BundleCandidate>(dependencyTrail));
         }
      }

      if (!buildOrder.contains(bundleCandidate))
      {
         buildOrder.add(bundleCandidate);
      }
   }

   private static void handleCycle(List<BundleCandidate> dependencyTrail)
   {
      final StringBuilder sb = new StringBuilder();
      for (BundleCandidate bundleCandidate : dependencyTrail)
      {
         final String symbolicName = bundleCandidate.getSymbolicName();
         if (symbolicName == null)
         {
            sb.append(bundleCandidate.getLocation());
         }
         else
         {
            sb.append(symbolicName);
            sb.append(":");
            sb.append(bundleCandidate.getVersion().toMinimalString());
         }
         sb.append(" -> ");
      }
      sb.delete(sb.length() - 4, sb.length());
      throw new IllegalStateException("Cycle in dependency graph detected: " + sb.toString());
   }
}
