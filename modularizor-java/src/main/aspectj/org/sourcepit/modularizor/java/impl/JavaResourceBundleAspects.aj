/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java.impl;

import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.java.JavaResourceBundleOperations;
import org.sourcepit.modularizor.java.JavaResourcesRoot;
import org.sourcepit.modularizor.java.JavaType;
import org.sourcepit.modularizor.java.ResourceVisitor;



/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect JavaResourceBundleAspects
{
   pointcut getResourcesRoot(JavaResourceBundle bundle, String name): target(bundle) && args(name) && execution(JavaResourcesRoot getResourcesRoot(String));

   pointcut getResourcesRoot2(JavaResourceBundle bundle, String name, boolean createOnDemand): target(bundle) && args(name, createOnDemand) && execution(JavaResourcesRoot getResourcesRoot(String, boolean));

   pointcut getPackage(JavaResourceBundle bundle, String rootName, String qualifiedPackageName, boolean createOnDemand): target(bundle) && args(rootName, qualifiedPackageName, createOnDemand) && execution(JavaPackage getPackage(String, String, boolean));

   pointcut getType(JavaResourceBundle bundle, String rootName, String qualifiedPackageName, String typeName,
      boolean createOnDemand): target(bundle) && args(rootName, qualifiedPackageName, typeName, createOnDemand) && execution(JavaType getType(String, String, String, boolean));
   
   pointcut accept(JavaResourceBundle bundle, ResourceVisitor visitor): target(bundle) && args(visitor) && execution(void JavaResourceBundle.accept(ResourceVisitor));

   JavaResourcesRoot around(JavaResourceBundle bundle, String name) : getResourcesRoot(bundle, name){
      return JavaResourceBundleOperations.getResourcesRoot(bundle, name, false);
   }

   JavaResourcesRoot around(JavaResourceBundle bundle, String name, boolean createOnDeamnd) : getResourcesRoot2(bundle, name, createOnDeamnd){
      return JavaResourceBundleOperations.getResourcesRoot(bundle, name, createOnDeamnd);
   }

   JavaPackage around(JavaResourceBundle bundle, String rootName, String qualifiedPackageName, boolean createOnDemand) : getPackage(bundle, rootName, qualifiedPackageName, createOnDemand){
      return JavaResourceBundleOperations.getPackage(bundle, rootName, qualifiedPackageName, createOnDemand);
   }

   JavaType around(JavaResourceBundle bundle, String rootName, String qualifiedPackageName, String typeName,
      boolean createOnDemand) : getType(bundle, rootName, qualifiedPackageName, typeName, createOnDemand){
      return JavaResourceBundleOperations.getType(bundle, rootName, qualifiedPackageName, typeName, createOnDemand);
   }

   void around(JavaResourceBundle bundle, ResourceVisitor visitor) : accept(bundle, visitor){
      JavaResourceBundleOperations.accept(bundle, visitor);
   }
}
