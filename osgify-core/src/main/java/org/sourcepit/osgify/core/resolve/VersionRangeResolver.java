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
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.context.BundleReference;

/**
 * @author Bernd
 */
@Component(role = VersionRangeResolver.class)
public class VersionRangeResolver
{
   @Requirement
   private Map<String, AbstractVersionRangeResolutionStrategy> strategiesMap;

   private List<AbstractVersionRangeResolutionStrategy> strategies;

   public VersionRange resolveVersionRange(BundleReference bundleReference)
   {
      for (AbstractVersionRangeResolutionStrategy strategy : getStrategies())
      {
         final VersionRange versionRange = strategy.resolveVersionRange(bundleReference);
         if (versionRange != null)
         {
            return versionRange;
         }
      }
      return VersionRange.INFINITE_RANGE;
   }

   private synchronized List<AbstractVersionRangeResolutionStrategy> getStrategies()
   {
      if (strategies == null)
      {
         strategies = new ArrayList<AbstractVersionRangeResolutionStrategy>(strategiesMap.values());
         Collections.sort(strategies);
      }
      return strategies;
   }
}
