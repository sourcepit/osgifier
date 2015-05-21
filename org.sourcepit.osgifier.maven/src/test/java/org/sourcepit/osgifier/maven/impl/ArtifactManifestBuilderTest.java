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
import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.inject.Inject;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.handler.ArtifactHandler;
import org.eclipse.sisu.launch.InjectedTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.artifact.ArtifactFactory;
import org.sourcepit.common.maven.artifact.ArtifactHandlerImpl;
import org.sourcepit.common.maven.artifact.MavenArtifactUtils;
import org.sourcepit.common.maven.model.util.MavenModelUtils;
import org.sourcepit.common.testing.Environment;
import org.sourcepit.common.testing.Workspace;
import org.sourcepit.osgifier.maven.ArtifactManifestBuilder;
import org.sourcepit.osgifier.maven.ArtifactManifestBuilderRequest;

import com.google.inject.Binder;
import com.google.inject.name.Names;

public class ArtifactManifestBuilderTest extends InjectedTest {
   private final Environment env = Environment.get("env-test.properties");

   @Rule
   public Workspace ws = newWorkspace();

   protected Workspace newWorkspace() {
      return new Workspace(new File(env.getBuildDir(), "ws"), false);
   }

   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();

   @Inject
   public ArtifactManifestBuilder manifestBuilder;

   private Artifact artifact;

   @Inject
   private ArtifactFactory artifactFactory;


   @Override
   public void configure(Binder binder) {
      super.configure(binder);
      binder.bind(ArtifactHandler.class).annotatedWith(Names.named("jar")).toInstance(new ArtifactHandlerImpl("jar"));
   }

   @Before
   public void setupMocks() throws Exception {
      final String groupId = "groupId";
      final String artifactId = "artifactId";

      org.eclipse.aether.artifact.Artifact aetherArtifact = artifactFactory.createArtifact(MavenModelUtils.toArtifactKey(
         groupId, artifactId, "jar", null, "1"));

      artifact = MavenArtifactUtils.toArtifact(aetherArtifact);
      artifact.setFile(ws.newDir(artifact.getArtifactId() + "-" + artifact.getVersion() + "." + artifact.getType()));
   }

   @Test
   public void test() {
      ArtifactManifestBuilderRequest request = new ArtifactManifestBuilderRequest();
      request.setArtifact(artifact);

      BundleManifest manifest = manifestBuilder.buildManifest(request).getBundleManifest();

      assertNotNull(manifest);
      assertEquals(1, manifest.getBundleVersion().getMajor());
      assertEquals("groupId.artifactId", manifest.getBundleSymbolicName().getSymbolicName());
   }

}
