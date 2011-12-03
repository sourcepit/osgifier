/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.osgify.context.BundleNode;
import org.sourcepit.osgify.context.BundleReference;
import org.sourcepit.osgify.context.ContextModelFactory;
import org.sourcepit.osgify.context.ContextModelPackage;
import org.sourcepit.osgify.context.OSGiFyContext;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContextModelFactoryImpl extends EFactoryImpl implements ContextModelFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static ContextModelFactory init()
   {
      try
      {
         ContextModelFactory theContextModelFactory = (ContextModelFactory) EPackage.Registry.INSTANCE
            .getEFactory("http://www.sourcepit.org/osgify/context/0.1");
         if (theContextModelFactory != null)
         {
            return theContextModelFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new ContextModelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ContextModelFactoryImpl()
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
         case ContextModelPackage.OS_GI_FY_CONTEXT :
            return createOSGiFyContext();
         case ContextModelPackage.BUNDLE_NODE :
            return createBundleNode();
         case ContextModelPackage.BUNDLE_REFERENCE :
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
   public ContextModelPackage getContextModelPackage()
   {
      return (ContextModelPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @deprecated
    * @generated
    */
   @Deprecated
   public static ContextModelPackage getPackage()
   {
      return ContextModelPackage.eINSTANCE;
   }

} // ContextModelFactoryImpl
