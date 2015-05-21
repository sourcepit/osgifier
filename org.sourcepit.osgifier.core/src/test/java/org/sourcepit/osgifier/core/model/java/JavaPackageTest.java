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
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.sourcepit.common.modeling.utils.EcoreUtils;
import org.sourcepit.common.modeling.utils.EcoreUtils.RunnableWithEObject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaPackageTest {
   @Test
   public void testGetResourceBundle() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            testGetResourceBundle((JavaResourceBundle) eObject);
         }
      });
   }

   private void testGetResourceBundle(JavaResourceBundle jBundle) {
      JavaPackage jPackage = jBundle.getPackage("/", "foo", true);
      assertThat(jPackage.getResourceBundle(), IsSame.sameInstance(jBundle));
   }

   @Test
   public void testGetResourceRoot() {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject() {
         public void run(EObject eObject) {
            testGetResourceRoot((JavaResourceBundle) eObject);
         }
      });
   }

   private void testGetResourceRoot(JavaResourceBundle jBundle) {
      JavaPackage jPackage = jBundle.getPackage("/", "foo", true);
      assertThat(jPackage.getResourcesRoot(), IsSame.sameInstance(jBundle.getResourcesRoot("/")));
   }

   @Test
   public void testNullArguments() {
      try {
         JavaPackage jPackage = JavaModelFactory.eINSTANCE.createJavaPackage();
         jPackage.getPackage(null, false);
         fail();
      }
      catch (IllegalArgumentException e) {
      }
   }

   @Test
   public void testGetPackage() {
      JavaPackage jPackage = JavaModelFactory.eINSTANCE.createJavaPackage();
      assertThat(jPackage.getPackages(), IsNull.notNullValue());
      assertThat(jPackage.getPackages().size(), Is.is(0));
      assertThat(jPackage.getQualifiedName(), IsNull.nullValue());

      jPackage.setName("org");
      assertThat(jPackage.getQualifiedName(), IsEqual.equalTo("org"));
      assertThat(jPackage.getName(), IsEqual.equalTo("org"));

      assertThat(jPackage.getPackage("sourcepit", false), IsNull.nullValue());

      JavaPackage subJPackage = jPackage.getPackage("sourcepit", true);
      assertThat(jPackage.getPackages().size(), Is.is(1));
      assertThat(subJPackage, IsNull.notNullValue());
      assertThat(subJPackage.getQualifiedName(), IsEqual.equalTo("org.sourcepit"));
      assertThat(subJPackage.getName(), IsEqual.equalTo("sourcepit"));
      assertThat((JavaPackage) subJPackage.getParentDirectory(), Is.is(jPackage));
      assertThat(subJPackage.getParentPackage(), Is.is(jPackage));

      JavaPackage subSubPackage = subJPackage.getPackage("foo", true);
      assertThat(subSubPackage.getParentPackage(), Is.is(subJPackage));
   }
}
