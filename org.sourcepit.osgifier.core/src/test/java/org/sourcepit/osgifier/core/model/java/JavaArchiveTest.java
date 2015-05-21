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

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaArchiveTest {
   @Test
   public void testGetResource() {
      JavaResourcesRoot jRoot = JavaModelFactory.eINSTANCE.createJavaResourcesRoot();
      jRoot.setName(".");

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      jArchive.getResourcesRoots().add(jRoot);

      try {
         jArchive.getResource(null);
         fail();
      }
      catch (IllegalArgumentException e) {
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
