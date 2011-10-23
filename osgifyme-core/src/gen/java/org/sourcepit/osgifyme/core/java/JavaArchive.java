/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Archive</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaArchive()
 * @model
 * @generated
 */
public interface JavaArchive extends JavaPackageBundle
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaType getType(String packageName, String typeName, boolean createOnDemand);

} // JavaArchive
