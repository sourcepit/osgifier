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

package org.sourcepit.osgifier.core.resolve;

import static org.junit.Assert.assertEquals;

import java.util.jar.JarFile;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;

public class ExistingBundleManifestVersionTest {
   @Test
   public void test() {
      PropertiesMap options = new LinkedPropertiesMap();

      resolveAndAssert("1.1.1.foo", "1.1.1.foo", options);
      resolveAndAssert("1.1.1", "1.1.1", options);

      options.put("osgifier.forceContextQualifier", "murks");

      resolveAndAssert("1.1.1.foo", "1.1.1.murks", options);
      resolveAndAssert("1.1.1", "1.1.1.murks", options);
   }

   private static void resolveAndAssert(String origin, String expected, PropertiesMap options) {
      BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      manifest.setBundleVersion(origin);

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      File file = jArchive.getFile(JarFile.MANIFEST_NAME, true);
      file.addExtension(manifest);

      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setContent(jArchive);

      Version result = new ExistingBundleManifestVersion().resolveVersion(bundle, options);
      assertEquals(Version.parse(expected), result);
   }
}
