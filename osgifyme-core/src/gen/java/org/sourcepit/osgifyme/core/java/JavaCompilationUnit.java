/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.JavaCompilationUnit#getImportDeclarations <em>Import Declarations</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaCompilationUnit()
 * @model
 * @generated
 */
public interface JavaCompilationUnit extends JavaTypeRoot
{
   /**
    * Returns the value of the '<em><b>Import Declarations</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifyme.core.java.ImportDeclaration}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifyme.core.java.ImportDeclaration#getCompilationUnit <em>Compilation Unit</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Import Declarations</em>' containment reference list isn't clear, there really should
    * be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Import Declarations</em>' containment reference list.
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaCompilationUnit_ImportDeclarations()
    * @see org.sourcepit.osgifyme.core.java.ImportDeclaration#getCompilationUnit
    * @model opposite="compilationUnit" containment="true"
    * @generated
    */
   EList<ImportDeclaration> getImportDeclarations();

} // JavaCompilationUnit
