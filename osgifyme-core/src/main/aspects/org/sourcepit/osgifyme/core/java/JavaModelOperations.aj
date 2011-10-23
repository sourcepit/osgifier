/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.CommonModelOperationsAspects;
import org.sourcepit.osgifyme.core.java.internal.impl.FullyQualifiedOperations;
import org.sourcepit.osgifyme.core.java.internal.impl.PackageBundleOperations;
import org.sourcepit.osgifyme.core.java.internal.impl.PackageOperations;
import org.sourcepit.osgifyme.core.java.internal.impl.TypeOperations;

public aspect JavaModelOperations extends CommonModelOperationsAspects
{
   pointcut getRootPackages(JavaPackageBundle bundle): target(bundle) && args() && execution(EList<JavaPackage> getRootPackages());

   pointcut getPackage(JavaPackageBundle bundle, String path, String fullyQualified, boolean createOnDemand): target(bundle) && args(path, fullyQualified, createOnDemand) && execution(JavaPackage getPackage(String, String, boolean));

   pointcut getPackageOnArchive(JavaArchive bundle, String fullyQualified, boolean createOnDemand): target(bundle) && args(fullyQualified, createOnDemand) && execution(JavaPackage getPackage(String, boolean));

   pointcut getSubPackage(JavaPackage segment, String name, boolean createOnDemand): target(segment) && args(name, createOnDemand) && execution(JavaPackage getSubPackage(String, boolean));

   pointcut getFullyQualifiedPackageName(FullyQualified fullyQualified): target(fullyQualified) && args() && execution(String getFullyQualifiedName());

   pointcut getPackageBundle(JavaPackage pkg) : target(pkg) && args() && execution(JavaPackageBundle getPackageBundle());

   pointcut getTypeRoot(JavaType type) : target(type) && args() && execution(JavaTypeRoot getTypeRoot());

   pointcut getType(JavaPackageBundle bundle, String path, String packageName, String typeName, boolean createOnDemand): target(bundle) && args(path, packageName, typeName, createOnDemand) && execution(JavaType getType(String, String, String, boolean));

   pointcut getTypeOnArchive(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand): target(bundle) && args(packageName, typeName, createOnDemand) && execution(JavaType getType(String, String, boolean));

   EList<JavaPackage> around(JavaPackageBundle bundle) : getRootPackages(bundle){
      return PackageBundleOperations.getRootPackages(bundle);
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
