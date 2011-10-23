/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Package Map Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageMapEntryImpl#getTypedValue <em>Value</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageMapEntryImpl#getTypedKey <em>Key</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JavaPackageMapEntryImpl extends EObjectImpl implements BasicEMap.Entry<String, EList<JavaPackage>>
{
   /**
    * The cached value of the '{@link #getTypedValue() <em>Value</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTypedValue()
    * @generated
    * @ordered
    */
   protected EList<JavaPackage> value;

   /**
    * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTypedKey()
    * @generated
    * @ordered
    */
   protected static final String KEY_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTypedKey()
    * @generated
    * @ordered
    */
   protected String key = KEY_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaPackageMapEntryImpl()
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
      return JavaModelPackage.Literals.JAVA_PACKAGE_MAP_ENTRY;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaPackage> getTypedValue()
   {
      if (value == null)
      {
         value = new EObjectContainmentEList<JavaPackage>(JavaPackage.class, this,
            JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__VALUE);
      }
      return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getTypedKey()
   {
      return key;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTypedKey(String newKey)
   {
      String oldKey = key;
      key = newKey;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__KEY, oldKey,
            key));
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
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__VALUE :
            return ((InternalEList<?>) getTypedValue()).basicRemove(otherEnd, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__VALUE :
            return getTypedValue();
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__KEY :
            return getTypedKey();
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
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__VALUE :
            getTypedValue().clear();
            getTypedValue().addAll((Collection<? extends JavaPackage>) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__KEY :
            setTypedKey((String) newValue);
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
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__VALUE :
            getTypedValue().clear();
            return;
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__KEY :
            setTypedKey(KEY_EDEFAULT);
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
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__VALUE :
            return value != null && !value.isEmpty();
         case JavaModelPackage.JAVA_PACKAGE_MAP_ENTRY__KEY :
            return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
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
      result.append(" (key: ");
      result.append(key);
      result.append(')');
      return result.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected int hash = -1;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public int getHash()
   {
      if (hash == -1)
      {
         Object theKey = getKey();
         hash = (theKey == null ? 0 : theKey.hashCode());
      }
      return hash;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setHash(int hash)
   {
      this.hash = hash;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getKey()
   {
      return getTypedKey();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setKey(String key)
   {
      setTypedKey(key);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaPackage> getValue()
   {
      return getTypedValue();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaPackage> setValue(EList<JavaPackage> value)
   {
      EList<JavaPackage> oldValue = getValue();
      getTypedValue().clear();
      getTypedValue().addAll(value);
      return oldValue;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @SuppressWarnings("unchecked")
   public EMap<String, EList<JavaPackage>> getEMap()
   {
      EObject container = eContainer();
      return container == null ? null : (EMap<String, EList<JavaPackage>>) container.eGet(eContainmentFeature());
   }

} // JavaPackageMapEntryImpl
