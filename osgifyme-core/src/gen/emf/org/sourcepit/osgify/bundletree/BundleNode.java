/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree;

import org.sourcepit.common.manifest.osgi.VersionRange;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.BundleNode#getVersionRange <em>Version Range</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.BundleNode#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.BundleNode#isOptional <em>Optional</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getBundleNode()
 * @model
 * @generated
 */
public interface BundleNode extends AbstractBundleNode
{
   /**
    * Returns the value of the '<em><b>Version Range</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Version Range</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Version Range</em>' attribute.
    * @see #setVersionRange(VersionRange)
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getBundleNode_VersionRange()
    * @model dataType="org.sourcepit.common.manifest.osgi.VersionRange"
    * @generated
    */
   VersionRange getVersionRange();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.bundletree.BundleNode#getVersionRange <em>Version Range</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Version Range</em>' attribute.
    * @see #getVersionRange()
    * @generated
    */
   void setVersionRange(VersionRange value);

   /**
    * Returns the value of the '<em><b>Enabled</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Enabled</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Enabled</em>' attribute.
    * @see #setEnabled(boolean)
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getBundleNode_Enabled()
    * @model
    * @generated
    */
   boolean isEnabled();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.bundletree.BundleNode#isEnabled <em>Enabled</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Enabled</em>' attribute.
    * @see #isEnabled()
    * @generated
    */
   void setEnabled(boolean value);

   /**
    * Returns the value of the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Optional</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Optional</em>' attribute.
    * @see #setOptional(boolean)
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getBundleNode_Optional()
    * @model
    * @generated
    */
   boolean isOptional();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.bundletree.BundleNode#isOptional <em>Optional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Optional</em>' attribute.
    * @see #isOptional()
    * @generated
    */
   void setOptional(boolean value);

} // BundleNode
