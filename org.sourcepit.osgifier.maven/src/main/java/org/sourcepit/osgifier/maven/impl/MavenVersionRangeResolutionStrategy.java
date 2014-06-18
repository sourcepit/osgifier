/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven.impl;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.resolve.AbstractVersionRangeResolutionStrategy;

@Named("MavenVersionRangeResolutionStrategy")
public class MavenVersionRangeResolutionStrategy extends AbstractVersionRangeResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.HIGH;
   }

   @Override
   public VersionRange resolveVersionRange(BundleReference bundleReference)
   {
      final String mVersionRange = getMavenVersionRange(bundleReference);
      if (mVersionRange != null)
      {
         return MavenToOSGiUtils.toVersionRange(mVersionRange);
      }
      return null;
   }


   private String getMavenVersionRange(BundleReference bundleReference)
   {
      final MavenDependency mavenDependency = bundleReference.getExtension(MavenDependency.class);
      if (mavenDependency != null)
      {
         return mavenDependency.getVersionConstraint();
      }
      return null;
   }
}
