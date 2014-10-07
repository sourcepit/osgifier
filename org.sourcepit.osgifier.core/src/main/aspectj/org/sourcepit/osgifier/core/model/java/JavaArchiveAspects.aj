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

import org.sourcepit.osgifier.core.java.internal.impl.JavaArchiveOperations;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.Resource;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect JavaArchiveAspects
{
   pointcut getResource(JavaArchive bundle, String name): target(bundle) && args(name) && execution(Resource JavaArchive.getResource(String));

   pointcut getPackage(JavaArchive bundle, String fullyQualified, boolean createOnDemand): target(bundle) && args(fullyQualified, createOnDemand) && execution(JavaPackage JavaArchive.getPackage(String, boolean));

   pointcut getType(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand): target(bundle) && args(packageName, typeName, createOnDemand) && execution(JavaType JavaArchive.getType(String, String, boolean));

   pointcut getFile(JavaArchive bundle, String name, boolean createOnDemand): target(bundle) && args(name, createOnDemand) && execution(File JavaArchive.getFile(String, boolean));

   Resource around(JavaArchive bundle, String name) : getResource(bundle, name){
      return JavaArchiveOperations.getResource(bundle, name);
   }

   JavaPackage around(JavaArchive bundle, String fullyQualified, boolean createOnDemand) : getPackage(bundle, fullyQualified, createOnDemand){
      return JavaArchiveOperations.getPackage(bundle, fullyQualified, createOnDemand);
   }

   JavaType around(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand) : getType(bundle, packageName, typeName, createOnDemand){
      return JavaArchiveOperations.getType(bundle, packageName, typeName, createOnDemand);
   }

   File around(JavaArchive bundle, String name, boolean createOnDemand) : getFile(bundle, name, createOnDemand){
      return JavaArchiveOperations.getFile(bundle, name, createOnDemand);
   }
}
