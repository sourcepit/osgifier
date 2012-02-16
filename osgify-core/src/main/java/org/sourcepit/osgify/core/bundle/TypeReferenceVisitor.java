/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.sourcepit.modeling.common.Annotation;
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
         for (Entry<String, EObject> entry : annotation.getReferences())
         {
            foundTypeReference(jType, entry.getKey());
         }
      }

      for (JavaType innerJType : jType.getInnerTypes())
      {
         visit(innerJType);
      }
   }

   protected abstract void foundTypeReference(JavaType jType, String qualifiedName);
}