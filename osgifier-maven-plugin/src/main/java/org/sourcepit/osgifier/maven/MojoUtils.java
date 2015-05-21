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

package org.sourcepit.osgifier.maven;

import java.util.Map;
import java.util.Properties;

import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;

public final class MojoUtils {
   private MojoUtils() {
      super();
   }

   public static PropertiesSource getOptions(final Properties projectProperties,
      final Map<String, String> pluginProperties) {
      return new AbstractPropertiesSource() {
         @Override
         public String get(String key) {
            String value = projectProperties.getProperty(key);
            if (pluginProperties != null) {
               if (value == null) {
                  value = pluginProperties.get(key);
               }
               if (value == null && key.startsWith("osgifier.")) {
                  value = pluginProperties.get(key.substring(9));
               }
            }
            return value;
         }
      };
   }
}
