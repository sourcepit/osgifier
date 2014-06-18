/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.osgify.core.bundle.PackageExporterType.EXECUTION_ENVIRONMENT;
import static org.sourcepit.osgify.core.bundle.PackageExporterType.OWN_BUNDLE;
import static org.sourcepit.osgify.core.bundle.PackageExporterType.REQUIRED_BUNDLE;
import static org.sourcepit.osgify.core.bundle.PackageExporterType.VENDOR;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;

public final class PackageExportDescription
{
   private final PackageExporterType exporterType;

   private final BundleReference bundleReference;

   private final BundleCandidate bundle;

   private final PackageExport packageExport;

   public static PackageExportDescription exportedByOwnBundle(BundleCandidate bundle, PackageExport packageExport)
   {
      return new PackageExportDescription(OWN_BUNDLE, null, bundle, packageExport);
   }

   public static PackageExportDescription exportedByExecutionEnvironment()
   {
      return new PackageExportDescription(EXECUTION_ENVIRONMENT, null, null, null);
   }

   public static PackageExportDescription exportedByVendor()
   {
      return new PackageExportDescription(VENDOR, null, null, null);
   }

   public static PackageExportDescription exportedByRequiredBundle(BundleReference bundleReference,
      PackageExport packageExport)
   {
      return new PackageExportDescription(REQUIRED_BUNDLE, bundleReference, bundleReference.getTarget(), packageExport);
   }

   private PackageExportDescription(@NotNull PackageExporterType exporterType, BundleReference bundleReference,
      BundleCandidate bundle, PackageExport packageExport)
   {
      this.exporterType = exporterType;
      this.bundle = bundle;
      this.bundleReference = bundleReference;
      this.packageExport = packageExport;
   }

   public PackageExporterType getExporterType()
   {
      return exporterType;
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