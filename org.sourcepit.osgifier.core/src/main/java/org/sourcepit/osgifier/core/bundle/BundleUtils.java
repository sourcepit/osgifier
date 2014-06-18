/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.bundle;

import java.util.ArrayList;
import java.util.List;

import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Parameter;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;

public final class BundleUtils
{
   private BundleUtils()
   {
      super();
   }

   public static boolean isInternalPackage(PackageExport packageExport)
   {
      final Parameter parameter = packageExport.getParameter("x-internal");
      return parameter != null && "true".equals(parameter.getValue());
   }
   
   public static VersionRange trimQualifiers(VersionRange range)
   {
      if (range != null)
      {
         final Version low = trimQualifier(range.getLowVersion());
         final Version high = trimQualifier(range.getHighVersion());
         return new VersionRange(low, range.isLowInclusive(), high, range.isHighInclusive());
      }
      return null;
   }

   private static Version trimQualifier(Version version)
   {
      if (version != null && version.getQualifier().length() > 0)
      {
         final int minor = version.getMinor();
         final int micro = version.getMicro();
         return new Version(version.getMajor(), minor == 0 && micro == 0 ? -1 : minor, micro == 0 ? -1 : micro);
      }
      return version;
   }
   
   public static List<BundleCandidate> getEmbeddedBundles(BundleCandidate bundle)
   {
      final List<BundleCandidate> embeddedBundles = new ArrayList<BundleCandidate>();
      for (BundleReference bundleReference : bundle.getDependencies())
      {
         switch (bundleReference.getEmbedInstruction())
         {
            case NOT :
               break;
            case UNPACKED :
            case PACKED :
               embeddedBundles.add(bundleReference.getTarget());
               break;
            default :
               throw new IllegalStateException();
         }
      }
      return embeddedBundles;
   }
}
