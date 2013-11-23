/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.moduleworlds;

import java.io.File;

import org.sourcepit.common.modeling.XAnnotatable;
import org.sourcepit.modularizor.java.JavaResourceBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Module</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.AbstractModule#getModuleWorld <em>Module World</em>}</li>
 * <li>{@link org.sourcepit.modularizor.moduleworlds.AbstractModule#getSourceAttachment <em>Source Attachment</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getAbstractModule()
 * @model abstract="true"
 * @generated
 */
public interface AbstractModule extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Module World</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModules
    * <em>Modules</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Module World</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Module World</em>' container reference.
    * @see #setModuleWorld(ModuleWorld)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getAbstractModule_ModuleWorld()
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorld#getModules
    * @model opposite="modules" required="true" transient="false"
    * @generated
    */
   ModuleWorld getModuleWorld();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.AbstractModule#getModuleWorld
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
    * Returns the value of the '<em><b>Source Attachment</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Source Attachment</em>' attribute isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Source Attachment</em>' attribute.
    * @see #setSourceAttachment(File)
    * @see org.sourcepit.modularizor.moduleworlds.ModuleWorldsPackage#getAbstractModule_SourceAttachment()
    * @model dataType="org.sourcepit.common.modeling.EFile"
    * @generated
    */
   File getSourceAttachment();

   /**
    * Sets the value of the '{@link org.sourcepit.modularizor.moduleworlds.AbstractModule#getSourceAttachment
    * <em>Source Attachment</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Source Attachment</em>' attribute.
    * @see #getSourceAttachment()
    * @generated
    */
   void setSourceAttachment(File value);

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @model kind="operation" required="true"
    * @generated
    */
   JavaResourceBundle getJavaResources();

} // AbstractModule
