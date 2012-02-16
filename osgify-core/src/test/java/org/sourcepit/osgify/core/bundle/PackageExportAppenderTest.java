/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.EXPORT_PACKAGE;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.appendType;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.newBundleCandidate;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.eclipse.emf.common.util.EList;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sonatype.guice.bean.containers.InjectedTest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
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
         exportAppender.append(null);
         fail();
      }
      catch (ConstraintViolationException e)
      {
      }

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      try
      {
         exportAppender.append(bundle);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      try
      {
         exportAppender.append(bundle);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }
      JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      jProject.getType("src/main/java", "foo", "Bar", true);
      jProject.getType("src/test/java", "foo", "BarTest", true);
      bundle.setContent(jProject);

      exportAppender.append(bundle);

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

      exportAppender.append(bundle);

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

      exportAppender.append(bundle);

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

      exportAppender.append(bundle);

      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE), IsEqual.equalTo("org.sourcepit;version=1.0.1"));
   }

   @Test
   public void testFallbackToBundleVersion()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");

      exportAppender.append(bundle);

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

      exportAppender.append(bundle);

      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE),
         IsEqual.equalTo("org.sourcepit;version=1337,org.sourcepit.foo;version=1.0.1"));
   }

   @Test
   public void testEraseVersionOfPlatformFamilyPackages()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "javax.xml.stream.Foo", 47);
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);

      BundleManifest manifest = bundle.getManifest();

      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(bundle);

      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE),
         IsEqual.equalTo("javax.xml.stream,org.sourcepit;version=1.0.1"));
   }

   @Test
   public void testEraseVersionOfVendorPackages()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "sun.misc.Foo", 47); // vendor specific package of compatible EE
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleVersion("1.0.1");
      manifest.setBundleRequiredExecutionEnvironment("OSGi/Minimum-1.0");

      exportAppender.append(bundle);
      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE), IsEqual.equalTo("org.sourcepit;version=1.0.1,sun.misc"));
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

      exportAppender.append(bundle);
      assertThat(manifest.getHeaderValue(EXPORT_PACKAGE),
         IsEqual.equalTo("a;version=1.0.1,a2;version=1.0.1,d;version=1.0.1,z;version=1.0.1"));
   }
}
