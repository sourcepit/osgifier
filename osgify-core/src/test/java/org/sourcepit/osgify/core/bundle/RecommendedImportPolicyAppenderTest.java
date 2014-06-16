/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;

public class RecommendedImportPolicyAppenderTest
{

   @Test
   public void testGetRecommendedImportPolicies()
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setSymbolicName("org.sourcepit.foo");

      PropertiesMap options = new LinkedPropertiesMap();

      VersionRangePolicy[] policies = RecommendedImportPolicyAppender.getRecommendedImportPolicies(bundle, options);
      assertNull(policies);

      try
      {
         options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo=bar");
         RecommendedImportPolicyAppender.getRecommendedImportPolicies(bundle, options);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }

      try
      {
         options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo=perfect|compatible|any");
         RecommendedImportPolicyAppender.getRecommendedImportPolicies(bundle, options);
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }

      options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo = perfect ");
      policies = RecommendedImportPolicyAppender.getRecommendedImportPolicies(bundle, options);
      assertEquals(VersionRangePolicy.PERFECT, policies[0]);
      assertEquals(VersionRangePolicy.PERFECT, policies[1]);

      options.put("osgifier.recommendedImportPolicies", "org.sourcepit.foo = perfect| greaterOrEqual ");
      policies = RecommendedImportPolicyAppender.getRecommendedImportPolicies(bundle, options);
      assertEquals(VersionRangePolicy.PERFECT, policies[0]);
      assertEquals(VersionRangePolicy.GREATER_OR_EQUAL, policies[1]);

      options.clear();
      options.put("osgifier.recommendedImportPolicies", "org.** = perfect| any ");
      policies = RecommendedImportPolicyAppender.getRecommendedImportPolicies(bundle, options);
      assertEquals(VersionRangePolicy.PERFECT, policies[0]);
      assertEquals(VersionRangePolicy.ANY, policies[1]);
   }

   @Test
   public void testGetRecommendedImportPolicyHeader()
   {
      final RecommendedImportPolicyAppender appender = new RecommendedImportPolicyAppender();

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setSymbolicName("org.sourcepit.foo");
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());

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
