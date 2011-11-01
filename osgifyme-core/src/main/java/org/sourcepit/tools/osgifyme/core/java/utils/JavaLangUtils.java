/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.utils;

import java.util.StringTokenizer;

import javax.validation.constraints.NotNull;

public final class JavaLangUtils
{
   private JavaLangUtils()
   {
      super();
   }

   public static boolean isFullyQiallifiedPackageName(@NotNull String fullyQuallifiedName)
   {
      return isFullyQuallifiedPackageName(fullyQuallifiedName, ".");
   }

   public static boolean isFullyQuallifiedPackageName(@NotNull String fullyQuallifiedName, @NotNull String separator)
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

   public static boolean isJavaPackageSegmentName(@NotNull String segmentName)
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

   @NotNull
   public static String toPackageName(@NotNull String resourcePath)
   {
      String pkgName = resourcePath.replace('/', '.');
      if (pkgName.endsWith("."))
      {
         pkgName = pkgName.substring(0, pkgName.length() - 1);
      }
      return pkgName;
   }

   @NotNull
   public static String[] getPackageAndFileName(@NotNull String resourcePath)
   {
      int idx = resourcePath.lastIndexOf("/");
      final String pkgName;
      final String typeName;
      if (idx == -1)
      {
         pkgName = "";
         typeName = resourcePath;
      }
      else
      {
         pkgName = toPackageName(resourcePath.substring(0, idx));
         typeName = resourcePath.substring(idx + 1);
      }
      return new String[] {pkgName, typeName};
   }
}
