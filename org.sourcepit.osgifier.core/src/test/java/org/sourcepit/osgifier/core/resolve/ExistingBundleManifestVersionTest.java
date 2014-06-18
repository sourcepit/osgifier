/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.resolve;

import static org.junit.Assert.*;

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
import org.sourcepit.osgifier.core.resolve.ExistingBundleManifestVersion;

public class ExistingBundleManifestVersionTest
{
   @Test
   public void test()
   {
      PropertiesMap options = new LinkedPropertiesMap();
      
      resolveAndAssert("1.1.1.foo", "1.1.1.foo", options);
      resolveAndAssert("1.1.1", "1.1.1", options);
      
      options.put("osgifier.forceContextQualifier", "murks");
      
      resolveAndAssert("1.1.1.foo", "1.1.1.murks", options);
      resolveAndAssert("1.1.1", "1.1.1.murks", options);
   }

   private static void resolveAndAssert(String origin, String expected, PropertiesMap options)
   {
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
