/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.validation.ConstraintViolationException;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.modularizor.core.util.SymbolicNameUtils;

/**
 * @author Bernd
 */
public class SymbolicNameUtilsTest
{

   @Test
   public void testIsValidSymbolicName()
   {
      assertFalse(SymbolicNameUtils.isValidSymbolicName(null));
      assertFalse(SymbolicNameUtils.isValidSymbolicName(""));
      assertFalse(SymbolicNameUtils.isValidSymbolicName(" "));
      assertFalse(SymbolicNameUtils.isValidSymbolicName("."));
      assertFalse(SymbolicNameUtils.isValidSymbolicName("ä"));
      assertFalse(SymbolicNameUtils.isValidSymbolicName("a..a"));
      assertFalse(SymbolicNameUtils.isValidSymbolicName("a.a."));
      assertTrue(SymbolicNameUtils.isValidSymbolicName("0"));
      assertTrue(SymbolicNameUtils.isValidSymbolicName("a"));
      assertTrue(SymbolicNameUtils.isValidSymbolicName("-"));
      assertTrue(SymbolicNameUtils.isValidSymbolicName("hans-dampf"));
      assertTrue(SymbolicNameUtils.isValidSymbolicName("morks.und.co"));
   }

   @Test
   public void testToValidSymbolicName()
   {
      try
      {
         SymbolicNameUtils.toValidSymbolicName(null);
         fail();
      }
      catch (ConstraintViolationException e)
      {
      }

      try
      {
         SymbolicNameUtils.toValidSymbolicName("");
         fail();
      }
      catch (ConstraintViolationException e)
      {
      }

      assertThat(SymbolicNameUtils.toValidSymbolicName(" "), IsEqual.equalTo("_"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("."), IsEqual.equalTo("_"));
      assertThat(SymbolicNameUtils.toValidSymbolicName(".."), IsEqual.equalTo("_"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("..."), IsEqual.equalTo("_"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("...................."), IsEqual.equalTo("_"));
      assertThat(SymbolicNameUtils.toValidSymbolicName(".foo.bar."), IsEqual.equalTo("foo.bar"));
      assertThat(SymbolicNameUtils.toValidSymbolicName(" foo.bar "), IsEqual.equalTo("foo.bar"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("foo-bar"), IsEqual.equalTo("foo-bar"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("händler"), IsEqual.equalTo("haendler"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("höhö"), IsEqual.equalTo("hoehoe"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("üben"), IsEqual.equalTo("ueben"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("muß"), IsEqual.equalTo("muss"));
      assertThat(SymbolicNameUtils.toValidSymbolicName("0"), IsEqual.equalTo("0"));
   }

}
