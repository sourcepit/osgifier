/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class JavaUtilsTest
{

   @Test
   public void test()
   {
      assertTrue(JavaUtils.isFullyQiallifiedPackageName("", "."));
      assertTrue(JavaUtils.isFullyQiallifiedPackageName("com.bosch", "."));
      assertTrue(JavaUtils.isFullyQiallifiedPackageName("com.bosch", "."));
      assertFalse(JavaUtils.isFullyQiallifiedPackageName("com.bo-sch", "."));
      assertFalse(JavaUtils.isFullyQiallifiedPackageName("com.1bosch", "."));

      assertTrue(JavaUtils.isFullyQiallifiedPackageName("", "/"));
      assertTrue(JavaUtils.isFullyQiallifiedPackageName("com/bosch/", "/"));
      assertTrue(JavaUtils.isFullyQiallifiedPackageName("com/bosch", "/"));
      assertFalse(JavaUtils.isFullyQiallifiedPackageName("com/bo-sch", "/"));
      assertFalse(JavaUtils.isFullyQiallifiedPackageName("com/1bosch", "/"));

      assertTrue(JavaUtils.isFullyQiallifiedPackageName("", "\\"));
      assertTrue(JavaUtils.isFullyQiallifiedPackageName("com\\bosch\\", "\\"));
      assertTrue(JavaUtils.isFullyQiallifiedPackageName("com\\bosch", "\\"));
      assertFalse(JavaUtils.isFullyQiallifiedPackageName("com\\bo-sch", "\\"));
      assertFalse(JavaUtils.isFullyQiallifiedPackageName("com\\1bosch", "\\"));
   }

}
