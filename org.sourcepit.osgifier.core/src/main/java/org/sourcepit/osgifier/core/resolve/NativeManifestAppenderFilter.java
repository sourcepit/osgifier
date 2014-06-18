/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.resolve;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

public interface NativeManifestAppenderFilter
{
   static NativeManifestAppenderFilter APPEND_ALL = new NativeManifestAppenderFilter()
   {
      @Override
      public boolean isAppendNativeManifest(BundleCandidate bundle, BundleManifest manifest, PropertiesSource options)
      {
         return true;
      }
   };

   boolean isAppendNativeManifest(BundleCandidate bundle, BundleManifest manifest, PropertiesSource options);
}
