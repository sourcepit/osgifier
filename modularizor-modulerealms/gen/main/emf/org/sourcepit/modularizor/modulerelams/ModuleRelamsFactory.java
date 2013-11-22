/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage
 * @generated
 */
public interface ModuleRelamsFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   ModuleRelamsFactory eINSTANCE = org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Module Realms</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Module Realms</em>'.
    * @generated
    */
   ModuleRealms createModuleRealms();

   /**
    * Returns a new object of class '<em>Module Realm</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Module Realm</em>'.
    * @generated
    */
   ModuleRealm createModuleRealm();

   /**
    * Returns a new object of class '<em>Module Reference</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Module Reference</em>'.
    * @generated
    */
   ModuleReference createModuleReference();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the package supported by this factory.
    * @generated
    */
   ModuleRelamsPackage getModuleRelamsPackage();

} // ModuleRelamsFactory
