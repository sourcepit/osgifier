/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.osgify.core.model.java.ImportDeclaration;
import org.sourcepit.osgify.core.model.java.JavaCompilationUnit;
import org.sourcepit.osgify.core.model.java.JavaModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sourcepit.osgify.core.model.java.impl.JavaCompilationUnitImpl#getImportDeclarations <em>Import Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaCompilationUnitImpl extends JavaFileImpl implements JavaCompilationUnit
{
   /**
    * The cached value of the '{@link #getImportDeclarations() <em>Import Declarations</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getImportDeclarations()
    * @generated
    * @ordered
    */
   protected EList<ImportDeclaration> importDeclarations;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected JavaCompilationUnitImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   protected EClass eStaticClass()
   {
      return JavaModelPackage.Literals.JAVA_COMPILATION_UNIT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<ImportDeclaration> getImportDeclarations()
   {
      if (importDeclarations == null)
      {
         importDeclarations = new EObjectContainmentWithInverseEList<ImportDeclaration>(ImportDeclaration.class, this, JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS, JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT);
      }
      return importDeclarations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getImportDeclarations()).basicAdd(otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS:
            return ((InternalEList<?>)getImportDeclarations()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS:
            return getImportDeclarations();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS:
            getImportDeclarations().clear();
            getImportDeclarations().addAll((Collection<? extends ImportDeclaration>)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS:
            getImportDeclarations().clear();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS:
            return importDeclarations != null && !importDeclarations.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // JavaCompilationUnitImpl
