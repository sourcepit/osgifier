/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.bundle;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.modularizor.core.ee.AccessRule;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.core.model.context.ContextModelFactory;

public class ExportDescriptionTest
{

   @Test
   public void testCompareByImporterName()
   {
      BundleCandidate bundleA = newBundle("a", "1");
      BundleCandidate bundleZ = newBundle("z", "1");

      PackageReference descA = new PackageReference(bundleA, "foo", null, null, null, null);
      PackageReference descZ = new PackageReference(bundleZ, "foo", null, null, null, null);

      List<PackageReference> descs = new ArrayList<PackageReference>();
      descs.add(descZ);
      descs.add(descA);

      Collections.sort(descs);

      assertEquals(bundleA, descs.get(0).getImportingBundle());
      assertEquals(bundleZ, descs.get(1).getImportingBundle());
   }

   @Test
   public void testCompareByImporterVersion()
   {
      BundleCandidate bundleA = newBundle("a", "1");
      BundleCandidate bundleZ = newBundle("a", "2");

      PackageReference descA = new PackageReference(bundleA, "foo", null, null, null, null);
      PackageReference descZ = new PackageReference(bundleZ, "foo", null, null, null, null);

      List<PackageReference> descs = new ArrayList<PackageReference>();
      descs.add(descZ);
      descs.add(descA);

      Collections.sort(descs);

      assertEquals(bundleA, descs.get(0).getImportingBundle());
      assertEquals(bundleZ, descs.get(1).getImportingBundle());
   }

   @Test
   public void testCompareByPackageName()
   {
      BundleCandidate bundleA = newBundle("a", "1");
      BundleCandidate bundleZ = newBundle("a", "1");

      PackageReference descA = new PackageReference(bundleA, "packageA", null, null, null, null);
      PackageReference descZ = new PackageReference(bundleZ, "packageB", null, null, null, null);

      List<PackageReference> descs = new ArrayList<PackageReference>();
      descs.add(descZ);
      descs.add(descA);

      Collections.sort(descs);

      assertEquals(bundleA, descs.get(0).getImportingBundle());
      assertEquals(bundleZ, descs.get(1).getImportingBundle());
   }

   @Test
   public void testCompareBySelfReference()
   {
      BundleCandidate bundleA = newBundle("a", "1");
      BundleCandidate bundleZ = newBundle("a", "1");

      PackageReference descA = new PackageReference(bundleZ, "foo", null, bundleA, null, null);
      PackageReference descZ = new PackageReference(bundleZ, "foo", null, bundleZ, null, null);

      List<PackageReference> descs = new ArrayList<PackageReference>();
      descs.add(descZ);
      descs.add(descA);

      Collections.sort(descs);

      assertEquals(bundleA, descs.get(0).getExportingBundle());
      assertEquals(bundleZ, descs.get(1).getExportingBundle());
   }

   @Test
   public void testCompareByAccessRule()
   {
      BundleCandidate importer = newBundle("importer", "1");

      BundleCandidate exporter = newBundle("exporter", "1");

      PackageReference descA = new PackageReference(importer, "foo", null, exporter, null, AccessRule.NON_ACCESSIBLE);
      PackageReference descB = new PackageReference(importer, "foo", null, exporter, null, AccessRule.DISCOURAGED);
      PackageReference descC = new PackageReference(importer, "foo", null, exporter, null, AccessRule.ACCESSIBLE);

      List<PackageReference> descs = new ArrayList<PackageReference>();
      descs.add(descC);
      descs.add(descB);
      descs.add(descA);

      Collections.sort(descs);

      assertEquals(descA, descs.get(0));
      assertEquals(descB, descs.get(1));
      assertEquals(descC, descs.get(2));
   }

   @Test
   public void testCompareByEnvironment()
   {
      BundleCandidate importer = newBundle("importer", "1");

      BundleCandidate exporter = newBundle("exporter", "1");

      PackageReference descA = new PackageReference(importer, "foo", null, exporter, null, AccessRule.ACCESSIBLE);
      PackageReference descB = new PackageReference(importer, "foo", null, null, null, AccessRule.ACCESSIBLE);

      List<PackageReference> descs = new ArrayList<PackageReference>();
      descs.add(descB);
      descs.add(descA);

      Collections.sort(descs);

      assertEquals(descA, descs.get(0));
      assertEquals(descB, descs.get(1));
   }

   @Test
   public void testCompareByVersion()
   {
      BundleCandidate importer = newBundle("importer", "1");

      PackageExport pkgA = BundleManifestFactory.eINSTANCE.createPackageExport();
      pkgA.getPackageNames().add("foo");

      PackageExport pkgB = BundleManifestFactory.eINSTANCE.createPackageExport();
      pkgB.getPackageNames().add("foo");
      pkgB.setVersion(Version.parse("1"));

      BundleCandidate exporterA = newBundle("exporterA", "1");
      BundleCandidate exporterB = newBundle("exporterB", "1");

      PackageReference descA = new PackageReference(importer, "foo", null, exporterA, pkgA, AccessRule.ACCESSIBLE);
      PackageReference descB = new PackageReference(importer, "foo", null, exporterB, pkgB, AccessRule.ACCESSIBLE);

      List<PackageReference> descs = new ArrayList<PackageReference>();
      descs.add(descB);
      descs.add(descA);

      Collections.sort(descs);

      assertEquals(descA, descs.get(0));
      assertEquals(descB, descs.get(1));

      pkgA.setVersion(Version.parse("2"));

      Collections.sort(descs);

      assertEquals(descB, descs.get(0));
      assertEquals(descA, descs.get(1));
   }

   private BundleCandidate newBundle(String symbolicName, String version)
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleSymbolicName(symbolicName);
      manifest.setBundleVersion(version);
      return bundle;
   }

}
