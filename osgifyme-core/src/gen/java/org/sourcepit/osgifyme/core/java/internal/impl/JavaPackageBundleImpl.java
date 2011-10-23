/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgifyme.core.java.DependencyNode;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaPackageBundle;
import org.sourcepit.osgifyme.core.java.JavaType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Package Bundle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageBundleImpl#getAnnotations <em>Annotations</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageBundleImpl#getPathToRootPackagesMap <em>Path To
 * Root Packages Map</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaPackageBundleImpl#getDependencies <em>Dependencies
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class JavaPackageBundleImpl extends EObjectImpl implements JavaPackageBundle
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
    * The cached value of the '{@link #getPathToRootPackagesMap() <em>Path To Root Packages Map</em>}' map.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getPathToRootPackagesMap()
    * @generated
    * @ordered
    */
   protected EMap<String, EList<JavaPackage>> pathToRootPackagesMap;

   /**
    * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getDependencies()
    * @generated
    * @ordered
    */
   protected EList<DependencyNode> dependencies;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaPackageBundleImpl()
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
      return JavaModelPackage.Literals.JAVA_PACKAGE_BUNDLE;
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
            JavaModelPackage.JAVA_PACKAGE_BUNDLE__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EMap<String, EList<JavaPackage>> getPathToRootPackagesMap()
   {
      if (pathToRootPackagesMap == null)
      {
         pathToRootPackagesMap = new EcoreEMap<String, EList<JavaPackage>>(
            JavaModelPackage.Literals.JAVA_PACKAGE_MAP_ENTRY, JavaPackageMapEntryImpl.class, this,
            JavaModelPackage.JAVA_PACKAGE_BUNDLE__PATH_TO_ROOT_PACKAGES_MAP);
      }
      return pathToRootPackagesMap;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<DependencyNode> getDependencies()
   {
      if (dependencies == null)
      {
         dependencies = new EObjectContainmentWithInverseEList<DependencyNode>(DependencyNode.class, this,
            JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES, JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE);
      }
      return dependencies;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<JavaPackage> getRootPackages()
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
   public JavaPackage getPackage(String path, String fullyQualifiedName, boolean createOnDemand)
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
   public JavaType getType(String path, String packageName, String typeName, boolean createOnDemand)
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
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__ANNOTATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getDependencies()).basicAdd(otherEnd, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__ANNOTATIONS :
            return ((InternalEList<?>) getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__PATH_TO_ROOT_PACKAGES_MAP :
            return ((InternalEList<?>) getPathToRootPackagesMap()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES :
            return ((InternalEList<?>) getDependencies()).basicRemove(otherEnd, msgs);
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
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__ANNOTATIONS :
            return getAnnotations();
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__PATH_TO_ROOT_PACKAGES_MAP :
            if (coreType)
               return getPathToRootPackagesMap();
            else
               return getPathToRootPackagesMap().map();
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES :
            return getDependencies();
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
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__ANNOTATIONS :
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>) newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__PATH_TO_ROOT_PACKAGES_MAP :
            ((EStructuralFeature.Setting) getPathToRootPackagesMap()).set(newValue);
            return;
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES :
            getDependencies().clear();
            getDependencies().addAll((Collection<? extends DependencyNode>) newValue);
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
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__ANNOTATIONS :
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__PATH_TO_ROOT_PACKAGES_MAP :
            getPathToRootPackagesMap().clear();
            return;
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES :
            getDependencies().clear();
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
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__ANNOTATIONS :
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__PATH_TO_ROOT_PACKAGES_MAP :
            return pathToRootPackagesMap != null && !pathToRootPackagesMap.isEmpty();
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES :
            return dependencies != null && !dependencies.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // JavaPackageBundleImpl
