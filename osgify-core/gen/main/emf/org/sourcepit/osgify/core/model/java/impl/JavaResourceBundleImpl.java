/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java.impl;

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
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.modeling.common.Annotatable;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.modeling.common.Extendable;
import org.sourcepit.modeling.common.XAnnotatable;
import org.sourcepit.osgify.core.model.java.JavaModelPackage;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Resource Bundle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sourcepit.osgify.core.model.java.impl.JavaResourceBundleImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.sourcepit.osgify.core.model.java.impl.JavaResourceBundleImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.sourcepit.osgify.core.model.java.impl.JavaResourceBundleImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.sourcepit.osgify.core.model.java.impl.JavaResourceBundleImpl#getResourcesRoots <em>Resources Roots</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JavaResourceBundleImpl extends EObjectImpl implements JavaResourceBundle
{
   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * The cached value of the '{@link #getExtensions() <em>Extensions</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getExtensions()
    * @generated
    * @ordered
    */
   protected EList<EObject> extensions;

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
    * The cached value of the '{@link #getResourcesRoots() <em>Resources Roots</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getResourcesRoots()
    * @generated
    * @ordered
    */
   protected EList<JavaResourcesRoot> resourcesRoots;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected JavaResourceBundleImpl()
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
      return JavaModelPackage.Literals.JAVA_RESOURCE_BUNDLE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setName(String newName)
   {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_RESOURCE_BUNDLE__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<EObject> getExtensions()
   {
      if (extensions == null)
      {
         extensions = new EObjectContainmentEList<EObject>(EObject.class, this, JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS);
      }
      return extensions;
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
         annotations = new EObjectContainmentWithInverseEList<Annotation>(Annotation.class, this, JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<JavaResourcesRoot> getResourcesRoots()
   {
      if (resourcesRoots == null)
      {
         resourcesRoots = new EObjectContainmentWithInverseEList<JavaResourcesRoot>(JavaResourcesRoot.class, this, JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS, JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE);
      }
      return resourcesRoots;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public JavaResourcesRoot getResourcesRoot(String name)
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
   public JavaResourcesRoot getResourcesRoot(String name, boolean createOnDemand)
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
   public JavaPackage getPackage(String rootName, String qualifiedPackageName, boolean createOnDemand)
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
   public JavaType getType(String rootName, String qualifiedPackageName, String typeName, boolean createOnDemand)
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
   public JavaResourceBundle getResourceBundle()
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
   public <T extends EObject> T getExtension(Class<T> extensionType)
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
   public <T extends EObject> EList<T> getExtensions(Class<T> extensionType)
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
   public <T extends EObject> void addExtension(T extension)
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
   public <T extends EObject> void removeExtension(T extension)
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
   public <T extends EObject> void removeExtensions(Class<T> extentionType)
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
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getResourcesRoots()).basicAdd(otherEnd, msgs);
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
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS:
            return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS:
            return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS:
            return ((InternalEList<?>)getResourcesRoots()).basicRemove(otherEnd, msgs);
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
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__NAME:
            return getName();
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS:
            return getExtensions();
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS:
            return getAnnotations();
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS:
            return getResourcesRoots();
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
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__NAME:
            setName((String)newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS:
            getExtensions().clear();
            getExtensions().addAll((Collection<? extends EObject>)newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS:
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>)newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS:
            getResourcesRoots().clear();
            getResourcesRoots().addAll((Collection<? extends JavaResourcesRoot>)newValue);
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
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__NAME:
            setName(NAME_EDEFAULT);
            return;
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS:
            getExtensions().clear();
            return;
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS:
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS:
            getResourcesRoots().clear();
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
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS:
            return extensions != null && !extensions.isEmpty();
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS:
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS:
            return resourcesRoots != null && !resourcesRoots.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == Extendable.class)
      {
         switch (derivedFeatureID)
         {
            case JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS: return CommonModelPackage.EXTENDABLE__EXTENSIONS;
            default: return -1;
         }
      }
      if (baseClass == Annotatable.class)
      {
         switch (derivedFeatureID)
         {
            case JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS: return CommonModelPackage.ANNOTATABLE__ANNOTATIONS;
            default: return -1;
         }
      }
      if (baseClass == XAnnotatable.class)
      {
         switch (derivedFeatureID)
         {
            default: return -1;
         }
      }
      return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == Extendable.class)
      {
         switch (baseFeatureID)
         {
            case CommonModelPackage.EXTENDABLE__EXTENSIONS: return JavaModelPackage.JAVA_RESOURCE_BUNDLE__EXTENSIONS;
            default: return -1;
         }
      }
      if (baseClass == Annotatable.class)
      {
         switch (baseFeatureID)
         {
            case CommonModelPackage.ANNOTATABLE__ANNOTATIONS: return JavaModelPackage.JAVA_RESOURCE_BUNDLE__ANNOTATIONS;
            default: return -1;
         }
      }
      if (baseClass == XAnnotatable.class)
      {
         switch (baseFeatureID)
         {
            default: return -1;
         }
      }
      return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
      result.append(" (name: ");
      result.append(name);
      result.append(')');
      return result.toString();
   }

} // JavaResourceBundleImpl
