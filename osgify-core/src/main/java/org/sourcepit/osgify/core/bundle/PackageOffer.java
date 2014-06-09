/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;

public final class PackageOffer
{
   private final PackageOrigin type;

   private final BundleReference bundleReference;

   private final BundleCandidate bundle;

   private final PackageExport packageExport;

   public static PackageOffer exportedByOwnBundle(BundleCandidate bundle, PackageExport packageExport)
   {
      return new PackageOffer(PackageOrigin.OWN_BUNDLE, null, bundle, packageExport);
   }

   public static PackageOffer exportedByExecutionEnvironment()
   {
      return new PackageOffer(PackageOrigin.EXECUTION_ENVIRONMENT, null, null, null);
   }

   public static PackageOffer exportedByVendor()
   {
      return new PackageOffer(PackageOrigin.VENDOR, null, null, null);
   }

   public static PackageOffer exportedByRequiredBundle(BundleReference bundleReference, PackageExport packageExport)
   {
      return new PackageOffer(PackageOrigin.REQUIRED_BUNDLE, bundleReference, bundleReference.getTarget(),
         packageExport);
   }

   private PackageOffer(@NotNull PackageOrigin type, BundleReference bundleReference, BundleCandidate bundle,
      PackageExport packageExport)
   {
      this.type = type;
      this.bundle = bundle;
      this.bundleReference = bundleReference;
      this.packageExport = packageExport;
   }

   public PackageOrigin getType()
   {
      return type;
   }

   public BundleReference getBundleReference()
   {
      return bundleReference;
   }

   public BundleCandidate getBundle()
   {
      return bundle;
   }

   public PackageExport getPackageExport()
   {
      return packageExport;
   }
}