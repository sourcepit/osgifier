/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Set;

import org.apache.bcel.classfile.JavaClass;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgify.core.model.java.JavaType;

public class JavaTypeReferencesAnalyzer implements IJavaTypeAnalyzer
{
   public void analyze(JavaType javaType, JavaClass javaClass)
   {
      final String superclassName = javaClass.getSuperclassName();
      if (!isNullOrEmpty(superclassName))
      {
         final Annotation annotation = javaType.getAnnotation("superclassName", true);
         annotation.getReferences().put(superclassName, null);
      }

      final String[] interfaceNames = javaClass.getInterfaceNames();
      if (interfaceNames != null && interfaceNames.length > 0)
      {
         final Annotation annotation = javaType.getAnnotation("interfaceNames", true);
         for (String interfaceName : interfaceNames)
         {
            annotation.getReferences().put(interfaceName, null);
         }
      }

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
