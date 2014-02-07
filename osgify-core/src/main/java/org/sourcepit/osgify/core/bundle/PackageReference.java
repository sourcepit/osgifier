/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import javax.validation.constraints.NotNull;

import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;

public final class PackageReference
{
   private final BundleCandidate importingBundle;
   private final String packageName;
   private final BundleReference referenceToExporter;
   private final BundleCandidate exportingBundle;
   private final PackageExport packageExport;
   private final AccessRule accessRule;

   public PackageReference(@NotNull BundleCandidate importingBundle, @NotNull String packageName,
      BundleReference referenceToExporter, BundleCandidate exportingBundle, PackageExport packageExport,
      @NotNull AccessRule accessRule)
   {
      this.importingBundle = importingBundle;
      this.packageName = packageName;
      this.referenceToExporter = referenceToExporter;
      this.exportingBundle = exportingBundle;
      this.packageExport = packageExport;
      this.accessRule = accessRule;
   }

   public BundleCandidate getImportingBundle()
   {
      return importingBundle;
   }

   public String getPackageName()
   {
      return packageName;
   }

   public BundleReference getReferenceToExporter()
   {
      return referenceToExporter;
   }

   public BundleCandidate getExportingBundle()
   {
      return exportingBundle;
   }

   public PackageExport getPackageExport()
   {
      return packageExport;
   }

   public boolean isExecutionEnvironmentReference()
   {
      return exportingBundle == null;
   }

   public boolean isSelfReference()
   {
      return importingBundle == exportingBundle;
   }

   public AccessRule getAccessRule()
   {
      return accessRule;
   }

   public boolean isOptional()
   {
      if (isSelfReference())
      {
         return false;
      }
      else if (isExecutionEnvironmentReference())
      {
         return false;
      }
      else
      {
         final BundleReference referenceToExporter = getReferenceToExporter();
         return referenceToExporter.isOptional();
      }
   }

   @Override
   public String toString()
   {
      final StringBuilder builder = new StringBuilder();
      builder.append("PackageReference [importingBundle=");
      builder.append(importingBundle == null ? null : importingBundle.getSymbolicName());
      builder.append(", packageName=");
      builder.append(packageName);
      builder.append(", exportingBundle=");
      builder.append(exportingBundle == null ? null : exportingBundle.getSymbolicName());
      builder.append(", accessRule=");
      builder.append(accessRule);
      builder.append("]");
      return builder.toString();
   }


}