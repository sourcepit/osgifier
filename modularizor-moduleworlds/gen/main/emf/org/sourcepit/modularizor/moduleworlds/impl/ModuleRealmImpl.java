/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleReference;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Realm</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleRealmImpl#getModuleWorld <em>Module World</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleRealmImpl#getModule <em>Module</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleRealmImpl#getReferencedModules <em>Referenced Modules
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ModuleRealmImpl extends XAnnotatableImpl implements ModuleRealm
{
   /**
    * The cached value of the '{@link #getModule() <em>Module</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getModule()
    * @generated
    * @ordered
    */
   protected AbstractModule module;

   /**
    * The cached value of the '{@link #getReferencedModules() <em>Referenced Modules</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getReferencedModules()
    * @generated
    * @ordered
    */
   protected EList<ModuleReference> referencedModules;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ModuleRealmImpl()
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
      return ModuleWorldsPackage.Literals.MODULE_REALM;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleWorld getModuleWorld()
   {
      if (eContainerFeatureID() != ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD)
         return null;
      return (ModuleWorld) eInternalContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetModuleWorld(ModuleWorld newModuleWorld, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newModuleWorld, ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setModuleWorld(ModuleWorld newModuleWorld)
   {
      if (newModuleWorld != eInternalContainer()
         || (eContainerFeatureID() != ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD && newModuleWorld != null))
      {
         if (EcoreUtil.isAncestor(this, newModuleWorld))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newModuleWorld != null)
            msgs = ((InternalEObject) newModuleWorld).eInverseAdd(this,
               ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS, ModuleWorld.class, msgs);
         msgs = basicSetModuleWorld(newModuleWorld, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD,
            newModuleWorld, newModuleWorld));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public AbstractModule getModule()
   {
      if (module != null && module.eIsProxy())
      {
         InternalEObject oldModule = (InternalEObject) module;
         module = (AbstractModule) eResolveProxy(oldModule);
         if (module != oldModule)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModuleWorldsPackage.MODULE_REALM__MODULE,
                  oldModule, module));
         }
      }
      return module;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public AbstractModule basicGetModule()
   {
      return module;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setModule(AbstractModule newModule)
   {
      AbstractModule oldModule = module;
      module = newModule;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.MODULE_REALM__MODULE, oldModule,
            module));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<ModuleReference> getReferencedModules()
   {
      if (referencedModules == null)
      {
         referencedModules = new EObjectContainmentWithInverseEList<ModuleReference>(ModuleReference.class, this,
            ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES, ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM);
      }
      return referencedModules;
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
         case ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetModuleWorld((ModuleWorld) otherEnd, msgs);
         case ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getReferencedModules())
               .basicAdd(otherEnd, msgs);
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
         case ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD :
            return basicSetModuleWorld(null, msgs);
         case ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES :
            return ((InternalEList<?>) getReferencedModules()).basicRemove(otherEnd, msgs);
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
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
   {
      switch (eContainerFeatureID())
      {
         case ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD :
            return eInternalContainer().eInverseRemove(this, ModuleWorldsPackage.MODULE_WORLD__MODULE_REALMS,
               ModuleWorld.class, msgs);
      }
      return super.eBasicRemoveFromContainerFeature(msgs);
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
         case ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD :
            return getModuleWorld();
         case ModuleWorldsPackage.MODULE_REALM__MODULE :
            if (resolve)
               return getModule();
            return basicGetModule();
         case ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES :
            return getReferencedModules();
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
         case ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD :
            setModuleWorld((ModuleWorld) newValue);
            return;
         case ModuleWorldsPackage.MODULE_REALM__MODULE :
            setModule((AbstractModule) newValue);
            return;
         case ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES :
            getReferencedModules().clear();
            getReferencedModules().addAll((Collection<? extends ModuleReference>) newValue);
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
         case ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD :
            setModuleWorld((ModuleWorld) null);
            return;
         case ModuleWorldsPackage.MODULE_REALM__MODULE :
            setModule((AbstractModule) null);
            return;
         case ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES :
            getReferencedModules().clear();
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
         case ModuleWorldsPackage.MODULE_REALM__MODULE_WORLD :
            return getModuleWorld() != null;
         case ModuleWorldsPackage.MODULE_REALM__MODULE :
            return module != null;
         case ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES :
            return referencedModules != null && !referencedModules.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // ModuleRealmImpl
