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
import org.sourcepit.osgify.bundletree.Bundle;
import org.sourcepit.osgify.bundletree.BundleReference;
import org.sourcepit.osgify.bundletree.BundleTreeModelFactory;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.bundletree.OSGiFyContext;

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
         case BundleTreeModelPackage.OS_GI_FY_CONTEXT :
            return createOSGiFyContext();
         case BundleTreeModelPackage.BUNDLE :
            return createBundle();
         case BundleTreeModelPackage.BUNDLE_REFERENCE :
            return createBundleReference();
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
   public OSGiFyContext createOSGiFyContext()
   {
      OSGiFyContextImpl osGiFyContext = new OSGiFyContextImpl();
      return osGiFyContext;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Bundle createBundle()
   {
      BundleImpl bundle = new BundleImpl();
      return bundle;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleReference createBundleReference()
   {
      BundleReferenceImpl bundleReference = new BundleReferenceImpl();
      return bundleReference;
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
