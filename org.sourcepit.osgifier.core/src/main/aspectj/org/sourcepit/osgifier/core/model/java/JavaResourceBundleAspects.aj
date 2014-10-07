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

import org.sourcepit.osgifier.core.java.internal.impl.JavaResourceBundleOperations;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.ResourceVisitor;


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
