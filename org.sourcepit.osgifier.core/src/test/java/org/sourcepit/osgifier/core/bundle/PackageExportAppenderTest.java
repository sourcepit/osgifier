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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.EXPORT_PACKAGE;
import static org.sourcepit.common.manifest.osgi.ParameterType.DIRECTIVE;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.addBundleReference;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.appendType;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newBundleCandidate;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newJArchive;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sisu.launch.InjectedTest;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.EmbedInstruction;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaProject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class PackageExportAppenderTest extends InjectedTest {
   @Inject
   private PackageExportAppender exportAppender;

   @Test
   public void testNullArguments() {
      try {
         exportAppender.append(null, new LinkedPropertiesMap());
         fail();
      }
      catch (IllegalArgumentException e) {
      }

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      try {
         exportAppender.append(bundle, new LinkedPropertiesMap());
         fail();
      }
      catch (NullPointerException e) {
      }
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      try {
         exportAppender.append(bundle, new LinkedPropertiesMap());
         fail();
      }
      catch (NullPointerException e) {
      }
      JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      jProject.getType("src/main/java", "foo", "Bar", true);
      jProject.getType("src/test/java", "foo", "BarTest", true);
      bundle.setContent(jProject);

      exportAppender.append(bundle, new LinkedPropertiesMap());

      EList<PackageExport> exportPackage = bundle.getManifest().getExportPackage();
      assertThat(exportPackage.size(), Is.is(1));
   }

   @Test
   public void testMultiplePackageRoot() {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();

      BundleManifest mf = BundleManifestFactory.eINSTANCE.createBundleManifest();
      bundle.setManifest(mf);

      JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      jProject.getType("src/main/java", "foo", "Bar", true);
      jProject.getType("src/test/java", "foo", "BarTest", true);
      bundle.setContent(jProject);

      exportAppender.append(bundle, new LinkedPropertiesMap());

      EList<PackageExport> exportPackage = mf.getExportPackage();
      assertThat(exportPackage.size(), Is.is(1));
      assertThat(exportPackage.get(0).getPackageNames().size(), Is.is(1));
      assertThat(exportPackage.get(0).getPackageNames().get(0), IsEqual.equalTo("foo"));
   }

   @Test
   public void testDefaultPackage() {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();

      BundleManifest mf = BundleManifestFactory.eINSTANCE.createBundleManifest();
      bundle.setManifest(mf);

      JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      jProject.getType("/", null, "Bar", true);
      bundle.setContent(jProject);

      exportAppender.append(bundle, new LinkedPropertiesMap());

      assertNull(mf.getExportPackage());
   }

   @Test
   public void testFilterPlatformPackages() {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "java.lang.Object", 47);
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);

      BundleManifest manifest = bundle.getManifest();

      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(bundle, new LinkedPropertiesMap());
      assertEquals("java.lang;version=1.0.1,org.sourcepit;version=1.0.1", manifest.getHeaderValue(EXPORT_PACKAGE));
   }

   @Test
   public void testFallbackToBundleVersion() {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");

      exportAppender.append(bundle, new LinkedPropertiesMap());

      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE), IsEqual.equalTo("org.sourcepit;version=1.0.1"));
   }

   @Test
   public void testVersionFromPackageinfo() {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      JavaPackage jPackage = appendType(jArchive, "org.sourcepit.Foo", 47).getFile().getParentPackage();
      File packageInfo = jPackage.getFile("packageinfo", true);
      packageInfo.getAnnotation("content", true).setData("version", "1337");

      // test invalid version, too
      jPackage = appendType(jArchive, "org.sourcepit.foo.Bar", 47).getFile().getParentPackage();
      packageInfo = jPackage.getFile("packageinfo", true);
      packageInfo.getAnnotation("content", true).setData("version", "murks");

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(bundle, new LinkedPropertiesMap());

      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE),
         IsEqual.equalTo("org.sourcepit;version=1337,org.sourcepit.foo;version=1.0.1"));
   }

   @Test
   public void testDoNotEraseVersionOfPlatformFamilyPackages() {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "javax.xml.stream.Foo", 47);
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);

      BundleManifest manifest = bundle.getManifest();

      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(bundle, new LinkedPropertiesMap());
      assertEquals("javax.xml.stream;version=1.0.1,org.sourcepit;version=1.0.1",
         manifest.getHeaderValue(EXPORT_PACKAGE));
   }

   @Test
   public void testDoNotEraseVersionOfVendorPackages() {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "sun.misc.Foo", 47); // vendor specific package of compatible EE
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(bundle, new LinkedPropertiesMap());
      assertEquals("org.sourcepit;version=1.0.1,sun.misc;version=1.0.1", manifest.getHeaderValue(EXPORT_PACKAGE));
   }

   @Test
   public void testSortAlphabetically() {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "z.Foo", 47);
      appendType(jArchive, "d.Foo", 47);
      appendType(jArchive, "a.Foo", 47);
      appendType(jArchive, "a2.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(bundle, new LinkedPropertiesMap());
      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE),
         IsEqual.equalTo("a;version=1.0.1,a2;version=1.0.1,d;version=1.0.1,z;version=1.0.1"));
   }

   @Test
   public void testDoNotExportPackagesAlreadyExportedByDependency() {
      JavaArchive jArchivB = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchivB, "org.sourcepit.bundleB.Foo", 47);

      BundleCandidate bundleB = newBundleCandidate(jArchivB);
      BundleManifest manifestB = bundleB.getManifest();
      manifestB.setBundleVersion("2");

      JavaArchive jArchiveA = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchiveA, "org.sourcepit.bundleA.Foo", 47);
      appendType(jArchiveA, "org.sourcepit.bundleB.Foo", 47);

      BundleCandidate bundleA = newBundleCandidate(jArchiveA);
      BundleManifest manifestA = bundleA.getManifest();
      manifestA.setBundleVersion("1");

      addBundleReference(bundleA, bundleB);

      exportAppender.append(bundleB, new LinkedPropertiesMap());
      exportAppender.append(bundleA, new LinkedPropertiesMap());

      EList<PackageExport> exportPackage = manifestA.getExportPackage();
      assertEquals(1, exportPackage.size());
      assertEquals(1, exportPackage.get(0).getPackageNames().size());
      assertEquals("org.sourcepit.bundleA", exportPackage.get(0).getPackageNames().get(0));
   }

   @Test
   public void testInternal() {
      final PropertiesMap properties = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Type", 47);
      appendType(jArchive, "org.sourcepit.internal.Type", 47);
      appendType(jArchive, "org.sourcepit.internal.foo.Type", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1");

      exportAppender.append(bundle, properties);

      EList<PackageExport> exportPackage = manifest.getExportPackage();
      assertEquals(3, exportPackage.size());

      PackageExport packageExport = exportPackage.get(0);
      assertEquals("org.sourcepit", packageExport.getPackageNames().get(0));
      assertNull(packageExport.getParameter("x-internal"));

      packageExport = exportPackage.get(1);
      assertEquals("org.sourcepit.internal", packageExport.getPackageNames().get(0));
      assertNull(packageExport.getParameter("x-internal"));

      packageExport = exportPackage.get(2);
      assertEquals("org.sourcepit.internal.foo", packageExport.getPackageNames().get(0));
      assertNull(packageExport.getParameter("x-internal"));

      properties.put("osgifier.internalPackages", "**.internal.**, **.internal");

      bundle = newBundleCandidate(jArchive);
      manifest = bundle.getManifest();
      manifest.setBundleVersion("1");

      exportAppender.append(bundle, properties);

      exportPackage = manifest.getExportPackage();
      assertEquals(3, exportPackage.size());

      packageExport = exportPackage.get(0);
      assertEquals("org.sourcepit", packageExport.getPackageNames().get(0));
      assertNull(packageExport.getParameter("x-internal"));

      packageExport = exportPackage.get(1);
      assertEquals("org.sourcepit.internal", packageExport.getPackageNames().get(0));
      Parameter parameter = packageExport.getParameter("x-internal");
      assertNotNull(parameter);
      assertEquals(DIRECTIVE, parameter.getType());
      assertEquals("true", parameter.getValue());

      packageExport = exportPackage.get(2);
      assertEquals("org.sourcepit.internal.foo", packageExport.getPackageNames().get(0));
      parameter = packageExport.getParameter("x-internal");
      assertNotNull(parameter);
      assertEquals(DIRECTIVE, parameter.getType());
      assertEquals("true", parameter.getValue());
   }

   @Test
   public void testExportEmbeddedPackages() throws Exception {
      final PropertiesMap properties = new LinkedPropertiesMap();

      BundleCandidate a = newBundleCandidate("1", newJArchive("a.A"));
      BundleCandidate b = newBundleCandidate("2", newJArchive("b.B"));
      BundleCandidate c = newBundleCandidate("3", newJArchive("c.C"));
      BundleCandidate d = newBundleCandidate("4", newJArchive("d.D"));

      addBundleReference(a, b).setEmbedInstruction(EmbedInstruction.PACKED);
      addBundleReference(a, c).setEmbedInstruction(EmbedInstruction.UNPACKED);
      addBundleReference(a, d);

      exportAppender.append(d, properties);
      exportAppender.append(c, properties);
      exportAppender.append(b, properties);
      exportAppender.append(a, properties);

      List<PackageExport> exportPackage = a.getManifest().getExportPackage();
      assertEquals(3, exportPackage.size());

      PackageExport packageExport = exportPackage.get(0);
      assertEquals("[a]", packageExport.getPackageNames().toString());
      assertEquals("1", packageExport.getVersion().toMinimalString());

      packageExport = exportPackage.get(1);
      assertEquals("[b]", packageExport.getPackageNames().toString());
      assertEquals("2", packageExport.getVersion().toMinimalString());

      packageExport = exportPackage.get(2);
      assertEquals("[c]", packageExport.getPackageNames().toString());
      assertEquals("3", packageExport.getVersion().toMinimalString());
   }

   @Test
   public void testExportEmbeddedPackagesWithDups() throws Exception {
      final PropertiesMap properties = new LinkedPropertiesMap();

      BundleCandidate a = newBundleCandidate("1", newJArchive("a.A"));
      BundleCandidate b1 = newBundleCandidate("2", newJArchive("b.B"));
      BundleCandidate b2 = newBundleCandidate("3", newJArchive("b.B"));

      addBundleReference(a, b1).setEmbedInstruction(EmbedInstruction.PACKED);
      addBundleReference(a, b2).setEmbedInstruction(EmbedInstruction.UNPACKED);

      exportAppender.append(b2, properties);
      exportAppender.append(b1, properties);
      exportAppender.append(a, properties);

      List<PackageExport> exportPackage = a.getManifest().getExportPackage();
      assertEquals(2, exportPackage.size());

      PackageExport packageExport = exportPackage.get(0);
      assertEquals("[a]", packageExport.getPackageNames().toString());
      assertEquals("1", packageExport.getVersion().toMinimalString());

      packageExport = exportPackage.get(1);
      assertEquals("[b]", packageExport.getPackageNames().toString());
      assertEquals("2", packageExport.getVersion().toMinimalString());
   }
}
