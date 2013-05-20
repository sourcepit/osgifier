/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import org.sourcepit.osgify.core.model.context.BundleCandidate;

public class ManifestGeneratorFilter
{
   public boolean isGenerateManifest(BundleCandidate bundle)
   {
      return !bundle.isNativeBundle() || isOverrideNativeBundle(bundle);
   }

   private boolean isOverrideNativeBundle(BundleCandidate bundle)
   {
      return false;
   }

   public boolean isSourceBundle(BundleCandidate bundle)
   {
      return bundle.getTargetBundle() != null;
   }

   public boolean isScanBundle(BundleCandidate bundle)
   {
      return isGenerateManifest(bundle) && !isSourceBundle(bundle);
   }
}
