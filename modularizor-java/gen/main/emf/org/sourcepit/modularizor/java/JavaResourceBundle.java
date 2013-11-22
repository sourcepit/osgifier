/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Resource Bundle</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.java.JavaResourceBundle#getResourcesRoots <em>Resources Roots</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.java.JavaModelPackage#getJavaResourceBundle()
 * @model abstract="true"
 * @generated
 */
public interface JavaResourceBundle extends JavaElement
{
   /**
    * Returns the value of the '<em><b>Resources Roots</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.modularizor.java.JavaResourcesRoot}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.modularizor.java.JavaResourcesRoot#getPackageBundle
    * <em>Package Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Resources Roots</em>' containment reference list isn't clear, there really should be
    * more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Resources Roots</em>' containment reference list.
    * @see org.sourcepit.modularizor.java.JavaModelPackage#getJavaResourceBundle_ResourcesRoots()
    * @see org.sourcepit.modularizor.java.JavaResourcesRoot#getPackageBundle
    * @model opposite="packageBundle" containment="true"
    * @generated
    */
   EList<JavaResourcesRoot> getResourcesRoots();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaResourcesRoot getResourcesRoot(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaResourcesRoot getResourcesRoot(String name, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaPackage getPackage(String rootName, String qualifiedPackageName, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaType getType(String rootName, String qualifiedPackageName, String typeName, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model visitorType="org.sourcepit.modularizor.java.ResourceVisitor" visitorRequired="true"
    * @generated
    */
   void accept(ResourceVisitor visitor);

} // JavaResourceBundle
