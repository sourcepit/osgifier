/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.model.context.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sourcepit.common.manifest.ManifestPackage;
import org.sourcepit.common.manifest.osgi.BundleManifestPackage;
import org.sourcepit.common.modeling.CommonModelingPackage;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleLocalization;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.ContextModelPackage;
import org.sourcepit.osgifier.core.model.context.EmbedInstruction;
import org.sourcepit.osgifier.core.model.context.LocalizedData;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.model.java.JavaModelPackage;
import org.sourcepit.osgifier.core.model.java.impl.JavaModelPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContextModelPackageImpl extends EPackageImpl implements ContextModelPackage {
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass osgifierContextEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass bundleCandidateEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass bundleReferenceEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass bundleLocalizationEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass localizedDataEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EEnum embedInstructionEEnum = null;

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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private ContextModelPackageImpl() {
      super(eNS_URI, ContextModelFactory.eINSTANCE);
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
    * This method is used to initialize {@link ContextModelPackage#eINSTANCE} when that field is accessed. Clients
    * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static ContextModelPackage init() {
      if (isInited)
         return (ContextModelPackage) EPackage.Registry.INSTANCE.getEPackage(ContextModelPackage.eNS_URI);

      // Obtain or create and register package
      ContextModelPackageImpl theContextModelPackage = (ContextModelPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ContextModelPackageImpl
         ? EPackage.Registry.INSTANCE.get(eNS_URI)
         : new ContextModelPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      CommonModelingPackage.eINSTANCE.eClass();
      ManifestPackage.eINSTANCE.eClass();

      // Obtain or create and register interdependencies
      JavaModelPackageImpl theJavaModelPackage = (JavaModelPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(JavaModelPackage.eNS_URI) instanceof JavaModelPackageImpl
         ? EPackage.Registry.INSTANCE.getEPackage(JavaModelPackage.eNS_URI)
         : JavaModelPackage.eINSTANCE);

      // Create package meta-data objects
      theContextModelPackage.createPackageContents();
      theJavaModelPackage.createPackageContents();

      // Initialize created meta-data
      theContextModelPackage.initializePackageContents();
      theJavaModelPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theContextModelPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(ContextModelPackage.eNS_URI, theContextModelPackage);
      return theContextModelPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getOsgifierContext() {
      return osgifierContextEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getOsgifierContext_Bundles() {
      return (EReference) osgifierContextEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getBundleCandidate() {
      return bundleCandidateEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleCandidate_Location() {
      return (EAttribute) bundleCandidateEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleCandidate_Content() {
      return (EReference) bundleCandidateEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleCandidate_Dependencies() {
      return (EReference) bundleCandidateEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleCandidate_Version() {
      return (EAttribute) bundleCandidateEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleCandidate_SymbolicName() {
      return (EAttribute) bundleCandidateEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleCandidate_NativeBundle() {
      return (EAttribute) bundleCandidateEClass.getEStructuralFeatures().get(5);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleCandidate_Manifest() {
      return (EReference) bundleCandidateEClass.getEStructuralFeatures().get(6);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleCandidate_Localization() {
      return (EReference) bundleCandidateEClass.getEStructuralFeatures().get(7);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleCandidate_SourceBundle() {
      return (EReference) bundleCandidateEClass.getEStructuralFeatures().get(8);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleCandidate_TargetBundle() {
      return (EReference) bundleCandidateEClass.getEStructuralFeatures().get(9);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getBundleReference() {
      return bundleReferenceEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleReference_VersionRange() {
      return (EAttribute) bundleReferenceEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleReference_Optional() {
      return (EAttribute) bundleReferenceEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleReference_Target() {
      return (EReference) bundleReferenceEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleReference_Provided() {
      return (EAttribute) bundleReferenceEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleReference_EmbedInstruction() {
      return (EAttribute) bundleReferenceEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getBundleLocalization() {
      return bundleLocalizationEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleLocalization_Data() {
      return (EReference) bundleLocalizationEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getLocalizedData() {
      return localizedDataEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getLocalizedData_Locale() {
      return (EAttribute) localizedDataEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getLocalizedData_Data() {
      return (EReference) localizedDataEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EEnum getEmbedInstruction() {
      return embedInstructionEEnum;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ContextModelFactory getContextModelFactory() {
      return (ContextModelFactory) getEFactoryInstance();
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
   public void createPackageContents() {
      if (isCreated)
         return;
      isCreated = true;

      // Create classes and their features
      osgifierContextEClass = createEClass(OSGIFIER_CONTEXT);
      createEReference(osgifierContextEClass, OSGIFIER_CONTEXT__BUNDLES);

      bundleCandidateEClass = createEClass(BUNDLE_CANDIDATE);
      createEAttribute(bundleCandidateEClass, BUNDLE_CANDIDATE__LOCATION);
      createEReference(bundleCandidateEClass, BUNDLE_CANDIDATE__CONTENT);
      createEReference(bundleCandidateEClass, BUNDLE_CANDIDATE__DEPENDENCIES);
      createEAttribute(bundleCandidateEClass, BUNDLE_CANDIDATE__VERSION);
      createEAttribute(bundleCandidateEClass, BUNDLE_CANDIDATE__SYMBOLIC_NAME);
      createEAttribute(bundleCandidateEClass, BUNDLE_CANDIDATE__NATIVE_BUNDLE);
      createEReference(bundleCandidateEClass, BUNDLE_CANDIDATE__MANIFEST);
      createEReference(bundleCandidateEClass, BUNDLE_CANDIDATE__LOCALIZATION);
      createEReference(bundleCandidateEClass, BUNDLE_CANDIDATE__SOURCE_BUNDLE);
      createEReference(bundleCandidateEClass, BUNDLE_CANDIDATE__TARGET_BUNDLE);

      bundleReferenceEClass = createEClass(BUNDLE_REFERENCE);
      createEAttribute(bundleReferenceEClass, BUNDLE_REFERENCE__VERSION_RANGE);
      createEAttribute(bundleReferenceEClass, BUNDLE_REFERENCE__OPTIONAL);
      createEReference(bundleReferenceEClass, BUNDLE_REFERENCE__TARGET);
      createEAttribute(bundleReferenceEClass, BUNDLE_REFERENCE__PROVIDED);
      createEAttribute(bundleReferenceEClass, BUNDLE_REFERENCE__EMBED_INSTRUCTION);

      bundleLocalizationEClass = createEClass(BUNDLE_LOCALIZATION);
      createEReference(bundleLocalizationEClass, BUNDLE_LOCALIZATION__DATA);

      localizedDataEClass = createEClass(LOCALIZED_DATA);
      createEAttribute(localizedDataEClass, LOCALIZED_DATA__LOCALE);
      createEReference(localizedDataEClass, LOCALIZED_DATA__DATA);

      // Create enums
      embedInstructionEEnum = createEEnum(EMBED_INSTRUCTION);
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
   public void initializePackageContents() {
      if (isInitialized)
         return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Obtain other dependent packages
      CommonModelingPackage theCommonModelingPackage = (CommonModelingPackage) EPackage.Registry.INSTANCE.getEPackage(CommonModelingPackage.eNS_URI);
      JavaModelPackage theJavaModelPackage = (JavaModelPackage) EPackage.Registry.INSTANCE.getEPackage(JavaModelPackage.eNS_URI);
      BundleManifestPackage theBundleManifestPackage = (BundleManifestPackage) EPackage.Registry.INSTANCE.getEPackage(BundleManifestPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      osgifierContextEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      bundleCandidateEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());
      bundleReferenceEClass.getESuperTypes().add(theCommonModelingPackage.getXAnnotatable());

      // Initialize classes and features; add operations and parameters
      initEClass(osgifierContextEClass, OsgifierContext.class, "OsgifierContext", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getOsgifierContext_Bundles(), this.getBundleCandidate(), null, "bundles", null, 0, -1,
         OsgifierContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(bundleCandidateEClass, BundleCandidate.class, "BundleCandidate", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getBundleCandidate_Location(), theCommonModelingPackage.getEFile(), "location", null, 1, 1,
         BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleCandidate_Content(), theJavaModelPackage.getJavaResourceBundle(), null, "content", null,
         1, 1, BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleCandidate_Dependencies(), this.getBundleReference(), null, "dependencies", null, 0, -1,
         BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleCandidate_Version(), theBundleManifestPackage.getVersion(), "version", null, 0, 1,
         BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleCandidate_SymbolicName(), ecorePackage.getEString(), "symbolicName", null, 0, 1,
         BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleCandidate_NativeBundle(), ecorePackage.getEBoolean(), "nativeBundle", null, 0, 1,
         BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleCandidate_Manifest(), theBundleManifestPackage.getBundleManifest(), null, "manifest",
         null, 0, 1, BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
         !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleCandidate_Localization(), this.getBundleLocalization(), null, "localization", null, 0, 1,
         BundleCandidate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleCandidate_SourceBundle(), this.getBundleCandidate(),
         this.getBundleCandidate_TargetBundle(), "sourceBundle", null, 0, 1, BundleCandidate.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);
      initEReference(getBundleCandidate_TargetBundle(), this.getBundleCandidate(),
         this.getBundleCandidate_SourceBundle(), "targetBundle", null, 0, 1, BundleCandidate.class, !IS_TRANSIENT,
         !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
         IS_ORDERED);

      initEClass(bundleReferenceEClass, BundleReference.class, "BundleReference", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getBundleReference_VersionRange(), theBundleManifestPackage.getVersionRange(), "versionRange",
         null, 0, 1, BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleReference_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1,
         BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleReference_Target(), this.getBundleCandidate(), null, "target", null, 0, 1,
         BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleReference_Provided(), ecorePackage.getEBoolean(), "provided", null, 0, 1,
         BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleReference_EmbedInstruction(), this.getEmbedInstruction(), "embedInstruction", null, 1, 1,
         BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(bundleLocalizationEClass, BundleLocalization.class, "BundleLocalization", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBundleLocalization_Data(), this.getLocalizedData(), null, "data", null, 0, -1,
         BundleLocalization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      EOperation op = addEOperation(bundleLocalizationEClass, null, "set", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "locale", 1, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "key", 1, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

      op = addEOperation(bundleLocalizationEClass, ecorePackage.getEString(), "get", 0, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "locale", 1, 1, IS_UNIQUE, IS_ORDERED);
      addEParameter(op, ecorePackage.getEString(), "key", 1, 1, IS_UNIQUE, IS_ORDERED);

      initEClass(localizedDataEClass, LocalizedData.class, "LocalizedData", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getLocalizedData_Locale(), ecorePackage.getEString(), "locale", "\"\"", 1, 1, LocalizedData.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getLocalizedData_Data(), theCommonModelingPackage.getEStringMapEntry(), null, "data", null, 0, -1,
         LocalizedData.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Initialize enums and add enum literals
      initEEnum(embedInstructionEEnum, EmbedInstruction.class, "EmbedInstruction");
      addEEnumLiteral(embedInstructionEEnum, EmbedInstruction.NOT);
      addEEnumLiteral(embedInstructionEEnum, EmbedInstruction.UNPACKED);
      addEEnumLiteral(embedInstructionEEnum, EmbedInstruction.PACKED);

      // Create resource
      createResource(eNS_URI);
   }

} // ContextModelPackageImpl
