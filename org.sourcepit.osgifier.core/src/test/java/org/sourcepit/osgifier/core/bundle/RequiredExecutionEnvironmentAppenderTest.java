/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.EXPORT_PACKAGE;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.appendType;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.appendTypeReference;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newBundleCandidate;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.sisu.launch.InjectedTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.core.bundle.RequiredExecutionEnvironmentAppender;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaType;

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

   @Test
   public void test()
   {
      PropertiesMap options = new LinkedPropertiesMap();

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

   @Test
   public void testBeAwareOfPackagesFromDependencies() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();


      final BundleCandidate activation;
      {
         JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
         appendType(jArchive, "javax.activation.MimeType", 48);
         activation = newBundleCandidate(jArchive);
         activation.getManifest().setHeader(EXPORT_PACKAGE, "javax.activation");
      }

      final BundleCandidate mail;
      {
         JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
         JavaType jType = appendType(jArchive, "javax.mail.Service", 48);
         appendTypeReference(jType, "javax.activation.MimeType");
         mail = newBundleCandidate(jArchive);
      }

      execEnvAppender.append(mail, options);

      BundleManifest manifest = mail.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("JavaSE-1.6"));

      BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setTarget(activation);
      mail.getDependencies().add(ref);

      manifest.setHeader(BUNDLE_REQUIREDEXECUTIONENVIRONMENT, null);

      execEnvAppender.append(mail, options);

      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("J2SE-1.4, JavaSE/compact1-1.8"));
   }

   @Test
   public void testGetMappedEEIds() throws Exception
   {
      PropertiesMap options = new LinkedPropertiesMap();
      List<String> mappedEEs;

      mappedEEs = RequiredExecutionEnvironmentAppender.getMappedEEIds(options, "foo", Version.parse("1.0.0"));
      assertEquals("[]", mappedEEs.toString());

      options.put("osgifier.executionEnvironmentMappings", "foo=JavaSE-1.6");

      mappedEEs = RequiredExecutionEnvironmentAppender.getMappedEEIds(options, "foo", Version.parse("1.0.0"));
      assertEquals("[JavaSE-1.6]", mappedEEs.toString());

      options.put("osgifier.executionEnvironmentMappings", "foo_1.0.0=JavaSE-1.5");

      mappedEEs = RequiredExecutionEnvironmentAppender.getMappedEEIds(options, "foo", Version.parse("1.0.0"));
      assertEquals("[JavaSE-1.5]", mappedEEs.toString());

      options.put("osgifier.executionEnvironmentMappings", "foo_1.0.0 = J2SE-1.4| | | | JavaSE/compact1-1.8");

      mappedEEs = RequiredExecutionEnvironmentAppender.getMappedEEIds(options, "foo", Version.parse("1.0.0"));
      assertEquals("[J2SE-1.4, JavaSE/compact1-1.8]", mappedEEs.toString());

      options.put("osgifier.executionEnvironmentMappings",
         "foo_1.0.0 = J2SE-1.4| | | | JavaSE/compact1-1.8, bar = J2SE-1.3");

      mappedEEs = RequiredExecutionEnvironmentAppender.getMappedEEIds(options, "foo", Version.parse("1.0.0"));
      assertEquals("[J2SE-1.4, JavaSE/compact1-1.8]", mappedEEs.toString());

      mappedEEs = RequiredExecutionEnvironmentAppender.getMappedEEIds(options, "bar", Version.parse("1.0.0"));
      assertEquals("[J2SE-1.3]", mappedEEs.toString());
   }

   @Test
   public void testExecutionEnvironmentMappings() throws Exception
   {

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      bundle.setSymbolicName("foo");
      bundle.setVersion(Version.parse("1"));

      // without mapping
      PropertiesMap options = new LinkedPropertiesMap();
      execEnvAppender.append(bundle, options);
      BundleManifest manifest = bundle.getManifest();
      assertEquals("J2SE-1.3, JavaSE/compact1-1.8", manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT));

      // with mapping
      manifest.setHeader(BUNDLE_REQUIREDEXECUTIONENVIRONMENT, null);

      options.put("osgifier.executionEnvironmentMappings", "foo = OSGi/Minimum-1.0 | J2SE-1.3");

      execEnvAppender.append(bundle, options);

      manifest = bundle.getManifest();
      assertEquals("OSGi/Minimum-1.0, J2SE-1.3", manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT));
   }
}
