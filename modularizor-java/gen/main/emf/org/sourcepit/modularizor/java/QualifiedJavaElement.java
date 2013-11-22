/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qualified Java Element</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see org.sourcepit.modularizor.java.JavaModelPackage#getQualifiedJavaElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface QualifiedJavaElement extends JavaElement
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   String getQualifiedName();

} // QualifiedJavaElement
