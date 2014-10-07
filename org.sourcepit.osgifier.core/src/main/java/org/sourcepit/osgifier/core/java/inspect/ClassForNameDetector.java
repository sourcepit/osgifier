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

package org.sourcepit.osgifier.core.java.inspect;

import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.JavaClass;
import org.sourcepit.osgifier.core.model.java.JavaType;

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
