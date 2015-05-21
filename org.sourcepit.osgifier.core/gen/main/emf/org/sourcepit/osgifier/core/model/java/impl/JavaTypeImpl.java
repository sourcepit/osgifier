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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.modeling.Annotatable;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.common.modeling.CommonModelingPackage;
import org.sourcepit.common.modeling.Extendable;
import org.sourcepit.common.modeling.XAnnotatable;
import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaModelPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaTypeImpl#getName <em>Name</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaTypeImpl#getExtensions <em>Extensions</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaTypeImpl#getAnnotations <em>Annotations</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaTypeImpl#getInnerTypes <em>Inner Types</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaTypeImpl#getOuterType <em>Outer Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaTypeImpl extends EObjectImpl implements JavaType {
   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getExtensions()
    * @generated
    * @ordered
    */
   protected EList<EObject> extensions;

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
    * The cached value of the '{@link #getInnerTypes() <em>Inner Types</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getInnerTypes()
    * @generated
    * @ordered
    */
   protected EList<JavaType> innerTypes;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaTypeImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   protected EClass eStaticClass() {
      return JavaModelPackage.Literals.JAVA_TYPE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getName() {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setName(String newName) {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_TYPE__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<EObject> getExtensions() {
      if (extensions == null) {
         extensions = new EObjectContainmentEList<EObject>(EObject.class, this, JavaModelPackage.JAVA_TYPE__EXTENSIONS);
      }
      return extensions;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<Annotation> getAnnotations() {
      if (annotations == null) {
         annotations = new EObjectContainmentWithInverseEList<Annotation>(Annotation.class, this,
            JavaModelPackage.JAVA_TYPE__ANNOTATIONS, CommonModelingPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaType> getInnerTypes() {
      if (innerTypes == null) {
         innerTypes = new EObjectContainmentWithInverseEList<JavaType>(JavaType.class, this,
            JavaModelPackage.JAVA_TYPE__INNER_TYPES, JavaModelPackage.JAVA_TYPE__OUTER_TYPE);
      }
      return innerTypes;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaType getOuterType() {
      if (eContainerFeatureID() != JavaModelPackage.JAVA_TYPE__OUTER_TYPE)
         return null;
      return (JavaType) eInternalContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetOuterType(JavaType newOuterType, NotificationChain msgs) {
      msgs = eBasicSetContainer((InternalEObject) newOuterType, JavaModelPackage.JAVA_TYPE__OUTER_TYPE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setOuterType(JavaType newOuterType) {
      if (newOuterType != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.JAVA_TYPE__OUTER_TYPE && newOuterType != null)) {
         if (EcoreUtil.isAncestor(this, newOuterType))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newOuterType != null)
            msgs = ((InternalEObject) newOuterType).eInverseAdd(this, JavaModelPackage.JAVA_TYPE__INNER_TYPES,
               JavaType.class, msgs);
         msgs = basicSetOuterType(newOuterType, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_TYPE__OUTER_TYPE, newOuterType,
            newOuterType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaFile getFile() {
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
   public String getQualifiedName() {
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
   public JavaResourceBundle getResourceBundle() {
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
   public Annotation getAnnotation(String source) {
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
   public Annotation getAnnotation(String source, boolean createOnDemand) {
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
   public String getAnnotationData(String source, String key) {
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
   public String setAnnotationData(String source, String key, String value) {
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
   public <T extends EObject> T getExtension(Class<T> extensionType) {
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
   public <T extends EObject> EList<T> getExtensions(Class<T> extensionType) {
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
   public <T extends EObject> void addExtension(T extension) {
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
   public <T extends EObject> void removeExtension(T extension) {
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
   public <T extends EObject> void removeExtensions(Class<T> extentionType) {
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
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID) {
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getInnerTypes()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetOuterType((JavaType) otherEnd, msgs);
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
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
      switch (featureID) {
         case JavaModelPackage.JAVA_TYPE__EXTENSIONS :
            return ((InternalEList<?>) getExtensions()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS :
            return ((InternalEList<?>) getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES :
            return ((InternalEList<?>) getInnerTypes()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE :
            return basicSetOuterType(null, msgs);
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
   public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
      switch (eContainerFeatureID()) {
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.JAVA_TYPE__INNER_TYPES, JavaType.class,
               msgs);
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
   public Object eGet(int featureID, boolean resolve, boolean coreType) {
      switch (featureID) {
         case JavaModelPackage.JAVA_TYPE__NAME :
            return getName();
         case JavaModelPackage.JAVA_TYPE__EXTENSIONS :
            return getExtensions();
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS :
            return getAnnotations();
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES :
            return getInnerTypes();
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE :
            return getOuterType();
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
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case JavaModelPackage.JAVA_TYPE__NAME :
            setName((String) newValue);
            return;
         case JavaModelPackage.JAVA_TYPE__EXTENSIONS :
            getExtensions().clear();
            getExtensions().addAll((Collection<? extends EObject>) newValue);
            return;
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS :
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>) newValue);
            return;
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES :
            getInnerTypes().clear();
            getInnerTypes().addAll((Collection<? extends JavaType>) newValue);
            return;
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE :
            setOuterType((JavaType) newValue);
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
   public void eUnset(int featureID) {
      switch (featureID) {
         case JavaModelPackage.JAVA_TYPE__NAME :
            setName(NAME_EDEFAULT);
            return;
         case JavaModelPackage.JAVA_TYPE__EXTENSIONS :
            getExtensions().clear();
            return;
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS :
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES :
            getInnerTypes().clear();
            return;
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE :
            setOuterType((JavaType) null);
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
   public boolean eIsSet(int featureID) {
      switch (featureID) {
         case JavaModelPackage.JAVA_TYPE__NAME :
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case JavaModelPackage.JAVA_TYPE__EXTENSIONS :
            return extensions != null && !extensions.isEmpty();
         case JavaModelPackage.JAVA_TYPE__ANNOTATIONS :
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_TYPE__INNER_TYPES :
            return innerTypes != null && !innerTypes.isEmpty();
         case JavaModelPackage.JAVA_TYPE__OUTER_TYPE :
            return getOuterType() != null;
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
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
      if (baseClass == Extendable.class) {
         switch (derivedFeatureID) {
            case JavaModelPackage.JAVA_TYPE__EXTENSIONS :
               return CommonModelingPackage.EXTENDABLE__EXTENSIONS;
            default :
               return -1;
         }
      }
      if (baseClass == Annotatable.class) {
         switch (derivedFeatureID) {
            case JavaModelPackage.JAVA_TYPE__ANNOTATIONS :
               return CommonModelingPackage.ANNOTATABLE__ANNOTATIONS;
            default :
               return -1;
         }
      }
      if (baseClass == XAnnotatable.class) {
         switch (derivedFeatureID) {
            default :
               return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
      if (baseClass == Extendable.class) {
         switch (baseFeatureID) {
            case CommonModelingPackage.EXTENDABLE__EXTENSIONS :
               return JavaModelPackage.JAVA_TYPE__EXTENSIONS;
            default :
               return -1;
         }
      }
      if (baseClass == Annotatable.class) {
         switch (baseFeatureID) {
            case CommonModelingPackage.ANNOTATABLE__ANNOTATIONS :
               return JavaModelPackage.JAVA_TYPE__ANNOTATIONS;
            default :
               return -1;
         }
      }
      if (baseClass == XAnnotatable.class) {
         switch (baseFeatureID) {
            default :
               return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String toString() {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (name: ");
      result.append(name);
      result.append(')');
      return result.toString();
   }

} // JavaTypeImpl
