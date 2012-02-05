/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import java.io.IOException;
import java.io.InputStream;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.sourcepit.osgify.core.model.java.JavaType;

public abstract class AbstractJavaTypeAnalyzer implements IJavaTypeAnalyzer
{

   public void analyze(JavaType javaType, InputStream content)
   {
      final JavaClass javaClass;
      try
      {
         javaClass = new ClassParser(content, javaType.getQualifiedName()).parse();
      }
      catch (ClassFormatException e)
      {
         throw new IllegalStateException(e);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      analyze(javaType, javaClass);
   }

   protected abstract void analyze(JavaType javaType, JavaClass javaClass);

}
