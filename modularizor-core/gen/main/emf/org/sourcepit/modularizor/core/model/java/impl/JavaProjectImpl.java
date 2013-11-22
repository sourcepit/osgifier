/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java.impl;

import org.eclipse.emf.ecore.EClass;
import org.sourcepit.modularizor.core.model.java.JavaModelPackage;
import org.sourcepit.modularizor.core.model.java.JavaProject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Project</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class JavaProjectImpl extends JavaResourceBundleImpl implements JavaProject
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaProjectImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return JavaModelPackage.Literals.JAVA_PROJECT;
   }

} // JavaProjectImpl
