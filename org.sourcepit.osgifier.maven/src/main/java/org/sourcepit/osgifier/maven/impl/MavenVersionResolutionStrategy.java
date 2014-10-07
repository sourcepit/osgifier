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

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.sourcepit.osgifier.maven.impl.MavenToOSGiUtils.toVersion;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.model.MavenProjectCoordinates;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.resolve.AbstractVersionResolutionStrategy;

@Named("MavenVersionResolutionStrategy")
public class MavenVersionResolutionStrategy extends AbstractVersionResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.NORMAL;
   }

   @Override
   public Version resolveVersion(BundleCandidate bundleCandidate, PropertiesSource options)
   {
      final String ctxQualifier = options.get("osgifier.forceContextQualifier");
      final boolean saveMaveQualifier = options.getBoolean("osgifier.saveMavenQualifier", true);

      final MavenProjectCoordinates mavenArtifact = bundleCandidate.getExtension(MavenProjectCoordinates.class);
      if (mavenArtifact != null)
      {
         final String mvnVersion = mavenArtifact.getVersion();
         if (mvnVersion != null)
         {
            Version version = toVersion(mvnVersion, true);
            if (!isNullOrEmpty(ctxQualifier))
            {
               final String oldQualifier = version.getQualifier();
               String newQualifier = ctxQualifier;
               if (saveMaveQualifier && !isNullOrEmpty(oldQualifier))
               {
                  newQualifier = ctxQualifier + "-" + oldQualifier;
               }
               version = new Version(version.getMajor(), version.getMinor(), version.getMicro(), newQualifier);
            }

            return version;
         }
      }
      return null;
   }

}
