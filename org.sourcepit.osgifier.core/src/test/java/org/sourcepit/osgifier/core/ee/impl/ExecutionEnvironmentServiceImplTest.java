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

package org.sourcepit.osgifier.core.ee.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

public class ExecutionEnvironmentServiceImplTest {

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection0() throws Exception {
      Collection<String> c1 = asList("a");
      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1));
      assertEquals(1, intersection.size());
      assertTrue(intersection.contains("a"));
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection1() throws Exception {
      Collection<String> c1 = asList("a");
      Collection<String> c2 = asList("b");
      Collection<String> c3 = asList("c");

      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1, c2, c3));
      assertEquals(0, intersection.size());
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection2() throws Exception {
      Collection<String> c1 = asList("a", "b");
      Collection<String> c2 = asList("b");
      Collection<String> c3 = asList("b", "c");

      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1, c2, c3));
      assertEquals(1, intersection.size());
      assertTrue(intersection.contains("b"));
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection3() throws Exception {
      Collection<String> c1 = asList("a", "b", "c");
      Collection<String> c2 = asList("b", "c");

      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1, c2));
      assertEquals(2, intersection.size());
      assertTrue(intersection.contains("b"));
      assertTrue(intersection.contains("c"));
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection4() throws Exception {
      Collection<String> c1 = asList("a", "b", "c");
      Collection<String> c2 = asList("b", "c", "d");
      Collection<String> c3 = asList("b", "c", "d", "e");
      Collection<String> c4 = asList("b", "c", "d");

      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1, c2, c3, c4));
      assertEquals(2, intersection.size());
      assertTrue(intersection.contains("b"));
      assertTrue(intersection.contains("c"));
   }
}
