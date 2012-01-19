/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;

public class MavenToOSGiUtilsTest
{
   @Test
   public void testToVersion() throws Exception
   {
      // assertEquals("1.0.0", JavaUtil.cleanupVersion("1"));
      // assertEquals("1.1.0", JavaUtil.cleanupVersion("1.1"));
      // assertEquals("0.0.0.murks", JavaUtil.cleanupVersion("murks"));
      // assertEquals("1.0.0.v200192827", JavaUtil.cleanupVersion("1.v200192827"));
      // assertEquals("1.0.0.SNAPSHOT", JavaUtil.cleanupVersion("1-SNAPSHOT"));
      // assertEquals("1.0.0.SNAPSHOT", JavaUtil.cleanupVersion("1.0.SNAPSHOT"));

      Version version = MavenToOSGiUtils.toVersion("1", false);
      assertThat("1.0.0", IsEqual.equalTo(version.toString()));
      assertThat("1", IsEqual.equalTo(version.toMinimalString()));
   }

   @Test
   public void testToVersionRange() throws Exception
   {

      // assertEquals("[1,2)", JavaUtil.createVersionRange("1", null));
      // assertEquals("[1,2)", JavaUtil.createVersionRange(null, "1"));
      // assertEquals("[1.5,2)", JavaUtil.createVersionRange("1.5", null));
      // assertEquals("[1.5,2)", JavaUtil.createVersionRange("1.5", "3"));
      // assertEquals("[1.5,2)", JavaUtil.createVersionRange(null, "1.5"));
      // assertEquals("[1.5,2)", JavaUtil.createVersionRange(null, "1.5-SNAPSHOT"));
      // assertEquals("[1.5.2,1.6)", JavaUtil.createVersionRange("1.5.2", null));
      // assertEquals("[1.5.2,1.6)", JavaUtil.createVersionRange(null, "1.5.2"));
      // assertEquals("[1.5.2,1.6)", JavaUtil.createVersionRange(null, "1.5.2-SNAPSHOT"));
      // assertEquals("[1.0.0,1.1)", JavaUtil.createVersionRange("1.0.0.v2011", null));

      // 1.0 Version 1.0
      // [1.0,2.0) Versions 1.0 (included) to 2.0 (not included)
      // [1.0,2.0] Versions 1.0 to 2.0 (both included)
      // [1.5,) Versions 1.5 and higher
      // (,1.0],[1.2,) Versions up to 1.0 (included) and 1.2 or higher

      String mVersionRange;
      VersionRange oVersionRange;

      mVersionRange = "[1.0.0,2.0.0)";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1.0.0,2.0.0)"));

      mVersionRange = "[1.0,2.2]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1.0,2.2]"));

      mVersionRange = "[1,2)";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1,2)"));

      mVersionRange = "LATEST";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("0.0.0"));

      mVersionRange = "RELEASE";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("0.0.0"));

      mVersionRange = "1.0";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0"));

      mVersionRange = "1.0-RC1";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0.0.RC1"));

      mVersionRange = "[1.5,)";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.5"));

      mVersionRange = "(,1.0],[1.2,)";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.2"));

      mVersionRange = "[1.0.0-RC1,2.0.0)";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1.0.0.RC1,2.0.0)"));

      mVersionRange = "[1,2-RC2]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1,2.0.0.RC2]"));

      mVersionRange = "1.0-SNAPSHOT";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0"));

      mVersionRange = "1.0-SnApShOt";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0"));

      mVersionRange = "1.0-ABC-SNAPSHOT";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0.0.ABC"));
   }
}
