/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.Annotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Type</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.JavaType#getInnerTypes <em>Inner Types</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.JavaType#getOuterType <em>Outer Type</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.JavaType#getSimpleName <em>Simple Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaType()
 * @model
 * @generated
 */
public interface JavaType extends Annotatable, FullyQualified
{
   /**
    * Returns the value of the '<em><b>Inner Types</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifyme.core.java.JavaType}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifyme.core.java.JavaType#getOuterType
    * <em>Outer Type</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Inner Types</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Inner Types</em>' containment reference list.
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaType_InnerTypes()
    * @see org.sourcepit.osgifyme.core.java.JavaType#getOuterType
    * @model opposite="outerType" containment="true"
    * @generated
    */
   EList<JavaType> getInnerTypes();

   /**
    * Returns the value of the '<em><b>Outer Type</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifyme.core.java.JavaType#getInnerTypes
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
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaType_OuterType()
    * @see org.sourcepit.osgifyme.core.java.JavaType#getInnerTypes
    * @model opposite="innerTypes"
    * @generated
    */
   JavaType getOuterType();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.JavaType#getOuterType <em>Outer Type</em>}'
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
    * Returns the value of the '<em><b>Simple Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Simple Name</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Simple Name</em>' attribute.
    * @see #setSimpleName(String)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaType_SimpleName()
    * @model required="true"
    * @generated
    */
   String getSimpleName();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.JavaType#getSimpleName <em>Simple Name</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Simple Name</em>' attribute.
    * @see #getSimpleName()
    * @generated
    */
   void setSimpleName(String value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   JavaTypeRoot getTypeRoot();

} // JavaType
