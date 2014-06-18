/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.headermod;

import java.util.ArrayList;
import java.util.List;

public class HeaderModifications
{
   private final List<SetHeaderModification> headers = new ArrayList<SetHeaderModification>(2);

   private final List<String> removals = new ArrayList<String>(2);

   public List<SetHeaderModification> getHeaders()
   {
      return headers;
   }

   public List<String> getRemovals()
   {
      return removals;
   }

}
