/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.model.context.BundleCandidate;

public interface JavaContentAppenderFilter
{
   static JavaContentAppenderFilter APPEND_ALL = new JavaContentAppenderFilter()
   {
      @Override
      public boolean isAppendContent(BundleCandidate bundle, PropertiesSource options)
      {
         return true;
      }
   };

   static JavaContentAppenderFilter SKIP_NATIVE_AND_SOURCE = new JavaContentAppenderFilter()
   {
      @Override
      public boolean isAppendContent(BundleCandidate bundle, PropertiesSource options)
      {
         return !bundle.isNativeBundle() && bundle.getTargetBundle() == null;
      }
   };

   boolean isAppendContent(BundleCandidate candidate, PropertiesSource options);
}