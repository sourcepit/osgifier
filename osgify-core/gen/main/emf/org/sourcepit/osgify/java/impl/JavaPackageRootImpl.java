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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgify.java.JavaModelPackage;
import org.sourcepit.osgify.java.JavaPackage;
import org.sourcepit.osgify.java.JavaPackageBundle;
import org.sourcepit.osgify.java.JavaPackageRoot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Package Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageRootImpl#getAnnotations <em>Annotations</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageRootImpl#getPath <em>Path</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageRootImpl#getRootPackages <em>Root Packages</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.impl.JavaPackageRootImpl#getPackageBundle <em>Package Bundle</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JavaPackageRootImpl extends EObjectImpl implements JavaPackageRoot
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
    * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getPath()
    * @generated
    * @ordered
    */
   protected static final String PATH_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getPath()
    * @generated
    * @ordered
    */
   protected String path = PATH_EDEFAULT;

   /**
    * The cached value of the '{@link #getRootPackages() <em>Root Packages</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getRootPackages()
    * @generated
    * @ordered
    */
   protected EList<JavaPackage> rootPackages;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaPackageRootImpl()
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
      return JavaModelPackage.Literals.JAVA_PACKAGE_ROOT;
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
            JavaModelPackage.JAVA_PACKAGE_ROOT__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getPath()
   {
      return path;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setPath(String newPath)
   {
      String oldPath = path;
      path = newPath;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_PACKAGE_ROOT__PATH, oldPath, path));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaPackage> getRootPackages()
   {
      if (rootPackages == null)
      {
         rootPackages = new EObjectContainmentEList<JavaPackage>(JavaPackage.class, this,
            JavaModelPackage.JAVA_PACKAGE_ROOT__ROOT_PACKAGES);
      }
      return rootPackages;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageBundle getPackageBundle()
   {
      if (eContainerFeatureID() != JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE)
         return null;
      return (JavaPackageBundle) eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetPackageBundle(JavaPackageBundle newPackageBundle, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newPackageBundle, JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE,
         msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setPackageBundle(JavaPackageBundle newPackageBundle)
   {
      if (newPackageBundle != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE && newPackageBundle != null))
      {
         if (EcoreUtil.isAncestor(this, newPackageBundle))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newPackageBundle != null)
            msgs = ((InternalEObject) newPackageBundle).eInverseAdd(this,
               JavaModelPackage.JAVA_PACKAGE_BUNDLE__PACKAGE_ROOTS, JavaPackageBundle.class, msgs);
         msgs = basicSetPackageBundle(newPackageBundle, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE,
            newPackageBundle, newPackageBundle));
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
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ANNOTATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetPackageBundle((JavaPackageBundle) otherEnd, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ANNOTATIONS :
            return ((InternalEList<?>) getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ROOT_PACKAGES :
            return ((InternalEList<?>) getRootPackages()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE :
            return basicSetPackageBundle(null, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.JAVA_PACKAGE_BUNDLE__PACKAGE_ROOTS,
               JavaPackageBundle.class, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ANNOTATIONS :
            return getAnnotations();
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PATH :
            return getPath();
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ROOT_PACKAGES :
            return getRootPackages();
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE :
            return getPackageBundle();
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
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ANNOTATIONS :
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PATH :
            setPath((String) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ROOT_PACKAGES :
            getRootPackages().clear();
            getRootPackages().addAll((Collection<? extends JavaPackage>) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE :
            setPackageBundle((JavaPackageBundle) newValue);
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
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ANNOTATIONS :
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PATH :
            setPath(PATH_EDEFAULT);
            return;
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ROOT_PACKAGES :
            getRootPackages().clear();
            return;
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE :
            setPackageBundle((JavaPackageBundle) null);
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
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ANNOTATIONS :
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PATH :
            return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
         case JavaModelPackage.JAVA_PACKAGE_ROOT__ROOT_PACKAGES :
            return rootPackages != null && !rootPackages.isEmpty();
         case JavaModelPackage.JAVA_PACKAGE_ROOT__PACKAGE_BUNDLE :
            return getPackageBundle() != null;
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
      result.append(" (path: ");
      result.append(path);
      result.append(')');
      return result.toString();
   }

} // JavaPackageRootImpl
