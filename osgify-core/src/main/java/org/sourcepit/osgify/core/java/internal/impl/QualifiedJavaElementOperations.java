/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.internal.impl;

import org.sourcepit.osgify.core.model.java.JavaFile;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.QualifiedJavaElement;
import org.sourcepit.osgify.core.model.java.util.JavaModelSwitch;

public final class QualifiedJavaElementOperations
{
   private QualifiedJavaElementOperations()
   {
      super();
   }

   public static String getQualifiedName(QualifiedJavaElement qualified)
   {
      final StringBuilder sb = new StringBuilder();
      buildQualifiedName(sb, qualified);
      if (sb.length() == 0)
      {
         return null;
      }
      sb.deleteCharAt(sb.length() - 1);
      return sb.toString();
   }

   private static void buildQualifiedName(StringBuilder sb, QualifiedJavaElement qualified)
   {
      if (qualified == null)
      {
         return;
      }
      buildQualifiedName(sb, getParent(qualified));
      String name = qualified.getName();
      if (name == null)
      {
         return;
      }
      sb.append(name);
      sb.append('.');
   }

   private static QualifiedJavaElement getParent(QualifiedJavaElement fullyQualified)
   {
      return new JavaModelSwitch<QualifiedJavaElement>()
      {
         public QualifiedJavaElement caseJavaPackage(JavaPackage pgk)
         {
            return pgk.getParentPackage();
         };

         public QualifiedJavaElement caseJavaType(JavaType type)
         {
            final JavaType outerType = type.getOuterType();
            if (outerType != null)
            {
               return outerType;
            }
            final JavaFile typeRoot = type.getFile();
            return typeRoot == null ? null : typeRoot.getParentPackage();
         };

      }.doSwitch(fullyQualified);
   }
}
