/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.List;

public class PackageResolutionResult
{
   private String requiredPackage;

   private PackageExportDescription selectedExporter;

   private AccessRestriction accessRestriction;

   private List<PackageExportDescription> exporters;

   public PackageResolutionResult(String requiredPackage, PackageExportDescription selectedExporter,
      AccessRestriction accessRestriction, List<PackageExportDescription> exporters)
   {
      this.requiredPackage = requiredPackage;
      this.selectedExporter = selectedExporter;
      this.accessRestriction = accessRestriction;
      this.exporters = exporters;
   }

   public String getRequiredPackage()
   {
      return requiredPackage;
   }

   public PackageExportDescription getSelectedExporter()
   {
      return selectedExporter;
   }

   public AccessRestriction getAccessRestriction()
   {
      return accessRestriction;
   }

   public List<PackageExportDescription> getExporters()
   {
      return exporters;
   }
}