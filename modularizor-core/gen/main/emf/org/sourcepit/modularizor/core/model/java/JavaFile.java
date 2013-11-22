/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java File</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.core.model.java.JavaFile#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaFile()
 * @model abstract="true"
 * @generated
 */
public interface JavaFile extends JavaResource, File
{
   /**
    * Returns the value of the '<em><b>Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' containment reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Type</em>' containment reference.
    * @see #setType(JavaType)
    * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaFile_Type()
    * @model containment="true" required="true"
    * @generated
    */
   JavaType getType();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.core.model.java.JavaFile#getType <em>Type</em>}'
    * containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Type</em>' containment reference.
    * @see #getType()
    * @generated
    */
   void setType(JavaType value);

} // JavaFile
