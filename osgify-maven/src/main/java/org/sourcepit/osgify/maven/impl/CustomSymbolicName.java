/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Named;

import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.resolve.AbstractSymbolicNameResolutionStrategy;

@Named("CustomSymbolicName")
public class CustomSymbolicName extends AbstractSymbolicNameResolutionStrategy
{
   @Override
   public Priority getPriority()
   {
      return Priority.MAXIMUM;
   }
   
   @Override
   public boolean isUnambiguous()
   {
      return true;
   }

   @Override
   public String resolveSymbolicName(BundleCandidate bundleCandidate, PropertiesSource options)
   {
      final MavenArtifact artifact = bundleCandidate.getExtension(MavenArtifact.class);
      if (artifact != null)
      {
         return resolveSymbolicName(artifact.getArtifactKey(), options);
      }
      return null;
   }

   private String resolveSymbolicName(ArtifactKey artifact, PropertiesSource options)
   {
      final String rawMappings = options.get("osgifier.symbolicNameMappings");
      if (rawMappings != null)
      {
         final Map<String, String> mappings = parse(rawMappings);
         String artifactKey = artifact.toString();
         String name = mappings.get(artifactKey);
         if (name == null)
         {
            artifactKey = artifactKey.substring(0, artifactKey.lastIndexOf(":"));
            name = mappings.get(artifactKey);
         }
         return name;
      }

      return null;
   }

   private static Map<String, String> parse(String rawMappings)
   {
      final Map<String, String> mappings = new LinkedHashMap<String, String>();
      for (String entry : rawMappings.split(","))
      {
         final String[] keyToValue = entry.split("=");
         if (keyToValue.length == 2)
         {
            final String key = keyToValue[0].trim();
            if (!mappings.containsKey(key))
            {
               mappings.put(key, keyToValue[1].trim());
            }
         }
      }
      return mappings;
   }

}
