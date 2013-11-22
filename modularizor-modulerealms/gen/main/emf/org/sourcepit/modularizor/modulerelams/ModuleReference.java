/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.modulerelams;

import org.sourcepit.common.modeling.XAnnotatable;
import org.sourcepit.modularizor.java.JavaResourceBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Reference</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.modulerelams.ModuleReference#getModuleRealm <em>Module Realm</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.ModuleReference#getTargetModule <em>Target Module</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleReference()
 * @model
 * @generated
 */
public interface ModuleReference extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Module Realm</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleReferences <em>Module References</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module Realm</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module Realm</em>' container reference.
    * @see #setModuleRealm(ModuleRealm)
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleReference_ModuleRealm()
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleReferences
    * @model opposite="moduleReferences" required="true" transient="false"
    * @generated
    */
   ModuleRealm getModuleRealm();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.modulerelams.ModuleReference#getModuleRealm
    * <em>Module Realm</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Module Realm</em>' container reference.
    * @see #getModuleRealm()
    * @generated
    */
   void setModuleRealm(ModuleRealm value);

   /**
    * Returns the value of the '<em><b>Target Module</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Target Module</em>' reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Target Module</em>' reference.
    * @see #setTargetModule(JavaResourceBundle)
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleReference_TargetModule()
    * @model required="true"
    * @generated
    */
   JavaResourceBundle getTargetModule();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.modulerelams.ModuleReference#getTargetModule
    * <em>Target Module</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Target Module</em>' reference.
    * @see #getTargetModule()
    * @generated
    */
   void setTargetModule(JavaResourceBundle value);

} // ModuleReference
