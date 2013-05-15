/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.sourcepit.common.maven.model.MavenArtifactConflictCoordinates;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.resolve.AbstractSymbolicNameResolutionStrategy;

@Named("MavenGavToSymbolicNameMappingStrategy")
public class MavenGavToSymbolicNameMappingStrategy extends AbstractSymbolicNameResolutionStrategy
{
   private final static Map<String, String> MAPPINGS = new HashMap<String, String>();

   static
   {
      MAPPINGS.put("stax:stax-api", "javax.xml.stax.api");
   }

   @Override
   public boolean isUnambiguous()
   {
      return true;
   }

   public Priority getPriority()
   {
      return Priority.MAXIMUM;
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
               return resolveSymbolicName(groupId, artifactId);
            }
         }
      }
      return null;
   }

   private String resolveSymbolicName(String groupId, String artifactId)
   {
      return MAPPINGS.get(groupId + ":" + artifactId);
   }

}
