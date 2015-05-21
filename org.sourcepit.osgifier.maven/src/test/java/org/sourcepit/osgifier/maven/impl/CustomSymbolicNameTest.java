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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;

public class CustomSymbolicNameTest {

   @Test
   public void testWithClassifier() {
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
   public void testWithoutClassifier() {
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
