/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.sourcepit.modeling.common.CommonModelPackage;

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
 * @see org.sourcepit.osgify.context.ContextModelFactory
 * @model kind="package"
 * @generated
 */
public interface ContextModelPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNAME = "context";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_URI = "http://www.sourcepit.org/osgify/context/0.1";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_PREFIX = "ctx";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   ContextModelPackage eINSTANCE = org.sourcepit.osgify.context.impl.ContextModelPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.context.impl.OsgifyContextImpl <em>Osgify Context</em>}'
    * class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.context.impl.OsgifyContextImpl
    * @see org.sourcepit.osgify.context.impl.ContextModelPackageImpl#getOsgifyContext()
    * @generated
    */
   int OSGIFY_CONTEXT = 0;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFY_CONTEXT__EXTENSIONS = CommonModelPackage.EXTENDABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Bundles</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFY_CONTEXT__BUNDLES = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Osgify Context</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFY_CONTEXT_FEATURE_COUNT = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.context.impl.BundleNodeImpl <em>Bundle Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.context.impl.BundleNodeImpl
    * @see org.sourcepit.osgify.context.impl.ContextModelPackageImpl#getBundleNode()
    * @generated
    */
   int BUNDLE_NODE = 1;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__EXTENSIONS = CommonModelPackage.EXTENDABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Content</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__CONTENT = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__DEPENDENCIES = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__VERSION = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE__SYMBOLIC_NAME = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Bundle Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_NODE_FEATURE_COUNT = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 4;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.context.impl.BundleReferenceImpl
    * <em>Bundle Reference</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.context.impl.BundleReferenceImpl
    * @see org.sourcepit.osgify.context.impl.ContextModelPackageImpl#getBundleReference()
    * @generated
    */
   int BUNDLE_REFERENCE = 2;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__EXTENSIONS = CommonModelPackage.EXTENDABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Version Range</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__VERSION_RANGE = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__OPTIONAL = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__TARGET = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Provided</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__PROVIDED = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Bundle Reference</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE_FEATURE_COUNT = CommonModelPackage.EXTENDABLE_FEATURE_COUNT + 4;


   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.context.OsgifyContext <em>Osgify Context</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Osgify Context</em>'.
    * @see org.sourcepit.osgify.context.OsgifyContext
    * @generated
    */
   EClass getOsgifyContext();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.context.OsgifyContext#getBundles <em>Bundles</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Bundles</em>'.
    * @see org.sourcepit.osgify.context.OsgifyContext#getBundles()
    * @see #getOsgifyContext()
    * @generated
    */
   EReference getOsgifyContext_Bundles();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.context.BundleNode <em>Bundle Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Node</em>'.
    * @see org.sourcepit.osgify.context.BundleNode
    * @generated
    */
   EClass getBundleNode();

   /**
    * Returns the meta object for the containment reference '{@link org.sourcepit.osgify.context.BundleNode#getContent
    * <em>Content</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Content</em>'.
    * @see org.sourcepit.osgify.context.BundleNode#getContent()
    * @see #getBundleNode()
    * @generated
    */
   EReference getBundleNode_Content();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.context.BundleNode#getDependencies <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Dependencies</em>'.
    * @see org.sourcepit.osgify.context.BundleNode#getDependencies()
    * @see #getBundleNode()
    * @generated
    */
   EReference getBundleNode_Dependencies();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.context.BundleNode#getVersion
    * <em>Version</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version</em>'.
    * @see org.sourcepit.osgify.context.BundleNode#getVersion()
    * @see #getBundleNode()
    * @generated
    */
   EAttribute getBundleNode_Version();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.context.BundleNode#getSymbolicName
    * <em>Symbolic Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Symbolic Name</em>'.
    * @see org.sourcepit.osgify.context.BundleNode#getSymbolicName()
    * @see #getBundleNode()
    * @generated
    */
   EAttribute getBundleNode_SymbolicName();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.context.BundleReference <em>Bundle Reference</em>}
    * '.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Reference</em>'.
    * @see org.sourcepit.osgify.context.BundleReference
    * @generated
    */
   EClass getBundleReference();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.context.BundleReference#getVersionRange
    * <em>Version Range</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version Range</em>'.
    * @see org.sourcepit.osgify.context.BundleReference#getVersionRange()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_VersionRange();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.context.BundleReference#isOptional
    * <em>Optional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Optional</em>'.
    * @see org.sourcepit.osgify.context.BundleReference#isOptional()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_Optional();

   /**
    * Returns the meta object for the reference '{@link org.sourcepit.osgify.context.BundleReference#getTarget
    * <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target</em>'.
    * @see org.sourcepit.osgify.context.BundleReference#getTarget()
    * @see #getBundleReference()
    * @generated
    */
   EReference getBundleReference_Target();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.context.BundleReference#isProvided
    * <em>Provided</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Provided</em>'.
    * @see org.sourcepit.osgify.context.BundleReference#isProvided()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_Provided();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the factory that creates the instances of the model.
    * @generated
    */
   ContextModelFactory getContextModelFactory();

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
       * The meta object literal for the '{@link org.sourcepit.osgify.context.impl.OsgifyContextImpl
       * <em>Osgify Context</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.context.impl.OsgifyContextImpl
       * @see org.sourcepit.osgify.context.impl.ContextModelPackageImpl#getOsgifyContext()
       * @generated
       */
      EClass OSGIFY_CONTEXT = eINSTANCE.getOsgifyContext();

      /**
       * The meta object literal for the '<em><b>Bundles</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference OSGIFY_CONTEXT__BUNDLES = eINSTANCE.getOsgifyContext_Bundles();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.context.impl.BundleNodeImpl <em>Bundle Node</em>}'
       * class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.context.impl.BundleNodeImpl
       * @see org.sourcepit.osgify.context.impl.ContextModelPackageImpl#getBundleNode()
       * @generated
       */
      EClass BUNDLE_NODE = eINSTANCE.getBundleNode();

      /**
       * The meta object literal for the '<em><b>Content</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_NODE__CONTENT = eINSTANCE.getBundleNode_Content();

      /**
       * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_NODE__DEPENDENCIES = eINSTANCE.getBundleNode_Dependencies();

      /**
       * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_NODE__VERSION = eINSTANCE.getBundleNode_Version();

      /**
       * The meta object literal for the '<em><b>Symbolic Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_NODE__SYMBOLIC_NAME = eINSTANCE.getBundleNode_SymbolicName();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.context.impl.BundleReferenceImpl
       * <em>Bundle Reference</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.context.impl.BundleReferenceImpl
       * @see org.sourcepit.osgify.context.impl.ContextModelPackageImpl#getBundleReference()
       * @generated
       */
      EClass BUNDLE_REFERENCE = eINSTANCE.getBundleReference();

      /**
       * The meta object literal for the '<em><b>Version Range</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_REFERENCE__VERSION_RANGE = eINSTANCE.getBundleReference_VersionRange();

      /**
       * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_REFERENCE__OPTIONAL = eINSTANCE.getBundleReference_Optional();

      /**
       * The meta object literal for the '<em><b>Target</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_REFERENCE__TARGET = eINSTANCE.getBundleReference_Target();

      /**
       * The meta object literal for the '<em><b>Provided</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_REFERENCE__PROVIDED = eINSTANCE.getBundleReference_Provided();

   }

} // ContextModelPackage
