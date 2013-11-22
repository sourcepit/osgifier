/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams.impl;

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
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.modulerelams.ModuleRealm;
import org.sourcepit.modularizor.modulerelams.ModuleRealms;
import org.sourcepit.modularizor.modulerelams.ModuleReference;
import org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Realm</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmImpl#getModuleRealms <em>Module Realms</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmImpl#getRealmModule <em>Realm Module</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmImpl#getModuleReferences <em>Module References
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ModuleRealmImpl extends XAnnotatableImpl implements ModuleRealm
{
   /**
    * The cached value of the '{@link #getRealmModule() <em>Realm Module</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getRealmModule()
    * @generated
    * @ordered
    */
   protected JavaResourceBundle realmModule;

   /**
    * The cached value of the '{@link #getModuleReferences() <em>Module References</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getModuleReferences()
    * @generated
    * @ordered
    */
   protected EList<ModuleReference> moduleReferences;

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
      return ModuleRelamsPackage.Literals.MODULE_REALM;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleRealms getModuleRealms()
   {
      if (eContainerFeatureID() != ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS)
         return null;
      return (ModuleRealms) eInternalContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetModuleRealms(ModuleRealms newModuleRealms, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newModuleRealms, ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS,
         msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setModuleRealms(ModuleRealms newModuleRealms)
   {
      if (newModuleRealms != eInternalContainer()
         || (eContainerFeatureID() != ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS && newModuleRealms != null))
      {
         if (EcoreUtil.isAncestor(this, newModuleRealms))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newModuleRealms != null)
            msgs = ((InternalEObject) newModuleRealms).eInverseAdd(this,
               ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS, ModuleRealms.class, msgs);
         msgs = basicSetModuleRealms(newModuleRealms, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS,
            newModuleRealms, newModuleRealms));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourceBundle getRealmModule()
   {
      if (realmModule != null && realmModule.eIsProxy())
      {
         InternalEObject oldRealmModule = (InternalEObject) realmModule;
         realmModule = (JavaResourceBundle) eResolveProxy(oldRealmModule);
         if (realmModule != oldRealmModule)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                  ModuleRelamsPackage.MODULE_REALM__REALM_MODULE, oldRealmModule, realmModule));
         }
      }
      return realmModule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourceBundle basicGetRealmModule()
   {
      return realmModule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setRealmModule(JavaResourceBundle newRealmModule)
   {
      JavaResourceBundle oldRealmModule = realmModule;
      realmModule = newRealmModule;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleRelamsPackage.MODULE_REALM__REALM_MODULE,
            oldRealmModule, realmModule));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<ModuleReference> getModuleReferences()
   {
      if (moduleReferences == null)
      {
         moduleReferences = new EObjectContainmentWithInverseEList<ModuleReference>(ModuleReference.class, this,
            ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES, ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM);
      }
      return moduleReferences;
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
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetModuleRealms((ModuleRealms) otherEnd, msgs);
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getModuleReferences()).basicAdd(otherEnd, msgs);
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
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS :
            return basicSetModuleRealms(null, msgs);
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES :
            return ((InternalEList<?>) getModuleReferences()).basicRemove(otherEnd, msgs);
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
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS :
            return eInternalContainer().eInverseRemove(this, ModuleRelamsPackage.MODULE_REALMS__MODULE_REALMS,
               ModuleRealms.class, msgs);
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
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS :
            return getModuleRealms();
         case ModuleRelamsPackage.MODULE_REALM__REALM_MODULE :
            if (resolve)
               return getRealmModule();
            return basicGetRealmModule();
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES :
            return getModuleReferences();
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
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS :
            setModuleRealms((ModuleRealms) newValue);
            return;
         case ModuleRelamsPackage.MODULE_REALM__REALM_MODULE :
            setRealmModule((JavaResourceBundle) newValue);
            return;
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES :
            getModuleReferences().clear();
            getModuleReferences().addAll((Collection<? extends ModuleReference>) newValue);
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
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS :
            setModuleRealms((ModuleRealms) null);
            return;
         case ModuleRelamsPackage.MODULE_REALM__REALM_MODULE :
            setRealmModule((JavaResourceBundle) null);
            return;
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES :
            getModuleReferences().clear();
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
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REALMS :
            return getModuleRealms() != null;
         case ModuleRelamsPackage.MODULE_REALM__REALM_MODULE :
            return realmModule != null;
         case ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES :
            return moduleReferences != null && !moduleReferences.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // ModuleRealmImpl
