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

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;

public enum VersionRangePolicy {
   PERFECT("perfect"), STRICT("strict"), EQUIVALENT("equivalent"), COMPATIBLE("compatible"), GREATER_OR_EQUAL(
      "greaterOrEqual"), ANY("any");

   private final String literal;

   private VersionRangePolicy(String literal) {
      this.literal = literal;
   }

   public String literal() {
      return literal;
   }

   public static VersionRangePolicy parse(String literal) {
      for (VersionRangePolicy policy : values()) {
         if (policy.literal().equalsIgnoreCase(literal)) {
            return policy;
         }
      }
      throw new IllegalArgumentException("Unknown version range policy: " + literal);
   }

   public VersionRange toVersionRange(Version version, boolean eraseMicro) {
      switch (this) {
         case PERFECT :
            return toPerfect(version);
         case STRICT :
            return toStrict(version);
         case EQUIVALENT :
            return toEquivalent(version, eraseMicro);
         case COMPATIBLE :
            return toCompatible(version, eraseMicro);
         case GREATER_OR_EQUAL :
            return toGreaterOrEqual(version, eraseMicro);
         case ANY :
            return VersionRange.INFINITE_RANGE;
         default :
            throw new IllegalStateException();
      }
   }

   private static VersionRange toGreaterOrEqual(Version version, boolean eraseMicro) {
      final Version left = toLeft(version, eraseMicro);
      return new VersionRange(left, true, null, false);
   }

   private static VersionRange toCompatible(Version version, boolean eraseMicro) {
      final Version left = toLeft(version, eraseMicro);
      final Version right = new Version(version.getMajor() + 1, -1, -1);
      return new VersionRange(left, true, right, false);
   }

   private static VersionRange toEquivalent(Version version, boolean eraseMicro) {
      final Version left = toLeft(version, eraseMicro);
      final Version right = new Version(version.getMajor(), version.getMinor() + 1, -1);
      return new VersionRange(left, true, right, false);
   }

   private static Version toLeft(Version version, boolean eraseMicro) {
      final Version left;
      if (eraseMicro) {
         left = new Version(version.getMajor(), version.getMinor() == 0 ? -1 : version.getMinor(), -1);
      }
      else {
         left = version.trimQualifier();
      }
      return eraseZeros(left);
   }

   private static VersionRange toStrict(Version version) {
      final Version left = eraseZeros(version.trimQualifier());
      final Version right = new Version(version.getMajor(), version.getMinor(), version.getMicro() + 1);
      return new VersionRange(left, true, right, false);
   }

   private static Version eraseZeros(Version v) {
      if (v.getQualifier().length() > 0) {
         return v;
      }
      if (v.getMicro() == 0) {
         final int micro = -1;
         final int minor = v.getMinor() == 0 ? -1 : v.getMinor();
         return new Version(v.getMajor(), minor, micro);
      }
      return v;
   }

   private static VersionRange toPerfect(Version version) {
      return new VersionRange(version, true, version, true);
   }

}