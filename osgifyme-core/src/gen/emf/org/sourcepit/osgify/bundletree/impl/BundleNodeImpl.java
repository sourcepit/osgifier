/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.bundletree.BundleNode;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleNodeImpl#getVersionRange <em>Version Range</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleNodeImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleNodeImpl#isOptional <em>Optional</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleNodeImpl extends AbstractBundleNodeImpl implements BundleNode
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
    * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isEnabled()
    * @generated
    * @ordered
    */
   protected static final boolean ENABLED_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isEnabled()
    * @generated
    * @ordered
    */
   protected boolean enabled = ENABLED_EDEFAULT;

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
   protected BundleNodeImpl()
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
      return BundleTreeModelPackage.Literals.BUNDLE_NODE;
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE_NODE__VERSION_RANGE,
            oldVersionRange, versionRange));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isEnabled()
   {
      return enabled;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setEnabled(boolean newEnabled)
   {
      boolean oldEnabled = enabled;
      enabled = newEnabled;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE_NODE__ENABLED, oldEnabled,
            enabled));
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE_NODE__OPTIONAL,
            oldOptional, optional));
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
         case BundleTreeModelPackage.BUNDLE_NODE__VERSION_RANGE :
            return getVersionRange();
         case BundleTreeModelPackage.BUNDLE_NODE__ENABLED :
            return isEnabled();
         case BundleTreeModelPackage.BUNDLE_NODE__OPTIONAL :
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
         case BundleTreeModelPackage.BUNDLE_NODE__VERSION_RANGE :
            setVersionRange((VersionRange) newValue);
            return;
         case BundleTreeModelPackage.BUNDLE_NODE__ENABLED :
            setEnabled((Boolean) newValue);
            return;
         case BundleTreeModelPackage.BUNDLE_NODE__OPTIONAL :
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
         case BundleTreeModelPackage.BUNDLE_NODE__VERSION_RANGE :
            setVersionRange(VERSION_RANGE_EDEFAULT);
            return;
         case BundleTreeModelPackage.BUNDLE_NODE__ENABLED :
            setEnabled(ENABLED_EDEFAULT);
            return;
         case BundleTreeModelPackage.BUNDLE_NODE__OPTIONAL :
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
         case BundleTreeModelPackage.BUNDLE_NODE__VERSION_RANGE :
            return VERSION_RANGE_EDEFAULT == null ? versionRange != null : !VERSION_RANGE_EDEFAULT.equals(versionRange);
         case BundleTreeModelPackage.BUNDLE_NODE__ENABLED :
            return enabled != ENABLED_EDEFAULT;
         case BundleTreeModelPackage.BUNDLE_NODE__OPTIONAL :
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
      result.append(" (versionRange: ");
      result.append(versionRange);
      result.append(", enabled: ");
      result.append(enabled);
      result.append(", optional: ");
      result.append(optional);
      result.append(')');
      return result.toString();
   }

} // BundleNodeImpl
