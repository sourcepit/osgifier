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

import static org.junit.Assert.*;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.DYNAMICIMPORT_PACKAGE;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.addBundleReference;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.appendType;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newBundleCandidate;
import static org.sourcepit.osgifier.core.bundle.TestContextHelper.newJArchive;

import javax.inject.Inject;

import org.eclipse.sisu.launch.InjectedTest;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.osgifier.core.bundle.DynamicPackageImportAppender;
import org.sourcepit.osgifier.core.java.inspect.ClassForNameDetector;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.EmbedInstruction;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class DynamicPackageImportAppenderTest extends InjectedTest
{
   @Inject
   private DynamicPackageImportAppender dynamicImportAppender;

   @Test
   public void testPositive()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "foo.Bar", 47).getAnnotation(ClassForNameDetector.SOURCE, true).setData(
         ClassForNameDetector.CLASS_FOR_NAME, true);

      BundleCandidate bundle = newBundleCandidate(jArchive);

      dynamicImportAppender.append(bundle);

      BundleManifest manifest = bundle.getManifest();
      String dynamicImportPackage = manifest.getHeaderValue(DYNAMICIMPORT_PACKAGE);

      assertThat(dynamicImportPackage, IsEqual.equalTo("*"));
   }

   @Test
   public void testAlreadySet()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "foo.Bar", 47).getAnnotation(ClassForNameDetector.SOURCE, true).setData(
         ClassForNameDetector.CLASS_FOR_NAME, true);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setHeader(DYNAMICIMPORT_PACKAGE, "*");

      dynamicImportAppender.append(bundle);

      String dynamicImportPackage = manifest.getHeaderValue(DYNAMICIMPORT_PACKAGE);

      assertThat(dynamicImportPackage, IsEqual.equalTo("*"));
   }

   @Test
   public void testIsAlreadySettoSthElse()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "foo.Bar", 47).getAnnotation(ClassForNameDetector.SOURCE, true).setData(
         ClassForNameDetector.CLASS_FOR_NAME, true);

      BundleCandidate bundle = newBundleCandidate(jArchive);
      BundleManifest manifest = bundle.getManifest();
      manifest.setHeader(DYNAMICIMPORT_PACKAGE, "javax.*");

      dynamicImportAppender.append(bundle);

      String dynamicImportPackage = manifest.getHeaderValue(DYNAMICIMPORT_PACKAGE);

      assertThat(dynamicImportPackage, IsEqual.equalTo("*"));
   }

   @Test
   public void testNegative()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendType(jArchive, "foo.Bar", 47).getAnnotation(ClassForNameDetector.SOURCE, true).setData(
         ClassForNameDetector.CLASS_FOR_NAME, false);

      BundleCandidate bundle = newBundleCandidate(jArchive);

      dynamicImportAppender.append(bundle);

      BundleManifest manifest = bundle.getManifest();
      String dynamicImportPackage = manifest.getHeaderValue(DYNAMICIMPORT_PACKAGE);

      assertThat(dynamicImportPackage, IsNull.nullValue());
   }

   @Test
   public void testCombineDynamicImportsFromEmbedded() throws Exception
   {
      BundleCandidate a = newBundleCandidate("1", newJArchive("a.A"));
      a.getManifest().setHeader(DYNAMICIMPORT_PACKAGE, "foo.*");

      BundleCandidate b = newBundleCandidate("1", newJArchive("b.B"));
      b.getManifest().setHeader(DYNAMICIMPORT_PACKAGE, "bar.*");

      BundleCandidate c = newBundleCandidate("1", newJArchive("c.C"));
      c.getManifest().setHeader(DYNAMICIMPORT_PACKAGE, "acme.*");

      BundleCandidate d = newBundleCandidate("1", newJArchive("d.D"));
      addBundleReference(d, a).setEmbedInstruction(EmbedInstruction.UNPACKED);
      addBundleReference(d, b).setEmbedInstruction(EmbedInstruction.UNPACKED);
      addBundleReference(d, c);

      dynamicImportAppender.append(d);

      assertEquals("bar.*;foo.*", d.getManifest().getHeaderValue(DYNAMICIMPORT_PACKAGE));
   }

}
