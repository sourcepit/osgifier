/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.modeling.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Realm</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModuleWorld <em>Module World</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModule <em>Module</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getReferencedModules <em>Referenced Modules</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleRealm()
 * @model
 * @generated
 */
public interface ModuleRealm extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Module World</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModuleRealms <em>Module Realms</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module World</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module World</em>' container reference.
    * @see #setModuleWorld(ModuleWorld)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleRealm_ModuleWorld()
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModuleRealms
    * @model opposite="moduleRealms" required="true" transient="false"
    * @generated
    */
   ModuleWorld getModuleWorld();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModuleWorld
    * <em>Module World</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Module World</em>' container reference.
    * @see #getModuleWorld()
    * @generated
    */
   void setModuleWorld(ModuleWorld value);

   /**
    * Returns the value of the '<em><b>Module</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module</em>' reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module</em>' reference.
    * @see #setModule(AbstractModule)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleRealm_Module()
    * @model required="true"
    * @generated
    */
   AbstractModule getModule();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModule <em>Module</em>}'
    * reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Module</em>' reference.
    * @see #getModule()
    * @generated
    */
   void setModule(AbstractModule value);

   /**
    * Returns the value of the '<em><b>Referenced Modules</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.modularizor.moduleworlds.ModuleReference}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleReference#getModuleRealm <em>Module Realm</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Referenced Modules</em>' containment reference list isn't clear, there really should be
    * more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Referenced Modules</em>' containment reference list.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleRealm_ReferencedModules()
    * @see org.sourcepit.modularizor.moduleworlds.ModuleReference#getModuleRealm
    * @model opposite="moduleRealm" containment="true"
    * @generated
    */
   EList<ModuleReference> getReferencedModules();

} // ModuleRealm
