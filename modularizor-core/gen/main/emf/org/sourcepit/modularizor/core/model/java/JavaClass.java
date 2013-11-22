/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Class</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.core.model.java.JavaClass#getMajor <em>Major</em>}</li>
 * <li>{@link org.sourcepit.modularizor.core.model.java.JavaClass#getMinor <em>Minor</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaClass()
 * @model
 * @generated
 */
public interface JavaClass extends JavaFile
{
   /**
    * Returns the value of the '<em><b>Major</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Major</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Major</em>' attribute.
    * @see #setMajor(int)
    * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaClass_Major()
    * @model
    * @generated
    */
   int getMajor();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.core.model.java.JavaClass#getMajor <em>Major</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Major</em>' attribute.
    * @see #getMajor()
    * @generated
    */
   void setMajor(int value);

   /**
    * Returns the value of the '<em><b>Minor</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Minor</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Minor</em>' attribute.
    * @see #setMinor(int)
    * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaClass_Minor()
    * @model
    * @generated
    */
   int getMinor();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.core.model.java.JavaClass#getMinor <em>Minor</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Minor</em>' attribute.
    * @see #getMinor()
    * @generated
    */
   void setMinor(int value);

} // JavaClass
