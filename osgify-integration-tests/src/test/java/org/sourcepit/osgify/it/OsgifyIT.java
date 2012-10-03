/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.it;

import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelPackage;
import org.sourcepit.common.maven.model.MavenProject;
import org.sourcepit.common.maven.testing.ExternalMavenTest;
import org.sourcepit.common.testing.Environment;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelPackage;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaProject;

public class OsgifyIT extends ExternalMavenTest
{
   private final static boolean DEBUG = false;

   @Override
   protected boolean isDebug()
   {
      return DEBUG;
   }

   @Override
   protected Environment newEnvironment()
   {
      return Environment.get("osgiy-its.properties");
   }

   @Test
   public void testOsgify() throws Exception
   {
      executeBuild("osgify-test");
   }

   @Test
   public void testReactor() throws Exception
   {
      final File projectDir = executeBuild("reactor-it");

      OsgifyContext bundleModel = loadModel(new File(projectDir, "bundle/target/osgify-context.xml"));
      assertThat(bundleModel.getBundles().size(), Is.is(1));

      OsgifyContext testsModel = loadModel(new File(projectDir, "bundle-tests/target/osgify-context.xml"));
      assertThat(testsModel.getBundles().size(), Is.is(4));

      BundleCandidate bundleCandidate;
      BundleManifest manifest;
      MavenProject mavenProject;
      MavenArtifact mavenArtifact;

      bundleCandidate = testsModel.getBundles().get(0);
      mavenProject = bundleCandidate.getExtension(MavenProject.class);
      mavenArtifact = bundleCandidate.getExtension(MavenArtifact.class);
      manifest = bundleCandidate.getManifest();
      assertThat(bundleCandidate.getContent(), IsInstanceOf.instanceOf(JavaProject.class));
      assertThat(mavenProject, IsNull.notNullValue());
      assertThat(mavenArtifact, IsNull.nullValue());
      assertThat(mavenProject.getArtifactId(), IsEqual.equalTo("bundle-tests"));
      assertThat(manifest, IsNull.notNullValue());
      assertThat(manifest.getBundleSymbolicName().getSymbolicName(), IsEqual.equalTo("org.sourcepit.test.bundle.tests"));

      bundleCandidate = testsModel.getBundles().get(1);
      mavenProject = bundleCandidate.getExtension(MavenProject.class);
      mavenArtifact = bundleCandidate.getExtension(MavenArtifact.class);
      manifest = bundleCandidate.getManifest();
      assertThat(bundleCandidate.getContent(), IsInstanceOf.instanceOf(JavaProject.class));
      assertThat(mavenProject, IsNull.notNullValue());
      assertThat(mavenArtifact, IsNull.nullValue());
      assertThat(mavenProject.getArtifactId(), IsEqual.equalTo("bundle"));
      assertThat(manifest, IsNull.notNullValue());
      assertThat(manifest.getBundleSymbolicName().getSymbolicName(), IsEqual.equalTo("org.sourcepit.test.bundle"));

      bundleCandidate = testsModel.getBundles().get(2);
      mavenProject = bundleCandidate.getExtension(MavenProject.class);
      mavenArtifact = bundleCandidate.getExtension(MavenArtifact.class);
      manifest = bundleCandidate.getManifest();
      assertThat(bundleCandidate.getContent(), IsInstanceOf.instanceOf(JavaArchive.class));
      assertThat(mavenProject, IsNull.nullValue());
      assertThat(mavenArtifact, IsNull.notNullValue());
      assertThat(mavenArtifact.getArtifactId(), IsEqual.equalTo("junit"));
      assertThat(manifest, IsNull.notNullValue());
      assertThat(manifest.getBundleSymbolicName().getSymbolicName(), IsEqual.equalTo("org.junit"));

      bundleCandidate = testsModel.getBundles().get(3);
      mavenProject = bundleCandidate.getExtension(MavenProject.class);
      mavenArtifact = bundleCandidate.getExtension(MavenArtifact.class);
      manifest = bundleCandidate.getManifest();
      assertThat(bundleCandidate.getContent(), IsInstanceOf.instanceOf(JavaArchive.class));
      assertThat(mavenProject, IsNull.nullValue());
      assertThat(mavenArtifact, IsNull.notNullValue());
      assertThat(mavenArtifact.getArtifactId(), IsEqual.equalTo("hamcrest-core"));
      assertThat(manifest, IsNull.notNullValue());
      assertThat(manifest.getBundleSymbolicName().getSymbolicName(), IsEqual.equalTo("org.hamcrest.core"));
   }

   protected File executeBuild(String projectName) throws IOException
   {
      final File projectDir = getResource(projectName);
      build(projectDir, "-B", "-e", "clean", "package");
      return projectDir;
   }

   protected OsgifyContext loadModel(File file) throws IOException
   {
      MavenModelPackage.eINSTANCE.eClass();
      ContextModelPackage.eINSTANCE.eClass();
      Resource resource = new XMLResourceImpl(URI.createFileURI(file.getAbsolutePath()));
      resource.load(null);

      OsgifyContext ctx = (OsgifyContext) resource.getContents().get(0);
      return ctx;
   }
}
