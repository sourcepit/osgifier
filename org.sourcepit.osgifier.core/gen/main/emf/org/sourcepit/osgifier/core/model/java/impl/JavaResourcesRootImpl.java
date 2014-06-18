/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
import org.sourcepit.osgifier.core.model.java.Directory;
import org.sourcepit.osgifier.core.model.java.File;
import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaModelPackage;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.JavaResourcesType;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.Resource;
import org.sourcepit.osgifier.core.model.java.ResourceVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Resources Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaResourcesRootImpl#getName <em>Name</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaResourcesRootImpl#getExtensions <em>Extensions</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaResourcesRootImpl#getAnnotations <em>Annotations</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaResourcesRootImpl#getParentDirectory <em>Parent Directory
 * </em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaResourcesRootImpl#getResources <em>Resources</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaResourcesRootImpl#getPackageBundle <em>Package Bundle
 * </em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.java.impl.JavaResourcesRootImpl#getResourcesType <em>Resources Type
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaResourcesRootImpl extends EObjectImpl implements JavaResourcesRoot
{
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
    * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getResources()
    * @generated
    * @ordered
    */
   protected EList<Resource> resources;

   /**
    * The default value of the '{@link #getResourcesType() <em>Resources Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getResourcesType()
    * @generated
    * @ordered
    */
   protected static final JavaResourcesType RESOURCES_TYPE_EDEFAULT = JavaResourcesType.BIN;

   /**
    * The cached value of the '{@link #getResourcesType() <em>Resources Type</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getResourcesType()
    * @generated
    * @ordered
    */
   protected JavaResourcesType resourcesType = RESOURCES_TYPE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected JavaResourcesRootImpl()
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
      return JavaModelPackage.Literals.JAVA_RESOURCES_ROOT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setName(String newName)
   {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_RESOURCES_ROOT__NAME, oldName,
            name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<EObject> getExtensions()
   {
      if (extensions == null)
      {
         extensions = new EObjectContainmentEList<EObject>(EObject.class, this,
            JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS);
      }
      return extensions;
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
            JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS, CommonModelingPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Directory getParentDirectory()
   {
      if (eContainerFeatureID() != JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY)
         return null;
      return (Directory) eInternalContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetParentDirectory(Directory newParentDirectory, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newParentDirectory,
         JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setParentDirectory(Directory newParentDirectory)
   {
      if (newParentDirectory != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY && newParentDirectory != null))
      {
         if (EcoreUtil.isAncestor(this, newParentDirectory))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newParentDirectory != null)
            msgs = ((InternalEObject) newParentDirectory).eInverseAdd(this, JavaModelPackage.DIRECTORY__RESOURCES,
               Directory.class, msgs);
         msgs = basicSetParentDirectory(newParentDirectory, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY,
            newParentDirectory, newParentDirectory));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<Resource> getResources()
   {
      if (resources == null)
      {
         resources = new EObjectContainmentWithInverseEList<Resource>(Resource.class, this,
            JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES, JavaModelPackage.RESOURCE__PARENT_DIRECTORY);
      }
      return resources;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourceBundle getPackageBundle()
   {
      if (eContainerFeatureID() != JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE)
         return null;
      return (JavaResourceBundle) eInternalContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetPackageBundle(JavaResourceBundle newPackageBundle, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newPackageBundle,
         JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setPackageBundle(JavaResourceBundle newPackageBundle)
   {
      if (newPackageBundle != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE && newPackageBundle != null))
      {
         if (EcoreUtil.isAncestor(this, newPackageBundle))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newPackageBundle != null)
            msgs = ((InternalEObject) newPackageBundle).eInverseAdd(this,
               JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS, JavaResourceBundle.class, msgs);
         msgs = basicSetPackageBundle(newPackageBundle, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE,
            newPackageBundle, newPackageBundle));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourcesType getResourcesType()
   {
      return resourcesType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setResourcesType(JavaResourcesType newResourcesType)
   {
      JavaResourcesType oldResourcesType = resourcesType;
      resourcesType = newResourcesType == null ? RESOURCES_TYPE_EDEFAULT : newResourcesType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES_TYPE,
            oldResourcesType, resourcesType));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaType getType(String qualifiedPackageName, String typeName, boolean createOnDemand)
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
   public EList<JavaPackage> getPackages()
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
   public EList<JavaFile> getJavaFiles()
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
   public JavaPackage getPackage(String name)
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
   public JavaPackage getPackage(String name, boolean createOnDemand)
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
   public JavaFile getJavaFile(String name)
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
   public JavaFile getJavaFile(String name, boolean createOnDemand)
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
   public JavaType getType(String name)
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
   public JavaType getType(String name, boolean createOnDemand)
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
   public Resource getResource(String name)
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
   public EList<Directory> getDirectories()
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
   public Directory getDirectory(String name)
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
   public Directory getDirectory(String name, boolean createOnDemand)
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
   public File getFile(String name)
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
   public File getFile(String name, boolean createOnDemand)
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
   public EList<File> getFiles()
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
   public void accept(ResourceVisitor visitor)
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
   public JavaResourceBundle getResourceBundle()
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
   public String setAnnotationData(String source, String key, String value)
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
   public <T extends EObject> T getExtension(Class<T> extensionType)
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
   public <T extends EObject> EList<T> getExtensions(Class<T> extensionType)
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
   public <T extends EObject> void addExtension(T extension)
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
   public <T extends EObject> void removeExtension(T extension)
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
   public <T extends EObject> void removeExtensions(Class<T> extentionType)
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
         case JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetParentDirectory((Directory) otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getResources()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetPackageBundle((JavaResourceBundle) otherEnd, msgs);
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
         case JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS :
            return ((InternalEList<?>) getExtensions()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS :
            return ((InternalEList<?>) getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
            return basicSetParentDirectory(null, msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES :
            return ((InternalEList<?>) getResources()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE :
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
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.DIRECTORY__RESOURCES, Directory.class,
               msgs);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.JAVA_RESOURCE_BUNDLE__RESOURCES_ROOTS,
               JavaResourceBundle.class, msgs);
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
         case JavaModelPackage.JAVA_RESOURCES_ROOT__NAME :
            return getName();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS :
            return getExtensions();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS :
            return getAnnotations();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
            return getParentDirectory();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES :
            return getResources();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE :
            return getPackageBundle();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES_TYPE :
            return getResourcesType();
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
         case JavaModelPackage.JAVA_RESOURCES_ROOT__NAME :
            setName((String) newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS :
            getExtensions().clear();
            getExtensions().addAll((Collection<? extends EObject>) newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS :
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>) newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
            setParentDirectory((Directory) newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES :
            getResources().clear();
            getResources().addAll((Collection<? extends Resource>) newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE :
            setPackageBundle((JavaResourceBundle) newValue);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES_TYPE :
            setResourcesType((JavaResourcesType) newValue);
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
         case JavaModelPackage.JAVA_RESOURCES_ROOT__NAME :
            setName(NAME_EDEFAULT);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS :
            getExtensions().clear();
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS :
            getAnnotations().clear();
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
            setParentDirectory((Directory) null);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES :
            getResources().clear();
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE :
            setPackageBundle((JavaResourceBundle) null);
            return;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES_TYPE :
            setResourcesType(RESOURCES_TYPE_EDEFAULT);
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
         case JavaModelPackage.JAVA_RESOURCES_ROOT__NAME :
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS :
            return extensions != null && !extensions.isEmpty();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS :
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
            return getParentDirectory() != null;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES :
            return resources != null && !resources.isEmpty();
         case JavaModelPackage.JAVA_RESOURCES_ROOT__PACKAGE_BUNDLE :
            return getPackageBundle() != null;
         case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES_TYPE :
            return resourcesType != RESOURCES_TYPE_EDEFAULT;
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
   public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
   {
      if (baseClass == Extendable.class)
      {
         switch (derivedFeatureID)
         {
            case JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS :
               return CommonModelingPackage.EXTENDABLE__EXTENSIONS;
            default :
               return -1;
         }
      }
      if (baseClass == Annotatable.class)
      {
         switch (derivedFeatureID)
         {
            case JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS :
               return CommonModelingPackage.ANNOTATABLE__ANNOTATIONS;
            default :
               return -1;
         }
      }
      if (baseClass == XAnnotatable.class)
      {
         switch (derivedFeatureID)
         {
            default :
               return -1;
         }
      }
      if (baseClass == Resource.class)
      {
         switch (derivedFeatureID)
         {
            case JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY :
               return JavaModelPackage.RESOURCE__PARENT_DIRECTORY;
            default :
               return -1;
         }
      }
      if (baseClass == Directory.class)
      {
         switch (derivedFeatureID)
         {
            case JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES :
               return JavaModelPackage.DIRECTORY__RESOURCES;
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
   public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
   {
      if (baseClass == Extendable.class)
      {
         switch (baseFeatureID)
         {
            case CommonModelingPackage.EXTENDABLE__EXTENSIONS :
               return JavaModelPackage.JAVA_RESOURCES_ROOT__EXTENSIONS;
            default :
               return -1;
         }
      }
      if (baseClass == Annotatable.class)
      {
         switch (baseFeatureID)
         {
            case CommonModelingPackage.ANNOTATABLE__ANNOTATIONS :
               return JavaModelPackage.JAVA_RESOURCES_ROOT__ANNOTATIONS;
            default :
               return -1;
         }
      }
      if (baseClass == XAnnotatable.class)
      {
         switch (baseFeatureID)
         {
            default :
               return -1;
         }
      }
      if (baseClass == Resource.class)
      {
         switch (baseFeatureID)
         {
            case JavaModelPackage.RESOURCE__PARENT_DIRECTORY :
               return JavaModelPackage.JAVA_RESOURCES_ROOT__PARENT_DIRECTORY;
            default :
               return -1;
         }
      }
      if (baseClass == Directory.class)
      {
         switch (baseFeatureID)
         {
            case JavaModelPackage.DIRECTORY__RESOURCES :
               return JavaModelPackage.JAVA_RESOURCES_ROOT__RESOURCES;
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
   public String toString()
   {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (name: ");
      result.append(name);
      result.append(", resourcesType: ");
      result.append(resourcesType);
      result.append(')');
      return result.toString();
   }

} // JavaResourcesRootImpl
