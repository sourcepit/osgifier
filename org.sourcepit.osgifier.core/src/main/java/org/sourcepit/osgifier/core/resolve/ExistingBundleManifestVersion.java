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

package org.sourcepit.osgifier.core.resolve;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.Resource;

@Named("ExistingBundleManifestVersion")
public class ExistingBundleManifestVersion extends AbstractVersionResolutionStrategy {
   public Priority getPriority() {
      return Priority.MAXIMUM;
   }

   @Override
   public Version resolveVersion(BundleCandidate bundleCandidate, PropertiesSource options) {
      final JavaResourceBundle jBundle = bundleCandidate.getContent();
      if (jBundle != null) {
         for (JavaResourcesRoot jRoot : jBundle.getResourcesRoots()) {
            final Resource resource = jRoot.getResource("META-INF/MANIFEST.MF");
            if (resource != null) {
               final BundleManifest bundleManifest = resource.getExtension(BundleManifest.class);
               if (bundleManifest != null) {
                  Version version = bundleManifest.getBundleVersion();
                  if (version != null) {
                     final String ctxQualifier = options.get("osgifier.forceContextQualifier");
                     if (!isNullOrEmpty(ctxQualifier)) {
                        version = new Version(version.getMajor(), version.getMinor(), version.getMicro(), ctxQualifier);
                     }
                  }
                  return version;
               }
            }
         }
      }
      return null;
   }

}
