/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.model.context.BundleCandidate;

public abstract class AbstractOsgifyContextInflatorFilter implements OsgifyContextInflatorFilter
{
   @Override
   public boolean isAppendNativeManifest(BundleCandidate bundle, BundleManifest manifest, PropertiesSource options)
   {
      return true;
   }

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
}
