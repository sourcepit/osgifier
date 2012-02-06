/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.inspect;

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
import org.sourcepit.osgify.core.model.java.Directory;
import org.sourcepit.osgify.core.model.java.File;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaProject;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;

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
