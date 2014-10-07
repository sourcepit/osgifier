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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSources;
import org.sourcepit.osgifier.core.headermod.HeaderModifications;

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
