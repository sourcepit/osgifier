/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.modeling.XAnnotatable;
import org.sourcepit.modularizor.java.JavaResourceBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Realms</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.modulerelams.ModuleRealms#getModules <em>Modules</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.ModuleRealms#getModuleRealms <em>Module Realms</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleRealms()
 * @model
 * @generated
 */
public interface ModuleRealms extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Modules</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.modularizor.java.JavaResourceBundle}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Modules</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Modules</em>' containment reference list.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleRealms_Modules()
    * @model containment="true"
    * @generated
    */
   EList<JavaResourceBundle> getModules();

   /**
    * Returns the value of the '<em><b>Module Realms</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.modularizor.modulerelams.ModuleRealm}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleRealms <em>Module Realms</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module Realms</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module Realms</em>' containment reference list.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleRealms_ModuleRealms()
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleRealms
    * @model opposite="moduleRealms" containment="true"
    * @generated
    */
   EList<ModuleRealm> getModuleRealms();

} // ModuleRealms
