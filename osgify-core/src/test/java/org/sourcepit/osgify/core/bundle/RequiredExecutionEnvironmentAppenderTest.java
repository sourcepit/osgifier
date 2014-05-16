/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.appendType;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.appendTypeReference;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.newBundleCandidate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.sisu.launch.InjectedTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.ee.OsgiEE;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaType;

import com.google.common.base.Strings;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class RequiredExecutionEnvironmentAppenderTest extends InjectedTest
{
   @Inject
   private RequiredExecutionEnvironmentAppender execEnvAppender;

   @Test
   public void testGetExcludedExecutionEnvironments() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();

      Set<String> excludes = RequiredExecutionEnvironmentAppender.getExcludedExecutionEnvironments(options);
      assertTrue(excludes.isEmpty());

      options.put("osgifier.excludedExecutionEnvironments", "OSGi/Minimum-1.0, JavaSE/compact1-1.8");

      excludes = RequiredExecutionEnvironmentAppender.getExcludedExecutionEnvironments(options);
      assertEquals(2, excludes.size());
      assertTrue(excludes.contains("OSGi/Minimum-1.0"));
      assertTrue(excludes.contains("JavaSE/compact1-1.8"));
   }

   public void testToFilterString() throws Exception
   {
      OsgiEE foo = new OsgiEE("Foo", null);
      assertEquals("(osgi.ee=Foo)", toFilterString(Arrays.asList(foo)));

      OsgiEE java6 = new OsgiEE("JavaSE", "1.6");
      OsgiEE java7 = new OsgiEE("JavaSE", "1.7");
      OsgiEE compact1 = new OsgiEE("JavaSE/compact1", "1.8");

      assertEquals("(&(osgi.ee=JavaSE)(version>=1.6))", toFilterString(Arrays.asList(java6)));
      assertEquals("(| (&(osgi.ee=JavaSE)(version>=1.6)) (&(osgi.ee=JavaSE)(version>=1.7)) )",
         toFilterString(Arrays.asList(java6, java7)));
      assertEquals(
         "(| (&(osgi.ee=JavaSE)(version>=1.6)) (&(osgi.ee=JavaSE)(version>=1.7)) (&(osgi.ee=JavaSE/compact1)(version>=1.8)) )",
         toFilterString(Arrays.asList(java6, java7, compact1)));
   }

   private static String toFilterString(Collection<OsgiEE> ees)
   {
      final StringBuilder sb = new StringBuilder();
      if (ees.size() > 1)
      {
         sb.append("(| ");
      }
      for (OsgiEE ee : ees)
      {
         final String version = ee.getVersion();
         final boolean hasVersion = !Strings.isNullOrEmpty(version);
         if (hasVersion)
         {
            sb.append("(&");
         }
         sb.append("(osgi.ee=");
         sb.append(ee.getName());
         if (hasVersion)
         {
            sb.append(")(version>=");
            sb.append(version);
            sb.append(")");
         }
         sb.append(") ");
      }
      sb.deleteCharAt(sb.length() - 1);
      if (ees.size() > 1)
      {
         sb.append(" )");
      }
      return sb.toString();
   }

   @Test
   public void test()
   {
      PropertiesSource options = new LinkedPropertiesMap();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      BundleManifest manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("J2SE-1.3, JavaSE/compact1-1.8"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 46);

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("OSGi/Minimum-1.1"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 51);

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("JavaSE-1.7, JavaSE/compact1-1.8"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      JavaType jType = appendType(jArchive, "org.sourcepit.Foo", 47);
      appendTypeReference(jType, "java.nio.file.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("JavaSE-1.7, JavaSE/compact1-1.8"));

      // no perfect match
      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 51);
      appendTypeReference(jType, "javax.microedition.io.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertNull(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 48);
      appendTypeReference(jType, "java.lang.Object");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("J2SE-1.4, JavaSE/compact1-1.8"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 47);
      appendTypeReference(jType, "java.text.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("J2SE-1.3, JavaSE/compact1-1.8"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 49);
      appendTypeReference(jType, "java.lang.Object");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("J2SE-1.5, JavaSE/compact1-1.8"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 45);
      appendTypeReference(jType, "java.lang.Object");
      appendTypeReference(jType, "java.applet.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("JRE-1.1"));
   }
}
