/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage
 * @generated
 */
public interface JavaModelFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   JavaModelFactory eINSTANCE = org.sourcepit.osgifyme.core.java.internal.impl.JavaModelFactoryImpl.init();

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
    * Returns a new object of class '<em>Java Class</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Class</em>'.
    * @generated
    */
   JavaClass createJavaClass();

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
    * Returns a new object of class '<em>Java Archive</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Archive</em>'.
    * @generated
    */
   JavaArchive createJavaArchive();

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
    * Returns a new object of class '<em>Java Model</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Java Model</em>'.
    * @generated
    */
   JavaModel createJavaModel();

   /**
    * Returns a new object of class '<em>Dependency Node</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Dependency Node</em>'.
    * @generated
    */
   DependencyNode createDependencyNode();

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
