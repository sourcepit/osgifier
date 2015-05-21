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

package org.sourcepit.osgifier.core.headermod;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.inject.Named;

import org.eclipse.emf.common.util.EMap;
import org.sourcepit.common.manifest.Manifest;

@Named
public class HeaderModifier {
   public void applyModifications(Manifest manifest, HeaderModifications headerMods) {
      for (SetHeaderModification set : headerMods.getHeaders()) {
         applySetHeaderModification(manifest, set);
      }

      for (String removal : headerMods.getRemovals()) {
         applyRemoval(manifest, Pattern.compile(removal));
      }
   }

   private void applyRemoval(Manifest manifest, Pattern removal) {
      final EMap<String, String> headers = manifest.getHeaders();

      final Iterator<Entry<String, String>> it = headers.iterator();
      while (it.hasNext()) {
         Entry<java.lang.String, java.lang.String> header = (Entry<java.lang.String, java.lang.String>) it.next();
         if (removal.matcher(header.getKey()).matches()) {
            it.remove();
         }
      }
   }

   private void applySetHeaderModification(Manifest manifest, SetHeaderModification set) {
      final boolean after = !isNullOrEmpty(set.getAfter());
      final boolean before = !isNullOrEmpty(set.getBefore());

      manifest.setHeader(set.getName(), set.getValue());

      if (after || before) {
         final EMap<String, String> headers = manifest.getHeaders();
         final int currentIdx = headers.indexOfKey(set.getName());
         int newIdx = headers.indexOfKey(after ? set.getAfter() : set.getBefore());
         if (newIdx > -1) {
            if (before) {
               headers.move(newIdx, currentIdx);
            }
            else if (newIdx == headers.size() - 1) {
               headers.add(headers.remove(currentIdx));
            }
            else {
               headers.move(newIdx + 1, currentIdx);
            }
         }
      }
   }
}
