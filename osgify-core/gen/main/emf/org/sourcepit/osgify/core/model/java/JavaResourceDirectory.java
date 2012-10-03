/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Resource Directory</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getJavaResourceDirectory()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface JavaResourceDirectory extends JavaElement, Directory
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   EList<JavaPackage> getPackages();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   EList<JavaFile> getJavaFiles();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   EList<Resource> getResources();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaPackage getPackage(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaPackage getPackage(String name, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaFile getJavaFile(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaFile getJavaFile(String name, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaType getType(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaType getType(String name, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   JavaResourcesType getResourcesType();

} // JavaResourceDirectory
