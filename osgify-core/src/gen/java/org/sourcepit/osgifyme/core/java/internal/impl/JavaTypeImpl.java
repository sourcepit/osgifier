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
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.osgifyme.core.java.JavaTypeRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl#getInnerTypes <em>Inner Types</em>}</li>
 *   <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl#getOuterType <em>Outer Type</em>}</li>
 *   <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaTypeImpl#getSimpleName <em>Simple Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaTypeImpl extends EObjectImpl implements JavaType
{
   /**
    * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getAnnotations()
    * @generated
    * @ordered
    */
   protected EList<Annotation> annotations;

   /**
    * The cached value of the '{@link #getInnerTypes() <em>Inner Types</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getInnerTypes()
    * @generated
    * @ordered
    */
   protected EList<JavaType> innerTypes;

   /**
    * The default value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSimpleName()
    * @generated
    * @ordered
    */
   protected static final String SIMPLE_NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getSimpleName() <em>Simple Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getSimpleName()
    * @generated
    * @ordered
    */
   protected String simpleName = SIMPLE_NAME_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected JavaTypeImpl()
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
      return JavaModelPackage.Literals.JAVA_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<Annotation> getAnnotations()
   {
      if (annotations == null)
      {
         annotations = new EObjectContainmentWithInverseEList<Annotation>(Annotation.class, this, JavaModelPackage.JAVA_TYPE__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<JavaType> getInnerTypes()
   {
      if (innerTypes == null)
      {
         innerTypes = new EObjectContainmentWithInverseEList<JavaType>(JavaType.class, this, JavaModelPackage.JAVA_TYPE__INNER_TYPES, JavaModelPackage.JAVA_TYPE__OUTER_TYPE);
      }
      return innerTypes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public JavaType getOuterType()
   {
      if (eContainerFeatureID() != JavaModelPackage.JAVA_TYPE__OUTER_TYPE) return null;
      return (JavaType)eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetOuterType(JavaType newOuterType, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject)newOuterType, JavaModelPackage.JAVA_TYPE__OUTER_TYPE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setOuterType(JavaType newOuterType)
   {
      if (newOuterType != eInternalContainer() || (eContainerFeatureID() != JavaModelPackage.JAVA_TYPE__OUTER_TYPE && newOuterType != null))
      {
         if (EcoreUtil.isAncestor(this, newOuterType))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newOuterType != null)
            msgs = ((InternalEObject)newOuterType).eInverseAdd(this, JavaModelPackage.JAVA_TYPE__INNER_TYPES, JavaType.class, msgs);
         msgs = basicSetOuterType(newOuterType, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_TYPE__OUTER_TYPE, newOuterType, newOuterType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getSimpleName()
   {
      return simpleName;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setSimpleName(String newSimpleName)
   {
      String oldSimpleName = simpleName;
      simpleName = newSimpleName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_TYPE__SIMPLE_NAME, oldSimpleName, simpleName));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public JavaTypeRoot getTypeRoot()
   {
      // TODO: implement this method
      // Ensure that you remove @generated or mark it @generated NOT
      throw new UnsupportedOperationException();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
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
    * @generated
    */
   @SuppressWarnings("unchecked")
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getInnerTypes()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE:
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetOuterType((JavaType)otherEnd, msgs);
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
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS:
            return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES:
            return ((InternalEList<?>)getInnerTypes()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE:
            return basicSetOuterType(null, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
   {
      switch (eContainerFeatureID())
      {
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE:
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.JAVA_TYPE__INNER_TYPES, JavaType.class, msgs);
      }
      return super.eBasicRemoveFromContainerFeature(msgs);
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
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS:
            return getAnnotations();
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES:
            return getInnerTypes();
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE:
            return getOuterType();
         case JavaModelPackage.JAVA_TYPE__SIMPLE_NAME:
            return getSimpleName();
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
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS:
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>)newValue);
            return;
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES:
            getInnerTypes().clear();
            getInnerTypes().addAll((Collection<? extends JavaType>)newValue);
            return;
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE:
            setOuterType((JavaType)newValue);
            return;
         case JavaModelPackage.JAVA_TYPE__SIMPLE_NAME:
            setSimpleName((String)newValue);
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
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS:
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES:
            getInnerTypes().clear();
            return;
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE:
            setOuterType((JavaType)null);
            return;
         case JavaModelPackage.JAVA_TYPE__SIMPLE_NAME:
            setSimpleName(SIMPLE_NAME_EDEFAULT);
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
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS:
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES:
            return innerTypes != null && !innerTypes.isEmpty();
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE:
            return getOuterType() != null;
         case JavaModelPackage.JAVA_TYPE__SIMPLE_NAME:
            return SIMPLE_NAME_EDEFAULT == null ? simpleName != null : !SIMPLE_NAME_EDEFAULT.equals(simpleName);
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (simpleName: ");
      result.append(simpleName);
      result.append(')');
      return result.toString();
   }

} // JavaTypeImpl
