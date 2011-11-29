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
import org.sourcepit.osgify.bundletree.BundleTree;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.bundletree.RootBundleNode;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Tree</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleTreeImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.BundleTreeImpl#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleTreeImpl extends EObjectImpl implements BundleTree
{
   /**
    * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getNodes()
    * @generated
    * @ordered
    */
   protected EList<RootBundleNode> nodes;

   /**
    * The cached value of the '{@link #getBundles() <em>Bundles</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getBundles()
    * @generated
    * @ordered
    */
   protected EList<JavaPackageBundle> bundles;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected BundleTreeImpl()
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
      return BundleTreeModelPackage.Literals.BUNDLE_TREE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<RootBundleNode> getNodes()
   {
      if (nodes == null)
      {
         nodes = new EObjectContainmentEList<RootBundleNode>(RootBundleNode.class, this,
            BundleTreeModelPackage.BUNDLE_TREE__NODES);
      }
      return nodes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaPackageBundle> getBundles()
   {
      if (bundles == null)
      {
         bundles = new EObjectContainmentEList<JavaPackageBundle>(JavaPackageBundle.class, this,
            BundleTreeModelPackage.BUNDLE_TREE__BUNDLES);
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
         case BundleTreeModelPackage.BUNDLE_TREE__NODES :
            return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
         case BundleTreeModelPackage.BUNDLE_TREE__BUNDLES :
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
         case BundleTreeModelPackage.BUNDLE_TREE__NODES :
            return getNodes();
         case BundleTreeModelPackage.BUNDLE_TREE__BUNDLES :
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
         case BundleTreeModelPackage.BUNDLE_TREE__NODES :
            getNodes().clear();
            getNodes().addAll((Collection<? extends RootBundleNode>) newValue);
            return;
         case BundleTreeModelPackage.BUNDLE_TREE__BUNDLES :
            getBundles().clear();
            getBundles().addAll((Collection<? extends JavaPackageBundle>) newValue);
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
         case BundleTreeModelPackage.BUNDLE_TREE__NODES :
            getNodes().clear();
            return;
         case BundleTreeModelPackage.BUNDLE_TREE__BUNDLES :
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
         case BundleTreeModelPackage.BUNDLE_TREE__NODES :
            return nodes != null && !nodes.isEmpty();
         case BundleTreeModelPackage.BUNDLE_TREE__BUNDLES :
            return bundles != null && !bundles.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // BundleTreeImpl
