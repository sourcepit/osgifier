/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.common.manifest.osgi.ParameterType.DIRECTIVE;
import static org.sourcepit.common.manifest.osgi.Version.EMPTY_VERSION;
import static org.sourcepit.common.manifest.osgi.VersionRange.INFINITE_RANGE;
import static org.sourcepit.osgify.core.bundle.PackageImportType.INTERNAL_IMPORT;
import static org.sourcepit.osgify.core.bundle.PackageImportType.PUBLIC_IMPORT;
import static org.sourcepit.osgify.core.bundle.PackageImportType.SELF_IMPORT;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.COMPATIBLE;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.EQUIVALENT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.PackageImport;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentImplementation;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class PackageImportAppender
{
   private final PackageResolver packageResolver;

   private final ExecutionEnvironmentService environmentService;

   @Inject
   public PackageImportAppender(PackageResolver packageResolver, ExecutionEnvironmentService environmentService)
   {
      this.packageResolver = packageResolver;
      this.environmentService = environmentService;
   }

   public void append(@NotNull BundleCandidate bundle, PropertiesSource options)
   {
      final boolean treatInheritedPackagesAsInternal = options.getBoolean("osgifier.treatInheritedPackagesAsInternal",
         false);

      final List<PackageResolutionResult> results = packageResolver.resolveRequiredPackages(bundle,
         treatInheritedPackagesAsInternal);

      for (PackageResolutionResult result : results)
      {
         final String requiredPackage = result.getRequiredPackage();
         final PackageExportDescription exporter = result.getSelectedExporter();
         if (exporter == null)
         {
            newUnresolvableImport(bundle, requiredPackage, options);
         }
         else
         {
            switch (exporter.getExporterType())
            {
               case OWN_BUNDLE :
                  newSelfImport(bundle, requiredPackage, exporter.getPackageExport(), options);
                  break;
               case EXECUTION_ENVIRONMENT :
                  newExecutionEnvironmentImport(bundle, requiredPackage, options);
                  break;
               case VENDOR :
                  newVendorImport(bundle, requiredPackage, options);
                  break;
               case REQUIRED_BUNDLE :
                  newPackageImport(bundle, requiredPackage, exporter.getBundleReference(), exporter.getBundle(),
                     exporter.getPackageExport(), result.getAccessRestriction(), options);
                  break;
               default :
                  break;
            }
         }
      }

      // erase ee and vendor package versions
      final List<PackageImport> importPackage = bundle.getManifest().getImportPackage();
      if (importPackage != null)
      {
         final Set<String> eePackages = new HashSet<String>();

         for (ExecutionEnvironment executionEnvironment : environmentService.getExecutionEnvironments())
         {
            eePackages.addAll(executionEnvironment.getPackages());
         }

         for (ExecutionEnvironmentImplementation vendors : environmentService.getExecutionEnvironmentImplementations())
         {
            eePackages.addAll(vendors.getVendorPackages());
         }

         for (PackageImport packageImport : importPackage)
         {
            for (String packageName : packageImport.getPackageNames())
            {
               if (eePackages.contains(packageName))
               {
                  packageImport.setVersion(null);
                  break;
               }
            }
         }
      }
   }

   private static VersionRangePolicy getVersionRangePolicy(PropertiesSource options,
      final PackageImportType importType, final VersionRangePolicy defaultPolicy)
   {
      final String option = "osgifier." + importType.literal() + "Policy";
      return VersionRangePolicy.parse(options.get(option, defaultPolicy.literal()));
   }


   private void newSelfImport(BundleCandidate bundle, String requiredPackage, PackageExport packageExport,
      PropertiesSource options)
   {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);

      final Version version = packageExport.getVersion();
      if (version != null && !version.equals(EMPTY_VERSION))
      {
         final VersionRangePolicy policy = getVersionRangePolicy(options, SELF_IMPORT, EQUIVALENT);

         final VersionRange versionRange = policy.toVersionRange(version,
            options.getBoolean("osgifier.eraseMicro", true));
         if (!VersionRange.INFINITE_RANGE.equals(versionRange))
         {
            packageImport.setVersion(versionRange);
         }
      }

      bundle.getManifest().getImportPackage(true).add(packageImport);
   }

   private void newPackageImport(BundleCandidate importingBundle, String requiredPackage,
      BundleReference bundleReference, BundleCandidate exportingBundle, PackageExport packageExport,
      AccessRestriction accessRestriction, PropertiesSource options)
   {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);

      final Version version = packageExport.getVersion();
      if (version != null && !version.equals(EMPTY_VERSION))
      {
         VersionRange versionRange = bundleReference.getVersionRange();
         if (versionRange == null || versionRange.getHighVersion() == null || !versionRange.includes(version))
         {
            final VersionRangePolicy policy = getVersionRangePolicy(accessRestriction, options);
            versionRange = policy.toVersionRange(version, options.getBoolean("osgifier.eraseMicro", true));
         }

         if (!INFINITE_RANGE.equals(versionRange))
         {
            packageImport.setVersion(versionRange);
         }
      }

      final boolean optional = bundleReference.isOptional();
      if (optional)
      {
         setOptional(packageImport);
      }

      importingBundle.getManifest().getImportPackage(true).add(packageImport);
   }

   private void setOptional(final PackageImport packageImport)
   {
      Parameter parameter = BundleManifestFactory.eINSTANCE.createParameter();
      parameter.setName("resolution");
      parameter.setType(DIRECTIVE);
      parameter.setValue("optional");
      packageImport.getParameters().add(parameter);
   }

   private void newExecutionEnvironmentImport(BundleCandidate bundle, String requiredPackage, PropertiesSource options)
   {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);
      bundle.getManifest().getImportPackage(true).add(packageImport);
   }

   private void newVendorImport(BundleCandidate bundle, String requiredPackage, PropertiesSource options)
   {
      // TODO currently vendor packages are ignored.. ok?
      // final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      // packageImport.getPackageNames().add(requiredPackage);
      // bundle.getManifest().getImportPackage(true).add(packageImport);
   }

   private void newUnresolvableImport(BundleCandidate bundle, String requiredPackage, PropertiesSource options)
   {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);
      setOptional(packageImport);
   }

   private VersionRangePolicy getVersionRangePolicy(AccessRestriction accessRestriction, PropertiesSource options)
   {
      switch (accessRestriction)
      {
         case NONE :
            return getVersionRangePolicy(options, PUBLIC_IMPORT, COMPATIBLE);
         case DISCOURAGED :
            return getVersionRangePolicy(options, INTERNAL_IMPORT, EQUIVALENT);
         default :
            throw new IllegalStateException();
      }
   }


}
