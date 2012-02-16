/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.collections.CollectionUtils;
import org.sourcepit.common.utils.collections.ValueLookup;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.File;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class PackageExportAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(PackageExportAppender.class);

   private final ExecutionEnvironmentService execEnvService;

   @Inject
   public PackageExportAppender(ExecutionEnvironmentService execEnvService)
   {
      this.execEnvService = execEnvService;
   }

   public void append(@NotNull BundleCandidate bundle)
   {
      final JavaResourceBundle jBundle = bundle.getContent();
      if (jBundle == null)
      {
         throw new IllegalArgumentException("Java content of bundle " + bundle.getSymbolicName() + " must not be null.");
      }
      final BundleManifest manifest = bundle.getManifest();
      if (manifest == null)
      {
         throw new IllegalArgumentException("Manifest of bundle " + bundle.getSymbolicName() + " must not be null.");
      }

      final Map<String, List<JavaPackage>> nameToJPackagesMap = determinePackagesToExport(jBundle,
         manifest.getBundleRequiredExecutionEnvironment());

      final List<String> packageNames = new ArrayList<String>(nameToJPackagesMap.keySet());
      Collections.sort(packageNames);
      for (String packageName : packageNames)
      {
         final Version version = determinePackageVersion(manifest, packageName, nameToJPackagesMap.get(packageName));
         appendExport(manifest, packageName, version);
      }
   }

   private void appendExport(final BundleManifest manifest, final String packageName, final Version version)
   {
      final PackageExport packageExport = BundleManifestFactory.eINSTANCE.createPackageExport();
      packageExport.getPackageNames().add(packageName);
      if (version != null)
      {
         packageExport.setVersion(version);
      }
      manifest.getExportPackage(true).add(packageExport);
   }

   private Map<String, List<JavaPackage>> determinePackagesToExport(JavaResourceBundle jBundle,
      EList<String> executionEnvironments)
   {
      final Map<String, List<JavaPackage>> nameToJPackagesMap = new HashMap<String, List<JavaPackage>>();
      collectPackagesToExport(jBundle, nameToJPackagesMap);
      filterExecutionEnvironmentPackages(nameToJPackagesMap, executionEnvironments);
      return nameToJPackagesMap;
   }

   private Version determinePackageVersion(BundleManifest manifest, String packageName, List<JavaPackage> jPackages)
   {
      Version version = determinePackageVersionFromPackageInfo(jPackages);
      if (version == null)
      {
         version = manifest.getBundleVersion();
      }

      // export packages reachable via execution platforms (and implementations) with default version
      final EList<String> executionEnvironments = manifest.getBundleRequiredExecutionEnvironment();
      if (executionEnvironments != null)
      {
         final AccessRule accessRule = execEnvService.getAccessRuleById(executionEnvironments, packageName);
         if (accessRule != AccessRule.NON_ACCESSIBLE)
         {
            version = null;
         }
      }

      return version;
   }

   private void filterExecutionEnvironmentPackages(final Map<String, List<JavaPackage>> nameToJPackagesMap,
      EList<String> executionEnvironments)
   {
      if (executionEnvironments != null)
      {
         for (String execEnvId : executionEnvironments)
         {
            ExecutionEnvironment executionEnvironment = execEnvService.getExecutionEnvironment(execEnvId);
            if (executionEnvironment == null)
            {
               LOGGER.warn("Unknow execution environment {}", execEnvId);
            }
            else
            {
               for (String packageName : executionEnvironment.getPackages())
               {
                  nameToJPackagesMap.remove(packageName);
               }
            }
         }
      }
   }

   private static Version determinePackageVersionFromPackageInfo(List<JavaPackage> jPackages)
   {
      return CollectionUtils.getValue(jPackages, new ValueLookup<JavaPackage, Version>()
      {
         public Version lookup(JavaPackage element)
         {
            final File packageinfo = element.getFile("packageinfo");
            if (packageinfo != null)
            {
               final String versionString = packageinfo.getAnnotationData("content", "version");
               if (versionString != null)
               {
                  try
                  {
                     return Version.parse(versionString);
                  }
                  catch (IllegalArgumentException e)
                  {
                     LOGGER
                        .warn(
                           "Unable to determine version from 'packageinfo' file in package {}. Format of version '{}' is invalid.",
                           element.getQualifiedName(), versionString);
                  }
               }
            }
            return null;
         }
      });
   }

   private static void collectPackagesToExport(final JavaResourceBundle jBundle,
      Map<String, List<JavaPackage>> nameToJPackagesMap)
   {
      final EList<JavaResourcesRoot> jResources = jBundle.getResourcesRoots();
      for (JavaResourcesRoot resourcesRoot : jResources)
      {
         if (!resourcesRoot.getFiles().isEmpty())
         {
            LOGGER.debug("Default package of " + jBundle.getName()
               + " contains files. These files are not accessible in OSGi.");
         }
         EList<JavaPackage> packages = resourcesRoot.getPackages();
         for (JavaPackage jPackage : packages)
         {
            collectPackagesToExport(nameToJPackagesMap, jPackage);
         }
      }
   }

   private static void collectPackagesToExport(Map<String, List<JavaPackage>> nameToJPackagesMap, JavaPackage jPackage)
   {
      if (!jPackage.getFiles().isEmpty())
      {
         final String fully = jPackage.getQualifiedName();

         List<JavaPackage> jPackages = nameToJPackagesMap.get(fully);
         if (jPackages == null)
         {
            jPackages = new ArrayList<JavaPackage>();
            nameToJPackagesMap.put(fully, jPackages);
         }
         jPackages.add(jPackage);
      }
      for (JavaPackage javaPackage : jPackage.getPackages())
      {
         collectPackagesToExport(nameToJPackagesMap, javaPackage);
      }
   }
}
