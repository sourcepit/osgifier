/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Resources Root</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.core.model.java.JavaResourcesRoot#getPackageBundle <em>Package Bundle</em>}</li>
 * <li>{@link org.sourcepit.modularizor.core.model.java.JavaResourcesRoot#getResourcesType <em>Resources Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaResourcesRoot()
 * @model
 * @generated
 */
public interface JavaResourcesRoot extends JavaResourceDirectory
{
   /**
    * Returns the value of the '<em><b>Package Bundle</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.core.model.java.JavaResourceBundle#getResourcesRoots <em>Resources Roots</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Package Bundle</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Package Bundle</em>' container reference.
    * @see #setPackageBundle(JavaResourceBundle)
    * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaResourcesRoot_PackageBundle()
    * @see org.sourcepit.modularizor.core.model.java.JavaResourceBundle#getResourcesRoots
    * @model opposite="resourcesRoots" required="true" transient="false"
    * @generated
    */
   JavaResourceBundle getPackageBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.core.model.java.JavaResourcesRoot#getPackageBundle
    * <em>Package Bundle</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Package Bundle</em>' container reference.
    * @see #getPackageBundle()
    * @generated
    */
   void setPackageBundle(JavaResourceBundle value);

   /**
    * Returns the value of the '<em><b>Resources Type</b></em>' attribute.
    * The default value is <code>"BIN"</code>.
    * The literals are from the enumeration {@link org.sourcepit.modularizor.core.model.java.JavaResourcesType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Resources Type</em>' attribute isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Resources Type</em>' attribute.
    * @see org.sourcepit.modularizor.core.model.java.JavaResourcesType
    * @see #setResourcesType(JavaResourcesType)
    * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage#getJavaResourcesRoot_ResourcesType()
    * @model default="BIN" required="true"
    * @generated
    */
   JavaResourcesType getResourcesType();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.core.model.java.JavaResourcesRoot#getResourcesType
    * <em>Resources Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Resources Type</em>' attribute.
    * @see org.sourcepit.modularizor.core.model.java.JavaResourcesType
    * @see #getResourcesType()
    * @generated
    */
   void setResourcesType(JavaResourcesType value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaType getType(String qualifiedPackageName, String typeName, boolean createOnDemand);

} // JavaResourcesRoot
