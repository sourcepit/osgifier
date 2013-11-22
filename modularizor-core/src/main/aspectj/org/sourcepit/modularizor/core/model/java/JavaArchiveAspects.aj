/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;

import org.sourcepit.modularizor.core.java.internal.impl.JavaArchiveOperations;
import org.sourcepit.modularizor.core.model.java.File;
import org.sourcepit.modularizor.core.model.java.JavaArchive;
import org.sourcepit.modularizor.core.model.java.JavaPackage;
import org.sourcepit.modularizor.core.model.java.JavaType;
import org.sourcepit.modularizor.core.model.java.Resource;

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
