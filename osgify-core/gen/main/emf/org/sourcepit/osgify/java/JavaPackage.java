/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Package</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.java.JavaPackage#getTypeRoots <em>Type Roots</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.JavaPackage#getSimpleName <em>Simple Name</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.JavaPackage#getPackages <em>Packages</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.JavaPackage#getParentPackage <em>Parent Package</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackage()
 * @model
 * @generated
 */
public interface JavaPackage extends XAnnotatable, FullyQualified
{
   /**
    * Returns the value of the '<em><b>Type Roots</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.java.JavaTypeRoot}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgify.java.JavaTypeRoot#getParentPackage
    * <em>Parent Package</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Type Roots</em>' containment reference list isn't clear, there really should be more of
    * a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Type Roots</em>' containment reference list.
    * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackage_TypeRoots()
    * @see org.sourcepit.osgify.java.JavaTypeRoot#getParentPackage
    * @model opposite="parentPackage" containment="true"
    * @generated
    */
   EList<JavaTypeRoot> getTypeRoots();

   /**
    * Returns the value of the '<em><b>Simple Name</b></em>' attribute.
    * The default value is <code>""</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Simple Name</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Simple Name</em>' attribute.
    * @see #setSimpleName(String)
    * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackage_SimpleName()
    * @model default="" required="true"
    * @generated
    */
   String getSimpleName();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.java.JavaPackage#getSimpleName <em>Simple Name</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Simple Name</em>' attribute.
    * @see #getSimpleName()
    * @generated
    */
   void setSimpleName(String value);

   /**
    * Returns the value of the '<em><b>Packages</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.java.JavaPackage}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgify.java.JavaPackage#getParentPackage
    * <em>Parent Package</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Packages</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Packages</em>' containment reference list.
    * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackage_Packages()
    * @see org.sourcepit.osgify.java.JavaPackage#getParentPackage
    * @model opposite="parentPackage" containment="true"
    * @generated
    */
   EList<JavaPackage> getPackages();

   /**
    * Returns the value of the '<em><b>Parent Package</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgify.java.JavaPackage#getPackages
    * <em>Packages</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parent Package</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Parent Package</em>' container reference.
    * @see #setParentPackage(JavaPackage)
    * @see org.sourcepit.osgify.java.JavaModelPackage#getJavaPackage_ParentPackage()
    * @see org.sourcepit.osgify.java.JavaPackage#getPackages
    * @model opposite="packages"
    * @generated
    */
   JavaPackage getParentPackage();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.java.JavaPackage#getParentPackage <em>Parent Package</em>}'
    * container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Parent Package</em>' container reference.
    * @see #getParentPackage()
    * @generated
    */
   void setParentPackage(JavaPackage value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaPackage getSubPackage(String name, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   JavaPackageBundle getPackageBundle();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   JavaPackageRoot getPackageRoot();

} // JavaPackage
