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

package org.sourcepit.osgifier.maven;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.path.PathMatcher;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

public class DefaultOsgifierContextInflatorFilter extends AbstractOsgifierContextInflatorFilter
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
