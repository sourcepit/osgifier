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
 * A representation of the model object '<em><b>Directory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.Directory#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getDirectory()
 * @model
 * @generated
 */
public interface Directory extends Resource {
   /**
    * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifier.core.model.java.Resource}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifier.core.model.java.Resource#getParentDirectory <em>Parent Directory</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Resources</em>' containment reference list isn't clear, there really should be more of
    * a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Resources</em>' containment reference list.
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getDirectory_Resources()
    * @see org.sourcepit.osgifier.core.model.java.Resource#getParentDirectory
    * @model opposite="parentDirectory" containment="true"
    * @generated
    */
   EList<Resource> getResources();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   Resource getResource(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   EList<Directory> getDirectories();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   Directory getDirectory(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   Directory getDirectory(String name, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   File getFile(String name);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   File getFile(String name, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   EList<File> getFiles();

} // Directory
