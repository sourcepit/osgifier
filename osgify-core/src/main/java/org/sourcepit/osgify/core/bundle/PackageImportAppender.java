/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.PackageImport;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.manifest.osgi.ParameterType;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.ee.AccessRule;
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

   private ExecutionEnvironmentService environmentService;

   @Inject
   public PackageImportAppender(ExecutionEnvironmentService environmentService)
   {
      this.environmentService = environmentService;
   }

   public enum AccessModifier
   {
      PUBLIC, INTERNAL
   }

   public enum ImportType
   {
      PUBLIC("public"), INTERNAL("internal"), SELF("self");

      private final String literal;

      private ImportType(String literal)
      {
         this.literal = literal;
      }

      public String getLiteral()
      {
         return literal;
      }

      public static ImportType parse(String literal)
      {
         for (ImportType type : values())
         {
            if (type.name().equalsIgnoreCase(literal))
            {
               return type;
            }
         }
         throw new IllegalArgumentException("Unknown import type: " + literal);
      }

   }

   public void append(@NotNull BundleCandidate bundle, PropertiesSource options)
   {
      final Map<String, Exporter> requiredPackgeToExporterMap = resolve(bundle);

      final boolean treatInheritedPackagesAsInternal = options.getBoolean("osgifier.treatInheritedPackagesAsInternal",
         false);

      final Map<BundleCandidate, AccessModifier> requiredBundleToImportTypeMap = determineOverallImportTypeForRequiredBundles(
         bundle, requiredPackgeToExporterMap, treatInheritedPackagesAsInternal);

      for (Entry<String, Exporter> entry : requiredPackgeToExporterMap.entrySet())
      {
         final String requiredPackage = entry.getKey();
         final Exporter exporter = entry.getValue();
         if (exporter == null)
         {
            newUnresolvableImport(bundle, requiredPackage, options);
         }
         else
         {
            switch (exporter.getType())
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
                     exporter.getPackageExport(), requiredBundleToImportTypeMap.get(exporter.getBundle()), options);
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

   private static VersionRangePolicy getVersionRangePolicy(PropertiesSource options, final ImportType importType,
      final VersionRangePolicy defaultPolicy)
   {
      final String option = "osgifier.importTypes." + importType.getLiteral() + ".policy";
      return VersionRangePolicy.parse(options.get(option, defaultPolicy.literal()));
   }


   private void newSelfImport(BundleCandidate bundle, String requiredPackage, PackageExport packageExport,
      PropertiesSource options)
   {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);

      final Version version = packageExport.getVersion();
      if (version != null && !version.equals(Version.EMPTY_VERSION))
      {
         final VersionRangePolicy policy = getVersionRangePolicy(options, ImportType.SELF,
            VersionRangePolicy.EQUIVALENT);

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
      AccessModifier accessModifier, PropertiesSource options)
   {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);

      final Version version = packageExport.getVersion();
      if (version != null && !version.equals(Version.EMPTY_VERSION))
      {
         VersionRange versionRange = bundleReference.getVersionRange();
         if (versionRange == null || versionRange.getHighVersion() == null || !versionRange.includes(version))
         {
            final VersionRangePolicy policy = getVersionRangePolicy(accessModifier, options);
            versionRange = policy.toVersionRange(version, options.getBoolean("osgifier.eraseMicro", true));
         }

         if (!VersionRange.INFINITE_RANGE.equals(versionRange))
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
      parameter.setType(ParameterType.DIRECTIVE);
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

   private VersionRangePolicy getVersionRangePolicy(AccessModifier accessModifier, PropertiesSource options)
   {
      switch (accessModifier)
      {
         case PUBLIC :
            return getVersionRangePolicy(options, ImportType.PUBLIC, VersionRangePolicy.COMPATIBLE);
         case INTERNAL :
            return getVersionRangePolicy(options, ImportType.INTERNAL, VersionRangePolicy.EQUIVALENT);
         default :
            throw new IllegalStateException();
      }
   }

   private Map<BundleCandidate, AccessModifier> determineOverallImportTypeForRequiredBundles(BundleCandidate bundle,
      final Map<String, Exporter> requiredPackgeToExporterMap, boolean treatInheritedPackagesAsInternal)
   {
      final Map<BundleCandidate, AccessModifier> requiredBundleToImportTypeMap = new HashMap<BundleCandidate, AccessModifier>();

      for (Entry<String, Exporter> entry : requiredPackgeToExporterMap.entrySet())
      {
         final String requiredPackage = entry.getKey();
         final Exporter exporter = entry.getValue();

         if (exporter != null && exporter.getType() == ExporterType.REQUIRED_BUNDLE)
         {
            final BundleCandidate requiredBundle = exporter.getBundle();

            final AccessModifier currentImportType = requiredBundleToImportTypeMap.get(requiredBundle);
            if (currentImportType == null || currentImportType == AccessModifier.PUBLIC)
            {
               final PackageExport packageExport = exporter.getPackageExport();

               AccessModifier importType = BundleUtils.isInternalPackage(packageExport)
                  ? AccessModifier.INTERNAL
                  : AccessModifier.PUBLIC;

               if (importType == AccessModifier.PUBLIC && treatInheritedPackagesAsInternal)
               {
                  // if our bundle implements classes from a public package we assume that we are providing an api
                  // implementation
                  final boolean roleProvider = getBundlePackages(bundle).getReferencedPackages().getInherited()
                     .contains(requiredPackage);
                  if (roleProvider)
                  {
                     importType = AccessModifier.INTERNAL;
                  }
               }
               requiredBundleToImportTypeMap.put(requiredBundle, importType);
            }
         }
      }
      return requiredBundleToImportTypeMap;
   }

   private Map<String, Exporter> resolve(BundleCandidate bundle)
   {
      final Map<String, Exporter> requiredPackgeToExporterMap = new LinkedHashMap<String, Exporter>();
      for (String requiredPackage : getReferencedPackages(bundle))
      {
         final List<Exporter> exportes = resolve(bundle, requiredPackage);
         if (exportes.isEmpty())
         {
            final boolean hiddenBundlePackage = getBundlePackages(bundle).getPackages().contains(requiredPackage);
            if (!hiddenBundlePackage)
            {
               requiredPackgeToExporterMap.put(requiredPackage, null); // unresolvable
            }
         }
         else
         {
            Exporter exporter = exportes.get(0);
            requiredPackgeToExporterMap.put(requiredPackage, exporter);
         }
      }
      return requiredPackgeToExporterMap;
   }

   private Collection<String> getReferencedPackages(BundleCandidate bundle)
   {
      final BundlePackages bundlePackages = getBundlePackages(bundle);

      final Collection<String> requiredPackges = new ArrayList<String>(bundlePackages.getReferencedPackages().getAll());
      removeEEPackages(bundle, requiredPackges);

      // add self imports after removing ee packages to add ee packages contributed by our bundle also
      final List<PackageExport> exportPackage = bundle.getManifest().getExportPackage();
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            requiredPackges.addAll(packageExport.getPackageNames());
         }
      }

      return requiredPackges;
   }

   private final WeakHashMap<BundleCandidate, BundlePackages> cache = new WeakHashMap<BundleCandidate, BundlePackages>();

   private BundlePackages getBundlePackages(BundleCandidate bundle)
   {
      BundlePackages result = cache.get(bundle);
      if (result == null)
      {
         result = new BundlePackagesCollector().collect(bundle);
         cache.put(bundle, result);
      }
      return result;
   }

   public enum ExporterType
   {
      OWN_BUNDLE, EXECUTION_ENVIRONMENT, VENDOR, REQUIRED_BUNDLE
   }

   private static final class Exporter
   {
      private final ExporterType type;

      private final BundleReference bundleReference;

      private final BundleCandidate bundle;

      private final PackageExport packageExport;

      public static Exporter exportedByOwnBundle(BundleCandidate bundle, PackageExport packageExport)
      {
         return new Exporter(ExporterType.OWN_BUNDLE, null, bundle, packageExport);
      }

      public static Exporter exportedByExecutionEnvironment()
      {
         return new Exporter(ExporterType.EXECUTION_ENVIRONMENT, null, null, null);
      }

      public static Exporter exportedByVendor()
      {
         return new Exporter(ExporterType.VENDOR, null, null, null);
      }

      public static Exporter exportedByRequiredBundle(BundleReference bundleReference, PackageExport packageExport)
      {
         return new Exporter(ExporterType.REQUIRED_BUNDLE, bundleReference, bundleReference.getTarget(), packageExport);
      }

      private Exporter(@NotNull ExporterType type, BundleReference bundleReference, BundleCandidate bundle,
         PackageExport packageExport)
      {
         this.type = type;
         this.bundle = bundle;
         this.bundleReference = bundleReference;
         this.packageExport = packageExport;
      }

      public ExporterType getType()
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

   private List<Exporter> resolve(BundleCandidate bundle, String requiredPackage)
   {
      final List<Exporter> exporters = new ArrayList<Exporter>();

      // self
      {
         final PackageExport packageExport = getPackageExport(bundle, requiredPackage);
         if (packageExport != null)
         {
            exporters.add(Exporter.exportedByOwnBundle(bundle, packageExport));
         }
      }

      // dependencies
      for (BundleReference bundleReference : bundle.getDependencies())
      {
         final BundleCandidate requiredBundle = bundleReference.getTarget();

         final PackageExport packageExport = getPackageExport(requiredBundle, requiredPackage);
         if (packageExport != null)
         {
            exporters.add(Exporter.exportedByRequiredBundle(bundleReference, packageExport));
         }
      }

      // ee or vendor
      switch (getAccessRule(bundle, requiredPackage))
      {
         case ACCESSIBLE : // ee
            exporters.add(Exporter.exportedByExecutionEnvironment());
            break;
         case DISCOURAGED : // vendor
            exporters.add(Exporter.exportedByVendor());
            break;
         case NON_ACCESSIBLE : // neither nor
            break;
         default :
            throw new IllegalStateException();
      }

      return exporters;
   }

   private static final PackageExport getPackageExport(BundleCandidate requiredBundle, String requiredPackage)
   {
      final List<PackageExport> exportPackage = requiredBundle.getManifest().getExportPackage();
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            if (packageExport.getPackageNames().contains(requiredPackage))
            {
               return packageExport;
            }
         }
      }
      return null;
   }

   private AccessRule getAccessRule(BundleCandidate bundle, String requiredPackage)
   {
      final List<String> requiredEEs = bundle.getManifest().getBundleRequiredExecutionEnvironment();
      if (requiredEEs != null)
      {
         return environmentService.getAccessRuleById(requiredEEs, requiredPackage);
      }
      return AccessRule.NON_ACCESSIBLE;
   }

   private void removeEEPackages(BundleCandidate bundle, final Collection<String> requiredPackges)
   {
      final List<String> requiredEEs = bundle.getManifest().getBundleRequiredExecutionEnvironment();
      if (requiredEEs != null)
      {
         for (String ee : requiredEEs)
         {
            requiredPackges.removeAll(environmentService.getExecutionEnvironment(ee).getPackages());
         }
      }
   }

}
