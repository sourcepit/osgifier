/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.sourcepit.common.manifest.ManifestPackage;
import org.sourcepit.common.manifest.osgi.BundleManifestPackage;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgify.bundletree.AbstractBundleCoordinate;
import org.sourcepit.osgify.bundletree.Bundle;
import org.sourcepit.osgify.bundletree.BundleReference;
import org.sourcepit.osgify.bundletree.BundleTreeModelFactory;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.bundletree.OSGiFyContext;
import org.sourcepit.osgify.java.JavaModelPackage;
import org.sourcepit.osgify.java.impl.JavaModelPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class BundleTreeModelPackageImpl extends EPackageImpl implements BundleTreeModelPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass osGiFyContextEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass abstractBundleCoordinateEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass bundleEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass bundleReferenceEClass = null;

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
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private BundleTreeModelPackageImpl()
   {
      super(eNS_URI, BundleTreeModelFactory.eINSTANCE);
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
    * This method is used to initialize {@link BundleTreeModelPackage#eINSTANCE} when that field is accessed. Clients
    * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
    * begin-user-doc --> <!-- end-user-doc -->
    * 
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static BundleTreeModelPackage init()
   {
      if (isInited)
         return (BundleTreeModelPackage) EPackage.Registry.INSTANCE.getEPackage(BundleTreeModelPackage.eNS_URI);

      // Obtain or create and register package
      BundleTreeModelPackageImpl theBundleTreeModelPackage = (BundleTreeModelPackageImpl) (EPackage.Registry.INSTANCE
         .get(eNS_URI) instanceof BundleTreeModelPackageImpl
         ? EPackage.Registry.INSTANCE.get(eNS_URI)
         : new BundleTreeModelPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      CommonModelPackage.eINSTANCE.eClass();
      ManifestPackage.eINSTANCE.eClass();

      // Obtain or create and register interdependencies
      JavaModelPackageImpl theJavaModelPackage = (JavaModelPackageImpl) (EPackage.Registry.INSTANCE
         .getEPackage(JavaModelPackage.eNS_URI) instanceof JavaModelPackageImpl ? EPackage.Registry.INSTANCE
         .getEPackage(JavaModelPackage.eNS_URI) : JavaModelPackage.eINSTANCE);

      // Create package meta-data objects
      theBundleTreeModelPackage.createPackageContents();
      theJavaModelPackage.createPackageContents();

      // Initialize created meta-data
      theBundleTreeModelPackage.initializePackageContents();
      theJavaModelPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theBundleTreeModelPackage.freeze();


      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(BundleTreeModelPackage.eNS_URI, theBundleTreeModelPackage);
      return theBundleTreeModelPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getOSGiFyContext()
   {
      return osGiFyContextEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getOSGiFyContext_Bundles()
   {
      return (EReference) osGiFyContextEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getAbstractBundleCoordinate()
   {
      return abstractBundleCoordinateEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getAbstractBundleCoordinate_Version()
   {
      return (EAttribute) abstractBundleCoordinateEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getAbstractBundleCoordinate_SymbolicName()
   {
      return (EAttribute) abstractBundleCoordinateEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getBundle()
   {
      return bundleEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundle_Content()
   {
      return (EReference) bundleEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundle_Dependencies()
   {
      return (EReference) bundleEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getBundleReference()
   {
      return bundleReferenceEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleReference_VersionRange()
   {
      return (EAttribute) bundleReferenceEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleReference_Optional()
   {
      return (EAttribute) bundleReferenceEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleReference_Target()
   {
      return (EReference) bundleReferenceEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleReference_Provided()
   {
      return (EAttribute) bundleReferenceEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleTreeModelFactory getBundleTreeModelFactory()
   {
      return (BundleTreeModelFactory) getEFactoryInstance();
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
      osGiFyContextEClass = createEClass(OS_GI_FY_CONTEXT);
      createEReference(osGiFyContextEClass, OS_GI_FY_CONTEXT__BUNDLES);

      abstractBundleCoordinateEClass = createEClass(ABSTRACT_BUNDLE_COORDINATE);
      createEAttribute(abstractBundleCoordinateEClass, ABSTRACT_BUNDLE_COORDINATE__VERSION);
      createEAttribute(abstractBundleCoordinateEClass, ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME);

      bundleEClass = createEClass(BUNDLE);
      createEReference(bundleEClass, BUNDLE__CONTENT);
      createEReference(bundleEClass, BUNDLE__DEPENDENCIES);

      bundleReferenceEClass = createEClass(BUNDLE_REFERENCE);
      createEAttribute(bundleReferenceEClass, BUNDLE_REFERENCE__VERSION_RANGE);
      createEAttribute(bundleReferenceEClass, BUNDLE_REFERENCE__OPTIONAL);
      createEReference(bundleReferenceEClass, BUNDLE_REFERENCE__TARGET);
      createEAttribute(bundleReferenceEClass, BUNDLE_REFERENCE__PROVIDED);
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
      BundleManifestPackage theBundleManifestPackage = (BundleManifestPackage) EPackage.Registry.INSTANCE
         .getEPackage(BundleManifestPackage.eNS_URI);
      JavaModelPackage theJavaModelPackage = (JavaModelPackage) EPackage.Registry.INSTANCE
         .getEPackage(JavaModelPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      bundleEClass.getESuperTypes().add(this.getAbstractBundleCoordinate());
      bundleReferenceEClass.getESuperTypes().add(this.getAbstractBundleCoordinate());

      // Initialize classes and features; add operations and parameters
      initEClass(osGiFyContextEClass, OSGiFyContext.class, "OSGiFyContext", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getOSGiFyContext_Bundles(), this.getBundle(), null, "bundles", null, 0, -1, OSGiFyContext.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(abstractBundleCoordinateEClass, AbstractBundleCoordinate.class, "AbstractBundleCoordinate",
         IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getAbstractBundleCoordinate_Version(), theBundleManifestPackage.getVersion(), "version", null, 0,
         1, AbstractBundleCoordinate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getAbstractBundleCoordinate_SymbolicName(), ecorePackage.getEString(), "symbolicName", null, 0, 1,
         AbstractBundleCoordinate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(bundleEClass, Bundle.class, "Bundle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBundle_Content(), theJavaModelPackage.getJavaPackageBundle(), null, "content", null, 1, 1,
         Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getBundle_Dependencies(), this.getBundleReference(), null, "dependencies", null, 0, -1,
         Bundle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(bundleReferenceEClass, BundleReference.class, "BundleReference", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getBundleReference_VersionRange(), theBundleManifestPackage.getVersionRange(), "versionRange",
         null, 0, 1, BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
         IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleReference_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1,
         BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleReference_Target(), this.getBundle(), null, "target", null, 0, 1, BundleReference.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleReference_Provided(), ecorePackage.getEBoolean(), "provided", null, 0, 1,
         BundleReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} // BundleTreeModelPackageImpl
