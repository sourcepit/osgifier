/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Bundle Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getSymbolicName <em>Symbolic Name</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getAbstractBundleNode()
 * @model abstract="true"
 * @generated
 */
public interface AbstractBundleNode extends EObject
{
   /**
    * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.bundletree.BundleNode}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Nodes</em>' containment reference list.
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getAbstractBundleNode_Nodes()
    * @model containment="true"
    * @generated
    */
   EList<BundleNode> getNodes();

   /**
    * Returns the value of the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Target</em>' reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Target</em>' reference.
    * @see #setTarget(JavaPackageBundle)
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getAbstractBundleNode_Target()
    * @model
    * @generated
    */
   JavaPackageBundle getTarget();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getTarget <em>Target</em>}'
    * reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Target</em>' reference.
    * @see #getTarget()
    * @generated
    */
   void setTarget(JavaPackageBundle value);

   /**
    * Returns the value of the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Version</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Version</em>' attribute.
    * @see #setVersion(Version)
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getAbstractBundleNode_Version()
    * @model dataType="org.sourcepit.common.manifest.osgi.Version"
    * @generated
    */
   Version getVersion();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getVersion <em>Version</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Version</em>' attribute.
    * @see #getVersion()
    * @generated
    */
   void setVersion(Version value);

   /**
    * Returns the value of the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Symbolic Name</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Symbolic Name</em>' attribute.
    * @see #setSymbolicName(String)
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getAbstractBundleNode_SymbolicName()
    * @model
    * @generated
    */
   String getSymbolicName();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getSymbolicName
    * <em>Symbolic Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Symbolic Name</em>' attribute.
    * @see #getSymbolicName()
    * @generated
    */
   void setSymbolicName(String value);

   /**
    * Returns the value of the '<em><b>Scope</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Scope</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Scope</em>' attribute.
    * @see #setScope(String)
    * @see org.sourcepit.osgify.bundletree.BundleTreeModelPackage#getAbstractBundleNode_Scope()
    * @model
    * @generated
    */
   String getScope();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.bundletree.AbstractBundleNode#getScope <em>Scope</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Scope</em>' attribute.
    * @see #getScope()
    * @generated
    */
   void setScope(String value);

} // AbstractBundleNode
