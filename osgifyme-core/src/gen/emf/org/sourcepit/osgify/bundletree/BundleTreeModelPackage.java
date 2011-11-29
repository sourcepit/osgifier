/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgify.bundletree.BundleTreeModelFactory
 * @model kind="package"
 * @generated
 */
public interface BundleTreeModelPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNAME = "bundletree";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_URI = "http://org.sourcepit.osgifyme/bundle-tee/1.0";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_PREFIX = "bundle-tee";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   BundleTreeModelPackage eINSTANCE = org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.BundleTreeImpl <em>Bundle Tree</em>}'
    * class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundleTree()
    * @generated
    */
   int BUNDLE_TREE = 0;

   /**
    * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_TREE__NODES = 0;

   /**
    * The feature id for the '<em><b>Bundles</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_TREE__BUNDLES = 1;

   /**
    * The number of structural features of the '<em>Bundle Tree</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_TREE_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl
    * <em>Abstract Bundle Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getAbstractBundleNode()
    * @generated
    */
   int ABSTRACT_BUNDLE_NODE = 1;

   /**
    * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_NODE__NODES = 0;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_NODE__TARGET = 1;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_NODE__VERSION = 2;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME = 3;

   /**
    * The feature id for the '<em><b>Scope</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_NODE__SCOPE = 4;

   /**
    * The number of structural features of the '<em>Abstract Bundle Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_NODE_FEATURE_COUNT = 5;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.RootBundleNodeImpl
    * <em>Root Bundle Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.RootBundleNodeImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getRootBundleNode()
    * @generated
    */
   int ROOT_BUNDLE_NODE = 2;

   /**
    * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ROOT_BUNDLE_NODE__NODES = ABSTRACT_BUNDLE_NODE__NODES;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ROOT_BUNDLE_NODE__TARGET = ABSTRACT_BUNDLE_NODE__TARGET;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ROOT_BUNDLE_NODE__VERSION = ABSTRACT_BUNDLE_NODE__VERSION;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ROOT_BUNDLE_NODE__SYMBOLIC_NAME = ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME;

   /**
    * The feature id for the '<em><b>Scope</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ROOT_BUNDLE_NODE__SCOPE = ABSTRACT_BUNDLE_NODE__SCOPE;

   /**
    * The number of structural features of the '<em>Root Bundle Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ROOT_BUNDLE_NODE_FEATURE_COUNT = ABSTRACT_BUNDLE_NODE_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.BundleNodeImpl <em>Bundle Node</em>}'
    * class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.BundleNodeImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundleNode()
    * @generated
    */
   int BUNDLE_NODE = 3;

   /**
    * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__NODES = ABSTRACT_BUNDLE_NODE__NODES;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__TARGET = ABSTRACT_BUNDLE_NODE__TARGET;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__VERSION = ABSTRACT_BUNDLE_NODE__VERSION;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__SYMBOLIC_NAME = ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME;

   /**
    * The feature id for the '<em><b>Scope</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__SCOPE = ABSTRACT_BUNDLE_NODE__SCOPE;

   /**
    * The feature id for the '<em><b>Version Range</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__VERSION_RANGE = ABSTRACT_BUNDLE_NODE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Enabled</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__ENABLED = ABSTRACT_BUNDLE_NODE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__OPTIONAL = ABSTRACT_BUNDLE_NODE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>Bundle Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE_FEATURE_COUNT = ABSTRACT_BUNDLE_NODE_FEATURE_COUNT + 3;


   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.BundleTree <em>Bundle Tree</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Tree</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleTree
    * @generated
    */
   EClass getBundleTree();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.bundletree.BundleTree#getNodes <em>Nodes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Nodes</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleTree#getNodes()
    * @see #getBundleTree()
    * @generated
    */
   EReference getBundleTree_Nodes();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.bundletree.BundleTree#getBundles <em>Bundles</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Bundles</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleTree#getBundles()
    * @see #getBundleTree()
    * @generated
    */
   EReference getBundleTree_Bundles();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode
    * <em>Abstract Bundle Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Abstract Bundle Node</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleNode
    * @generated
    */
   EClass getAbstractBundleNode();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getNodes <em>Nodes</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Nodes</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleNode#getNodes()
    * @see #getAbstractBundleNode()
    * @generated
    */
   EReference getAbstractBundleNode_Nodes();

   /**
    * Returns the meta object for the reference '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getTarget
    * <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleNode#getTarget()
    * @see #getAbstractBundleNode()
    * @generated
    */
   EReference getAbstractBundleNode_Target();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getVersion
    * <em>Version</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleNode#getVersion()
    * @see #getAbstractBundleNode()
    * @generated
    */
   EAttribute getAbstractBundleNode_Version();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getSymbolicName <em>Symbolic Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Symbolic Name</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleNode#getSymbolicName()
    * @see #getAbstractBundleNode()
    * @generated
    */
   EAttribute getAbstractBundleNode_SymbolicName();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getScope
    * <em>Scope</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Scope</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleNode#getScope()
    * @see #getAbstractBundleNode()
    * @generated
    */
   EAttribute getAbstractBundleNode_Scope();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.RootBundleNode
    * <em>Root Bundle Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Root Bundle Node</em>'.
    * @see org.sourcepit.osgify.bundletree.RootBundleNode
    * @generated
    */
   EClass getRootBundleNode();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.BundleNode <em>Bundle Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Node</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleNode
    * @generated
    */
   EClass getBundleNode();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.BundleNode#getVersionRange
    * <em>Version Range</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version Range</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleNode#getVersionRange()
    * @see #getBundleNode()
    * @generated
    */
   EAttribute getBundleNode_VersionRange();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.BundleNode#isEnabled
    * <em>Enabled</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Enabled</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleNode#isEnabled()
    * @see #getBundleNode()
    * @generated
    */
   EAttribute getBundleNode_Enabled();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.BundleNode#isOptional
    * <em>Optional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Optional</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleNode#isOptional()
    * @see #getBundleNode()
    * @generated
    */
   EAttribute getBundleNode_Optional();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the factory that creates the instances of the model.
    * @generated
    */
   BundleTreeModelFactory getBundleTreeModelFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    * <li>each class,</li>
    * <li>each feature of each class,</li>
    * <li>each enum,</li>
    * <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   interface Literals
   {
      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.BundleTreeImpl
       * <em>Bundle Tree</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundleTree()
       * @generated
       */
      EClass BUNDLE_TREE = eINSTANCE.getBundleTree();

      /**
       * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_TREE__NODES = eINSTANCE.getBundleTree_Nodes();

      /**
       * The meta object literal for the '<em><b>Bundles</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_TREE__BUNDLES = eINSTANCE.getBundleTree_Bundles();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl
       * <em>Abstract Bundle Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getAbstractBundleNode()
       * @generated
       */
      EClass ABSTRACT_BUNDLE_NODE = eINSTANCE.getAbstractBundleNode();

      /**
       * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference ABSTRACT_BUNDLE_NODE__NODES = eINSTANCE.getAbstractBundleNode_Nodes();

      /**
       * The meta object literal for the '<em><b>Target</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference ABSTRACT_BUNDLE_NODE__TARGET = eINSTANCE.getAbstractBundleNode_Target();

      /**
       * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute ABSTRACT_BUNDLE_NODE__VERSION = eINSTANCE.getAbstractBundleNode_Version();

      /**
       * The meta object literal for the '<em><b>Symbolic Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME = eINSTANCE.getAbstractBundleNode_SymbolicName();

      /**
       * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute ABSTRACT_BUNDLE_NODE__SCOPE = eINSTANCE.getAbstractBundleNode_Scope();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.RootBundleNodeImpl
       * <em>Root Bundle Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.RootBundleNodeImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getRootBundleNode()
       * @generated
       */
      EClass ROOT_BUNDLE_NODE = eINSTANCE.getRootBundleNode();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.BundleNodeImpl
       * <em>Bundle Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.BundleNodeImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundleNode()
       * @generated
       */
      EClass BUNDLE_NODE = eINSTANCE.getBundleNode();

      /**
       * The meta object literal for the '<em><b>Version Range</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_NODE__VERSION_RANGE = eINSTANCE.getBundleNode_VersionRange();

      /**
       * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_NODE__ENABLED = eINSTANCE.getBundleNode_Enabled();

      /**
       * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_NODE__OPTIONAL = eINSTANCE.getBundleNode_Optional();

   }

} // BundleTreeModelPackage
