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

import org.eclipse.emf.ecore.EObject;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.sourcepit.common.modeling.utils.EcoreUtils;
import org.sourcepit.common.modeling.utils.EcoreUtils.RunnableWithEObject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaTypeTest {
   @Test
   public void testGetResourceBundle() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            testGetResourceBundle((JavaResourceBundle) eObject);
         }
      });
   }

   private void testGetResourceBundle(JavaResourceBundle jBundle) {
      JavaType jType = jBundle.getType("/", "org.sourcepit", "Foo", true);
      assertThat(jType.getResourceBundle(), IsSame.sameInstance(jBundle));
   }

   @Test
   public void testName() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            testName((JavaResourceBundle) eObject);
         }
      });
   }

   private void testName(JavaResourceBundle jBundle) {
      JavaType jType = JavaModelFactory.eINSTANCE.createJavaType();
      assertThat(jType.getName(), IsNull.nullValue());

      jType.setName("Foo");
      assertThat(jType.getName(), IsEqual.equalTo("Foo"));
      assertThat(jType.getQualifiedName(), IsEqual.equalTo("Foo"));

      JavaFile jFile = jBundle.getType("/", "org.sourcepit", "Bar", true).getFile();
      jFile.setType(jType);

      assertThat(jFile.getName(), IsEqual.equalTo("Bar"));
      assertThat(jType.getName(), IsEqual.equalTo("Foo"));
      assertThat(jType.getQualifiedName(), IsEqual.equalTo("org.sourcepit.Foo"));

   }

}
