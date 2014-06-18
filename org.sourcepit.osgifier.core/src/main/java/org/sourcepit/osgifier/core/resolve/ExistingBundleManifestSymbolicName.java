/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
