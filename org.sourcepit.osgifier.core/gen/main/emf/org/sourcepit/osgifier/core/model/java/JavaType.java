/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.model.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaType#getInnerTypes <em>Inner Types</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaType#getOuterType <em>Outer Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaType()
 * @model
 * @generated
 */
public interface JavaType extends QualifiedJavaElement
{
   /**
    * Returns the value of the '<em><b>Inner Types</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifier.core.model.java.JavaType}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifier.core.model.java.JavaType#getOuterType
    * <em>Outer Type</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Inner Types</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Inner Types</em>' containment reference list.
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaType_InnerTypes()
    * @see org.sourcepit.osgifier.core.model.java.JavaType#getOuterType
    * @model opposite="outerType" containment="true"
    * @generated
    */
   EList<JavaType> getInnerTypes();

   /**
    * Returns the value of the '<em><b>Outer Type</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifier.core.model.java.JavaType#getInnerTypes
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
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaType_OuterType()
    * @see org.sourcepit.osgifier.core.model.java.JavaType#getInnerTypes
    * @model opposite="innerTypes"
    * @generated
    */
   JavaType getOuterType();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.java.JavaType#getOuterType <em>Outer Type</em>}'
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
