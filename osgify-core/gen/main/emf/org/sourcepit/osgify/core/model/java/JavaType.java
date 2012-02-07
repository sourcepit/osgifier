/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Type</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.core.model.java.JavaType#getInnerTypes <em>Inner Types</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.java.JavaType#getOuterType <em>Outer Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getJavaType()
 * @model
 * @generated
 */
public interface JavaType extends QualifiedJavaElement
{
   /**
    * Returns the value of the '<em><b>Inner Types</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.core.model.java.JavaType}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgify.core.model.java.JavaType#getOuterType
    * <em>Outer Type</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Inner Types</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Inner Types</em>' containment reference list.
    * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getJavaType_InnerTypes()
    * @see org.sourcepit.osgify.core.model.java.JavaType#getOuterType
    * @model opposite="outerType" containment="true"
    * @generated
    */
   EList<JavaType> getInnerTypes();

   /**
    * Returns the value of the '<em><b>Outer Type</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgify.core.model.java.JavaType#getInnerTypes
    * <em>Inner Types</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Outer Type</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Outer Type</em>' container reference.
    * @see #setOuterType(JavaType)
    * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getJavaType_OuterType()
    * @see org.sourcepit.osgify.core.model.java.JavaType#getInnerTypes
    * @model opposite="innerTypes"
    * @generated
    */
   JavaType getOuterType();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.java.JavaType#getOuterType <em>Outer Type</em>}'
    * container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Outer Type</em>' container reference.
    * @see #getOuterType()
    * @generated
    */
   void setOuterType(JavaType value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   JavaFile getFile();

} // JavaType
