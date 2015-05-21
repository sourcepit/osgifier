/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.bundle;

import static org.sourcepit.osgifier.core.bundle.PackageExporterType.EXECUTION_ENVIRONMENT;
import static org.sourcepit.osgifier.core.bundle.PackageExporterType.OWN_BUNDLE;
import static org.sourcepit.osgifier.core.bundle.PackageExporterType.REQUIRED_BUNDLE;
import static org.sourcepit.osgifier.core.bundle.PackageExporterType.VENDOR;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;

public final class PackageExportDescription {
   private final PackageExporterType exporterType;

   private final BundleReference bundleReference;

   private final BundleCandidate bundle;

   private final PackageExport packageExport;

   public static PackageExportDescription exportedByOwnBundle(BundleCandidate bundle, PackageExport packageExport) {
      return new PackageExportDescription(OWN_BUNDLE, null, bundle, packageExport);
   }

   public static PackageExportDescription exportedByExecutionEnvironment() {
      return new PackageExportDescription(EXECUTION_ENVIRONMENT, null, null, null);
   }

   public static PackageExportDescription exportedByVendor() {
      return new PackageExportDescription(VENDOR, null, null, null);
   }

   public static PackageExportDescription exportedByRequiredBundle(BundleReference bundleReference,
      PackageExport packageExport) {
      return new PackageExportDescription(REQUIRED_BUNDLE, bundleReference, bundleReference.getTarget(), packageExport);
   }

   private PackageExportDescription(@NotNull PackageExporterType exporterType, BundleReference bundleReference,
      BundleCandidate bundle, PackageExport packageExport) {
      this.exporterType = exporterType;
      this.bundle = bundle;
      this.bundleReference = bundleReference;
      this.packageExport = packageExport;
   }

   public PackageExporterType getExporterType() {
      return exporterType;
   }

   public BundleReference getBundleReference() {
      return bundleReference;
   }

   public BundleCandidate getBundle() {
      return bundle;
   }

   public PackageExport getPackageExport() {
      return packageExport;
   }
}