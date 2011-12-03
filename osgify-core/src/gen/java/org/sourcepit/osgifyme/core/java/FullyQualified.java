/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fully Qualified</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getFullyQualified()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface FullyQualified extends EObject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" required="true"
    * @generated
    */
   String getFullyQualifiedName();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @model kind="operation" required="true"
    * @generated
    */
   String getSimpleName();

} // FullyQualified
