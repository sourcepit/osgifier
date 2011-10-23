/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.impl;

import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgifyme.core.java.DependencyNode;
import org.sourcepit.osgifyme.core.java.FullyQualified;
import org.sourcepit.osgifyme.core.java.ImportDeclaration;
import org.sourcepit.osgifyme.core.java.JavaArchive;
import org.sourcepit.osgifyme.core.java.JavaClass;
import org.sourcepit.osgifyme.core.java.JavaCompilationUnit;
import org.sourcepit.osgifyme.core.java.JavaModel;
import org.sourcepit.osgifyme.core.java.JavaModelFactory;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaPackageBundle;
import org.sourcepit.osgifyme.core.java.JavaProject;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.osgifyme.core.java.JavaTypeRoot;

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
   private EClass javaPackageMapEntryEClass = null;

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
   private EClass javaModelEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass dependencyNodeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass fullyQualifiedEClass = null;

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
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#eNS_URI
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

      // Create package meta-data objects
      theJavaModelPackage.createPackageContents();

      // Initialize created meta-data
      theJavaModelPackage.initializePackageContents();

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
   public EReference getJavaPackageBundle_PathToRootPackagesMap()
   {
      return (EReference) javaPackageBundleEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackageBundle_Dependencies()
   {
      return (EReference) javaPackageBundleEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaPackageMapEntry()
   {
      return javaPackageMapEntryEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaPackageMapEntry_Value()
   {
      return (EReference) javaPackageMapEntryEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getJavaPackageMapEntry_Key()
   {
      return (EAttribute) javaPackageMapEntryEClass.getEStructuralFeatures().get(1);
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
   public EClass getJavaModel()
   {
      return javaModelEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaModel_Projects()
   {
      return (EReference) javaModelEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaModel_Archives()
   {
      return (EReference) javaModelEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getDependencyNode()
   {
      return dependencyNodeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getDependencyNode_Target()
   {
      return (EReference) dependencyNodeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getDependencyNode_Optional()
   {
      return (EAttribute) dependencyNodeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getDependencyNode_Dependencies()
   {
      return (EReference) dependencyNodeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getDependencyNode_Enabled()
   {
      return (EAttribute) dependencyNodeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getDependencyNode_ParentNode()
   {
      return (EReference) dependencyNodeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getDependencyNode_PackageBundle()
   {
      return (EReference) dependencyNodeEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getDependencyNode_Scope()
   {
      return (EAttribute) dependencyNodeEClass.getEStructuralFeatures().get(6);
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
      createEReference(javaPackageBundleEClass, JAVA_PACKAGE_BUNDLE__PATH_TO_ROOT_PACKAGES_MAP);
      createEReference(javaPackageBundleEClass, JAVA_PACKAGE_BUNDLE__DEPENDENCIES);

      javaPackageMapEntryEClass = createEClass(JAVA_PACKAGE_MAP_ENTRY);
      createEReference(javaPackageMapEntryEClass, JAVA_PACKAGE_MAP_ENTRY__VALUE);
      createEAttribute(javaPackageMapEntryEClass, JAVA_PACKAGE_MAP_ENTRY__KEY);

      javaArchiveEClass = createEClass(JAVA_ARCHIVE);

      javaProjectEClass = createEClass(JAVA_PROJECT);

      javaModelEClass = createEClass(JAVA_MODEL);
      createEReference(javaModelEClass, JAVA_MODEL__PROJECTS);
      createEReference(javaModelEClass, JAVA_MODEL__ARCHIVES);

      dependencyNodeEClass = createEClass(DEPENDENCY_NODE);
      createEReference(dependencyNodeEClass, DEPENDENCY_NODE__TARGET);
      createEAttribute(dependencyNodeEClass, DEPENDENCY_NODE__OPTIONAL);
      createEReference(dependencyNodeEClass, DEPENDENCY_NODE__DEPENDENCIES);
      createEAttribute(dependencyNodeEClass, DEPENDENCY_NODE__ENABLED);
      createEReference(dependencyNodeEClass, DEPENDENCY_NODE__PARENT_NODE);
      createEReference(dependencyNodeEClass, DEPENDENCY_NODE__PACKAGE_BUNDLE);
      createEAttribute(dependencyNodeEClass, DEPENDENCY_NODE__SCOPE);

      fullyQualifiedEClass = createEClass(FULLY_QUALIFIED);
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
      javaModelEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());
      dependencyNodeEClass.getESuperTypes().add(theCommonModelPackage.getAnnotatable());

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

      initEClass(javaPackageBundleEClass, JavaPackageBundle.class, "JavaPackageBundle", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaPackageBundle_PathToRootPackagesMap(), this.getJavaPackageMapEntry(), null,
         "pathToRootPackagesMap", null, 0, -1, JavaPackageBundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
         IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaPackageBundle_Dependencies(), this.getDependencyNode(),
         this.getDependencyNode_PackageBundle(), "dependencies", null, 0, -1, JavaPackageBundle.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      addEOperation(javaPackageBundleEClass, this.getJavaPackage(), "getRootPackages", 1, -1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaPackageBundleEClass, this.getJavaPackage(), "getPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "fullyQualifiedName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaPackageBundleEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "packageName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "typeName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaPackageMapEntryEClass, Entry.class, "JavaPackageMapEntry", !IS_ABSTRACT, !IS_INTERFACE,
         !IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaPackageMapEntry_Value(), this.getJavaPackage(), null, "value", null, 0, -1, Entry.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getJavaPackageMapEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Entry.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

      initEClass(javaModelEClass, JavaModel.class, "JavaModel", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaModel_Projects(), this.getJavaProject(), null, "projects", null, 0, -1, JavaModel.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaModel_Archives(), this.getJavaArchive(), null, "archives", null, 0, -1, JavaModel.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(dependencyNodeEClass, DependencyNode.class, "DependencyNode", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getDependencyNode_Target(), this.getJavaPackageBundle(), null, "target", null, 0, 1,
         DependencyNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getDependencyNode_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1,
         DependencyNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getDependencyNode_Dependencies(), this.getDependencyNode(), this.getDependencyNode_ParentNode(),
         "dependencies", null, 0, -1, DependencyNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getDependencyNode_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1,
         DependencyNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getDependencyNode_ParentNode(), this.getDependencyNode(), this.getDependencyNode_Dependencies(),
         "parentNode", null, 0, 1, DependencyNode.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getDependencyNode_PackageBundle(), this.getJavaPackageBundle(),
         this.getJavaPackageBundle_Dependencies(), "packageBundle", null, 0, 1, DependencyNode.class, IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);
      initEAttribute(getDependencyNode_Scope(), ecorePackage.getEString(), "scope", "compile", 0, 1,
         DependencyNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(fullyQualifiedEClass, FullyQualified.class, "FullyQualified", IS_ABSTRACT, IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      addEOperation(fullyQualifiedEClass, ecorePackage.getEString(), "getFullyQualifiedName", 1, 1, IS_UNIQUE,
         IS_ORDERED);

      addEOperation(fullyQualifiedEClass, ecorePackage.getEString(), "getSimpleName", 1, 1, IS_UNIQUE, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} // JavaModelPackageImpl
