/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.sourcepit.modeling.common.Annotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sourcepit.osgifyme.core.java.ImportDeclaration#getCompilationUnit <em>Compilation Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getImportDeclaration()
 * @model
 * @generated
 */
public interface ImportDeclaration extends Annotatable
{
   /**
    * Returns the value of the '<em><b>Compilation Unit</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifyme.core.java.JavaCompilationUnit#getImportDeclarations <em>Import Declarations</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Compilation Unit</em>' container reference isn't clear, there really should be more of
    * a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Compilation Unit</em>' container reference.
    * @see #setCompilationUnit(JavaCompilationUnit)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getImportDeclaration_CompilationUnit()
    * @see org.sourcepit.osgifyme.core.java.JavaCompilationUnit#getImportDeclarations
    * @model opposite="importDeclarations" required="true"
    * @generated
    */
   JavaCompilationUnit getCompilationUnit();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.ImportDeclaration#getCompilationUnit <em>Compilation Unit</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Compilation Unit</em>' container reference.
    * @see #getCompilationUnit()
    * @generated
    */
   void setCompilationUnit(JavaCompilationUnit value);

} // ImportDeclaration
