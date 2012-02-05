/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.modeling.common.Annotatable;
import org.sourcepit.modeling.common.Extendable;
import org.sourcepit.modeling.common.XAnnotatable;
import org.sourcepit.osgify.core.model.java.Directory;
import org.sourcepit.osgify.core.model.java.File;
import org.sourcepit.osgify.core.model.java.ImportDeclaration;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaClass;
import org.sourcepit.osgify.core.model.java.JavaCompilationUnit;
import org.sourcepit.osgify.core.model.java.JavaElement;
import org.sourcepit.osgify.core.model.java.JavaFile;
import org.sourcepit.osgify.core.model.java.JavaModelPackage;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaProject;
import org.sourcepit.osgify.core.model.java.JavaResource;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.Named;
import org.sourcepit.osgify.core.model.java.QualifiedJavaElement;
import org.sourcepit.osgify.core.model.java.Resource;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgify.core.model.java.JavaModelPackage
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
      public Adapter caseNamed(Named object)
      {
         return createNamedAdapter();
      }

      @Override
      public Adapter caseResource(Resource object)
      {
         return createResourceAdapter();
      }

      @Override
      public Adapter caseDirectory(Directory object)
      {
         return createDirectoryAdapter();
      }

      @Override
      public Adapter caseFile(File object)
      {
         return createFileAdapter();
      }

      @Override
      public Adapter caseJavaElement(JavaElement object)
      {
         return createJavaElementAdapter();
      }

      @Override
      public Adapter caseQualifiedJavaElement(QualifiedJavaElement object)
      {
         return createQualifiedJavaElementAdapter();
      }

      @Override
      public Adapter caseJavaResourceBundle(JavaResourceBundle object)
      {
         return createJavaResourceBundleAdapter();
      }

      @Override
      public Adapter caseJavaProject(JavaProject object)
      {
         return createJavaProjectAdapter();
      }

      @Override
      public Adapter caseJavaArchive(JavaArchive object)
      {
         return createJavaArchiveAdapter();
      }

      @Override
      public Adapter caseJavaResourceDirectory(JavaResourceDirectory object)
      {
         return createJavaResourceDirectoryAdapter();
      }

      @Override
      public Adapter caseJavaResourcesRoot(JavaResourcesRoot object)
      {
         return createJavaResourcesRootAdapter();
      }

      @Override
      public Adapter caseJavaPackage(JavaPackage object)
      {
         return createJavaPackageAdapter();
      }

      @Override
      public Adapter caseJavaFile(JavaFile object)
      {
         return createJavaFileAdapter();
      }

      @Override
      public Adapter caseJavaClass(JavaClass object)
      {
         return createJavaClassAdapter();
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
      public Adapter caseJavaType(JavaType object)
      {
         return createJavaTypeAdapter();
      }

      @Override
      public Adapter caseJavaResource(JavaResource object)
      {
         return createJavaResourceAdapter();
      }

      @Override
      public Adapter caseExtendable(Extendable object)
      {
         return createExtendableAdapter();
      }

      @Override
      public Adapter caseAnnotatable(Annotatable object)
      {
         return createAnnotatableAdapter();
      }

      @Override
      public Adapter caseXAnnotatable(XAnnotatable object)
      {
         return createXAnnotatableAdapter();
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
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.Named <em>Named</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.Named
    * @generated
    */
   public Adapter createNamedAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.Resource
    * <em>Resource</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.Resource
    * @generated
    */
   public Adapter createResourceAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.Directory
    * <em>Directory</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.Directory
    * @generated
    */
   public Adapter createDirectoryAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.File <em>File</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.File
    * @generated
    */
   public Adapter createFileAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaElement
    * <em>Java Element</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaElement
    * @generated
    */
   public Adapter createJavaElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.QualifiedJavaElement
    * <em>Qualified Java Element</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.QualifiedJavaElement
    * @generated
    */
   public Adapter createQualifiedJavaElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaResourceBundle
    * <em>Java Resource Bundle</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaResourceBundle
    * @generated
    */
   public Adapter createJavaResourceBundleAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaProject
    * <em>Java Project</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaProject
    * @generated
    */
   public Adapter createJavaProjectAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaArchive
    * <em>Java Archive</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaArchive
    * @generated
    */
   public Adapter createJavaArchiveAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaResourceDirectory
    * <em>Java Resource Directory</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaResourceDirectory
    * @generated
    */
   public Adapter createJavaResourceDirectoryAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaResourcesRoot
    * <em>Java Resources Root</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaResourcesRoot
    * @generated
    */
   public Adapter createJavaResourcesRootAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaPackage
    * <em>Java Package</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaPackage
    * @generated
    */
   public Adapter createJavaPackageAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaFile
    * <em>Java File</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaFile
    * @generated
    */
   public Adapter createJavaFileAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaClass
    * <em>Java Class</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaClass
    * @generated
    */
   public Adapter createJavaClassAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaCompilationUnit
    * <em>Java Compilation Unit</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaCompilationUnit
    * @generated
    */
   public Adapter createJavaCompilationUnitAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.ImportDeclaration
    * <em>Import Declaration</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.ImportDeclaration
    * @generated
    */
   public Adapter createImportDeclarationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaType
    * <em>Java Type</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaType
    * @generated
    */
   public Adapter createJavaTypeAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.osgify.core.model.java.JavaResource
    * <em>Java Resource</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.osgify.core.model.java.JavaResource
    * @generated
    */
   public Adapter createJavaResourceAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link org.sourcepit.modeling.common.Extendable <em>Extendable</em>}
    * '.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modeling.common.Extendable
    * @generated
    */
   public Adapter createExtendableAdapter()
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
    * Creates a new adapter for an object of class '{@link org.sourcepit.modeling.common.XAnnotatable
    * <em>XAnnotatable</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * 
    * @return the new adapter.
    * @see org.sourcepit.modeling.common.XAnnotatable
    * @generated
    */
   public Adapter createXAnnotatableAdapter()
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
