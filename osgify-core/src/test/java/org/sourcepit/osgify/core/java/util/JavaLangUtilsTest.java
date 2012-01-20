/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.sourcepit.common.utils.path.Path;

public class JavaLangUtilsTest
{

   @Test
   public void testIsFullyQiallifiedPackageName()
   {
      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("", "."));
      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("com.bosch", "."));
      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("com.bosch", "."));
      assertFalse(JavaLangUtils.isFullyQuallifiedPackageName("com.bo-sch", "."));
      assertFalse(JavaLangUtils.isFullyQuallifiedPackageName("com.1bosch", "."));

      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("", "/"));
      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("com/bosch/", "/"));
      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("com/bosch", "/"));
      assertFalse(JavaLangUtils.isFullyQuallifiedPackageName("com/bo-sch", "/"));
      assertFalse(JavaLangUtils.isFullyQuallifiedPackageName("com/1bosch", "/"));

      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("", "\\"));
      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("com\\bosch\\", "\\"));
      assertTrue(JavaLangUtils.isFullyQuallifiedPackageName("com\\bosch", "\\"));
      assertFalse(JavaLangUtils.isFullyQuallifiedPackageName("com\\bo-sch", "\\"));
      assertFalse(JavaLangUtils.isFullyQuallifiedPackageName("com\\1bosch", "\\"));
   }

   @Test
   public void testToPackageName() throws Exception
   {
      try
      {
         JavaLangUtils.toPackageName((Path) null);
      }
      catch (ConstraintViolationException e)
      {
         System.out.println(e.getMessage());
      }

      JavaLangUtils.toPackageName("");
   }

}
