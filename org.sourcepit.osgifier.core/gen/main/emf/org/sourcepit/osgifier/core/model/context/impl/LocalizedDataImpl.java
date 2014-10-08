/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.model.context.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.modeling.CommonModelingPackage;
import org.sourcepit.common.modeling.impl.EStringMapEntryImpl;
import org.sourcepit.osgifier.core.model.context.ContextModelPackage;
import org.sourcepit.osgifier.core.model.context.LocalizedData;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Localized Data</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.LocalizedDataImpl#getLocale <em>Locale</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.LocalizedDataImpl#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocalizedDataImpl extends EObjectImpl implements LocalizedData
{
   /**
    * The default value of the '{@link #getLocale() <em>Locale</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getLocale()
    * @generated
    * @ordered
    */
   protected static final String LOCALE_EDEFAULT = "\"\"";

   /**
    * The cached value of the '{@link #getLocale() <em>Locale</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getLocale()
    * @generated
    * @ordered
    */
   protected String locale = LOCALE_EDEFAULT;

   /**
    * The cached value of the '{@link #getData() <em>Data</em>}' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getData()
    * @generated
    * @ordered
    */
   protected EMap<String, String> data;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected LocalizedDataImpl()
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
      return ContextModelPackage.Literals.LOCALIZED_DATA;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getLocale()
   {
      return locale;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setLocale(String newLocale)
   {
      String oldLocale = locale;
      locale = newLocale;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.LOCALIZED_DATA__LOCALE, oldLocale,
            locale));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EMap<String, String> getData()
   {
      if (data == null)
      {
         data = new EcoreEMap<String, String>(CommonModelingPackage.Literals.ESTRING_MAP_ENTRY,
            EStringMapEntryImpl.class, this, ContextModelPackage.LOCALIZED_DATA__DATA);
      }
      return data;
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
         case ContextModelPackage.LOCALIZED_DATA__DATA :
            return ((InternalEList<?>) getData()).basicRemove(otherEnd, msgs);
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
         case ContextModelPackage.LOCALIZED_DATA__LOCALE :
            return getLocale();
         case ContextModelPackage.LOCALIZED_DATA__DATA :
            if (coreType)
               return getData();
            else
               return getData().map();
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
         case ContextModelPackage.LOCALIZED_DATA__LOCALE :
            setLocale((String) newValue);
            return;
         case ContextModelPackage.LOCALIZED_DATA__DATA :
            ((EStructuralFeature.Setting) getData()).set(newValue);
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
         case ContextModelPackage.LOCALIZED_DATA__LOCALE :
            setLocale(LOCALE_EDEFAULT);
            return;
         case ContextModelPackage.LOCALIZED_DATA__DATA :
            getData().clear();
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
         case ContextModelPackage.LOCALIZED_DATA__LOCALE :
            return LOCALE_EDEFAULT == null ? locale != null : !LOCALE_EDEFAULT.equals(locale);
         case ContextModelPackage.LOCALIZED_DATA__DATA :
            return data != null && !data.isEmpty();
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
      result.append(" (locale: ");
      result.append(locale);
      result.append(')');
      return result.toString();
   }

} // LocalizedDataImpl
