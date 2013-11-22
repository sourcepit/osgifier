/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import javax.validation.constraints.NotNull;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JavaResourcesRootOperations
{
   public JavaResourcesRootOperations()
   {
      super();
   }

   public static JavaType getType(@NotNull JavaResourcesRoot jRoot, String qualifiedPackageName,
      @NotNull String typeName, boolean createOnDemand)
   {
      final JavaResourceDirectory parentJDir;
      if (qualifiedPackageName == null)
      {
         parentJDir = jRoot;
      }
      else
      {
         parentJDir = jRoot.getPackage(qualifiedPackageName, createOnDemand);
      }

      if (parentJDir == null)
      {
         return null;
      }
      return parentJDir.getType(typeName, createOnDemand);
   }
}
