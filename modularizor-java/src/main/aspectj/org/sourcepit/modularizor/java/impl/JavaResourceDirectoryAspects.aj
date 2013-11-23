/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java.impl;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modularizor.java.JavaFile;
import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResourceDirectory;
import org.sourcepit.modularizor.java.JavaResourceDirectoryOperations;
import org.sourcepit.modularizor.java.JavaType;

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
