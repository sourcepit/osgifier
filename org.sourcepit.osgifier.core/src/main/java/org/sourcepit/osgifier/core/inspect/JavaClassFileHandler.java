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

package org.sourcepit.osgifier.core.inspect;


import static org.sourcepit.osgifier.core.inspect.JavaResourceType.CLASS_FILE;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgifier.core.java.inspect.IJavaTypeAnalyzer;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaClassFileHandler extends AbstractJavaResourceHandler
{
   private final static Logger LOG = LoggerFactory.getLogger(JavaClassFileHandler.class);

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

      org.sourcepit.osgifier.core.model.java.JavaClass jClass = (org.sourcepit.osgifier.core.model.java.JavaClass) javaType
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
         LOG.warn("Faild to parse class {}.", javaType.getQualifiedName(), e);
         return null;
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      return javaClass;
   }

}
