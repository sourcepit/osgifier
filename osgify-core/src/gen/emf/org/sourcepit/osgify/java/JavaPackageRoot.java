/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.Annotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Package Root</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.java.JavaPackageRoot#getPath <em>Path</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.JavaPackageRoot#getRootPackages <em>Root Packages</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.JavaPackageRoot#getPackageBundle <em>Package Bundle</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackageRoot()
 * @model
 * @generated
 */
public interface JavaPackageRoot extends Annotatable
{
   /**
    * Returns the value of the '<em><b>Path</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Path</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Path</em>' attribute.
    * @see #setPath(String)
    * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackageRoot_Path()
    * @model required="true"
    * @generated
    */
   String getPath();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.java.JavaPackageRoot#getPath <em>Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Path</em>' attribute.
    * @see #getPath()
    * @generated
    */
   void setPath(String value);

   /**
    * Returns the value of the '<em><b>Root Packages</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.java.JavaPackage}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Root Packages</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Root Packages</em>' containment reference list.
    * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackageRoot_RootPackages()
    * @model containment="true"
    * @generated
    */
   EList<JavaPackage> getRootPackages();

   /**
    * Returns the value of the '<em><b>Package Bundle</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgify.java.JavaPackageBundle#getPackageRoots
    * <em>Package Roots</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Package Bundle</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Package Bundle</em>' container reference.
    * @see #setPackageBundle(JavaPackageBundle)
    * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackageRoot_PackageBundle()
    * @see org.sourcepit.osgify.java.JavaPackageBundle#getPackageRoots
    * @model opposite="packageRoots" required="true" transient="false"
    * @generated
    */
   JavaPackageBundle getPackageBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.java.JavaPackageRoot#getPackageBundle <em>Package Bundle</em>}'
    * container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Package Bundle</em>' container reference.
    * @see #getPackageBundle()
    * @generated
    */
   void setPackageBundle(JavaPackageBundle value);

} // JavaPackageRoot
