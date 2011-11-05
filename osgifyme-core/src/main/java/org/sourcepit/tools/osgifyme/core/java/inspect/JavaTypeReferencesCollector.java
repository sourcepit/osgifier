/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.inspect;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.classfile.JavaClass;

public class JavaTypeReferencesCollector extends EmptyVisitor
{
   private ConstantPool constantPool;

   private Set<String> refs = new LinkedHashSet<String>();

   private Set<String> ignoredRefs = new LinkedHashSet<String>();

   @NotNull
   public static Set<String> collect(@NotNull JavaClass javaClass)
   {
      JavaTypeReferencesCollector collector = new JavaTypeReferencesCollector();
      new DescendingVisitor(javaClass, collector).visit();
      collector.refs.removeAll(collector.ignoredRefs);
      return collector.refs;
   }

   @Override
   public void visitJavaClass(JavaClass obj)
   {
      constantPool = obj.getConstantPool();

      String name = obj.getClassName();
      ignoredRefs.add(name);

      int idx = name.lastIndexOf('$');
      while (idx > -1)
      {
         name = name.substring(0, idx);
         ignoredRefs.add(name);
         idx = name.lastIndexOf('$');
      }

   }

   @Override
   public void visitConstantPool(ConstantPool obj)
   {
      constantPool = obj;
   }

   @Override
   public void visitConstantClass(ConstantClass obj)
   {
      String name = (String) obj.getConstantValue(constantPool);
      refs.add(name.replace('/', '.'));
   }

   @Override
   public void visitInnerClass(InnerClass obj)
   {
      String name = constantPool.getConstantString(obj.getInnerClassIndex(), Constants.CONSTANT_Class);
      ignoredRefs.add(name.replace('/', '.'));
   }
}
