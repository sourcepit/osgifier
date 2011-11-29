/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.osgify.bundletree.BundleNode;
import org.sourcepit.osgify.bundletree.BundleTree;
import org.sourcepit.osgify.bundletree.BundleTreeModelFactory;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.bundletree.RootBundleNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class BundleTreeModelFactoryImpl extends EFactoryImpl implements BundleTreeModelFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static BundleTreeModelFactory init()
   {
      try
      {
         BundleTreeModelFactory theBundleTreeModelFactory = (BundleTreeModelFactory) EPackage.Registry.INSTANCE
            .getEFactory("http://org.sourcepit.osgifyme/bundle-tee/1.0");
         if (theBundleTreeModelFactory != null)
         {
            return theBundleTreeModelFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new BundleTreeModelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleTreeModelFactoryImpl()
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
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case BundleTreeModelPackage.BUNDLE_TREE :
            return createBundleTree();
         case BundleTreeModelPackage.ROOT_BUNDLE_NODE :
            return createRootBundleNode();
         case BundleTreeModelPackage.BUNDLE_NODE :
            return createBundleNode();
         default :
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleTree createBundleTree()
   {
      BundleTreeImpl bundleTree = new BundleTreeImpl();
      return bundleTree;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public RootBundleNode createRootBundleNode()
   {
      RootBundleNodeImpl rootBundleNode = new RootBundleNodeImpl();
      return rootBundleNode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleNode createBundleNode()
   {
      BundleNodeImpl bundleNode = new BundleNodeImpl();
      return bundleNode;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleTreeModelPackage getBundleTreeModelPackage()
   {
      return (BundleTreeModelPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @deprecated
    * @generated
    */
   @Deprecated
   public static BundleTreeModelPackage getPackage()
   {
      return BundleTreeModelPackage.eINSTANCE;
   }

} // BundleTreeModelFactoryImpl
