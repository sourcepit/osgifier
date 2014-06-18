/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.bundle;

import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Named;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.utils.path.PathMatcher;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.util.OptionsUtils;

import com.google.common.base.Strings;

@Named
public class RecommendedImportPolicyAppender
{
   public void append(@NotNull BundleCandidate bundle, PropertiesSource options)
   {
      final BundleManifest manifest = bundle.getManifest();
      final VersionRangePolicy[] policies = getRecommendedImportPoliciesFromOptions(manifest, options);
      if (policies != null)
      {
         final StringBuilder value = new StringBuilder();
         value.append(policies[0].literal());
         if (policies[1] != policies[0])
         {
            value.append(", ");
            value.append(policies[1].literal());
         }
         manifest.setHeader("Osgifier-RecommendedImportPolicy", value.toString());
      }
   }

   static VersionRangePolicy[] getRecommendedImportPoliciesFromHeader(BundleManifest manifest)
   {
      final String policies = manifest.getHeaderValue("Osgifier-RecommendedImportPolicy");
      if (Strings.isNullOrEmpty(policies))
      {
         return null;
      }
      return toPolicies(policies, ",");
   }

   static VersionRangePolicy[] getRecommendedImportPoliciesFromOptions(BundleManifest manifest, PropertiesSource options)
   {
      final String mapValue = options.get("osgifier.recommendedImportPolicies");
      if (Strings.isNullOrEmpty(mapValue))
      {
         return null;
      }

      final String symbolicName = manifest.getBundleSymbolicName().getSymbolicName();

      final Map<String, String> symbolicNameToPolicies = OptionsUtils.parseMapValue(mapValue);

      final String policies = getPolicies(symbolicNameToPolicies, symbolicName);
      if (Strings.isNullOrEmpty(policies))
      {
         return null;
      }
      return toPolicies(policies, "\\|");
   }

   private static VersionRangePolicy[] toPolicies(final String policyNameString, String sep)
   {
      final String[] policyNames = policyNameString.split(sep);

      final VersionRangePolicy[] result = new VersionRangePolicy[2];
      result[0] = VersionRangePolicy.parse(policyNames[0].trim());
      if (policyNames.length == 1)
      {
         result[1] = result[0];
      }
      else if (policyNames.length == 2)
      {
         result[1] = VersionRangePolicy.parse(policyNames[1].trim());
      }
      else
      {
         throw new IllegalArgumentException("Invalid import policy: " + policyNameString);
      }
      return result;
   }

   private static String getPolicies(final Map<String, String> symbolicNameToPolicies, final String symbolicName)
   {
      final String policies = symbolicNameToPolicies.get(symbolicName);
      if (!Strings.isNullOrEmpty(policies))
      {
         return policies;
      }

      for (Entry<String, String> entry : symbolicNameToPolicies.entrySet())
      {
         if (PathMatcher.parsePackagePatterns(entry.getKey()).isMatch(symbolicName))
         {
            return entry.getValue();
         }
      }

      return null;
   }
}
