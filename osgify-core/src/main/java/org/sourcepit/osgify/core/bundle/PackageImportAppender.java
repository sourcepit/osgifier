/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableList;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT;
import static org.sourcepit.common.manifest.osgi.ParameterType.DIRECTIVE;
import static org.sourcepit.osgify.core.bundle.BundleUtils.isInternalPackage;
import static org.sourcepit.osgify.core.bundle.BundleUtils.trimQualifiers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.PackageImport;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.manifest.osgi.ParameterType;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;

import com.google.common.collect.Multimap;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class PackageImportAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(PackageImportAppender.class);

   private BundleRequirementsService packagesService;

   private ExecutionEnvironmentService environmentService;

   @Inject
   public PackageImportAppender(ExecutionEnvironmentService environmentService,
      BundleRequirementsService packagesService)
   {
      this.environmentService = environmentService;
      this.packagesService = packagesService;
   }

   public void append(@NotNull BundleCandidate bundle)
   {
      final List<PackageRequirement> packageRequirements = determinePackageRequirements(bundle);
      for (PackageRequirement requirement : packageRequirements)
      {
         final List<PackageReference> references = packagesService.getPossiblePackageReferences(bundle,
            requirement.getRequiredPackage());

         final PackageImport packageImport = determinePackageImport(requirement, references);
         if (packageImport != null)
         {
            final BundleManifest manifest = bundle.getManifest();
            manifest.getImportPackage(true).add(packageImport);
         }
      }
   }

   private List<PackageRequirement> determinePackageRequirements(BundleCandidate bundle)
   {
      final Map<String, PackageRequirementBuilder> nameToRequirementMap = new LinkedHashMap<String, PackageRequirementBuilder>();
      addRequiredPackages(nameToRequirementMap, bundle);
      addOwnPackages(nameToRequirementMap, bundle);
      filterExecutionEnvironmentPackages(nameToRequirementMap, bundle);

      List<PackageRequirement> packageRequirements = new ArrayList<PackageRequirement>(nameToRequirementMap.size());
      for (String packageName : nameToRequirementMap.keySet())
      {
         packageRequirements.add(nameToRequirementMap.get(packageName).toPackageRequirement());
      }
      sort(packageRequirements);
      packageRequirements = unmodifiableList(packageRequirements);
      return packageRequirements;
   }

   private PackageImport determinePackageImport(final PackageRequirement requirement,
      final List<PackageReference> possibleReferences)
   {
      final List<PackageImport> packageImports = new ArrayList<PackageImport>()
      {
         private static final long serialVersionUID = 1L;

         public boolean add(PackageImport e)
         {
            if (contains(e))
            {
               return false;
            }
            return super.add(e);
         };

         public boolean contains(Object o)
         {
            for (PackageImport packageImport : this)
            {
               if (EcoreUtil.equals((EObject) o, packageImport))
               {
                  return true;
               }
            }
            return false;
         }
      };

      for (PackageReference reference : possibleReferences)
      {
         if (reference.isExecutionEnvironmentReference() && reference.getAccessRule() == AccessRule.DISCOURAGED)
         {
            packageImports.add(null);
         }
         else
         {
            final PackageImport packageImport = createPackageImport(requirement, reference);
            packageImports.add(packageImport);
         }
      }

      if (packageImports.isEmpty())
      {
         LOGGER.warn("Unresolveable referende to package " + requirement.getRequiredPackage());
         return null;
      }
      else
      {
         if (packageImports.size() > 1)
         {
            final StringBuilder msg = new StringBuilder();
            msg.append("Unambiguous referende to package ");
            msg.append(requirement.getRequiredPackage());
            msg.append(". Exporters: ");
            for (PackageReference exportDescription : possibleReferences)
            {
               appendExporter(msg, exportDescription);
               msg.append(", ");
            }
            msg.deleteCharAt(msg.length() - 2);
            LOGGER.warn(msg.toString());
         }
         return packageImports.get(0);
      }
   }

   private void appendExporter(final StringBuilder msg, PackageReference exportDescription)
   {
      if (exportDescription.isExecutionEnvironmentReference())
      {
         msg.append("Execution Environment or Vendor (");
         msg.append(exportDescription.getImportingBundle().getManifest()
            .getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT));
         msg.append(")");
      }
      else
      {
         final BundleCandidate exportingBundle = exportDescription.getExportingBundle();
         msg.append(exportingBundle.getSymbolicName());
         msg.append('_');
         msg.append(exportingBundle.getVersion().toMinimalString());
         if (exportDescription.isOptional())
         {
            msg.append(" (optional)");
         }
      }
   }

   private PackageImport createPackageImport(PackageRequirement packageReference, PackageReference exportDescription)
   {
      final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
      packageImport.getPackageNames().add(exportDescription.getPackageName());
      packageImport.setVersion(determineVersionRange(exportDescription, packageReference));
      final boolean optional = exportDescription.isOptional();
      if (optional)
      {
         Parameter parameter = BundleManifestFactory.eINSTANCE.createParameter();
         parameter.setName("resolution");
         parameter.setType(ParameterType.DIRECTIVE);
         parameter.setValue("optional");
         packageImport.getParameters().add(parameter);
      }
      return packageImport;
   }

   private VersionRange determineVersionRange(PackageReference exportDescription, PackageRequirement packageReference)
   {
      if (exportDescription != null)
      {
         return determineVersionRange(exportDescription, packageReference.isDemandedByPublicPackages(),
            packageReference.isDemandedByInternalPackages());
      }
      return null;
   }

   public enum DemanderRole
   {
      PROVIDER, FRIEND, CONSUMER;
   }

   private static VersionRange determineVersionRange(final PackageReference exportDescription,
      boolean demandedByPublicPackages, boolean demandedByInternalPackages)
   {
      final PackageExport packageExport = exportDescription.getPackageExport();

      if (exportDescription.isExecutionEnvironmentReference())
      {
         return null;
      }
      else
      {
         final boolean isSelfReference = exportDescription.isSelfReference();

         final DemanderRole role = determineRoleOfDemandingBundle(isSelfReference, demandedByPublicPackages,
            demandedByInternalPackages, exportDescription.getPackageName(),
            isInternalPackage(exportDescription.getPackageExport()));

         if (DemanderRole.FRIEND == role)
         {
            addFriendDirective(packageExport, exportDescription.getImportingBundle().getSymbolicName());
         }

         final Version packageVersion = packageExport.getVersion();
         if (packageVersion == null || Version.EMPTY_VERSION.equals(packageVersion))
         {
            return null;
         }

         final VersionRange referenceRange = isSelfReference ? null : exportDescription.getReferenceToExporter()
            .getVersionRange();

         return determineImportVersionRange(referenceRange, packageVersion, role);
      }
   }


   public static VersionRange determineImportVersionRange(VersionRange referenceRange, Version packageVersion,
      DemanderRole role)
   {
      Version l, h;
      if (referenceRange == null)
      {
         l = packageVersion;
         h = packageVersion;
      }
      else
      {
         if (!referenceRange.includes(packageVersion))
         {
            Version rl = referenceRange.getLowVersion();
            rl = new Version(rl.getMajor(), rl.getMinor(), -1);
            if (!new VersionRange(rl, referenceRange.isLowInclusive(), referenceRange.getHighVersion(),
               referenceRange.isHighInclusive()).includes(packageVersion))
            {
               referenceRange = new VersionRange(packageVersion, true, packageVersion, true);
            }
         }
         else if (referenceRange.getHighVersion() != null)
         {
            return referenceRange;
         }

         Version rl = referenceRange.getLowVersion();
         Version rh = referenceRange.getHighVersion();
         if (rh == null)
         {
            rh = rl;
         }

         l = min(rl, packageVersion);
         h = max(rh, packageVersion);
      }

      final VersionRange roleRange;
      switch (role)
      {
         case CONSUMER :
            roleRange = toConsumerRange(l, h);
            break;
         case FRIEND :
            roleRange = toFriendRange(l, h);
            break;
         case PROVIDER :
            roleRange = toProviderRange(l, h);
            break;
         default :
            throw new IllegalStateException();
      }

      return trimQualifiers(roleRange);
   }

   private static VersionRange toProviderRange(Version lv, Version hv)
   {
      return new VersionRange(lv, true, new Version(hv.getMajor(), hv.getMinor() + 1, -1), false);
   }

   private static VersionRange toFriendRange(Version lv, Version hv)
   {
      return new VersionRange(lv, true, new Version(hv.getMajor(), hv.getMinor() + 1, -1), false);
   }

   private static VersionRange toConsumerRange(Version lv, Version hv)
   {
      return new VersionRange(lv, true, new Version(hv.getMajor() + 1, -1, -1), false);
   }

   private static Version max(Version v1, Version v2)
   {
      return v1.compareTo(v2) > 0 ? v1 : v2;
   }

   private static Version min(Version v1, Version v2)
   {
      return v1.compareTo(v2) < 0 ? v1 : v2;
   }

   private static void addFriendDirective(PackageExport packageExport, String symbolicName)
   {
      Parameter xInternal = packageExport.getParameter("x-internal");
      if (xInternal != null)
      {
         packageExport.getParameters().remove(xInternal);
      }

      Parameter xFriends = packageExport.getParameter("x-friends");
      if (xFriends == null)
      {
         xFriends = BundleManifestFactory.eINSTANCE.createParameter();
         xFriends.setName("x-friends");
         xFriends.setType(DIRECTIVE);
         xFriends.setQuoted(true);
         packageExport.getParameters().add(xFriends);
      }

      String value = xFriends.getValue();
      if (value == null)
      {
         value = symbolicName;
      }
      else
      {
         value = ", " + symbolicName;
      }
      xFriends.setValue(value);
   }

   private static DemanderRole determineRoleOfDemandingBundle(boolean isSelfReference,
      boolean demandedByPublicPackages, boolean demandedByInternalPackages, String packageName, boolean internal)
   {
      if (internal)
      {
         return isSelfReference ? DemanderRole.PROVIDER : DemanderRole.FRIEND;
      }
      else if (isSelfReference && demandedByInternalPackages)
      {
         return DemanderRole.PROVIDER;
      }
      else
      {
         return DemanderRole.CONSUMER;
      }
   }

   private void addOwnPackages(final Map<String, PackageRequirementBuilder> refs, BundleCandidate bundle)
   {
      final EList<PackageExport> exportPackage = bundle.getManifest().getExportPackage();
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            final boolean internalPackage = isInternalPackage(packageExport);
            for (String packageName : packageExport.getPackageNames())
            {
               PackageRequirementBuilder ref = refs.get(packageName);
               if (ref == null)
               {
                  ref = new PackageRequirementBuilder();
                  ref.setDemandingBundle(bundle);
                  ref.setRequiredPackage(packageName);
                  refs.put(packageName, ref);
               }

               if (internalPackage)
               {
                  ref.setDemandedByInternalPackages(true);
               }
               else
               {
                  ref.setDemandedByPublicPackages(true);
               }
            }
         }
      }
   }

   private void addRequiredPackages(final Map<String, PackageRequirementBuilder> refs, BundleCandidate bundle)
   {
      final Multimap<String, String> requiredToDemandingPackages = packagesService
         .getRequiredToDemandingPackages(bundle);

      final Set<String> internalPackages = new HashSet<String>();
      final Set<String> publicPackages = new HashSet<String>();
      collectExportedPackages(bundle.getManifest(), internalPackages, publicPackages);

      for (Entry<String, Collection<String>> entry : requiredToDemandingPackages.asMap().entrySet())
      {
         final String requiredPackage = entry.getKey();
         final Collection<String> demandingPackages = entry.getValue();

         final PackageRequirementBuilder ref = new PackageRequirementBuilder();
         ref.setDemandingBundle(bundle);
         ref.setRequiredPackage(requiredPackage);
         for (String demandingPackage : demandingPackages)
         {
            if (publicPackages.contains(demandingPackage))
            {
               ref.setDemandedByPublicPackages(true);
            }
            else if (internalPackages.contains(demandingPackage))
            {
               ref.setDemandedByInternalPackages(true);
            }
         }
         refs.put(requiredPackage, ref);
      }
   }

   private static void collectExportedPackages(BundleManifest manifest, Set<String> internalPackages,
      Set<String> publicPackages)
   {
      EList<PackageExport> exportPackage = manifest.getExportPackage();
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            if (isInternalPackage(packageExport))
            {
               internalPackages.addAll(packageExport.getPackageNames());
            }
            else
            {
               publicPackages.addAll(packageExport.getPackageNames());
            }
         }
      }
   }

   private void filterExecutionEnvironmentPackages(Map<String, PackageRequirementBuilder> refs, BundleCandidate bundle)
   {
      final EList<String> executionEnvironments = bundle.getManifest().getBundleRequiredExecutionEnvironment();
      if (executionEnvironments != null)
      {
         for (String execEnvId : executionEnvironments)
         {
            final ExecutionEnvironment executionEnvironment = environmentService.getExecutionEnvironment(execEnvId);
            if (executionEnvironment == null)
            {
               LOGGER.warn("Unknow execution environment {}", execEnvId);
            }
            else
            {
               for (String packageName : executionEnvironment.getPackages())
               {
                  refs.remove(packageName);
               }
            }
         }
      }
   }
}
