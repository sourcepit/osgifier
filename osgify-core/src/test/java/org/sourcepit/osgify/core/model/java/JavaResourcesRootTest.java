/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaResourcesRootTest
{
   @Test
   public void testResourcesTypeDefault()
   {
      JavaResourcesRoot jResources = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      assertThat(jResources.getResourcesType(), Is.is(JavaResourcesType.BIN));
   }

   @Test
   public void testResourcesType()
   {
      JavaResourcesRoot jResources = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      jResources.setResourcesType(JavaResourcesType.BIN);
      assertThat(jResources.getJavaFile("Foo", true), IsInstanceOf.instanceOf(JavaClass.class));
      assertThat(jResources.getType("Bar", true).getFile(), IsInstanceOf.instanceOf(JavaClass.class));

      jResources = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      jResources.setResourcesType(JavaResourcesType.SRC);
      assertThat(jResources.getJavaFile("Foo", true), IsInstanceOf.instanceOf(JavaCompilationUnit.class));
      assertThat(jResources.getType("Bar", true).getFile(), IsInstanceOf.instanceOf(JavaCompilationUnit.class));
   }

}
