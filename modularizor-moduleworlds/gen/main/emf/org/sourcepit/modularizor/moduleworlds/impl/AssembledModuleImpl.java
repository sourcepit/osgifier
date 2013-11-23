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
import org.sourcepit.modularizor.java.JavaArchive;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assembled Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.AssembledModuleImpl#getJavaResources <em>Java Resources</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.AssembledModuleImpl#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AssembledModuleImpl extends AbstractModuleImpl implements AssembledModule
{
   /**
    * The cached value of the '{@link #getJavaResources() <em>Java Resources</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getJavaResources()
    * @generated
    * @ordered
    */
   protected JavaArchive javaResources;

   /**
    * The default value of the '{@link #getFile() <em>File</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getFile()
    * @generated
    * @ordered
    */
   protected static final File FILE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getFile() <em>File</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getFile()
    * @generated
    * @ordered
    */
   protected File file = FILE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected AssembledModuleImpl()
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
      return ModuleWorldsPackage.Literals.ASSEMBLED_MODULE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaArchive getJavaResources()
   {
      return javaResources;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetJavaResources(JavaArchive newJavaResources, NotificationChain msgs)
   {
      JavaArchive oldJavaResources = javaResources;
      javaResources = newJavaResources;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES, oldJavaResources, newJavaResources);
         if (msgs == null)
            msgs = notification;
         else
            msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setJavaResources(JavaArchive newJavaResources)
   {
      if (newJavaResources != javaResources)
      {
         NotificationChain msgs = null;
         if (javaResources != null)
            msgs = ((InternalEObject) javaResources).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
               - ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES, null, msgs);
         if (newJavaResources != null)
            msgs = ((InternalEObject) newJavaResources).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES, null, msgs);
         msgs = basicSetJavaResources(newJavaResources, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES,
            newJavaResources, newJavaResources));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public File getFile()
   {
      return file;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setFile(File newFile)
   {
      File oldFile = file;
      file = newFile;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.ASSEMBLED_MODULE__FILE, oldFile,
            file));
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
         case ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES :
            return basicSetJavaResources(null, msgs);
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
         case ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES :
            return getJavaResources();
         case ModuleWorldsPackage.ASSEMBLED_MODULE__FILE :
            return getFile();
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
         case ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES :
            setJavaResources((JavaArchive) newValue);
            return;
         case ModuleWorldsPackage.ASSEMBLED_MODULE__FILE :
            setFile((File) newValue);
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
         case ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES :
            setJavaResources((JavaArchive) null);
            return;
         case ModuleWorldsPackage.ASSEMBLED_MODULE__FILE :
            setFile(FILE_EDEFAULT);
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
         case ModuleWorldsPackage.ASSEMBLED_MODULE__JAVA_RESOURCES :
            return javaResources != null;
         case ModuleWorldsPackage.ASSEMBLED_MODULE__FILE :
            return FILE_EDEFAULT == null ? file != null : !FILE_EDEFAULT.equals(file);
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
      result.append(" (file: ");
      result.append(file);
      result.append(')');
      return result.toString();
   }

} // AssembledModuleImpl
