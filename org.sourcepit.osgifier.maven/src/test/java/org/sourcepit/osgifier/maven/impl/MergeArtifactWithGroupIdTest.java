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

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;

/**
 * @author Bernd
 */
public class MergeArtifactWithGroupIdTest {

   @Test
   public void test() {
      MavenArtifact mavenArtifact = MavenModelFactory.eINSTANCE.createMavenArtifact();

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      bundleCandidate.addExtension(mavenArtifact);

      mavenArtifact.setGroupId("commons-io");
      mavenArtifact.setArtifactId("commons-io");

      String name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsNull.nullValue());

      mavenArtifact.setGroupId("org.junit");
      mavenArtifact.setArtifactId("junit");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsNull.nullValue());

      mavenArtifact.setGroupId("org.osgi");
      mavenArtifact.setArtifactId("org.osgi.core");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.osgi.core"));

      mavenArtifact.setGroupId("org.aspectj");
      mavenArtifact.setArtifactId("aspectjrt");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.aspectj.rt"));

      mavenArtifact.setGroupId("org.hamcrest");
      mavenArtifact.setArtifactId("hamcrest-core");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.hamcrest.core"));

      mavenArtifact.setGroupId("org.hamcrest");
      mavenArtifact.setArtifactId("hamcrest_core");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.hamcrest.core"));

      mavenArtifact.setGroupId("org.hamcrest");
      mavenArtifact.setArtifactId("hamcrest.core");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.hamcrest.core"));

      mavenArtifact.setGroupId("org.hamcrest");
      mavenArtifact.setArtifactId("hamcrest._-core");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.hamcrest.core"));

      mavenArtifact.setGroupId("org.foo");
      mavenArtifact.setArtifactId("bar");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.foo.bar"));

      mavenArtifact.setGroupId("org.sourcepit.common");
      mavenArtifact.setArtifactId("common-manifest");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.sourcepit.common.manifest"));

      mavenArtifact.setGroupId("org.sourcepit.tools");
      mavenArtifact.setArtifactId("osgifier-core");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.sourcepit.tools.osgifier.core"));

      mavenArtifact.setGroupId("org.sourcepit-tools");
      mavenArtifact.setArtifactId("osgifier_maven-plugin");

      name = new MergeArtifactWithGroupId().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, IsEqual.equalTo("org.sourcepit.tools.osgifier.maven.plugin"));
   }

}
