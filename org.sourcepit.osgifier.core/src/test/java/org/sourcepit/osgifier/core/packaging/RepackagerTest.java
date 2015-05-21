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

package org.sourcepit.osgifier.core.packaging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.sourcepit.common.utils.file.FileUtils.deleteFileOrDirectory;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.inject.Inject;

import org.eclipse.sisu.launch.InjectedTest;
import org.junit.Rule;
import org.junit.Test;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.ManifestFactory;
import org.sourcepit.common.testing.Environment;
import org.sourcepit.common.testing.Workspace;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class RepackagerTest extends InjectedTest {
   protected Environment env = Environment.get("env-test.properties");

   @Rule
   public Workspace ws = new Workspace(env.getBuildDir(), "test-ws", false);

   @Inject
   private Repackager repackager;

   @Test
   public void testInjectManifest() throws Exception {
      final File jarFile = getResource("lib/stax-api-1.0.1.jar");
      assertTrue(jarFile.canRead());

      Manifest manifest = ManifestFactory.eINSTANCE.createManifest();
      manifest.setHeader("Foo", "bar");

      repackager.injectManifest(jarFile, manifest, null);

      JarFile jar = new JarFile(jarFile);

      assertEquals("bar", jar.getManifest().getMainAttributes().getValue("Foo"));
   }

   @Test
   public void testCopyJarAndInjectManifest() throws Exception {
      final File jarFile = getResource("lib/stax-api-1.0.1.jar");
      assertTrue(jarFile.canRead());

      Manifest manifest = ManifestFactory.eINSTANCE.createManifest();
      manifest.setHeader("Foo", "bar");

      File destJarFile = ws.newFile();
      deleteFileOrDirectory(destJarFile);

      repackager.copyJarAndInjectManifest(jarFile, destJarFile, manifest, null);

      JarFile jar = new JarFile(destJarFile);

      assertEquals("bar", jar.getManifest().getMainAttributes().getValue("Foo"));
   }

   public Environment getEnvironment() {
      return env;
   }

   protected Workspace getWs() {
      return ws;
   }

   protected File getResource(String path) throws IOException {
      File resources = getResourcesDir();
      File resource = new File(resources, path).getCanonicalFile();
      return ws.importFileOrDir(resource);
   }

   protected File getResourcesDir() {
      return getEnvironment().getResourcesDir();
   }

}
