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
import org.sourcepit.osgifier.core.model.java.Directory;
import org.sourcepit.osgifier.core.model.java.DirectoryOperations;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.Resource;


/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect DirectoryAspects
{
   pointcut getResource(Directory dir, String name): target(dir) && args(name) && execution(Resource Directory.getResource(String));

   pointcut getDirectories(Directory dir): target(dir) && execution(EList<Directory> getDirectories());

   pointcut getDirectory(Directory dir, String name): target(dir) && args(name) && execution(Directory getDirectory(String));

   pointcut getDirectory2(Directory dir, String name, boolean createOnDemand): target(dir) && args(name, createOnDemand) && execution(Directory getDirectory(String, boolean));

   pointcut getFiles(Directory dir): target(dir) && execution(EList<File> getFiles());

   pointcut getFile(Directory dir, String name): target(dir) && args(name) && execution(File getFile(String));

   pointcut getFile2(Directory dir, String name, boolean createOnDemand): target(dir) && args(name, createOnDemand) && execution(File getFile(String, boolean));

   Resource around(Directory dir, String name) : getResource(dir, name){
      return DirectoryOperations.getResource(dir, name);
   }

   EList<Directory> around(Directory dir) : getDirectories(dir){
      return DirectoryOperations.getDirectories(dir);
   }

   Directory around(Directory dir, String name) : getDirectory(dir, name){
      return DirectoryOperations.getDirectory(dir, name, false);
   }

   Directory around(Directory dir, String name, boolean createOnDemand) : getDirectory2(dir, name, createOnDemand){
      return DirectoryOperations.getDirectory(dir, name, createOnDemand);
   }

   EList<File> around(Directory dir) : getFiles(dir){
      return DirectoryOperations.getFiles(dir);
   }

   File around(Directory dir, String name) : getFile(dir, name){
      return DirectoryOperations.getFile(dir, name, false);
   }

   File around(Directory dir, String name, boolean createOnDemand) : getFile2(dir, name, createOnDemand){
      return DirectoryOperations.getFile(dir, name, createOnDemand);
   }
}
