/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import org.codehaus.plexus.component.annotations.Component;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleSymbolicName;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgify.context.BundleCandidate;
import org.sourcepit.osgify.java.JavaPackageBundle;
import org.sourcepit.osgify.java.JavaPackageRoot;

/**
 * @author Bernd
 */
@Component(role = AbstractSymbolicNameResolutionStrategy.class, hint = "ExistingBundleManifestSymbolicName")
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
      final JavaPackageBundle jContent = bundleCandidate.getContent();
      if (jContent != null)
      {
         for (JavaPackageRoot jPackageRoot : jContent.getPackageRoots())
         {
            final BundleManifest bundleManifest = jPackageRoot.getExtension(BundleManifest.class);
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
