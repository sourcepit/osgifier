/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.assertThat;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.appendType;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.appendTypeReference;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.newBundleCandidate;

import javax.inject.Inject;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sonatype.guice.bean.containers.InjectedTest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
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
   public void test()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 47);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      BundleManifest manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("OSGi/Minimum-1.0"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 48);

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("J2SE-1.4, CDC-1.1/Foundation-1.1"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "org.sourcepit.Foo", 51);

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("JavaSE-1.7"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      JavaType jType = appendType(jArchive, "org.sourcepit.Foo", 47);
      appendTypeReference(jType, "java.nio.file.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("JavaSE-1.7"));

      // no perfect match
      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 51);
      appendTypeReference(jType, "javax.microedition.io.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("CDC-1.1/Foundation-1.1"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 48);
      appendTypeReference(jType, "java.lang.Object");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT),
         IsEqual.equalTo("J2SE-1.4, CDC-1.1/Foundation-1.1"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 47);
      appendTypeReference(jType, "java.text.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("OSGi/Minimum-1.2"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 49);
      appendTypeReference(jType, "java.lang.Object");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("J2SE-1.5"));

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jType = appendType(jArchive, "org.sourcepit.Foo", 45);
      appendTypeReference(jType, "java.lang.Object");
      appendTypeReference(jType, "java.applet.Foo");

      bundle = newBundleCandidate(jArchive);
      execEnvAppender.append(bundle);

      manifest = bundle.getManifest();
      assertThat(manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT), IsEqual.equalTo("J2SE-1.3"));
   }

}
