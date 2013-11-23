/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.impl;

import java.io.File;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.sourcepit.modularizor.java.JavaProject;
import org.sourcepit.modularizor.moduleworlds.ExplodedModule;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exploded Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl#getDirectory <em>Directory</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl#getSourceDirectories <em>Source Directories
 * </em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl#getBinaryDirectories <em>Binary Directories
 * </em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl#getJavaResources <em>Java Resources</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ExplodedModuleImpl extends AbstractModuleImpl implements ExplodedModule
{
   /**
    * The default value of the '{@link #getDirectory() <em>Directory</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getDirectory()
    * @generated
    * @ordered
    */
   protected static final File DIRECTORY_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getDirectory() <em>Directory</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getDirectory()
    * @generated
    * @ordered
    */
   protected File directory = DIRECTORY_EDEFAULT;

   /**
    * The cached value of the '{@link #getSourceDirectories() <em>Source Directories</em>}' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSourceDirectories()
    * @generated
    * @ordered
    */
   protected EList<File> sourceDirectories;

   /**
    * The cached value of the '{@link #getBinaryDirectories() <em>Binary Directories</em>}' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getBinaryDirectories()
    * @generated
    * @ordered
    */
   protected EList<File> binaryDirectories;

   /**
    * The cached value of the '{@link #getJavaResources() <em>Java Resources</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getJavaResources()
    * @generated
    * @ordered
    */
   protected JavaProject javaResources;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ExplodedModuleImpl()
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
      return ModuleWorldsPackage.Literals.EXPLODED_MODULE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public File getDirectory()
   {
      return directory;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setDirectory(File newDirectory)
   {
      File oldDirectory = directory;
      directory = newDirectory;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.EXPLODED_MODULE__DIRECTORY,
            oldDirectory, directory));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<File> getSourceDirectories()
   {
      if (sourceDirectories == null)
      {
         sourceDirectories = new EDataTypeUniqueEList<File>(File.class, this,
            ModuleWorldsPackage.EXPLODED_MODULE__SOURCE_DIRECTORIES);
      }
      return sourceDirectories;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<File> getBinaryDirectories()
   {
      if (binaryDirectories == null)
      {
         binaryDirectories = new EDataTypeUniqueEList<File>(File.class, this,
            ModuleWorldsPackage.EXPLODED_MODULE__BINARY_DIRECTORIES);
      }
      return binaryDirectories;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaProject getJavaResources()
   {
      return javaResources;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetJavaResources(JavaProject newJavaResources, NotificationChain msgs)
   {
      JavaProject oldJavaResources = javaResources;
      javaResources = newJavaResources;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES, oldJavaResources, newJavaResources);
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
   public void setJavaResources(JavaProject newJavaResources)
   {
      if (newJavaResources != javaResources)
      {
         NotificationChain msgs = null;
         if (javaResources != null)
            msgs = ((InternalEObject) javaResources).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
               - ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES, null, msgs);
         if (newJavaResources != null)
            msgs = ((InternalEObject) newJavaResources).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES, null, msgs);
         msgs = basicSetJavaResources(newJavaResources, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES,
            newJavaResources, newJavaResources));
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
         case ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES :
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
         case ModuleWorldsPackage.EXPLODED_MODULE__DIRECTORY :
            return getDirectory();
         case ModuleWorldsPackage.EXPLODED_MODULE__SOURCE_DIRECTORIES :
            return getSourceDirectories();
         case ModuleWorldsPackage.EXPLODED_MODULE__BINARY_DIRECTORIES :
            return getBinaryDirectories();
         case ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES :
            return getJavaResources();
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
         case ModuleWorldsPackage.EXPLODED_MODULE__DIRECTORY :
            setDirectory((File) newValue);
            return;
         case ModuleWorldsPackage.EXPLODED_MODULE__SOURCE_DIRECTORIES :
            getSourceDirectories().clear();
            getSourceDirectories().addAll((Collection<? extends File>) newValue);
            return;
         case ModuleWorldsPackage.EXPLODED_MODULE__BINARY_DIRECTORIES :
            getBinaryDirectories().clear();
            getBinaryDirectories().addAll((Collection<? extends File>) newValue);
            return;
         case ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES :
            setJavaResources((JavaProject) newValue);
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
         case ModuleWorldsPackage.EXPLODED_MODULE__DIRECTORY :
            setDirectory(DIRECTORY_EDEFAULT);
            return;
         case ModuleWorldsPackage.EXPLODED_MODULE__SOURCE_DIRECTORIES :
            getSourceDirectories().clear();
            return;
         case ModuleWorldsPackage.EXPLODED_MODULE__BINARY_DIRECTORIES :
            getBinaryDirectories().clear();
            return;
         case ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES :
            setJavaResources((JavaProject) null);
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
         case ModuleWorldsPackage.EXPLODED_MODULE__DIRECTORY :
            return DIRECTORY_EDEFAULT == null ? directory != null : !DIRECTORY_EDEFAULT.equals(directory);
         case ModuleWorldsPackage.EXPLODED_MODULE__SOURCE_DIRECTORIES :
            return sourceDirectories != null && !sourceDirectories.isEmpty();
         case ModuleWorldsPackage.EXPLODED_MODULE__BINARY_DIRECTORIES :
            return binaryDirectories != null && !binaryDirectories.isEmpty();
         case ModuleWorldsPackage.EXPLODED_MODULE__JAVA_RESOURCES :
            return javaResources != null;
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
      result.append(" (directory: ");
      result.append(directory);
      result.append(", sourceDirectories: ");
      result.append(sourceDirectories);
      result.append(", binaryDirectories: ");
      result.append(binaryDirectories);
      result.append(')');
      return result.toString();
   }

} // ExplodedModuleImpl
