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

package org.sourcepit.osgifier.core.java;

import java.util.Set;

import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.JavaTypeVisitor;
import org.sourcepit.osgifier.core.model.java.ResourceVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class TypeReferenceVisitor extends JavaTypeVisitor implements ResourceVisitor
{
   @Override
   protected void visit(JavaType jType)
   {
      final Annotation annotation = jType.getAnnotation("referencedTypes");
      if (annotation != null)
      {
         final Set<String> qualifiedNames = annotation.getReferences().keySet();
         foundTypeReference(jType, qualifiedNames);
      }

      for (JavaType innerJType : jType.getInnerTypes())
      {
         visit(innerJType);
      }
   }

   protected abstract void foundTypeReference(JavaType jType, Set<String> qualifiedNames);
}