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
