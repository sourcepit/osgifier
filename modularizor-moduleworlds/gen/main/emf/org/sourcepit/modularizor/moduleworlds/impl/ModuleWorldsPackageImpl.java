/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sourcepit.common.modeling.CommonModelingPackage;
import org.sourcepit.modularizor.java.JavaModelPackage;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ExplodedModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleReference;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsFactory;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ModuleWorldsPackageImpl extends EPackageImpl implements ModuleWorldsPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass moduleWorldEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass moduleRealmEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass moduleReferenceEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass abstractModuleEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass assembledModuleEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass explodedModuleEClass = null;

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
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private ModuleWorldsPackageImpl()
   {
      super(eNS_URI, ModuleWorldsFactory.eINSTANCE);
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
    * This method is used to initialize {@link ModuleWorldsPackage#eINSTANCE} when that field is accessed. Clients
    * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static ModuleWorldsPackage init()
   {
      if (isInited)
         return (ModuleWorldsPackage) EPackage.Registry.INSTANCE.getEPackage(ModuleWorldsPackage.eNS_URI);

      // Obtain or create and register package
      ModuleWorldsPackageImpl theModuleWorldsPackage = (ModuleWorldsPackageImpl) (EPackage.Registry.INSTANCE
         .get(eNS_URI) instanceof ModuleWorldsPackageImpl
         ? EPackage.Registry.INSTANCE.get(eNS_URI)
         : new ModuleWorldsPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      JavaModelPackage.eINSTANCE.eClass();

      // Create package meta-data objects
      theModuleWorldsPackage.createPackageContents();

      // Initialize created meta-data
      theModuleWorldsPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theModuleWorldsPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(ModuleWorldsPackage.eNS_URI, theModuleWorldsPackage);
      return theModuleWorldsPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getModuleWorld()
   {
      return moduleWorldEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleWorld_Modules()
   {
      return (EReference) moduleWorldEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleWorld_ModuleRealms()
   {
      return (EReference) moduleWorldEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getModuleRealm()
   {
      return moduleRealmEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleRealm_ModuleWorld()
   {
      return (EReference) moduleRealmEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleRealm_Module()
   {
      return (EReference) moduleRealmEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleRealm_ReferencedModules()
   {
      return (EReference) moduleRealmEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getModuleReference()
   {
      return moduleReferenceEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleReference_ModuleRealm()
   {
      return (EReference) moduleReferenceEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleReference_TargetModule()
   {
      return (EReference) moduleReferenceEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getModuleReference_Transitive()
   {
      return (EAttribute) moduleReferenceEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getModuleReference_Optional()
   {
      return (EAttribute) moduleReferenceEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getAbstractModule()
   {
      return abstractModuleEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getAbstractModule_ModuleWorld()
   {
      return (EReference) abstractModuleEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getAbstractModule_SourceAttachment()
   {
      return (EAttribute) abstractModuleEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getAssembledModule()
   {
      return assembledModuleEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getAssembledModule_JavaResources()
   {
      return (EReference) assembledModuleEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getAssembledModule_File()
   {
      return (EAttribute) assembledModuleEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getExplodedModule()
   {
      return explodedModuleEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getExplodedModule_Directory()
   {
      return (EAttribute) explodedModuleEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getExplodedModule_SourceDirectories()
   {
      return (EAttribute) explodedModuleEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getExplodedModule_BinaryDirectories()
   {
      return (EAttribute) explodedModuleEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getExplodedModule_JavaResources()
   {
      return (EReference) explodedModuleEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ModuleWorldsFactory getModuleWorldsFactory()
   {
      return (ModuleWorldsFactory) getEFactoryInstance();
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
      moduleWorldEClass = createEClass(MODULE_WORLD);
      createEReference(moduleWorldEClass, MODULE_WORLD__MODULES);
      createEReference(moduleWorldEClass, MODULE_WORLD__MODULE_REALMS);

      moduleRealmEClass = createEClass(MODULE_REALM);
      createEReference(moduleRealmEClass, MODULE_REALM__MODULE_WORLD);
      createEReference(moduleRealmEClass, MODULE_REALM__MODULE);
      createEReference(moduleRealmEClass, MODULE_REALM__REFERENCED_MODULES);

      moduleReferenceEClass = createEClass(MODULE_REFERENCE);
      createEReference(moduleReferenceEClass, MODULE_REFERENCE__MODULE_REALM);
      createEReference(moduleReferenceEClass, MODULE_REFERENCE__TARGET_MODULE);
      createEAttribute(moduleReferenceEClass, MODULE_REFERENCE__TRANSITIVE);
      createEAttribute(moduleReferenceEClass, MODULE_REFERENCE__OPTIONAL);

      abstractModuleEClass = createEClass(ABSTRACT_MODULE);
      createEReference(abstractModuleEClass, ABSTRACT_MODULE__MODULE_WORLD);
      createEAttribute(abstractModuleEClass, ABSTRACT_MODULE__SOURCE_ATTACHMENT);

      assembledModuleEClass = createEClass(ASSEMBLED_MODULE);
      createEReference(assembledModuleEClass, ASSEMBLED_MODULE__JAVA_RESOURCES);
      createEAttribute(assembledModuleEClass, ASSEMBLED_MODULE__FILE);

      explodedModuleEClass = createEClass(EXPLODED_MODULE);
      createEAttribute(explodedModuleEClass, EXPLODED_MODULE__DIRECTORY);
      createEAttribute(explodedModuleEClass, EXPLODED_MODULE__SOURCE_DIRECTORIES);
      createEAttribute(explodedModuleEClass, EXPLODED_MODULE__BINARY_DIRECTORIES);
      createEReference(explodedModuleEClass, EXPLODED_MODULE__JAVA_RESOURCES);
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
      CommonModelingPackage theCommonModelingPackage = (CommonModelingPackage) EPackage.Registry.INSTANCE
         .getEPackage(CommonModelingPackage.eNS_URI);
      JavaModelPackage theJavaModelPackage = (JavaModelPackage) EPackage.Registry.INSTANCE
         .getEPackage(JavaModelPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      moduleWorldEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      moduleRealmEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      moduleReferenceEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      abstractModuleEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      assembledModuleEClass.getESuperTypes().add(this.getAbstractModule());
      explodedModuleEClass.getESuperTypes().add(this.getAbstractModule());

      // Initialize classes and features; add operations and parameters
      initEClass(moduleWorldEClass, ModuleWorld.class, "ModuleWorld", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getModuleWorld_Modules(), this.getAbstractModule(), this.getAbstractModule_ModuleWorld(),
         "modules", null, 0, -1, ModuleWorld.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleWorld_ModuleRealms(), this.getModuleRealm(), this.getModuleRealm_ModuleWorld(),
         "moduleRealms", null, 0, -1, ModuleWorld.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(moduleRealmEClass, ModuleRealm.class, "ModuleRealm", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getModuleRealm_ModuleWorld(), this.getModuleWorld(), this.getModuleWorld_ModuleRealms(),
         "moduleWorld", null, 1, 1, ModuleRealm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleRealm_Module(), this.getAbstractModule(), null, "module", null, 1, 1, ModuleRealm.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleRealm_ReferencedModules(), this.getModuleReference(),
         this.getModuleReference_ModuleRealm(), "referencedModules", null, 0, -1, ModuleRealm.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      initEClass(moduleReferenceEClass, ModuleReference.class, "ModuleReference", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getModuleReference_ModuleRealm(), this.getModuleRealm(), this.getModuleRealm_ReferencedModules(),
         "moduleRealm", null, 1, 1, ModuleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleReference_TargetModule(), this.getAbstractModule(), null, "targetModule", null, 1, 1,
         ModuleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getModuleReference_Transitive(), ecorePackage.getEBoolean(), "transitive", null, 0, 1,
         ModuleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getModuleReference_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1,
         ModuleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(abstractModuleEClass, AbstractModule.class, "AbstractModule", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getAbstractModule_ModuleWorld(), this.getModuleWorld(), this.getModuleWorld_Modules(),
         "moduleWorld", null, 1, 1, AbstractModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getAbstractModule_SourceAttachment(), theCommonModelingPackage.getEFile(), "sourceAttachment",
         null, 0, 1, AbstractModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      addEOperation(abstractModuleEClass, theJavaModelPackage.getJavaResourceBundle(), "getJavaResources", 1, 1,
         IS_UNIQUE, IS_ORDERED);

      initEClass(assembledModuleEClass, AssembledModule.class, "AssembledModule", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getAssembledModule_JavaResources(), theJavaModelPackage.getJavaArchive(), null, "javaResources",
         null, 1, 1, AssembledModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getAssembledModule_File(), theCommonModelingPackage.getEFile(), "file", null, 1, 1,
         AssembledModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(explodedModuleEClass, ExplodedModule.class, "ExplodedModule", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getExplodedModule_Directory(), theCommonModelingPackage.getEFile(), "directory", null, 1, 1,
         ExplodedModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExplodedModule_SourceDirectories(), theCommonModelingPackage.getEFile(), "sourceDirectories",
         null, 0, -1, ExplodedModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getExplodedModule_BinaryDirectories(), theCommonModelingPackage.getEFile(), "binaryDirectories",
         null, 0, -1, ExplodedModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getExplodedModule_JavaResources(), theJavaModelPackage.getJavaProject(), null, "javaResources",
         null, 1, 1, ExplodedModule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} // ModuleWorldsPackageImpl
