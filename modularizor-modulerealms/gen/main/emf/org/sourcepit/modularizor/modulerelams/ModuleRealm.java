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
 * A representation of the model object '<em><b>Module Realm</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleRealms <em>Module Realms</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getRealmModule <em>Realm Module</em>}</li>
 * <li>{@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleReferences <em>Module References</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleRealm()
 * @model
 * @generated
 */
public interface ModuleRealm extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Module Realms</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleRealms#getModuleRealms <em>Module Realms</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module Realms</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module Realms</em>' container reference.
    * @see #setModuleRealms(ModuleRealms)
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleRealm_ModuleRealms()
    * @see org.sourcepit.modularizor.modulerelams.ModuleRealms#getModuleRealms
    * @model opposite="moduleRealms" required="true" transient="false"
    * @generated
    */
   ModuleRealms getModuleRealms();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getModuleRealms
    * <em>Module Realms</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Module Realms</em>' container reference.
    * @see #getModuleRealms()
    * @generated
    */
   void setModuleRealms(ModuleRealms value);

   /**
    * Returns the value of the '<em><b>Realm Module</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Realm Module</em>' reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Realm Module</em>' reference.
    * @see #setRealmModule(JavaResourceBundle)
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleRealm_RealmModule()
    * @model required="true"
    * @generated
    */
   JavaResourceBundle getRealmModule();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.modulerelams.ModuleRealm#getRealmModule
    * <em>Realm Module</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Realm Module</em>' reference.
    * @see #getRealmModule()
    * @generated
    */
   void setRealmModule(JavaResourceBundle value);

   /**
    * Returns the value of the '<em><b>Module References</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.modularizor.modulerelams.ModuleReference}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.modulerelams.ModuleReference#getModuleRealm <em>Module Realm</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module References</em>' containment reference list isn't clear, there really should be
    * more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module References</em>' containment reference list.
    * @see org.sourcepit.modularizor.modulerelams.ModuleRelamsPackage#getModuleRealm_ModuleReferences()
    * @see org.sourcepit.modularizor.modulerelams.ModuleReference#getModuleRealm
    * @model opposite="moduleRealm" containment="true"
    * @generated
    */
   EList<ModuleReference> getModuleReferences();

} // ModuleRealm
