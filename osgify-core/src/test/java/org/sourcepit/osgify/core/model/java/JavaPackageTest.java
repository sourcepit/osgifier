/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.validation.ConstraintViolationException;

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
public class JavaPackageTest
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
      JavaPackage jPackage = jBundle.getPackage("/", "foo", true);
      assertThat(jPackage.getResourceBundle(), IsSame.sameInstance(jBundle));
   }

   @Test
   public void testGetResourceRoot()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaResourceBundle(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            testGetResourceRoot((JavaResourceBundle) eObject);
         }
      });
   }

   private void testGetResourceRoot(JavaResourceBundle jBundle)
   {
      JavaPackage jPackage = jBundle.getPackage("/", "foo", true);
      assertThat(jPackage.getResourcesRoot(), IsSame.sameInstance(jBundle.getResourcesRoot("/")));
   }

   @Test
   public void testNullArguments()
   {
      try
      {
         JavaPackage jPackage = JavaModelFactory.eINSTANCE.createJavaPackage();
         jPackage.getPackage(null, false);
         fail();
      }
      catch (ConstraintViolationException e)
      {
      }
   }

   @Test
   public void testGetPackage()
   {
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
