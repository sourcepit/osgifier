/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

public class ExecutionEnvironmentServiceImplTest
{

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection0() throws Exception
   {
      Collection<String> c1 = asList("a");
      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1));
      assertEquals(1, intersection.size());
      assertTrue(intersection.contains("a"));
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection1() throws Exception
   {
      Collection<String> c1 = asList("a");
      Collection<String> c2 = asList("b");
      Collection<String> c3 = asList("c");

      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1, c2, c3));
      assertEquals(0, intersection.size());
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection2() throws Exception
   {
      Collection<String> c1 = asList("a", "b");
      Collection<String> c2 = asList("b");
      Collection<String> c3 = asList("b", "c");

      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1, c2, c3));
      assertEquals(1, intersection.size());
      assertTrue(intersection.contains("b"));
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection3() throws Exception
   {
      Collection<String> c1 = asList("a", "b", "c");
      Collection<String> c2 = asList("b", "c");

      Set<String> intersection = ExecutionEnvironmentServiceImpl.newIntersection(asList(c1, c2));
      assertEquals(2, intersection.size());
      assertTrue(intersection.contains("b"));
      assertTrue(intersection.contains("c"));
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNewIntersection4() throws Exception
   {
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
