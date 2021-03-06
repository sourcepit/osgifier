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

import static org.sourcepit.common.manifest.osgi.ParameterType.DIRECTIVE;
import static org.sourcepit.common.manifest.osgi.Version.EMPTY_VERSION;
import static org.sourcepit.common.manifest.osgi.VersionRange.INFINITE_RANGE;
import static org.sourcepit.osgifier.core.bundle.PackageImportType.INTERNAL_IMPORT;
import static org.sourcepit.osgifier.core.bundle.PackageImportType.PUBLIC_IMPORT;
import static org.sourcepit.osgifier.core.bundle.PackageImportType.SELF_IMPORT;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.COMPATIBLE;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.EQUIVALENT;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.BundleRequirement;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.PackageImport;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;

@Named
public class PackageImportFactory {

   public PackageImport newPackageImport(BundleCandidate bundle, PackageResolutionResult result,
      PropertiesSource options) {
      final String requiredPackage = result.getRequiredPackage();

      final PackageExportDescription exporter = result.getSelectedExporter();
      if (exporter == null) {
         return newUnresolvableImport(bundle, requiredPackage, options);
      }

      switch (exporter.getExporterType()) {
         case OWN_BUNDLE :
            return newSelfImport(bundle, requiredPackage, exporter.getPackageExport(), options);
         case EXECUTION_ENVIRONMENT :
            return newExecutionEnvironmentImport(bundle, requiredPackage, options);
         case VENDOR :
            return newVendorImport(bundle, requiredPackage, options);
         case REQUIRED_BUNDLE :
            return newPackageImport(bundle, requiredPackage, exporter.getBundleReference(), exporter.getBundle(),
               exporter.getPackageExport(), result.getAccessRestriction(), options);
         default :
            throw new IllegalStateException();
      }
   }

   private PackageImport newSelfImport(BundleCandidate bundle, String requiredPackage, PackageExport packageExport,
      PropertiesSource options) {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);

      final Version version = packageExport.getVersion();
      if (version != null && !version.equals(EMPTY_VERSION)) {
         final VersionRangePolicy policy;

         final VersionRangePolicy[] policies = getRecommendedImportPolicies(bundle, options);
         if (policies == null) {
            policy = getVersionRangePolicy(options, SELF_IMPORT, EQUIVALENT);
         }
         else {
            policy = policies[1];
         }

         final VersionRange versionRange = policy.toVersionRange(version,
            options.getBoolean("osgifier.eraseMicro", true));
         if (!VersionRange.INFINITE_RANGE.equals(versionRange)) {
            packageImport.setVersion(versionRange);
         }
      }
      return packageImport;
   }

   private PackageImport newPackageImport(BundleCandidate importingBundle, String requiredPackage,
      BundleReference bundleReference, BundleCandidate exportingBundle, PackageExport packageExport,
      AccessRestriction accessRestriction, PropertiesSource options) {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);

      final Version version = packageExport.getVersion();
      if (version != null && !version.equals(EMPTY_VERSION)) {
         VersionRange versionRange = bundleReference.getVersionRange();
         if (versionRange == null || versionRange.getHighVersion() == null || !versionRange.includes(version)) {
            final VersionRangePolicy policy = getVersionRangePolicy(exportingBundle, accessRestriction, options);
            versionRange = policy.toVersionRange(version, options.getBoolean("osgifier.eraseMicro", true));
         }

         if (!INFINITE_RANGE.equals(versionRange)) {
            packageImport.setVersion(versionRange);
         }
      }

      final boolean optional = bundleReference.isOptional();
      if (optional) {
         packageImport.getParameters().add(newOptional());
      }

      return packageImport;
   }

   private static Parameter newOptional() {
      final Parameter parameter = BundleManifestFactory.eINSTANCE.createParameter();
      parameter.setName("resolution");
      parameter.setType(DIRECTIVE);
      parameter.setValue("optional");
      return parameter;
   }

   private PackageImport newExecutionEnvironmentImport(BundleCandidate bundle, String requiredPackage,
      PropertiesSource options) {
      // see http://wiki.osgi.org/wiki/Why_does_Eclipse_find_javax.swing_but_not_Felix%3F
      if (requiredPackage.startsWith("java.")) {
         return null;
      }

      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(requiredPackage);
      return packageImport;
   }

   private PackageImport newVendorImport(BundleCandidate bundle, String requiredPackage, PropertiesSource options) {
      // final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      // packageImport.getPackageNames().add(requiredPackage);
      // return packageImport;
      return null;
   }

   private PackageImport newUnresolvableImport(BundleCandidate bundle, String requiredPackage, PropertiesSource options) {
      // TODO currently unresolvable packages are ignored.. ok?
      // final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      // packageImport.getPackageNames().add(requiredPackage);
      // setOptional(packageImport);
      // return packageImport;
      return null;
   }

   private static VersionRangePolicy getVersionRangePolicy(PropertiesSource options,
      final PackageImportType importType, final VersionRangePolicy defaultPolicy) {
      final String option = "osgifier." + importType.literal() + "Policy";
      return VersionRangePolicy.parse(options.get(option, defaultPolicy.literal()));
   }

   private static VersionRangePolicy getVersionRangePolicy(BundleCandidate exportingBundle,
      AccessRestriction accessRestriction, PropertiesSource options) {
      final VersionRangePolicy[] policies = getRecommendedImportPolicies(exportingBundle, options);
      switch (accessRestriction) {
         case NONE :
            if (policies == null) {
               return getVersionRangePolicy(options, PUBLIC_IMPORT, COMPATIBLE);
            }
            return policies[0];
         case DISCOURAGED :
            if (policies == null) {
               return getVersionRangePolicy(options, INTERNAL_IMPORT, EQUIVALENT);
            }
            return policies[1];
         default :
            throw new IllegalStateException();
      }
   }

   private static VersionRangePolicy[] getRecommendedImportPolicies(BundleCandidate exportingBundle,
      PropertiesSource options) {
      VersionRangePolicy[] policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromOptions(
         exportingBundle.getManifest(), options);

      if (policies == null) {
         policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromHeader(exportingBundle.getManifest());
      }

      return policies;
   }

   public BundleRequirement newRequireBundle(BundleCandidate bundle, BundleCandidate requiredBundle, boolean optional,
      AccessRestriction accessRestriction, PropertiesSource options) {
      final VersionRangePolicy policy = getVersionRangePolicy(requiredBundle, accessRestriction, options);
      final Version version = requiredBundle.getVersion();
      final VersionRange versionRange = policy.toVersionRange(version, options.getBoolean("osgifier.eraseMicro", true));
      final BundleRequirement requirement = BundleManifestFactory.eINSTANCE.createBundleRequirement();
      requirement.getSymbolicNames().add(requiredBundle.getSymbolicName());
      if (!INFINITE_RANGE.equals(versionRange)) {
         requirement.setBundleVersion(versionRange);
      }
      if (optional) {
         requirement.getParameters().add(newOptional());
      }
      return requirement;
   }
}
