/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import javax.validation.constraints.NotNull;

import org.eclipse.emf.common.util.EList;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class ResourceOperations
{
   private ResourceOperations()
   {
      super();
   }

   public static void accept(@NotNull Resource resource, @NotNull ResourceVisitor visitor)
   {
      if (visitor.visit(resource) && resource instanceof Directory)
      {
         final EList<Resource> members = ((Directory) resource).getResources();
         for (Resource member : members)
         {
            member.accept(visitor);
         }
      }
   }
}
