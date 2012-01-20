/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.inspect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.emf.ecore.resource.Resource;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.osgify.core.java.util.JavaLangUtils;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaPackageRoot;
import org.sourcepit.osgify.core.model.java.JavaType;

/**
 * @author Bernd
 */
public abstract class JavaResourceVisitor implements ResourceVisitor
{
   private static final Path PATH_MANIFEST = new Path("META-INF/MANIFEST.MF");

   public void visit(Path path, boolean isDirectory, InputStream content)
   {
      final boolean isJPackage = isDirectory && JavaLangUtils.isFullyQuallifiedPackageName(path);
      if (isJPackage)
      {
         visitJPackage(path);
      }
      else if (isInJPackage(path))
      {
         if (!isDirectory && isJClass(path))
         {
            visitJClass(path, content);
         }
         else
         {
            visitJResource(path, isDirectory, content);
         }
      }
      else
      {
         visitResource(path, isDirectory, content);
      }
   }

   private boolean isInJPackage(Path path)
   {
      return path.getParent() == null || JavaLangUtils.isFullyQuallifiedPackageName(path.getParent());
   }

   private boolean isJClass(Path path)
   {
      return "class".equals(path.getFileExtension());
   }

   private void visitJPackage(Path path)
   {
      getPackage(getJPackageName(path), true);
   }

   private void visitJClass(Path path, InputStream content)
   {
      final String jPackageName = getJPackageName(path.getParent());
      final String jTypeName = path.getFileName();
      final JavaType jType = getType(jPackageName, jTypeName, true);
      visitJType(jType, content);
   }

   private String getJPackageName(Path path)
   {
      return path == null ? "" : JavaLangUtils.toPackageName(path);
   }

   protected abstract void visitJType(JavaType javaType, InputStream content);

   private void visitJResource(Path path, boolean isDirectory, InputStream content)
   {
      if (!isDirectory && path.getLastSegment().equals("packageinfo") && isInJPackage(path))
      {
         try
         {
            final Properties props = new Properties();
            props.load(content);
            if (!props.isEmpty())
            {
               final JavaPackage pgk = getPackage(getJPackageName(path.getParent()), true);
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

   private void visitResource(Path path, boolean isDirectory, InputStream content)
   {
      if (!isDirectory && PATH_MANIFEST.equals(path))
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
