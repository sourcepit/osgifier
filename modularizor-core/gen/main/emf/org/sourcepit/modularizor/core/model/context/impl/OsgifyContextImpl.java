/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.context.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.core.model.context.ContextModelPackage;
import org.sourcepit.modularizor.core.model.context.OsgifyContext;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Osgify Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.core.model.context.impl.OsgifyContextImpl#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OsgifyContextImpl extends XAnnotatableImpl implements OsgifyContext
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
   protected EList<BundleCandidate> bundles;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected OsgifyContextImpl()
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
      return ContextModelPackage.Literals.OSGIFY_CONTEXT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<BundleCandidate> getBundles()
   {
      if (bundles == null)
      {
         bundles = new EObjectContainmentEList<BundleCandidate>(BundleCandidate.class, this,
            ContextModelPackage.OSGIFY_CONTEXT__BUNDLES);
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
         case ContextModelPackage.OSGIFY_CONTEXT__BUNDLES :
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
         case ContextModelPackage.OSGIFY_CONTEXT__BUNDLES :
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
         case ContextModelPackage.OSGIFY_CONTEXT__BUNDLES :
            getBundles().clear();
            getBundles().addAll((Collection<? extends BundleCandidate>) newValue);
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
         case ContextModelPackage.OSGIFY_CONTEXT__BUNDLES :
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
         case ContextModelPackage.OSGIFY_CONTEXT__BUNDLES :
            return bundles != null && !bundles.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // OsgifyContextImpl
