/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage
 * @generated
 */
public interface BundleTreeModelFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   BundleTreeModelFactory eINSTANCE = org.sourcepit.osgify.bundletree.impl.BundleTreeModelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Bundle Tree</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Bundle Tree</em>'.
    * @generated
    */
   BundleTree createBundleTree();

   /**
    * Returns a new object of class '<em>Root Bundle Node</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Root Bundle Node</em>'.
    * @generated
    */
   RootBundleNode createRootBundleNode();

   /**
    * Returns a new object of class '<em>Bundle Node</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return a new object of class '<em>Bundle Node</em>'.
    * @generated
    */
   BundleNode createBundleNode();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the package supported by this factory.
    * @generated
    */
   BundleTreeModelPackage getBundleTreeModelPackage();

} // BundleTreeModelFactory
