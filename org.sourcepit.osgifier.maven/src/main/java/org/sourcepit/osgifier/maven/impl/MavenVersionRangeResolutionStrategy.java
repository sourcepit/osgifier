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

import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.resolve.AbstractVersionRangeResolutionStrategy;

@Named("MavenVersionRangeResolutionStrategy")
public class MavenVersionRangeResolutionStrategy extends AbstractVersionRangeResolutionStrategy {
   public Priority getPriority() {
      return Priority.HIGH;
   }

   @Override
   public VersionRange resolveVersionRange(BundleReference bundleReference) {
      final String mVersionRange = getMavenVersionRange(bundleReference);
      if (mVersionRange != null) {
         return MavenToOSGiUtils.toVersionRange(mVersionRange);
      }
      return null;
   }


   private String getMavenVersionRange(BundleReference bundleReference) {
      final MavenDependency mavenDependency = bundleReference.getExtension(MavenDependency.class);
      if (mavenDependency != null) {
         return mavenDependency.getVersionConstraint();
      }
      return null;
   }
}
