/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.inspect;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import javax.validation.ConstraintViolationException;

import org.eclipse.emf.common.util.EList;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
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
   public void testJar()
   {
      try
      {
         new JavaTypeAndPackageInvestigator().investigateJar(null, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      JavaArchive javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      try
      {
         new JavaTypeAndPackageInvestigator().investigateJar(javaArchive, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      File jarFile = new File("target/testResources/osgifyme-core.jar");
      assertTrue(jarFile.exists());

      javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      new JavaTypeAndPackageInvestigator().investigateJar(javaArchive, jarFile);

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
   }

   @Test
   public void testProject() throws Exception
   {
      try
      {
         new JavaTypeAndPackageInvestigator().investigateProject(null, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      JavaProject javaProject = JavaModelFactory.eINSTANCE.createJavaProject();
      try
      {
         new JavaTypeAndPackageInvestigator().investigateProject(javaProject, null);
         fail();
      }
      catch (ConstraintViolationException e)
      { // noop
      }

      File projectDir = new File("target/testResources");
      assertTrue(projectDir.exists());

      javaProject = JavaModelFactory.eINSTANCE.createJavaProject();

      new JavaTypeAndPackageInvestigator().investigateProject(javaProject, projectDir);

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
   }

}
