/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.osgify.bundletree.Bundle;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.bundletree.OSGiFyContext;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>OS Gi Fy Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.OSGiFyContextImpl#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OSGiFyContextImpl extends EObjectImpl implements OSGiFyContext
{
   /**
    * The cached value of the '{@link #getBundles() <em>Bundles</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getBundles()
    * @generated
    * @ordered
    */
   protected EList<Bundle> bundles;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected OSGiFyContextImpl()
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
      return BundleTreeModelPackage.Literals.OS_GI_FY_CONTEXT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<Bundle> getBundles()
   {
      if (bundles == null)
      {
         bundles = new EObjectContainmentEList<Bundle>(Bundle.class, this,
            BundleTreeModelPackage.OS_GI_FY_CONTEXT__BUNDLES);
      }
      return bundles;
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
         case BundleTreeModelPackage.OS_GI_FY_CONTEXT__BUNDLES :
            return ((InternalEList<?>) getBundles()).basicRemove(otherEnd, msgs);
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
         case BundleTreeModelPackage.OS_GI_FY_CONTEXT__BUNDLES :
            return getBundles();
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
         case BundleTreeModelPackage.OS_GI_FY_CONTEXT__BUNDLES :
            getBundles().clear();
            getBundles().addAll((Collection<? extends Bundle>) newValue);
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
         case BundleTreeModelPackage.OS_GI_FY_CONTEXT__BUNDLES :
            getBundles().clear();
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
         case BundleTreeModelPackage.OS_GI_FY_CONTEXT__BUNDLES :
            return bundles != null && !bundles.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // OSGiFyContextImpl
