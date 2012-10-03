/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.Resource;

@Named("ExistingBundleManifestVersion")
public class ExistingBundleManifestVersion extends AbstractVersionResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.MAXIMUM;
   }

   @Override
   public Version resolveVersion(BundleCandidate bundleCandidate)
   {
      final JavaResourceBundle jBundle = bundleCandidate.getContent();
      if (jBundle != null)
      {
         for (JavaResourcesRoot jRoot : jBundle.getResourcesRoots())
         {
            final Resource resource = jRoot.getResource("META-INF/MANIFEST.MF");
            if (resource != null)
            {
               final BundleManifest bundleManifest = resource.getExtension(BundleManifest.class);
               if (bundleManifest != null)
               {
                  return bundleManifest.getBundleVersion();
               }
            }
         }
      }
      return null;
   }

}
