/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.modulerelams.ModuleRealm;
import org.sourcepit.modularizor.modulerelams.ModuleRealms;
import org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Realms</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmsImpl#getModules <em>Modules</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmsImpl#getModuleRealms <em>Module Realms</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ModuleRealmsImpl extends XAnnotatableImpl implements ModuleRealms
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
   protected EList<JavaResourceBundle> modules;

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
   protected ModuleRealmsImpl()
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
      return ModuleRelamsPackage.Literals.MODULE_REALMS;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaResourceBundle> getModules()
   {
      if (modules == null)
      {
         modules = new EObjectContainmentEList<JavaResourceBundle>(JavaResourceBundle.class, this,
            ModuleRelamsPackage.MODULE_REALMS__MODULES);
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
            ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS, ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS);
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
         case ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS :
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
         case ModuleRelamsPackage.MODULE_REALMS__MODULES :
            return ((InternalEList<?>) getModules()).basicRemove(otherEnd, msgs);
         case ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS :
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
         case ModuleRelamsPackage.MODULE_REALMS__MODULES :
            return getModules();
         case ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS :
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
         case ModuleRelamsPackage.MODULE_REALMS__MODULES :
            getModules().clear();
            getModules().addAll((Collection<? extends JavaResourceBundle>) newValue);
            return;
         case ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS :
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
         case ModuleRelamsPackage.MODULE_REALMS__MODULES :
            getModules().clear();
            return;
         case ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS :
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
         case ModuleRelamsPackage.MODULE_REALMS__MODULES :
            return modules != null && !modules.isEmpty();
         case ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS :
            return moduleRealms != null && !moduleRealms.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // ModuleRealmsImpl
