/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import javax.validation.ConstraintViolationException;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.modularizor.core.model.java.Directory;
import org.sourcepit.modularizor.core.model.java.File;
import org.sourcepit.modularizor.core.model.java.JavaArchive;
import org.sourcepit.modularizor.core.model.java.JavaModelFactory;
import org.sourcepit.modularizor.core.model.java.JavaResourcesRoot;
import org.sourcepit.modularizor.core.model.java.Resource;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaArchiveTest
{
   @Test
   public void testGetResource()
   {
      JavaResourcesRoot jRoot = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      jRoot.setName(".");

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jArchive.getResourcesRoots().add(jRoot);

      try
      {
         jArchive.getResource(null);
         fail();
      }
      catch (ConstraintViolationException e)
      {
      }

      assertThat(jArchive.getResource("a"), IsNull.nullValue());

      File a = jRoot.getFile("a", true);
      assertThat(a.getName(), IsEqual.equalTo("a"));
      assertThat(jArchive.getResource("a"), IsNull.notNullValue());
      assertThat(jArchive.getResource("b"), IsNull.nullValue());

      File b = jRoot.getFile("b", true);
      assertThat(b.getName(), IsEqual.equalTo("b"));
      assertThat(jArchive.getResource("a"), IsNull.notNullValue());
      assertThat(jArchive.getResource("b"), IsNull.notNullValue());

      File file = jRoot.getFile("foo/Bar.txt", true);
      assertThat(file.getName(), IsEqual.equalTo("Bar.txt"));
      assertThat(file.getParentDirectory().getParentDirectory(), Is.is((Directory) jRoot));

      Resource resource = jArchive.getResource("foo/Bar.txt");
      assertThat(resource, IsEqual.equalTo((Resource) file));
   }
}
