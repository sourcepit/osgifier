/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.util;

import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.modeling.common.Annotatable;
import org.sourcepit.osgifyme.core.java.DependencyNode;
import org.sourcepit.osgifyme.core.java.FullyQualified;
import org.sourcepit.osgifyme.core.java.ImportDeclaration;
import org.sourcepit.osgifyme.core.java.JavaArchive;
import org.sourcepit.osgifyme.core.java.JavaClass;
import org.sourcepit.osgifyme.core.java.JavaCompilationUnit;
import org.sourcepit.osgifyme.core.java.JavaModel;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaPackageBundle;
import org.sourcepit.osgifyme.core.java.JavaProject;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.osgifyme.core.java.JavaTypeRoot;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage
 * @generated
 */
public class JavaModelAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected static JavaModelPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaModelAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = JavaModelPackage.eINSTANCE;
      }
   }

   /**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
    * This implementation returns <code>true</code> if the object is either the model's package or is an instance object
    * of the model.
    * <!-- end-user-doc -->
    * 
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object object)
   {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject) object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

   /**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaModelSwitch<Adapter> modelSwitch = new JavaModelSwitch<Adapter>()
   {
      @Override
      public Adapter caseJavaType(JavaType object)
      {
         return createJavaTypeAdapter();
      }

      @Override
      public Adapter caseJavaCompilationUnit(JavaCompilationUnit object)
      {
         return createJavaCompilationUnitAdapter();
      }

      @Override
      public Adapter caseImportDeclaration(ImportDeclaration object)
      {
         return createImportDeclarationAdapter();
      }

      @Override
      public Adapter caseJavaTypeRoot(JavaTypeRoot object)
      {
         return createJavaTypeRootAdapter();
      }

      @Override
      public Adapter caseJavaClass(JavaClass object)
      {
         return createJavaClassAdapter();
      }

      @Override
      public Adapter caseJavaPackage(JavaPackage object)
      {
         return createJavaPackageAdapter();
      }

      @Override
      public Adapter caseJavaPackageBundle(JavaPackageBundle object)
      {
         return createJavaPackageBundleAdapter();
      }

      @Override
      public Adapter caseJavaPackageMapEntry(Entry<String, EList<JavaPackage>> object)
      {
         return createJavaPackageMapEntryAdapter();
      }

      @Override
      public Adapter caseJavaArchive(JavaArchive object)
      {
         return createJavaArchiveAdapter();
      }

      @Override
      public Adapter caseJavaProject(JavaProject object)
      {
         return createJavaProjectAdapter();
      }

      @Override
      public Adapter caseJavaModel(JavaModel object)
      {
         return createJavaModelAdapter();
      }

      @Override
      public Adapter caseDependencyNode(DependencyNode object)
      {
         return createDependencyNodeAdapter();
      }

      @Override
      public Adapter caseFullyQualified(FullyQualified object)
      {
         return createFullyQualifiedAdapter();
      }

      @Override
      public Adapter caseAnnotatable(Annotatable object)
      {
         return createAnnotatableAdapter();
      }

      @Override
      public Adapter defaultCase(EObject object)
      {
         return createEObjectAdapter();
      }
   };

   /**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
   @Override
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject) target);
   }


   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaType <em>Java Type</em>}
    * '.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaType
    * @generated
    */
   public Adapter createJavaTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaCompilationUnit
    * <em>Java Compilation Unit</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaCompilationUnit
    * @generated
    */
   public Adapter createJavaCompilationUnitAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.ImportDeclaration
    * <em>Import Declaration</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.ImportDeclaration
    * @generated
    */
   public Adapter createImportDeclarationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaTypeRoot
    * <em>Java Type Root</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaTypeRoot
    * @generated
    */
   public Adapter createJavaTypeRootAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaClass
    * <em>Java Class</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaClass
    * @generated
    */
   public Adapter createJavaClassAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaPackage
    * <em>Java Package</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaPackage
    * @generated
    */
   public Adapter createJavaPackageAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaPackageBundle
    * <em>Java Package Bundle</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageBundle
    * @generated
    */
   public Adapter createJavaPackageBundleAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Java Package Map Entry</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see java.util.Map.Entry
    * @generated
    */
   public Adapter createJavaPackageMapEntryAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaArchive
    * <em>Java Archive</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaArchive
    * @generated
    */
   public Adapter createJavaArchiveAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaProject
    * <em>Java Project</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaProject
    * @generated
    */
   public Adapter createJavaProjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.JavaModel
    * <em>Java Model</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.JavaModel
    * @generated
    */
   public Adapter createJavaModelAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.DependencyNode
    * <em>Dependency Node</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode
    * @generated
    */
   public Adapter createDependencyNodeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgifyme.core.java.FullyQualified
    * <em>Fully Qualified</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgifyme.core.java.FullyQualified
    * @generated
    */
   public Adapter createFullyQualifiedAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modeling.common.Annotatable
    * <em>Annotatable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modeling.common.Annotatable
    * @generated
    */
   public Adapter createAnnotatableAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @generated
    */
   public Adapter createEObjectAdapter()
   {
      return null;
   }

} // JavaModelAdapterFactory
