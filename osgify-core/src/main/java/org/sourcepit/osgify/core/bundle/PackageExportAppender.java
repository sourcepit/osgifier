/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.common.manifest.osgi.ParameterType.DIRECTIVE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.impl.BundleManifestFactoryImpl;
import org.sourcepit.common.utils.collections.CollectionUtils;
import org.sourcepit.common.utils.collections.ValueLookup;
import org.sourcepit.common.utils.path.PathMatcher;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
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

   public void append(@NotNull BundleCandidate bundle, @NotNull PropertiesSource properties)
   {
      final Map<String, PackageExport> packageToExport = new HashMap<String, PackageExport>();
      addBundlePackageExports(packageToExport, bundle, properties);
      addPackageExportsOfEmbeddedBundles(packageToExport, bundle);

      if (!packageToExport.isEmpty())
      {
         final List<String> packageNames = new ArrayList<String>(packageToExport.keySet());
         Collections.sort(packageNames);

         final List<PackageExport> exportPackage = bundle.getManifest().getExportPackage(true);
         for (String packageName : packageNames)
         {
            exportPackage.add(packageToExport.get(packageName));
         }
      }
   }

   private void addPackageExportsOfEmbeddedBundles(final Map<String, PackageExport> packageToExport,
      BundleCandidate bundle)
   {
      for (BundleCandidate embeddedBundle : determineEmbeddedBundles(bundle))
      {
         final List<PackageExport> exportPackage = embeddedBundle.getManifest().getExportPackage();
         if (exportPackage != null)
         {
            for (PackageExport packageExport : exportPackage)
            {
               for (String packageName : packageExport.getPackageNames())
               {
                  if (!packageToExport.containsKey(packageName))
                  {
                     final PackageExport copy = EcoreUtil.copy(packageExport);
                     copy.getPackageNames().clear();
                     copy.getPackageNames().add(packageName);
                     packageToExport.put(packageName, copy);
                  }
               }
            }
         }
      }
   }

   private void addBundlePackageExports(final Map<String, PackageExport> packageToExport, BundleCandidate bundle,
      PropertiesSource options)
   {
      final JavaResourceBundle jBundle = bundle.getContent();
      final BundleManifest manifest = bundle.getManifest();
      final Map<String, List<JavaPackage>> nameToJPackagesMap = determinePackagesToExport(bundle, jBundle,
         manifest.getBundleRequiredExecutionEnvironment());

      final String filter = options.get("osgifier.internalPackages");
      final PathMatcher internalPackages = filter == null ? null : PathMatcher.parsePackagePatterns(filter);

      for (Entry<String, List<JavaPackage>> entry : nameToJPackagesMap.entrySet())
      {
         final String packageName = entry.getKey();
         final Version version = determinePackageVersion(manifest, packageName, nameToJPackagesMap.get(packageName));
         packageToExport.put(packageName, newPackageExport(internalPackages, packageName, version));
      }
   }

   private static List<BundleCandidate> determineEmbeddedBundles(BundleCandidate bundle)
   {
      final List<BundleCandidate> embeddedBundles = new ArrayList<BundleCandidate>();
      for (BundleReference bundleReference : bundle.getDependencies())
      {
         switch (bundleReference.getEmbedInstruction())
         {
            case NOT :
               break;
            case UNPACKED :
            case PACKED :
               embeddedBundles.add(bundleReference.getTarget());
               break;
            default :
               throw new IllegalStateException();
         }
      }
      return embeddedBundles;
   }

   private void appendExport(PathMatcher internalPackages, final BundleManifest manifest, final String packageName,
      final Version version)
   {
      final PackageExport packageExport = newPackageExport(internalPackages, packageName, version);

      manifest.getExportPackage(true).add(packageExport);
   }

   private PackageExport newPackageExport(PathMatcher internalPackages, final String packageName, final Version version)
   {
      final PackageExport packageExport = BundleManifestFactory.eINSTANCE.createPackageExport();
      packageExport.getPackageNames().add(packageName);
      if (version != null)
      {
         packageExport.setVersion(version);
      }

      if (internalPackages != null && internalPackages.isMatch(packageName))
      {
         final Parameter parameter = BundleManifestFactoryImpl.eINSTANCE.createParameter();
         parameter.setName("x-internal");
         parameter.setValue("true");
         parameter.setType(DIRECTIVE);
         packageExport.getParameters().add(parameter);
      }
      return packageExport;
   }

   private Map<String, List<JavaPackage>> determinePackagesToExport(BundleCandidate bundle, JavaResourceBundle jBundle,
      EList<String> executionEnvironments)
   {
      final Map<String, List<JavaPackage>> nameToJPackagesMap = new HashMap<String, List<JavaPackage>>();
      collectPackagesToExport(jBundle, nameToJPackagesMap);
      filterPackagesFromReferencedBundles(nameToJPackagesMap, bundle.getDependencies());
      return nameToJPackagesMap;
   }

   private void filterPackagesFromReferencedBundles(Map<String, List<JavaPackage>> nameToJPackagesMap,
      EList<BundleReference> dependencies)
   {
      for (BundleReference bundleReference : dependencies)
      {
         final BundleCandidate referencedBundle = bundleReference.getTarget();

         final BundleManifest manifest = referencedBundle.getManifest();
         if (manifest == null) // HACK happens when we have cyclic references
         {
            continue;
         }

         final EList<PackageExport> exportPackage = manifest.getExportPackage();
         if (exportPackage != null)
         {
            for (PackageExport packageExport : exportPackage)
            {
               for (String packageName : packageExport.getPackageNames())
               {
                  nameToJPackagesMap.remove(packageName);
               }
            }
         }
      }
   }

   private Version determinePackageVersion(BundleManifest manifest, String packageName, List<JavaPackage> jPackages)
   {
      Version version = determinePackageVersionFromPackageInfo(jPackages);
      if (version == null)
      {
         version = manifest.getBundleVersion();
      }
      return version;
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
