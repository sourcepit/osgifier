/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;

public class ExportDescriptionTest
{

   @Test
   public void testCompareByImporterName()
   {
      BundleCandidate bundleA = newBundle("a", "1");
      BundleCandidate bundleZ = newBundle("z", "1");

      ExportDescription descA = new ExportDescription(bundleA, "foo", null, null, null, null);
      ExportDescription descZ = new ExportDescription(bundleZ, "foo", null, null, null, null);

      List<ExportDescription> descs = new ArrayList<ExportDescription>();
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

      ExportDescription descA = new ExportDescription(bundleA, "foo", null, null, null, null);
      ExportDescription descZ = new ExportDescription(bundleZ, "foo", null, null, null, null);

      List<ExportDescription> descs = new ArrayList<ExportDescription>();
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

      ExportDescription descA = new ExportDescription(bundleA, "packageA", null, null, null, null);
      ExportDescription descZ = new ExportDescription(bundleZ, "packageB", null, null, null, null);

      List<ExportDescription> descs = new ArrayList<ExportDescription>();
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

      ExportDescription descA = new ExportDescription(bundleZ, "foo", null, bundleA, null, null);
      ExportDescription descZ = new ExportDescription(bundleZ, "foo", null, bundleZ, null, null);

      List<ExportDescription> descs = new ArrayList<ExportDescription>();
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

      ExportDescription descA = new ExportDescription(importer, "foo", null, exporter, null, AccessRule.NON_ACCESSIBLE);
      ExportDescription descB = new ExportDescription(importer, "foo", null, exporter, null, AccessRule.DISCOURAGED);
      ExportDescription descC = new ExportDescription(importer, "foo", null, exporter, null, AccessRule.ACCESSIBLE);

      List<ExportDescription> descs = new ArrayList<ExportDescription>();
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

      ExportDescription descA = new ExportDescription(importer, "foo", null, exporter, null, AccessRule.ACCESSIBLE);
      ExportDescription descB = new ExportDescription(importer, "foo", null, null, null, AccessRule.ACCESSIBLE);

      List<ExportDescription> descs = new ArrayList<ExportDescription>();
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

      ExportDescription descA = new ExportDescription(importer, "foo", null, exporterA, pkgA, AccessRule.ACCESSIBLE);
      ExportDescription descB = new ExportDescription(importer, "foo", null, exporterB, pkgB, AccessRule.ACCESSIBLE);

      List<ExportDescription> descs = new ArrayList<ExportDescription>();
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
