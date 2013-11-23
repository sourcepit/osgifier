/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.maven.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.core.model.context.ContextModelFactory;

public class CustomSymbolicNameTest
{

   @Test
   public void testWithClassifier()
   {
      CustomSymbolicName strategy = new CustomSymbolicName();

      MavenArtifact artifact = MavenModelFactory.eINSTANCE.createMavenArtifact();
      artifact.setGroupId("group");
      artifact.setArtifactId("artifact");
      artifact.setType("type");
      artifact.setClassifier("classifier");
      artifact.setVersion("version");

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.addExtension(artifact);

      final PropertiesMap options = new LinkedPropertiesMap();

      String name = strategy.resolveSymbolicName(bundle, options);
      assertEquals(null, name);

      options.put("osgifier.symbolicNameMappings", "group:artifact:type:classifier:version=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("foo", name);

      options.put("osgifier.symbolicNameMappings",
         "group:artifact:type:classifier:version=bar,group:artifact:type:classifier:version=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("bar", name);

      options.put("osgifier.symbolicNameMappings",
         "group:artifact:type:classifier=bar,group:artifact:type:classifier:version=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("foo", name);

      options.put("osgifier.symbolicNameMappings", "group:artifact:type:classifier=bar,group:artifact=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("bar", name);
   }

   @Test
   public void testWithoutClassifier()
   {
      CustomSymbolicName strategy = new CustomSymbolicName();

      MavenArtifact artifact = MavenModelFactory.eINSTANCE.createMavenArtifact();
      artifact.setGroupId("group");
      artifact.setArtifactId("artifact");
      artifact.setType("type");
      artifact.setVersion("version");

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.addExtension(artifact);

      final PropertiesMap options = new LinkedPropertiesMap();

      String name = strategy.resolveSymbolicName(bundle, options);
      assertEquals(null, name);

      options.put("osgifier.symbolicNameMappings", "group:artifact:type:version=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("foo", name);

      options.put("osgifier.symbolicNameMappings", "group:artifact:type:version=bar,group:artifact:type:version=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("bar", name);

      options.put("osgifier.symbolicNameMappings", "group:artifact:type=bar,group:artifact:type:version=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("foo", name);

      options.put("osgifier.symbolicNameMappings", "group:artifact:type=bar,group:artifact=foo");

      name = strategy.resolveSymbolicName(bundle, options);
      assertEquals("bar", name);
   }

}
