/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

import static com.google.common.base.Strings.isNullOrEmpty;
import static org.sourcepit.osgify.maven.impl.MavenToOSGiUtils.toVersion;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.model.MavenProjectCoordinates;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.resolve.AbstractVersionResolutionStrategy;

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
