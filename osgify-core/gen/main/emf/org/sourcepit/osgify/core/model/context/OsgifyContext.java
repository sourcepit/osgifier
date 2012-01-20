/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.context;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.XAnnotatable;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Osgify Context</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.core.model.context.OsgifyContext#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getOsgifyContext()
 * @model
 * @generated
 */
public interface OsgifyContext extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Bundles</b></em>' containment reference list. The list contents are of type
    * {@link org.sourcepit.osgify.core.model.context.BundleCandidate}. <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Bundles</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Bundles</em>' containment reference list.
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getOsgifyContext_Bundles()
    * @model containment="true"
    * @generated
    */
   EList<BundleCandidate> getBundles();

} // OsgifyContext
