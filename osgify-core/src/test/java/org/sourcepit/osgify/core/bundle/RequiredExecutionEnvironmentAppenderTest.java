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

import java.util.Set;

import javax.inject.Inject;

import org.eclipse.sisu.launch.InjectedTest;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaType;

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

}
