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

package org.sourcepit.osgifier.core.model.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.osgifier.core.java.internal.impl.JavaResourceDirectoryOperations;
import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect JavaResourceDirectoryAspects
{
   pointcut getPackages(JavaResourceDirectory jDir): target(jDir) && execution(EList<JavaPackage> JavaResourceDirectory.getPackages());

   pointcut getJavaFiles(JavaResourceDirectory jDir): target(jDir) && execution(EList<JavaFile> JavaResourceDirectory.getJavaFiles());

   pointcut getPackage(JavaResourceDirectory jDir, String name): target(jDir) && args(name) && execution(JavaPackage JavaResourceDirectory.getPackage(String));

   pointcut getPackage2(JavaResourceDirectory jDir, String name, boolean createOnDemand): target(jDir) && args(name, createOnDemand) && execution(JavaPackage JavaResourceDirectory.getPackage(String, boolean));

   pointcut getJavaFile(JavaResourceDirectory jDir, String name): target(jDir) && args(name) && execution(JavaFile JavaResourceDirectory.getJavaFile(String));

   pointcut getJavaFile2(JavaResourceDirectory jDir, String name, boolean createOnDemand): target(jDir) && args(name, createOnDemand) && execution(JavaFile JavaResourceDirectory.getJavaFile(String, boolean));

   pointcut getType(JavaResourceDirectory jDir, String name): target(jDir) && args(name) && execution(JavaType JavaResourceDirectory.getType(String));

   pointcut getType2(JavaResourceDirectory jDir, String name, boolean createOnDemand): target(jDir) && args(name, createOnDemand) && execution(JavaType JavaResourceDirectory.getType(String, boolean));


   EList<JavaPackage> around(JavaResourceDirectory jDir) : getPackages(jDir){
      return JavaResourceDirectoryOperations.getPackages(jDir);
   }

   EList<JavaFile> around(JavaResourceDirectory jDir) : getJavaFiles(jDir){
      return JavaResourceDirectoryOperations.getJavaFiles(jDir);
   }

   JavaPackage around(JavaResourceDirectory segment, String name) : getPackage(segment, name){
      return JavaResourceDirectoryOperations.getPackage(segment, name, false);
   }

   JavaPackage around(JavaResourceDirectory jDir, String name, boolean createOnDemand) : getPackage2(jDir, name, createOnDemand){
      return JavaResourceDirectoryOperations.getPackage(jDir, name, createOnDemand);
   }

   JavaFile around(JavaResourceDirectory jDir, String name) : getJavaFile(jDir, name){
      return JavaResourceDirectoryOperations.getJavaFile(jDir, name, false);
   }

   JavaFile around(JavaResourceDirectory jDir, String name, boolean createOnDemand) : getJavaFile2(jDir, name, createOnDemand){
      return JavaResourceDirectoryOperations.getJavaFile(jDir, name, createOnDemand);
   }

   JavaType around(JavaResourceDirectory jDir, String name) : getType(jDir, name){
      return JavaResourceDirectoryOperations.getType(jDir, name, false);
   }

   JavaType around(JavaResourceDirectory jDir, String name, boolean createOnDemand) : getType2(jDir, name, createOnDemand){
      return JavaResourceDirectoryOperations.getType(jDir, name, createOnDemand);
   }
}
