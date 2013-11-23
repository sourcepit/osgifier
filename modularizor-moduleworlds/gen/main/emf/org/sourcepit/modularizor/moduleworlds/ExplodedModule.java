/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modularizor.java.JavaProject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exploded Module</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getDirectory <em>Directory</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getSourceDirectories <em>Source Directories</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getBinaryDirectories <em>Binary Directories</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getJavaResources <em>Java Resources</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getExplodedModule()
 * @model
 * @generated
 */
public interface ExplodedModule extends AbstractModule
{
   /**
    * Returns the value of the '<em><b>Directory</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Directory</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Directory</em>' attribute.
    * @see #setDirectory(File)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getExplodedModule_Directory()
    * @model dataType="org.sourcepit.common.modeling.EFile" required="true"
    * @generated
    */
   File getDirectory();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getDirectory
    * <em>Directory</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Directory</em>' attribute.
    * @see #getDirectory()
    * @generated
    */
   void setDirectory(File value);

   /**
    * Returns the value of the '<em><b>Source Directories</b></em>' attribute list.
    * The list contents are of type {@link java.io.File}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Source Directories</em>' attribute list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Source Directories</em>' attribute list.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getExplodedModule_SourceDirectories()
    * @model dataType="org.sourcepit.common.modeling.EFile"
    * @generated
    */
   EList<File> getSourceDirectories();

   /**
    * Returns the value of the '<em><b>Binary Directories</b></em>' attribute list.
    * The list contents are of type {@link java.io.File}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Binary Directories</em>' attribute list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Binary Directories</em>' attribute list.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getExplodedModule_BinaryDirectories()
    * @model dataType="org.sourcepit.common.modeling.EFile"
    * @generated
    */
   EList<File> getBinaryDirectories();

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
    * @see #setJavaResources(JavaProject)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getExplodedModule_JavaResources()
    * @model containment="true" required="true"
    * @generated
    */
   JavaProject getJavaResources();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ExplodedModule#getJavaResources
    * <em>Java Resources</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Java Resources</em>' containment reference.
    * @see #getJavaResources()
    * @generated
    */
   void setJavaResources(JavaProject value);

} // ExplodedModule
