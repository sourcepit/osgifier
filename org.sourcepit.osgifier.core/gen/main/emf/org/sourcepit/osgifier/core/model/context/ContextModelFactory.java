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

package org.sourcepit.osgifier.core.model.context;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage
 * @generated
 */
public interface ContextModelFactory extends EFactory {
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   ContextModelFactory eINSTANCE = org.sourcepit.osgifier.core.model.context.impl.ContextModelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Osgifier Context</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Osgifier Context</em>'.
    * @generated
    */
   OsgifierContext createOsgifierContext();

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
    * Returns a new object of class '<em>Bundle Localization</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Bundle Localization</em>'.
    * @generated
    */
   BundleLocalization createBundleLocalization();

   /**
    * Returns a new object of class '<em>Localized Data</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Localized Data</em>'.
    * @generated
    */
   LocalizedData createLocalizedData();

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
