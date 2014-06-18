/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.apache.bcel.classfile.ClassFormatException;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.commons.io.IOUtils;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ClassForNameDetectorTest
{

   @Test
   public void testPositive() throws Exception
   {
      // fake usage of class for name
      Class.forName("java.lang.Object");

      Class.forName("java.lang.Object", true, getClass().getClassLoader());

      getClass().getClassLoader().loadClass("java.lang.Object");

      // parse this class
      JavaClass javaClass = parseClass(getClass());

      JavaType jType = JavaModelFactory.eINSTANCE.createJavaType();
      new ClassForNameDetector().analyze(jType, javaClass);

      assertThat(jType.getAnnotationData(ClassForNameDetector.SOURCE, ClassForNameDetector.CLASS_FOR_NAME),
         IsEqual.equalTo("true"));
      assertThat(
         jType.getAnnotationData(ClassForNameDetector.SOURCE, ClassForNameDetector.CLASS_FOR_NAME_WITH_CLASSLOADER),
         IsEqual.equalTo("true"));
      assertThat(jType.getAnnotationData(ClassForNameDetector.SOURCE, ClassForNameDetector.CLASSLOADER_LOAD_CLASS),
         IsEqual.equalTo("true"));
   }

   @Test
   public void testNegative() throws Exception
   {
      JavaClass javaClass = parseClass(Object.class);

      JavaType jType = JavaModelFactory.eINSTANCE.createJavaType();
      new ClassForNameDetector().analyze(jType, javaClass);

      assertNull(jType.getAnnotationData(ClassForNameDetector.SOURCE, ClassForNameDetector.CLASS_FOR_NAME));
      assertNull(jType.getAnnotationData(ClassForNameDetector.SOURCE,
         ClassForNameDetector.CLASS_FOR_NAME_WITH_CLASSLOADER));
      assertNull(jType.getAnnotationData(ClassForNameDetector.SOURCE, ClassForNameDetector.CLASSLOADER_LOAD_CLASS));
   }

   private InputStream openByteCodeStream(Class<?> clazz)
   {
      String qualifiedName = clazz.getName();
      String resourceName = qualifiedName.replace('.', '/') + ".class";
      return getClass().getClassLoader().getResourceAsStream(resourceName);
   }

   private JavaClass parseClass(Class<?> clazz)
   {
      InputStream inputStream = openByteCodeStream(clazz);
      try
      {
         return new ClassParser(inputStream, clazz.getName()).parse();
      }
      catch (ClassFormatException e)
      {
         throw new IllegalStateException(e);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      finally
      {
         IOUtils.closeQuietly(inputStream);
      }
   }

}
