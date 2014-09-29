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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Resource Bundle</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaResourceBundle#getResourcesRoots <em>Resources Roots</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaResourceBundle()
 * @model abstract="true"
 * @generated
 */
public interface JavaResourceBundle extends JavaElement
{
   /**
    * Returns the value of the '<em><b>Resources Roots</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifier.core.model.java.JavaResourcesRoot}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifier.core.model.java.JavaResourcesRoot#getPackageBundle <em>Package Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Resources Roots</em>' containment reference list isn't clear, there really should be
    * more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Resources Roots</em>' containment reference list.
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaResourceBundle_ResourcesRoots()
    * @see org.sourcepit.osgifier.core.model.java.JavaResourcesRoot#getPackageBundle
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
    * @model visitorType="org.sourcepit.osgifier.core.model.java.ResourceVisitor" visitorRequired="true"
    * @generated
    */
   void accept(ResourceVisitor visitor);

} // JavaResourceBundle
