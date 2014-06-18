/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
public interface Directory extends Resource
{
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
