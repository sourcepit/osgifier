/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.model.java.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.osgifier.core.model.java.ImportDeclaration;
import org.sourcepit.osgifier.core.model.java.JavaCompilationUnit;
import org.sourcepit.osgifier.core.model.java.JavaModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Compilation Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaCompilationUnitImpl#getImportDeclarations <em>Import
 * Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaCompilationUnitImpl extends JavaFileImpl implements JavaCompilationUnit
{
   /**
    * The cached value of the '{@link #getImportDeclarations() <em>Import Declarations</em>}' containment reference
    * list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getImportDeclarations()
    * @generated
    * @ordered
    */
   protected EList<ImportDeclaration> importDeclarations;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaCompilationUnitImpl()
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
      return JavaModelPackage.Literals.JAVA_COMPILATION_UNIT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<ImportDeclaration> getImportDeclarations()
   {
      if (importDeclarations == null)
      {
         importDeclarations = new EObjectContainmentWithInverseEList<ImportDeclaration>(ImportDeclaration.class, this,
            JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS,
            JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT);
      }
      return importDeclarations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getImportDeclarations()).basicAdd(otherEnd,
               msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS :
            return ((InternalEList<?>) getImportDeclarations()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS :
            return getImportDeclarations();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS :
            getImportDeclarations().clear();
            getImportDeclarations().addAll((Collection<? extends ImportDeclaration>) newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS :
            getImportDeclarations().clear();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS :
            return importDeclarations != null && !importDeclarations.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // JavaCompilationUnitImpl
