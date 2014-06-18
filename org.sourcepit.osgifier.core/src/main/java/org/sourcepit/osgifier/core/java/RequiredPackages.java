/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.java;

import java.util.Collection;

public class RequiredPackages
{
   private final Collection<String> inherited;
   private final Collection<String> invoked;
   private final Collection<String> all;

   RequiredPackages(Collection<String> inherited, Collection<String> invoked, Collection<String> all)
   {
      this.inherited = inherited;
      this.invoked = invoked;
      this.all = all;
   }

   public Collection<String> getInherited()
   {
      return inherited;
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
