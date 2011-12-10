/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.modeling.common.Extendable;
import org.sourcepit.osgify.context.BundleNode;
import org.sourcepit.osgify.context.BundleReference;
import org.sourcepit.osgify.context.ContextModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleReferenceImpl#getExtensions <em>Extensions</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleReferenceImpl#getVersionRange <em>Version Range</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleReferenceImpl#isOptional <em>Optional</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleReferenceImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleReferenceImpl#isProvided <em>Provided</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleReferenceImpl extends AbstractBundleCoordinateImpl implements BundleReference
{
   /**
    * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getExtensions()
    * @generated
    * @ordered
    */
   protected EList<EObject> extensions;

   /**
    * The default value of the '{@link #getVersionRange() <em>Version Range</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersionRange()
    * @generated
    * @ordered
    */
   protected static final VersionRange VERSION_RANGE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getVersionRange() <em>Version Range</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersionRange()
    * @generated
    * @ordered
    */
   protected VersionRange versionRange = VERSION_RANGE_EDEFAULT;

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
    * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTarget()
    * @generated
    * @ordered
    */
   protected BundleNode target;

   /**
    * The default value of the '{@link #isProvided() <em>Provided</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isProvided()
    * @generated
    * @ordered
    */
   protected static final boolean PROVIDED_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isProvided() <em>Provided</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isProvided()
    * @generated
    * @ordered
    */
   protected boolean provided = PROVIDED_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected BundleReferenceImpl()
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
      return ContextModelPackage.Literals.BUNDLE_REFERENCE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<EObject> getExtensions()
   {
      if (extensions == null)
      {
         extensions = new EObjectContainmentEList<EObject>(EObject.class, this,
            ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS);
      }
      return extensions;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public VersionRange getVersionRange()
   {
      return versionRange;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setVersionRange(VersionRange newVersionRange)
   {
      VersionRange oldVersionRange = versionRange;
      versionRange = newVersionRange;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE,
            oldVersionRange, versionRange));
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
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL,
            oldOptional, optional));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleNode getTarget()
   {
      if (target != null && target.eIsProxy())
      {
         InternalEObject oldTarget = (InternalEObject) target;
         target = (BundleNode) eResolveProxy(oldTarget);
         if (target != oldTarget)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextModelPackage.BUNDLE_REFERENCE__TARGET,
                  oldTarget, target));
         }
      }
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleNode basicGetTarget()
   {
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTarget(BundleNode newTarget)
   {
      BundleNode oldTarget = target;
      target = newTarget;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__TARGET, oldTarget,
            target));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isProvided()
   {
      return provided;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setProvided(boolean newProvided)
   {
      boolean oldProvided = provided;
      provided = newProvided;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__PROVIDED,
            oldProvided, provided));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public <T extends EObject> T getExtension(Class<T> extensionType)
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
   public <T extends EObject> EList<T> getExtensions(Class<T> extensionType)
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
   public <T extends EObject> void addExtension(T extension)
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
   public <T extends EObject> void removeExtension(T extension)
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
   public <T extends EObject> void removeExtensions(Class<T> extentionType)
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
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS :
            return ((InternalEList<?>) getExtensions()).basicRemove(otherEnd, msgs);
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
         case ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS :
            return getExtensions();
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            return getVersionRange();
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            return isOptional();
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            if (resolve)
               return getTarget();
            return basicGetTarget();
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            return isProvided();
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
         case ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS :
            getExtensions().clear();
            getExtensions().addAll((Collection<? extends EObject>) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            setVersionRange((VersionRange) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            setOptional((Boolean) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            setTarget((BundleNode) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            setProvided((Boolean) newValue);
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
         case ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS :
            getExtensions().clear();
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            setVersionRange(VERSION_RANGE_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            setOptional(OPTIONAL_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            setTarget((BundleNode) null);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            setProvided(PROVIDED_EDEFAULT);
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
         case ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS :
            return extensions != null && !extensions.isEmpty();
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            return VERSION_RANGE_EDEFAULT == null ? versionRange != null : !VERSION_RANGE_EDEFAULT.equals(versionRange);
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            return optional != OPTIONAL_EDEFAULT;
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            return target != null;
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            return provided != PROVIDED_EDEFAULT;
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
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == Extendable.class)
      {
         switch (derivedFeatureID)
         {
            case ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS :
               return CommonModelPackage.EXTENDABLE__EXTENSIONS;
            default :
               return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == Extendable.class)
      {
         switch (baseFeatureID)
         {
            case CommonModelPackage.EXTENDABLE__EXTENSIONS :
               return ContextModelPackage.BUNDLE_REFERENCE__EXTENSIONS;
            default :
               return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
      result.append(" (versionRange: ");
      result.append(versionRange);
      result.append(", optional: ");
      result.append(optional);
      result.append(", provided: ");
      result.append(provided);
      result.append(')');
      return result.toString();
   }

} // BundleReferenceImpl
