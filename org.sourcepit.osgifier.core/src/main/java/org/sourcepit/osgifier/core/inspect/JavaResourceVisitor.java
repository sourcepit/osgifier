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

import static org.sourcepit.osgifier.core.inspect.JavaResourceType.CLASS_FILE;
import static org.sourcepit.osgifier.core.inspect.JavaResourceType.DIRECTORY_IN_PACKAGE;
import static org.sourcepit.osgifier.core.inspect.JavaResourceType.DIRECTORY_OUTSIDE_PACKAGE;
import static org.sourcepit.osgifier.core.inspect.JavaResourceType.FILE_IN_PACKAGE;
import static org.sourcepit.osgifier.core.inspect.JavaResourceType.FILE_OUTSIDE_PACKAGE;
import static org.sourcepit.osgifier.core.inspect.JavaResourceType.PACKAGE;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

import org.sourcepit.common.utils.path.Path;
import org.sourcepit.osgifier.core.java.util.JavaLangUtils;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.JavaType;

/**
 * @author Bernd
 */
public class JavaResourceVisitor implements ResourceVisitor {
   private final List<JavaResourceHandler> resourceHandlers = new ArrayList<JavaResourceHandler>();

   private final JavaResourceBundle jBundle;
   private final ReadWriteLock modelLock;
   private final String rootName;

   private JavaResourcesRoot jRoot;

   public JavaResourceVisitor(JavaResourceBundle jResources, String rootName, ReadWriteLock modelLock) {
      this.jBundle = jResources;
      this.rootName = rootName;
      this.modelLock = modelLock;
   }

   public List<JavaResourceHandler> getResourceHandlers() {
      return resourceHandlers;
   }

   protected JavaResourcesRoot getPackageRoot(boolean createOnDemand) {
      if (jRoot == null) {
         modelLock.writeLock().lock();
         try {
            jRoot = jBundle.getResourcesRoot(rootName, createOnDemand);
         }
         finally {
            modelLock.writeLock().unlock();
         }
      }
      return jRoot;
   }

   protected JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand) {
      final JavaResourcesRoot root = getPackageRoot(createOnDemand);
      return root == null ? null : root.getPackage(fullyQualifiedName, createOnDemand);
   }

   protected JavaType getType(String packageName, String typeName, boolean createOnDemand) {
      final JavaPackage pkg = getPackage(packageName, createOnDemand);
      return pkg == null ? null : pkg.getType(typeName, createOnDemand);
   }

   public void visit(Path path, boolean isDirectory, InputStream content) {
      final JavaResourceType type;
      final boolean isJPackage = isDirectory && JavaLangUtils.isFullyQuallifiedPackageName(path);
      if (isJPackage) {
         type = PACKAGE;
      }
      else if (isInJPackage(path)) {
         if (!isDirectory && isJClass(path)) {
            type = CLASS_FILE;
         }
         else {
            type = isDirectory ? DIRECTORY_IN_PACKAGE : FILE_IN_PACKAGE;
         }
      }
      else {
         type = isDirectory ? DIRECTORY_OUTSIDE_PACKAGE : FILE_OUTSIDE_PACKAGE;
      }
      handleResource(type, path, content);
   }

   private void handleResource(JavaResourceType type, Path path, InputStream content) {
      for (JavaResourceHandler resourceHandler : getResourceHandlers()) {
         if (resourceHandler.handle(getPackageRoot(true), type, modelLock, path, content)) {
            break;
         }
      }
   }

   private boolean isInJPackage(Path path) {
      return path.getParent() == null || JavaLangUtils.isFullyQuallifiedPackageName(path.getParent());
   }

   private boolean isJClass(Path path) {
      return "class".equals(path.getFileExtension());
   }
}
