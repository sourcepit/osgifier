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

package org.sourcepit.osgifier.core.util;

import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgifier.core.inspect.ResourceVisitor;
import org.sourcepit.osgifier.test.resources.TypeA;

public abstract class AbstractTraverserTest
{
   public static final String TEST_RESOURCES_PACKAGE_PATH = TypeA.class.getPackage().getName().replace('.', '/');

   @Test
   public void testTravers()
   {
      final Map<String, Boolean> pathToIsDir = Collections.synchronizedMap(new HashMap<String, Boolean>());
      ResourceVisitor visitor = new ResourceVisitor()
      {
         public void visit(Path path, boolean isDirectory, InputStream content)
         {
            if (isDirectory)
            {
               assertThat(content, IsNull.nullValue());
            }
            pathToIsDir.put(path.toString(), Boolean.valueOf(isDirectory));
         }
      };
      travers(visitor);
      Boolean isDirectory = pathToIsDir.get(TEST_RESOURCES_PACKAGE_PATH);
      assertThat(isDirectory, Is.is(Boolean.TRUE));

      isDirectory = pathToIsDir.get(TEST_RESOURCES_PACKAGE_PATH + "/" + TypeA.class.getSimpleName() + ".java");
      assertThat(isDirectory, Is.is(Boolean.FALSE));
   }

   protected abstract void travers(ResourceVisitor visitor);

   @Test
   public void testSkipChildren()
   {
      final Map<String, Boolean> pathToIsDir = new HashMap<String, Boolean>();
      ResourceVisitor visitor = new ResourceVisitor()
      {
         public void visit(Path path, boolean isDirectory, InputStream content)
         {
            if (isDirectory && path.getSegments().size() == 1)
            {
               assertThat(content, IsNull.nullValue());
               pathToIsDir.put(path.toString(), Boolean.valueOf(isDirectory));
            }
         }
      };
      travers(visitor);
      assertThat(pathToIsDir.size(), IsEqual.equalTo(2));
      assertThat(pathToIsDir.containsKey("META-INF"), IsEqual.equalTo(true));
      assertThat(pathToIsDir.containsKey("org"), IsEqual.equalTo(true));
   }
}
