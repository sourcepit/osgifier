/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSources;
import org.sourcepit.osgify.core.headermod.HeaderModifications;

public class ArtifactManifestBuilderRequest
{
   private Artifact artifact;

   private Artifact sourceArtifact;

   private final List<Artifact> dependencies = new ArrayList<Artifact>();

   private PropertiesSource options = PropertiesSources.emptyPropertiesSource();

   private String symbolicName;

   private Date timestamp;

   private Manifest manifestToMerge;
   
   private HeaderModifications headerModifications;
   
   public static PropertiesSource toOptions(Map<?, ?> map)
   {
      return PropertiesSources.toPropertiesSource(map);
   }
   
   public static PropertiesSource chainOptions(PropertiesSource... options)
   {
      return PropertiesSources.chain(options);
   }
   
   public Artifact getArtifact()
   {
      return artifact;
   }

   public void setArtifact(Artifact artifact)
   {
      this.artifact = artifact;
   }

   public Artifact getSourceArtifact()
   {
      return sourceArtifact;
   }

   public void setSourceArtifact(Artifact sourceArtifact)
   {
      this.sourceArtifact = sourceArtifact;
   }

   public List<Artifact> getDependencies()
   {
      return dependencies;
   }

   public PropertiesSource getOptions()
   {
      return options;
   }

   public void setOptions(PropertiesSource options)
   {
      this.options = options;
   }

   public String getSymbolicName()
   {
      return symbolicName;
   }

   public void setSymbolicName(String symbolicName)
   {
      this.symbolicName = symbolicName;
   }

   public Date getTimestamp()
   {
      return timestamp;
   }

   public void setTimestamp(Date timestamp)
   {
      this.timestamp = timestamp;
   }

   public Manifest getManifestToMerge()
   {
      return manifestToMerge;
   }

   public void setManifestToMerge(Manifest manifestToMerge)
   {
      this.manifestToMerge = manifestToMerge;
   }
   
   public HeaderModifications getHeaderModifications()
   {
      return headerModifications;
   }
   
   public void setHeaderModifications(HeaderModifications headerModifications)
   {
      this.headerModifications = headerModifications;
   }
}
