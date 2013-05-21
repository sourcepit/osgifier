/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

import javax.inject.Named;

import org.sourcepit.common.maven.model.MavenArtifactConflictCoordinates;
import org.sourcepit.common.maven.model.MavenArtifactCoordinates;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.resolve.AbstractSymbolicNameResolutionStrategy;

/**
 * @author Bernd
 */
@Named("MergeArtifactWithGroupId")
public class MergeArtifactWithGroupId extends AbstractSymbolicNameResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.NORMAL;
   }

   @Override
   public String resolveSymbolicName(BundleCandidate bundleCandidate)
   {
      final MavenArtifactConflictCoordinates mavenArtifact = bundleCandidate
         .getExtension(MavenArtifactConflictCoordinates.class);
      if (mavenArtifact != null)
      {
         final String groupId = mavenArtifact.getGroupId();
         if (groupId != null)
         {
            final String artifactId = mavenArtifact.getArtifactId();
            if (artifactId != null)
            {
               String symbolicName = resolveSymbolicName(groupId, artifactId);
               if (symbolicName != null && mavenArtifact instanceof MavenArtifactCoordinates)
               {
                  final MavenArtifactCoordinates gav = (MavenArtifactCoordinates) mavenArtifact;

                  String classifier = gav.getClassifier();
                  if ("java-source".equals(gav.getType()) && (classifier == null || "sources".equals(classifier)))
                  {
                     classifier = "source";
                  }

                  if (classifier != null)
                  {
                     symbolicName = resolveSymbolicName(symbolicName, classifier);
                  }
               }
               return beautify(symbolicName);
            }
         }
      }
      return null;
   }

   private String beautify(String symbolicName)
   {
      if (symbolicName == null)
      {
         return null;
      }
      final StringBuilder sb = new StringBuilder();
      for (char c : symbolicName.toCharArray())
      {
         switch (c)
         {
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

   private String resolveSymbolicName(String groupId, String artifactId)
   {
      final String[] segments = groupId.split("\\.");
      if (segments.length > 1)
      {
         final StringBuilder sb = new StringBuilder();
         sb.append(groupId);

         String idPrefix = groupId;
         if (!artifactId.startsWith(idPrefix))
         {
            idPrefix = segments[segments.length - 1];
         }

         if (artifactId.startsWith(idPrefix))
         {
            final String appendix = artifactId.substring(idPrefix.length());
            boolean trim = true;
            for (char c : appendix.toCharArray())
            {
               switch (c)
               {
                  case '-' :
                  case '_' :
                  case '.' :
                     if (trim)
                     {
                        break;
                     }
                  default :
                     if (trim)
                     {
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
         else
         {
            sb.append('.');
            sb.append(artifactId);
         }
         return sb.toString();
      }
      return null;
   }
}
