/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.impl;

import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaPackageBundle;
import org.sourcepit.osgifyme.core.java.JavaPackageRoot;

public final class PackageOperations
{
   private PackageOperations()
   {
      super();
   }

   public static JavaPackageBundle getPackageBundle(JavaPackage pkg)
   {
      JavaPackageRoot packageRoot = pkg.getPackageRoot();
      return packageRoot == null ? null : packageRoot.getPackageBundle();
   }

   public static JavaPackageRoot getPackageRoot(JavaPackage pkg)
   {
      JavaPackage current = pkg;
      while (current.getParentPackage() != null)
      {
         current = current.getParentPackage();
      }
      return (JavaPackageRoot) pkg.eContainer();
   }
}
