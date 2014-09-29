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

package org.sourcepit.osgifier.core.bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.ANY;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.COMPATIBLE;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.EQUIVALENT;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.GREATER_OR_EQUAL;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.PERFECT;
import static org.sourcepit.osgifier.core.bundle.VersionRangePolicy.STRICT;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgifier.core.bundle.VersionRangePolicy;

public class VersionRangePolicyTest
{

   @Test
   public void testParse()
   {
      try
      {
         VersionRangePolicy.parse(null);
         fail();
      }
      catch (IllegalArgumentException e)
      { // expected
      }

      try
      {
         VersionRangePolicy.parse("foo");
         fail();
      }
      catch (IllegalArgumentException e)
      { // expected
      }

      assertSame(PERFECT, VersionRangePolicy.parse("perfect"));
      assertSame(STRICT, VersionRangePolicy.parse("strict"));
      assertSame(EQUIVALENT, VersionRangePolicy.parse("equivalent"));
      assertSame(COMPATIBLE, VersionRangePolicy.parse("compatible"));
      assertSame(GREATER_OR_EQUAL, VersionRangePolicy.parse("greaterOrEqual"));
      assertSame(ANY, VersionRangePolicy.parse("any"));

      assertSame(PERFECT, VersionRangePolicy.parse("pErFeCt"));
   }

   @Test
   public void testToVersionRange() throws Exception
   {
      final Version version = Version.parse("1.2.3.qualifier");

      assertEquals("[" + version + "," + version + "]", PERFECT.toVersionRange(version, false).toString());
      assertEquals("[" + version + "," + version + "]", PERFECT.toVersionRange(version, true).toString());

      assertEquals("[1.2.3,1.2.4)", STRICT.toVersionRange(version, false).toString());
      assertEquals("[1.2.3,1.2.4)", STRICT.toVersionRange(version, true).toString());

      assertEquals("[1.2.3,1.3)", EQUIVALENT.toVersionRange(version, false).toString());
      assertEquals("[1.2,1.3)", EQUIVALENT.toVersionRange(version, true).toString());

      assertEquals("[1.2.3,2)", COMPATIBLE.toVersionRange(version, false).toString());
      assertEquals("[1.2,2)", COMPATIBLE.toVersionRange(version, true).toString());

      assertEquals("0", ANY.toVersionRange(version, false).toString());
      assertEquals("0", ANY.toVersionRange(version, true).toString());

      assertEquals("1.2.3", GREATER_OR_EQUAL.toVersionRange(version, false).toString());
      assertEquals("1.2", GREATER_OR_EQUAL.toVersionRange(version, true).toString());
   }

   @Test
   public void testToVersionRange2() throws Exception
   {
      final Version version = Version.parse("1.0.0.qualifier");

      assertEquals("[" + version + "," + version + "]", PERFECT.toVersionRange(version, false).toString());
      assertEquals("[" + version + "," + version + "]", PERFECT.toVersionRange(version, true).toString());

      assertEquals("[1,1.0.1)", STRICT.toVersionRange(version, false).toString());
      assertEquals("[1,1.0.1)", STRICT.toVersionRange(version, true).toString());

      assertEquals("[1,1.1)", EQUIVALENT.toVersionRange(version, false).toString());
      assertEquals("[1,1.1)", EQUIVALENT.toVersionRange(version, true).toString());

      assertEquals("[1,2)", COMPATIBLE.toVersionRange(version, false).toString());
      assertEquals("[1,2)", COMPATIBLE.toVersionRange(version, true).toString());

      assertEquals("0", ANY.toVersionRange(version, false).toString());
      assertEquals("0", ANY.toVersionRange(version, true).toString());

      assertEquals("1", GREATER_OR_EQUAL.toVersionRange(version, false).toString());
      assertEquals("1", GREATER_OR_EQUAL.toVersionRange(version, true).toString());
   }

}
