/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.headermod;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.inject.Named;

import org.eclipse.emf.common.util.EMap;
import org.sourcepit.common.manifest.Manifest;

@Named
public class HeaderModifier
{
   public void applyModifications(Manifest manifest, HeaderModifications headerMods)
   {
      for (SetHeaderModification set : headerMods.getHeaders())
      {
         applySetHeaderModification(manifest, set);
      }

      for (String removal : headerMods.getRemovals())
      {
         applyRemoval(manifest, Pattern.compile(removal));
      }
   }

   private void applyRemoval(Manifest manifest, Pattern removal)
   {
      final EMap<String, String> headers = manifest.getHeaders();

      final Iterator<Entry<String, String>> it = headers.iterator();
      while (it.hasNext())
      {
         Entry<java.lang.String, java.lang.String> header = (Entry<java.lang.String, java.lang.String>) it.next();
         if (removal.matcher(header.getKey()).matches())
         {
            it.remove();
         }
      }
   }

   private void applySetHeaderModification(Manifest manifest, SetHeaderModification set)
   {
      final boolean after = !isNullOrEmpty(set.getAfter());
      final boolean before = !isNullOrEmpty(set.getBefore());

      manifest.setHeader(set.getName(), set.getValue());

      if (after || before)
      {
         final EMap<String, String> headers = manifest.getHeaders();
         final int currentIdx = headers.indexOfKey(set.getName());
         int newIdx = headers.indexOfKey(after ? set.getAfter() : set.getBefore());
         if (newIdx > -1)
         {
            if (before)
            {
               headers.move(newIdx, currentIdx);
            }
            else if (newIdx == headers.size() - 1)
            {
               headers.add(headers.remove(currentIdx));
            }
            else
            {
               headers.move(newIdx + 1, currentIdx);
            }
         }
      }
   }
}
