/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder.impl;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.osgify.maven.manifest.builder.ManifestBuilderResult;

/**
 * @author DerGilb
 *
 */
public class ManifestBuilderResultImpl implements ManifestBuilderResult
{
   private BundleManifest bundleManifest;
   private BundleManifest sourceBundleManifest;

   public ManifestBuilderResultImpl(BundleManifest bundleManifest, BundleManifest sourceBundleManifest)
   {
      super();
      this.bundleManifest = bundleManifest;
      this.sourceBundleManifest = sourceBundleManifest;
   }

   public ManifestBuilderResultImpl(BundleManifest bundleManifest)
   {
      this(bundleManifest, null);
   }

   public void setBundleManifest(BundleManifest bundleManifest)
   {
      this.bundleManifest = bundleManifest;
   }

   public void setSourceBundleManifest(BundleManifest sourceBundleManifest)
   {
      this.sourceBundleManifest = sourceBundleManifest;
   }

   @Override
   public BundleManifest getBundleManifest()
   {
      return bundleManifest;
   }

   @Override
   public BundleManifest getSourceBundleManifest()
   {
      return sourceBundleManifest;
   }

}
