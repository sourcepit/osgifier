/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.test.resources;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;

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

   public static class Hans<E>
   {
      private Long[][] i = new Long[1][8];

      private Map<Short[], Boolean[][][]> map = new HashMap<Short[], Boolean[][][]>();

      private Runnable r = new Runnable()
      {
         public void run()
         {
            Collection<Boolean[][][]> values = map.values();
            values.clear();
            Long.valueOf(i[0][0]);
         }
      };

      public BaseMatcher<E> and(Matcher<? extends E> matcher)
      {
         return null;
      }

      public Hans()
      {
         r.run();

         final Integer[] i = new Integer[1];

         new Comparator<String>()
         {
            public int compare(String o1, String o2)
            {
               return i[0];
            }
         }.compare(null, null);
      }
   }
}
