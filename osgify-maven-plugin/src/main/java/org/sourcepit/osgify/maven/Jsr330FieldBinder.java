/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.util.HashSet;
import java.util.Set;

import org.sonatype.guice.bean.binders.QualifiedTypeBinder;
import org.sonatype.guice.bean.reflect.ClassSpace;

import com.google.inject.Binder;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class Jsr330FieldBinder extends Jsr330FieldVisitorImpl
{
   private final Binder binder;

   private final ClassSpace classSpace;

   private final Set<String> processedSignatures = new HashSet<String>();

   private final QualifiedTypeBinder qualifiedTypeBinder;

   public Jsr330FieldBinder(Binder binder, ClassSpace classSpace)
   {
      this.binder = binder;
      this.classSpace = classSpace;
      this.qualifiedTypeBinder = new QualifiedTypeBinder(binder);
   }

   @Override
   protected void visitedField(String signature, boolean hasInjectAnnotation, String namedAnnotationValue)
   {
      final int length = signature.length();
      if (hasInjectAnnotation && length > 2 && signature.charAt(0) == 'L' && signature.charAt(length - 1) == ';'
         && processedSignatures.add(signature))
      {
         if (signature.charAt(length - 2) == '>')
         {
            final int fieldTypeEnd = signature.indexOf('<');

            final String fieldType = toClassName(signature.substring(1, fieldTypeEnd));


            final String innerSignature = signature.substring(fieldTypeEnd + 1, length - 2);

            String className = toClassName(innerSignature);

            System.out.println(fieldType + " " + innerSignature + " " + className);
         }
         else
         {
            String className = toClassName(signature);
            
            
            

            System.out.println(className);
         }
      }
   }

   private String toClassName(String signature)
   {
      int length = signature.length() - 1;
      if (signature.charAt(0) == 'L' && signature.charAt(length) == ';')
      {
         return signature.substring(1, length).replace('/', '.');
      }
      return signature;
   }
}
