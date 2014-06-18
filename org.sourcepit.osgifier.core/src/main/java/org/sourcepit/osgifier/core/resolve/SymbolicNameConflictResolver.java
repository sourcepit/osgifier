/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.resolve;

import java.util.List;

import javax.inject.Named;

import org.sourcepit.osgifier.core.model.context.BundleCandidate;

@Named
public class SymbolicNameConflictResolver
{
   public boolean resolveNameConflict(BundleCandidate bundle1, final List<String> names1, BundleCandidate bundle2,
      final List<String> names2)
   {
      if (resolveParallel(bundle1, names1, bundle2, names2))
      {
         return true;
      }

      if (resolveLeftDominant(bundle1, names1, bundle2, names2))
      {
         return true;
      }

      return resolveLeftDominant(bundle2, names2, bundle1, names1);
   }

   private boolean resolveParallel(BundleCandidate conflictBundle, final List<String> conflictNames,
      BundleCandidate bundle, final List<String> names)
   {
      for (int i = 0; i < conflictNames.size(); i++)
      {
         if (names.size() > i)
         {
            final String conflictName = conflictNames.get(i);
            final String name = names.get(i);
            if (!conflictName.equals(name))
            {
               applySymbolicName(conflictBundle, conflictName);
               applySymbolicName(bundle, name);
               return true;
            }
         }
      }
      return false;
   }

   private boolean resolveLeftDominant(BundleCandidate dominantBundle, List<String> names1, BundleCandidate bundle,
      final List<String> names)
   {
      for (String conflictName : names1)
      {
         for (String name : names)
         {
            if (!conflictName.equals(name))
            {
               applySymbolicName(dominantBundle, conflictName);
               applySymbolicName(bundle, name);
               return true;
            }
         }
      }
      return false;
   }

   private static void applySymbolicName(BundleCandidate conflictBundle, String conflictName)
   {
      conflictBundle.getManifest().setBundleSymbolicName(conflictName);
      conflictBundle.setSymbolicName(conflictName);
   }
}
