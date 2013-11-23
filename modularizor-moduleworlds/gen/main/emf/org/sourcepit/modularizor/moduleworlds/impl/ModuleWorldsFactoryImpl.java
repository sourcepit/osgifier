/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ExplodedModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleReference;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsFactory;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ModuleWorldsFactoryImpl extends EFactoryImpl implements ModuleWorldsFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static ModuleWorldsFactory init()
   {
      try
      {
         ModuleWorldsFactory theModuleWorldsFactory = (ModuleWorldsFactory) EPackage.Registry.INSTANCE
            .getEFactory(ModuleWorldsPackage.eNS_URI);
         if (theModuleWorldsFactory != null)
         {
            return theModuleWorldsFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new ModuleWorldsFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleWorldsFactoryImpl()
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
         case ModuleWorldsPackage.MODULE_WORLD :
            return createModuleWorld();
         case ModuleWorldsPackage.MODULE_REALM :
            return createModuleRealm();
         case ModuleWorldsPackage.MODULE_REFERENCE :
            return createModuleReference();
         case ModuleWorldsPackage.ASSEMBLED_MODULE :
            return createAssembledModule();
         case ModuleWorldsPackage.EXPLODED_MODULE :
            return createExplodedModule();
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
   public ModuleWorld createModuleWorld()
   {
      ModuleWorldImpl moduleWorld = new ModuleWorldImpl();
      return moduleWorld;
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
   public AssembledModule createAssembledModule()
   {
      AssembledModuleImpl assembledModule = new AssembledModuleImpl();
      return assembledModule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ExplodedModule createExplodedModule()
   {
      ExplodedModuleImpl explodedModule = new ExplodedModuleImpl();
      return explodedModule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleWorldsPackage getModuleWorldsPackage()
   {
      return (ModuleWorldsPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @deprecated
    * @generated
    */
   @Deprecated
   public static ModuleWorldsPackage getPackage()
   {
      return ModuleWorldsPackage.eINSTANCE;
   }

} // ModuleWorldsFactoryImpl
