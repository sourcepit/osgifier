/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.EXPORT_PACKAGE;
import static org.sourcepit.common.manifest.osgi.ParameterType.DIRECTIVE;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.appendType;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.newBundleCandidate;

import javax.inject.Inject;
import java.lang.IllegalArgumentException;

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
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.java.File;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaProject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class PackageExportAppenderTest extends InjectedTest
{
   @Inject
   private PackageExportAppender exportAppender;

   @Test
   public void testNullArguments()
   {
      try
      {
         exportAppender.append(new LinkedPropertiesMap(), null);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      try
      {
         exportAppender.append(new LinkedPropertiesMap(), bundle);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      try
      {
         exportAppender.append(new LinkedPropertiesMap(), bundle);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }
      JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      jProject.getType("src/main/java", "foo", "Bar", true);
      jProject.getType("src/test/java", "foo", "BarTest", true);
      bundle.setContent(jProject);

      exportAppender.append(new LinkedPropertiesMap(), bundle);

      EList<PackageExport> exportPackage = bundle.getManifest().getExportPackage();
      assertThat(exportPackage.size(), Is.is(1));
   }

   @Test
   public void testMultiplePackageRoot()
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();

      BundleManifest mf = BundleManifestFactory.eINSTANCE.createBundleManifest();
      bundle.setManifest(mf);

      JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      jProject.getType("src/main/java", "foo", "Bar", true);
      jProject.getType("src/test/java", "foo", "BarTest", true);
      bundle.setContent(jProject);

      exportAppender.append(new LinkedPropertiesMap(), bundle);

      EList<PackageExport> exportPackage = mf.getExportPackage();
      assertThat(exportPackage.size(), Is.is(1));
      assertThat(exportPackage.get(0).getPackageNames().size(), Is.is(1));
      assertThat(exportPackage.get(0).getPackageNames().get(0), IsEqual.equalTo("foo"));
   }

   @Test
   public void testDefaultPackage()
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();

      BundleManifest mf = BundleManifestFactory.eINSTANCE.createBundleManifest();
      bundle.setManifest(mf);

      JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      jProject.getType("/", null, "Bar", true);
      bundle.setContent(jProject);

      exportAppender.append(new LinkedPropertiesMap(), bundle);

      assertNull(mf.getExportPackage());
   }

   @Test
   public void testFilterPlatformPackages()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "java.lang.Object", 47);
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);

      BundleManifest manifest = bundle.getManifest();

      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(new LinkedPropertiesMap(), bundle);
      assertEquals("java.lang;version=1.0.1,org.sourcepit;version=1.0.1", manifest.getHeaderValue(EXPORT_PACKAGE));
   }

   @Test
   public void testFallbackToBundleVersion()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");

      exportAppender.append(new LinkedPropertiesMap(), bundle);

      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE), IsEqual.equalTo("org.sourcepit;version=1.0.1"));
   }

   @Test
   public void testVersionFromPackageinfo()
   {
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

      exportAppender.append(new LinkedPropertiesMap(), bundle);

      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE),
         IsEqual.equalTo("org.sourcepit;version=1337,org.sourcepit.foo;version=1.0.1"));
   }

   @Test
   public void testDoNotEraseVersionOfPlatformFamilyPackages()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "javax.xml.stream.Foo", 47);
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);

      BundleManifest manifest = bundle.getManifest();

      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(new LinkedPropertiesMap(), bundle);
      assertEquals("javax.xml.stream;version=1.0.1,org.sourcepit;version=1.0.1", manifest.getHeaderValue(EXPORT_PACKAGE));
   }

   @Test
   public void testDoNotEraseVersionOfVendorPackages()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "sun.misc.Foo", 47); // vendor specific package of compatible EE
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(new LinkedPropertiesMap(), bundle);
      assertEquals("org.sourcepit;version=1.0.1,sun.misc;version=1.0.1", manifest.getHeaderValue(EXPORT_PACKAGE));
   }

   @Test
   public void testSortAlphabetically()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "z.Foo", 47);
      appendType(jArchive, "d.Foo", 47);
      appendType(jArchive, "a.Foo", 47);
      appendType(jArchive, "a2.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(new LinkedPropertiesMap(), bundle);
      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE),
         IsEqual.equalTo("a;version=1.0.1,a2;version=1.0.1,d;version=1.0.1,z;version=1.0.1"));
   }

   @Test
   public void testDoNotExportPackagesAlreadyExportedByDependency()
   {
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

      BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setTarget(bundleB);
      bundleA.getDependencies().add(reference);

      exportAppender.append(new LinkedPropertiesMap(), bundleB);
      exportAppender.append(new LinkedPropertiesMap(), bundleA);

      EList<PackageExport> exportPackage = manifestA.getExportPackage();
      assertEquals(1, exportPackage.size());
      assertEquals(1, exportPackage.get(0).getPackageNames().size());
      assertEquals("org.sourcepit.bundleA", exportPackage.get(0).getPackageNames().get(0));
   }

   @Test
   public void testInternal()
   {
      final PropertiesMap properties = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Type", 47);
      appendType(jArchive, "org.sourcepit.internal.Type", 47);
      appendType(jArchive, "org.sourcepit.internal.foo.Type", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1");

      exportAppender.append(properties, bundle);

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

      exportAppender.append(properties, bundle);

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
}
