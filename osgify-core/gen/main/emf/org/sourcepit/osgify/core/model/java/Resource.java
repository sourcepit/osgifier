/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import org.sourcepit.modeling.common.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.core.model.java.Resource#getParentDirectory <em>Parent Directory</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getResource()
 * @model abstract="true"
 * @generated
 */
public interface Resource extends Named, XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Parent Directory</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgify.core.model.java.Directory#getResources
    * <em>Resources</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parent Directory</em>' container reference isn't clear, there really should be more of
    * a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Parent Directory</em>' container reference.
    * @see #setParentDirectory(Directory)
    * @see org.sourcepit.osgify.core.model.java.JavaModelPackage#getResource_ParentDirectory()
    * @see org.sourcepit.osgify.core.model.java.Directory#getResources
    * @model opposite="resources" transient="false"
    * @generated
    */
   Directory getParentDirectory();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.java.Resource#getParentDirectory
    * <em>Parent Directory</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Parent Directory</em>' container reference.
    * @see #getParentDirectory()
    * @generated
    */
   void setParentDirectory(Directory value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model visitorType="org.sourcepit.osgify.core.model.java.ResourceVisitor" visitorRequired="true"
    * @generated
    */
   void accept(ResourceVisitor visitor);

} // Resource
