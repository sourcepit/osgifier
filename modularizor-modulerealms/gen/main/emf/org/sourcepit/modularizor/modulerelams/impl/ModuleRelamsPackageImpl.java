/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sourcepit.common.modeling.CommonModelingPackage;
import org.sourcepit.modularizor.java.JavaModelPackage;
import org.sourcepit.modularizor.modulerelams.ModuleRealm;
import org.sourcepit.modularizor.modulerelams.ModuleRealms;
import org.sourcepit.modularizor.modulerelams.ModuleReference;
import org.sourcepit.modularizor.modulerelams.ModuleRelamsFactory;
import org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ModuleRelamsPackageImpl extends EPackageImpl implements ModuleRelamsPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass moduleRealmsEClass = null;

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
    * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
    * EPackage.Registry} by the package
    * package URI value.
    * <p>
    * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
    * performs initialization of the package, or returns the registered package, if one already exists. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private ModuleRelamsPackageImpl()
   {
      super(eNS_URI, ModuleRelamsFactory.eINSTANCE);
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
    * This method is used to initialize {@link ModuleRelamsPackage#eINSTANCE} when that field is accessed. Clients
    * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static ModuleRelamsPackage init()
   {
      if (isInited)
         return (ModuleRelamsPackage) EPackage.Registry.INSTANCE.getEPackage(ModuleRelamsPackage.eNS_URI);

      // Obtain or create and register package
      ModuleRelamsPackageImpl theModuleRelamsPackage = (ModuleRelamsPackageImpl) (EPackage.Registry.INSTANCE
         .get(eNS_URI) instanceof ModuleRelamsPackageImpl
         ? EPackage.Registry.INSTANCE.get(eNS_URI)
         : new ModuleRelamsPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      JavaModelPackage.eINSTANCE.eClass();

      // Create package meta-data objects
      theModuleRelamsPackage.createPackageContents();

      // Initialize created meta-data
      theModuleRelamsPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theModuleRelamsPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(ModuleRelamsPackage.eNS_URI, theModuleRelamsPackage);
      return theModuleRelamsPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getModuleRealms()
   {
      return moduleRealmsEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleRealms_Modules()
   {
      return (EReference) moduleRealmsEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleRealms_ModuleRealms()
   {
      return (EReference) moduleRealmsEClass.getEStructuralFeatures().get(1);
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
   public EReference getModuleRealm_ModuleRealms()
   {
      return (EReference) moduleRealmEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleRealm_RealmModule()
   {
      return (EReference) moduleRealmEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getModuleRealm_ModuleReferences()
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
   public ModuleRelamsFactory getModuleRelamsFactory()
   {
      return (ModuleRelamsFactory) getEFactoryInstance();
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
      moduleRealmsEClass = createEClass(MODULE_REALMS);
      createEReference(moduleRealmsEClass, MODULE_REALMS__MODULES);
      createEReference(moduleRealmsEClass, MODULE_REALMS__MODULE_REALMS);

      moduleRealmEClass = createEClass(MODULE_REALM);
      createEReference(moduleRealmEClass, MODULE_REALM__MODULE_REALMS);
      createEReference(moduleRealmEClass, MODULE_REALM__REALM_MODULE);
      createEReference(moduleRealmEClass, MODULE_REALM__MODULE_REFERENCES);

      moduleReferenceEClass = createEClass(MODULE_REFERENCE);
      createEReference(moduleReferenceEClass, MODULE_REFERENCE__MODULE_REALM);
      createEReference(moduleReferenceEClass, MODULE_REFERENCE__TARGET_MODULE);
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
      moduleRealmsEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      moduleRealmEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      moduleReferenceEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());

      // Initialize classes and features; add operations and parameters
      initEClass(moduleRealmsEClass, ModuleRealms.class, "ModuleRealms", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getModuleRealms_Modules(), theJavaModelPackage.getJavaResourceBundle(), null, "modules", null, 0,
         -1, ModuleRealms.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleRealms_ModuleRealms(), this.getModuleRealm(), this.getModuleRealm_ModuleRealms(),
         "moduleRealms", null, 0, -1, ModuleRealms.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(moduleRealmEClass, ModuleRealm.class, "ModuleRealm", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getModuleRealm_ModuleRealms(), this.getModuleRealms(), this.getModuleRealms_ModuleRealms(),
         "moduleRealms", null, 1, 1, ModuleRealm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleRealm_RealmModule(), theJavaModelPackage.getJavaResourceBundle(), null, "realmModule",
         null, 1, 1, ModuleRealm.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleRealm_ModuleReferences(), this.getModuleReference(),
         this.getModuleReference_ModuleRealm(), "moduleReferences", null, 0, -1, ModuleRealm.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      initEClass(moduleReferenceEClass, ModuleReference.class, "ModuleReference", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getModuleReference_ModuleRealm(), this.getModuleRealm(), this.getModuleRealm_ModuleReferences(),
         "moduleRealm", null, 1, 1, ModuleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getModuleReference_TargetModule(), theJavaModelPackage.getJavaResourceBundle(), null,
         "targetModule", null, 1, 1, ModuleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
         IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} // ModuleRelamsPackageImpl
