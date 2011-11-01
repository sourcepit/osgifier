/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.inspect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.tools.osgifyme.core.java.utils.JavaLangUtils;
import org.sourcepit.tools.osgifyme.core.utils.IResourceVisitor;

public abstract class JavaResourceVisitor implements IResourceVisitor
{
   public boolean visit(String path, boolean isDirectory, InputStream content)
   {
      final boolean isPackage = isDirectory && isJavaPackage(path);
      if (isPackage)
      {
         visitPackage(path);
      }
      else if (path.endsWith(".class"))
      {
         visitClassFile(path, content);
      }
      else
      {
         visitResource(path, isDirectory, content);
      }
      return isPackage;
   }

   protected boolean isJavaPackage(String path)
   {
      return JavaLangUtils.isFullyQuallifiedPackageName(path, "/");
   }

   protected void visitPackage(String path)
   {
      getPackage(JavaLangUtils.toPackageName(path), true);
   }

   protected void visitClassFile(String path, InputStream content)
   {
      final String[] result = JavaLangUtils.getPackageAndFileName(path);
      final String pkgName = result[0];
      final String typeName = result[1].substring(0, result[1].length() - 6);
      getType(pkgName, typeName, true);
   }

   protected void visitResource(String path, boolean isDirectory, InputStream content)
   {
      if (!isDirectory && path.endsWith("packageinfo"))
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
   }

   protected abstract JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand);

   protected abstract JavaType getType(String packageName, String typeName, boolean createOnDemand);
}
