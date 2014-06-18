/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven;

import org.sourcepit.common.manifest.osgi.BundleManifest;

public class ArtifactManifestBuilderResult
{
   private BundleManifest bundleManifest;

   private BundleManifest sourceBundleManifest;

   public BundleManifest getBundleManifest()
   {
      return bundleManifest;
   }

   public void setBundleManifest(BundleManifest bundleManifest)
   {
      this.bundleManifest = bundleManifest;
   }

   public BundleManifest getSourceBundleManifest()
   {
      return sourceBundleManifest;
   }

   public void setSourceBundleManifest(BundleManifest sourceBundleManifest)
   {
      this.sourceBundleManifest = sourceBundleManifest;
   }
}
