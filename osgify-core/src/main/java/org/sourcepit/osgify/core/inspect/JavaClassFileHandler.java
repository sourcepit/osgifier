/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.inspect;


import static org.sourcepit.osgify.core.inspect.JavaResourceType.CLASS_FILE;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgify.core.java.inspect.IJavaTypeAnalyzer;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaClassFileHandler extends AbstractJavaResourceHandler
{
   private final List<IJavaTypeAnalyzer> typeAnalyzers = new ArrayList<IJavaTypeAnalyzer>();

   public List<IJavaTypeAnalyzer> getTypeAnalyzers()
   {
      return typeAnalyzers;
   }

   public boolean handle(JavaResourcesRoot jResources, JavaResourceType type, ReadWriteLock modelLock, Path path,
      InputStream content)
   {
      if (CLASS_FILE != type)
      {
         return false;
      }
      final JavaType javaType = getJavaType(jResources, modelLock, path);
      analyzeType(javaType, content);
      return true;
   }

   private void analyzeType(final JavaType javaType, InputStream content)
   {
      final JavaClass javaClass = parseClass(javaType, content);
      if (javaClass == null)
      {
         return;
      }

      org.sourcepit.osgify.core.model.java.JavaClass jClass = (org.sourcepit.osgify.core.model.java.JavaClass) javaType
         .getFile();
      jClass.setMajor(javaClass.getMajor());
      jClass.setMinor(javaClass.getMinor());

      if (!typeAnalyzers.isEmpty())
      {
         for (IJavaTypeAnalyzer analyzer : typeAnalyzers)
         {
            analyzer.analyze(javaType, javaClass);
         }
      }
   }

   private JavaClass parseClass(final JavaType javaType, InputStream content)
   {
      final JavaClass javaClass;
      try
      {
         javaClass = new ClassParser(content, javaType.getQualifiedName()).parse();
      }
      catch (ClassFormatException e)
      {
         e.printStackTrace();
         // throw new IllegalStateException(e);
         return null;
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      return javaClass;
   }

}
