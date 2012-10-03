/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.osgify.core.model.context.BundleCandidate;

/**
 * @author Bernd
 */
@Named
public class SymbolicNameResolver
{
   // existing manifest
   // is set
   // artifactId
   // project folder name
   // derived from packages

   @Inject
   private Map<String, AbstractSymbolicNameResolutionStrategy> strategiesMap;

   private List<AbstractSymbolicNameResolutionStrategy> unambiguousStartegies;

   private List<AbstractSymbolicNameResolutionStrategy> ambiguousStartegies;

   private synchronized List<AbstractSymbolicNameResolutionStrategy> getUnambiguousStartegies()
   {
      if (unambiguousStartegies == null)
      {
         unambiguousStartegies = new ArrayList<AbstractSymbolicNameResolutionStrategy>();
         for (AbstractSymbolicNameResolutionStrategy strategy : strategiesMap.values())
         {
            if (strategy.isUnambiguous())
            {
               getUnambiguousStartegies().add(strategy);
            }
         }
         Collections.sort(unambiguousStartegies);
      }
      return unambiguousStartegies;
   }

   private synchronized List<AbstractSymbolicNameResolutionStrategy> getAmbiguousStartegies()
   {
      if (ambiguousStartegies == null)
      {
         ambiguousStartegies = new ArrayList<AbstractSymbolicNameResolutionStrategy>();
         for (AbstractSymbolicNameResolutionStrategy strategy : strategiesMap.values())
         {
            if (!strategy.isUnambiguous())
            {
               getAmbiguousStartegies().add(strategy);
            }
         }
         Collections.sort(ambiguousStartegies);
      }
      return ambiguousStartegies;
   }

   public String resolveSymbolicName(BundleCandidate bundleCandidate)
   {
      final List<String> symbolicNames = resolveSymbolicNames(bundleCandidate, true);
      return symbolicNames.isEmpty() ? null : symbolicNames.get(0);
   }

   public List<String> resolveSymbolicNames(BundleCandidate bundleCandidate)
   {
      return resolveSymbolicNames(bundleCandidate, false);
   }

   private List<String> resolveSymbolicNames(BundleCandidate bundleCandidate, final boolean firstHit)
   {
      final List<String> symbolicNames = new ArrayList<String>();
      addResolvedNames(getUnambiguousStartegies(), firstHit, bundleCandidate, symbolicNames);
      if (symbolicNames.isEmpty() || !firstHit)
      {
         addResolvedNames(getAmbiguousStartegies(), firstHit, bundleCandidate, symbolicNames);
      }
      return symbolicNames;
   }

   private void addResolvedNames(List<AbstractSymbolicNameResolutionStrategy> strategies, boolean firstHit,
      BundleCandidate bundleCandidate, List<String> symbolicNames)
   {
      for (AbstractSymbolicNameResolutionStrategy strategy : strategies)
      {
         final String symbolicName = strategy.resolveSymbolicName(bundleCandidate);
         if (symbolicName != null && !symbolicNames.contains(symbolicName))
         {
            symbolicNames.add(symbolicName);
            if (firstHit)
            {
               break;
            }
         }
      }
   }
}
