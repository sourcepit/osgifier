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

package org.sourcepit.osgifier.core.resolve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

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

   public String resolveSymbolicName(BundleCandidate bundleCandidate, PropertiesSource options)
   {
      final List<String> symbolicNames = resolveSymbolicNames(bundleCandidate, true, options);
      return symbolicNames.isEmpty() ? null : symbolicNames.get(0);
   }

   public List<String> resolveSymbolicNames(BundleCandidate bundleCandidate, PropertiesSource options)
   {
      return resolveSymbolicNames(bundleCandidate, false, options);
   }

   private List<String> resolveSymbolicNames(BundleCandidate bundleCandidate, final boolean firstHit,
      PropertiesSource options)
   {
      final List<String> symbolicNames = new ArrayList<String>();
      addResolvedNames(getUnambiguousStartegies(), firstHit, bundleCandidate, symbolicNames, options);
      if (symbolicNames.isEmpty() || !firstHit)
      {
         addResolvedNames(getAmbiguousStartegies(), firstHit, bundleCandidate, symbolicNames, options);
      }
      return symbolicNames;
   }

   private void addResolvedNames(List<AbstractSymbolicNameResolutionStrategy> strategies, boolean firstHit,
      BundleCandidate bundleCandidate, List<String> symbolicNames, PropertiesSource options)
   {
      for (AbstractSymbolicNameResolutionStrategy strategy : strategies)
      {
         final String symbolicName = strategy.resolveSymbolicName(bundleCandidate, options);
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
