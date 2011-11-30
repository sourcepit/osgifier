/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OS Gi Fy Context</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.OSGiFyContext#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getOSGiFyContext()
 * @model
 * @generated
 */
public interface OSGiFyContext extends EObject
{
   /**
    * Returns the value of the '<em><b>Bundles</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.bundletree.Bundle}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Bundles</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Bundles</em>' containment reference list.
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getOSGiFyContext_Bundles()
    * @model containment="true"
    * @generated
    */
   EList<Bundle> getBundles();

} // OSGiFyContext
