/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.model.VersionedIdentifiable;
import org.sourcepit.common.utils.priority.Priority;
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
   public Version resolveVersion(BundleCandidate bundleCandidate)
   {
      final VersionedIdentifiable mavenArtifact = bundleCandidate.getExtension(VersionedIdentifiable.class);
      if (mavenArtifact != null)
      {
         final String mvnVersion = mavenArtifact.getVersion();
         if (mvnVersion != null)
         {
            return MavenToOSGiUtils.toVersion(mvnVersion, true);
         }
      }
      return null;
   }

}
