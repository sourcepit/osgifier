/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java;

import java.util.Set;

import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgify.core.java.TypeVisitor;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class TypeReferenceVisitor extends TypeVisitor implements ResourceVisitor
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