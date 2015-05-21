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

package org.sourcepit.osgifier.core.model.context;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.sourcepit.osgifier.core.model.context.ContextModelFactory
 * @model kind="package"
 * @generated
 */
public interface ContextModelPackage extends EPackage {
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
   String eNS_URI = "http://www.sourcepit.org/osgifier/context/0.1";

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
   ContextModelPackage eINSTANCE = org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.osgifier.core.model.context.impl.OsgifierContextImpl
    * <em>Osgifier Context</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgifier.core.model.context.impl.OsgifierContextImpl
    * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getOsgifierContext()
    * @generated
    */
   int OSGIFIER_CONTEXT = 0;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFIER_CONTEXT__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFIER_CONTEXT__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Bundles</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFIER_CONTEXT__BUNDLES = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Osgifier Context</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OSGIFIER_CONTEXT_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl
    * <em>Bundle Candidate</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl
    * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getBundleCandidate()
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
    * The feature id for the '<em><b>Localization</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__LOCALIZATION = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 7;

   /**
    * The feature id for the '<em><b>Source Bundle</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__SOURCE_BUNDLE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 8;

   /**
    * The feature id for the '<em><b>Target Bundle</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE__TARGET_BUNDLE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 9;

   /**
    * The number of structural features of the '<em>Bundle Candidate</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_CANDIDATE_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 10;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl
    * <em>Bundle Reference</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl
    * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getBundleReference()
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
    * The feature id for the '<em><b>Embed Instruction</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__EMBED_INSTRUCTION = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 4;

   /**
    * The number of structural features of the '<em>Bundle Reference</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 5;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifier.core.model.context.impl.BundleLocalizationImpl
    * <em>Bundle Localization</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgifier.core.model.context.impl.BundleLocalizationImpl
    * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getBundleLocalization()
    * @generated
    */
   int BUNDLE_LOCALIZATION = 3;

   /**
    * The feature id for the '<em><b>Data</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_LOCALIZATION__DATA = 0;

   /**
    * The number of structural features of the '<em>Bundle Localization</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_LOCALIZATION_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifier.core.model.context.impl.LocalizedDataImpl
    * <em>Localized Data</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgifier.core.model.context.impl.LocalizedDataImpl
    * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getLocalizedData()
    * @generated
    */
   int LOCALIZED_DATA = 4;

   /**
    * The feature id for the '<em><b>Locale</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LOCALIZED_DATA__LOCALE = 0;

   /**
    * The feature id for the '<em><b>Data</b></em>' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LOCALIZED_DATA__DATA = 1;

   /**
    * The number of structural features of the '<em>Localized Data</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int LOCALIZED_DATA_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifier.core.model.context.EmbedInstruction
    * <em>Embed Instruction</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgifier.core.model.context.EmbedInstruction
    * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getEmbedInstruction()
    * @generated
    */
   int EMBED_INSTRUCTION = 5;


   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifier.core.model.context.OsgifierContext
    * <em>Osgifier Context</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Osgifier Context</em>'.
    * @see org.sourcepit.osgifier.core.model.context.OsgifierContext
    * @generated
    */
   EClass getOsgifierContext();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgifier.core.model.context.OsgifierContext#getBundles <em>Bundles</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Bundles</em>'.
    * @see org.sourcepit.osgifier.core.model.context.OsgifierContext#getBundles()
    * @see #getOsgifierContext()
    * @generated
    */
   EReference getOsgifierContext_Bundles();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate
    * <em>Bundle Candidate</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Candidate</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate
    * @generated
    */
   EClass getBundleCandidate();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocation <em>Location</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Location</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocation()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_Location();

   /**
    * Returns the meta object for the containment reference '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getContent <em>Content</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Content</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getContent()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_Content();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getDependencies <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Dependencies</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getDependencies()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_Dependencies();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getVersion <em>Version</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getVersion()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_Version();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getSymbolicName <em>Symbolic Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Symbolic Name</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getSymbolicName()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_SymbolicName();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#isNativeBundle <em>Native Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Native Bundle</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#isNativeBundle()
    * @see #getBundleCandidate()
    * @generated
    */
   EAttribute getBundleCandidate_NativeBundle();

