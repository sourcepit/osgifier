/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import java.util.Set;

import org.apache.bcel.classfile.JavaClass;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.osgify.core.model.java.JavaType;

public class JavaTypeReferencesAnalyzer implements IJavaTypeAnalyzer
{
   public void analyze(JavaType javaType, JavaClass javaClass)
   {
      final Set<String> refs = JavaTypeReferencesCollector.collect(javaClass);
      if (!refs.isEmpty())
      {
         final Annotation annotation = javaType.getAnnotation("referencedTypes", true);
         for (String ref : refs)
         {
            annotation.getReferences().put(ref, null);
         }
      }
   }
}
