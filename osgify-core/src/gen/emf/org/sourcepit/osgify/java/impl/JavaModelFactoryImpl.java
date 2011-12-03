/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.java.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.osgify.java.ImportDeclaration;
import org.sourcepit.osgify.java.JavaArchive;
import org.sourcepit.osgify.java.JavaClass;
import org.sourcepit.osgify.java.JavaCompilationUnit;
import org.sourcepit.osgify.java.JavaModelFactory;
import org.sourcepit.osgify.java.JavaModelPackage;
import org.sourcepit.osgify.java.JavaPackage;
import org.sourcepit.osgify.java.JavaPackageRoot;
import org.sourcepit.osgify.java.JavaProject;
import org.sourcepit.osgify.java.JavaType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class JavaModelFactoryImpl extends EFactoryImpl implements JavaModelFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static JavaModelFactory init()
   {
      try
      {
         JavaModelFactory theJavaModelFactory = (JavaModelFactory) EPackage.Registry.INSTANCE
            .getEFactory("http://www.sourcepit.org/osgify/java/0.1");
         if (theJavaModelFactory != null)
         {
            return theJavaModelFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new JavaModelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaModelFactoryImpl()
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
         case JavaModelPackage.JAVA_TYPE :
            return createJavaType();
         case JavaModelPackage.JAVA_COMPILATION_UNIT :
            return createJavaCompilationUnit();
         case JavaModelPackage.IMPORT_DECLARATION :
            return createImportDeclaration();
         case JavaModelPackage.JAVA_CLASS :
            return createJavaClass();
         case JavaModelPackage.JAVA_PACKAGE :
            return createJavaPackage();
         case JavaModelPackage.JAVA_ARCHIVE :
            return createJavaArchive();
         case JavaModelPackage.JAVA_PROJECT :
            return createJavaProject();
         case JavaModelPackage.JAVA_PACKAGE_ROOT :
            return createJavaPackageRoot();
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
   public JavaType createJavaType()
   {
      JavaTypeImpl javaType = new JavaTypeImpl();
      return javaType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaCompilationUnit createJavaCompilationUnit()
   {
      JavaCompilationUnitImpl javaCompilationUnit = new JavaCompilationUnitImpl();
      return javaCompilationUnit;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ImportDeclaration createImportDeclaration()
   {
      ImportDeclarationImpl importDeclaration = new ImportDeclarationImpl();
      return importDeclaration;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaClass createJavaClass()
   {
      JavaClassImpl javaClass = new JavaClassImpl();
      return javaClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackage createJavaPackage()
   {
      JavaPackageImpl javaPackage = new JavaPackageImpl();
      return javaPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaArchive createJavaArchive()
   {
      JavaArchiveImpl javaArchive = new JavaArchiveImpl();
      return javaArchive;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaProject createJavaProject()
   {
      JavaProjectImpl javaProject = new JavaProjectImpl();
      return javaProject;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageRoot createJavaPackageRoot()
   {
      JavaPackageRootImpl javaPackageRoot = new JavaPackageRootImpl();
      return javaPackageRoot;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaModelPackage getJavaModelPackage()
   {
      return (JavaModelPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @deprecated
    * @generated
    */
   @Deprecated
   public static JavaModelPackage getPackage()
   {
      return JavaModelPackage.eINSTANCE;
   }

} // JavaModelFactoryImpl
