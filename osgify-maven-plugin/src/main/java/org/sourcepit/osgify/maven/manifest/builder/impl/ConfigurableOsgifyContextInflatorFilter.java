/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder.impl;

import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.maven.DefaultOsgifyContextInflatorFilter;

/**
 * @author DerGilb
 *
 */
public class ConfigurableOsgifyContextInflatorFilter extends DefaultOsgifyContextInflatorFilter
{
   private final BundleCandidate configurableBundle;
   private boolean appendExecutionEnvironment = true;
   private boolean appendPackageExports = true;
   private boolean appendPackageImports = true;
   private boolean appendDynamicImports = true;

   public ConfigurableOsgifyContextInflatorFilter(BundleCandidate projectBundle)
   {
      this.configurableBundle = projectBundle;
   }

   @Override
   public boolean isAppendExecutionEnvironment(BundleCandidate bundle, PropertiesSource options)
   {
      return appendExecutionEnvironment && bundle.equals(configurableBundle);
   }

   @Override
   public boolean isAppendPackageExports(BundleCandidate bundle, PropertiesSource options)
   {
      return appendPackageExports && bundle.equals(configurableBundle);
   }

   @Override
   public boolean isAppendPackageImports(BundleCandidate bundle, PropertiesSource options)
   {
      return appendPackageImports && bundle.equals(configurableBundle);
   }

   @Override
   public boolean isAppendDynamicImports(BundleCandidate bundle, PropertiesSource options)
   {
      return appendDynamicImports && bundle.equals(configurableBundle);
   }

   public BundleCandidate getProjectBundle()
   {
      return configurableBundle;
   }

   public boolean isAppendExecutionEnvironment()
   {
      return appendExecutionEnvironment;
   }

   public void setAppendExecutionEnvironment(boolean appendExecutionEnvironment)
   {
      this.appendExecutionEnvironment = appendExecutionEnvironment;
   }

   public boolean isAppendPackageExports()
   {
      return appendPackageExports;
   }

   public void setAppendPackageExports(boolean appendPackageExports)
   {
      this.appendPackageExports = appendPackageExports;
   }

   public boolean isAppendPackageImports()
   {
      return appendPackageImports;
   }

   public void setAppendPackageImports(boolean appendPackageImports)
   {
      this.appendPackageImports = appendPackageImports;
   }

   public boolean isAppendDynamicImports()
   {
      return appendDynamicImports;
   }

   public void setAppendDynamicImports(boolean appendDynamicImports)
   {
      this.appendDynamicImports = appendDynamicImports;
   }


}
