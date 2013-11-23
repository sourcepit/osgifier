/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleReference;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl#getModuleRealm <em>Module Realm</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl#getTargetModule <em>Target Module</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl#isTransitive <em>Transitive</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl#isOptional <em>Optional</em>}</li>
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
   protected AbstractModule targetModule;

   /**
    * The default value of the '{@link #isTransitive() <em>Transitive</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isTransitive()
    * @generated
    * @ordered
    */
   protected static final boolean TRANSITIVE_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isTransitive() <em>Transitive</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isTransitive()
    * @generated
    * @ordered
    */
   protected boolean transitive = TRANSITIVE_EDEFAULT;

   /**
    * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isOptional()
    * @generated
    * @ordered
    */
   protected static final boolean OPTIONAL_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isOptional()
    * @generated
    * @ordered
    */
   protected boolean optional = OPTIONAL_EDEFAULT;

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
      return ModuleWorldsPackage.Literals.MODULE_REFERENCE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleRealm getModuleRealm()
   {
      if (eContainerFeatureID() != ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM)
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
      msgs = eBasicSetContainer((InternalEObject) newModuleRealm, ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM,
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
         || (eContainerFeatureID() != ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM && newModuleRealm != null))
      {
         if (EcoreUtil.isAncestor(this, newModuleRealm))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newModuleRealm != null)
            msgs = ((InternalEObject) newModuleRealm).eInverseAdd(this,
               ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES, ModuleRealm.class, msgs);
         msgs = basicSetModuleRealm(newModuleRealm, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM,
            newModuleRealm, newModuleRealm));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public AbstractModule getTargetModule()
   {
      if (targetModule != null && targetModule.eIsProxy())
      {
         InternalEObject oldTargetModule = (InternalEObject) targetModule;
         targetModule = (AbstractModule) eResolveProxy(oldTargetModule);
         if (targetModule != oldTargetModule)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                  ModuleWorldsPackage.MODULE_REFERENCE__TARGET_MODULE, oldTargetModule, targetModule));
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
   public AbstractModule basicGetTargetModule()
   {
      return targetModule;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTargetModule(AbstractModule newTargetModule)
   {
      AbstractModule oldTargetModule = targetModule;
      targetModule = newTargetModule;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.MODULE_REFERENCE__TARGET_MODULE,
            oldTargetModule, targetModule));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isTransitive()
   {
      return transitive;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTransitive(boolean newTransitive)
   {
      boolean oldTransitive = transitive;
      transitive = newTransitive;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.MODULE_REFERENCE__TRANSITIVE,
            oldTransitive, transitive));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isOptional()
   {
      return optional;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setOptional(boolean newOptional)
   {
      boolean oldOptional = optional;
      optional = newOptional;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.MODULE_REFERENCE__OPTIONAL,
            oldOptional, optional));
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
         case ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM :
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
         case ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM :
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
         case ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM :
            return eInternalContainer().eInverseRemove(this, ModuleWorldsPackage.MODULE_REALM__REFERENCED_MODULES,
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
         case ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM :
            return getModuleRealm();
         case ModuleWorldsPackage.MODULE_REFERENCE__TARGET_MODULE :
            if (resolve)
               return getTargetModule();
            return basicGetTargetModule();
         case ModuleWorldsPackage.MODULE_REFERENCE__TRANSITIVE :
            return isTransitive();
         case ModuleWorldsPackage.MODULE_REFERENCE__OPTIONAL :
            return isOptional();
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
         case ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM :
            setModuleRealm((ModuleRealm) newValue);
            return;
         case ModuleWorldsPackage.MODULE_REFERENCE__TARGET_MODULE :
            setTargetModule((AbstractModule) newValue);
            return;
         case ModuleWorldsPackage.MODULE_REFERENCE__TRANSITIVE :
            setTransitive((Boolean) newValue);
            return;
         case ModuleWorldsPackage.MODULE_REFERENCE__OPTIONAL :
            setOptional((Boolean) newValue);
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
         case ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM :
            setModuleRealm((ModuleRealm) null);
            return;
         case ModuleWorldsPackage.MODULE_REFERENCE__TARGET_MODULE :
            setTargetModule((AbstractModule) null);
            return;
         case ModuleWorldsPackage.MODULE_REFERENCE__TRANSITIVE :
            setTransitive(TRANSITIVE_EDEFAULT);
            return;
         case ModuleWorldsPackage.MODULE_REFERENCE__OPTIONAL :
            setOptional(OPTIONAL_EDEFAULT);
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
         case ModuleWorldsPackage.MODULE_REFERENCE__MODULE_REALM :
            return getModuleRealm() != null;
         case ModuleWorldsPackage.MODULE_REFERENCE__TARGET_MODULE :
            return targetModule != null;
         case ModuleWorldsPackage.MODULE_REFERENCE__TRANSITIVE :
            return transitive != TRANSITIVE_EDEFAULT;
         case ModuleWorldsPackage.MODULE_REFERENCE__OPTIONAL :
            return optional != OPTIONAL_EDEFAULT;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (transitive: ");
      result.append(transitive);
      result.append(", optional: ");
      result.append(optional);
      result.append(')');
      return result.toString();
   }

} // ModuleReferenceImpl
