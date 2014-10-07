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

package org.sourcepit.osgifier.core.ee;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.sourcepit.osgifier.core.ee.ExecutionEnvironment;
import org.sourcepit.osgifier.core.ee.OsgiEE;

public class ExecutionEnvironmentTest
{

   @Test
   public void testCompareTo()
   {
      ExecutionEnvironment ee1 = newExecutionEnvironment("ee1", "2000-01-01", 0, Collections.<String> emptyList());
      assertEquals(0, ee1.compareTo(ee1));

      ExecutionEnvironment ee2 = newExecutionEnvironment("ee2", "2000-01-01", 0, Collections.<String> emptyList());
      assertEquals(0, ee2.compareTo(ee2));
      assertEquals(-1, ee1.compareTo(ee2));
      assertEquals(1, ee2.compareTo(ee1));

      ExecutionEnvironment ee3 = newExecutionEnvironment("ee3", "2000-01-01", 1, Collections.<String> emptyList());
      assertEquals(0, ee3.compareTo(ee3));
      assertEquals(-1, ee1.compareTo(ee3));
      assertEquals(1, ee3.compareTo(ee1));

      ExecutionEnvironment ee4 = newExecutionEnvironment("ee4", "2000-01-01", 1, asList("package1"));
      assertEquals(0, ee4.compareTo(ee4));
      assertEquals(-1, ee1.compareTo(ee4));
      assertEquals(1, ee4.compareTo(ee1));
      assertEquals(-1, ee3.compareTo(ee4));
      assertEquals(1, ee4.compareTo(ee3));

      ExecutionEnvironment ee5 = newExecutionEnvironment("ee5", "2000-01-02", 1, asList("package1"));
      assertEquals(0, ee5.compareTo(ee5));
      assertEquals(-1, ee1.compareTo(ee5));
      assertEquals(1, ee5.compareTo(ee1));
      assertEquals(-1, ee3.compareTo(ee5));
      assertEquals(1, ee5.compareTo(ee3));
      assertEquals(-1, ee4.compareTo(ee5));
      assertEquals(1, ee5.compareTo(ee4));

      ExecutionEnvironment ee6 = newExecutionEnvironment("ee6", "2000-01-01", 1, asList("package1", "package2"));
      assertEquals(0, ee6.compareTo(ee6));
      assertEquals(-1, ee1.compareTo(ee6));
      assertEquals(1, ee6.compareTo(ee1));
      assertEquals(-1, ee3.compareTo(ee6));
      assertEquals(1, ee6.compareTo(ee3));
      assertEquals(-1, ee4.compareTo(ee6));
      assertEquals(1, ee6.compareTo(ee4));
      assertEquals(-1, ee5.compareTo(ee6));
      assertEquals(1, ee6.compareTo(ee5));

      ExecutionEnvironment ee7 = newExecutionEnvironment("ee1", "2000-01-01", 2, Collections.<String> emptyList());
      assertEquals(0, ee7.compareTo(ee7));
      assertEquals(-1, ee1.compareTo(ee7));
      assertEquals(1, ee7.compareTo(ee1));
      assertEquals(-1, ee3.compareTo(ee7));
      assertEquals(1, ee7.compareTo(ee3));
      assertEquals(-1, ee4.compareTo(ee7));
      assertEquals(1, ee7.compareTo(ee4));
      assertEquals(-1, ee5.compareTo(ee7));
      assertEquals(1, ee7.compareTo(ee5));
   }

   private ExecutionEnvironment newExecutionEnvironment(String id, String releaseDate, float maxClassVersion,
      final List<String> packages)
   {
      return new ExecutionEnvironment(id, releaseDate, maxClassVersion,
         new OsgiEE(id, String.valueOf(maxClassVersion)), packages);
   }

}
