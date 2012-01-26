/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.net.URL;

import org.sonatype.guice.bean.reflect.ClassSpace;
import org.sonatype.guice.bean.scanners.ClassSpaceVisitor;
import org.sonatype.guice.bean.scanners.EmptyClassVisitor;
import org.sonatype.guice.bean.scanners.asm.ClassVisitor;
import org.sonatype.guice.bean.scanners.asm.FieldVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ClassSpaceVisitorImpl extends EmptyClassVisitor implements ClassSpaceVisitor
{
   private ClassSpace space;

   private URL location;

   private String source;

   private String clazzName;

   private Class<?> clazz;

   public void visit(ClassSpace space)
   {
      this.space = space;
      source = null;
   }

   public ClassVisitor visitClass(URL url)
   {
      location = url;
      clazzName = null;
      clazz = null;
      return this;
   }

   @Override
   public FieldVisitor visitField(final int access, final String name, final String desc, final String signature,
      final Object value)
   {
      // desc Ljava/util/List;
      // signature Ljava/util/List<Lorg/codehaus/plexus/logging/Logger;>;
      if (!"foo".equals(name) && !"bar".equals(name))
      {
         return null;
      }

      Jsr330FieldVisitorImpl visitor = new Jsr330FieldVisitorImpl()
      {
         @Override
         protected void visitedField(String signature, boolean hasInjectAnnotation, String namedAnnotationValue)
         {
            ClassSpaceVisitorImpl.this.visitedField(access, name, desc, signature, hasInjectAnnotation,
               namedAnnotationValue);
         }
      };
      visitor.visitField(access, name, desc, signature);
      
      return visitor;
   }

   protected void visitedField(int access, String fieldName, String signature, String fullSignature,
      boolean hasInjectAnnotation, String namedAnnotationValue)
   {

   }
}
