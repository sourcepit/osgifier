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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class OptionsUtils
{
   private OptionsUtils()
   {
      super();
   }

   public static Map<String, String> parseMapValue(String mapValue)
   {
      final Map<String, String> mappings = new LinkedHashMap<String, String>();
      for (String entry : mapValue.split(","))
      {
         final String[] keyToValue = entry.split("=");
         if (keyToValue.length == 2)
         {
            final String key = keyToValue[0].trim();
            if (!mappings.containsKey(key))
            {
               mappings.put(key, keyToValue[1].trim());
            }
         }
      }
      return mappings;
   }

   public static List<String> parseListValue(String value)
   {
      final List<String> list = new ArrayList<String>();
      for (String entry : value.split(","))
      {
         list.add(entry.trim());
      }
      return list;
   }


}
