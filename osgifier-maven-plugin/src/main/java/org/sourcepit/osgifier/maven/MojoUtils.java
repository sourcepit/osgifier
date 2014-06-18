/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven;

import java.util.Map;
import java.util.Properties;

import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;

public final class MojoUtils
{
   private MojoUtils()
   {
      super();
   }

   public static PropertiesSource getOptions(final Properties projectProperties,
      final Map<String, String> pluginProperties)
   {
      return new AbstractPropertiesSource()
      {
         @Override
         public String get(String key)
         {
            String value = projectProperties.getProperty(key);
            if (pluginProperties != null)
            {
               if (value == null)
               {
                  value = pluginProperties.get(key);
               }
               if (value == null && key.startsWith("osgifier."))
               {
                  value = pluginProperties.get(key.substring(9));
               }
            }
            return value;
         }
      };
   }
}
