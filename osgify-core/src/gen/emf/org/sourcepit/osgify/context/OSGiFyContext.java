/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>OS Gi Fy Context</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.context.OSGiFyContext#getBundles <em>Bundles</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.context.ContextModelPackage#getOSGiFyContext()
 * @model
 * @generated
 */
public interface OSGiFyContext extends EObject
{
   /**
    * Returns the value of the '<em><b>Bundles</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.context.BundleNode}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Bundles</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Bundles</em>' containment reference list.
    * @see org.sourcepit.osgify.context.ContextModelPackage#getOSGiFyContext_Bundles()
    * @model containment="true"
    * @generated
    */
   EList<BundleNode> getBundles();

} // OSGiFyContext
