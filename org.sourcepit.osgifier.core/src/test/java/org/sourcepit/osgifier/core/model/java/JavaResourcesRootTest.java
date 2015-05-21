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

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.junit.Test;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaResourcesRootTest {
   @Test
   public void testResourcesTypeDefault() {
      JavaResourcesRoot jResources = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      assertThat(jResources.getResourcesType(), Is.is(JavaResourcesType.BIN));
   }

   @Test
   public void testResourcesType() {
      JavaResourcesRoot jResources = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      jResources.setResourcesType(JavaResourcesType.BIN);
      assertThat(jResources.getJavaFile("Foo", true), IsInstanceOf.instanceOf(JavaClass.class));
      assertThat(jResources.getType("Bar", true).getFile(), IsInstanceOf.instanceOf(JavaClass.class));

      jResources = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      jResources.setResourcesType(JavaResourcesType.SRC);
      assertThat(jResources.getJavaFile("Foo", true), IsInstanceOf.instanceOf(JavaCompilationUnit.class));
      assertThat(jResources.getType("Bar", true).getFile(), IsInstanceOf.instanceOf(JavaCompilationUnit.class));
   }

   @Test
   public void testGetType() {
      JavaResourcesRoot jRoot = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      try {
         jRoot.getType(null, null, false);
         fail();
      }
      catch (IllegalArgumentException e) {
      }

      try {
         jRoot.getType("foo", null, false);
         fail();
      }
      catch (IllegalArgumentException e) {
      }

      assertThat(jRoot.getType(null, "Foo", false), IsNull.nullValue());

      JavaType jType = jRoot.getType(null, "Foo", true);
      assertThat(jType, IsNull.notNullValue());

      JavaFile typeRoot = jType.getFile();
      assertThat(typeRoot, IsNull.notNullValue());
      assertThat(typeRoot.getName(), IsEqual.equalTo("Foo"));

      assertThat((JavaResourcesRoot) typeRoot.getParentDirectory(), IsSame.sameInstance(jRoot));
   }

}
