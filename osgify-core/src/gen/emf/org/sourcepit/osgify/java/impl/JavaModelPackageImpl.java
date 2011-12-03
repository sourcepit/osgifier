/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.java.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sourcepit.common.manifest.ManifestPackage;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgify.context.ContextModelPackage;
import org.sourcepit.osgify.context.impl.ContextModelPackageImpl;
import org.sourcepit.osgify.java.FullyQualified;
import org.sourcepit.osgify.java.ImportDeclaration;
import org.sourcepit.osgify.java.JavaArchive;
import org.sourcepit.osgify.java.JavaClass;
import org.sourcepit.osgify.java.JavaCompilationUnit;
import org.sourcepit.osgify.java.JavaModelFactory;
import org.sourcepit.osgify.java.JavaModelPackage;
import org.sourcepit.osgify.java.JavaPackage;
import org.sourcepit.osgify.java.JavaPackageBundle;
import org.sourcepit.osgify.java.JavaPackageRoot;
import org.sourcepit.osgify.java.JavaProject;
import org.sourcepit.osgify.java.JavaType;
import org.sourcepit.osgify.java.JavaTypeRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class JavaModelPackageImpl extends EPackageImpl implements JavaModelPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaCompilationUnitEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass importDeclarationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaTypeRootEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaClassEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaPackageEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaPackageBundleEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaArchiveEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaProjectEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass fullyQualifiedEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaPackageRootEClass = null;

   /**
    * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
    * EPackage.Registry} by the package
    * package URI value.
    * <p>
    * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
    * performs initialization of the package, or returns the registered package, if one already exists. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see org.sourcepit.osgify.java.JavaModelPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private JavaModelPackageImpl()
   {
      super(eNS_URI, JavaModelFactory.eINSTANCE);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private static boolean isInited = false;

   /**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    * 
    * <p>
    * This method is used to initialize {@link JavaModelPackage#eINSTANCE} when that field is accessed. Clients should
    * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
    * --> <!-- end-user-doc -->
    * 
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static JavaModelPackage init()
   {
      if (isInited)
         return (JavaModelPackage) EPackage.Registry.INSTANCE.getEPackage(JavaModelPackage.eNS_URI);

      // Obtain or create and register package
      JavaModelPackageImpl theJavaModelPackage = (JavaModelPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof JavaModelPackageImpl
         ? EPackage.Registry.INSTANCE.get(eNS_URI)
         : new JavaModelPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      CommonModelPackage.eINSTANCE.eClass();
      ManifestPackage.eINSTANCE.eClass();

      // Obtain or create and register interdependencies
      ContextModelPackageImpl theContextModelPackage = (ContextModelPackageImpl) (EPackage.Registry.INSTANCE
         .getEPackage(ContextModelPackage.eNS_URI) instanceof ContextModelPackageImpl ? EPackage.Registry.INSTANCE
         .getEPackage(ContextModelPackage.eNS_URI) : ContextModelPackage.eINSTANCE);

      // Create package meta-data objects
      theJavaModelPackage.createPackageContents();
      theContextModelPackage.createPackageContents();

      // Initialize created meta-data
      theJavaModelPackage.initializePackageContents();
      theContextModelPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theJavaModelPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(JavaModelPackage.eNS_URI, theJavaModelPackage);
      return theJavaModelPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaType()
   {
      return javaTypeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaType_InnerTypes()
   {
      return (EReference) javaTypeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaType_OuterType()
   {
      return (EReference) javaTypeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getJavaType_SimpleName()
   {
      return (EAttribute) javaTypeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaCompilationUnit()
   {
      return javaCompilationUnitEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaCompilationUnit_ImportDeclarations()
   {
      return (EReference) javaCompilationUnitEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getImportDeclaration()
   {
      return importDeclarationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getImportDeclaration_CompilationUnit()
   {
      return (EReference) importDeclarationEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaTypeRoot()
   {
      return javaTypeRootEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaTypeRoot_Type()
   {
      return (EReference) javaTypeRootEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaTypeRoot_ParentPackage()
   {
      return (EReference) javaTypeRootEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaClass()
   {
      return javaClassEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaPackage()
   {
      return javaPackageEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackage_TypeRoots()
   {
      return (EReference) javaPackageEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getJavaPackage_SimpleName()
   {
      return (EAttribute) javaPackageEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackage_Packages()
   {
      return (EReference) javaPackageEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackage_ParentPackage()
   {
      return (EReference) javaPackageEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaPackageBundle()
   {
      return javaPackageBundleEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackageBundle_PackageRoots()
   {
      return (EReference) javaPackageBundleEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaArchive()
   {
      return javaArchiveEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaProject()
   {
      return javaProjectEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getFullyQualified()
   {
      return fullyQualifiedEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaPackageRoot()
   {
      return javaPackageRootEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getJavaPackageRoot_Path()
   {
      return (EAttribute) javaPackageRootEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackageRoot_RootPackages()
   {
      return (EReference) javaPackageRootEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackageRoot_PackageBundle()
   {
      return (EReference) javaPackageRootEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaModelFactory getJavaModelFactory()
   {
      return (JavaModelFactory) getEFactoryInstance();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private boolean isCreated = false;

   /**
    * Creates the meta-model objects for the package. This method is
    * guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void createPackageContents()
   {
      if (isCreated)
         return;
      isCreated = true;

      // Create classes and their features
      javaTypeEClass = createEClass(JAVA_TYPE);
      createEReference(javaTypeEClass, JAVA_TYPE__INNER_TYPES);
      createEReference(javaTypeEClass, JAVA_TYPE__OUTER_TYPE);
      createEAttribute(javaTypeEClass, JAVA_TYPE__SIMPLE_NAME);

      javaCompilationUnitEClass = createEClass(JAVA_COMPILATION_UNIT);
      createEReference(javaCompilationUnitEClass, JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS);

      importDeclarationEClass = createEClass(IMPORT_DECLARATION);
      createEReference(importDeclarationEClass, IMPORT_DECLARATION__COMPILATION_UNIT);

      javaTypeRootEClass = createEClass(JAVA_TYPE_ROOT);
      createEReference(javaTypeRootEClass, JAVA_TYPE_ROOT__TYPE);
      createEReference(javaTypeRootEClass, JAVA_TYPE_ROOT__PARENT_PACKAGE);

      javaClassEClass = createEClass(JAVA_CLASS);

      javaPackageEClass = createEClass(JAVA_PACKAGE);
      createEReference(javaPackageEClass, JAVA_PACKAGE__TYPE_ROOTS);
      createEAttribute(javaPackageEClass, JAVA_PACKAGE__SIMPLE_NAME);
      createEReference(javaPackageEClass, JAVA_PACKAGE__PACKAGES);
      createEReference(javaPackageEClass, JAVA_PACKAGE__PARENT_PACKAGE);

      javaPackageBundleEClass = createEClass(JAVA_PACKAGE_BUNDLE);
      createEReference(javaPackageBundleEClass, JAVA_PACKAGE_BUNDLE__PACKAGE_ROOTS);

      javaArchiveEClass = createEClass(JAVA_ARCHIVE);

      javaProjectEClass = createEClass(JAVA_PROJECT);

      fullyQualifiedEClass = createEClass(FULLY_QUALIFIED);

      javaPackageRootEClass = createEClass(JAVA_PACKAGE_ROOT);
      createEAttribute(javaPackageRootEClass, JAVA_PACKAGE_ROOT__PATH);
      createEReference(javaPackageRootEClass, JAVA_PACKAGE_ROOT__ROOT_PACKAGES);
      createEReference(javaPackageRootEClass, JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private boolean isInitialized = false;

   /**
    * Complete the initialization of the package and its meta-model. This
    * method is guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void initializePackageContents()
   {
      if (isInitialized)
         return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Obtain other dependent packages
      CommonModelPackage theCommonModelPackage = (CommonModelPackage) EPackage.Registry.INSTANCE
         .getEPackage(CommonModelPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      javaTypeEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());
      javaTypeEClass.getESuperTypes().add(this.getFullyQualified());
      javaCompilationUnitEClass.getESuperTypes().add(this.getJavaTypeRoot());
      importDeclarationEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());
      javaTypeRootEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());
      javaClassEClass.getESuperTypes().add(this.getJavaTypeRoot());
      javaPackageEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());
      javaPackageEClass.getESuperTypes().add(this.getFullyQualified());
      javaPackageBundleEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());
      javaArchiveEClass.getESuperTypes().add(this.getJavaPackageBundle());
      javaProjectEClass.getESuperTypes().add(this.getJavaPackageBundle());
      javaPackageRootEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());

      // Initialize classes and features; add operations and parameters
      initEClass(javaTypeEClass, JavaType.class, "JavaType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaType_InnerTypes(), this.getJavaType(), this.getJavaType_OuterType(), "innerTypes", null, 0,
         -1, JavaType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaType_OuterType(), this.getJavaType(), this.getJavaType_InnerTypes(), "outerType", null, 0,
         1, JavaType.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getJavaType_SimpleName(), ecorePackage.getEString(), "simpleName", null, 1, 1, JavaType.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      addEOperation(javaTypeEClass, this.getJavaTypeRoot(), "getTypeRoot", 1, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaCompilationUnitEClass, JavaCompilationUnit.class, "JavaCompilationUnit", !IS_ABSTRACT,
         !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaCompilationUnit_ImportDeclarations(), this.getImportDeclaration(),
         this.getImportDeclaration_CompilationUnit(), "importDeclarations", null, 0, -1, JavaCompilationUnit.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(importDeclarationEClass, ImportDeclaration.class, "ImportDeclaration", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getImportDeclaration_CompilationUnit(), this.getJavaCompilationUnit(),
         this.getJavaCompilationUnit_ImportDeclarations(), "compilationUnit", null, 1, 1, ImportDeclaration.class,
         IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(javaTypeRootEClass, JavaTypeRoot.class, "JavaTypeRoot", IS_ABSTRACT, IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaTypeRoot_Type(), this.getJavaType(), null, "type", null, 1, 1, JavaTypeRoot.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaTypeRoot_ParentPackage(), this.getJavaPackage(), this.getJavaPackage_TypeRoots(),
         "parentPackage", null, 1, 1, JavaTypeRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(javaClassEClass, JavaClass.class, "JavaClass", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(javaPackageEClass, JavaPackage.class, "JavaPackage", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaPackage_TypeRoots(), this.getJavaTypeRoot(), this.getJavaTypeRoot_ParentPackage(),
         "typeRoots", null, 0, -1, JavaPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getJavaPackage_SimpleName(), ecorePackage.getEString(), "simpleName", "", 1, 1, JavaPackage.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaPackage_Packages(), this.getJavaPackage(), this.getJavaPackage_ParentPackage(), "packages",
         null, 0, -1, JavaPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaPackage_ParentPackage(), this.getJavaPackage(), this.getJavaPackage_Packages(),
         "parentPackage", null, 0, 1, JavaPackage.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      EOperation op = addEOperation(javaPackageEClass, this.getJavaPackage(), "getSubPackage", 0, 1, IS_UNIQUE,
         IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(javaPackageEClass, this.getJavaPackageBundle(), "getPackageBundle", 1, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(javaPackageEClass, this.getJavaPackageRoot(), "getPackageRoot", 1, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaPackageBundleEClass, JavaPackageBundle.class, "JavaPackageBundle", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaPackageBundle_PackageRoots(), this.getJavaPackageRoot(),
         this.getJavaPackageRoot_PackageBundle(), "packageRoots", null, 0, -1, JavaPackageBundle.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      op = addEOperation(javaPackageBundleEClass, this.getJavaPackage(), "getRootPackages", 1, -1, IS_UNIQUE,
         IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaPackageBundleEClass, this.getJavaPackage(), "getPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "fullyQualifiedName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaPackageBundleEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "packageName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "typeName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaPackageBundleEClass, this.getJavaPackageRoot(), "getPackageRoot", 0, 1, IS_UNIQUE,
         IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaPackageBundleEClass, this.getJavaPackageRoot(), "getPackageRoot", 0, 1, IS_UNIQUE,
         IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaArchiveEClass, JavaArchive.class, "JavaArchive", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      op = addEOperation(javaArchiveEClass, this.getJavaPackage(), "getPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "fullyQualifiedName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaArchiveEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "packageName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "typeName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaProjectEClass, JavaProject.class, "JavaProject", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(fullyQualifiedEClass, FullyQualified.class, "FullyQualified", IS_ABSTRACT, IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      addEOperation(fullyQualifiedEClass, ecorePackage.getEString(), "getFullyQualifiedName", 1, 1, IS_UNIQUE,
         IS_ORDERED);

      addEOperation(fullyQualifiedEClass, ecorePackage.getEString(), "getSimpleName", 1, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaPackageRootEClass, JavaPackageRoot.class, "JavaPackageRoot", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getJavaPackageRoot_Path(), ecorePackage.getEString(), "path", null, 1, 1, JavaPackageRoot.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaPackageRoot_RootPackages(), this.getJavaPackage(), null, "rootPackages", null, 0, -1,
         JavaPackageRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaPackageRoot_PackageBundle(), this.getJavaPackageBundle(),
         this.getJavaPackageBundle_PackageRoots(), "packageBundle", null, 1, 1, JavaPackageRoot.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} // JavaModelPackageImpl
