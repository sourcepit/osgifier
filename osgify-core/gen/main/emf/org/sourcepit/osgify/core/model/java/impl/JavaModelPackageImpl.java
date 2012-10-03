/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sourcepit.common.manifest.ManifestPackage;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgify.core.model.context.ContextModelPackage;
import org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl;
import org.sourcepit.osgify.core.model.java.Directory;
import org.sourcepit.osgify.core.model.java.File;
import org.sourcepit.osgify.core.model.java.ImportDeclaration;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaClass;
import org.sourcepit.osgify.core.model.java.JavaCompilationUnit;
import org.sourcepit.osgify.core.model.java.JavaElement;
import org.sourcepit.osgify.core.model.java.JavaFile;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaModelPackage;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaProject;
import org.sourcepit.osgify.core.model.java.JavaResource;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaResourcesType;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.Named;
import org.sourcepit.osgify.core.model.java.QualifiedJavaElement;
import org.sourcepit.osgify.core.model.java.Resource;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;

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
   private EClass namedEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass resourceEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass directoryEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass fileEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaElementEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass qualifiedJavaElementEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaResourceBundleEClass = null;

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
   private EClass javaArchiveEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaResourceDirectoryEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaResourcesRootEClass = null;

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
   private EClass javaFileEClass = null;

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
   private EClass javaTypeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass javaResourceEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass resourceVisitorEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EEnum javaResourcesTypeEEnum = null;

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
    * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#eNS_URI
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
   public EClass getNamed()
   {
      return namedEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getNamed_Name()
   {
      return (EAttribute) namedEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getResource()
   {
      return resourceEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getResource_ParentDirectory()
   {
      return (EReference) resourceEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getDirectory()
   {
      return directoryEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getDirectory_Resources()
   {
      return (EReference) directoryEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getFile()
   {
      return fileEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaElement()
   {
      return javaElementEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getQualifiedJavaElement()
   {
      return qualifiedJavaElementEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaResourceBundle()
   {
      return javaResourceBundleEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaResourceBundle_ResourcesRoots()
   {
      return (EReference) javaResourceBundleEClass.getEStructuralFeatures().get(0);
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
   public EClass getJavaResourceDirectory()
   {
      return javaResourceDirectoryEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getJavaResourcesRoot()
   {
      return javaResourcesRootEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaResourcesRoot_PackageBundle()
   {
      return (EReference) javaResourcesRootEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getJavaResourcesRoot_ResourcesType()
   {
      return (EAttribute) javaResourcesRootEClass.getEStructuralFeatures().get(1);
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
   public EClass getJavaFile()
   {
      return javaFileEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getJavaFile_Type()
   {
      return (EReference) javaFileEClass.getEStructuralFeatures().get(0);
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
   public EAttribute getJavaClass_Major()
   {
      return (EAttribute) javaClassEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getJavaClass_Minor()
   {
      return (EAttribute) javaClassEClass.getEStructuralFeatures().get(1);
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
   public EClass getJavaResource()
   {
      return javaResourceEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getResourceVisitor()
   {
      return resourceVisitorEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EEnum getJavaResourcesType()
   {
      return javaResourcesTypeEEnum;
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
      namedEClass = createEClass(NAMED);
      createEAttribute(namedEClass, NAMED__NAME);

      resourceEClass = createEClass(RESOURCE);
      createEReference(resourceEClass, RESOURCE__PARENT_DIRECTORY);

      directoryEClass = createEClass(DIRECTORY);
      createEReference(directoryEClass, DIRECTORY__RESOURCES);

      fileEClass = createEClass(FILE);

      javaElementEClass = createEClass(JAVA_ELEMENT);

      qualifiedJavaElementEClass = createEClass(QUALIFIED_JAVA_ELEMENT);

      javaResourceBundleEClass = createEClass(JAVA_RESOURCE_BUNDLE);
      createEReference(javaResourceBundleEClass, JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS);

      javaProjectEClass = createEClass(JAVA_PROJECT);

      javaArchiveEClass = createEClass(JAVA_ARCHIVE);

      javaResourceDirectoryEClass = createEClass(JAVA_RESOURCE_DIRECTORY);

      javaResourcesRootEClass = createEClass(JAVA_RESOURCES_ROOT);
      createEReference(javaResourcesRootEClass, JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE);
      createEAttribute(javaResourcesRootEClass, JAVA_RESOURCES_ROOT__RESOURCES_TYPE);

      javaPackageEClass = createEClass(JAVA_PACKAGE);

      javaFileEClass = createEClass(JAVA_FILE);
      createEReference(javaFileEClass, JAVA_FILE__TYPE);

      javaClassEClass = createEClass(JAVA_CLASS);
      createEAttribute(javaClassEClass, JAVA_CLASS__MAJOR);
      createEAttribute(javaClassEClass, JAVA_CLASS__MINOR);

      javaCompilationUnitEClass = createEClass(JAVA_COMPILATION_UNIT);
      createEReference(javaCompilationUnitEClass, JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS);

      importDeclarationEClass = createEClass(IMPORT_DECLARATION);
      createEReference(importDeclarationEClass, IMPORT_DECLARATION__COMPILATION_UNIT);

      javaTypeEClass = createEClass(JAVA_TYPE);
      createEReference(javaTypeEClass, JAVA_TYPE__INNER_TYPES);
      createEReference(javaTypeEClass, JAVA_TYPE__OUTER_TYPE);

      javaResourceEClass = createEClass(JAVA_RESOURCE);

      resourceVisitorEClass = createEClass(RESOURCE_VISITOR);

      // Create enums
      javaResourcesTypeEEnum = createEEnum(JAVA_RESOURCES_TYPE);
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
      resourceEClass.getESuperTypes().add(this.getNamed());
      resourceEClass.getESuperTypes().add(theCommonModelPackage.getXAnnotatable());
      directoryEClass.getESuperTypes().add(this.getResource());
      fileEClass.getESuperTypes().add(this.getResource());
      javaElementEClass.getESuperTypes().add(this.getNamed());
      javaElementEClass.getESuperTypes().add(theCommonModelPackage.getXAnnotatable());
      qualifiedJavaElementEClass.getESuperTypes().add(this.getJavaElement());
      javaResourceBundleEClass.getESuperTypes().add(this.getJavaElement());
      javaProjectEClass.getESuperTypes().add(this.getJavaResourceBundle());
      javaArchiveEClass.getESuperTypes().add(this.getJavaResourceBundle());
      javaResourceDirectoryEClass.getESuperTypes().add(this.getJavaElement());
      javaResourceDirectoryEClass.getESuperTypes().add(this.getDirectory());
      javaResourcesRootEClass.getESuperTypes().add(this.getJavaResourceDirectory());
      javaPackageEClass.getESuperTypes().add(this.getJavaResource());
      javaPackageEClass.getESuperTypes().add(this.getQualifiedJavaElement());
      javaPackageEClass.getESuperTypes().add(this.getJavaResourceDirectory());
      javaPackageEClass.getESuperTypes().add(this.getDirectory());
      javaFileEClass.getESuperTypes().add(this.getJavaResource());
      javaFileEClass.getESuperTypes().add(this.getFile());
      javaClassEClass.getESuperTypes().add(this.getJavaFile());
      javaCompilationUnitEClass.getESuperTypes().add(this.getJavaFile());
      importDeclarationEClass.getESuperTypes().add(this.getJavaElement());
      javaTypeEClass.getESuperTypes().add(this.getQualifiedJavaElement());
      javaResourceEClass.getESuperTypes().add(this.getResource());
      javaResourceEClass.getESuperTypes().add(this.getJavaElement());

      // Initialize classes and features; add operations and parameters
      initEClass(namedEClass, Named.class, "Named", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getNamed_Name(), ecorePackage.getEString(), "name", null, 1, 1, Named.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(resourceEClass, Resource.class, "Resource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getResource_ParentDirectory(), this.getDirectory(), this.getDirectory_Resources(),
         "parentDirectory", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      EOperation op = addEOperation(resourceEClass, null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, this.getResourceVisitor(), "visitor", 1, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(directoryEClass, Directory.class, "Directory", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getDirectory_Resources(), this.getResource(), this.getResource_ParentDirectory(), "resources",
         null, 0, -1, Directory.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      op = addEOperation(directoryEClass, this.getResource(), "getResource", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(directoryEClass, this.getDirectory(), "getDirectories", 0, -1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(directoryEClass, this.getDirectory(), "getDirectory", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(directoryEClass, this.getDirectory(), "getDirectory", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(directoryEClass, this.getFile(), "getFile", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(directoryEClass, this.getFile(), "getFile", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(directoryEClass, this.getFile(), "getFiles", 0, -1, IS_UNIQUE, IS_ORDERED);

      initEClass(fileEClass, File.class, "File", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(javaElementEClass, JavaElement.class, "JavaElement", IS_ABSTRACT, IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      addEOperation(javaElementEClass, this.getJavaResourceBundle(), "getResourceBundle", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(qualifiedJavaElementEClass, QualifiedJavaElement.class, "QualifiedJavaElement", IS_ABSTRACT,
         IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      addEOperation(qualifiedJavaElementEClass, ecorePackage.getEString(), "getQualifiedName", 1, 1, IS_UNIQUE,
         IS_ORDERED);

      initEClass(javaResourceBundleEClass, JavaResourceBundle.class, "JavaResourceBundle", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaResourceBundle_ResourcesRoots(), this.getJavaResourcesRoot(),
         this.getJavaResourcesRoot_PackageBundle(), "resourcesRoots", null, 0, -1, JavaResourceBundle.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      op = addEOperation(javaResourceBundleEClass, this.getJavaResourcesRoot(), "getResourcesRoot", 0, 1, IS_UNIQUE,
         IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceBundleEClass, this.getJavaResourcesRoot(), "getResourcesRoot", 0, 1, IS_UNIQUE,
         IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceBundleEClass, this.getJavaPackage(), "getPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "rootName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "qualifiedPackageName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceBundleEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "rootName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "qualifiedPackageName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "typeName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceBundleEClass, null, "accept", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, this.getResourceVisitor(), "visitor", 1, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaProjectEClass, JavaProject.class, "JavaProject", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(javaArchiveEClass, JavaArchive.class, "JavaArchive", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      op = addEOperation(javaArchiveEClass, this.getJavaPackage(), "getPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "fullyQualifiedName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaArchiveEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "packageName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "typeName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaArchiveEClass, this.getResource(), "getResource", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaArchiveEClass, this.getDirectory(), "getDirectory", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaArchiveEClass, this.getDirectory(), "getDirectory", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaArchiveEClass, this.getFile(), "getFile", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaArchiveEClass, this.getFile(), "getFile", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaResourceDirectoryEClass, JavaResourceDirectory.class, "JavaResourceDirectory", IS_ABSTRACT,
         IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      addEOperation(javaResourceDirectoryEClass, this.getJavaPackage(), "getPackages", 0, -1, IS_UNIQUE, IS_ORDERED);

      addEOperation(javaResourceDirectoryEClass, this.getJavaFile(), "getJavaFiles", 0, -1, IS_UNIQUE, IS_ORDERED);

      addEOperation(javaResourceDirectoryEClass, this.getResource(), "getResources", 0, -1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceDirectoryEClass, this.getJavaPackage(), "getPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceDirectoryEClass, this.getJavaPackage(), "getPackage", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceDirectoryEClass, this.getJavaFile(), "getJavaFile", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceDirectoryEClass, this.getJavaFile(), "getJavaFile", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceDirectoryEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(javaResourceDirectoryEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(javaResourceDirectoryEClass, this.getJavaResourcesType(), "getResourcesType", 1, 1, IS_UNIQUE,
         IS_ORDERED);

      initEClass(javaResourcesRootEClass, JavaResourcesRoot.class, "JavaResourcesRoot", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaResourcesRoot_PackageBundle(), this.getJavaResourceBundle(),
         this.getJavaResourceBundle_ResourcesRoots(), "packageBundle", null, 1, 1, JavaResourcesRoot.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getJavaResourcesRoot_ResourcesType(), this.getJavaResourcesType(), "resourcesType", "BIN", 1, 1,
         JavaResourcesRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      op = addEOperation(javaResourcesRootEClass, this.getJavaType(), "getType", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "qualifiedPackageName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "typeName", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEBoolean(), "createOnDemand", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaPackageEClass, JavaPackage.class, "JavaPackage", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(javaFileEClass, JavaFile.class, "JavaFile", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaFile_Type(), this.getJavaType(), null, "type", null, 1, 1, JavaFile.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      initEClass(javaClassEClass, JavaClass.class, "JavaClass", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getJavaClass_Major(), ecorePackage.getEInt(), "major", null, 0, 1, JavaClass.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getJavaClass_Minor(), ecorePackage.getEInt(), "minor", null, 0, 1, JavaClass.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

      initEClass(javaTypeEClass, JavaType.class, "JavaType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getJavaType_InnerTypes(), this.getJavaType(), this.getJavaType_OuterType(), "innerTypes", null, 0,
         -1, JavaType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getJavaType_OuterType(), this.getJavaType(), this.getJavaType_InnerTypes(), "outerType", null, 0,
         1, JavaType.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      addEOperation(javaTypeEClass, this.getJavaFile(), "getFile", 1, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(javaResourceEClass, JavaResource.class, "JavaResource", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      addEOperation(javaResourceEClass, this.getJavaResourcesRoot(), "getResourcesRoot", 0, 1, IS_UNIQUE, IS_ORDERED);

      addEOperation(javaResourceEClass, this.getJavaResourceDirectory(), "getParentDirectory", 0, 1, IS_UNIQUE,
         IS_ORDERED);

      addEOperation(javaResourceEClass, this.getJavaPackage(), "getParentPackage", 0, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(resourceVisitorEClass, ResourceVisitor.class, "ResourceVisitor", IS_ABSTRACT, IS_INTERFACE,
         !IS_GENERATED_INSTANCE_CLASS);

      // Initialize enums and add enum literals
      initEEnum(javaResourcesTypeEEnum, JavaResourcesType.class, "JavaResourcesType");
      addEEnumLiteral(javaResourcesTypeEEnum, JavaResourcesType.BIN);
      addEEnumLiteral(javaResourcesTypeEEnum, JavaResourcesType.SRC);

      // Create resource
      createResource(eNS_URI);
   }

} // JavaModelPackageImpl
