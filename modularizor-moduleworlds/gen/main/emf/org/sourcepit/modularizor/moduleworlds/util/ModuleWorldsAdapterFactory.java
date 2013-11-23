/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.common.modeling.Annotatable;
import org.sourcepit.common.modeling.Extendable;
import org.sourcepit.common.modeling.XAnnotatable;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ExplodedModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleReference;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage
 * @generated
 */
public class ModuleWorldsAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected static ModuleWorldsPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleWorldsAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = ModuleWorldsPackage.eINSTANCE;
      }
   }

   /**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
    * This implementation returns <code>true</code> if the object is either the model's package or is an instance object
    * of the model.
    * <!-- end-user-doc -->
    * 
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object object)
   {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject) object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

   /**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ModuleWorldsSwitch<Adapter> modelSwitch = new ModuleWorldsSwitch<Adapter>()
   {
      @Override
      public Adapter caseModuleWorld(ModuleWorld object)
      {
         return createModuleWorldAdapter();
      }

      @Override
      public Adapter caseModuleRealm(ModuleRealm object)
      {
         return createModuleRealmAdapter();
      }

      @Override
      public Adapter caseModuleReference(ModuleReference object)
      {
         return createModuleReferenceAdapter();
      }

      @Override
      public Adapter caseAbstractModule(AbstractModule object)
      {
         return createAbstractModuleAdapter();
      }

      @Override
      public Adapter caseAssembledModule(AssembledModule object)
      {
         return createAssembledModuleAdapter();
      }

      @Override
      public Adapter caseExplodedModule(ExplodedModule object)
      {
         return createExplodedModuleAdapter();
      }

      @Override
      public Adapter caseExtendable(Extendable object)
      {
         return createExtendableAdapter();
      }

      @Override
      public Adapter caseAnnotatable(Annotatable object)
      {
         return createAnnotatableAdapter();
      }

      @Override
      public Adapter caseXAnnotatable(XAnnotatable object)
      {
         return createXAnnotatableAdapter();
      }

      @Override
      public Adapter defaultCase(EObject object)
      {
         return createEObjectAdapter();
      }
   };

   /**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
   @Override
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject) target);
   }


   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modularizor.moduleworlds.ModuleWorld
    * <em>Module World</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorld
    * @generated
    */
   public Adapter createModuleWorldAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm
    * <em>Module Realm</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleRealm
    * @generated
    */
   public Adapter createModuleRealmAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modularizor.moduleworlds.ModuleReference
    * <em>Module Reference</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleReference
    * @generated
    */
   public Adapter createModuleReferenceAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modularizor.moduleworlds.AbstractModule
    * <em>Abstract Module</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modularizor.moduleworlds.AbstractModule
    * @generated
    */
   public Adapter createAbstractModuleAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modularizor.moduleworlds.AssembledModule
    * <em>Assembled Module</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modularizor.moduleworlds.AssembledModule
    * @generated
    */
   public Adapter createAssembledModuleAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule
    * <em>Exploded Module</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modularizor.moduleworlds.ExplodedModule
    * @generated
    */
   public Adapter createExplodedModuleAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.common.modeling.Extendable <em>Extendable</em>}
    * '.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.common.modeling.Extendable
    * @generated
    */
   public Adapter createExtendableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.common.modeling.Annotatable
    * <em>Annotatable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.common.modeling.Annotatable
    * @generated
    */
   public Adapter createAnnotatableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.common.modeling.XAnnotatable
    * <em>XAnnotatable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.common.modeling.XAnnotatable
    * @generated
    */
   public Adapter createXAnnotatableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @generated
    */
   public Adapter createEObjectAdapter()
   {
      return null;
   }

} // ModuleWorldsAdapterFactory
