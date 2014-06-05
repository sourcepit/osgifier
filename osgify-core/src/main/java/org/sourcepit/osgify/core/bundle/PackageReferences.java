/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.Collection;

public class PackageReferences
{
   private final Collection<String> implemented;
   private final Collection<String> invoked;
   private final Collection<String> all;

   PackageReferences(Collection<String> implemented, Collection<String> invoked, Collection<String> all)
   {
      this.implemented = implemented;
      this.invoked = invoked;
      this.all = all;
   }

   public Collection<String> getImplemented()
   {
      return implemented;
   }

   public Collection<String> getInvoked()
   {
      return invoked;
   }

   public Collection<String> getAll()
   {
      return all;
   }

}
