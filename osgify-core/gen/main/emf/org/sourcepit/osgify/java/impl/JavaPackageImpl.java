/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.java.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.modeling.common.impl.XAnnotatableImpl;
import org.sourcepit.osgify.java.JavaModelPackage;
import org.sourcepit.osgify.java.JavaPackage;
import org.sourcepit.osgify.java.JavaPackageBundle;
import org.sourcepit.osgify.java.JavaPackageRoot;
import org.sourcepit.osgify.java.JavaTypeRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageImpl#getTypeRoots <em>Type Roots</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageImpl#getSimpleName <em>Simple Name</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageImpl#getPackages <em>Packages</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageImpl#getParentPackage <em>Parent Package</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JavaPackageImpl extends XAnnotatableImpl implements JavaPackage
{
   /**
    * The cached value of the '{@link #getTypeRoots() <em>Type Roots</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTypeRoots()
    * @generated
    * @ordered
    */
   protected EList<JavaTypeRoot> typeRoots;

   /**
    * The default value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSimpleName()
    * @generated
    * @ordered
    */
   protected static final String SIMPLE_NAME_EDEFAULT = "";

   /**
    * The cached value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSimpleName()
    * @generated
    * @ordered
    */
   protected String simpleName = SIMPLE_NAME_EDEFAULT;

   /**
    * The cached value of the '{@link #getPackages() <em>Packages</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getPackages()
    * @generated
    * @ordered
    */
   protected EList<JavaPackage> packages;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaPackageImpl()
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
      return JavaModelPackage.Literals.JAVA_PACKAGE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaTypeRoot> getTypeRoots()
   {
      if (typeRoots == null)
      {
         typeRoots = new EObjectContainmentWithInverseEList<JavaTypeRoot>(JavaTypeRoot.class, this,
            JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS, JavaModelPackage.JAVA_TYPE_ROOT__PARENT_PACKAGE);
      }
      return typeRoots;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getSimpleName()
   {
      return simpleName;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setSimpleName(String newSimpleName)
   {
      String oldSimpleName = simpleName;
      simpleName = newSimpleName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_PACKAGE__SIMPLE_NAME,
            oldSimpleName, simpleName));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaPackage> getPackages()
   {
      if (packages == null)
      {
         packages = new EObjectContainmentWithInverseEList<JavaPackage>(JavaPackage.class, this,
            JavaModelPackage.JAVA_PACKAGE__PACKAGES, JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE);
      }
      return packages;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackage getParentPackage()
   {
      if (eContainerFeatureID() != JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE)
         return null;
      return (JavaPackage) eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetParentPackage(JavaPackage newParentPackage, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newParentPackage, JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setParentPackage(JavaPackage newParentPackage)
   {
      if (newParentPackage != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE && newParentPackage != null))
      {
         if (EcoreUtil.isAncestor(this, newParentPackage))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newParentPackage != null)
            msgs = ((InternalEObject) newParentPackage).eInverseAdd(this, JavaModelPackage.JAVA_PACKAGE__PACKAGES,
               JavaPackage.class, msgs);
         msgs = basicSetParentPackage(newParentPackage, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE,
            newParentPackage, newParentPackage));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackage getSubPackage(String name, boolean createOnDemand)
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageBundle getPackageBundle()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageRoot getPackageRoot()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getFullyQualifiedName()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
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
         case JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getTypeRoots()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE__PACKAGES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getPackages()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetParentPackage((JavaPackage) otherEnd, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS :
            return ((InternalEList<?>) getTypeRoots()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE__PACKAGES :
            return ((InternalEList<?>) getPackages()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE :
            return basicSetParentPackage(null, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.JAVA_PACKAGE__PACKAGES,
               JavaPackage.class, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS :
            return getTypeRoots();
         case JavaModelPackage.JAVA_PACKAGE__SIMPLE_NAME :
            return getSimpleName();
         case JavaModelPackage.JAVA_PACKAGE__PACKAGES :
            return getPackages();
         case JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE :
            return getParentPackage();
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
         case JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS :
            getTypeRoots().clear();
            getTypeRoots().addAll((Collection<? extends JavaTypeRoot>) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE__SIMPLE_NAME :
            setSimpleName((String) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE__PACKAGES :
            getPackages().clear();
            getPackages().addAll((Collection<? extends JavaPackage>) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE :
            setParentPackage((JavaPackage) newValue);
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
         case JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS :
            getTypeRoots().clear();
            return;
         case JavaModelPackage.JAVA_PACKAGE__SIMPLE_NAME :
            setSimpleName(SIMPLE_NAME_EDEFAULT);
            return;
         case JavaModelPackage.JAVA_PACKAGE__PACKAGES :
            getPackages().clear();
            return;
         case JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE :
            setParentPackage((JavaPackage) null);
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
         case JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS :
            return typeRoots != null && !typeRoots.isEmpty();
         case JavaModelPackage.JAVA_PACKAGE__SIMPLE_NAME :
            return SIMPLE_NAME_EDEFAULT == null ? simpleName != null : !SIMPLE_NAME_EDEFAULT.equals(simpleName);
         case JavaModelPackage.JAVA_PACKAGE__PACKAGES :
            return packages != null && !packages.isEmpty();
         case JavaModelPackage.JAVA_PACKAGE__PARENT_PACKAGE :
            return getParentPackage() != null;
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (simpleName: ");
      result.append(simpleName);
      result.append(')');
      return result.toString();
   }

} // JavaPackageImpl
