/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.utils;

import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.tools.osgifyme.test.resources.TypeA;

public abstract class AbstractTraverserTest
{
   private static final String TEST_RESOURCES_PACKAGE_PATH = TypeA.class.getPackage().getName().replace('.', '/') + "/";

   @Test
   public void testTravers()
   {
      final Map<String, Boolean> pathToIsDir = new HashMap<String, Boolean>();
      IResourceVisitor visitor = new IResourceVisitor()
      {
         public boolean visit(String path, boolean isDirectory, InputStream content)
         {
            if (isDirectory)
            {
               assertThat(content, IsNull.nullValue());
            }
            pathToIsDir.put(path, Boolean.valueOf(isDirectory));
            return true;
         }
      };
      travers(visitor);

      Boolean isDirectory = pathToIsDir.get(TEST_RESOURCES_PACKAGE_PATH);
      assertThat(isDirectory, Is.is(Boolean.TRUE));

      isDirectory = pathToIsDir.get(TEST_RESOURCES_PACKAGE_PATH + TypeA.class.getSimpleName() + ".java");
      assertThat(isDirectory, Is.is(Boolean.FALSE));
   }

   protected abstract void travers(IResourceVisitor visitor);

   @Test
   public void testSkipChildren()
   {
      final Map<String, Boolean> pathToIsDir = new HashMap<String, Boolean>();
      IResourceVisitor visitor = new IResourceVisitor()
      {
         public boolean visit(String path, boolean isDirectory, InputStream content)
         {
            if (isDirectory)
            {
               assertThat(content, IsNull.nullValue());
            }
            pathToIsDir.put(path, Boolean.valueOf(isDirectory));
            return false;
         }
      };
      travers(visitor);
      assertThat(2, IsEqual.equalTo(pathToIsDir.size()));
   }
}
