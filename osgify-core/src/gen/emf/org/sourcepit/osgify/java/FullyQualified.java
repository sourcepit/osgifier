/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.java;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fully Qualified</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see org.sourcepit.osgify.java.JavaModelPackage#getFullyQualified()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface FullyQualified extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   String getFullyQualifiedName();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   String getSimpleName();

} // FullyQualified
