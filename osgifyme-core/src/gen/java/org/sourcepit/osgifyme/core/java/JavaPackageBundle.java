/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.sourcepit.modeling.common.Annotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Package Bundle</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.JavaPackageBundle#getPathToRootPackagesMap <em>Path To Root Packages Map
 * </em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.JavaPackageBundle#getDependencies <em>Dependencies</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaPackageBundle()
 * @model abstract="true"
 * @generated
 */
public interface JavaPackageBundle extends Annotatable
{
   /**
    * Returns the value of the '<em><b>Path To Root Packages Map</b></em>' map.
    * The key is of type {@link java.lang.String},
    * and the value is of type list of {@link org.sourcepit.osgifyme.core.java.JavaPackage},
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Path To Root Packages Map</em>' map isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Path To Root Packages Map</em>' map.
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaPackageBundle_PathToRootPackagesMap()
    * @model mapType=
    *        "org.sourcepit.osgifyme.core.java.JavaPackageMapEntry<org.eclipse.emf.ecore.EString, org.sourcepit.osgifyme.core.java.JavaPackage>"
    * @generated
    */
   EMap<String, EList<JavaPackage>> getPathToRootPackagesMap();

   /**
    * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifyme.core.java.DependencyNode}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getPackageBundle
    * <em>Package Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Dependencies</em>' containment reference list.
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getJavaPackageBundle_Dependencies()
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getPackageBundle
    * @model opposite="packageBundle" containment="true"
    * @generated
    */
   EList<DependencyNode> getDependencies();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   EList<JavaPackage> getRootPackages();

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaPackage getPackage(String path, String fullyQualifiedName, boolean createOnDemand);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model
    * @generated
    */
   JavaType getType(String path, String packageName, String typeName, boolean createOnDemand);

} // JavaPackageBundle
