/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.inspect;

import static org.sourcepit.modularizor.core.inspect.JavaResourceType.FILE_IN_PACKAGE;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;

import org.sourcepit.common.utils.path.Path;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.modularizor.core.model.java.File;
import org.sourcepit.modularizor.core.model.java.JavaResourceDirectory;
import org.sourcepit.modularizor.core.model.java.JavaResourcesRoot;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class PackageInfoHandler extends AbstractJavaResourceHandler
{

   public boolean handle(JavaResourcesRoot jResources, JavaResourceType type, ReadWriteLock modelLock, Path path,
      InputStream content)
   {
      if (FILE_IN_PACKAGE == type && path.getLastSegment().equals("packageinfo"))
      {
         try
         {
            final Properties props = new Properties();
            props.load(content);
            if (!props.isEmpty())
            {
               final Path parentPath = path.getParent();

               final JavaResourceDirectory jDir = parentPath == null ? jResources : getJavaPackage(jResources,
                  modelLock, parentPath);

               final File file;
               modelLock.writeLock().lock();
               try
               {
                  file = jDir.getFile(path.getLastSegment(), true);
                  final Annotation annotation = file.getAnnotation("content", true);
                  for (Entry<Object, Object> entry : props.entrySet())
                  {
                     annotation.setData((String) entry.getKey(), (String) entry.getValue());
                  }
               }
               finally
               {
                  modelLock.writeLock().unlock();
               }
            }
         }
         catch (IOException e)
         { // TODO log warning
         }
         return true;
      }
      return false;
   }
}
