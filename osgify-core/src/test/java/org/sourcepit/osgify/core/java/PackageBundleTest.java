/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.eclipse.emf.ecore.EObject;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.modeling.common.utils.EcoreUtils;
import org.sourcepit.modeling.common.utils.EcoreUtils.RunnableWithEObject;
import org.sourcepit.osgify.core.model.java.JavaModelPackage;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaPackageBundle;

public class PackageBundleTest
{
   @Test
   public void test()
   {
      EcoreUtils.foreachSupertype(JavaModelPackage.eINSTANCE.getJavaPackageBundle(), new RunnableWithEObject()
      {
         public void run(EObject eObject)
         {
            JavaPackageBundle bundle = (JavaPackageBundle) eObject;
            try
            {
               bundle.getPackage(null, null, false);
               fail();
            }
            catch (IllegalArgumentException e)
            { // noop
            }

            try
            {
               bundle.getPackage("bin", null, false);
               fail();
            }
            catch (IllegalArgumentException e)
            { // noop
            }

            assertThat(bundle.getPackage("bin", "", false), IsNull.nullValue());

            JavaPackage segment = bundle.getPackage("bin", "org.sourcepit", true);
            assertThat(segment, IsEqual.equalTo(bundle.getPackage("bin", segment.getFullyQualifiedName(), false)));
            assertThat(segment.getSimpleName(), IsEqual.equalTo("sourcepit"));
            assertThat(segment.getFullyQualifiedName(), IsEqual.equalTo("org.sourcepit"));

            segment = segment.getParentPackage();
            assertThat(segment.getSimpleName(), IsEqual.equalTo("org"));

            segment = segment.getParentPackage();
            assertThat(segment, IsNull.nullValue());
         }
      });
   }
}
