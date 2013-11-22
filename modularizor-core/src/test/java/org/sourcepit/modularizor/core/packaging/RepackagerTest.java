/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.packaging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.ManifestFactory;
import org.sourcepit.common.testing.Environment;
import org.sourcepit.common.testing.Workspace;
import org.sourcepit.guplex.test.GuplexTest;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class RepackagerTest extends GuplexTest
{
   protected Environment env = Environment.get("env-test.properties");

   @Rule
   public Workspace ws = new Workspace(env.getBuildDir(), "test-ws", false);

   @Inject
   private Repackager repackager;

   @Test
   public void testInjectManifest() throws Exception
   {
      final File jarFile = getResource("lib/stax-api-1.0.1.jar");
      assertTrue(jarFile.canRead());

      Manifest manifest = ManifestFactory.eINSTANCE.createManifest();
      manifest.setHeader("Foo", "bar");

      repackager.injectManifest(jarFile, manifest);

      JarFile jar = new JarFile(jarFile);

      assertEquals("bar", jar.getManifest().getMainAttributes().getValue("Foo"));
   }

   @Test
   public void testCopyJarAndInjectManifest() throws Exception
   {
      final File jarFile = getResource("lib/stax-api-1.0.1.jar");
      assertTrue(jarFile.canRead());

      Manifest manifest = ManifestFactory.eINSTANCE.createManifest();
      manifest.setHeader("Foo", "bar");

      File destJarFile = ws.newFile();
      FileUtils.forceDelete(destJarFile);

      repackager.copyJarAndInjectManifest(jarFile, destJarFile, manifest);

      JarFile jar = new JarFile(destJarFile);

      assertEquals("bar", jar.getManifest().getMainAttributes().getValue("Foo"));
   }

   public Environment getEnvironment()
   {
      return env;
   }

   protected Workspace getWs()
   {
      return ws;
   }

   protected File getResource(String path) throws IOException
   {
      File resources = getResourcesDir();
      File resource = new File(resources, path).getCanonicalFile();
      return ws.importFileOrDir(resource);
   }

   protected File getResourcesDir()
   {
      return getEnvironment().getResourcesDir();
   }

}
