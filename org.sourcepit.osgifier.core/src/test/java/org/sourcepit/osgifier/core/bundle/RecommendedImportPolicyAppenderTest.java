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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;

public class RecommendedImportPolicyAppenderTest {

   @Test
   public void testGetRecommendedImportPoliciesFromHeader() {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());

      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleSymbolicName("org.sourcepit.foo");

      VersionRangePolicy[] policies;
      policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromHeader(manifest);
      assertNull(policies);

      manifest.setHeader("Osgifier-RecommendedImportPolicy", "perfect");

      policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromHeader(manifest);
      assertEquals(VersionRangePolicy.PERFECT, policies[0]);
      assertEquals(VersionRangePolicy.PERFECT, policies[1]);

      manifest.setHeader("Osgifier-RecommendedImportPolicy", "any, strict");

      policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromHeader(manifest);
      assertEquals(VersionRangePolicy.ANY, policies[0]);
      assertEquals(VersionRangePolicy.STRICT, policies[1]);

      try {
         manifest.setHeader("Osgifier-RecommendedImportPolicy", "any, strict, perfect");
         RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromHeader(manifest);
         fail();
      }
      catch (IllegalArgumentException e) {
      }

      try {
         manifest.setHeader("Osgifier-RecommendedImportPolicy", "foo");
         RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromHeader(manifest);
         fail();
      }
      catch (IllegalArgumentException e) {
      }
   }

   @Test
   public void testGetRecommendedImportPoliciesFromOptions() {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());

      BundleManifest manifest = bundle.getManifest();
      manifest.setBundleSymbolicName("org.sourcepit.foo");

      PropertiesMap options = new LinkedPropertiesMap();

      VersionRangePolicy[] policies;
      policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromOptions(manifest, options);
      assertNull(policies);

      try {
         options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo=bar");
         RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromOptions(manifest, options);
         fail();
      }
      catch (IllegalArgumentException e) {
      }

      try {
         options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo=perfect|compatible|any");
         RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromOptions(manifest, options);
         fail();
      }
      catch (IllegalArgumentException e) {
      }

      options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo = perfect ");
      policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromOptions(manifest, options);
      assertEquals(VersionRangePolicy.PERFECT, policies[0]);
      assertEquals(VersionRangePolicy.PERFECT, policies[1]);

      options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo = perfect| greaterOrEqual ");
      policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromOptions(manifest, options);
      assertEquals(VersionRangePolicy.PERFECT, policies[0]);
      assertEquals(VersionRangePolicy.GREATER_OR_EQUAL, policies[1]);

      options.clear();
      options.put("osgifier.recommendedImportPolicies", "org.** = perfect| any ");
      policies = RecommendedImportPolicyAppender.getRecommendedImportPoliciesFromOptions(manifest, options);
      assertEquals(VersionRangePolicy.PERFECT, policies[0]);
      assertEquals(VersionRangePolicy.ANY, policies[1]);
   }

   @Test
   public void testGetRecommendedImportPolicyHeader() {
      final RecommendedImportPolicyAppender appender = new RecommendedImportPolicyAppender();

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      bundle.getManifest().setBundleSymbolicName("org.sourcepit.foo");

      PropertiesMap options = new LinkedPropertiesMap();
      appender.append(bundle, options);
      assertNull(bundle.getManifest().getHeader("Osgifier-RecommendedImportPolicy"));

      options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo=perfect");
      appender.append(bundle, options);
      assertEquals("perfect", bundle.getManifest().getHeaderValue("Osgifier-RecommendedImportPolicy"));

      options.clear();
      options.put("osgifier.recommendedImportPolicies", "org.** = perfect| any ");
      appender.append(bundle, options);
      assertEquals("perfect, any", bundle.getManifest().getHeaderValue("Osgifier-RecommendedImportPolicy"));

   }
}
