/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.model.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaResource()
 * @model abstract="true"
 * @generated
 */
public interface JavaResource extends Resource, JavaElement
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   JavaResourcesRoot getResourcesRoot();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   JavaResourceDirectory getParentDirectory();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   JavaPackage getParentPackage();

} // JavaResource