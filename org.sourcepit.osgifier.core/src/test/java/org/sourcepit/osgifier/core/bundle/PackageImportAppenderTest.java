/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.bundle;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.EXPORT_PACKAGE;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.IMPORT_PACKAGE;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.REQUIRE_BUNDLE;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.addBundleReference;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.addPackageExport;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.appendPackageExport;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.appendTypeWithReferences;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newBundleCandidate;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newJArchive;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newPackageExport;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.setInternal;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sisu.launch.InjectedTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.PackageImport;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.core.bundle.PackageExportAppender;
import org.sourcepit.osgifier.core.bundle.PackageImportAppender;
import org.sourcepit.osgifier.core.bundle.VersionRangePolicy;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.EmbedInstruction;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class PackageImportAppenderTest extends InjectedTest
{
   @Inject
   private PackageExportAppender exportAppender;

   @Inject
   private PackageImportAppender importAppender;

   @Test
   public void testSortAlphabetically()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      appendPackageExport(ref, newPackageExport("a3", null));

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "z.Bar", "a3.Bar");
      appendTypeWithReferences(jArchive, "z.Bar", 47, "a2.Bar");
      appendTypeWithReferences(jArchive, "a2.Bar", 47, "a.Bar");

      BundleCandidate bundle = newBundleCandidate("1.0.1", "OSGi/Minimum-1.0", jArchive);
      bundle.getDependencies().add(ref);
      appendPackageExport(bundle, newPackageExport("z", null));
      appendPackageExport(bundle, newPackageExport("a2", null));
      appendPackageExport(bundle, newPackageExport("a", null));

      importAppender.append(bundle, options);

      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("a,a2,a3,z"));
   }

   @Test
   public void testImportReferencedPackages()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "org.sourcepit.Foo", 47, "foo.Bar");
      BundleCandidate bundle = newBundleCandidate("1.0.1", "OSGi/Minimum-1.0", jArchive);

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      appendPackageExport(ref, newPackageExport("foo", "1.2.3"));

      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);

      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo;version=\"[1.2,2)\""));

      options.put("osgifier.eraseMicro", "false");

      bundle.getManifest().setHeader(IMPORT_PACKAGE, null);

      importAppender.append(bundle, options);

      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo;version=\"[1.2.3,2)\""));
   }

   @Test
   public void testFilterJavaPackages()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      appendPackageExport(ref, newPackageExport("b", null));

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "java.lang.String", "javax.microedition.io.Foo", "b.Foo");

      BundleCandidate bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);

      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("b,javax.microedition.io"));
   }

   @Test
   public void testIgnoreUnresolveablePackages()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "b.Foo");

      BundleCandidate bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);

      importAppender.append(bundle, options);

      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, nullValue());
   }

   @Test
   public void testEquivalentVersionsForPublicSelfImports()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "b.Foo");

      BundleCandidate bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);
      appendPackageExport(bundle, newPackageExport("b", "2"));

      importAppender.append(bundle, options);

      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("b;version=\"[2,2.1)\""));
   }

   @Test
   public void testDefaultVersionIfPackageIsExportedWithDefaultVersion()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "b.Foo", "foo.Foo");

      BundleCandidate bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);
      appendPackageExport(bundle, newPackageExport("b", null));

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      appendPackageExport(ref, newPackageExport("foo", null));

      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);

      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("b,foo"));
   }

   @Test
   public void testIgnorePlatformFamilyPackages()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "javax.xml.stream.Foo", "sun.misc.Foo");
      BundleCandidate bundle = newBundleCandidate("1.0.1", "JavaSE-1.6", jArchive);
      importAppender.append(bundle, options);
      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("javax.xml.stream"));
   }

   @Test
   public void testUseVersionRangeOfReference()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "foo.Foo");

      // test package version fits into version range of reference
      BundleCandidate bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setVersionRange(VersionRange.parse("[1,2)"));
      appendPackageExport(ref, newPackageExport("foo", "1.1"));
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);
      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo;version=\"[1,2)\""));

      // test package version doesn't fit into version range of reference
      bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);

      ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setVersionRange(VersionRange.parse("[1,2)"));
      appendPackageExport(ref, newPackageExport("foo", "3"));
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);
      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo;version=\"[3,4)\""));

      // test package version is default version
      bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);

      ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setVersionRange(VersionRange.parse("[1,2)"));
      appendPackageExport(ref, newPackageExport("foo", "0.0.0"));
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);
      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo"));

      // test package version is default version (null)
      bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);

      ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setVersionRange(VersionRange.parse("[1,2)"));
      appendPackageExport(ref, newPackageExport("foo", null));
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);
      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo"));

      // test ref version isn't a "real" range
      bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);

      ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setVersionRange(VersionRange.parse("1"));
      appendPackageExport(ref, newPackageExport("foo", "2"));
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);
      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo;version=\"[2,3)\"")); // unbounded version ranges of package refs
                                                                            // are ignored
   }

   @Test
   public void testOptionalReference()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "foo.Foo");

      // test package version fits into version range of reference
      BundleCandidate bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setOptional(true);
      appendPackageExport(ref, newPackageExport("foo", null));
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);
      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo;resolution:=optional"));
   }

   @Test
   public void testIgnoreUnboundedVersionRange()
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "foo.Foo");

      BundleCandidate bundle = newBundleCandidate("999", "CDC-1.0/Foundation-1.0", jArchive);

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setVersionRange(VersionRange.parse("1"));
      appendPackageExport(ref, newPackageExport("foo", "2"));
      bundle.getDependencies().add(ref);

      importAppender.append(bundle, options);
      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("foo;version=\"[2,3)\""));
   }

   @Test
   public void testImportOwnExports() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "javax.xml.stream.XMLStreamWriter", 47, "javax.xml.namespace.QName");

      BundleCandidate bundle = newBundleCandidate("1.0.1", null, jArchive);
      bundle.getManifest().setBundleSymbolicName("stax.api");
      bundle.setSymbolicName("stax.api");

      appendPackageExport(bundle, newPackageExport("javax.xml.namespace", null));
      appendPackageExport(bundle, newPackageExport("javax.xml.stream", null));

      importAppender.append(bundle, options);

      EList<PackageImport> importPackage = bundle.getManifest().getImportPackage();
      assertEquals(2, importPackage.size());

      PackageImport packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals(packageImport.getPackageNames().get(0), "javax.xml.namespace");

      packageImport = importPackage.get(1);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals(packageImport.getPackageNames().get(0), "javax.xml.stream");
   }

   @Test
   public void testVersionErasureOfKnownEEPackages() throws Exception
   {
      BundleCandidate requiredBundle = newBundleCandidate(null);
      addPackageExport(requiredBundle, "javax.activation", "1.2.3");

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "bundle.package", 47, "javax.activation.MimeType");

      BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setOptional(true);
      reference.setTarget(requiredBundle);

      BundleCandidate bundleCandidate = newBundleCandidate("1", "J2SE-1.4", jArchive);
      bundleCandidate.getDependencies().add(reference);

      PropertiesMap options = new LinkedPropertiesMap();
      importAppender.append(bundleCandidate, options);

      List<PackageImport> importPackage = bundleCandidate.getManifest().getImportPackage();
      assertEquals(1, importPackage.size());

      final PackageImport packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals("javax.activation", packageImport.getPackageNames().get(0));
      assertNull(packageImport.getVersion());
      assertEquals("optional", packageImport.getParameterValue("resolution"));
   }

   @Test
   public void testVersionErasureOfKnownVendorPackages() throws Exception
   {
      BundleCandidate requiredBundle = newBundleCandidate(null);
      addPackageExport(requiredBundle, "com.sun.javafx.event", "1.2.3");

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "bundle.package", 47, "com.sun.javafx.event.Foo");

      BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setOptional(true);
      reference.setTarget(requiredBundle);

      BundleCandidate bundleCandidate = newBundleCandidate("1", "J2SE-1.4", jArchive);
      bundleCandidate.getDependencies().add(reference);

      PropertiesMap options = new LinkedPropertiesMap();
      importAppender.append(bundleCandidate, options);

      List<PackageImport> importPackage = bundleCandidate.getManifest().getImportPackage();
      assertEquals(1, importPackage.size());

      final PackageImport packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals("com.sun.javafx.event", packageImport.getPackageNames().get(0));
      assertNull(packageImport.getVersion());
      assertEquals("optional", packageImport.getParameterValue("resolution"));
   }

   @Test
   public void testCustomVersionRangePolicyForPublicImports() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();
      options.put("osgifier.publicImportPolicy", VersionRangePolicy.STRICT.literal());

      BundleCandidate requiredBundle = newBundleCandidate(null);
      addPackageExport(requiredBundle, "required.package", "1.2.3");

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "bundle.package", 47, "required.package.Foo");

      BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setTarget(requiredBundle);

      BundleCandidate bundleCandidate = newBundleCandidate(jArchive);
      bundleCandidate.getDependencies().add(reference);

      importAppender.append(bundleCandidate, options);

      List<PackageImport> importPackage = bundleCandidate.getManifest().getImportPackage();
      assertEquals(1, importPackage.size());

      final PackageImport packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals("required.package", packageImport.getPackageNames().get(0));
      assertEquals("[1.2.3,1.2.4)", packageImport.getVersion().toString());
   }

   @Test
   public void testCustomVersionRangePolicyForInternalImports() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();
      options.put("osgifier.internalImportPolicy", VersionRangePolicy.GREATER_OR_EQUAL.literal());

      BundleCandidate requiredBundle = newBundleCandidate(null);
      setInternal(addPackageExport(requiredBundle, "required.package", "1.2.3"));

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "bundle.package", 47, "required.package.Foo");

      BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setTarget(requiredBundle);

      BundleCandidate bundleCandidate = newBundleCandidate(jArchive);
      bundleCandidate.getDependencies().add(reference);

      importAppender.append(bundleCandidate, options);

      List<PackageImport> importPackage = bundleCandidate.getManifest().getImportPackage();
      assertEquals(1, importPackage.size());

      final PackageImport packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals("required.package", packageImport.getPackageNames().get(0));
      // 1.2 because of option osgifier.eraseMicro = true
      assertEquals("1.2", packageImport.getVersion().toString());
   }

   @Test
   public void testCustomVersionRangePolicyForSelfImports() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();
      options.put("osgifier.selfImportPolicy", VersionRangePolicy.ANY.literal());

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "bundle.package", 47, "required.package.Foo");

      BundleCandidate bundleCandidate = newBundleCandidate(jArchive);
      setInternal(addPackageExport(bundleCandidate, "required.package", "1.2.3"));

      importAppender.append(bundleCandidate, options);

      List<PackageImport> importPackage = bundleCandidate.getManifest().getImportPackage();
      assertEquals(1, importPackage.size());

      final PackageImport packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals("required.package", packageImport.getPackageNames().get(0));
      // 1.2 because of option osgifier.eraseMicro = true
      assertNull(packageImport.getVersion());
   }

   @Test
   public void testEraseMicro() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      BundleCandidate requiredBundle = newBundleCandidate(null);
      addPackageExport(requiredBundle, "required.package", "1.2.3");

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "bundle.package", 47, "required.package.Foo");

      BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setTarget(requiredBundle);

      BundleCandidate bundleCandidate = newBundleCandidate(jArchive);
      bundleCandidate.getDependencies().add(reference);

      importAppender.append(bundleCandidate, options);

      List<PackageImport> importPackage = bundleCandidate.getManifest().getImportPackage();
      assertEquals(1, importPackage.size());

      PackageImport packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals("required.package", packageImport.getPackageNames().get(0));
      assertEquals("[1.2,2)", packageImport.getVersion().toString());

      options.setBoolean("osgifier.eraseMicro", false);
      bundleCandidate.getManifest().setImportPackage(null);
      importAppender.append(bundleCandidate, options);

      importPackage = bundleCandidate.getManifest().getImportPackage();
      assertEquals(1, importPackage.size());

      packageImport = importPackage.get(0);
      assertEquals(1, packageImport.getPackageNames().size());
      assertEquals("required.package", packageImport.getPackageNames().get(0));
      assertEquals("[1.2.3,2)", packageImport.getVersion().toString());
   }

   @Test
   public void testReimportEmbeddedPackages() throws Exception
   {
      final PropertiesMap options = new LinkedPropertiesMap();

      BundleCandidate a = newBundleCandidate("1", newJArchive("a.A"));
      BundleCandidate b = newBundleCandidate("2", newJArchive("b.B"));
      BundleCandidate c = newBundleCandidate("3", newJArchive("c.C"));
      BundleCandidate d = newBundleCandidate("4", newJArchive("d.D"));

      addBundleReference(a, b).setEmbedInstruction(EmbedInstruction.PACKED);
      addBundleReference(a, c).setEmbedInstruction(EmbedInstruction.UNPACKED);
      addBundleReference(a, d);

      exportAppender.append(d, options);
      importAppender.append(d, options);
      exportAppender.append(c, options);
      importAppender.append(c, options);
      exportAppender.append(b, options);
      importAppender.append(b, options);
      exportAppender.append(a, options);
      importAppender.append(a, options);

      final List<PackageImport> importPackage = a.getManifest().getImportPackage();
      assertEquals(3, importPackage.size());

      PackageImport packageImport = importPackage.get(0);
      assertEquals("[a]", packageImport.getPackageNames().toString());
      assertEquals("[1,1.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(1);
      assertEquals("[b]", packageImport.getPackageNames().toString());
      assertEquals("[2,2.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(2);
      assertEquals("[c]", packageImport.getPackageNames().toString());
      assertEquals("[3,3.1)", packageImport.getVersion().toString());
   }

   @Test
   public void testReimportEmbeddedPackagesWithDep() throws Exception
   {
      final PropertiesMap options = new LinkedPropertiesMap();

      BundleCandidate a = newBundleCandidate("1", newJArchive("a.A"));
      BundleCandidate b = newBundleCandidate("2", newJArchive("b.B"));

      final JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "c.C", 47, "d.D");
      BundleCandidate c = newBundleCandidate("3", jArchive);

      BundleCandidate d = newBundleCandidate("4", newJArchive("d.D"));
      addPackageExport(d, "d", "5");

      addBundleReference(a, b).setEmbedInstruction(EmbedInstruction.PACKED);
      addBundleReference(a, c).setEmbedInstruction(EmbedInstruction.UNPACKED);
      addBundleReference(c, d);

      exportAppender.append(d, options);
      importAppender.append(d, options);
      exportAppender.append(c, options);
      importAppender.append(c, options);
      exportAppender.append(b, options);
      importAppender.append(b, options);
      exportAppender.append(a, options);
      importAppender.append(a, options);


      final List<PackageImport> importPackage = a.getManifest().getImportPackage();
      assertEquals(4, importPackage.size());

      PackageImport packageImport = importPackage.get(0);
      assertEquals("[a]", packageImport.getPackageNames().toString());
      assertEquals("[1,1.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(1);
      assertEquals("[b]", packageImport.getPackageNames().toString());
      assertEquals("[2,2.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(2);
      assertEquals("[c]", packageImport.getPackageNames().toString());
      assertEquals("[3,3.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(3);
      assertEquals("[d]", packageImport.getPackageNames().toString());
      assertEquals("[5,6)", packageImport.getVersion().toString());
   }

   @Test
   public void testReimportEmbeddedPackagesWithPublicAndInternalPackageRequirement() throws Exception
   {
      final PropertiesMap options = new LinkedPropertiesMap();
      options.put("osgifier.internalPackages", "c.internal");

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.A", 47, "c.C");
      BundleCandidate a = newBundleCandidate("1", jArchive);

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "b.B", 47, "c.internal.C");
      BundleCandidate b = newBundleCandidate("2", jArchive);

      BundleCandidate c = newBundleCandidate("4", newJArchive("c.D", "c.internal.C"));

      addBundleReference(a, b).setEmbedInstruction(EmbedInstruction.PACKED);
      addBundleReference(a, c);
      addBundleReference(b, c);

      exportAppender.append(c, options);
      importAppender.append(c, options);
      exportAppender.append(b, options);
      importAppender.append(b, options);
      exportAppender.append(a, options);
      importAppender.append(a, options);

      assertEquals("c;version=4,c.internal;version=4;x-internal:=true", c.getManifest().getHeaderValue(EXPORT_PACKAGE));

      final List<PackageImport> importPackage = a.getManifest().getImportPackage();
      assertEquals(4, importPackage.size());

      PackageImport packageImport = importPackage.get(0);
      assertEquals("[a]", packageImport.getPackageNames().toString());
      assertEquals("[1,1.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(1);
      assertEquals("[b]", packageImport.getPackageNames().toString());
      assertEquals("[2,2.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(2);
      assertEquals("[c]", packageImport.getPackageNames().toString());
      // [4,4.1) would be better but hard to determine
      assertEquals("[4,5)", packageImport.getVersion().toString());

      packageImport = importPackage.get(3);
      assertEquals("[c.internal]", packageImport.getPackageNames().toString());
      assertEquals("[4,4.1)", packageImport.getVersion().toString());
   }

   @Test
   public void testRecommendedImportPolicy() throws Exception
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.A1", 47, "b.B");
      appendTypeWithReferences(jArchive, "a.A2", 47, "c.internal.C");

      BundleCandidate a = newBundleCandidate("1", jArchive);
      a.getManifest().setBundleSymbolicName("a");

      BundleCandidate b = newBundleCandidate("2", newJArchive("b.B"));
      b.getManifest().setBundleSymbolicName("b");
      b.getManifest().setHeader("Osgifier-RecommendedImportPolicy", "any");

      BundleCandidate c = newBundleCandidate("3", newJArchive("c.internal.C"));
      c.getManifest().setBundleSymbolicName("c");

      addBundleReference(a, b);
      addBundleReference(a, c);

      final PropertiesMap options = new LinkedPropertiesMap();
      options.put("osgifier.internalPackages", "c.internal");

      exportAppender.append(c, options);
      exportAppender.append(b, options);
      exportAppender.append(a, options);

      importAppender.append(a, options);

      List<PackageImport> importPackage = a.getManifest().getImportPackage();
      assertEquals(3, importPackage.size());

      PackageImport packageImport = importPackage.get(1);
      assertEquals("b", packageImport.getPackageNames().get(0));
      assertNull(packageImport.getVersion());

      packageImport = importPackage.get(2);
      assertEquals("c.internal", packageImport.getPackageNames().get(0));
      assertEquals("[3,3.1)", packageImport.getVersion().toString());


      // test options overrides header
      a.getManifest().setHeader(IMPORT_PACKAGE, null);
      options.put("osgifier.recommendedImportPolicies", "**=strict|perfect");
      importAppender.append(a, options);

      importPackage = a.getManifest().getImportPackage();
      assertEquals(3, importPackage.size());

      packageImport = importPackage.get(1);
      assertEquals("b", packageImport.getPackageNames().get(0));
      assertEquals("[2,2.0.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(2);
      assertEquals("c.internal", packageImport.getPackageNames().get(0));
      assertEquals("[3,3]", packageImport.getVersion().toString());

      // test options publicImportPolicy/internalImportPolicy doesn't override recommendedImportPolicies
      a.getManifest().setHeader(IMPORT_PACKAGE, null);
      options.put("osgifier.publicImportPolicy", VersionRangePolicy.ANY.literal());
      options.put("osgifier.internalImportPolicy", VersionRangePolicy.GREATER_OR_EQUAL.literal());
      importAppender.append(a, options);

      importPackage = a.getManifest().getImportPackage();
      assertEquals(3, importPackage.size());

      packageImport = importPackage.get(1);
      assertEquals("b", packageImport.getPackageNames().get(0));
      assertEquals("[2,2.0.1)", packageImport.getVersion().toString());

      packageImport = importPackage.get(2);
      assertEquals("c.internal", packageImport.getPackageNames().get(0));
      assertEquals("[3,3]", packageImport.getVersion().toString());
   }

   @Test
   public void testRecommendedImportPolicyOnSelfImports() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "a.Bar", 47, "b.Foo");

      BundleCandidate bundle = newBundleCandidate("1.0.1", "CDC-1.0/Foundation-1.0", jArchive);
      bundle.getManifest().setBundleSymbolicName("bundle");
      appendPackageExport(bundle, newPackageExport("b", "2"));

      importAppender.append(bundle, options);

      String packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("b;version=\"[2,2.1)\""));

      options.put("osgifier.recommendedImportPolicies", "**=any");
      bundle.getManifest().setHeader(IMPORT_PACKAGE, null);
      importAppender.append(bundle, options);

      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("b"));

      bundle.getManifest().setHeader(IMPORT_PACKAGE, null);
      options.put("osgifier.recommendedImportPolicies", "**=any|greaterOrEqual");
      importAppender.append(bundle, options);

      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("b;version=\"2\""));

      // test options selfImportPolicy doesn't override recommendedImportPolicies
      bundle.getManifest().setHeader(IMPORT_PACKAGE, null);
      options.put("osgifier.selfImportPolicy", VersionRangePolicy.ANY.literal());
      importAppender.append(bundle, options);

      packageImports = bundle.getManifest().getHeaderValue(IMPORT_PACKAGE);
      assertThat(packageImports, IsEqual.equalTo("b;version=\"2\""));
   }

   @Test
   public void testRequireBundle() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      BundleCandidate c = newBundle("c", "3");
      appendTypeWithReferences((JavaArchive) c.getContent(), "c.C", 47);

      BundleCandidate b = newBundle("b", "2");
      appendTypeWithReferences((JavaArchive) b.getContent(), "b.B", 47);

      BundleCandidate a = newBundle("a", "1");
      appendTypeWithReferences((JavaArchive) a.getContent(), "a.A", 47, "b.B", "c.C");

      addBundleReference(a, b);
      addBundleReference(a, c);

      exportAppender.append(c, options);
      exportAppender.append(b, options);
      exportAppender.append(a, options);

      options.put("osgifier.requireBundle", "c");
      importAppender.append(a, options);

      BundleManifest mf = a.getManifest();
      assertEquals("c;bundle-version=\"[3,4)\"", mf.getHeaderValue(REQUIRE_BUNDLE));
      assertEquals("a;version=\"[1,1.1)\",b;version=\"[2,3)\"", mf.getHeaderValue(IMPORT_PACKAGE));

      options.put("osgifier.requireBundle", "b,c");
      mf.setRequireBundle(null);
      mf.setImportPackage(null);
      importAppender.append(a, options);
      assertEquals("b;bundle-version=\"[2,3)\",c;bundle-version=\"[3,4)\"", mf.getHeaderValue(REQUIRE_BUNDLE));
      assertEquals("a;version=\"[1,1.1)\"", mf.getHeaderValue(IMPORT_PACKAGE));

      options.put("osgifier.requireBundle", "**");
      mf.setRequireBundle(null);
      mf.setImportPackage(null);
      importAppender.append(a, options);
      assertEquals("b;bundle-version=\"[2,3)\",c;bundle-version=\"[3,4)\"", mf.getHeaderValue(REQUIRE_BUNDLE));
      assertEquals("a;version=\"[1,1.1)\"", mf.getHeaderValue(IMPORT_PACKAGE));
   }

   @Test
   public void testRequireBundleUsePackageImportsForSelfReferences() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      BundleCandidate a = newBundle("a", "1");
      appendTypeWithReferences((JavaArchive) a.getContent(), "a.A", 47);
      exportAppender.append(a, options);

      options.put("osgifier.requireBundle", "a");
      importAppender.append(a, options);

      BundleManifest mf = a.getManifest();
      assertNull(mf.getHeaderValue(REQUIRE_BUNDLE));
      assertEquals("a;version=\"[1,1.1)\"", mf.getHeaderValue(IMPORT_PACKAGE));
   }

   @Test
   public void testRequireBundleWithInternalPackage() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      BundleCandidate b = newBundle("b", "2");
      appendTypeWithReferences((JavaArchive) b.getContent(), "b.B", 47);
      appendTypeWithReferences((JavaArchive) b.getContent(), "b.internal.B", 47);

      BundleCandidate a = newBundle("a", "1");
      appendTypeWithReferences((JavaArchive) a.getContent(), "a.A", 47, "b.B", "b.internal.B");

      addBundleReference(a, b);

      options.put("osgifier.internalPackages", "b.internal");
      exportAppender.append(b, options);
      exportAppender.append(a, options);

      options.put("osgifier.requireBundle", "b");
      importAppender.append(a, options);

      BundleManifest mf = a.getManifest();
      assertEquals("b;bundle-version=\"[2,2.1)\"", mf.getHeaderValue(REQUIRE_BUNDLE));
      assertEquals("a;version=\"[1,1.1)\"", mf.getHeaderValue(IMPORT_PACKAGE));
   }

   @Test
   public void testRequireBundleWithOptional() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      BundleCandidate b = newBundle("b", "2");
      appendTypeWithReferences((JavaArchive) b.getContent(), "b.B", 47);

      BundleCandidate a = newBundle("a", "1");
      appendTypeWithReferences((JavaArchive) a.getContent(), "a.A", 47, "b.B");

      addBundleReference(a, b).setOptional(true);

      exportAppender.append(b, options);
      exportAppender.append(a, options);

      options.put("osgifier.requireBundle", "b");
      importAppender.append(a, options);

      BundleManifest mf = a.getManifest();
      assertEquals("b;bundle-version=\"[2,3)\";resolution:=optional", mf.getHeaderValue(REQUIRE_BUNDLE));
      assertEquals("a;version=\"[1,1.1)\"", mf.getHeaderValue(IMPORT_PACKAGE));
   }

   private BundleCandidate newBundle(String symbolicName, String version)
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate a = newBundleCandidate(version, jArchive);
      a.setSymbolicName(symbolicName);
      a.getManifest().setBundleSymbolicName(symbolicName);
      return a;
   }
}
