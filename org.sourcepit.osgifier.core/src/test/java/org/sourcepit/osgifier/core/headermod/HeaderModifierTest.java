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

package org.sourcepit.osgifier.core.headermod;

import static org.junit.Assert.*;

import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgifier.core.headermod.HeaderModifications;
import org.sourcepit.osgifier.core.headermod.HeaderModifier;
import org.sourcepit.osgifier.core.headermod.SetHeaderModification;

public class HeaderModifierTest
{
   @Test
   public void testSetHeader()
   {
      final HeaderModifier headerModifier = new HeaderModifier();

      HeaderModifications headerMods = new HeaderModifications();

      SetHeaderModification set = new SetHeaderModification();
      set.setName("Bundle-Activator");
      set.setValue("com.acme.Foo");

      headerMods.getHeaders().add(set);

      BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();

      headerModifier.applyModifications(manifest, headerMods);

      assertEquals("com.acme.Foo", manifest.getBundleActivator());

      set.setValue("wurst");
      headerModifier.applyModifications(manifest, headerMods);
      assertEquals("wurst", manifest.getBundleActivator());

   }

   @Test
   public void testSetHeaderAfter()
   {
      final HeaderModifier headerModifier = new HeaderModifier();

      HeaderModifications headerMods = new HeaderModifications();

      SetHeaderModification set = new SetHeaderModification();
      set.setName("Bundle-Activator");
      set.setValue("com.acme.Foo");
      set.setAfter("Bundle-Version");
      headerMods.getHeaders().add(set);

      BundleManifest manifest;
      manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      headerModifier.applyModifications(manifest, headerMods);
      assertEquals("com.acme.Foo", manifest.getBundleActivator());

      manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      manifest.setBundleVersion("1.0.0.qualifier");
      PackageExport pe = BundleManifestFactory.eINSTANCE.createPackageExport();
      pe.getPackageNames().add("com.acme");
      manifest.getExportPackage(true).add(pe);

      headerModifier.applyModifications(manifest, headerMods);

      EList<Entry<String, String>> headers = manifest.getHeaders();
      assertEquals(5, headers.size());

      assertEquals("Manifest-Version", headers.get(0).getKey());
      assertEquals("1.0", headers.get(0).getValue());

      assertEquals("Bundle-ManifestVersion", headers.get(1).getKey());
      assertEquals("2", headers.get(1).getValue());

      assertEquals("Bundle-Version", headers.get(2).getKey());
      assertEquals("1.0.0.qualifier", headers.get(2).getValue());

      assertEquals("Bundle-Activator", headers.get(3).getKey());
      assertEquals("com.acme.Foo", headers.get(3).getValue());

      assertEquals("Export-Package", headers.get(4).getKey());
      assertEquals("com.acme", headers.get(4).getValue());

      set.setValue("wurst");
      set.setAfter("Export-Package");

      headerModifier.applyModifications(manifest, headerMods);

      assertEquals("Manifest-Version", headers.get(0).getKey());
      assertEquals("1.0", headers.get(0).getValue());

      assertEquals("Bundle-ManifestVersion", headers.get(1).getKey());
      assertEquals("2", headers.get(1).getValue());

      assertEquals("Bundle-Version", headers.get(2).getKey());
      assertEquals("1.0.0.qualifier", headers.get(2).getValue());

      assertEquals("Export-Package", headers.get(3).getKey());
      assertEquals("com.acme", headers.get(3).getValue());

      assertEquals("Bundle-Activator", headers.get(4).getKey());
      assertEquals("wurst", headers.get(4).getValue());
   }

   @Test
   public void testSetHeaderBefore()
   {
      final HeaderModifier headerModifier = new HeaderModifier();

      HeaderModifications headerMods = new HeaderModifications();

      SetHeaderModification set = new SetHeaderModification();
      set.setName("Bundle-Activator");
      set.setValue("com.acme.Foo");
      set.setBefore("Bundle-Version");
      headerMods.getHeaders().add(set);

      BundleManifest manifest;
      manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      headerModifier.applyModifications(manifest, headerMods);
      assertEquals("com.acme.Foo", manifest.getBundleActivator());

      manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      manifest.setBundleVersion("1.0.0.qualifier");
      PackageExport pe = BundleManifestFactory.eINSTANCE.createPackageExport();
      pe.getPackageNames().add("com.acme");
      manifest.getExportPackage(true).add(pe);

      headerModifier.applyModifications(manifest, headerMods);

      EList<Entry<String, String>> headers = manifest.getHeaders();
      assertEquals(5, headers.size());

      assertEquals("Manifest-Version", headers.get(0).getKey());
      assertEquals("1.0", headers.get(0).getValue());

      assertEquals("Bundle-ManifestVersion", headers.get(1).getKey());
      assertEquals("2", headers.get(1).getValue());

      assertEquals("Bundle-Activator", headers.get(2).getKey());
      assertEquals("com.acme.Foo", headers.get(2).getValue());

      assertEquals("Bundle-Version", headers.get(3).getKey());
      assertEquals("1.0.0.qualifier", headers.get(3).getValue());

      assertEquals("Export-Package", headers.get(4).getKey());
      assertEquals("com.acme", headers.get(4).getValue());

      set.setValue("wurst");
      set.setBefore("Manifest-Version");

      headerModifier.applyModifications(manifest, headerMods);
      assertEquals(5, headers.size());

      assertEquals("Bundle-Activator", headers.get(0).getKey());
      assertEquals("wurst", headers.get(0).getValue());

      assertEquals("Manifest-Version", headers.get(1).getKey());
      assertEquals("1.0", headers.get(1).getValue());

      assertEquals("Bundle-ManifestVersion", headers.get(2).getKey());
      assertEquals("2", headers.get(2).getValue());

      assertEquals("Bundle-Version", headers.get(3).getKey());
      assertEquals("1.0.0.qualifier", headers.get(3).getValue());

      assertEquals("Export-Package", headers.get(4).getKey());
      assertEquals("com.acme", headers.get(4).getValue());
   }

   @Test
   public void testRemovals() throws Exception
   {
      final HeaderModifier headerModifier = new HeaderModifier();

      final BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      manifest.setBundleSymbolicName("foo");
      manifest.setBundleVersion("1.0.0");
      manifest.setHeader("My-Header", "hui");

      HeaderModifications headerMods = new HeaderModifications();
      headerMods.getRemovals().add("Bundle-Version");

      headerModifier.applyModifications(manifest, headerMods);

      EMap<String, String> headers = manifest.getHeaders();
      assertEquals(4, headers.size());

      assertEquals("Manifest-Version", headers.get(0).getKey());
      assertEquals("1.0", headers.get(0).getValue());

      assertEquals("Bundle-ManifestVersion", headers.get(1).getKey());
      assertEquals("2", headers.get(1).getValue());

      assertEquals("Bundle-SymbolicName", headers.get(2).getKey());
      assertEquals("foo", headers.get(2).getValue());

      assertEquals("My-Header", headers.get(3).getKey());
      assertEquals("hui", headers.get(3).getValue());

      headerMods.getRemovals().add("Bundle-.*");

      headerModifier.applyModifications(manifest, headerMods);

      assertEquals(2, headers.size());

      assertEquals("Manifest-Version", headers.get(0).getKey());
      assertEquals("1.0", headers.get(0).getValue());

      assertEquals("My-Header", headers.get(1).getKey());
      assertEquals("hui", headers.get(1).getValue());
   }
}
