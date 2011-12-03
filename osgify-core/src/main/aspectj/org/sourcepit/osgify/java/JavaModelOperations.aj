/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.CommonModelOperationsAspects;
import org.sourcepit.osgify.core.java.internal.impl.FullyQualifiedOperations;
import org.sourcepit.osgify.core.java.internal.impl.PackageBundleOperations;
import org.sourcepit.osgify.core.java.internal.impl.PackageOperations;
import org.sourcepit.osgify.core.java.internal.impl.TypeOperations;

public aspect JavaModelOperations extends CommonModelOperationsAspects
{
   pointcut getPackageRoot(JavaPackageBundle bundle, String path): target(bundle) && args(path) && execution(JavaPackageRoot getPackageRoot(String));

   pointcut getPackageRootCreateOnDemand(JavaPackageBundle bundle, String path, boolean createOnDemand): target(bundle) && args(path, createOnDemand) && execution(JavaPackageRoot getPackageRoot(String, boolean));

   pointcut getRootPackages(JavaPackageBundle bundle, String path): target(bundle) && args(path) && execution(EList<JavaPackage> getRootPackages(String));

   pointcut getPackage(JavaPackageBundle bundle, String path, String fullyQualified, boolean createOnDemand): target(bundle) && args(path, fullyQualified, createOnDemand) && execution(JavaPackage getPackage(String, String, boolean));

   pointcut getPackageOnArchive(JavaArchive bundle, String fullyQualified, boolean createOnDemand): target(bundle) && args(fullyQualified, createOnDemand) && execution(JavaPackage getPackage(String, boolean));

   pointcut getPackageRootForPackage(JavaPackage pkg): target(pkg) && execution(JavaPackageRoot getPackageRoot());

   pointcut getSubPackage(JavaPackage segment, String name, boolean createOnDemand): target(segment) && args(name, createOnDemand) && execution(JavaPackage getSubPackage(String, boolean));

   pointcut getFullyQualifiedPackageName(FullyQualified fullyQualified): target(fullyQualified) && args() && execution(String getFullyQualifiedName());

   pointcut getPackageBundle(JavaPackage pkg) : target(pkg) && args() && execution(JavaPackageBundle getPackageBundle());

   pointcut getTypeRoot(JavaType type) : target(type) && args() && execution(JavaTypeRoot getTypeRoot());

   pointcut getType(JavaPackageBundle bundle, String path, String packageName, String typeName, boolean createOnDemand): target(bundle) && args(path, packageName, typeName, createOnDemand) && execution(JavaType getType(String, String, String, boolean));

   pointcut getTypeOnArchive(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand): target(bundle) && args(packageName, typeName, createOnDemand) && execution(JavaType getType(String, String, boolean));

   JavaPackageRoot around(JavaPackageBundle bundle, String path) : getPackageRoot(bundle, path){
      return PackageBundleOperations.getPackageRoot(bundle, path);
   }

   JavaPackageRoot around(JavaPackageBundle bundle, String path, boolean createOnDeamnd) : getPackageRootCreateOnDemand(bundle, path, createOnDeamnd){
      return PackageBundleOperations.getPackageRoot(bundle, path, createOnDeamnd);
   }

   EList<JavaPackage> around(JavaPackageBundle bundle, String path) : getRootPackages(bundle, path){
      return PackageBundleOperations.getRootPackages(bundle, path);
   }

   JavaPackage around(JavaPackageBundle bundle, String path, String fullyQualified, boolean createOnDemand) : getPackage(bundle, path, fullyQualified, createOnDemand){
      return PackageBundleOperations.getPackage(bundle, path, fullyQualified, createOnDemand);
   }

   JavaPackage around(JavaArchive bundle, String fullyQualified, boolean createOnDemand) : getPackageOnArchive(bundle, fullyQualified, createOnDemand){
      return PackageBundleOperations.getPackage(bundle, fullyQualified, createOnDemand);
   }

   JavaType around(JavaPackageBundle bundle, String path, String packageName, String typeName, boolean createOnDemand) : getType(bundle, path, packageName, typeName, createOnDemand){
      return PackageBundleOperations.getType(bundle, path, packageName, typeName, createOnDemand);
   }

   JavaType around(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand) : getTypeOnArchive(bundle, packageName, typeName, createOnDemand){
      return PackageBundleOperations.getType(bundle, packageName, typeName, createOnDemand);
   }

   JavaPackageRoot around(JavaPackage pkg) : getPackageRootForPackage(pkg){
      return PackageOperations.getPackageRoot(pkg);
   }

   JavaPackage around(JavaPackage segment, String name, boolean createOnDemand) : getSubPackage(segment, name, createOnDemand){
      return PackageBundleOperations.getSubPackage(segment, name, createOnDemand);
   }

   String around(FullyQualified fullyQualified) : getFullyQualifiedPackageName(fullyQualified){
      return FullyQualifiedOperations.getFullyQualifiedName(fullyQualified);
   }

   JavaPackageBundle around(JavaPackage pkg): getPackageBundle(pkg){
      return PackageOperations.getPackageBundle(pkg);
   }

   JavaTypeRoot around(JavaType type): getTypeRoot(type){
      return TypeOperations.getTypeRoot(type);
   }
}
