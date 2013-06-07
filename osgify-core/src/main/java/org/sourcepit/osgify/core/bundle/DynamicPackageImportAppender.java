/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.common.manifest.osgi.BundleHeaderName.DYNAMICIMPORT_PACKAGE;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.DynamicPackageImport;
import org.sourcepit.osgify.core.model.context.BundleCandidate;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class DynamicPackageImportAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(DynamicPackageImportAppender.class);

   private BundleRequirementsService packagesService;

   @Inject
   public DynamicPackageImportAppender(BundleRequirementsService packagesService)
   {
      this.packagesService = packagesService;
   }

   public void append(BundleCandidate bundle)
   {
      final BundleManifest manifest = bundle.getManifest();
      if (!hasDynamicImportPackage(manifest, "*") && packagesService.usesClassForName(bundle.getContent()))
      {
         LOGGER
            .warn("Detected usage of Class.forName(String). The behaviour of this method is differs between OSGi and pure Java. Setting the 'DynamicImport-Package: *' header to workaround this problem. ");
         manifest.setHeader(DYNAMICIMPORT_PACKAGE, "*");
      }
   }

   private boolean hasDynamicImportPackage(BundleManifest manifest, String pattern)
   {
      EList<DynamicPackageImport> dynamicImportPackage = manifest.getDynamicImportPackage();
      if (dynamicImportPackage != null)
      {
         for (DynamicPackageImport dynamicPackageImport : dynamicImportPackage)
         {
            if (dynamicPackageImport.getPackageNames().contains(pattern))
            {
               return true;
            }
         }
      }
      return false;
   }
}
