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

package org.sourcepit.osgifier.maven.impl;

import static org.junit.Assert.assertThat;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;

public class MavenToOSGiUtilsTest {
   @Test
   public void testToVersion() throws Exception {
      // assertEquals("1.0.0", JavaUtil.cleanupVersion("1"));
      // assertEquals("1.1.0", JavaUtil.cleanupVersion("1.1"));
      // assertEquals("0.0.0.murks", JavaUtil.cleanupVersion("murks"));
      // assertEquals("1.0.0.v200192827", JavaUtil.cleanupVersion("1.v200192827"));
      // assertEquals("1.0.0.SNAPSHOT", JavaUtil.cleanupVersion("1-SNAPSHOT"));
      // assertEquals("1.0.0.SNAPSHOT", JavaUtil.cleanupVersion("1.0.SNAPSHOT"));

      Version version = MavenToOSGiUtils.toVersion("1", false);
      assertThat("1.0.0", IsEqual.equalTo(version.toString()));
      assertThat("1", IsEqual.equalTo(version.toMinimalString()));

      version = MavenToOSGiUtils.toVersion("1.1", false);
      assertThat("1.1.0", IsEqual.equalTo(version.toString()));
      assertThat("1.1", IsEqual.equalTo(version.toMinimalString()));

      version = MavenToOSGiUtils.toVersion("murks", false);
      assertThat(version, IsNull.nullValue());

      version = MavenToOSGiUtils.toVersion("murks", true);
      assertThat("0.0.0.murks", IsEqual.equalTo(version.toString()));
      assertThat("0.0.0.murks", IsEqual.equalTo(version.toMinimalString()));

      version = MavenToOSGiUtils.toVersion("1.v200192827", false);
      assertThat("1.0.0.v200192827", IsEqual.equalTo(version.toString()));
      assertThat("1.0.0.v200192827", IsEqual.equalTo(version.toMinimalString()));

      version = MavenToOSGiUtils.toVersion("1-SNAPSHOT", false);
      assertThat("1.0.0.SNAPSHOT", IsEqual.equalTo(version.toString()));
      assertThat("1.0.0.SNAPSHOT", IsEqual.equalTo(version.toMinimalString()));

      version = MavenToOSGiUtils.toVersion("1.0.0-SNAPSHOT", false);
      assertThat("1.0.0.SNAPSHOT", IsEqual.equalTo(version.toString()));
      assertThat("1.0.0.SNAPSHOT", IsEqual.equalTo(version.toMinimalString()));
   }

