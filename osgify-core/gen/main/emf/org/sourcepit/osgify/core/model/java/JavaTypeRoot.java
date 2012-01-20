/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import org.sourcepit.modeling.common.XAnnotatable;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Java Type Root</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.core.model.java.JavaTypeRoot#getType <em>Type</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.java.JavaTypeRoot#getParentPackage <em>Parent Package</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getJavaTypeRoot()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface JavaTypeRoot extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Type</b></em>' containment reference. <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type</em>' containment reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Type</em>' containment reference.
    * @see #setType(JavaType)
    * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getJavaTypeRoot_Type()
    * @model containment="true" required="true"
    * @generated
    */
   JavaType getType();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.java.JavaTypeRoot#getType <em>Type</em>}'
    * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Type</em>' containment reference.
    * @see #getType()
    * @generated
    */
   void setType(JavaType value);

   /**
    * Returns the value of the '<em><b>Parent Package</b></em>' container reference. It is bidirectional and its
    * opposite is '{@link org.sourcepit.osgify.core.model.java.JavaPackage#getTypeRoots <em>Type Roots</em>}'. <!--
    * begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parent Package</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Parent Package</em>' container reference.
    * @see #setParentPackage(JavaPackage)
    * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getJavaTypeRoot_ParentPackage()
    * @see org.sourcepit.osgify.core.model.java.JavaPackage#getTypeRoots
    * @model opposite="typeRoots" required="true" transient="false"
    * @generated
    */
   JavaPackage getParentPackage();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.java.JavaTypeRoot#getParentPackage
    * <em>Parent Package</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Parent Package</em>' container reference.
    * @see #getParentPackage()
    * @generated
    */
   void setParentPackage(JavaPackage value);

} // JavaTypeRoot
