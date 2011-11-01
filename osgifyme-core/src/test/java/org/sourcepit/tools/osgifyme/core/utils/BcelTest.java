/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.DescendingVisitor;
import org.apache.bcel.classfile.EmptyVisitor;
import org.apache.bcel.classfile.InnerClass;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassLoaderRepository;
import org.junit.Test;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.osgifyme.core.java.JavaArchive;
import org.sourcepit.osgifyme.core.java.JavaModel;
import org.sourcepit.osgifyme.core.java.JavaModelFactory;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.tools.osgifyme.core.java.utils.JavaLangUtils;
import org.sourcepit.tools.osgifyme.test.resources.TypeA;

public class BcelTest
{
   @Test
   public void testBcel() throws Exception
   {
      File jarFile = new File("target/testResources/osgifyme-core.jar");
      assertTrue(jarFile.exists());

      URLClassLoader cl = new URLClassLoader(new URL[] {jarFile.toURL()});

      final ClassLoaderRepository repo = new ClassLoaderRepository(cl);

      final JavaModel javaModel = JavaModelFactory.eINSTANCE.createJavaModel();

      final JavaArchive javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      new ZipTraverser(jarFile).travers(new IResourceVisitor()
      {
         public boolean visit(String path, boolean isDirectory, InputStream content)
         {
            final boolean isPackage = isDirectory && JavaLangUtils.isFullyQuallifiedPackageName(path, "/");
            if (isPackage)
            {
               javaArchive.getPackage(toPackageName(path), true);
            }
            else if (path.endsWith(".class"))
            {
               final String[] result = getPackageAndFileName(path);
               final String pkgName = result[0];
               final String typeName = result[1].substring(0, result[1].length() - 6);
               javaArchive.getType(pkgName, typeName, true);
            }
            else if (path.endsWith("packageinfo"))
            {
               try
               {
                  final Properties props = new Properties();
                  props.load(content);
                  if (!props.isEmpty())
                  {
                     final JavaPackage pgk = javaArchive.getPackage(getPackageAndFileName(path)[0], true);
                     final Annotation annotation = pgk.getAnnotation("packageinfo", true);
                     for (Entry<Object, Object> entry : props.entrySet())
                     {
                        annotation.setData((String) entry.getKey(), (String) entry.getValue());
                     }
                  }
               }
               catch (IOException e)
               { // TODO log warning
               }
            }

            return isPackage;
         }

         private String toPackageName(String path)
         {
            String pkgName = path.replace('/', '.');
            if (pkgName.endsWith("."))
            {
               pkgName = pkgName.substring(0, pkgName.length() - 1);
            }
            return pkgName;
         }

         private String[] getPackageAndFileName(String path)
         {
            int idx = path.lastIndexOf("/");
            final String pkgName;
            final String typeName;
            if (idx == -1)
            {
               pkgName = "";
               typeName = path;
            }
            else
            {
               pkgName = toPackageName(path.substring(0, idx));
               typeName = path.substring(idx + 1);
            }
            return new String[] {pkgName, typeName};
         }

      });


      JavaClass jc = repo.loadClass(TypeA.class.getName());

      jc = repo.loadClass(TypeA.Hans.class.getName());


      new DescendingVisitor(jc, new EmptyVisitor()
      {
         private ConstantPool cp;

         @Override
         public void visitJavaClass(JavaClass obj)
         {
            cp = obj.getConstantPool();
         }

         @Override
         public void visitConstantPool(ConstantPool obj)
         {
            cp = obj;
         }

         @Override
         public void visitConstantClass(ConstantClass obj)
         {
            String name = (String) obj.getConstantValue(cp);
            System.out.println("visitConstantClass " + name);
         }

         @Override
         public void visitInnerClass(InnerClass obj)
         {
            String name = cp.getConstantString(obj.getInnerClassIndex(), Constants.CONSTANT_Class);
            System.out.println("visitInnerClass " + name);
         }

      }).visit();

      System.out.println();
   }
}
