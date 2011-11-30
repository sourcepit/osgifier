/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.osgify.bundletree.Bundle;
import org.sourcepit.osgify.bundletree.BundleReference;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleImpl#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleImpl#getDependencies <em>Dependencies</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleImpl extends AbstractBundleCoordinateImpl implements Bundle
{
   /**
    * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getContent()
    * @generated
    * @ordered
    */
   protected JavaPackageBundle content;

   /**
    * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getDependencies()
    * @generated
    * @ordered
    */
   protected EList<BundleReference> dependencies;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected BundleImpl()
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
      return BundleTreeModelPackage.Literals.BUNDLE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageBundle getContent()
   {
      return content;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetContent(JavaPackageBundle newContent, NotificationChain msgs)
   {
      JavaPackageBundle oldContent = content;
      content = newContent;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            BundleTreeModelPackage.BUNDLE__CONTENT, oldContent, newContent);
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
   public void setContent(JavaPackageBundle newContent)
   {
      if (newContent != content)
      {
         NotificationChain msgs = null;
         if (content != null)
            msgs = ((InternalEObject) content).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
               - BundleTreeModelPackage.BUNDLE__CONTENT, null, msgs);
         if (newContent != null)
            msgs = ((InternalEObject) newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - BundleTreeModelPackage.BUNDLE__CONTENT, null, msgs);
         msgs = basicSetContent(newContent, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.BUNDLE__CONTENT, newContent,
            newContent));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<BundleReference> getDependencies()
   {
      if (dependencies == null)
      {
         dependencies = new EObjectContainmentEList<BundleReference>(BundleReference.class, this,
            BundleTreeModelPackage.BUNDLE__DEPENDENCIES);
      }
      return dependencies;
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
         case BundleTreeModelPackage.BUNDLE__CONTENT :
            return basicSetContent(null, msgs);
         case BundleTreeModelPackage.BUNDLE__DEPENDENCIES :
            return ((InternalEList<?>) getDependencies()).basicRemove(otherEnd, msgs);
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
         case BundleTreeModelPackage.BUNDLE__CONTENT :
            return getContent();
         case BundleTreeModelPackage.BUNDLE__DEPENDENCIES :
            return getDependencies();
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
         case BundleTreeModelPackage.BUNDLE__CONTENT :
            setContent((JavaPackageBundle) newValue);
            return;
         case BundleTreeModelPackage.BUNDLE__DEPENDENCIES :
            getDependencies().clear();
            getDependencies().addAll((Collection<? extends BundleReference>) newValue);
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
         case BundleTreeModelPackage.BUNDLE__CONTENT :
            setContent((JavaPackageBundle) null);
            return;
         case BundleTreeModelPackage.BUNDLE__DEPENDENCIES :
            getDependencies().clear();
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
         case BundleTreeModelPackage.BUNDLE__CONTENT :
            return content != null;
         case BundleTreeModelPackage.BUNDLE__DEPENDENCIES :
            return dependencies != null && !dependencies.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // BundleImpl
