/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.java.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.modeling.common.impl.XAnnotatableImpl;
import org.sourcepit.osgify.java.ImportDeclaration;
import org.sourcepit.osgify.java.JavaCompilationUnit;
import org.sourcepit.osgify.java.JavaModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.java.impl.ImportDeclarationImpl#getCompilationUnit <em>Compilation Unit</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ImportDeclarationImpl extends XAnnotatableImpl implements ImportDeclaration
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ImportDeclarationImpl()
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
      return JavaModelPackage.Literals.IMPORT_DECLARATION;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaCompilationUnit getCompilationUnit()
   {
      if (eContainerFeatureID() != JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT)
         return null;
      return (JavaCompilationUnit) eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetCompilationUnit(JavaCompilationUnit newCompilationUnit, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newCompilationUnit,
         JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setCompilationUnit(JavaCompilationUnit newCompilationUnit)
   {
      if (newCompilationUnit != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT && newCompilationUnit != null))
      {
         if (EcoreUtil.isAncestor(this, newCompilationUnit))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newCompilationUnit != null)
            msgs = ((InternalEObject) newCompilationUnit).eInverseAdd(this,
               JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS, JavaCompilationUnit.class, msgs);
         msgs = basicSetCompilationUnit(newCompilationUnit, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT,
            newCompilationUnit, newCompilationUnit));
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
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetCompilationUnit((JavaCompilationUnit) otherEnd, msgs);
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
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return basicSetCompilationUnit(null, msgs);
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
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
   {
      switch (eContainerFeatureID())
      {
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return eInternalContainer().eInverseRemove(this,
               JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS, JavaCompilationUnit.class, msgs);
      }
      return super.eBasicRemoveFromContainerFeature(msgs);
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
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return getCompilationUnit();
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
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            setCompilationUnit((JavaCompilationUnit) newValue);
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
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            setCompilationUnit((JavaCompilationUnit) null);
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
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return getCompilationUnit() != null;
      }
      return super.eIsSet(featureID);
   }

} // ImportDeclarationImpl
