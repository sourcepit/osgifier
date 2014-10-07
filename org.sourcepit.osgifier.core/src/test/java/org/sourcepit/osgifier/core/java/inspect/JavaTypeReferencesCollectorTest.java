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

package org.sourcepit.osgifier.core.java.inspect;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;
import java.lang.IllegalArgumentException;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.osgifier.core.java.inspect.JavaTypeReferencesCollector;
import org.sourcepit.osgifier.test.resources.TypeA;

/**
 * @author Bernd
 */
public class JavaTypeReferencesCollectorTest
{
   @Test
   public void test() throws Exception
   {
      try
      {
         JavaTypeReferencesCollector.collect(null);
         fail();
      }
      catch (IllegalArgumentException e)
      { // expected
      }

      final File jarFile = new File("target/testResources/org.sourcepit.osgifier.core.jar");
      assertTrue(jarFile.exists());

      final ClassLoaderRepository classRepo = new ClassLoaderRepository(new URLClassLoader(new URL[] { jarFile.toURI()
         .toURL() }));

      JavaClass jClass = classRepo.loadClass(TypeA.class.getName());

      Set<String> typeRefs = JavaTypeReferencesCollector.collect(jClass);
      assertThat(typeRefs.size(), Is.is(3));

      Set<String> expectedRefs = new HashSet<String>();
      expectedRefs.add("java.lang.Object");
      expectedRefs.add("java.lang.String");
      expectedRefs.add("java.lang.Boolean");

      assertThat(typeRefs, IsEqual.equalTo(expectedRefs));

      jClass = classRepo.loadClass(TypeA.Hans.class.getName());

      typeRefs = JavaTypeReferencesCollector.collect(jClass);
      assertThat(typeRefs.size(), Is.is(11));

      expectedRefs = new HashSet<String>();

      expectedRefs.add("java.util.Map");
      expectedRefs.add("java.lang.Short");
      expectedRefs.add("java.lang.Boolean");
      expectedRefs.add("org.hamcrest.Matcher");
      expectedRefs.add("org.hamcrest.BaseMatcher");
      expectedRefs.add("java.lang.Object");
      expectedRefs.add("java.lang.String");
      expectedRefs.add("java.lang.Long");
      expectedRefs.add("java.util.HashMap");
      expectedRefs.add("java.lang.Runnable");
      expectedRefs.add("java.lang.Integer");

      assertThat(typeRefs, IsEqual.equalTo(expectedRefs));
   }

   @Test
   public void testSignatureOfInvokedMethod() throws Exception
   {
      final ClassLoaderRepository classRepo = new ClassLoaderRepository(
         JavaTypeReferencesCollectorTest_testSignatureOfInvokedMethod.class.getClassLoader());

      JavaClass jClass = classRepo.loadClass(JavaTypeReferencesCollectorTest_testSignatureOfInvokedMethod.class
         .getName());

      final Set<String> typeRefs = JavaTypeReferencesCollector.collect(jClass);
      assertThat(typeRefs.size(), Is.is(4));

      final Set<String> expectedRefs = new HashSet<String>();

      expectedRefs.add("java.lang.Object");
      expectedRefs
         .add("org.sourcepit.osgifier.core.java.inspect.JavaTypeReferencesCollectorTest_testSignatureOfInvokedMethod_Dummy");
      expectedRefs.add("java.lang.Integer");
      expectedRefs.add("java.lang.String");

      assertThat(typeRefs, IsEqual.equalTo(expectedRefs));
   }

   @Test
   public void testFullyQualfiedMethodSignatureTypes() throws Exception
   {
      final ClassLoaderRepository classRepo = new ClassLoaderRepository(
         JavaTypeReferencesCollectorTest_testSignatureOfInvokedMethod.class.getClassLoader());

      JavaClass jClass = classRepo
         .loadClass(JavaTypeReferencesCollectorTest_testFullyQualfiedMethodSignatureTypes.class.getName());

      final Set<String> typeRefs = JavaTypeReferencesCollector.collect(jClass);
      assertThat(typeRefs.size(), Is.is(3));

      final Set<String> expectedRefs = new HashSet<String>();
      expectedRefs.add("java.lang.Object");
      expectedRefs.add("javax.activation.CommandObject");
      expectedRefs.add("javax.activation.DataContentHandler");

      assertThat(typeRefs, IsEqual.equalTo(expectedRefs));
   }

   @Test
   public void testMethodAnnotations() throws Exception
   {
      final ClassLoaderRepository classRepo = new ClassLoaderRepository(
         JavaTypeReferencesCollectorTest_testSignatureOfInvokedMethod.class.getClassLoader());

      JavaClass jClass = classRepo.loadClass(JavaTypeReferencesCollectorTest_testMethodAnnotations.class.getName());

      final Set<String> typeRefs = JavaTypeReferencesCollector.collect(jClass);
      assertThat(typeRefs.size(), Is.is(3));

      final Set<String> expectedRefs = new HashSet<String>();
      expectedRefs.add("java.lang.Object");
      expectedRefs.add("javax.inject.Inject");
      expectedRefs.add("javax.inject.Named");

      assertThat(typeRefs, IsEqual.equalTo(expectedRefs));
   }
}
