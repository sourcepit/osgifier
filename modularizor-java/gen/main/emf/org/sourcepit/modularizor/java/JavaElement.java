/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import org.sourcepit.common.modeling.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Element</b></em>'.
 * <!-- end-user-doc -->
 * 
 * 
 * @see org.sourcepit.modularizor.java.JavaModelPackage#getJavaElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface JavaElement extends Named, XAnnotatable
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation"
    * @generated
    */
   JavaResourceBundle getResourceBundle();

} // JavaElement
