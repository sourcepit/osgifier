/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.impl;

import org.sourcepit.osgifyme.core.java.FullyQualified;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.osgifyme.core.java.JavaTypeRoot;
import org.sourcepit.osgifyme.core.java.internal.util.JavaModelSwitch;

public final class FullyQualifiedOperations
{
   private FullyQualifiedOperations()
   {
      super();
   }

   public static String getFullyQualifiedName(FullyQualified pkg)
   {
      final StringBuilder sb = new StringBuilder();
      buildFullyQualifiedName(sb, pkg);
      sb.deleteCharAt(sb.length() - 1);
      return sb.toString();
   }

   private static void buildFullyQualifiedName(StringBuilder sb, FullyQualified fullyQualified)
   {
      if (fullyQualified == null)
      {
         return;
      }
      buildFullyQualifiedName(sb, getParent(fullyQualified));
      sb.append(fullyQualified.getSimpleName());
      sb.append('.');
   }

   private static FullyQualified getParent(FullyQualified fullyQualified)
   {
      return new JavaModelSwitch<FullyQualified>()
      {
         public FullyQualified caseJavaPackage(JavaPackage pgk)
         {
            return pgk.getParentPackage();
         };

         public FullyQualified caseJavaType(JavaType type)
         {
            final JavaType outerType = type.getOuterType();
            if (outerType != null)
            {
               return outerType;
            }
            final JavaTypeRoot typeRoot = type.getTypeRoot();
            return typeRoot == null ? null : typeRoot.getParentPackage();
         };

      }.doSwitch(fullyQualified);
   }
}
