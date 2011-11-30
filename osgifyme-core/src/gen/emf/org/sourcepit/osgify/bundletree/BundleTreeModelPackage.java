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
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.OSGiFyContextImpl
    * <em>OS Gi Fy Context</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.OSGiFyContextImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getOSGiFyContext()
    * @generated
    */
   int OS_GI_FY_CONTEXT = 0;

   /**
    * The feature id for the '<em><b>Bundles</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OS_GI_FY_CONTEXT__BUNDLES = 0;

   /**
    * The number of structural features of the '<em>OS Gi Fy Context</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int OS_GI_FY_CONTEXT_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleCoordinateImpl
    * <em>Abstract Bundle Coordinate</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.AbstractBundleCoordinateImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getAbstractBundleCoordinate()
    * @generated
    */
   int ABSTRACT_BUNDLE_COORDINATE = 1;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_COORDINATE__VERSION = 0;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME = 1;

   /**
    * The number of structural features of the '<em>Abstract Bundle Coordinate</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.BundleImpl <em>Bundle</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.BundleImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundle()
    * @generated
    */
   int BUNDLE = 2;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE__VERSION = ABSTRACT_BUNDLE_COORDINATE__VERSION;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE__SYMBOLIC_NAME = ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME;

   /**
    * The feature id for the '<em><b>Content</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE__CONTENT = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE__DEPENDENCIES = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Bundle</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_FEATURE_COUNT = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl
    * <em>Bundle Reference</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl
    * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundleReference()
    * @generated
    */
   int BUNDLE_REFERENCE = 3;

   /**
    * The feature id for the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__VERSION = ABSTRACT_BUNDLE_COORDINATE__VERSION;

   /**
    * The feature id for the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__SYMBOLIC_NAME = ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME;

   /**
    * The feature id for the '<em><b>Version Range</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__VERSION_RANGE = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__OPTIONAL = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__TARGET = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 2;

   /**
    * The feature id for the '<em><b>Provided</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE__PROVIDED = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 3;

   /**
    * The number of structural features of the '<em>Bundle Reference</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    * @ordered
    */
   int BUNDLE_REFERENCE_FEATURE_COUNT = ABSTRACT_BUNDLE_COORDINATE_FEATURE_COUNT + 4;


   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.OSGiFyContext <em>OS Gi Fy Context</em>}
    * '.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>OS Gi Fy Context</em>'.
    * @see org.sourcepit.osgify.bundletree.OSGiFyContext
    * @generated
    */
   EClass getOSGiFyContext();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.bundletree.OSGiFyContext#getBundles <em>Bundles</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Bundles</em>'.
    * @see org.sourcepit.osgify.bundletree.OSGiFyContext#getBundles()
    * @see #getOSGiFyContext()
    * @generated
    */
   EReference getOSGiFyContext_Bundles();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.AbstractBundleCoordinate
    * <em>Abstract Bundle Coordinate</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Abstract Bundle Coordinate</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleCoordinate
    * @generated
    */
   EClass getAbstractBundleCoordinate();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.bundletree.AbstractBundleCoordinate#getVersion <em>Version</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleCoordinate#getVersion()
    * @see #getAbstractBundleCoordinate()
    * @generated
    */
   EAttribute getAbstractBundleCoordinate_Version();

   /**
    * Returns the meta object for the attribute '
    * {@link org.sourcepit.osgify.bundletree.AbstractBundleCoordinate#getSymbolicName <em>Symbolic Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Symbolic Name</em>'.
    * @see org.sourcepit.osgify.bundletree.AbstractBundleCoordinate#getSymbolicName()
    * @see #getAbstractBundleCoordinate()
    * @generated
    */
   EAttribute getAbstractBundleCoordinate_SymbolicName();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.Bundle <em>Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle</em>'.
    * @see org.sourcepit.osgify.bundletree.Bundle
    * @generated
    */
   EClass getBundle();

   /**
    * Returns the meta object for the containment reference '{@link org.sourcepit.osgify.bundletree.Bundle#getContent
    * <em>Content</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference '<em>Content</em>'.
    * @see org.sourcepit.osgify.bundletree.Bundle#getContent()
    * @see #getBundle()
    * @generated
    */
   EReference getBundle_Content();

   /**
    * Returns the meta object for the containment reference list '
    * {@link org.sourcepit.osgify.bundletree.Bundle#getDependencies <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the containment reference list '<em>Dependencies</em>'.
    * @see org.sourcepit.osgify.bundletree.Bundle#getDependencies()
    * @see #getBundle()
    * @generated
    */
   EReference getBundle_Dependencies();

   /**
    * Returns the meta object for class '{@link org.sourcepit.osgify.bundletree.BundleReference
    * <em>Bundle Reference</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for class '<em>Bundle Reference</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleReference
    * @generated
    */
   EClass getBundleReference();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.BundleReference#getVersionRange
    * <em>Version Range</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Version Range</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleReference#getVersionRange()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_VersionRange();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.BundleReference#isOptional
    * <em>Optional</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Optional</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleReference#isOptional()
    * @see #getBundleReference()
    * @generated
    */
   EAttribute getBundleReference_Optional();

   /**
    * Returns the meta object for the reference '{@link org.sourcepit.osgify.bundletree.BundleReference#getTarget
    * <em>Target</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the reference '<em>Target</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleReference#getTarget()
    * @see #getBundleReference()
    * @generated
    */
   EReference getBundleReference_Target();

   /**
    * Returns the meta object for the attribute '{@link org.sourcepit.osgify.bundletree.BundleReference#isProvided
    * <em>Provided</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the meta object for the attribute '<em>Provided</em>'.
    * @see org.sourcepit.osgify.bundletree.BundleReference#isProvided()
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
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.OSGiFyContextImpl
       * <em>OS Gi Fy Context</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.OSGiFyContextImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getOSGiFyContext()
       * @generated
       */
      EClass OS_GI_FY_CONTEXT = eINSTANCE.getOSGiFyContext();

      /**
       * The meta object literal for the '<em><b>Bundles</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference OS_GI_FY_CONTEXT__BUNDLES = eINSTANCE.getOSGiFyContext_Bundles();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleCoordinateImpl
       * <em>Abstract Bundle Coordinate</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.AbstractBundleCoordinateImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getAbstractBundleCoordinate()
       * @generated
       */
      EClass ABSTRACT_BUNDLE_COORDINATE = eINSTANCE.getAbstractBundleCoordinate();

      /**
       * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute ABSTRACT_BUNDLE_COORDINATE__VERSION = eINSTANCE.getAbstractBundleCoordinate_Version();

      /**
       * The meta object literal for the '<em><b>Symbolic Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EAttribute ABSTRACT_BUNDLE_COORDINATE__SYMBOLIC_NAME = eINSTANCE.getAbstractBundleCoordinate_SymbolicName();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.BundleImpl <em>Bundle</em>}'
       * class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.BundleImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundle()
       * @generated
       */
      EClass BUNDLE = eINSTANCE.getBundle();

      /**
       * The meta object literal for the '<em><b>Content</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE__CONTENT = eINSTANCE.getBundle_Content();

      /**
       * The meta object literal for the '<em><b>Dependencies</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @generated
       */
      EReference BUNDLE__DEPENDENCIES = eINSTANCE.getBundle_Dependencies();

      /**
       * The meta object literal for the '{@link org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl
       * <em>Bundle Reference</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * 
       * @see org.sourcepit.osgify.bundletree.impl.BundleReferenceImpl
       * @see org.sourcepit.osgify.bundletree.impl.BundleTreeModelPackageImpl#getBundleReference()
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

} // BundleTreeModelPackage
