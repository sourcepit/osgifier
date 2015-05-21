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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage
 * @generated
 */
public interface JavaModelFactory extends EFactory {
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   JavaModelFactory eINSTANCE = org.sourcepit.osgifier.core.model.java.impl.JavaModelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Directory</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Directory</em>'.
    * @generated
    */
   Directory createDirectory();

   /**
    * Returns a new object of class '<em>File</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>File</em>'.
    * @generated
    */
   File createFile();

   /**
    * Returns a new object of class '<em>Java Project</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Project</em>'.
    * @generated
    */
   JavaProject createJavaProject();

   /**
    * Returns a new object of class '<em>Java Archive</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Archive</em>'.
    * @generated
    */
   JavaArchive createJavaArchive();

   /**
    * Returns a new object of class '<em>Java Resources Root</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Resources Root</em>'.
    * @generated
    */
   JavaResourcesRoot createJavaResourcesRoot();

   /**
    * Returns a new object of class '<em>Java Package</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Package</em>'.
    * @generated
    */
   JavaPackage createJavaPackage();

   /**
    * Returns a new object of class '<em>Java Class</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Class</em>'.
    * @generated
    */
   JavaClass createJavaClass();

   /**
    * Returns a new object of class '<em>Java Compilation Unit</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Compilation Unit</em>'.
    * @generated
    */
   JavaCompilationUnit createJavaCompilationUnit();

   /**
    * Returns a new object of class '<em>Import Declaration</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Import Declaration</em>'.
    * @generated
    */
   ImportDeclaration createImportDeclaration();

   /**
    * Returns a new object of class '<em>Java Type</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Type</em>'.
    * @generated
    */
   JavaType createJavaType();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the package supported by this factory.
    * @generated
    */
   JavaModelPackage getJavaModelPackage();

} // JavaModelFactory
