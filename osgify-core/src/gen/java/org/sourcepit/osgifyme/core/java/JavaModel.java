/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.Annotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sourcepit.osgifyme.core.java.JavaModel#getProjects <em>Projects</em>}</li>
 *   <li>{@link org.sourcepit.osgifyme.core.java.JavaModel#getArchives <em>Archives</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaModel()
 * @model
 * @generated
 */
public interface JavaModel extends Annotatable
{
   /**
    * Returns the value of the '<em><b>Projects</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifyme.core.java.JavaProject}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Projects</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Projects</em>' containment reference list.
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaModel_Projects()
    * @model containment="true"
    * @generated
    */
   EList<JavaProject> getProjects();

   /**
    * Returns the value of the '<em><b>Archives</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifyme.core.java.JavaArchive}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Archives</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Archives</em>' containment reference list.
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaModel_Archives()
    * @model containment="true"
    * @generated
    */
   EList<JavaArchive> getArchives();

} // JavaModel