   @Test
   public void testToVersionRange() throws Exception {
      // Form eclipse version range doc
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
      assertThat(oVersionRange.toString(), IsEqual.equalTo("0"));

      mVersionRange = "RELEASE";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("0"));

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
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1.0.0,2.0.0)"));

      mVersionRange = "[1,2-RC2]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1,2]"));

      // Maven Version Range Spec
      // http://docs.codehaus.org/display/MAVEN/Dependency+Mediation+and+Conflict+Resolution#DependencyMediationandConflictResolution-DependencyVersionRanges
      // Proposed syntax:
      // Range | Meaning
      // (,1.0] : x <= 1.0
      // 1.0 : "Soft" requirement on 1.0 (just a recommendation - helps select the correct version if it matches all
      // ranges)
      // [1.0] : Hard requirement on 1.0
      // [1.2,1.3] : 1.2 <= x <= 1.3
      // [1.0,2.0) : 1.0 <= x < 2.0
      // [1.5,) : x >= 1.5
      // (,1.0],[1.2,) : x <= 1.0 or x >= 1.2. Multiple sets are comma-separated
      // (,1.1),(1.1,) : This excludes 1.1 if it is known not to work in combination with this library


      // Maven Envorcer Plugin
      // http://maven.apache.org/plugins/maven-enforcer-plugin/rules/versionRanges.html
      // Range Meaning
      // 1.0 x >= 1.0 * The default Maven meaning for 1.0 is everything (,) but with 1.0 recommended. Obviously this
      // doesn't work for enforcing versions here, so it has been redefined as a minimum version.
      // (,1.0] x <= 1.0
      // (,1.0) x < 1.0
      // [1.0] x == 1.0
      // [1.0,) x >= 1.0
      // (1.0,) x > 1.0
      // (1.0,2.0) 1.0 < x < 2.0
      // [1.0,2.0] 1.0 <= x <= 2.0
      // (,1.0],[1.2,) x <= 1.0 or x >= 1.2. Multiple sets are comma-separated
      // (,1.1),(1.1,) x != 1.1

      // OSGi Spec
      // Example Predicate
      // [1.2.3, 4.5.6) 1.2.3 <= x < 4.5.6
      // [1.2.3, 4.5.6] 1.2.3 <= x <= 4.5.6
      // (1.2.3, 4.5.6) 1.2.3 < x < 4.5.6
      // (1.2.3, 4.5.6] 1.2.3 < x <= 4.5.6
      // 1.2.3 1.2.3 <= x


      // Version (Maven Syntax) :: Maven : Enforcer : OSGi :: Osgifier (OSGi Syntax)
      // 1.0 :: * : x >= 1.0 : x >= 1.0 :: 1.0
      // (,1.0] :: x <= 1.0 : 0 < x <= 1.0 : 0 < x <= 1.0 :: [,1.0]

      // Mvn (,1] -> OSGi [,1]
      osgi_assertIsNotIncluded("(,1]", "0"); // !!!
      osgi_assertIsIncluded("[,1]", "0");
      mavn_assertIsIncluded("(,1]", "0");

      mVersionRange = "(,1]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[,1]"));

      // Mvn [,1] -> OSGi [,1]
      osgi_assertIsIncluded("[,1]", "0");
      mavn_assertIsIncluded("[,1]", "0");

      mVersionRange = "[,1]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[,1]"));

      // Mvn [0,1] -> OSGi [0,1]
      osgi_assertIsIncluded("[0,1]", "0");
      mavn_assertIsIncluded("[0,1]", "0");

      mVersionRange = "[0,1]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[0,1]"));

      // Mvn (0,1] -> OSGi (0,1] or (,1]
      osgi_assertIsNotIncluded("(,1]", "0");
      osgi_assertIsIncluded("(,1]", "0.1");
      osgi_assertIsNotIncluded("(0,1]", "0");
      osgi_assertIsIncluded("(0,1]", "0.0.1");
      mavn_assertIsNotIncluded("(0,1]", "0");
      mavn_assertIsIncluded("(0,1]", "0.0.1");

      mVersionRange = "(0,1]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("(0,1]"));

      // Mvn (1,2] -> OSGi (1,2]
      osgi_assertIsNotIncluded("(1,2]", "1");
      osgi_assertIsIncluded("(1,2]", "1.0.1");
      mavn_assertIsNotIncluded("(1,2]", "1");
      mavn_assertIsIncluded("(1,2]", "1.0.1");

      mVersionRange = "(1,2]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("(1,2]"));

      // Mvn [1,2] -> OSGi [1,2]
      osgi_assertIsIncluded("[1,2]", "1");
      mavn_assertIsIncluded("[1,2]", "1");

      mVersionRange = "[1,2]";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1,2]"));

      // Mvn [1-RC1,2) -> OSGi [1.0.0.RC1,2)
      // assertThat(VersionRange.parse("[1.0.0.RC1,2)").includes(Version.parse("1");

      osgi_assertIsIncluded("[1,2)", "1.0.0");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.RC1");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.RC2");
      osgi_assertIsIncluded("[1,2)", "1.0.0");
      osgi_assertIsIncluded("[1,2)", "1.0.0.A");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.a");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.fooooooo");
      osgi_assertIsIncluded("[1,2)", "1.0.0.AAA");
      osgi_assertIsIncluded("[1,2)", "1.0.0.AAAA");
      osgi_assertIsIncluded("[1,2)", "1.0.0.Z");
      osgi_assertIsIncluded("[1,2)", "1.0.0.SNAPSHOT");
      osgi_assertIsIncluded("[1,2)", "1.0.0.121212");

      // maven magic
      // assertThat(VersionRange.parse("[1.0.0.RC1,2)").includes(Version.parse("1.0.0.beta")), Is.is(false));
      // assertThat(VersionRange.parse("[1.0.0.RC1,2)").includes(Version.parse("1.0.0.alpha")), Is.is(false));
      // assertThat(VersionRange.parse("[1.0.0.RC1,2)").includes(Version.parse("1.0.0.ALPHA")), Is.is(false));

      osgi_assertIsIncluded("[1,2)", "1.0.0");
      osgi_assertIsIncluded("[1,2)", "1.0.0.RC1");
      osgi_assertIsIncluded("[1,2)", "1.0.0.RC2");
      osgi_assertIsIncluded("[1,2)", "1.0.0");
      osgi_assertIsIncluded("[1,2)", "1.0.0.A");
      osgi_assertIsIncluded("[1,2)", "1.0.0.a");
      osgi_assertIsIncluded("[1,2)", "1.0.0.fooooooo");
      osgi_assertIsIncluded("[1,2)", "1.0.0.AAA");
      osgi_assertIsIncluded("[1,2)", "1.0.0.AAAA");
      osgi_assertIsIncluded("[1,2)", "1.0.0.Z");
      osgi_assertIsIncluded("[1,2)", "1.0.0.SNAPSHOT");
      osgi_assertIsIncluded("[1,2)", "1.0.0.121212");

      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.RC1");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.RC2");
      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0");
      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0.A");
      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0.a");
      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0.fooooooo");
      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0.AAA");
      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0.AAAA");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.Z");
      osgi_assertIsIncluded("[1.0.0.RC1,2)", "1.0.0.SNAPSHOT");
      osgi_assertIsIncluded("[1.0.0,2)", "1.0.0.121212");

      mavn_assertIsIncluded("[1-RC1,2)", "1");
      mavn_assertIsIncluded("[1-RC1,2)", "1-RC1");
      mavn_assertIsIncluded("[1-RC1,2)", "1-RC2");
      mavn_assertIsIncluded("[1-RC1,2)", "1.0.0");
      mavn_assertIsIncluded("[1-RC1,2)", "1-A");
      mavn_assertIsIncluded("[1-RC1,2)", "1-a");
      mavn_assertIsIncluded("[1-RC1,2)", "1-fooooooo");
      mavn_assertIsIncluded("[1-RC1,2)", "1-AAA");
      mavn_assertIsIncluded("[1-RC1,2)", "1-AAAA");
      mavn_assertIsIncluded("[1-RC1,2)", "1-Z");
      mavn_assertIsIncluded("[1-RC1,2)", "1-SNAPSHOT");
      mavn_assertIsIncluded("[1-RC1,2)", "1-121212");


      // Mvn [1-cc,2) -> OSGi [1.0.0.cc,2)
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.RC1");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.RC2");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.A");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.a");
      osgi_assertIsIncluded("[1.0.0.cc,2)", "1.0.0.fooooooo");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.AAA");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.AAAA");
      osgi_assertIsIncluded("[1.0.0.cc,2)", "1.0.0.z");
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.SNAPSHOT");

      mavn_assertIsNotIncluded("[1-cc,2)", "1");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-RC1");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-RC2");
      mavn_assertIsNotIncluded("[1-cc,2)", "1.0.0");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-A");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-a");
      mavn_assertIsIncluded("[1-cc,2)", "1-fooooooo");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-AAA");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-AAAA");
      mavn_assertIsIncluded("[1-cc,2)", "1-z");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-SNAPSHOT");


      // maven magic

      // FIX: Version to lower
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.Z");
      mavn_assertIsIncluded("[1-cc,2)", "1-Z");

      // FIX: Version remove snapshot
      osgi_assertIsIncluded("[1.0.0.cc,2)", "1.0.0.snapshot");
      mavn_assertIsNotIncluded("[1-cc,2)", "1-snapshot");
      osgi_assertIsNotIncluded("[1.0.0.zz,2)", "1.0.0.snapshot");
      mavn_assertIsNotIncluded("[1-zz,2)", "1-snapshot");

      // FIX ?????????????????????
      osgi_assertIsNotIncluded("[1.0.0.cc,2)", "1.0.0.121212");
      mavn_assertIsIncluded("[1-cc,2)", "1-121212");
      osgi_assertIsIncluded("[1.0.0.121212,2)", "1.0.0.cc");
      mavn_assertIsNotIncluded("[1-121212,2)", "1-cc");

      // maven magic see org.apache.maven.artifact.versioning.ComparableVersion.StringItem.QUALIFIERS = { "alpha",
      // "beta", "milestone", "rc", "snapshot", "", "sp" };
      mavn_assertIsNotIncluded("[1-RC1,2)", "1-alpha");
      mavn_assertIsNotIncluded("[1-RC1,2)", "1-ALPHA");
      mavn_assertIsNotIncluded("[1-RC1,2)", "1-beta");

      // / ----------------> CUT QUALIFIERS WHILE CONVERTING RANGES!!!!!!

      mVersionRange = "[1-RC1,2)";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("[1,2)"));

      // (,1.0) :: x < 1.0 : x < 1.0 : x < 1.0 :: [0,1.0)
      // [1.0] :: x == 1.0: x == 1.0 : x == 1.0 :: [1.0,1.0]

      // how to handle snapshots?
      // assertEquals("[1.5,2)", JavaUtil.createVersionRange(null, "1.5-SNAPSHOT"));
      // assertEquals("[1.5.2,1.6)", JavaUtil.createVersionRange("1.5.2", null));
      // assertEquals("[1.5.2,1.6)", JavaUtil.createVersionRange(null, "1.5.2"));
      // assertEquals("[1.5.2,1.6)", JavaUtil.createVersionRange(null, "1.5.2-SNAPSHOT"));
      // assertEquals("[1.0.0,1.1)", JavaUtil.createVersionRange("1.0.0.v2011", null));

      mavn_assertIsIncluded("1.0-SNAPSHOT", "1.0-SNAPSHOT");
      osgi_assertIsIncluded("1.0.0.SNAPSHOT", "1.0.0.SNAPSHOT");

      mVersionRange = "1.0-SNAPSHOT";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      // assertThat(oVersionRange.toString(), IsEqual.equalTo("[1.0,2)"));
      // or
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0.0.SNAPSHOT"));
      // ?

      mVersionRange = "1.0-SnApShOt";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0.0.SnApShOt"));

      mVersionRange = "1.0-ABC-SNAPSHOT";
      oVersionRange = MavenToOSGiUtils.toVersionRange(mVersionRange);
      assertThat(oVersionRange.toString(), IsEqual.equalTo("1.0.0.ABC-SNAPSHOT"));
   }

   private static void mavn_assertIsIncluded(String versionRange, String version)
      throws InvalidVersionSpecificationException {
      assertThat(newMvnVersionRange(versionRange).containsVersion(newMvnVersion(version)), Is.is(true));
   }

   private static void mavn_assertIsNotIncluded(String versionRange, String version)
      throws InvalidVersionSpecificationException {
      assertThat(newMvnVersionRange(versionRange).containsVersion(newMvnVersion(version)), Is.is(false));
   }

   private static void osgi_assertIsIncluded(String versionRange, String version)
      throws InvalidVersionSpecificationException {
      assertThat(VersionRange.parse(versionRange).includes(Version.parse(version)), Is.is(true));
   }

   private static void osgi_assertIsNotIncluded(String versionRange, String version)
      throws InvalidVersionSpecificationException {
      assertThat(VersionRange.parse(versionRange).includes(Version.parse(version)), Is.is(false));
   }

   private static DefaultArtifactVersion newMvnVersion(String version) {
      return new DefaultArtifactVersion(version);
   }

   private static org.apache.maven.artifact.versioning.VersionRange newMvnVersionRange(String versionRange)
      throws InvalidVersionSpecificationException {
      return org.apache.maven.artifact.versioning.VersionRange.createFromVersionSpec(versionRange);
   }
}
