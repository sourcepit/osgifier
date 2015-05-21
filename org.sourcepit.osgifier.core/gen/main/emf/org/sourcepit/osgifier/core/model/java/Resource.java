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

import org.sourcepit.common.modeling.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.Resource#getParentDirectory <em>Parent Directory</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getResource()
 * @model abstract="true"
 * @generated
 */
public interface Resource extends Named, XAnnotatable {
   /**
    * Returns the value of the '<em><b>Parent Directory</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifier.core.model.java.Directory#getResources
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
    * @see org.sourcepit.osgifier.core.model.java.JavaModelPackage#getResource_ParentDirectory()
    * @see org.sourcepit.osgifier.core.model.java.Directory#getResources
    * @model opposite="resources" transient="false"
    * @generated
    */
   Directory getParentDirectory();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.java.Resource#getParentDirectory
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
    * @model visitorType="org.sourcepit.osgifier.core.model.java.ResourceVisitor" visitorRequired="true"
    * @generated
    */
   void accept(ResourceVisitor visitor);

} // Resource
