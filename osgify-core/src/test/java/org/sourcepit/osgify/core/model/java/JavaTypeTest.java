/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import static org.junit.Assert.assertThat;

import org.eclipse.emf.ecore.EObject;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.sourcepit.modeling.common.utils.EcoreUtils;
import org.sourcepit.modeling.common.utils.EcoreUtils.RunnableWithEObject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaTypeTest
{
   @Test
   public void testGetResourceBundle()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testGetResourceBundle((JavaResourceBundle) eObject);
         }
      });
   }

   private void testGetResourceBundle(JavaResourceBundle jBundle)
   {
      JavaType jType = jBundle.getType("/", "org.sourcepit", "Foo", true);
      assertThat(jType.getResourceBundle(), IsSame.sameInstance(jBundle));
   }

   @Test
   public void testName()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testName((JavaResourceBundle) eObject);
         }
      });
   }

   private void testName(JavaResourceBundle jBundle)
   {
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
