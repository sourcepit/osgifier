/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

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
import org.sourcepit.osgify.maven.ArtifactManifestBuilder;
import org.sourcepit.osgify.maven.ArtifactManifestBuilderRequest;

import com.google.inject.Binder;
import com.google.inject.name.Names;

public class ArtifactManifestBuilderTest extends InjectedTest
{
   private final Environment env = Environment.get("env-test.properties");

   @Rule
   public Workspace ws = newWorkspace();

   protected Workspace newWorkspace()
   {
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
   public void configure(Binder binder)
   {
      super.configure(binder);
      binder.bind(ArtifactHandler.class).annotatedWith(Names.named("jar")).toInstance(new ArtifactHandlerImpl("jar"));
   }

   @Before
   public void setupMocks() throws Exception
   {
      final String groupId = "groupId";
      final String artifactId = "artifactId";

      org.eclipse.aether.artifact.Artifact aetherArtifact = artifactFactory.createArtifact(MavenModelUtils
         .toArtifactKey(groupId, artifactId, "jar", null, "1"));

      artifact = MavenArtifactUtils.toArtifact(aetherArtifact);
      artifact.setFile(ws.newDir(artifact.getArtifactId() + "-" + artifact.getVersion() + "." + artifact.getType()));
   }

   @Test
   public void test()
   {
      ArtifactManifestBuilderRequest request = new ArtifactManifestBuilderRequest();
      request.setArtifact(artifact);
      
      BundleManifest manifest = manifestBuilder.buildManifest(request).getBundleManifest();

      assertNotNull(manifest);
      assertEquals(1, manifest.getBundleVersion().getMajor());
      assertEquals("groupId.artifactId", manifest.getBundleSymbolicName().getSymbolicName());
   }

}
