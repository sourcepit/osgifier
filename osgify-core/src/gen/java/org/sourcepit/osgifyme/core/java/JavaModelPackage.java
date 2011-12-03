/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

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
 * @see org.sourcepit.osgifyme.core.java.JavaModelFactory
 * @model kind="package"
 * @generated
 */
public interface JavaModelPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "java";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http://org.sourcepit.osgifyme.java/1.0";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "java";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   JavaModelPackage eINSTANCE = org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl <em>Java Type</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaType()
    * @generated
    */
   int JAVA_TYPE = 0;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Inner Types</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE__INNER_TYPES = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Outer Type</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE__OUTER_TYPE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Simple Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE__SIMPLE_NAME = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>Java Type</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.JavaTypeRoot <em>Java Type Root</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.JavaTypeRoot
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaTypeRoot()
    * @generated
    */
   int JAVA_TYPE_ROOT = 3;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE_ROOT__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE_ROOT__TYPE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Parent Package</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE_ROOT__PARENT_PACKAGE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Java Type Root</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_TYPE_ROOT_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaCompilationUnitImpl <em>Java Compilation Unit</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaCompilationUnitImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaCompilationUnit()
    * @generated
    */
   int JAVA_COMPILATION_UNIT = 1;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_COMPILATION_UNIT__ANNOTATIONS = JAVA_TYPE_ROOT__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_COMPILATION_UNIT__TYPE = JAVA_TYPE_ROOT__TYPE;

   /**
    * The feature id for the '<em><b>Parent Package</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_COMPILATION_UNIT__PARENT_PACKAGE = JAVA_TYPE_ROOT__PARENT_PACKAGE;

   /**
    * The feature id for the '<em><b>Import Declarations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS = JAVA_TYPE_ROOT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Java Compilation Unit</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_COMPILATION_UNIT_FEATURE_COUNT = JAVA_TYPE_ROOT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.ImportDeclarationImpl <em>Import Declaration</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.ImportDeclarationImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getImportDeclaration()
    * @generated
    */
   int IMPORT_DECLARATION = 2;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int IMPORT_DECLARATION__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Compilation Unit</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int IMPORT_DECLARATION__COMPILATION_UNIT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Import Declaration</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int IMPORT_DECLARATION_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaClassImpl <em>Java Class</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaClassImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaClass()
    * @generated
    */
   int JAVA_CLASS = 4;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_CLASS__ANNOTATIONS = JAVA_TYPE_ROOT__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Type</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_CLASS__TYPE = JAVA_TYPE_ROOT__TYPE;

   /**
    * The feature id for the '<em><b>Parent Package</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_CLASS__PARENT_PACKAGE = JAVA_TYPE_ROOT__PARENT_PACKAGE;

   /**
    * The number of structural features of the '<em>Java Class</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_CLASS_FEATURE_COUNT = JAVA_TYPE_ROOT_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageImpl <em>Java Package</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaPackage()
    * @generated
    */
   int JAVA_PACKAGE = 5;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Type Roots</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE__TYPE_ROOTS = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Simple Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE__SIMPLE_NAME = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Packages</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE__PACKAGES = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Parent Package</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE__PARENT_PACKAGE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Java Package</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 4;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageBundleImpl <em>Java Package Bundle</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageBundleImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaPackageBundle()
    * @generated
    */
   int JAVA_PACKAGE_BUNDLE = 6;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_BUNDLE__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_BUNDLE__DEPENDENCIES = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Package Roots</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_BUNDLE__PACKAGE_ROOTS = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_BUNDLE__VERSION = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>Java Package Bundle</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_BUNDLE_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaArchiveImpl <em>Java Archive</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaArchiveImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaArchive()
    * @generated
    */
   int JAVA_ARCHIVE = 7;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_ARCHIVE__ANNOTATIONS = JAVA_PACKAGE_BUNDLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_ARCHIVE__DEPENDENCIES = JAVA_PACKAGE_BUNDLE__DEPENDENCIES;

   /**
    * The feature id for the '<em><b>Package Roots</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_ARCHIVE__PACKAGE_ROOTS = JAVA_PACKAGE_BUNDLE__PACKAGE_ROOTS;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_ARCHIVE__VERSION = JAVA_PACKAGE_BUNDLE__VERSION;

   /**
    * The number of structural features of the '<em>Java Archive</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_ARCHIVE_FEATURE_COUNT = JAVA_PACKAGE_BUNDLE_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaProjectImpl <em>Java Project</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaProjectImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaProject()
    * @generated
    */
   int JAVA_PROJECT = 8;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PROJECT__ANNOTATIONS = JAVA_PACKAGE_BUNDLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PROJECT__DEPENDENCIES = JAVA_PACKAGE_BUNDLE__DEPENDENCIES;

   /**
    * The feature id for the '<em><b>Package Roots</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PROJECT__PACKAGE_ROOTS = JAVA_PACKAGE_BUNDLE__PACKAGE_ROOTS;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PROJECT__VERSION = JAVA_PACKAGE_BUNDLE__VERSION;

   /**
    * The number of structural features of the '<em>Java Project</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PROJECT_FEATURE_COUNT = JAVA_PACKAGE_BUNDLE_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaModelImpl <em>Java Model</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaModel()
    * @generated
    */
   int JAVA_MODEL = 9;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_MODEL__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Projects</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_MODEL__PROJECTS = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Archives</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_MODEL__ARCHIVES = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Java Model</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_MODEL_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl <em>Dependency Node</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getDependencyNode()
    * @generated
    */
   int DEPENDENCY_NODE = 10;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__TARGET = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__OPTIONAL = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__DEPENDENCIES = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Enabled</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__ENABLED = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 3;

   /**
    * The feature id for the '<em><b>Parent Node</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__PARENT_NODE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 4;

   /**
    * The feature id for the '<em><b>Package Bundle</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__PACKAGE_BUNDLE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 5;

   /**
    * The feature id for the '<em><b>Scope</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__SCOPE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 6;

   /**
    * The feature id for the '<em><b>Version Range</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE__VERSION_RANGE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 7;

   /**
    * The number of structural features of the '<em>Dependency Node</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DEPENDENCY_NODE_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 8;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.FullyQualified <em>Fully Qualified</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.FullyQualified
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getFullyQualified()
    * @generated
    */
   int FULLY_QUALIFIED = 11;

   /**
    * The number of structural features of the '<em>Fully Qualified</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int FULLY_QUALIFIED_FEATURE_COUNT = 0;

   /**
    * The meta object id for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageRootImpl <em>Java Package Root</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageRootImpl
    * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaPackageRoot()
    * @generated
    */
   int JAVA_PACKAGE_ROOT = 12;

   /**
    * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_ROOT__ANNOTATIONS = CommonModelPackage.ANNOTATABLE__ANNOTATIONS;

   /**
    * The feature id for the '<em><b>Path</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_ROOT__PATH = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Root Packages</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_ROOT__ROOT_PACKAGES = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Package Bundle</b></em>' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>Java Package Root</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int JAVA_PACKAGE_ROOT_FEATURE_COUNT = CommonModelPackage.ANNOTATABLE_FEATURE_COUNT + 3;


   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaType <em>Java Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Type</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaType
    * @generated
    */
   EClass getJavaType();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaType#getInnerTypes <em>Inner Types</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Inner Types</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaType#getInnerTypes()
    * @see #getJavaType()
    * @generated
    */
   EReference getJavaType_InnerTypes();

   /**
    * Returns the meta object for the container reference '{@link org.sourcepit.osgifyme.core.java.JavaType#getOuterType <em>Outer Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Outer Type</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaType#getOuterType()
    * @see #getJavaType()
    * @generated
    */
   EReference getJavaType_OuterType();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.JavaType#getSimpleName <em>Simple Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Simple Name</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaType#getSimpleName()
    * @see #getJavaType()
    * @generated
    */
   EAttribute getJavaType_SimpleName();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaCompilationUnit <em>Java Compilation Unit</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Compilation Unit</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaCompilationUnit
    * @generated
    */
   EClass getJavaCompilationUnit();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaCompilationUnit#getImportDeclarations <em>Import Declarations</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Import Declarations</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaCompilationUnit#getImportDeclarations()
    * @see #getJavaCompilationUnit()
    * @generated
    */
   EReference getJavaCompilationUnit_ImportDeclarations();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.ImportDeclaration <em>Import Declaration</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Import Declaration</em>'.
    * @see org.sourcepit.osgifyme.core.java.ImportDeclaration
    * @generated
    */
   EClass getImportDeclaration();

   /**
    * Returns the meta object for the container reference '{@link org.sourcepit.osgifyme.core.java.ImportDeclaration#getCompilationUnit <em>Compilation Unit</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Compilation Unit</em>'.
    * @see org.sourcepit.osgifyme.core.java.ImportDeclaration#getCompilationUnit()
    * @see #getImportDeclaration()
    * @generated
    */
   EReference getImportDeclaration_CompilationUnit();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaTypeRoot <em>Java Type Root</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Type Root</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaTypeRoot
    * @generated
    */
   EClass getJavaTypeRoot();

   /**
    * Returns the meta object for the containment reference '{@link org.sourcepit.osgifyme.core.java.JavaTypeRoot#getType <em>Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Type</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaTypeRoot#getType()
    * @see #getJavaTypeRoot()
    * @generated
    */
   EReference getJavaTypeRoot_Type();

   /**
    * Returns the meta object for the container reference '{@link org.sourcepit.osgifyme.core.java.JavaTypeRoot#getParentPackage <em>Parent Package</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Parent Package</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaTypeRoot#getParentPackage()
    * @see #getJavaTypeRoot()
    * @generated
    */
   EReference getJavaTypeRoot_ParentPackage();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaClass <em>Java Class</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Class</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaClass
    * @generated
    */
   EClass getJavaClass();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaPackage <em>Java Package</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Package</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackage
    * @generated
    */
   EClass getJavaPackage();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaPackage#getTypeRoots <em>Type Roots</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Type Roots</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackage#getTypeRoots()
    * @see #getJavaPackage()
    * @generated
    */
   EReference getJavaPackage_TypeRoots();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.JavaPackage#getSimpleName <em>Simple Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Simple Name</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackage#getSimpleName()
    * @see #getJavaPackage()
    * @generated
    */
   EAttribute getJavaPackage_SimpleName();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaPackage#getPackages <em>Packages</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Packages</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackage#getPackages()
    * @see #getJavaPackage()
    * @generated
    */
   EReference getJavaPackage_Packages();

   /**
    * Returns the meta object for the container reference '{@link org.sourcepit.osgifyme.core.java.JavaPackage#getParentPackage <em>Parent Package</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Parent Package</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackage#getParentPackage()
    * @see #getJavaPackage()
    * @generated
    */
   EReference getJavaPackage_ParentPackage();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaPackageBundle <em>Java Package Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Package Bundle</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageBundle
    * @generated
    */
   EClass getJavaPackageBundle();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaPackageBundle#getDependencies <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Dependencies</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageBundle#getDependencies()
    * @see #getJavaPackageBundle()
    * @generated
    */
   EReference getJavaPackageBundle_Dependencies();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaPackageBundle#getPackageRoots <em>Package Roots</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Package Roots</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageBundle#getPackageRoots()
    * @see #getJavaPackageBundle()
    * @generated
    */
   EReference getJavaPackageBundle_PackageRoots();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.JavaPackageBundle#getVersion <em>Version</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Version</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageBundle#getVersion()
    * @see #getJavaPackageBundle()
    * @generated
    */
   EAttribute getJavaPackageBundle_Version();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaArchive <em>Java Archive</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Archive</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaArchive
    * @generated
    */
   EClass getJavaArchive();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaProject <em>Java Project</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Project</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaProject
    * @generated
    */
   EClass getJavaProject();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaModel <em>Java Model</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Model</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaModel
    * @generated
    */
   EClass getJavaModel();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaModel#getProjects <em>Projects</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Projects</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaModel#getProjects()
    * @see #getJavaModel()
    * @generated
    */
   EReference getJavaModel_Projects();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaModel#getArchives <em>Archives</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Archives</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaModel#getArchives()
    * @see #getJavaModel()
    * @generated
    */
   EReference getJavaModel_Archives();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.DependencyNode <em>Dependency Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Dependency Node</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode
    * @generated
    */
   EClass getDependencyNode();

   /**
    * Returns the meta object for the reference '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getTarget <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Target</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getTarget()
    * @see #getDependencyNode()
    * @generated
    */
   EReference getDependencyNode_Target();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.DependencyNode#isOptional <em>Optional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Optional</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#isOptional()
    * @see #getDependencyNode()
    * @generated
    */
   EAttribute getDependencyNode_Optional();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getDependencies <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Dependencies</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getDependencies()
    * @see #getDependencyNode()
    * @generated
    */
   EReference getDependencyNode_Dependencies();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.DependencyNode#isEnabled <em>Enabled</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Enabled</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#isEnabled()
    * @see #getDependencyNode()
    * @generated
    */
   EAttribute getDependencyNode_Enabled();

   /**
    * Returns the meta object for the container reference '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getParentNode <em>Parent Node</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Parent Node</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getParentNode()
    * @see #getDependencyNode()
    * @generated
    */
   EReference getDependencyNode_ParentNode();

   /**
    * Returns the meta object for the container reference '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getPackageBundle <em>Package Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Package Bundle</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getPackageBundle()
    * @see #getDependencyNode()
    * @generated
    */
   EReference getDependencyNode_PackageBundle();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getScope <em>Scope</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Scope</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getScope()
    * @see #getDependencyNode()
    * @generated
    */
   EAttribute getDependencyNode_Scope();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getVersionRange <em>Version Range</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Version Range</em>'.
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getVersionRange()
    * @see #getDependencyNode()
    * @generated
    */
   EAttribute getDependencyNode_VersionRange();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.FullyQualified <em>Fully Qualified</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Fully Qualified</em>'.
    * @see org.sourcepit.osgifyme.core.java.FullyQualified
    * @generated
    */
   EClass getFullyQualified();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgifyme.core.java.JavaPackageRoot <em>Java Package Root</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Java Package Root</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageRoot
    * @generated
    */
   EClass getJavaPackageRoot();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgifyme.core.java.JavaPackageRoot#getPath <em>Path</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Path</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageRoot#getPath()
    * @see #getJavaPackageRoot()
    * @generated
    */
   EAttribute getJavaPackageRoot_Path();

   /**
    * Returns the meta object for the containment reference list '{@link org.sourcepit.osgifyme.core.java.JavaPackageRoot#getRootPackages <em>Root Packages</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Root Packages</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageRoot#getRootPackages()
    * @see #getJavaPackageRoot()
    * @generated
    */
   EReference getJavaPackageRoot_RootPackages();

   /**
    * Returns the meta object for the container reference '{@link org.sourcepit.osgifyme.core.java.JavaPackageRoot#getPackageBundle <em>Package Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the container reference '<em>Package Bundle</em>'.
    * @see org.sourcepit.osgifyme.core.java.JavaPackageRoot#getPackageBundle()
    * @see #getJavaPackageRoot()
    * @generated
    */
   EReference getJavaPackageRoot_PackageBundle();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   JavaModelFactory getJavaModelFactory();

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
    * @generated
    */
   interface Literals
   {
      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl <em>Java Type</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaType()
       * @generated
       */
      EClass JAVA_TYPE = eINSTANCE.getJavaType();

      /**
       * The meta object literal for the '<em><b>Inner Types</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_TYPE__INNER_TYPES = eINSTANCE.getJavaType_InnerTypes();

      /**
       * The meta object literal for the '<em><b>Outer Type</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_TYPE__OUTER_TYPE = eINSTANCE.getJavaType_OuterType();

      /**
       * The meta object literal for the '<em><b>Simple Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute JAVA_TYPE__SIMPLE_NAME = eINSTANCE.getJavaType_SimpleName();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaCompilationUnitImpl <em>Java Compilation Unit</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaCompilationUnitImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaCompilationUnit()
       * @generated
       */
      EClass JAVA_COMPILATION_UNIT = eINSTANCE.getJavaCompilationUnit();

      /**
       * The meta object literal for the '<em><b>Import Declarations</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS = eINSTANCE.getJavaCompilationUnit_ImportDeclarations();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.ImportDeclarationImpl <em>Import Declaration</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.ImportDeclarationImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getImportDeclaration()
       * @generated
       */
      EClass IMPORT_DECLARATION = eINSTANCE.getImportDeclaration();

      /**
       * The meta object literal for the '<em><b>Compilation Unit</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference IMPORT_DECLARATION__COMPILATION_UNIT = eINSTANCE.getImportDeclaration_CompilationUnit();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.JavaTypeRoot <em>Java Type Root</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.JavaTypeRoot
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaTypeRoot()
       * @generated
       */
      EClass JAVA_TYPE_ROOT = eINSTANCE.getJavaTypeRoot();

      /**
       * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_TYPE_ROOT__TYPE = eINSTANCE.getJavaTypeRoot_Type();

      /**
       * The meta object literal for the '<em><b>Parent Package</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_TYPE_ROOT__PARENT_PACKAGE = eINSTANCE.getJavaTypeRoot_ParentPackage();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaClassImpl <em>Java Class</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaClassImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaClass()
       * @generated
       */
      EClass JAVA_CLASS = eINSTANCE.getJavaClass();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageImpl <em>Java Package</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaPackage()
       * @generated
       */
      EClass JAVA_PACKAGE = eINSTANCE.getJavaPackage();

      /**
       * The meta object literal for the '<em><b>Type Roots</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_PACKAGE__TYPE_ROOTS = eINSTANCE.getJavaPackage_TypeRoots();

      /**
       * The meta object literal for the '<em><b>Simple Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute JAVA_PACKAGE__SIMPLE_NAME = eINSTANCE.getJavaPackage_SimpleName();

      /**
       * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_PACKAGE__PACKAGES = eINSTANCE.getJavaPackage_Packages();

      /**
       * The meta object literal for the '<em><b>Parent Package</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_PACKAGE__PARENT_PACKAGE = eINSTANCE.getJavaPackage_ParentPackage();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageBundleImpl <em>Java Package Bundle</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageBundleImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaPackageBundle()
       * @generated
       */
      EClass JAVA_PACKAGE_BUNDLE = eINSTANCE.getJavaPackageBundle();

      /**
       * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_PACKAGE_BUNDLE__DEPENDENCIES = eINSTANCE.getJavaPackageBundle_Dependencies();

      /**
       * The meta object literal for the '<em><b>Package Roots</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_PACKAGE_BUNDLE__PACKAGE_ROOTS = eINSTANCE.getJavaPackageBundle_PackageRoots();

      /**
       * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute JAVA_PACKAGE_BUNDLE__VERSION = eINSTANCE.getJavaPackageBundle_Version();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaArchiveImpl <em>Java Archive</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaArchiveImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaArchive()
       * @generated
       */
      EClass JAVA_ARCHIVE = eINSTANCE.getJavaArchive();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaProjectImpl <em>Java Project</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaProjectImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaProject()
       * @generated
       */
      EClass JAVA_PROJECT = eINSTANCE.getJavaProject();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaModelImpl <em>Java Model</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaModel()
       * @generated
       */
      EClass JAVA_MODEL = eINSTANCE.getJavaModel();

      /**
       * The meta object literal for the '<em><b>Projects</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_MODEL__PROJECTS = eINSTANCE.getJavaModel_Projects();

      /**
       * The meta object literal for the '<em><b>Archives</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_MODEL__ARCHIVES = eINSTANCE.getJavaModel_Archives();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl <em>Dependency Node</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getDependencyNode()
       * @generated
       */
      EClass DEPENDENCY_NODE = eINSTANCE.getDependencyNode();

      /**
       * The meta object literal for the '<em><b>Target</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DEPENDENCY_NODE__TARGET = eINSTANCE.getDependencyNode_Target();

      /**
       * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute DEPENDENCY_NODE__OPTIONAL = eINSTANCE.getDependencyNode_Optional();

      /**
       * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DEPENDENCY_NODE__DEPENDENCIES = eINSTANCE.getDependencyNode_Dependencies();

      /**
       * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute DEPENDENCY_NODE__ENABLED = eINSTANCE.getDependencyNode_Enabled();

      /**
       * The meta object literal for the '<em><b>Parent Node</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DEPENDENCY_NODE__PARENT_NODE = eINSTANCE.getDependencyNode_ParentNode();

      /**
       * The meta object literal for the '<em><b>Package Bundle</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference DEPENDENCY_NODE__PACKAGE_BUNDLE = eINSTANCE.getDependencyNode_PackageBundle();

      /**
       * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute DEPENDENCY_NODE__SCOPE = eINSTANCE.getDependencyNode_Scope();

      /**
       * The meta object literal for the '<em><b>Version Range</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute DEPENDENCY_NODE__VERSION_RANGE = eINSTANCE.getDependencyNode_VersionRange();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.FullyQualified <em>Fully Qualified</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.FullyQualified
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getFullyQualified()
       * @generated
       */
      EClass FULLY_QUALIFIED = eINSTANCE.getFullyQualified();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageRootImpl <em>Java Package Root</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageRootImpl
       * @see org.sourcepit.osgifyme.core.java.internal.impl.JavaModelPackageImpl#getJavaPackageRoot()
       * @generated
       */
      EClass JAVA_PACKAGE_ROOT = eINSTANCE.getJavaPackageRoot();

      /**
       * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute JAVA_PACKAGE_ROOT__PATH = eINSTANCE.getJavaPackageRoot_Path();

      /**
       * The meta object literal for the '<em><b>Root Packages</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_PACKAGE_ROOT__ROOT_PACKAGES = eINSTANCE.getJavaPackageRoot_RootPackages();

      /**
       * The meta object literal for the '<em><b>Package Bundle</b></em>' container reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE = eINSTANCE.getJavaPackageRoot_PackageBundle();

   }

} // JavaModelPackage
