/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds;

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
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsFactory
 * @model kind="package"
 * @generated
 */
public interface ModuleWorldsPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNAME = "moduleworlds";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_URI = "http://www.sourcepit.org/modularizor/moduleworlds/0.1";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   String eNS_PREFIX = "moduleworlds";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   ModuleWorldsPackage eINSTANCE = org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldImpl
    * <em>Module World</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldImpl
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getModuleWorld()
    * @generated
    */
   int MODULE_WORLD = 0;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_WORLD__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_WORLD__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Modules</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_WORLD__MODULES = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Module Realms</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_WORLD__MODULE_REALMS = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Module World</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_WORLD_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleRealmImpl
    * <em>Module Realm</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleRealmImpl
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getModuleRealm()
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
    * The feature id for the '<em><b>Module World</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__MODULE_WORLD = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Module</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__MODULE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Referenced Modules</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REALM__REFERENCED_MODULES = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

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
    * The meta object id for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl
    * <em>Module Reference</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getModuleReference()
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
    * The feature id for the '<em><b>Transitive</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE__TRANSITIVE = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE__OPTIONAL = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Module Reference</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int MODULE_REFERENCE_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 4;

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.moduleworlds.impl.AbstractModuleImpl
    * <em>Abstract Module</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.moduleworlds.impl.AbstractModuleImpl
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getAbstractModule()
    * @generated
    */
   int ABSTRACT_MODULE = 3;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_MODULE__EXTENSIONS = CommonModelingPackage.XANNOTATABLE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_MODULE__ANNOTATIONS = CommonModelingPackage.XANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Module World</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_MODULE__MODULE_WORLD = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Source Attachment</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_MODULE__SOURCE_ATTACHMENT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Abstract Module</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_MODULE_FEATURE_COUNT = CommonModelingPackage.XANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.moduleworlds.impl.AssembledModuleImpl
    * <em>Assembled Module</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.moduleworlds.impl.AssembledModuleImpl
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getAssembledModule()
    * @generated
    */
   int ASSEMBLED_MODULE = 4;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ASSEMBLED_MODULE__EXTENSIONS = ABSTRACT_MODULE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ASSEMBLED_MODULE__ANNOTATIONS = ABSTRACT_MODULE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Module World</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ASSEMBLED_MODULE__MODULE_WORLD = ABSTRACT_MODULE__MODULE_WORLD;

   /**
    * The feature id for the '<em><b>Source Attachment</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ASSEMBLED_MODULE__SOURCE_ATTACHMENT = ABSTRACT_MODULE__SOURCE_ATTACHMENT;

   /**
    * The feature id for the '<em><b>Java Resources</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ASSEMBLED_MODULE__JAVA_RESOURCES = ABSTRACT_MODULE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>File</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ASSEMBLED_MODULE__FILE = ABSTRACT_MODULE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Assembled Module</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ASSEMBLED_MODULE_FEATURE_COUNT = ABSTRACT_MODULE_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl
    * <em>Exploded Module</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl
    * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getExplodedModule()
    * @generated
    */
   int EXPLODED_MODULE = 5;

   /**
    * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__EXTENSIONS = ABSTRACT_MODULE__EXTENSIONS;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__ANNOTATIONS = ABSTRACT_MODULE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Module World</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__MODULE_WORLD = ABSTRACT_MODULE__MODULE_WORLD;

   /**
    * The feature id for the '<em><b>Source Attachment</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__SOURCE_ATTACHMENT = ABSTRACT_MODULE__SOURCE_ATTACHMENT;

   /**
    * The feature id for the '<em><b>Directory</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__DIRECTORY = ABSTRACT_MODULE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Source Directories</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__SOURCE_DIRECTORIES = ABSTRACT_MODULE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Binary Directories</b></em>' attribute list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__BINARY_DIRECTORIES = ABSTRACT_MODULE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Java Resources</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE__JAVA_RESOURCES = ABSTRACT_MODULE_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Exploded Module</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int EXPLODED_MODULE_FEATURE_COUNT = ABSTRACT_MODULE_FEATURE_COUNT + 4;


   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.moduleworlds.ModuleWorld
    * <em>Module World</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Module World</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorld
    * @generated
    */
   EClass getModuleWorld();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModules <em>Modules</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Modules</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModules()
    * @see #getModuleWorld()
    * @generated
    */
   EReference getModuleWorld_Modules();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModuleRealms <em>Module Realms</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Module Realms</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModuleRealms()
    * @see #getModuleWorld()
    * @generated
    */
   EReference getModuleWorld_ModuleRealms();

   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm
    * <em>Module Realm</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Module Realm</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleRealm
    * @generated
    */
   EClass getModuleRealm();

   /**
    * Returns the meta object for the container reference '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModuleWorld <em>Module World</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the container reference '<em>Module World</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModuleWorld()
    * @see #getModuleRealm()
    * @generated
    */
   EReference getModuleRealm_ModuleWorld();

   /**
    * Returns the meta object for the reference '{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModule
    * <em>Module</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Module</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModule()
    * @see #getModuleRealm()
    * @generated
    */
   EReference getModuleRealm_Module();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getReferencedModules <em>Referenced Modules</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Referenced Modules</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleRealm#getReferencedModules()
    * @see #getModuleRealm()
    * @generated
    */
   EReference getModuleRealm_ReferencedModules();

   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.moduleworlds.ModuleReference
    * <em>Module Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Module Reference</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleReference
    * @generated
    */
   EClass getModuleReference();

   /**
    * Returns the meta object for the container reference '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleReference#getModuleRealm <em>Module Realm</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the container reference '<em>Module Realm</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleReference#getModuleRealm()
    * @see #getModuleReference()
    * @generated
    */
   EReference getModuleReference_ModuleRealm();

   /**
    * Returns the meta object for the reference '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleReference#getTargetModule <em>Target Module</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target Module</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleReference#getTargetModule()
    * @see #getModuleReference()
    * @generated
    */
   EReference getModuleReference_TargetModule();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleReference#isTransitive <em>Transitive</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Transitive</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleReference#isTransitive()
    * @see #getModuleReference()
    * @generated
    */
   EAttribute getModuleReference_Transitive();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleReference#isOptional <em>Optional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Optional</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleReference#isOptional()
    * @see #getModuleReference()
    * @generated
    */
   EAttribute getModuleReference_Optional();

   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.moduleworlds.AbstractModule
    * <em>Abstract Module</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Abstract Module</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.AbstractModule
    * @generated
    */
   EClass getAbstractModule();

   /**
    * Returns the meta object for the container reference '
    * {@link org.sourcepit.modularizor.moduleworlds.AbstractModule#getModuleWorld <em>Module World</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the container reference '<em>Module World</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.AbstractModule#getModuleWorld()
    * @see #getAbstractModule()
    * @generated
    */
   EReference getAbstractModule_ModuleWorld();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.modularizor.moduleworlds.AbstractModule#getSourceAttachment <em>Source Attachment</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Source Attachment</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.AbstractModule#getSourceAttachment()
    * @see #getAbstractModule()
    * @generated
    */
   EAttribute getAbstractModule_SourceAttachment();

   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.moduleworlds.AssembledModule
    * <em>Assembled Module</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Assembled Module</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.AssembledModule
    * @generated
    */
   EClass getAssembledModule();

   /**
    * Returns the meta object for the containment reference '
    * {@link org.sourcepit.modularizor.moduleworlds.AssembledModule#getJavaResources <em>Java Resources</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Java Resources</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.AssembledModule#getJavaResources()
    * @see #getAssembledModule()
    * @generated
    */
   EReference getAssembledModule_JavaResources();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.modularizor.moduleworlds.AssembledModule#getFile
    * <em>File</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>File</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.AssembledModule#getFile()
    * @see #getAssembledModule()
    * @generated
    */
   EAttribute getAssembledModule_File();

   /**
    * Returns the meta object for class '{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule
    * <em>Exploded Module</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Exploded Module</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ExplodedModule
    * @generated
    */
   EClass getExplodedModule();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getDirectory <em>Directory</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Directory</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ExplodedModule#getDirectory()
    * @see #getExplodedModule()
    * @generated
    */
   EAttribute getExplodedModule_Directory();

   /**
    * Returns the meta object for the attribute list '
    * {@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getSourceDirectories <em>Source Directories</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute list '<em>Source Directories</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ExplodedModule#getSourceDirectories()
    * @see #getExplodedModule()
    * @generated
    */
   EAttribute getExplodedModule_SourceDirectories();

   /**
    * Returns the meta object for the attribute list '
    * {@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getBinaryDirectories <em>Binary Directories</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute list '<em>Binary Directories</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ExplodedModule#getBinaryDirectories()
    * @see #getExplodedModule()
    * @generated
    */
   EAttribute getExplodedModule_BinaryDirectories();

   /**
    * Returns the meta object for the containment reference '
    * {@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getJavaResources <em>Java Resources</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Java Resources</em>'.
    * @see org.sourcepit.modularizor.moduleworlds.ExplodedModule#getJavaResources()
    * @see #getExplodedModule()
    * @generated
    */
   EReference getExplodedModule_JavaResources();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the factory that creates the instances of the model.
    * @generated
    */
   ModuleWorldsFactory getModuleWorldsFactory();

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
       * The meta object literal for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldImpl
       * <em>Module World</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldImpl
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getModuleWorld()
       * @generated
       */
      EClass MODULE_WORLD = eINSTANCE.getModuleWorld();

      /**
       * The meta object literal for the '<em><b>Modules</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_WORLD__MODULES = eINSTANCE.getModuleWorld_Modules();

      /**
       * The meta object literal for the '<em><b>Module Realms</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_WORLD__MODULE_REALMS = eINSTANCE.getModuleWorld_ModuleRealms();

      /**
       * The meta object literal for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleRealmImpl
       * <em>Module Realm</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleRealmImpl
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getModuleRealm()
       * @generated
       */
      EClass MODULE_REALM = eINSTANCE.getModuleRealm();

      /**
       * The meta object literal for the '<em><b>Module World</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALM__MODULE_WORLD = eINSTANCE.getModuleRealm_ModuleWorld();

      /**
       * The meta object literal for the '<em><b>Module</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALM__MODULE = eINSTANCE.getModuleRealm_Module();

      /**
       * The meta object literal for the '<em><b>Referenced Modules</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference MODULE_REALM__REFERENCED_MODULES = eINSTANCE.getModuleRealm_ReferencedModules();

      /**
       * The meta object literal for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl
       * <em>Module Reference</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleReferenceImpl
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getModuleReference()
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

      /**
       * The meta object literal for the '<em><b>Transitive</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute MODULE_REFERENCE__TRANSITIVE = eINSTANCE.getModuleReference_Transitive();

      /**
       * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute MODULE_REFERENCE__OPTIONAL = eINSTANCE.getModuleReference_Optional();

      /**
       * The meta object literal for the '{@link org.sourcepit.modularizor.moduleworlds.impl.AbstractModuleImpl
       * <em>Abstract Module</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.moduleworlds.impl.AbstractModuleImpl
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getAbstractModule()
       * @generated
       */
      EClass ABSTRACT_MODULE = eINSTANCE.getAbstractModule();

      /**
       * The meta object literal for the '<em><b>Module World</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference ABSTRACT_MODULE__MODULE_WORLD = eINSTANCE.getAbstractModule_ModuleWorld();

      /**
       * The meta object literal for the '<em><b>Source Attachment</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute ABSTRACT_MODULE__SOURCE_ATTACHMENT = eINSTANCE.getAbstractModule_SourceAttachment();

      /**
       * The meta object literal for the '{@link org.sourcepit.modularizor.moduleworlds.impl.AssembledModuleImpl
       * <em>Assembled Module</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.moduleworlds.impl.AssembledModuleImpl
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getAssembledModule()
       * @generated
       */
      EClass ASSEMBLED_MODULE = eINSTANCE.getAssembledModule();

      /**
       * The meta object literal for the '<em><b>Java Resources</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference ASSEMBLED_MODULE__JAVA_RESOURCES = eINSTANCE.getAssembledModule_JavaResources();

      /**
       * The meta object literal for the '<em><b>File</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute ASSEMBLED_MODULE__FILE = eINSTANCE.getAssembledModule_File();

      /**
       * The meta object literal for the '{@link org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl
       * <em>Exploded Module</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.modularizor.moduleworlds.impl.ExplodedModuleImpl
       * @see org.sourcepit.modularizor.moduleworlds.impl.ModuleWorldsPackageImpl#getExplodedModule()
       * @generated
       */
      EClass EXPLODED_MODULE = eINSTANCE.getExplodedModule();

      /**
       * The meta object literal for the '<em><b>Directory</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute EXPLODED_MODULE__DIRECTORY = eINSTANCE.getExplodedModule_Directory();

      /**
       * The meta object literal for the '<em><b>Source Directories</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute EXPLODED_MODULE__SOURCE_DIRECTORIES = eINSTANCE.getExplodedModule_SourceDirectories();

      /**
       * The meta object literal for the '<em><b>Binary Directories</b></em>' attribute list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute EXPLODED_MODULE__BINARY_DIRECTORIES = eINSTANCE.getExplodedModule_BinaryDirectories();

      /**
       * The meta object literal for the '<em><b>Java Resources</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference EXPLODED_MODULE__JAVA_RESOURCES = eINSTANCE.getExplodedModule_JavaResources();

   }

} // ModuleWorldsPackage
