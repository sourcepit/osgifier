/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.model.java;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Resources Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaResourcesRoot#getPackageBundle <em>Package Bundle</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaResourcesRoot#getResourcesType <em>Resources Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaResourcesRoot()
 * @model
 * @generated
 */
public interface JavaResourcesRoot extends JavaResourceDirectory {
   /**
    * Returns the value of the '<em><b>Package Bundle</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifier.core.model.java.JavaResourceBundle#getResourcesRoots <em>Resources Roots</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Package Bundle</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Package Bundle</em>' container reference.
    * @see #setPackageBundle(JavaResourceBundle)
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaResourcesRoot_PackageBundle()
    * @see org.sourcepit.osgifier.core.model.java.JavaResourceBundle#getResourcesRoots
    * @model opposite="resourcesRoots" required="true" transient="false"
    * @generated
    */
   JavaResourceBundle getPackageBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.java.JavaResourcesRoot#getPackageBundle
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
    * The literals are from the enumeration {@link org.sourcepit.osgifier.core.model.java.JavaResourcesType}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Resources Type</em>' attribute isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Resources Type</em>' attribute.
    * @see org.sourcepit.osgifier.core.model.java.JavaResourcesType
    * @see #setResourcesType(JavaResourcesType)
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaResourcesRoot_ResourcesType()
    * @model default="BIN" required="true"
    * @generated
    */
   JavaResourcesType getResourcesType();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.java.JavaResourcesRoot#getResourcesType
    * <em>Resources Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Resources Type</em>' attribute.
    * @see org.sourcepit.osgifier.core.model.java.JavaResourcesType
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
