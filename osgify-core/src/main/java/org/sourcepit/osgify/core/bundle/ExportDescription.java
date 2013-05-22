/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import javax.validation.constraints.NotNull;

import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;

public final class ExportDescription implements Comparable<ExportDescription>
{
   private final BundleCandidate importingBundle;
   private final String packageName;
   private final BundleReference referenceToExporter;
   private final BundleCandidate exportingBundle;
   private final PackageExport packageExport;
   private final AccessRule accessRule;

   public ExportDescription(@NotNull BundleCandidate importingBundle, @NotNull String packageName,
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

   public boolean isPackageOfExecutionEnvironment()
   {
      return exportingBundle == null;
   }

   public boolean isSelfReference()
   {
      return importingBundle == exportingBundle;
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

   public AccessRule getAccessRule()
   {
      return accessRule;
   }

   @Override
   public int compareTo(ExportDescription other)
   {
      final String thisImporterName = getImporterSymbolicName(this);
      final String otherImporterName = getImporterSymbolicName(other);
      int result = thisImporterName.compareTo(otherImporterName);
      if (result != 0)
      {
         return result;
      }

      final Version thisImporterVersion = getImporterVersion(this);
      final Version otherImporterVersion = getImporterVersion(other);
      result = thisImporterVersion.compareTo(otherImporterVersion);
      if (result != 0)
      {
         return result;
      }

      final String thisPkgName = this.getPackageName();
      final String otherPkgName = other.getPackageName();
      result = thisPkgName.compareTo(otherPkgName);
      if (result != 0)
      {
         return result;
      }

      if (this.isSelfReference() && !other.isSelfReference())
      {
         return 1;
      }
      else if (!this.isSelfReference() && other.isSelfReference())
      {
         return -1;
      }

      result = getAccessRule().compareTo(other.getAccessRule());
      if (result != 0)
      {
         return result;
      }

      if (this.isPackageOfExecutionEnvironment() && !other.isPackageOfExecutionEnvironment())
      {
         return 1;
      }
      else if (!this.isPackageOfExecutionEnvironment() && other.isPackageOfExecutionEnvironment())
      {
         return -11;
      }

      final Version thisExportVersion = getExportVersion(this);
      final Version otherExportVersion = getExportVersion(other);
      result = thisExportVersion.compareTo(otherExportVersion);
      if (result != 0)
      {
         return result;
      }

      return 0;
   }

   private static String getImporterSymbolicName(ExportDescription desc)
   {
      return desc.getImportingBundle().getManifest().getBundleSymbolicName().getSymbolicName();
   }

   private static Version getImporterVersion(ExportDescription desc)
   {
      return desc.getImportingBundle().getManifest().getBundleVersion();
   }

   private static Version getExportVersion(ExportDescription desc)
   {
      PackageExport pkgExport = desc.getPackageExport();
      Version version = pkgExport.getVersion();
      if (version == null)
      {
         version = Version.EMPTY_VERSION;
      }
      return version;
   }
}