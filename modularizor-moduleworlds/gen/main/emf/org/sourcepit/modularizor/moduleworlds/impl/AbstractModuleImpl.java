/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.impl;

import java.io.File;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.AbstractModuleImpl#getModuleWorld <em>Module World</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.AbstractModuleImpl#getSourceAttachment <em>Source Attachment
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class AbstractModuleImpl extends XAnnotatableImpl implements AbstractModule
{
   /**
    * The default value of the '{@link #getSourceAttachment() <em>Source Attachment</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSourceAttachment()
    * @generated
    * @ordered
    */
   protected static final File SOURCE_ATTACHMENT_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getSourceAttachment() <em>Source Attachment</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSourceAttachment()
    * @generated
    * @ordered
    */
   protected File sourceAttachment = SOURCE_ATTACHMENT_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected AbstractModuleImpl()
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
      return ModuleWorldsPackage.Literals.ABSTRACT_MODULE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleWorld getModuleWorld()
   {
      if (eContainerFeatureID() != ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD)
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
      msgs = eBasicSetContainer((InternalEObject) newModuleWorld, ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD,
         msgs);
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
         || (eContainerFeatureID() != ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD && newModuleWorld != null))
      {
         if (EcoreUtil.isAncestor(this, newModuleWorld))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newModuleWorld != null)
            msgs = ((InternalEObject) newModuleWorld).eInverseAdd(this, ModuleWorldsPackage.MODULE_WORLD__MODULES,
               ModuleWorld.class, msgs);
         msgs = basicSetModuleWorld(newModuleWorld, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD,
            newModuleWorld, newModuleWorld));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public File getSourceAttachment()
   {
      return sourceAttachment;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setSourceAttachment(File newSourceAttachment)
   {
      File oldSourceAttachment = sourceAttachment;
      sourceAttachment = newSourceAttachment;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.ABSTRACT_MODULE__SOURCE_ATTACHMENT,
            oldSourceAttachment, sourceAttachment));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourceBundle getJavaResources()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
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
         case ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetModuleWorld((ModuleWorld) otherEnd, msgs);
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
         case ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD :
            return basicSetModuleWorld(null, msgs);
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
         case ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD :
            return eInternalContainer().eInverseRemove(this, ModuleWorldsPackage.MODULE_WORLD__MODULES,
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
         case ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD :
            return getModuleWorld();
         case ModuleWorldsPackage.ABSTRACT_MODULE__SOURCE_ATTACHMENT :
            return getSourceAttachment();
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
         case ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD :
            setModuleWorld((ModuleWorld) newValue);
            return;
         case ModuleWorldsPackage.ABSTRACT_MODULE__SOURCE_ATTACHMENT :
            setSourceAttachment((File) newValue);
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
         case ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD :
            setModuleWorld((ModuleWorld) null);
            return;
         case ModuleWorldsPackage.ABSTRACT_MODULE__SOURCE_ATTACHMENT :
            setSourceAttachment(SOURCE_ATTACHMENT_EDEFAULT);
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
         case ModuleWorldsPackage.ABSTRACT_MODULE__MODULE_WORLD :
            return getModuleWorld() != null;
         case ModuleWorldsPackage.ABSTRACT_MODULE__SOURCE_ATTACHMENT :
            return SOURCE_ATTACHMENT_EDEFAULT == null ? sourceAttachment != null : !SOURCE_ATTACHMENT_EDEFAULT
               .equals(sourceAttachment);
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
      result.append(" (sourceAttachment: ");
      result.append(sourceAttachment);
      result.append(')');
      return result.toString();
   }

} // AbstractModuleImpl
