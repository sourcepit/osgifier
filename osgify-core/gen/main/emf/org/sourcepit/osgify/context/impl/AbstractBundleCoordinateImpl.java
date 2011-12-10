/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgify.context.AbstractBundleCoordinate;
import org.sourcepit.osgify.context.ContextModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Bundle Coordinate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.context.impl.AbstractBundleCoordinateImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.AbstractBundleCoordinateImpl#getSymbolicName <em>Symbolic Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class AbstractBundleCoordinateImpl extends EObjectImpl implements AbstractBundleCoordinate
{
   /**
    * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersion()
    * @generated
    * @ordered
    */
   protected static final Version VERSION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersion()
    * @generated
    * @ordered
    */
   protected Version version = VERSION_EDEFAULT;

   /**
    * The default value of the '{@link #getSymbolicName() <em>Symbolic Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSymbolicName()
    * @generated
    * @ordered
    */
   protected static final String SYMBOLIC_NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getSymbolicName() <em>Symbolic Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSymbolicName()
    * @generated
    * @ordered
    */
   protected String symbolicName = SYMBOLIC_NAME_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected AbstractBundleCoordinateImpl()
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
      return ContextModelPackage.Literals.ABSTRACT_BUNDLE_COORDINATE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Version getVersion()
   {
      return version;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setVersion(Version newVersion)
   {
      Version oldVersion = version;
      version = newVersion;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__VERSION,
            oldVersion, version));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getSymbolicName()
   {
      return symbolicName;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setSymbolicName(String newSymbolicName)
   {
      String oldSymbolicName = symbolicName;
      symbolicName = newSymbolicName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET,
            ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME, oldSymbolicName, symbolicName));
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
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__VERSION :
            return getVersion();
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME :
            return getSymbolicName();
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
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__VERSION :
            setVersion((Version) newValue);
            return;
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME :
            setSymbolicName((String) newValue);
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
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__VERSION :
            setVersion(VERSION_EDEFAULT);
            return;
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME :
            setSymbolicName(SYMBOLIC_NAME_EDEFAULT);
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
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__VERSION :
            return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
         case ContextModelPackage.ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME :
            return SYMBOLIC_NAME_EDEFAULT == null ? symbolicName != null : !SYMBOLIC_NAME_EDEFAULT.equals(symbolicName);
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
      result.append(" (version: ");
      result.append(version);
      result.append(", symbolicName: ");
      result.append(symbolicName);
      result.append(')');
      return result.toString();
   }

} // AbstractBundleCoordinateImpl
