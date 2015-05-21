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

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.Set;

import org.apache.bcel.classfile.JavaClass;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgifier.core.model.java.JavaType;

public class JavaTypeReferencesAnalyzer implements IJavaTypeAnalyzer {
   public void analyze(JavaType javaType, JavaClass javaClass) {
      final String superclassName = javaClass.getSuperclassName();
      if (!isNullOrEmpty(superclassName)) {
         final Annotation annotation = javaType.getAnnotation("superclassName", true);
         annotation.getReferences().put(superclassName, null);
      }

      final String[] interfaceNames = javaClass.getInterfaceNames();
      if (interfaceNames != null && interfaceNames.length > 0) {
         final Annotation annotation = javaType.getAnnotation("interfaceNames", true);
         for (String interfaceName : interfaceNames) {
            annotation.getReferences().put(interfaceName, null);
         }
      }

      final Set<String> refs = JavaTypeReferencesCollector.collect(javaClass);
      if (!refs.isEmpty()) {
         final Annotation annotation = javaType.getAnnotation("referencedTypes", true);
         for (String ref : refs) {
            annotation.getReferences().put(ref, null);
         }
      }
   }
}
