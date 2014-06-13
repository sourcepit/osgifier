/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.plugin.testing.ArtifactStubFactory;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.apache.maven.project.MavenProject;
import org.eclipse.sisu.launch.InjectedTest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sourcepit.common.manifest.osgi.BundleManifest;

public class MavenProjectManifestBuilderTest extends InjectedTest
{
   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();

   @Inject
   public MavenProjectManifestBuilder manifestBuilder;

   private MavenProject mvnProjectMock;

   @Before
   public void setupMocks() throws Exception
   {
      mvnProjectMock = new MavenProjectStub();
      final String groupId = "groupId";
      final String artifactId = "artifactId";
      Artifact artifactMock = createArtifactMock(groupId, artifactId, "1", "jar", "compile", "pom");
      mvnProjectMock.setArtifact(artifactMock);
      mvnProjectMock.setGroupId(groupId);
      mvnProjectMock.setArtifactId(artifactId);
   }

   @Test
   public void test()
   {
      BundleManifest manifest = manifestBuilder.project(mvnProjectMock).withSourceBundleManifest(false).build()
         .getBundleManifest();

      assertNotNull(manifest);
      assertEquals(1, manifest.getBundleVersion().getMajor());
      assertEquals("groupId.artifactId", manifest.getBundleSymbolicName().getSymbolicName());
   }

   private Artifact createArtifactMock(String groupId, String artifactId, String version, String classifier,
      String scope, String type) throws Exception
   {
      ArtifactStubFactory artifactStubFactory = new ArtifactStubFactory(tempFolder.newFolder(groupId, artifactId,
         version), true);
      Artifact artifact = artifactStubFactory.createArtifact(groupId, artifactId, version, scope, type, classifier);
      artifact.setArtifactHandler(new DefaultArtifactHandler());
      return artifact;
   }


}
