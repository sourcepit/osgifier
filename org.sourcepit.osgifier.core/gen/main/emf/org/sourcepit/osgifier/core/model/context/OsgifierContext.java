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

package org.sourcepit.osgifier.core.model.context;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.modeling.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Osgifier Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.context.OsgifierContext#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getOsgifierContext()
 * @model
 * @generated
 */
public interface OsgifierContext extends XAnnotatable {
   /**
    * Returns the value of the '<em><b>Bundles</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifier.core.model.context.BundleCandidate}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Bundles</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Bundles</em>' containment reference list.
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getOsgifierContext_Bundles()
    * @model containment="true"
    * @generated
    */
   EList<BundleCandidate> getBundles();

} // OsgifierContext
