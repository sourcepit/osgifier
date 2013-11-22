/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.resolve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;

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
