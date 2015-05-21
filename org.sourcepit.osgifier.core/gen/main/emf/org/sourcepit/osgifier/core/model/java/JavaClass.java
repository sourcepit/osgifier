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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaClass#getMajor <em>Major</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaClass#getMinor <em>Minor</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaClass()
 * @model
 * @generated
 */
public interface JavaClass extends JavaFile {
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
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaClass_Major()
    * @model
    * @generated
    */
   int getMajor();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.java.JavaClass#getMajor <em>Major</em>}'
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
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaClass_Minor()
    * @model
    * @generated
    */
   int getMinor();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.java.JavaClass#getMinor <em>Minor</em>}'
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
