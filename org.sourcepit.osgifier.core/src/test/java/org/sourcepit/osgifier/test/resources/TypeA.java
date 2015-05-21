/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.test.resources;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Matcher;

public class TypeA {
   private Object o;

   public Object getO() {
      new String().charAt(0);

      Boolean b = new Boolean(false);
      if (b.booleanValue()) {
         return null;
      }
      return o;
   }

   public static class Hans<E> {
      private Long[][] i = new Long[1][8];

      private Map<Short[], Boolean[][][]> map = new HashMap<Short[], Boolean[][][]>();

      private Runnable r = new Runnable() {
         public void run() {
            Collection<Boolean[][][]> values = map.values();
            values.clear();
            Long.valueOf(i[0][0]);
         }
      };

      public BaseMatcher<E> and(Matcher<? extends E> matcher) {
         return null;
      }

      public Hans() {
         r.run();

         final Integer[] i = new Integer[1];

         new Comparator<String>() {
            public int compare(String o1, String o2) {
               return i[0];
            }
         }.compare(null, null);
      }
   }
}
