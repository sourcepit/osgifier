/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author Bernd
 */
public final class SymbolicNameUtils
{
   private static final String ALLOWED = "_-abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

   public static boolean isValidSymbolicName(String symbolicName)
   {
      if (symbolicName == null || symbolicName.length() == 0)
      {
         return false;
      }
      char[] chars = symbolicName.toCharArray();
      int length = chars.length;
      for (int i = 0; i < length; i++)
      {
         char c = chars[i];
         if (ALLOWED.indexOf(c) < 0)
         {
            if ('.' != c)
            {
               return false;
            }
            if (i == 0 || i == length - 1)
            {
               return false;
            }
            if (i > 0 && chars[i - 1] == '.')
            {
               return false;
            }
         }
      }
      return true;
   }

   public static String toValidSymbolicName(@NotNull @Size(min = 1) String symbolicName)
   {
      final StringBuilder sb = new StringBuilder();
      char[] chars = symbolicName.trim().toCharArray();
      int length = chars.length;
      for (int i = 0; i < length; i++)
      {
         char c = chars[i];
         if (ALLOWED.indexOf(c) < 0)
         {
            replace(sb, c, i, length);
         }
         else
         {
            sb.append(c);
         }
      }
      if (sb.length() == 0)
      {
         sb.append('_');
      }
      return sb.toString();
   }

   private static void replace(final StringBuilder sb, char c, int idx, int length)
   {
      switch (c)
      {
         case 'ä' :
            sb.append("ae");
            break;
         case 'ö' :
            sb.append("oe");
            break;
         case 'ü' :
            sb.append("ue");
            break;
         case 'ß' :
            sb.append("ss");
            break;
         case '.' :
            if (sb.length() != 0 && idx != length - 1)
            {
               sb.append(c);
            }
            break;
         default :
            sb.append('_');
            break;
      }
   }
}
