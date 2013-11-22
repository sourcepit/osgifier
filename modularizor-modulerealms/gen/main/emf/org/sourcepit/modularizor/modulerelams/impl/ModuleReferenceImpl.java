/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.modulerelams.ModuleRealm;
import org.sourcepit.modularizor.modulerelams.ModuleReference;
import org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.modulerelams.impl.ModuleReferenceImpl#getModuleRealm <em>Module Realm</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.impl.ModuleReferenceImpl#getTargetModule <em>Target Module</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ModuleReferenceImpl extends XAnnotatableImpl implements ModuleReference
{
   /**
    * The cached value of the '{@link #getTargetModule() <em>Target Module</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTargetModule()
    * @generated
    * @ordered
    */
   protected JavaResourceBundle targetModule;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ModuleReferenceImpl()
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
      return ModuleRelamsPackage.Literals.MODULE_REFERENCE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleRealm getModuleRealm()
   {
      if (eContainerFeatureID() != ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM)
         return null;
      return (ModuleRealm) eInternalContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetModuleRealm(ModuleRealm newModuleRealm, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newModuleRealm, ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM,
         msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setModuleRealm(ModuleRealm newModuleRealm)
   {
      if (newModuleRealm != eInternalContainer()
         || (eContainerFeatureID() != ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM && newModuleRealm != null))
      {
         if (EcoreUtil.isAncestor(this, newModuleRealm))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newModuleRealm != null)
            msgs = ((InternalEObject) newModuleRealm).eInverseAdd(this,
               ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES, ModuleRealm.class, msgs);
         msgs = basicSetModuleRealm(newModuleRealm, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM,
            newModuleRealm, newModuleRealm));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourceBundle getTargetModule()
   {
      if (targetModule != null && targetModule.eIsProxy())
      {
         InternalEObject oldTargetModule = (InternalEObject) targetModule;
         targetModule = (JavaResourceBundle) eResolveProxy(oldTargetModule);
         if (targetModule != oldTargetModule)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                  ModuleRelamsPackage.MODULE_REFERENCE__TARGET_MODULE, oldTargetModule, targetModule));
         }
      }
      return targetModule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourceBundle basicGetTargetModule()
   {
      return targetModule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTargetModule(JavaResourceBundle newTargetModule)
   {
      JavaResourceBundle oldTargetModule = targetModule;
      targetModule = newTargetModule;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleRelamsPackage.MODULE_REFERENCE__TARGET_MODULE,
            oldTargetModule, targetModule));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetModuleRealm((ModuleRealm) otherEnd, msgs);
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
         case ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM :
            return basicSetModuleRealm(null, msgs);
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
         case ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM :
            return eInternalContainer().eInverseRemove(this, ModuleRelamsPackage.MODULE_REALM__MODULE_REFERENCES,
               ModuleRealm.class, msgs);
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
         case ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM :
            return getModuleRealm();
         case ModuleRelamsPackage.MODULE_REFERENCE__TARGET_MODULE :
            if (resolve)
               return getTargetModule();
            return basicGetTargetModule();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM :
            setModuleRealm((ModuleRealm) newValue);
            return;
         case ModuleRelamsPackage.MODULE_REFERENCE__TARGET_MODULE :
            setTargetModule((JavaResourceBundle) newValue);
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
         case ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM :
            setModuleRealm((ModuleRealm) null);
            return;
         case ModuleRelamsPackage.MODULE_REFERENCE__TARGET_MODULE :
            setTargetModule((JavaResourceBundle) null);
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
         case ModuleRelamsPackage.MODULE_REFERENCE__MODULE_REALM :
            return getModuleRealm() != null;
         case ModuleRelamsPackage.MODULE_REFERENCE__TARGET_MODULE :
            return targetModule != null;
      }
      return super.eIsSet(featureID);
   }

} // ModuleReferenceImpl
