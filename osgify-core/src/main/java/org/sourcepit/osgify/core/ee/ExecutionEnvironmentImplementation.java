/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee;

import java.util.Collections;
import java.util.List;

import org.sourcepit.common.constraints.NotNull;

import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.common.utils.props.PropertiesUtils;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class ExecutionEnvironmentImplementation
{
   private final String eeId;
   private final String vendor;
   private final PropertiesMap properties;
   private final List<String> vendorPackages;

   public ExecutionEnvironmentImplementation(@NotNull String eeId, @NotNull String vendor,
      @NotNull PropertiesMap properties, @NotNull List<String> vendorPackages)
   {
      this.eeId = eeId;
      this.vendor = vendor;
      this.properties = PropertiesUtils.unmodifiablePropertiesMap(properties);
      this.vendorPackages = Collections.unmodifiableList(vendorPackages);
   }

   public String getExecutionEnvironmentId()
   {
      return eeId;
   }

   public String getVendor()
   {
      return vendor;
   }

   public List<String> getVendorPackages()
   {
      return vendorPackages;
   }

   public PropertiesMap getProperties()
   {
      return properties;
   }

   @Override
   public String toString()
   {
      return "ExecutionEnvironmentImplementation [executionEnvironmentId=" + eeId + ", vendor=" + vendor
         + ", properties=" + properties + ", vendorPackages=" + vendorPackages + "]";
   }

}
