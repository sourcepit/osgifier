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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.IllegalArgumentException;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.osgifier.core.util.SymbolicNameUtils;

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
      catch (IllegalArgumentException e)
      {
      }

      try
      {
         SymbolicNameUtils.toValidSymbolicName("");
         fail();
      }
      catch (IllegalArgumentException e)
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
