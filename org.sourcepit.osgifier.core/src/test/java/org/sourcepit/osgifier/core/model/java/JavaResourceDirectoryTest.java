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

package org.sourcepit.osgifier.core.model.java;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.eclipse.emf.ecore.EObject;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.modeling.utils.EcoreUtils;
import org.sourcepit.common.modeling.utils.EcoreUtils.RunnableWithEObject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaResourceDirectoryTest {
   @Test
   public void testGetPackages() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceDirectory(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            testGetPackages((JavaResourceDirectory) eObject);
         }
      });
   }

   private void testGetPackages(JavaResourceDirectory jDir) {
      try {
         jDir.getPackages().add(JavaModelFactory.eINSTANCE.createJavaPackage());
         fail("expected unmodifiable EList");
      }
      catch (UnsupportedOperationException e) {
      }
   }

   @Test
   public void testGetJavaFiles() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceDirectory(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            testGetJavaFiles((JavaResourceDirectory) eObject);
         }
      });
   }

   private void testGetJavaFiles(JavaResourceDirectory jDir) {
      try {
         jDir.getJavaFiles().add(JavaModelFactory.eINSTANCE.createJavaClass());
         fail("expected unmodifiable EList");
      }
      catch (UnsupportedOperationException e) {
      }
   }

   @Test
   public void testGetPackage() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceDirectory(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            testGetPackage((JavaResourceDirectory) eObject);
         }
      });
   }

   private void testGetPackage(JavaResourceDirectory jDir) {
      assertThat(jDir.getPackage("org"), IsNull.nullValue());

      JavaPackage org = jDir.getPackage("org", true);
      assertThat(org, IsNull.notNullValue());
      assertThat(org.getParentDirectory(), Is.is(jDir));

      assertThat(jDir.getPackage("org"), Is.is(org));

      jDir.getPackage("org.sourcepit.foo", true);
      JavaPackage sourcepit = org.getPackage("sourcepit");
      assertThat(sourcepit, IsNull.notNullValue());
      JavaPackage foo = sourcepit.getPackage("foo");
      assertThat(foo, IsNull.notNullValue());

      assertThat(jDir.getPackage("org.sourcepit"), Is.is(sourcepit));
      assertThat(org.getPackage("sourcepit"), Is.is(sourcepit));
      assertThat(org.getParentDirectory(), Is.is(jDir));
      assertThat(sourcepit.getParentPackage(), Is.is(org));

      assertThat(jDir.getPackage("org.sourcepit.foo"), Is.is(foo));
      assertThat(org.getPackage("sourcepit.foo"), Is.is(foo));
      assertThat(foo.getParentPackage(), Is.is(sourcepit));
   }

   @Test
   public void testGetType() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceDirectory(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            if (eObject instanceof JavaPackage) {
               // see JavaPackage#getResourcesType()
               JavaModelFactory.eINSTANCE.createJavaResourcesRoot().getResources().add((Resource) eObject);
            }
            testGetType((JavaResourceDirectory) eObject);
         }
      });
   }

   protected void testGetType(JavaResourceDirectory jDir) {
      assertThat(jDir.getType("Foo"), IsNull.nullValue());

      JavaType foo = jDir.getType("Foo", true);
      assertThat(foo, IsNull.notNullValue());

      JavaType muh = jDir.getType("Foo.Muh", true);
      assertThat(muh, IsNull.notNullValue());
      assertThat(muh.getOuterType(), Is.is(foo));

      JavaType muh2 = jDir.getType("Foo.Muh", true);
      assertThat(muh2, Is.is(muh));

      JavaType muh3 = jDir.getType("Foo.Muh3", true);
      assertThat(muh3, IsNot.not(Is.is(muh)));
      assertThat(muh3.getOuterType(), Is.is(foo));

      JavaType bar = jDir.getType("Foo.Muh.Bar", true);
      assertThat(bar.getOuterType(), Is.is(muh));
   }

}
