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

import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;

import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class DefaultFileHandler extends AbstractJavaResourceHandler {
   private boolean createEmptyDirectories = true;

   private boolean createEmptyFiles = true;

   public boolean isCreateEmptyDirectories() {
      return createEmptyDirectories;
   }

   public void setCreateEmptyDirectories(boolean createEmptyDirectories) {
      this.createEmptyDirectories = createEmptyDirectories;
   }

   public boolean isCreateEmptyFiles() {
      return createEmptyFiles;
   }

   public void setCreateEmptyFiles(boolean createEmptyFiles) {
      this.createEmptyFiles = createEmptyFiles;
   }

   public boolean handle(JavaResourcesRoot jResources, JavaResourceType type, ReadWriteLock modelLock, Path path,
      InputStream content) {
      switch (type) {
         case DIRECTORY_IN_PACKAGE :
            if (isCreateEmptyDirectories()) {
               getJavaPackage(jResources, modelLock, path);
            }
            return true;
         case DIRECTORY_OUTSIDE_PACKAGE :
            if (isCreateEmptyDirectories()) {
               getDirectory(jResources, modelLock, path);
            }
            return true;
         case FILE_IN_PACKAGE :
            if (isCreateEmptyFiles()) {
               getFileInJavaPackage(jResources, modelLock, path);
            }
            return true;
         case FILE_OUTSIDE_PACKAGE :
            if (isCreateEmptyFiles()) {
               getFile(jResources, modelLock, path);
            }
            return true;
         default :
            return false;
      }
   }
}
