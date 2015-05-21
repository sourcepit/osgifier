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

import javax.inject.Named;

import org.sourcepit.common.maven.model.MavenArtifactConflictCoordinates;
import org.sourcepit.common.maven.model.MavenArtifactCoordinates;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.resolve.AbstractSymbolicNameResolutionStrategy;

/**
 * @author Bernd
 */
@Named("MergeArtifactWithGroupId")
public class MergeArtifactWithGroupId extends AbstractSymbolicNameResolutionStrategy {
   public Priority getPriority() {
      return Priority.LOW;
   }

   @Override
   public String resolveSymbolicName(BundleCandidate bundleCandidate, PropertiesSource options) {
      final MavenArtifactConflictCoordinates mavenArtifact = bundleCandidate.getExtension(MavenArtifactConflictCoordinates.class);
      if (mavenArtifact != null) {
         final String groupId = mavenArtifact.getGroupId();
         if (groupId != null) {
            final String artifactId = mavenArtifact.getArtifactId();
            if (artifactId != null) {
               String symbolicName = resolveSymbolicName(groupId, artifactId);
               if (symbolicName != null && mavenArtifact instanceof MavenArtifactCoordinates) {
                  final MavenArtifactCoordinates gav = (MavenArtifactCoordinates) mavenArtifact;

                  String classifier = gav.getClassifier();
                  if ("java-source".equals(gav.getType()) && (classifier == null || "sources".equals(classifier))) {
                     classifier = "source";
                  }

                  if (classifier != null) {
                     symbolicName = resolveSymbolicName(symbolicName, classifier);
                  }
               }
               return beautify(symbolicName);
            }
         }
      }
      return null;
   }

   private String beautify(String symbolicName) {
      if (symbolicName == null) {
         return null;
      }
      final StringBuilder sb = new StringBuilder();
      for (char c : symbolicName.toCharArray()) {
         switch (c) {
            case '-' :
            case '_' :
               sb.append('.');
               break;
            default :
               sb.append(c);
               break;
         }
      }
      return sb.toString();
   }

   private String resolveSymbolicName(String groupId, String artifactId) {
      final String[] segments = groupId.split("\\.");
      if (segments.length > 1) {
         final StringBuilder sb = new StringBuilder();
         sb.append(groupId);

         String idPrefix = groupId;
         if (!artifactId.startsWith(idPrefix)) {
            idPrefix = segments[segments.length - 1];
         }

         if (artifactId.startsWith(idPrefix)) {
            final String appendix = artifactId.substring(idPrefix.length());
            boolean trim = true;
            for (char c : appendix.toCharArray()) {
               switch (c) {
                  case '-' :
                  case '_' :
                  case '.' :
                     if (trim) {
                        break;
                     }
                  default :
                     if (trim) {
                        sb.append('.');
                     }
                     trim = false;
                     sb.append(c);
                     break;
               }
            }
            if (sb.length() == groupId.length()) // nothing appended
            {
               return null;
            }
         }
         else {
            sb.append('.');
            sb.append(artifactId);
         }
         return sb.toString();
      }
      return null;
   }
}
