/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.context;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.modularizor.core.model.context.ContextModelPackage
 * @generated
 */
public interface ContextModelFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   ContextModelFactory eINSTANCE = org.sourcepit.modularizor.core.model.context.impl.ContextModelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Osgify Context</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Osgify Context</em>'.
    * @generated
    */
   OsgifyContext createOsgifyContext();

   /**
    * Returns a new object of class '<em>Bundle Candidate</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Bundle Candidate</em>'.
    * @generated
    */
   BundleCandidate createBundleCandidate();

   /**
    * Returns a new object of class '<em>Bundle Reference</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Bundle Reference</em>'.
    * @generated
    */
   BundleReference createBundleReference();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the package supported by this factory.
    * @generated
    */
   ContextModelPackage getContextModelPackage();

} // ContextModelFactory
