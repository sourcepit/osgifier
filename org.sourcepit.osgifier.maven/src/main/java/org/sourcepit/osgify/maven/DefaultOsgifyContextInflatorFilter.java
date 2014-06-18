/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.path.PathMatcher;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

public class DefaultOsgifyContextInflatorFilter extends AbstractOsgifyContextInflatorFilter
{
   @Override
   public boolean isAppendNativeManifest(BundleCandidate bundle, BundleManifest manifest, PropertiesSource options)
   {
      return !isOverrideNativeBundle(bundle, manifest, options);
   }

   private boolean isOverrideNativeBundle(BundleCandidate bundle, BundleManifest manifest, PropertiesSource options)
   {
      final String pattern = options.get("osgifier.overrideNativeBundles", Boolean.FALSE.toString()).trim();
      if (Boolean.FALSE.toString().equals(pattern))
      {
         return false;
      }
      else if (Boolean.TRUE.toString().equals(pattern))
      {
         return true;
      }
      else
      {
         final MavenArtifact artifact = bundle.getExtension(MavenArtifact.class);
         if (artifact != null && isMatch(pattern, artifact))
         {
            return true;
         }
         else
         {
            final PathMatcher matcher = PathMatcher.parsePackagePatterns(pattern);
            return matcher.isMatch(manifest.getBundleSymbolicName().getSymbolicName());
         }
      }
   }

   private boolean isMatch(String pattern, MavenArtifact artifact)
   {
      final String artifactKey = artifact.getArtifactKey().toString();
      final String versionLessArtifactKey = artifactKey.substring(0, artifactKey.lastIndexOf(":"));

      for (String segment : pattern.split(","))
      {
         final String key = segment.trim();
         if (key.equals(artifactKey) || key.equals(versionLessArtifactKey))
         {
            return true;
         }
      }
      return false;
   }
}
