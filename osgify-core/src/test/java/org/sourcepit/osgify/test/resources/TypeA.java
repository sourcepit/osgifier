/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.test.resources;

import java.util.Comparator;

public class TypeA
{
   private Object o;

   public Object getO()
   {
      new String().charAt(0);

      Boolean b = new Boolean(false);
      if (b.booleanValue())
      {
         return null;
      }
      return o;
   }

   public static class Hans
   {
      private Runnable r = new Runnable()
      {
         public void run()
         {
         }
      };

      public Hans()
      {
         r.run();

         new Comparator<String>()
         {
            public int compare(String o1, String o2)
            {
               return 0;
            }
         }.compare(null, null);
      }
   }
}
