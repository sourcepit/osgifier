/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.net.URL;

import org.sonatype.guice.bean.reflect.ClassSpace;
import org.sonatype.guice.bean.scanners.ClassSpaceVisitor;
import org.sonatype.guice.bean.scanners.QualifiedTypeVisitor;
import org.sonatype.guice.bean.scanners.asm.AnnotationVisitor;
import org.sonatype.guice.bean.scanners.asm.Attribute;
import org.sonatype.guice.bean.scanners.asm.ClassVisitor;
import org.sonatype.guice.bean.scanners.asm.FieldVisitor;
import org.sonatype.guice.bean.scanners.asm.MethodVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class QualifiedTypeVisitorDecorator implements ClassVisitor, ClassSpaceVisitor
{
   private final QualifiedTypeVisitor visitor;

   public QualifiedTypeVisitorDecorator(QualifiedTypeVisitor visitor)
   {
      this.visitor = visitor;
   }

   public void visitSource(String source, String debug)
   {
      visitor.visitSource(source, debug);
   }

   public void visitOuterClass(String owner, String name, String desc)
   {
      visitor.visitOuterClass(owner, name, desc);
   }

   public void visitAttribute(Attribute attr)
   {
      visitor.visitAttribute(attr);
   }

   public void visitInnerClass(String name, String outerName, String innerName, int access)
   {
      visitor.visitInnerClass(name, outerName, innerName, access);
   }

   public void visit(ClassSpace _space)
   {
      visitor.visit(_space);
   }

   public FieldVisitor visitField(int access, String name, String desc, String signature, Object value)
   {
      return visitor.visitField(access, name, desc, signature, value);
   }

   public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
   {
      return visitor.visitMethod(access, name, desc, signature, exceptions);
   }

   public ClassVisitor visitClass(URL url)
   {
      visitor.visitClass(url);
      return this;
   }

   public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
   {
      visitor.visit(version, access, name, signature, superName, interfaces);
   }

   public void visitEnd()
   {
      visitor.visitEnd();
   }

   public AnnotationVisitor visitAnnotation(String desc, boolean visible)
   {
      return visitor.visitAnnotation(desc, visible);
   }


}
