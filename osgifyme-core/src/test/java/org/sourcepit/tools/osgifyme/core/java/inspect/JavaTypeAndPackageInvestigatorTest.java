/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.inspect;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.osgifyme.core.java.JavaArchive;
import org.sourcepit.osgifyme.core.java.JavaModelFactory;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaProject;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.tools.osgifyme.core.utils.AbstractTraverserTest;
import org.sourcepit.tools.osgifyme.test.resources.TypeA;

/**
 * @author Bernd
 */
public class JavaTypeAndPackageInvestigatorTest
{

   @Test
   public void testJar() throws Exception
   {
      try
      {
         new JavaTypeAndPackageInvestigator().investigateJar(null, null, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      JavaArchive javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      try
      {
         new JavaTypeAndPackageInvestigator().investigateJar(javaArchive, null, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      File jarFile = new File("target/testResources/osgifyme-core.jar");
      assertTrue(jarFile.exists());

      javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      new JavaTypeAndPackageInvestigator().investigateJar(javaArchive, jarFile, null);

      JavaPackage pgk = javaArchive.getPackage(AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH, false);
      assertThat(pgk, IsNull.notNullValue());

      String version = pgk.getAnnotationData("packageinfo", "version");
      assertThat(version, IsEqual.equalTo("1.0"));

      JavaType type = javaArchive.getType(AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH,
         TypeA.class.getSimpleName(), false);
      assertThat(type, IsNull.notNullValue());

      EList<JavaType> innerTypes = type.getInnerTypes();
      assertThat(innerTypes.size(), Is.is(1));

      JavaType innerType = innerTypes.get(0);
      assertThat(innerType.getSimpleName(), IsEqual.equalTo(TypeA.Hans.class.getSimpleName()));
      assertThat(innerType.getOuterType(), IsEqual.equalTo(type));

      JavaType innerType2 = javaArchive.getType(AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH,
         TypeA.class.getSimpleName() + "." + TypeA.Hans.class.getSimpleName(), false);
      assertThat(innerType, IsEqual.equalTo(innerType2));

      print(javaArchive);
   }

   @Test
   public void testProject() throws Exception
   {
      try
      {
         new JavaTypeAndPackageInvestigator().investigateProject(null, null, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      JavaProject javaProject = JavaModelFactory.eINSTANCE.createJavaProject();
      try
      {
         new JavaTypeAndPackageInvestigator().investigateProject(javaProject, null, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      File projectDir = new File("target/testResources");
      assertTrue(projectDir.exists());

      javaProject = JavaModelFactory.eINSTANCE.createJavaProject();

      new JavaTypeAndPackageInvestigator().investigateProject(javaProject, projectDir, null);

      JavaPackage pgk = javaProject.getPackage("", AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH, false);
      assertThat(pgk, IsNull.notNullValue());

      String version = pgk.getAnnotationData("packageinfo", "version");
      assertThat(version, IsEqual.equalTo("1.0"));

      JavaType type = javaProject.getType("", AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH,
         TypeA.class.getSimpleName(), false);
      assertThat(type, IsNull.notNullValue());

      EList<JavaType> innerTypes = type.getInnerTypes();
      assertThat(innerTypes.size(), Is.is(1));

      JavaType innerType = innerTypes.get(0);
      assertThat(innerType.getSimpleName(), IsEqual.equalTo(TypeA.Hans.class.getSimpleName()));
      assertThat(innerType.getOuterType(), IsEqual.equalTo(type));

      JavaType innerType2 = javaProject.getType("", AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH,
         TypeA.class.getSimpleName() + "." + TypeA.Hans.class.getSimpleName(), false);
      assertThat(innerType, IsEqual.equalTo(innerType2));

      print(javaProject);
   }

   protected void print(EObject eObject) throws IOException, UnsupportedEncodingException
   {
      XMLResourceImpl resource = new XMLResourceImpl();
      resource.getContents().add(eObject);
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      resource.save(out, null);
      System.out.println(new String(out.toByteArray(), "UTF-8"));
   }

   @Test
   public void testWithReferenceCollector()
   {
      File projectDir = new File("target/testResources");
      assertTrue(projectDir.exists());

      JavaProject javaProject = JavaModelFactory.eINSTANCE.createJavaProject();

      new JavaTypeAndPackageInvestigator()
         .investigateProject(javaProject, projectDir, new JavaTypeReferencesAnalyzer());

      JavaType jType = javaProject.getType("", AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH,
         TypeA.class.getSimpleName(), false);

      Annotation typeRefs = jType.getAnnotation("referencedTypes");

      Set<String> expectedRefs = new HashSet<String>();
      expectedRefs.add("java.lang.Object");
      expectedRefs.add("java.lang.String");
      expectedRefs.add("java.lang.Boolean");

      assertThat(typeRefs.getReferences().keySet(), IsEqual.equalTo(expectedRefs));

      jType = javaProject.getType("", AbstractTraverserTest.TEST_RESOURCES_PACKAGE_PATH, "TypeA.Hans", false);

      typeRefs = jType.getAnnotation("referencedTypes");

      expectedRefs = new HashSet<String>();
      expectedRefs.add("java.lang.Object");
      expectedRefs.add("java.lang.Runnable");

      assertThat(typeRefs.getReferences().keySet(), IsEqual.equalTo(expectedRefs));
   }

}
