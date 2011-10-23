/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.utils;

import java.util.StringTokenizer;

public final class JavaUtils
{
   private JavaUtils()
   {
      super();
   }

   public static boolean isFullyQiallifiedPackageName(String fullyQuallifiedName)
   {
      return isFullyQiallifiedPackageName(fullyQuallifiedName, ".");
   }

   public static boolean isFullyQiallifiedPackageName(String fullyQuallifiedName, String separator)
   {
      final StringTokenizer segments = new StringTokenizer(fullyQuallifiedName, separator);
      while (segments.hasMoreTokens())
      {
         final String segment = segments.nextToken();
         if (!isJavaPackageSegmentName(segment))
         {
            return false;
         }
      }
      return true;
   }

   public static boolean isJavaPackageSegmentName(String segmentName)
   {
      final char[] chars = segmentName.toCharArray();
      if (chars.length > 0)
      {
         if (!Character.isJavaIdentifierStart(chars[0]))
         {
            return false;
         }
         for (int i = 1; i < chars.length; i++)
         {
            if (!Character.isJavaIdentifierPart(chars[i]))
            {
               return false;
            }
         }
      }
      return true;
   }
}
