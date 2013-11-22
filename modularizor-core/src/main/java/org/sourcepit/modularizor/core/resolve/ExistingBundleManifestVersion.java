/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.resolve;

import static com.google.common.base.Strings.isNullOrEmpty;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.core.model.java.JavaResourceBundle;
import org.sourcepit.modularizor.core.model.java.JavaResourcesRoot;
import org.sourcepit.modularizor.core.model.java.Resource;

@Named("ExistingBundleManifestVersion")
public class ExistingBundleManifestVersion extends AbstractVersionResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.MAXIMUM;
   }

   @Override
   public Version resolveVersion(BundleCandidate bundleCandidate, PropertiesSource options)
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
                  Version version = bundleManifest.getBundleVersion();
                  if (version != null)
                  {
                     final String ctxQualifier = options.get("osgifier.forceContextQualifier");
                     if (!isNullOrEmpty(ctxQualifier))
                     {
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
