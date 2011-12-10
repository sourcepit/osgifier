/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.context.BundleNode#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.BundleNode#getDependencies <em>Dependencies</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.context.ContextModelPackage#getBundleNode()
 * @model
 * @generated
 */
public interface BundleNode extends AbstractBundleCoordinate
{
   /**
    * Returns the value of the '<em><b>Content</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Content</em>' containment reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Content</em>' containment reference.
    * @see #setContent(JavaPackageBundle)
    * @see org.sourcepit.osgify.context.ContextModelPackage#getBundleNode_Content()
    * @model containment="true" required="true"
    * @generated
    */
   JavaPackageBundle getContent();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.context.BundleNode#getContent <em>Content</em>}' containment
    * reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Content</em>' containment reference.
    * @see #getContent()
    * @generated
    */
   void setContent(JavaPackageBundle value);

   /**
    * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.context.BundleReference}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Dependencies</em>' containment reference list.
    * @see org.sourcepit.osgify.context.ContextModelPackage#getBundleNode_Dependencies()
    * @model containment="true"
    * @generated
    */
   EList<BundleReference> getDependencies();

} // BundleNode
