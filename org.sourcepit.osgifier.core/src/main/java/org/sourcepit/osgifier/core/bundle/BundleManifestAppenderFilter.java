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

package org.sourcepit.osgifier.core.bundle;

import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

public interface BundleManifestAppenderFilter
{
   static BundleManifestAppenderFilter APPEND_ALL = new BundleManifestAppenderFilter()
   {

      @Override
      public boolean isAppendExecutionEnvironment(BundleCandidate bundle, PropertiesSource options)
      {
         return true;
      }

      @Override
      public boolean isAppendPackageExports(BundleCandidate bundle, PropertiesSource options)
      {
         return true;
      }

      @Override
      public boolean isAppendPackageImports(BundleCandidate bundle, PropertiesSource options)
      {
         return true;
      }

      @Override
      public boolean isAppendDynamicImports(BundleCandidate bundle, PropertiesSource options)
      {
         return true;
      }

      @Override
      public boolean isAppendRecommendedImportPolicy(BundleCandidate bundle, PropertiesSource options)
      {
         return true;
      }

   };

   boolean isAppendExecutionEnvironment(BundleCandidate bundle, PropertiesSource options);

   boolean isAppendPackageExports(BundleCandidate bundle, PropertiesSource options);

   boolean isAppendPackageImports(BundleCandidate bundle, PropertiesSource options);

   boolean isAppendDynamicImports(BundleCandidate bundle, PropertiesSource options);

   boolean isAppendRecommendedImportPolicy(BundleCandidate bundle, PropertiesSource options);
}
