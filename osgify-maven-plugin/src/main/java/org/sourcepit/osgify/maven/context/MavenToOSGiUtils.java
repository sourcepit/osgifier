/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.artifact.versioning.ArtifactVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.Restriction;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;

public final class MavenToOSGiUtils
{
   private static final Pattern OSGI_VERSION_PATTERN = Pattern.compile(
      "(\\d+)(\\.(\\d+)(\\.(\\d+))?)?([^a-zA-Z0-9](.*))?", Pattern.DOTALL);

   private MavenToOSGiUtils()
   {
      super();
   }

   public static VersionRange toVersionRange(String mvnVersionRange)
   {
      return toVersionRange(getMavenVersionRange(mvnVersionRange));
   }

   private static VersionRange toVersionRange(org.apache.maven.artifact.versioning.VersionRange mvnVersionRange)
   {
      final ArtifactVersion recommendedVersion = mvnVersionRange.getRecommendedVersion();
      if (recommendedVersion != null)
      {
         final String mvnVersionString = recommendedVersion.toString();
         if ("LATEST".equals(mvnVersionString) || "RELEASE".equals(mvnVersionString))
         {
            return VersionRange.INFINITE_RANGE;
         }
         else
         {
            final Version version;
            version = MavenToOSGiUtils.toVersion(trimQualifier(recommendedVersion).toString(), true);
            return new VersionRange(version, true, null, false);
         }
      }

      List<Restriction> restrictions = mvnVersionRange.getRestrictions();
      if (!restrictions.isEmpty())
      {
         final Restriction restriction = restrictions.get(restrictions.size() - 1);

         final ArtifactVersion lowerBound = trimQualifier(restriction.getLowerBound());
         final ArtifactVersion upperBound = trimQualifier(restriction.getUpperBound());

         final Version lowVersion = lowerBound == null ? null : MavenToOSGiUtils.toVersion(lowerBound.toString(), true);
         final Version highVersion = upperBound == null ? null : MavenToOSGiUtils
            .toVersion(upperBound.toString(), true);

         // Mvn (,1.0] -> OSGi [,1.0] // see tests
         final boolean lowerBoundInclusive = lowerBound == null || restriction.isLowerBoundInclusive();

         return new VersionRange(lowVersion, lowerBoundInclusive, highVersion, restriction.isUpperBoundInclusive());
      }
      throw new IllegalStateException();
   }

   private static ArtifactVersion trimQualifier(ArtifactVersion version)
   {
      if (version != null && version.getQualifier() != null)
      {
         final String versionString = version.toString();
         final int idx = versionString.lastIndexOf('-');
         return new DefaultArtifactVersion(versionString.substring(0, idx));
      }
      return version;
   }

   private static org.apache.maven.artifact.versioning.VersionRange getMavenVersionRange(String mvnVersionRange)
   {
      try
      {
         return org.apache.maven.artifact.versioning.VersionRange.createFromVersionSpec(mvnVersionRange);
      }
      catch (InvalidVersionSpecificationException e)
      {
         throw new IllegalArgumentException(e);
      }
   }

   public static Version toVersion(String version, boolean fallback)
   {
      final Version result;
      final Matcher m = OSGI_VERSION_PATTERN.matcher(version);
      if (m.matches())
      {
         final String major = m.group(1);
         final String minor = m.group(3);
         final String micro = m.group(5);
         final String qualifier = m.group(7);

         final boolean hasQualifier = qualifier != null;

         result = new Version(toInt(major), toSpecialInt(minor, hasQualifier), toSpecialInt(micro, hasQualifier),
            hasQualifier ? cleanupQualifier(qualifier) : null);
      }
      else if (fallback)
      {
         final StringBuilder sb = new StringBuilder();
         sb.append("0.0.0.");
         cleanupQualifier(sb, version);
         result = Version.parse(sb.toString());
      }
      else
      {
         result = null;
      }
      return result;
   }

   private static int toSpecialInt(final String number, final boolean hasQualifier)
   {
      return number == null ? (hasQualifier ? 0 : -1) : toInt(number);
   }

   private static int toInt(String number)
   {
      return Integer.valueOf(number).intValue();
   }

   private static String cleanupQualifier(String modifier)
   {
      StringBuilder sb = new StringBuilder();
      cleanupQualifier(sb, modifier);
      return sb.toString();
   }

   private static void cleanupQualifier(StringBuilder result, String modifier)
   {
      for (int i = 0; i < modifier.length(); i++)
      {
         char c = modifier.charAt(i);
         if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c == '-')
            result.append(c);
         else
            result.append('_');
      }
   }
}
