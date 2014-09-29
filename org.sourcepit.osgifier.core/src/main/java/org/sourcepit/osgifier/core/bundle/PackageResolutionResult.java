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

package org.sourcepit.osgifier.core.bundle;

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