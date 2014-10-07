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

package org.sourcepit.osgifier.core.inspect;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.eclipse.emf.ecore.resource.Resource;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.ManifestFactory;
import org.sourcepit.common.manifest.resource.ManifestResourceImpl;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgifier.core.inspect.DefaultFileHandler;
import org.sourcepit.osgifier.core.inspect.JavaResourceVisitor;
import org.sourcepit.osgifier.core.inspect.ManifestHandler;
import org.sourcepit.osgifier.core.model.java.Directory;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaProject;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;

public class JavaResourceVisitorTest
{

   @Test
   public void testVisitManifestMF() throws Exception
   {
      final JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();

      JavaResourceVisitor visitor = new JavaResourceVisitor(jProject, "", new ReentrantReadWriteLock());
      visitor.getResourceHandlers().add(new ManifestHandler());

      Manifest expectedMF = ManifestFactory.eINSTANCE.createManifest();
      expectedMF.setHeader("foo", "bar");

      Resource resource = new ManifestResourceImpl();
      resource.getContents().add(expectedMF);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      resource.save(baos, null);
      baos.close();

      visitor.visit(new Path("META-INF/MANIFEST.MF"), false, new ByteArrayInputStream(baos.toByteArray()));

      JavaResourcesRoot resourcesRoot = jProject.getResourcesRoot("", true);

      Directory dir = resourcesRoot.getDirectory("META-INF");
      assertThat(dir, IsNull.notNullValue());

      File mfFile = dir.getFile("MANIFEST.MF");
      assertThat(mfFile, IsNull.notNullValue());

      Manifest actualMF = mfFile.getExtension(Manifest.class);
      assertNotNull(actualMF);
      assertThat("bar", IsEqual.equalTo(actualMF.getHeaderValue("foo")));
   }

   @Test
   public void testVisitFile()
   {
      final JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();
      JavaResourceVisitor visitor = new JavaResourceVisitor(jProject, "", new ReentrantReadWriteLock());
      visitor.getResourceHandlers().add(new DefaultFileHandler());
      visitor.visit(new Path("META-INF/murks.txt"), false, new ByteArrayInputStream(new byte[0]));

      JavaResourcesRoot jRoot = jProject.getResourcesRoots().get(0);
      Directory dir = jRoot.getDirectory("META-INF");
      assertThat(dir, IsNot.not(IsInstanceOf.instanceOf(JavaResourceDirectory.class)));

      File file = dir.getFile("murks.txt");
      assertThat(file, IsNull.notNullValue());
      assertThat(file.getName(), IsEqual.equalTo("murks.txt"));
   }

}
