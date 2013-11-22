/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;

import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResourcesRoot;
import org.sourcepit.modularizor.java.JavaResourcesType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JavaPackageOperations
{
   private JavaPackageOperations()
   {
      super();
   }

   public static JavaResourcesType getResourcesType(JavaPackage jPackage)
   {
      final JavaResourcesRoot jResources = jPackage.getResourcesRoot();
      return jResources == null ? null : jResources.getResourcesType();
   }
}
