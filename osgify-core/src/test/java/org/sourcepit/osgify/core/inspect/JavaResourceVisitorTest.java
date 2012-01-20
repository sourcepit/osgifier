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
import java.io.InputStream;

import org.eclipse.emf.ecore.resource.Resource;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.ManifestFactory;
import org.sourcepit.common.manifest.resource.ManifestResourceImpl;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaPackageRoot;
import org.sourcepit.osgify.core.model.java.JavaProject;
import org.sourcepit.osgify.core.model.java.JavaType;

public class JavaResourceVisitorTest
{

   @Test
   public void testVisitManifestMF() throws Exception
   {
      final JavaProject jProject = JavaModelFactory.eINSTANCE.createJavaProject();

      JavaResourceVisitor visitor = new JavaResourceVisitor()
      {
         @Override
         protected void visitJType(JavaType javaType, InputStream content)
         {
         }

         @Override
         protected JavaPackageRoot getPackageRoot(boolean createOnDemand)
         {
            return jProject.getPackageRoot("", createOnDemand);
         }

         @Override
         protected JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand)
         {
            return null;
         }

         @Override
         protected JavaType getType(String packageName, String typeName, boolean createOnDemand)
         {
            return null;
         }
      };

      Manifest expectedMF = ManifestFactory.eINSTANCE.createManifest();
      expectedMF.setHeader("foo", "bar");

      Resource resource = new ManifestResourceImpl();
      resource.getContents().add(expectedMF);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      resource.save(baos, null);
      baos.close();

      visitor.visit(new Path("META-INF/MANIFEST.MF"), false, new ByteArrayInputStream(baos.toByteArray()));

      Manifest actualMF = jProject.getPackageRoot("", true).getExtension(Manifest.class);
      assertNotNull(actualMF);
      assertThat("bar", IsEqual.equalTo(actualMF.getHeaderValue("foo")));
   }

}
