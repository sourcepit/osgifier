/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgify.context.BundleCandidate;

/**
 * @author Bernd
 */
@Component(role = VersionResolver.class)
public class VersionResolver
{
   @Requirement
   private Map<String, AbstractVersionResolutionStrategy> strategiesMap;

   private List<AbstractVersionResolutionStrategy> strategies;

   public Version resolveVersion(BundleCandidate bundleCandidate)
   {
      for (AbstractVersionResolutionStrategy strategy : getStrategies())
      {
         final Version version = strategy.resolveVersion(bundleCandidate);
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
