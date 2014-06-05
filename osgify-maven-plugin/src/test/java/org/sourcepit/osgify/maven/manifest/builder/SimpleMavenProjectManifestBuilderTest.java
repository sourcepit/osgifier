/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder;

import javax.inject.Inject;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.plugin.testing.ArtifactStubFactory;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import org.apache.maven.project.MavenProject;
import org.eclipse.sisu.launch.InjectedTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sourcepit.common.manifest.osgi.BundleManifest;

public class SimpleMavenProjectManifestBuilderTest extends InjectedTest
{
   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();

   @Inject
   public ManifestBuilderFactory manifestBuilderFactory;

   private MavenProject mvnProjectMock;

   @Before
   public void setupMocks() throws Exception
   {
      mvnProjectMock = new MavenProjectStub();
      Artifact artifactMock = createArtifactMock("groupId", "artifactId", "1", null, "compile", "pom");
      mvnProjectMock.setArtifact(artifactMock);
   }

   @Test
   public void test() throws Exception
   {
      MavenProjectManifestBuilder builder = manifestBuilderFactory.forProject(mvnProjectMock);

      BundleManifest manifest = builder.attachSourceBundle(false).build().getBundleManifest();

      Assert.assertNotNull(manifest);
   }


   private Artifact createArtifactMock(String groupId, String artifactId, String version, String classifier,
      String scope, String type) throws Exception
   {
      ArtifactStubFactory artifactStubFactory = new ArtifactStubFactory(tempFolder.newFolder(groupId + artifactId
         + version), true);
      Artifact artifact = artifactStubFactory.createArtifact(groupId, artifactId, version, scope, type, classifier);
      artifact.setArtifactHandler(new DefaultArtifactHandler());
      return artifact;
   }


}
