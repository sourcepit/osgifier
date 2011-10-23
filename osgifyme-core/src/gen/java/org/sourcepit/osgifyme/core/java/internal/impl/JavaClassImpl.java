/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgifyme.core.java.JavaClass;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaClassImpl#getAnnotations <em>Annotations</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaClassImpl#getType <em>Type</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaClassImpl#getParentPackage <em>Parent Package</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JavaClassImpl extends EObjectImpl implements JavaClass
{
   /**
    * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getAnnotations()
    * @generated
    * @ordered
    */
   protected EList<Annotation> annotations;

   /**
    * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getType()
    * @generated
    * @ordered
    */
   protected JavaType type;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaClassImpl()
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
      return JavaModelPackage.Literals.JAVA_CLASS;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<Annotation> getAnnotations()
   {
      if (annotations == null)
      {
         annotations = new EObjectContainmentWithInverseEList<Annotation>(Annotation.class, this,
            JavaModelPackage.JAVA_CLASS__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaType getType()
   {
      return type;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetType(JavaType newType, NotificationChain msgs)
   {
      JavaType oldType = type;
      type = newType;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            JavaModelPackage.JAVA_CLASS__TYPE, oldType, newType);
         if (msgs == null)
            msgs = notification;
         else
            msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setType(JavaType newType)
   {
      if (newType != type)
      {
         NotificationChain msgs = null;
         if (type != null)
            msgs = ((InternalEObject) type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
               - JavaModelPackage.JAVA_CLASS__TYPE, null, msgs);
         if (newType != null)
            msgs = ((InternalEObject) newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - JavaModelPackage.JAVA_CLASS__TYPE, null, msgs);
         msgs = basicSetType(newType, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_CLASS__TYPE, newType, newType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackage getParentPackage()
   {
      if (eContainerFeatureID() != JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE)
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
      msgs = eBasicSetContainer((InternalEObject) newParentPackage, JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE, msgs);
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
         || (eContainerFeatureID() != JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE && newParentPackage != null))
      {
         if (EcoreUtil.isAncestor(this, newParentPackage))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newParentPackage != null)
            msgs = ((InternalEObject) newParentPackage).eInverseAdd(this, JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS,
               JavaPackage.class, msgs);
         msgs = basicSetParentPackage(newParentPackage, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE,
            newParentPackage, newParentPackage));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Annotation getAnnotation(String source)
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
   public Annotation getAnnotation(String source, boolean createOnDemand)
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
   public String getAnnotationData(String source, String key)
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
         case JavaModelPackage.JAVA_CLASS__ANNOTATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE :
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
         case JavaModelPackage.JAVA_CLASS__ANNOTATIONS :
            return ((InternalEList<?>) getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_CLASS__TYPE :
            return basicSetType(null, msgs);
         case JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE :
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
         case JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.JAVA_PACKAGE__TYPE_ROOTS,
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
         case JavaModelPackage.JAVA_CLASS__ANNOTATIONS :
            return getAnnotations();
         case JavaModelPackage.JAVA_CLASS__TYPE :
            return getType();
         case JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE :
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
         case JavaModelPackage.JAVA_CLASS__ANNOTATIONS :
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>) newValue);
            return;
         case JavaModelPackage.JAVA_CLASS__TYPE :
            setType((JavaType) newValue);
            return;
         case JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE :
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
         case JavaModelPackage.JAVA_CLASS__ANNOTATIONS :
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_CLASS__TYPE :
            setType((JavaType) null);
            return;
         case JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE :
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
         case JavaModelPackage.JAVA_CLASS__ANNOTATIONS :
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_CLASS__TYPE :
            return type != null;
         case JavaModelPackage.JAVA_CLASS__PARENT_PACKAGE :
            return getParentPackage() != null;
      }
      return super.eIsSet(featureID);
   }

} // JavaClassImpl