   /**
    * Returns the meta object for the containment reference '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getManifest <em>Manifest</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Manifest</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getManifest()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_Manifest();

   /**
    * Returns the meta object for the containment reference '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocalization <em>Localization</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Localization</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocalization()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_Localization();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getSourceBundle <em>Source Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Source Bundle</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getSourceBundle()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_SourceBundle();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getTargetBundle <em>Target Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target Bundle</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getTargetBundle()
    * @see #getBundleCandidate()
    * @generated
    */
   EReference getBundleCandidate_TargetBundle();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifier.core.model.context.BundleReference
    * <em>Bundle Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Reference</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleReference
    * @generated
    */
   EClass getBundleReference();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleReference#getVersionRange <em>Version Range</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version Range</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleReference#getVersionRange()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_VersionRange();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleReference#isOptional <em>Optional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Optional</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleReference#isOptional()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_Optional();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.osgifier.core.model.context.BundleReference#getTarget <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleReference#getTarget()
    * @see #getBundleReference()
    * @generated
    */
   EReference getBundleReference_Target();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleReference#isProvided <em>Provided</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Provided</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleReference#isProvided()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_Provided();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.BundleReference#getEmbedInstruction <em>Embed Instruction</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Embed Instruction</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleReference#getEmbedInstruction()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_EmbedInstruction();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifier.core.model.context.BundleLocalization
    * <em>Bundle Localization</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Localization</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleLocalization
    * @generated
    */
   EClass getBundleLocalization();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgifier.core.model.context.BundleLocalization#getData <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Data</em>'.
    * @see org.sourcepit.osgifier.core.model.context.BundleLocalization#getData()
    * @see #getBundleLocalization()
    * @generated
    */
   EReference getBundleLocalization_Data();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifier.core.model.context.LocalizedData
    * <em>Localized Data</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Localized Data</em>'.
    * @see org.sourcepit.osgifier.core.model.context.LocalizedData
    * @generated
    */
   EClass getLocalizedData();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgifier.core.model.context.LocalizedData#getLocale <em>Locale</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Locale</em>'.
    * @see org.sourcepit.osgifier.core.model.context.LocalizedData#getLocale()
    * @see #getLocalizedData()
    * @generated
    */
   EAttribute getLocalizedData_Locale();

   /**
    * Returns the meta object for the map '{@link org.sourcepit.osgifier.core.model.context.LocalizedData#getData
    * <em>Data</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the map '<em>Data</em>'.
    * @see org.sourcepit.osgifier.core.model.context.LocalizedData#getData()
    * @see #getLocalizedData()
    * @generated
    */
   EReference getLocalizedData_Data();

   /**
    * Returns the meta object for enum '{@link org.sourcepit.osgifier.core.model.context.EmbedInstruction
    * <em>Embed Instruction</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for enum '<em>Embed Instruction</em>'.
    * @see org.sourcepit.osgifier.core.model.context.EmbedInstruction
    * @generated
    */
   EEnum getEmbedInstruction();

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
   interface Literals {
      /**
       * The meta object literal for the '{@link org.sourcepit.osgifier.core.model.context.impl.OsgifierContextImpl
       * <em>Osgifier Context</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgifier.core.model.context.impl.OsgifierContextImpl
       * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getOsgifierContext()
       * @generated
       */
      EClass OSGIFIER_CONTEXT = eINSTANCE.getOsgifierContext();

      /**
       * The meta object literal for the '<em><b>Bundles</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference OSGIFIER_CONTEXT__BUNDLES = eINSTANCE.getOsgifierContext_Bundles();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl
       * <em>Bundle Candidate</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl
       * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getBundleCandidate()
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
       * The meta object literal for the '<em><b>Localization</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_CANDIDATE__LOCALIZATION = eINSTANCE.getBundleCandidate_Localization();

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
       * The meta object literal for the '{@link org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl
       * <em>Bundle Reference</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl
       * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getBundleReference()
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

      /**
       * The meta object literal for the '<em><b>Embed Instruction</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute BUNDLE_REFERENCE__EMBED_INSTRUCTION = eINSTANCE.getBundleReference_EmbedInstruction();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifier.core.model.context.impl.BundleLocalizationImpl
       * <em>Bundle Localization</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgifier.core.model.context.impl.BundleLocalizationImpl
       * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getBundleLocalization()
       * @generated
       */
      EClass BUNDLE_LOCALIZATION = eINSTANCE.getBundleLocalization();

      /**
       * The meta object literal for the '<em><b>Data</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE_LOCALIZATION__DATA = eINSTANCE.getBundleLocalization_Data();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifier.core.model.context.impl.LocalizedDataImpl
       * <em>Localized Data</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgifier.core.model.context.impl.LocalizedDataImpl
       * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getLocalizedData()
       * @generated
       */
      EClass LOCALIZED_DATA = eINSTANCE.getLocalizedData();

      /**
       * The meta object literal for the '<em><b>Locale</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute LOCALIZED_DATA__LOCALE = eINSTANCE.getLocalizedData_Locale();

      /**
       * The meta object literal for the '<em><b>Data</b></em>' map feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference LOCALIZED_DATA__DATA = eINSTANCE.getLocalizedData_Data();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifier.core.model.context.EmbedInstruction
       * <em>Embed Instruction</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgifier.core.model.context.EmbedInstruction
       * @see org.sourcepit.osgifier.core.model.context.impl.ContextModelPackageImpl#getEmbedInstruction()
       * @generated
       */
      EEnum EMBED_INSTRUCTION = eINSTANCE.getEmbedInstruction();

   }

} // ContextModelPackage
