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

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.bundle.BundleHeadersAppender;
import org.sourcepit.osgifier.core.bundle.BundleManifestAppenderFilter;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

/**
 * @author Bernd Vogt <Bernd.Vogt@bosch-si.com>
 */
@Named
public class MavenGAVHeadersAppender implements BundleHeadersAppender {
   @Override
   public void append(BundleCandidate bundle, BundleManifestAppenderFilter filter, PropertiesSource options) {
      final MavenArtifact extension = bundle.getExtension(MavenArtifact.class);
      if (extension != null) {
         final BundleManifest manifest = bundle.getManifest();
         manifest.setHeader("Maven-GroupId", extension.getGroupId());
         manifest.setHeader("Maven-ArtifactId", extension.getArtifactId());
         manifest.setHeader("Maven-Type", extension.getType());
         final String classifier = extension.getClassifier();
         if (!isNullOrEmpty(classifier)) {
            manifest.setHeader("Maven-Classifier", classifier);
         }
         manifest.setHeader("Maven-Version", extension.getVersion());
      }
   }
}
