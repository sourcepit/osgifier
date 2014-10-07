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

package org.sourcepit.osgifier.core.model.java.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sourcepit.osgifier.core.model.java.JavaClass;
import org.sourcepit.osgifier.core.model.java.JavaModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaClassImpl#getMajor <em>Major</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaClassImpl#getMinor <em>Minor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaClassImpl extends JavaFileImpl implements JavaClass
{
   /**
    * The default value of the '{@link #getMajor() <em>Major</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getMajor()
    * @generated
    * @ordered
    */
   protected static final int MAJOR_EDEFAULT = 0;

   /**
    * The cached value of the '{@link #getMajor() <em>Major</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getMajor()
    * @generated
    * @ordered
    */
   protected int major = MAJOR_EDEFAULT;

   /**
    * The default value of the '{@link #getMinor() <em>Minor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getMinor()
    * @generated
    * @ordered
    */
   protected static final int MINOR_EDEFAULT = 0;

   /**
    * The cached value of the '{@link #getMinor() <em>Minor</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getMinor()
    * @generated
    * @ordered
    */
   protected int minor = MINOR_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaClassImpl()
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
      return JavaModelPackage.Literals.JAVA_CLASS;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public int getMajor()
   {
      return major;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setMajor(int newMajor)
   {
      int oldMajor = major;
      major = newMajor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_CLASS__MAJOR, oldMajor, major));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public int getMinor()
   {
      return minor;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setMinor(int newMinor)
   {
      int oldMinor = minor;
      minor = newMinor;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_CLASS__MINOR, oldMinor, minor));
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
         case JavaModelPackage.JAVA_CLASS__MAJOR :
            return getMajor();
         case JavaModelPackage.JAVA_CLASS__MINOR :
            return getMinor();
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
         case JavaModelPackage.JAVA_CLASS__MAJOR :
            setMajor((Integer) newValue);
            return;
         case JavaModelPackage.JAVA_CLASS__MINOR :
            setMinor((Integer) newValue);
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
         case JavaModelPackage.JAVA_CLASS__MAJOR :
            setMajor(MAJOR_EDEFAULT);
            return;
         case JavaModelPackage.JAVA_CLASS__MINOR :
            setMinor(MINOR_EDEFAULT);
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
         case JavaModelPackage.JAVA_CLASS__MAJOR :
            return major != MAJOR_EDEFAULT;
         case JavaModelPackage.JAVA_CLASS__MINOR :
            return minor != MINOR_EDEFAULT;
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
      result.append(" (major: ");
      result.append(major);
      result.append(", minor: ");
      result.append(minor);
      result.append(')');
      return result.toString();
   }

} // JavaClassImpl
