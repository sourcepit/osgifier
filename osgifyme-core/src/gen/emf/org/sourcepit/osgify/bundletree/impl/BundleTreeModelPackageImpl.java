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
import org.sourcepit.osgify.bundletree.AbstractBundleNode;
import org.sourcepit.osgify.bundletree.BundleNode;
import org.sourcepit.osgify.bundletree.BundleTree;
import org.sourcepit.osgify.bundletree.BundleTreeModelFactory;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.bundletree.RootBundleNode;
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
   private EClass bundleTreeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass abstractBundleNodeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass rootBundleNodeEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EClass bundleNodeEClass = null;

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
   public EClass getBundleTree()
   {
      return bundleTreeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleTree_Nodes()
   {
      return (EReference) bundleTreeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getBundleTree_Bundles()
   {
      return (EReference) bundleTreeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getAbstractBundleNode()
   {
      return abstractBundleNodeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getAbstractBundleNode_Nodes()
   {
      return (EReference) abstractBundleNodeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EReference getAbstractBundleNode_Target()
   {
      return (EReference) abstractBundleNodeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getAbstractBundleNode_Version()
   {
      return (EAttribute) abstractBundleNodeEClass.getEStructuralFeatures().get(2);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getAbstractBundleNode_SymbolicName()
   {
      return (EAttribute) abstractBundleNodeEClass.getEStructuralFeatures().get(3);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getAbstractBundleNode_Scope()
   {
      return (EAttribute) abstractBundleNodeEClass.getEStructuralFeatures().get(4);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getRootBundleNode()
   {
      return rootBundleNodeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EClass getBundleNode()
   {
      return bundleNodeEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleNode_VersionRange()
   {
      return (EAttribute) bundleNodeEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleNode_Enabled()
   {
      return (EAttribute) bundleNodeEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EAttribute getBundleNode_Optional()
   {
      return (EAttribute) bundleNodeEClass.getEStructuralFeatures().get(2);
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
      bundleTreeEClass = createEClass(BUNDLE_TREE);
      createEReference(bundleTreeEClass, BUNDLE_TREE__NODES);
      createEReference(bundleTreeEClass, BUNDLE_TREE__BUNDLES);

      abstractBundleNodeEClass = createEClass(ABSTRACT_BUNDLE_NODE);
      createEReference(abstractBundleNodeEClass, ABSTRACT_BUNDLE_NODE__NODES);
      createEReference(abstractBundleNodeEClass, ABSTRACT_BUNDLE_NODE__TARGET);
      createEAttribute(abstractBundleNodeEClass, ABSTRACT_BUNDLE_NODE__VERSION);
      createEAttribute(abstractBundleNodeEClass, ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME);
      createEAttribute(abstractBundleNodeEClass, ABSTRACT_BUNDLE_NODE__SCOPE);

      rootBundleNodeEClass = createEClass(ROOT_BUNDLE_NODE);

      bundleNodeEClass = createEClass(BUNDLE_NODE);
      createEAttribute(bundleNodeEClass, BUNDLE_NODE__VERSION_RANGE);
      createEAttribute(bundleNodeEClass, BUNDLE_NODE__ENABLED);
      createEAttribute(bundleNodeEClass, BUNDLE_NODE__OPTIONAL);
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
      JavaModelPackage theJavaModelPackage = (JavaModelPackage) EPackage.Registry.INSTANCE
         .getEPackage(JavaModelPackage.eNS_URI);
      BundleManifestPackage theBundleManifestPackage = (BundleManifestPackage) EPackage.Registry.INSTANCE
         .getEPackage(BundleManifestPackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      rootBundleNodeEClass.getESuperTypes().add(this.getAbstractBundleNode());
      bundleNodeEClass.getESuperTypes().add(this.getAbstractBundleNode());

      // Initialize classes and features; add operations and parameters
      initEClass(bundleTreeEClass, BundleTree.class, "BundleTree", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getBundleTree_Nodes(), this.getRootBundleNode(), null, "nodes", null, 0, -1, BundleTree.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEReference(getBundleTree_Bundles(), theJavaModelPackage.getJavaPackageBundle(), null, "bundles", null, 0, -1,
         BundleTree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(abstractBundleNodeEClass, AbstractBundleNode.class, "AbstractBundleNode", IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEReference(getAbstractBundleNode_Nodes(), this.getBundleNode(), null, "nodes", null, 0, -1,
         AbstractBundleNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getAbstractBundleNode_Target(), theJavaModelPackage.getJavaPackageBundle(), null, "target", null,
         0, 1, AbstractBundleNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
         !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getAbstractBundleNode_Version(), theBundleManifestPackage.getVersion(), "version", null, 0, 1,
         AbstractBundleNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getAbstractBundleNode_SymbolicName(), ecorePackage.getEString(), "symbolicName", null, 0, 1,
         AbstractBundleNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getAbstractBundleNode_Scope(), ecorePackage.getEString(), "scope", null, 0, 1,
         AbstractBundleNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);

      initEClass(rootBundleNodeEClass, RootBundleNode.class, "RootBundleNode", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);

      initEClass(bundleNodeEClass, BundleNode.class, "BundleNode", !IS_ABSTRACT, !IS_INTERFACE,
         IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getBundleNode_VersionRange(), theBundleManifestPackage.getVersionRange(), "versionRange", null, 0,
         1, BundleNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
         !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleNode_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1, BundleNode.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getBundleNode_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1, BundleNode.class,
         !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} // BundleTreeModelPackageImpl
