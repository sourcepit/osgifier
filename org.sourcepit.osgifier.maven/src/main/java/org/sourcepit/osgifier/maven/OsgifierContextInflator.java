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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.bundle.BundleManifestAppender;
import org.sourcepit.osgifier.core.bundle.BundleManifestAppenderFilter;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.resolve.ContentAppender;
import org.sourcepit.osgifier.core.resolve.ContentAppenderFilter;
import org.sourcepit.osgifier.core.resolve.NativeManifestAppender;
import org.sourcepit.osgifier.core.resolve.SymbolicNameAndVersionAppender;

@Named
public class OsgifierContextInflator {
   @Inject
   private ContentAppender javaContentAppender;

   @Inject
   private SymbolicNameAndVersionAppender symbolicNameAndVersionAppender;

   @Inject
   private NativeManifestAppender nativeManifestAppender;

   @Inject
   private BundleManifestAppender manifestAppender;

   public void inflate(OsgifierContextInflatorFilter filter, PropertiesSource options,
      final OsgifierContext osgifierModel, Date timestamp) {
      options = getOptions(options, timestamp);

      nativeManifestAppender.appendNativeManifests(osgifierModel, filter, options);

      javaContentAppender.appendContents(osgifierModel, ContentAppenderFilter.SKIP_NATIVE_AND_SOURCE, options);

      symbolicNameAndVersionAppender.appendSymbolicNamesAndVersion(osgifierModel, options);

      applyManifests(filter, options, osgifierModel);
   }

   private PropertiesSource getOptions(final PropertiesSource options, Date timestamp) {
      final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
      format.setTimeZone(TimeZone.getTimeZone("UTC"));
      final String ctxQualifier = format.format(timestamp);
      return new AbstractPropertiesSource() {
         @Override
         public String get(String key) {
            if ("osgifier.forceContextQualifier".equals(key)) {
               return ctxQualifier;
            }
            return options == null ? null : options.get(key);
         }
      };
   }

   private void applyManifests(BundleManifestAppenderFilter filter, PropertiesSource options,
      OsgifierContext osgifierModel) {
      manifestAppender.append(osgifierModel, filter, options);
   }
}
