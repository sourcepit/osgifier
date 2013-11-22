/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams;

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
 * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsFactory
 * @model kind="package"
 * @generated
 */
public interface ModuleRelamsPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNAME = "modulerelams";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_URI = "http://www.sourcepit.org/modularizor/modulerelams/0.1";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_PREFIX = "modulerelams";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   ModuleRelamsPackage eINSTANCE = org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmsImpl
    * <em>Module Realms</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRealmsImpl
    * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsPackageImpl#getModuleRealms()
    * @generated
    */
   int MODULE_REALMS = 0;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALMS__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALMS__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Modules</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALMS__MODULES = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Module Realms</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALMS__MODULE_REALMS = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Module Realms</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALMS_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmImpl
    * <em>Module Realm</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRealmImpl
    * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsPackageImpl#getModuleRealm()
    * @generated
    */
   int MODULE_REALM = 1;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Module Realms</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__MODULE_REALMS = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Realm Module</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__REALM_MODULE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Module References</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__MODULE_REFERENCES = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>Module Realm</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.modulerelams.impl.ModuleReferenceImpl
    * <em>Module Reference</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.modulerelams.impl.ModuleReferenceImpl
    * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsPackageImpl#getModuleReference()
    * @generated
    */
   int MODULE_REFERENCE = 2;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Module Realm</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE__MODULE_REALM = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Target Module</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE__TARGET_MODULE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Module Reference</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;


   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.modulerelams.ModuleRealms
    * <em>Module Realms</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Module Realms</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealms
    * @generated
    */
   EClass getModuleRealms();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealms#getModules <em>Modules</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Modules</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealms#getModules()
    * @see #getModuleRealms()
    * @generated
    */
   EReference getModuleRealms_Modules();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealms#getModuleRealms <em>Module Realms</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Module Realms</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealms#getModuleRealms()
    * @see #getModuleRealms()
    * @generated
    */
   EReference getModuleRealms_ModuleRealms();

   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.modulerelams.ModuleRealm
    * <em>Module Realm</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Module Realm</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealm
    * @generated
    */
   EClass getModuleRealm();

   /**
    * Returns the meta object for the container reference '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleRealms <em>Module Realms</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the container reference '<em>Module Realms</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleRealms()
    * @see #getModuleRealm()
    * @generated
    */
   EReference getModuleRealm_ModuleRealms();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getRealmModule <em>Realm Module</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Realm Module</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealm#getRealmModule()
    * @see #getModuleRealm()
    * @generated
    */
   EReference getModuleRealm_RealmModule();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleReferences <em>Module References</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Module References</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleReferences()
    * @see #getModuleRealm()
    * @generated
    */
   EReference getModuleRealm_ModuleReferences();

   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.modulerelams.ModuleReference
    * <em>Module Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Module Reference</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleReference
    * @generated
    */
   EClass getModuleReference();

   /**
    * Returns the meta object for the container reference '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleReference#getModuleRealm <em>Module Realm</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the container reference '<em>Module Realm</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleReference#getModuleRealm()
    * @see #getModuleReference()
    * @generated
    */
   EReference getModuleReference_ModuleRealm();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleReference#getTargetModule <em>Target Module</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target Module</em>'.
    * @see org.sourcepit.modularizor.modulerelams.ModuleReference#getTargetModule()
    * @see #getModuleReference()
    * @generated
    */
   EReference getModuleReference_TargetModule();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the factory that creates the instances of the model.
    * @generated
    */
   ModuleRelamsFactory getModuleRelamsFactory();

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
       * The meta object literal for the '{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmsImpl
       * <em>Module Realms</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRealmsImpl
       * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsPackageImpl#getModuleRealms()
       * @generated
       */
      EClass MODULE_REALMS = eINSTANCE.getModuleRealms();

      /**
       * The meta object literal for the '<em><b>Modules</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALMS__MODULES = eINSTANCE.getModuleRealms_Modules();

      /**
       * The meta object literal for the '<em><b>Module Realms</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALMS__MODULE_REALMS = eINSTANCE.getModuleRealms_ModuleRealms();

      /**
       * The meta object literal for the '{@link org.sourcepit.modularizor.modulerelams.impl.ModuleRealmImpl
       * <em>Module Realm</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRealmImpl
       * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsPackageImpl#getModuleRealm()
       * @generated
       */
      EClass MODULE_REALM = eINSTANCE.getModuleRealm();

      /**
       * The meta object literal for the '<em><b>Module Realms</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALM__MODULE_REALMS = eINSTANCE.getModuleRealm_ModuleRealms();

      /**
       * The meta object literal for the '<em><b>Realm Module</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALM__REALM_MODULE = eINSTANCE.getModuleRealm_RealmModule();

      /**
       * The meta object literal for the '<em><b>Module References</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALM__MODULE_REFERENCES = eINSTANCE.getModuleRealm_ModuleReferences();

      /**
       * The meta object literal for the '{@link org.sourcepit.modularizor.modulerelams.impl.ModuleReferenceImpl
       * <em>Module Reference</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.modulerelams.impl.ModuleReferenceImpl
       * @see org.sourcepit.modularizor.modulerelams.impl.ModuleRelamsPackageImpl#getModuleReference()
       * @generated
       */
      EClass MODULE_REFERENCE = eINSTANCE.getModuleReference();

      /**
       * The meta object literal for the '<em><b>Module Realm</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REFERENCE__MODULE_REALM = eINSTANCE.getModuleReference_ModuleRealm();

      /**
       * The meta object literal for the '<em><b>Target Module</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REFERENCE__TARGET_MODULE = eINSTANCE.getModuleReference_TargetModule();

   }

} // ModuleRelamsPackage
