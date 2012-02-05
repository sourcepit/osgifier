/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import javax.inject.Named;

import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleSymbolicName;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;

/**
 * @author Bernd
 */
@Named("ExistingBundleManifestSymbolicName")
public class ExistingBundleManifestSymbolicName extends AbstractSymbolicNameResolutionStrategy
{
   public Priority getPriority()
   {
      return Priority.MAXIMUM;
   }

   @Override
   public boolean isUnambiguous()
   {
      return true;
   }

   @Override
   public String resolveSymbolicName(BundleCandidate bundleCandidate)
   {
      final JavaResourceBundle jBundle = bundleCandidate.getContent();
      if (jBundle != null)
      {
         for (JavaResourcesRoot jRoot : jBundle.getResourcesRoots())
         {
            final BundleManifest bundleManifest = jRoot.getExtension(BundleManifest.class);
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
      return null;
   }
}
