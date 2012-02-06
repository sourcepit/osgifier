/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import org.sourcepit.osgify.core.java.internal.impl.JavaArchiveOperations;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect JavaArchiveAspects
{
   pointcut getPackage(JavaArchive bundle, String fullyQualified, boolean createOnDemand): target(bundle) && args(fullyQualified, createOnDemand) && execution(JavaPackage JavaArchive.getPackage(String, boolean));

   pointcut getType(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand): target(bundle) && args(packageName, typeName, createOnDemand) && execution(JavaType JavaArchive.getType(String, String, boolean));

   JavaPackage around(JavaArchive bundle, String fullyQualified, boolean createOnDemand) : getPackage(bundle, fullyQualified, createOnDemand){
      return JavaArchiveOperations.getPackage(bundle, fullyQualified, createOnDemand);
   }

   JavaType around(JavaArchive bundle, String packageName, String typeName, boolean createOnDemand) : getType(bundle, packageName, typeName, createOnDemand){
      return JavaArchiveOperations.getType(bundle, packageName, typeName, createOnDemand);
   }
}
