/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds;

import java.io.File;

import org.sourcepit.modularizor.java.JavaArchive;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembled Module</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.AssembledModule#getJavaResources <em>Java Resources</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.AssembledModule#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getAssembledModule()
 * @model
 * @generated
 */
public interface AssembledModule extends AbstractModule
{
   /**
    * Returns the value of the '<em><b>Java Resources</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Java Resources</em>' containment reference isn't clear, there really should be more of
    * a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Java Resources</em>' containment reference.
    * @see #setJavaResources(JavaArchive)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getAssembledModule_JavaResources()
    * @model containment="true" required="true"
    * @generated
    */
   JavaArchive getJavaResources();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.AssembledModule#getJavaResources
    * <em>Java Resources</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Java Resources</em>' containment reference.
    * @see #getJavaResources()
    * @generated
    */
   void setJavaResources(JavaArchive value);

   /**
    * Returns the value of the '<em><b>File</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>File</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>File</em>' attribute.
    * @see #setFile(File)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getAssembledModule_File()
    * @model dataType="org.sourcepit.common.modeling.EFile" required="true"
    * @generated
    */
   File getFile();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.AssembledModule#getFile <em>File</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>File</em>' attribute.
    * @see #getFile()
    * @generated
    */
   void setFile(File value);

} // AssembledModule
