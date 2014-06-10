/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaClass;
import org.sourcepit.osgify.core.model.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class TestContextHelper
{
   private TestContextHelper()
   {
      super();
   }

   public static void appendTypeReference(JavaType jType, String qualifiedName)
   {
      jType.getAnnotation("referencedTypes", true).getReferences().put(qualifiedName, null);
   }

   public static BundleCandidate newBundleCandidate(JavaArchive jArchive)
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      bundle.setContent(jArchive);
      return bundle;
   }

   public static BundleCandidate newBundleCandidate(String bundleVersion, String executionEnvironment,
      JavaArchive jArchive)
   {
      final BundleCandidate bundle = newBundleCandidate(jArchive);
      bundle.getManifest().setBundleVersion(bundleVersion);
      bundle.setVersion(bundle.getManifest().getBundleVersion());
      bundle.getManifest().setBundleRequiredExecutionEnvironment(executionEnvironment);
      return bundle;
   }

   public static JavaType appendTypeWithReferences(JavaArchive jArchive, String qualifiedName, int major,
      String... typeReferences)
   {
      JavaType jType = appendType(jArchive, qualifiedName, major);
      if (typeReferences != null)
      {
         for (String typeReference : typeReferences)
         {
            appendTypeReference(jType, typeReference);
         }
      }
      return jType;
   }

   public static JavaType appendType(JavaArchive jArchive, String qualifiedName, int major)
   {
      final String packageName;
      final String typeName;
      int idx = qualifiedName.lastIndexOf('.');
      if (idx > -1)
      {
         packageName = qualifiedName.substring(0, idx);
         typeName = qualifiedName.substring(idx + 1);
      }
      else
      {
         packageName = null;
         typeName = qualifiedName;
      }

      final JavaType jType = jArchive.getType(packageName, typeName, true);
      final JavaClass file = (JavaClass) jType.getFile();
      file.setMajor(major);
      return jType;
   }

   public static PackageExport appendPackageExport(BundleManifest manifest, PackageExport packageExport)
   {
      manifest.getExportPackage(true).add(packageExport);
      return packageExport;
   }

   public static PackageExport appendPackageExport(BundleCandidate bundleCandidate, PackageExport packageExport)
   {
      BundleManifest manifest = bundleCandidate.getManifest();
      if (manifest == null)
      {
         manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
         bundleCandidate.setManifest(manifest);
      }
      return appendPackageExport(manifest, packageExport);
   }

   public static PackageExport addPackageExport(BundleCandidate requiredBundle, String packageName, String version)
   {
      return appendPackageExport(requiredBundle, newPackageExport(packageName, version));
   }

   public static PackageExport appendPackageExport(BundleReference bundleReference, PackageExport packageExport)
   {
      BundleCandidate bundleCandidate = bundleReference.getTarget();
      if (bundleCandidate == null)
      {
         Version pkgVersion = packageExport.getVersion();
         bundleCandidate = newBundleCandidate(pkgVersion == null ? "1" : pkgVersion.toString(), null, null);
         bundleReference.setTarget(bundleCandidate);
      }
      return appendPackageExport(bundleCandidate, packageExport);
   }

   public static PackageExport newPackageExport(String packageName, String version)
   {
      PackageExport packageExport = BundleManifestFactory.eINSTANCE.createPackageExport();
      packageExport.getPackageNames().add(packageName);
      if (version != null)
      {
         packageExport.setVersion(Version.parse(version));
      }
      return packageExport;
   }
}
