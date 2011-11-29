/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgifyme.core.java.JavaArchive;
import org.sourcepit.osgifyme.core.java.JavaModel;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaProject;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaModelImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaModelImpl#getProjects <em>Projects</em>}</li>
 *   <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.JavaModelImpl#getArchives <em>Archives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaModelImpl extends EObjectImpl implements JavaModel
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
    * The cached value of the '{@link #getProjects() <em>Projects</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getProjects()
    * @generated
    * @ordered
    */
   protected EList<JavaProject> projects;

   /**
    * The cached value of the '{@link #getArchives() <em>Archives</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getArchives()
    * @generated
    * @ordered
    */
   protected EList<JavaArchive> archives;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected JavaModelImpl()
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
      return JavaModelPackage.Literals.JAVA_MODEL;
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
         annotations = new EObjectContainmentWithInverseEList<Annotation>(Annotation.class, this, JavaModelPackage.JAVA_MODEL__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<JavaProject> getProjects()
   {
      if (projects == null)
      {
         projects = new EObjectContainmentEList<JavaProject>(JavaProject.class, this, JavaModelPackage.JAVA_MODEL__PROJECTS);
      }
      return projects;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList<JavaArchive> getArchives()
   {
      if (archives == null)
      {
         archives = new EObjectContainmentEList<JavaArchive>(JavaArchive.class, this, JavaModelPackage.JAVA_MODEL__ARCHIVES);
      }
      return archives;
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
         case JavaModelPackage.JAVA_MODEL__ANNOTATIONS:
            return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotations()).basicAdd(otherEnd, msgs);
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
         case JavaModelPackage.JAVA_MODEL__ANNOTATIONS:
            return ((InternalEList<?>)getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_MODEL__PROJECTS:
            return ((InternalEList<?>)getProjects()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_MODEL__ARCHIVES:
            return ((InternalEList<?>)getArchives()).basicRemove(otherEnd, msgs);
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
         case JavaModelPackage.JAVA_MODEL__ANNOTATIONS:
            return getAnnotations();
         case JavaModelPackage.JAVA_MODEL__PROJECTS:
            return getProjects();
         case JavaModelPackage.JAVA_MODEL__ARCHIVES:
            return getArchives();
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
         case JavaModelPackage.JAVA_MODEL__ANNOTATIONS:
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>)newValue);
            return;
         case JavaModelPackage.JAVA_MODEL__PROJECTS:
            getProjects().clear();
            getProjects().addAll((Collection<? extends JavaProject>)newValue);
            return;
         case JavaModelPackage.JAVA_MODEL__ARCHIVES:
            getArchives().clear();
            getArchives().addAll((Collection<? extends JavaArchive>)newValue);
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
         case JavaModelPackage.JAVA_MODEL__ANNOTATIONS:
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_MODEL__PROJECTS:
            getProjects().clear();
            return;
         case JavaModelPackage.JAVA_MODEL__ARCHIVES:
            getArchives().clear();
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
         case JavaModelPackage.JAVA_MODEL__ANNOTATIONS:
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_MODEL__PROJECTS:
            return projects != null && !projects.isEmpty();
         case JavaModelPackage.JAVA_MODEL__ARCHIVES:
            return archives != null && !archives.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // JavaModelImpl
