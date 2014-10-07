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

package org.sourcepit.osgifier.core.java.internal.impl;

import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.QualifiedJavaElement;
import org.sourcepit.osgifier.core.model.java.util.JavaModelSwitch;

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
