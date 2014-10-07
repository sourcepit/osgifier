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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.maven.impl.MavenVersionResolutionStrategy;

public class MavenVersionResolutionStrategyTest
{
   @Test
   public void testDefault()
   {
      Map<String, String> mavenToOsgiVersion = new LinkedHashMap<String, String>();
      mavenToOsgiVersion.put(null, null);
      mavenToOsgiVersion.put("1", "1.0.0");
      mavenToOsgiVersion.put("1.1", "1.1.0");
      mavenToOsgiVersion.put("murks", "0.0.0.murks");
      mavenToOsgiVersion.put("1.v200192827", "1.0.0.v200192827");
      mavenToOsgiVersion.put("1-SNAPSHOT", "1.0.0.SNAPSHOT");
      mavenToOsgiVersion.put("1.0.SNAPSHOT", "1.0.0.SNAPSHOT");

      PropertiesSource options = new LinkedPropertiesMap();
      assertEquals(mavenToOsgiVersion, options);
   }

   @Test
   public void testForceContextQualifier()
   {
      Map<String, String> mavenToOsgiVersion = new LinkedHashMap<String, String>();
      mavenToOsgiVersion.put(null, null);
      mavenToOsgiVersion.put("1", "1.0.0.foo");
      mavenToOsgiVersion.put("1.1", "1.1.0.foo");
      mavenToOsgiVersion.put("murks", "0.0.0.foo-murks");
      mavenToOsgiVersion.put("1.v200192827", "1.0.0.foo-v200192827");
      mavenToOsgiVersion.put("1-SNAPSHOT", "1.0.0.foo-SNAPSHOT");
      mavenToOsgiVersion.put("1.0.SNAPSHOT", "1.0.0.foo-SNAPSHOT");

      PropertiesMap options = new LinkedPropertiesMap();
      options.put("osgifier.forceContextQualifier", "foo");
      assertEquals(mavenToOsgiVersion, options);
   }

   @Test
   public void testForceContextQualifierTrimMavenQualifier()
   {
      Map<String, String> mavenToOsgiVersion = new LinkedHashMap<String, String>();
      mavenToOsgiVersion.put(null, null);
      mavenToOsgiVersion.put("1", "1.0.0.foo");
      mavenToOsgiVersion.put("1.1", "1.1.0.foo");
      mavenToOsgiVersion.put("murks", "0.0.0.foo");
      mavenToOsgiVersion.put("1.v200192827", "1.0.0.foo");
      mavenToOsgiVersion.put("1-SNAPSHOT", "1.0.0.foo");
      mavenToOsgiVersion.put("1.0.SNAPSHOT", "1.0.0.foo");

      PropertiesMap options = new LinkedPropertiesMap();
      options.put("osgifier.forceContextQualifier", "foo");
      options.put("osgifier.saveMavenQualifier", "false");
      assertEquals(mavenToOsgiVersion, options);
   }

   private void assertEquals(Map<String, String> mavenToOsgiVersion, PropertiesSource options)
   {
      MavenArtifact mavenArtifact = MavenModelFactory.eINSTANCE.createMavenArtifact();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      bundleCandidate.addExtension(mavenArtifact);

      for (Entry<String, String> entry : mavenToOsgiVersion.entrySet())
      {
         mavenArtifact.setVersion(entry.getKey());
         Version version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate, options);
         assertThat(version == null ? null : version.toString(), IsEqual.equalTo(entry.getValue()));
      }
   }

}
