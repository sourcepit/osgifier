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

package org.sourcepit.osgifier.maven.impl;

import java.util.Map;

import javax.inject.Named;

import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.resolve.AbstractSymbolicNameResolutionStrategy;
import org.sourcepit.osgifier.core.util.OptionsUtils;

@Named("CustomSymbolicName")
public class CustomSymbolicName extends AbstractSymbolicNameResolutionStrategy {
   @Override
   public Priority getPriority() {
      return Priority.MAXIMUM;
   }

   @Override
   public boolean isUnambiguous() {
      return true;
   }

   @Override
   public String resolveSymbolicName(BundleCandidate bundleCandidate, PropertiesSource options) {
      final MavenArtifact artifact = bundleCandidate.getExtension(MavenArtifact.class);
      if (artifact != null) {
         return resolveSymbolicName(artifact.getArtifactKey(), options);
      }
      return null;
   }

   private String resolveSymbolicName(ArtifactKey artifact, PropertiesSource options) {
      final String rawMappings = options.get("osgifier.symbolicNameMappings");
      if (rawMappings != null) {
         final Map<String, String> mappings = OptionsUtils.parseMapValue(rawMappings);
         String artifactKey = artifact.toString();
         String name = mappings.get(artifactKey);
         if (name == null) {
            artifactKey = artifactKey.substring(0, artifactKey.lastIndexOf(":"));
            name = mappings.get(artifactKey);
         }
         return name;
      }

      return null;
   }
}
