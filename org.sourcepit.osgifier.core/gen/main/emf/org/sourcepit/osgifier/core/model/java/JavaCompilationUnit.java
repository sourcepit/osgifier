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
 * A representation of the model object '<em><b>Java Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.JavaCompilationUnit#getImportDeclarations <em>Import Declarations
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaCompilationUnit()
 * @model
 * @generated
 */
public interface JavaCompilationUnit extends JavaFile
{
   /**
    * Returns the value of the '<em><b>Import Declarations</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifier.core.model.java.ImportDeclaration}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifier.core.model.java.ImportDeclaration#getCompilationUnit <em>Compilation Unit</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Import Declarations</em>' containment reference list isn't clear, there really should
    * be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Import Declarations</em>' containment reference list.
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getJavaCompilationUnit_ImportDeclarations()
    * @see org.sourcepit.osgifier.core.model.java.ImportDeclaration#getCompilationUnit
    * @model opposite="compilationUnit" containment="true"
    * @generated
    */
   EList<ImportDeclaration> getImportDeclarations();

} // JavaCompilationUnit
