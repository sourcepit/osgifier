/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.inspect;

import static org.sourcepit.osgifier.core.inspect.JavaResourceType.FILE_IN_PACKAGE;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.locks.ReadWriteLock;

import org.sourcepit.common.utils.path.Path;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;

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
