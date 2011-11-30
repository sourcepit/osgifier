/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.bundletree.Bundle;
import org.sourcepit.osgify.bundletree.BundleReference;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl#getVersionRange <em>Version Range</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl#isOptional <em>Optional</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl#isProvided <em>Provided</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleReferenceImpl extends AbstractBundleCoordinateImpl implements BundleReference
{
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
   protected Bundle target;

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
      return BundleTreeModelPackage.Literals.BUNDLE_REFERENCE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE_REFERENCE__VERSION_RANGE,
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE_REFERENCE__OPTIONAL,
            oldOptional, optional));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Bundle getTarget()
   {
      if (target != null && target.eIsProxy())
      {
         InternalEObject oldTarget = (InternalEObject) target;
         target = (Bundle) eResolveProxy(oldTarget);
         if (target != oldTarget)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                  BundleTreeModelPackage.BUNDLE_REFERENCE__TARGET, oldTarget, target));
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
   public Bundle basicGetTarget()
   {
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTarget(Bundle newTarget)
   {
      Bundle oldTarget = target;
      target = newTarget;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE_REFERENCE__TARGET,
            oldTarget, target));
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE_REFERENCE__PROVIDED,
            oldProvided, provided));
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
         case BundleTreeModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            return getVersionRange();
         case BundleTreeModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            return isOptional();
         case BundleTreeModelPackage.BUNDLE_REFERENCE__TARGET :
            if (resolve)
               return getTarget();
            return basicGetTarget();
         case BundleTreeModelPackage.BUNDLE_REFERENCE__PROVIDED :
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
         case BundleTreeModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            setVersionRange((VersionRange) newValue);
            return;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            setOptional((Boolean) newValue);
            return;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__TARGET :
            setTarget((Bundle) newValue);
            return;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__PROVIDED :
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
         case BundleTreeModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            setVersionRange(VERSION_RANGE_EDEFAULT);
            return;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            setOptional(OPTIONAL_EDEFAULT);
            return;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__TARGET :
            setTarget((Bundle) null);
            return;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__PROVIDED :
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
         case BundleTreeModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            return VERSION_RANGE_EDEFAULT == null ? versionRange != null : !VERSION_RANGE_EDEFAULT.equals(versionRange);
         case BundleTreeModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            return optional != OPTIONAL_EDEFAULT;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__TARGET :
            return target != null;
         case BundleTreeModelPackage.BUNDLE_REFERENCE__PROVIDED :
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
