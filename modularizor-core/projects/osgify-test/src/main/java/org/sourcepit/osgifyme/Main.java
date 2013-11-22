/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme;

import org.hamcrest.core.Is;


public class Main
{
   public static void main(String[] args)
   {
      Main main = new Main();
      if (Is.is(Boolean.TRUE).matches(Boolean.valueOf(main.isOSGiAvailable())))
      {
         System.out.println("Found OSGi classes");
      }
      else
      {
         System.out.println("Found no OSGi classes");
      }
   }

   private boolean isOSGiAvailable()
   {
      try
      {
         Class.forName("org.osgi.framework.Version");
         return true;
      }
      catch (ClassNotFoundException e)
      {
         return false;
      }
   }
}
