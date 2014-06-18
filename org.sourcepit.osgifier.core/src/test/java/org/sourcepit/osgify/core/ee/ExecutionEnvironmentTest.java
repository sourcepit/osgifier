/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

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
