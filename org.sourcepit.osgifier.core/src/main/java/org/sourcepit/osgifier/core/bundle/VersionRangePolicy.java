/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.bundle;

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;

public enum VersionRangePolicy
{
   PERFECT("perfect"), STRICT("strict"), EQUIVALENT("equivalent"), COMPATIBLE("compatible"), GREATER_OR_EQUAL(
      "greaterOrEqual"), ANY("any");

   private final String literal;

   private VersionRangePolicy(String literal)
   {
      this.literal = literal;
   }

   public String literal()
   {
      return literal;
   }

   public static VersionRangePolicy parse(String literal)
   {
      for (VersionRangePolicy policy : values())
      {
         if (policy.literal().equalsIgnoreCase(literal))
         {
            return policy;
         }
      }
      throw new IllegalArgumentException("Unknown version range policy: " + literal);
   }

   public VersionRange toVersionRange(Version version, boolean eraseMicro)
   {
      switch (this)
      {
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

   private static VersionRange toGreaterOrEqual(Version version, boolean eraseMicro)
   {
      final Version left = toLeft(version, eraseMicro);
      return new VersionRange(left, true, null, false);
   }

   private static VersionRange toCompatible(Version version, boolean eraseMicro)
   {
      final Version left = toLeft(version, eraseMicro);
      final Version right = new Version(version.getMajor() + 1, -1, -1);
      return new VersionRange(left, true, right, false);
   }

   private static VersionRange toEquivalent(Version version, boolean eraseMicro)
   {
      final Version left = toLeft(version, eraseMicro);
      final Version right = new Version(version.getMajor(), version.getMinor() + 1, -1);
      return new VersionRange(left, true, right, false);
   }

   private static Version toLeft(Version version, boolean eraseMicro)
   {
      final Version left;
      if (eraseMicro)
      {
         left = new Version(version.getMajor(), version.getMinor() == 0 ? -1 : version.getMinor(), -1);
      }
      else
      {
         left = version.trimQualifier();
      }
      return eraseZeros(left);
   }

   private static VersionRange toStrict(Version version)
   {
      final Version left = eraseZeros(version.trimQualifier());
      final Version right = new Version(version.getMajor(), version.getMinor(), version.getMicro() + 1);
      return new VersionRange(left, true, right, false);
   }

   private static Version eraseZeros(Version v)
   {
      if (v.getQualifier().length() > 0)
      {
         return v;
      }
      if (v.getMicro() == 0)
      {
         final int micro = -1;
         final int minor = v.getMinor() == 0 ? -1 : v.getMinor();
         return new Version(v.getMajor(), minor, micro);
      }
      return v;
   }

   private static VersionRange toPerfect(Version version)
   {
      return new VersionRange(version, true, version, true);
   }

}