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
 * A representation of the model object '<em><b>Module World</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModules <em>Modules</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModuleRealms <em>Module Realms</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleWorld()
 * @model
 * @generated
 */
public interface ModuleWorld extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Modules</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.modularizor.moduleworlds.AbstractModule}.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.moduleworlds.AbstractModule#getModuleWorld <em>Module World</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Modules</em>' containment reference list isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Modules</em>' containment reference list.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleWorld_Modules()
    * @see org.sourcepit.modularizor.moduleworlds.AbstractModule#getModuleWorld
    * @model opposite="moduleWorld" containment="true"
    * @generated
    */
   EList<AbstractModule> getModules();

   /**
    * Returns the value of the '<em><b>Module Realms</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.modularizor.moduleworlds.ModuleRealm}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModuleWorld
    * <em>Module World</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module Realms</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module Realms</em>' containment reference list.
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleWorld_ModuleRealms()
    * @see org.sourcepit.modularizor.moduleworlds.ModuleRealm#getModuleWorld
    * @model opposite="moduleWorld" containment="true"
    * @generated
    */
   EList<ModuleRealm> getModuleRealms();

} // ModuleWorld
