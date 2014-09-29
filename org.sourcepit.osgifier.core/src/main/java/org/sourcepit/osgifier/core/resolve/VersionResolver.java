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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

/**
 * @author Bernd
 */
@Named
public class VersionResolver
{
   @Inject
   private Map<String, AbstractVersionResolutionStrategy> strategiesMap;

   private List<AbstractVersionResolutionStrategy> strategies;

   public Version resolveVersion(BundleCandidate bundleCandidate, PropertiesSource options)
   {
      for (AbstractVersionResolutionStrategy strategy : getStrategies())
      {
         final Version version = strategy.resolveVersion(bundleCandidate, options);
         if (version != null)
         {
            return version;
         }
      }
      return Version.EMPTY_VERSION;
   }

   private synchronized List<AbstractVersionResolutionStrategy> getStrategies()
   {
      if (strategies == null)
      {
         strategies = new ArrayList<AbstractVersionResolutionStrategy>(strategiesMap.values());
         Collections.sort(strategies);
      }
      return strategies;
   }
}
