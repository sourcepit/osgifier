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
 * A representation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.ImportDeclaration#getCompilationUnit <em>Compilation Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getImportDeclaration()
 * @model
 * @generated
 */
public interface ImportDeclaration extends JavaElement
{
   /**
    * Returns the value of the '<em><b>Compilation Unit</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifier.core.model.java.JavaCompilationUnit#getImportDeclarations
    * <em>Import Declarations</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Compilation Unit</em>' container reference isn't clear, there really should be more of
    * a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Compilation Unit</em>' container reference.
    * @see #setCompilationUnit(JavaCompilationUnit)
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getImportDeclaration_CompilationUnit()
    * @see org.sourcepit.osgifier.core.model.java.JavaCompilationUnit#getImportDeclarations
    * @model opposite="importDeclarations" required="true"
    * @generated
    */
   JavaCompilationUnit getCompilationUnit();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.java.ImportDeclaration#getCompilationUnit
    * <em>Compilation Unit</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Compilation Unit</em>' container reference.
    * @see #getCompilationUnit()
    * @generated
    */
   void setCompilationUnit(JavaCompilationUnit value);

} // ImportDeclaration
