/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context;

import org.eclipse.emf.ecore.EObject;
import org.sourcepit.common.manifest.osgi.Version;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Bundle Coordinate</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.context.AbstractBundleCoordinate#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.AbstractBundleCoordinate#getSymbolicName <em>Symbolic Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.context.ContextModelPackage#getAbstractBundleCoordinate()
 * @model abstract="true"
 * @generated
 */
public interface AbstractBundleCoordinate extends EObject
{
   /**
    * Returns the value of the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Version</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Version</em>' attribute.
    * @see #setVersion(Version)
    * @see org.sourcepit.osgify.context.ContextModelPackage#getAbstractBundleCoordinate_Version()
    * @model dataType="org.sourcepit.common.manifest.osgi.Version"
    * @generated
    */
   Version getVersion();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.context.AbstractBundleCoordinate#getVersion <em>Version</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Version</em>' attribute.
    * @see #getVersion()
    * @generated
    */
   void setVersion(Version value);

   /**
    * Returns the value of the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Symbolic Name</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Symbolic Name</em>' attribute.
    * @see #setSymbolicName(String)
    * @see org.sourcepit.osgify.context.ContextModelPackage#getAbstractBundleCoordinate_SymbolicName()
    * @model
    * @generated
    */
   String getSymbolicName();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.context.AbstractBundleCoordinate#getSymbolicName
    * <em>Symbolic Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Symbolic Name</em>' attribute.
    * @see #getSymbolicName()
    * @generated
    */
   void setSymbolicName(String value);

} // AbstractBundleCoordinate
