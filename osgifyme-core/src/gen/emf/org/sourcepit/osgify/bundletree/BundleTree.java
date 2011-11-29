/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Tree</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.BundleTree#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.BundleTree#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getBundleTree()
 * @model
 * @generated
 */
public interface BundleTree extends EObject
{
   /**
    * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.bundletree.RootBundleNode}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Nodes</em>' containment reference list.
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getBundleTree_Nodes()
    * @model containment="true"
    * @generated
    */
   EList<RootBundleNode> getNodes();

   /**
    * Returns the value of the '<em><b>Bundles</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.java.JavaPackageBundle}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Bundles</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Bundles</em>' containment reference list.
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getBundleTree_Bundles()
    * @model containment="true"
    * @generated
    */
   EList<JavaPackageBundle> getBundles();

} // BundleTree
