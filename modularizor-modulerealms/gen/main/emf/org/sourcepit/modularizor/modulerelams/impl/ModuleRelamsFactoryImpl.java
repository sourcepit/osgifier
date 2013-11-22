/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.modularizor.modulerelams.ModuleRealm;
import org.sourcepit.modularizor.modulerelams.ModuleRealms;
import org.sourcepit.modularizor.modulerelams.ModuleReference;
import org.sourcepit.modularizor.modulerelams.ModuleRelamsFactory;
import org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ModuleRelamsFactoryImpl extends EFactoryImpl implements ModuleRelamsFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static ModuleRelamsFactory init()
   {
      try
      {
         ModuleRelamsFactory theModuleRelamsFactory = (ModuleRelamsFactory) EPackage.Registry.INSTANCE
            .getEFactory(ModuleRelamsPackage.eNS_URI);
         if (theModuleRelamsFactory != null)
         {
            return theModuleRelamsFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new ModuleRelamsFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleRelamsFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case ModuleRelamsPackage.MODULE_REALMS :
            return createModuleRealms();
         case ModuleRelamsPackage.MODULE_REALM :
            return createModuleRealm();
         case ModuleRelamsPackage.MODULE_REFERENCE :
            return createModuleReference();
         default :
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleRealms createModuleRealms()
   {
      ModuleRealmsImpl moduleRealms = new ModuleRealmsImpl();
      return moduleRealms;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleRealm createModuleRealm()
   {
      ModuleRealmImpl moduleRealm = new ModuleRealmImpl();
      return moduleRealm;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleReference createModuleReference()
   {
      ModuleReferenceImpl moduleReference = new ModuleReferenceImpl();
      return moduleReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleRelamsPackage getModuleRelamsPackage()
   {
      return (ModuleRelamsPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @deprecated
    * @generated
    */
   @Deprecated
   public static ModuleRelamsPackage getPackage()
   {
      return ModuleRelamsPackage.eINSTANCE;
   }

} // ModuleRelamsFactoryImpl
