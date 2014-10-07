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

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleSymbolicName;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.Resource;

/**
 * @author Bernd
 */
@Named("ExistingBundleManifestSymbolicName")
public class ExistingBundleManifestSymbolicName extends AbstractSymbolicNameResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.HIGH;
   }

   @Override
   public boolean isUnambiguous()
   {
      return true;
   }

   @Override
   public String resolveSymbolicName(BundleCandidate bundleCandidate, PropertiesSource options)
   {
      final JavaResourceBundle jBundle = bundleCandidate.getContent();
      if (jBundle != null)
      {
         for (JavaResourcesRoot jRoot : jBundle.getResourcesRoots())
         {
            Resource resource = jRoot.getResource("META-INF/MANIFEST.MF");
            if (resource != null)
            {
               final BundleManifest bundleManifest = resource.getExtension(BundleManifest.class);
               if (bundleManifest != null)
               {
                  final BundleSymbolicName bundleSymbolicName = bundleManifest.getBundleSymbolicName();
                  if (bundleSymbolicName != null)
                  {
                     return bundleSymbolicName.getSymbolicName();
                  }
               }
            }
         }
      }
      return null;
   }
}
