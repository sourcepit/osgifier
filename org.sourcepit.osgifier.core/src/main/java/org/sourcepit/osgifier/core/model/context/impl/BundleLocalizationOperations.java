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

package org.sourcepit.osgifier.core.model.context.impl;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.osgifier.core.model.context.BundleLocalization;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.LocalizedData;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class BundleLocalizationOperations {
   private BundleLocalizationOperations() {
      super();
   }

   public static void set(@NotNull BundleLocalization localization, @NotNull String locale, @NotNull String key,
      String value) {
      final LocalizedData localizedData = getLocalizedData(localization, locale, value != null);
      if (localizedData != null) {
         if (value == null) {
            localizedData.getData().removeKey(key);
         }
         else {
            localizedData.getData().put(key, value);
         }
      }
   }

   public static String get(BundleLocalization localization, String locale, String key) {
      final LocalizedData localizedData = getLocalizedData(localization, locale);
      return localizedData == null ? null : localizedData.getData().get(key);
   }

   private static LocalizedData getLocalizedData(BundleLocalization localization, String locale, boolean createOnDemand) {
      LocalizedData localizedData = getLocalizedData(localization, locale);
      if (localizedData == null && createOnDemand) {
         localizedData = ContextModelFactory.eINSTANCE.createLocalizedData();
         localizedData.setLocale(locale);
         localization.getData().add(localizedData);
      }
      return localizedData;
   }

   private static LocalizedData getLocalizedData(BundleLocalization localization, String locale) {
      for (LocalizedData localizedData : localization.getData()) {
         if (locale.equals(localizedData.getLocale())) {
            return localizedData;
         }
      }
      return null;
   }

}
