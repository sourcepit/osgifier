/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;

public class DefaultOsgifyContextInflatorFilterTest
{

   @Test
   public void testIsOverrideNativeBundle()
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();

      BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      manifest.setBundleSymbolicName("foo");

      PropertiesMap options = new LinkedPropertiesMap();

      OsgifyContextInflatorFilter filter = new DefaultOsgifyContextInflatorFilter();

      boolean override = filter.isAppendNativeManifest(bundle, manifest, options);
      assertTrue(override);

      options.put("osgifier.overrideNativeBundles", "foo");
      override = filter.isAppendNativeManifest(bundle, manifest, options);
      assertFalse(override);

      options.put("osgifier.overrideNativeBundles", "false");
      override = filter.isAppendNativeManifest(bundle, manifest, options);
      assertTrue(override);

      options.put("osgifier.overrideNativeBundles", "true");
      override = filter.isAppendNativeManifest(bundle, manifest, options);
      assertFalse(override);

      MavenArtifact artifact = MavenModelFactory.eINSTANCE.createMavenArtifact();
      artifact.setGroupId("foo");
      artifact.setArtifactId("foo");
      artifact.setVersion("1");

      bundle.addExtension(artifact);

      options.put("osgifier.overrideNativeBundles", "foo:foo:jar");
      override = filter.isAppendNativeManifest(bundle, manifest, options);
      assertFalse(override);
   }

}
