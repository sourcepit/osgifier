/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;

/**
 * @author Bernd
 */
public class InspectJavaPackageNamesTest
{

   @Test
   public void test()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      String name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate);
      assertThat(name, IsNull.nullValue());

      jArchive.getType("org.sourcepit.foo.internal", "BarImpl", true);

      name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate);
      assertThat(name, IsEqual.equalTo("org.sourcepit.foo.internal"));

      jArchive.getType("org.sourcepit.foo", "Bar", true);

      name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate);
      assertThat(name, IsEqual.equalTo("org.sourcepit.foo"));

      jArchive.getType("org.lulu", "Util", true);

      name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate);
      assertThat(name, IsEqual.equalTo("org.sourcepit.foo"));
   }

}
