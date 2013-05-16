/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.context;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.sourcepit.common.modeling.CommonModelingPackage;

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
 * @see org.sourcepit.osgify.core.model.context.ContextModelFactory
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
   ContextModelPackage eINSTANCE = org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.core.model.context.impl.OsgifyContextImpl
    * <em>Osgify Context</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.core.model.context.impl.OsgifyContextImpl
    * @see org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl#getOsgifyContext()
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
   int OSGIFY_CONTEXT__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFY_CONTEXT__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Bundles</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFY_CONTEXT__BUNDLES = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Osgify Context</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFY_CONTEXT_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.core.model.context.impl.BundleCandidateImpl
    * <em>Bundle Candidate</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.core.model.context.impl.BundleCandidateImpl
    * @see org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl#getBundleCandidate()
    * @generated
    */
   int BUNDLE_CANDIDATE = 1;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Location</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__LOCATION = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Content</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__CONTENT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__DEPENDENCIES = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__VERSION = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__SYMBOLIC_NAME = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 4;

   /**
    * The feature id for the '<em><b>Native Bundle</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__NATIVE_BUNDLE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 5;

   /**
    * The feature id for the '<em><b>Manifest</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__MANIFEST = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 6;

   /**
    * The feature id for the '<em><b>Source Bundle</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__SOURCE_BUNDLE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 7;

   /**
    * The feature id for the '<em><b>Target Bundle</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__TARGET_BUNDLE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 8;

   /**
    * The number of structural features of the '<em>Bundle Candidate</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 9;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.core.model.context.impl.BundleReferenceImpl
    * <em>Bundle Reference</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.core.model.context.impl.BundleReferenceImpl
    * @see org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl#getBundleReference()
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
   int BUNDLE_REFERENCE__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Version Range</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__VERSION_RANGE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__OPTIONAL = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__TARGET = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Provided</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__PROVIDED = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Bundle Reference</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 4;


   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.core.model.context.OsgifyContext
    * <em>Osgify Context</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Osgify Context</em>'.
    * @see org.sourcepit.osgify.core.model.context.OsgifyContext
    * @generated
    */
   EClass getOsgifyContext();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.core.model.context.OsgifyContext#getBundles <em>Bundles</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Bundles</em>'.
    * @see org.sourcepit.osgify.core.model.context.OsgifyContext#getBundles()
    * @see #getOsgifyContext()
    * @generated
    */
   EReference getOsgifyContext_Bundles();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.core.model.context.BundleCandidate
    * <em>Bundle Candidate</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Candidate</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate
    * @generated
    */
   EClass getBundleCandidate();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getLocation <em>Location</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Location</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getLocation()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_Location();

   /**
    * Returns the meta object for the containment reference '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getContent <em>Content</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Content</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getContent()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_Content();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getDependencies <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Dependencies</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getDependencies()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_Dependencies();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getVersion <em>Version</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getVersion()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_Version();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getSymbolicName <em>Symbolic Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Symbolic Name</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getSymbolicName()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_SymbolicName();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#isNativeBundle <em>Native Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Native Bundle</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#isNativeBundle()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_NativeBundle();

   /**
    * Returns the meta object for the containment reference '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getManifest <em>Manifest</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Manifest</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getManifest()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_Manifest();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getSourceBundle <em>Source Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Source Bundle</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getSourceBundle()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_SourceBundle();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate#getTargetBundle <em>Target Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target Bundle</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleCandidate#getTargetBundle()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_TargetBundle();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.core.model.context.BundleReference
    * <em>Bundle Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Reference</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleReference
    * @generated
    */
   EClass getBundleReference();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.core.model.context.BundleReference#getVersionRange <em>Version Range</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version Range</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleReference#getVersionRange()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_VersionRange();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.core.model.context.BundleReference#isOptional <em>Optional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Optional</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleReference#isOptional()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_Optional();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.osgify.core.model.context.BundleReference#getTarget <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleReference#getTarget()
    * @see #getBundleReference()
    * @generated
    */
   EReference getBundleReference_Target();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.core.model.context.BundleReference#isProvided <em>Provided</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Provided</em>'.
    * @see org.sourcepit.osgify.core.model.context.BundleReference#isProvided()
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
       * The meta object literal for the '{@link org.sourcepit.osgify.core.model.context.impl.OsgifyContextImpl
       * <em>Osgify Context</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.core.model.context.impl.OsgifyContextImpl
       * @see org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl#getOsgifyContext()
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
       * The meta object literal for the '{@link org.sourcepit.osgify.core.model.context.impl.BundleCandidateImpl
       * <em>Bundle Candidate</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.core.model.context.impl.BundleCandidateImpl
       * @see org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl#getBundleCandidate()
       * @generated
       */
      EClass BUNDLE_CANDIDATE = eINSTANCE.getBundleCandidate();

      /**
       * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_CANDIDATE__LOCATION = eINSTANCE.getBundleCandidate_Location();

      /**
       * The meta object literal for the '<em><b>Content</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_CANDIDATE__CONTENT = eINSTANCE.getBundleCandidate_Content();

      /**
       * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_CANDIDATE__DEPENDENCIES = eINSTANCE.getBundleCandidate_Dependencies();

      /**
       * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_CANDIDATE__VERSION = eINSTANCE.getBundleCandidate_Version();

      /**
       * The meta object literal for the '<em><b>Symbolic Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_CANDIDATE__SYMBOLIC_NAME = eINSTANCE.getBundleCandidate_SymbolicName();

      /**
       * The meta object literal for the '<em><b>Native Bundle</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_CANDIDATE__NATIVE_BUNDLE = eINSTANCE.getBundleCandidate_NativeBundle();

      /**
       * The meta object literal for the '<em><b>Manifest</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_CANDIDATE__MANIFEST = eINSTANCE.getBundleCandidate_Manifest();

      /**
       * The meta object literal for the '<em><b>Source Bundle</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_CANDIDATE__SOURCE_BUNDLE = eINSTANCE.getBundleCandidate_SourceBundle();

      /**
       * The meta object literal for the '<em><b>Target Bundle</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_CANDIDATE__TARGET_BUNDLE = eINSTANCE.getBundleCandidate_TargetBundle();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.core.model.context.impl.BundleReferenceImpl
       * <em>Bundle Reference</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.core.model.context.impl.BundleReferenceImpl
       * @see org.sourcepit.osgify.core.model.context.impl.ContextModelPackageImpl#getBundleReference()
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
