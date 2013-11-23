/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module World</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldImpl#getModules <em>Modules</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldImpl#getModuleRealms <em>Module Realms</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ModuleWorldImpl extends XAnnotatableImpl implements ModuleWorld
{
   /**
    * The cached value of the '{@link #getModules() <em>Modules</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getModules()
    * @generated
    * @ordered
    */
   protected EList<AbstractModule> modules;

   /**
    * The cached value of the '{@link #getModuleRealms() <em>Module Realms</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getModuleRealms()
    * @generated
    * @ordered
    */
   protected EList<ModuleRealm> moduleRealms;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ModuleWorldImpl()
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
   protected EClass eStaticClass()
   {
      return ModuleWorldsPackage.Literals.MODULE_WORLD;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<AbstractModule> getModules()
   {
      if (modules == null)
      {
         modules = new EObjectContainmentWithInverseEList<AbstractModule>(AbstractModule.class, this,
            ModuleWorldsPackage.MODULE_WORLD__MODULES, ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD);
      }
      return modules;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<ModuleRealm> getModuleRealms()
   {
      if (moduleRealms == null)
      {
         moduleRealms = new EObjectContainmentWithInverseEList<ModuleRealm>(ModuleRealm.class, this,
            ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS, ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD);
      }
      return moduleRealms;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case ModuleWorldsPackage.MODULE_WORLD__MODULES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getModules()).basicAdd(otherEnd, msgs);
         case ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getModuleRealms()).basicAdd(otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case ModuleWorldsPackage.MODULE_WORLD__MODULES :
            return ((InternalEList<?>) getModules()).basicRemove(otherEnd, msgs);
         case ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS :
            return ((InternalEList<?>) getModuleRealms()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case ModuleWorldsPackage.MODULE_WORLD__MODULES :
            return getModules();
         case ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS :
            return getModuleRealms();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case ModuleWorldsPackage.MODULE_WORLD__MODULES :
            getModules().clear();
            getModules().addAll((Collection<? extends AbstractModule>) newValue);
            return;
         case ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS :
            getModuleRealms().clear();
            getModuleRealms().addAll((Collection<? extends ModuleRealm>) newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case ModuleWorldsPackage.MODULE_WORLD__MODULES :
            getModules().clear();
            return;
         case ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS :
            getModuleRealms().clear();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case ModuleWorldsPackage.MODULE_WORLD__MODULES :
            return modules != null && !modules.isEmpty();
         case ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS :
            return moduleRealms != null && !moduleRealms.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // ModuleWorldImpl
