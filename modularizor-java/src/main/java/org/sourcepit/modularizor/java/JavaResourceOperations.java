/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import javax.validation.constraints.NotNull;

import org.eclipse.emf.ecore.EObject;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class JavaResourceOperations
{
   private JavaResourceOperations()
   {
      super();
   }

   public static JavaResourceDirectory getParentDirectory(@NotNull JavaResource jResource)
   {
      return (JavaResourceDirectory) jResource.eContainer();
   }

   public static JavaPackage getParentPackage(@NotNull JavaResource jResource)
   {
      final EObject eContainer = jResource.eContainer();
      if (eContainer instanceof JavaPackage)
      {
         return (JavaPackage) eContainer;
      }
      return null;
   }

   public static JavaResourcesRoot getResourcesRoot(@NotNull JavaResource jResource)
   {
      EObject current = jResource;
      while (current != null && !(current instanceof JavaResourcesRoot))
      {
         current = current.eContainer();
      }
      return (JavaResourcesRoot) current;
   }

}
