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

package org.sourcepit.osgifier.core.model.context;

import org.sourcepit.osgifier.core.model.context.impl.BundleLocalizationOperations;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect BundleLocalizationAspects
{
   pointcut set0(BundleLocalization localization, String locale, String key, String value): target(localization) && args(locale, key, value) && execution(void set(String, String, String));

   pointcut get0(BundleLocalization localization, String locale, String key): target(localization) && args(locale, key) && execution(String get(String, String));

   void around(BundleLocalization localization, String locale, String key, String value) : set0(localization, locale, key, value){
      BundleLocalizationOperations.set(localization, locale, key, value);
   }

   String around(BundleLocalization localization, String locale, String key) : get0(localization, locale, key){
      return BundleLocalizationOperations.get(localization, locale, key);
   }
}
