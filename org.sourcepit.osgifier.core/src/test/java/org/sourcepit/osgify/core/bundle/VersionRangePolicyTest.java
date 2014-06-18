/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.ANY;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.COMPATIBLE;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.EQUIVALENT;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.GREATER_OR_EQUAL;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.PERFECT;
import static org.sourcepit.osgify.core.bundle.VersionRangePolicy.STRICT;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;

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
