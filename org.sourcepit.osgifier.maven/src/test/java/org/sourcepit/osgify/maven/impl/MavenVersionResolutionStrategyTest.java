/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

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
