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
