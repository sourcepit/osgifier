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
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;

public class MavenVersionResolutionStrategyTest
{
   @Test
   public void test()
   {
      MavenArtifact mavenArtifact = MavenModelFactory.eINSTANCE.createMavenArtifact();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      bundleCandidate.addExtension(mavenArtifact);

      Version version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate);
      assertThat(version, IsNull.nullValue());

      mavenArtifact.setVersion("1");
      version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate);
      assertThat(version.toString(), IsEqual.equalTo("1.0.0"));

      mavenArtifact.setVersion("1.1");
      version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate);
      assertThat(version.toString(), IsEqual.equalTo("1.1.0"));

      mavenArtifact.setVersion("murks");
      version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate);
      assertThat(version.toString(), IsEqual.equalTo("0.0.0.murks"));

      mavenArtifact.setVersion("1.v200192827");
      version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate);
      assertThat(version.toString(), IsEqual.equalTo("1.0.0.v200192827"));

      mavenArtifact.setVersion("1-SNAPSHOT");
      version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate);
      assertThat(version.toString(), IsEqual.equalTo("1.0.0.SNAPSHOT"));

      mavenArtifact.setVersion("1.0.SNAPSHOT");
      version = new MavenVersionResolutionStrategy().resolveVersion(bundleCandidate);
      assertThat(version.toString(), IsEqual.equalTo("1.0.0.SNAPSHOT"));
   }

}
