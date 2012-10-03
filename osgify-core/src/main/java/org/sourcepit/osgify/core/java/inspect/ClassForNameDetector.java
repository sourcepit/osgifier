/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.sourcepit.osgify.core.model.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ClassForNameDetector implements IJavaTypeAnalyzer
{
   public static final String SOURCE = ClassForNameDetector.class.getName();
   public static final String CLASSLOADER_LOAD_CLASS = "java.lang.ClassLoader.loadClass (Ljava/lang/String;)Ljava/lang/Class;";
   public static final String CLASS_FOR_NAME_WITH_CLASSLOADER = "java.lang.Class.forName (Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;";
   public static final String CLASS_FOR_NAME = "java.lang.Class.forName (Ljava/lang/String;)Ljava/lang/Class;";

   public void analyze(final JavaType javaType, JavaClass javaClass)
   {
      new DescendingVisitor(javaClass, new EmptyVisitor()
      {
         private ConstantPool constantPool;

         @Override
         public void visitConstantPool(ConstantPool obj)
         {
            constantPool = obj;
            super.visitConstantPool(obj);
         }

         @Override
         public void visitConstantMethodref(ConstantMethodref obj)
         {
            super.visitConstantMethodref(obj);
            final String methodSignature = constantPool.constantToString(obj);
            if (CLASS_FOR_NAME.equals(methodSignature))
            {
               javaType.getAnnotation(SOURCE, true).setData(CLASS_FOR_NAME, true);
            }
            else if (CLASS_FOR_NAME_WITH_CLASSLOADER.equals(methodSignature))
            {
               javaType.getAnnotation(SOURCE, true).setData(CLASS_FOR_NAME_WITH_CLASSLOADER, true);
            }
            else if (CLASSLOADER_LOAD_CLASS.equals(methodSignature))
            {
               javaType.getAnnotation(SOURCE, true).setData(CLASSLOADER_LOAD_CLASS, true);
            }
         }
      }).visit();
   }
}
