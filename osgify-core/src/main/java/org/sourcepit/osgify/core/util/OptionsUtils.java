/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.util;

import java.util.LinkedHashMap;
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
   
   
}
