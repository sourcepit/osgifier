/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Archive</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaArchive()
 * @model
 * @generated
 */
public interface JavaArchive extends JavaPackageBundle
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaType getType(String packageName, String typeName, boolean createOnDemand);

} // JavaArchive
