/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import static org.junit.Assert.assertThat;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.osgify.context.BundleCandidate;
import org.sourcepit.osgify.context.ContextModelFactory;
import org.sourcepit.osgify.java.JavaArchive;
import org.sourcepit.osgify.java.JavaModelFactory;

public class MavenGavToSymbolicNameMappingStrategyTest
{
   @Test
   public void testResolveSymbolicName()
   {
      MavenArtifact mavenArtifact = MavenModelFactory.eINSTANCE.createMavenArtifact();
      
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      bundleCandidate.addExtension(mavenArtifact);
      
      mavenArtifact.setGroupId("commons-io");
      mavenArtifact.setArtifactId("commons-io");

      String name = new MavenGavToSymbolicNameMappingStrategy().resolveSymbolicName(bundleCandidate);
      assertThat(name, IsNull.nullValue());
      
      mavenArtifact.setGroupId("stax");
      mavenArtifact.setArtifactId("stax-api");

      name = new MavenGavToSymbolicNameMappingStrategy().resolveSymbolicName(bundleCandidate);
      assertThat(name, IsEqual.equalTo("javax.xml.stax.api"));
   }
}
