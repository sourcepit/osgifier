/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.emf.ecore.resource.Resource;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.osgify.core.java.util.JavaLangUtils;
import org.sourcepit.osgify.core.util.IResourceVisitor;
import org.sourcepit.osgify.java.JavaPackage;
import org.sourcepit.osgify.java.JavaPackageRoot;
import org.sourcepit.osgify.java.JavaType;

public abstract class JavaResourceVisitor implements IResourceVisitor
{
   public boolean visit(String path, boolean isDirectory, InputStream content)
   {
      final boolean isPackage = isDirectory && isJavaPackage(path);
      if (isPackage)
      {
         visitPackage(path);
      }
      else if (isJavaClassFile(path))
      {
         visitClassFile(path, content);
      }
      else
      {
         visitResource(path, isDirectory, content);
      }
      return true;
   }

   protected boolean isJavaPackage(String path)
   {
      return JavaLangUtils.isFullyQuallifiedPackageName(path, "/");
   }

   protected boolean isJavaClassFile(String path)
   {
      return path.endsWith(".class") && isInJavaPackage(path);
   }

   protected boolean isInJavaPackage(String path)
   {
      return JavaLangUtils.isFullyQuallifiedPackageName(JavaLangUtils.trimFileName(path)[0], "/");
   }

   protected void visitPackage(String path)
   {
      getPackage(JavaLangUtils.toPackageName(path), true);
   }

   protected void visitClassFile(String path, InputStream content)
   {
      final String[] result = JavaLangUtils.getPackageAndFileName(path);
      final String pkgName = result[0];
      final String typeName = result[1];
      JavaType javaType = getType(pkgName, typeName, true);

      visitType(javaType, content);
   }

   protected abstract void visitType(JavaType javaType, InputStream content);

   protected void visitResource(String path, boolean isDirectory, InputStream content)
   {
      if (!isDirectory && path.endsWith("packageinfo") && isInJavaPackage(path))
      {
         try
         {
            final Properties props = new Properties();
            props.load(content);
            if (!props.isEmpty())
            {
               final JavaPackage pgk = getPackage(JavaLangUtils.getPackageAndFileName(path)[0], true);
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

      if (!isDirectory && path.equals("META-INF/MANIFEST.MF"))
      {
         Resource resource = new GenericManifestResourceImpl();
         try
         {
            resource.load(content, null);
            Manifest manifest = (Manifest) resource.getContents().get(0);
            getPackageRoot(true).addExtension(manifest);
         }
         catch (IOException e)
         {// TODO log warning
         }
      }
   }

   protected abstract JavaPackageRoot getPackageRoot(boolean createOnDemand);

   protected abstract JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand);

   protected abstract JavaType getType(String packageName, String typeName, boolean createOnDemand);
}
