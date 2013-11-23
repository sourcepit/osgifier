/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds;

import org.sourcepit.common.modeling.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module Reference</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#getModuleRealm <em>Module Realm</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#getTargetModule <em>Target Module</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#isTransitive <em>Transitive</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#isOptional <em>Optional</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleReference()
 * @model
 * @generated
 */
public interface ModuleReference extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Module Realm</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.modularizor.moduleworlds.ModuleRealm#getReferencedModules <em>Referenced Modules</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module Realm</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module Realm</em>' container reference.
    * @see #setModuleRealm(ModuleRealm)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleReference_ModuleRealm()
    * @see org.sourcepit.modularizor.moduleworlds.ModuleRealm#getReferencedModules
    * @model opposite="referencedModules" required="true" transient="false"
    * @generated
    */
   ModuleRealm getModuleRealm();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#getModuleRealm
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
    * @see #setTargetModule(AbstractModule)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleReference_TargetModule()
    * @model required="true"
    * @generated
    */
   AbstractModule getTargetModule();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#getTargetModule
    * <em>Target Module</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Target Module</em>' reference.
    * @see #getTargetModule()
    * @generated
    */
   void setTargetModule(AbstractModule value);

   /**
    * Returns the value of the '<em><b>Transitive</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Transitive</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Transitive</em>' attribute.
    * @see #setTransitive(boolean)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleReference_Transitive()
    * @model
    * @generated
    */
   boolean isTransitive();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#isTransitive
    * <em>Transitive</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Transitive</em>' attribute.
    * @see #isTransitive()
    * @generated
    */
   void setTransitive(boolean value);

   /**
    * Returns the value of the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Optional</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Optional</em>' attribute.
    * @see #setOptional(boolean)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getModuleReference_Optional()
    * @model
    * @generated
    */
   boolean isOptional();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.ModuleReference#isOptional <em>Optional</em>}
    * ' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Optional</em>' attribute.
    * @see #isOptional()
    * @generated
    */
   void setOptional(boolean value);

} // ModuleReference
