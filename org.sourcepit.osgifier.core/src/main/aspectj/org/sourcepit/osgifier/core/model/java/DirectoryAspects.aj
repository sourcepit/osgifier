/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
