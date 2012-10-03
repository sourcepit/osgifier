/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.EList;
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
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class PackageImportAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(PackageImportAppender.class);

   private ExecutionEnvironmentService execEnvService;

   private ReferencedPackagesService packagesService;

   @Inject
   public PackageImportAppender(ExecutionEnvironmentService execEnvService, ReferencedPackagesService packagesService)
   {
      this.execEnvService = execEnvService;
      this.packagesService = packagesService;
   }

   public void append(@NotNull BundleCandidate bundle)
   {
      JavaResourceBundle jBundle = bundle.getContent();
      BundleManifest manifest = bundle.getManifest();

      final List<String> packageReferences = determinePackagesToImport(jBundle, manifest);
      for (String packageName : packageReferences)
      {
         final PackageImport packageImport = BundleManifestFactory.eINSTANCE.createPackageImport();
         packageImport.getPackageNames().add(packageName);
         packageImport.setVersion(determineVersionRange(bundle, packageName));

         final boolean optional = isOptional(bundle, packageName);
         if (optional)
         {
            Parameter parameter = BundleManifestFactory.eINSTANCE.createParameter();
            parameter.setName("optional");
            parameter.setType(ParameterType.DIRECTIVE);
            parameter.setValue(String.valueOf(optional));
            packageImport.getParameters().add(parameter);
         }

         manifest.getImportPackage(true).add(packageImport);
      }
   }

   private VersionRange determineVersionRange(BundleCandidate bundle, String packageName)
   {
      final ExportDescription exportDescription = packagesService.determineExporter(bundle, packageName);
      if (exportDescription != null)
      {
         final PackageExport packageExport = exportDescription.getPackageExport();
         if (exportDescription.isSelfReference()) // self reference
         {
            return toVersionRange(packageExport.getVersion(), true, true);
         }
         else if (!exportDescription.isPackageOfExecutionEnvironment())
         {
            final BundleReference referenceToExporter = exportDescription.getReferenceToExporter();

            final Version packageVersion = packageExport.getVersion();
            if (packageVersion == null || Version.EMPTY_VERSION.equals(packageVersion))
            {
               return null;
            }

            VersionRange range = referenceToExporter.getVersionRange();
            if (range != null && range.includes(packageVersion))
            {
               return range;
            }

            return toVersionRange(packageVersion, false, false);
         }
      }
      return null;
   }

   private VersionRange toVersionRange(Version version, boolean strict, boolean eraseQualifiers)
   {
      if (version != null)
      {
         if (strict)
         {
            return new VersionRange(version, true, version, true);
         }
         else
         {
            return new VersionRange(version, true, null, false);
         }
      }
      return null;
   }

   private boolean isOptional(BundleCandidate bundle, String packageName)
   {
      final ExportDescription exportDescription = packagesService.determineExporter(bundle, packageName);
      if (exportDescription != null)
      {
         if (exportDescription.isSelfReference())
         {
            return false;
         }
         else if (exportDescription.isPackageOfExecutionEnvironment())
         {
            return false;
         }
         else
         {
            final BundleReference referenceToExporter = exportDescription.getReferenceToExporter();
            return referenceToExporter.isOptional();
         }
      }
      return true;
   }

   private List<String> determinePackagesToImport(JavaResourceBundle jBundle, BundleManifest manifest)
   {
      final Set<String> packageReferences = new LinkedHashSet<String>();
      packageReferences.addAll(packagesService.getNamesOfReferencedPackages(jBundle));

      final EList<PackageExport> exportPackage = manifest.getExportPackage();
      if (exportPackage != null)
      {
         for (PackageExport packageExport : exportPackage)
         {
            packageReferences.addAll(packageExport.getPackageNames());
         }
      }

      filterExecutionEnvironmentPackages(packageReferences, manifest.getBundleRequiredExecutionEnvironment());

      final List<String> result = new ArrayList<String>(packageReferences);
      Collections.sort(result);
      return result;
   }

   private void filterExecutionEnvironmentPackages(final Collection<String> packageNames,
      EList<String> executionEnvironments)
   {
      if (executionEnvironments != null)
      {
         for (String execEnvId : executionEnvironments)
         {
            final ExecutionEnvironment executionEnvironment = execEnvService.getExecutionEnvironment(execEnvId);
            if (executionEnvironment == null)
            {
               LOGGER.warn("Unknow execution environment {}", execEnvId);
            }
            else
            {
               packageNames.removeAll(executionEnvironment.getPackages());
            }
         }
      }
   }
}
