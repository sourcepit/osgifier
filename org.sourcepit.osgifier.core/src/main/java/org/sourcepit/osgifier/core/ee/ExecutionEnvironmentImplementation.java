/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.ee;

import java.util.Collections;
import java.util.List;

import org.sourcepit.common.constraints.NotNull;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class ExecutionEnvironmentImplementation
{
   private final String eeId;
   private final String vendor;
   private final List<String> vendorPackages;

   public ExecutionEnvironmentImplementation(@NotNull String eeId, @NotNull String vendor,
      @NotNull List<String> vendorPackages)
   {
      this.eeId = eeId;
      this.vendor = vendor;
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

   @Override
   public String toString()
   {
      return "ExecutionEnvironmentImplementation [executionEnvironmentId=" + eeId + ", vendor=" + vendor
         + ", vendorPackages=" + vendorPackages + "]";
   }

}
