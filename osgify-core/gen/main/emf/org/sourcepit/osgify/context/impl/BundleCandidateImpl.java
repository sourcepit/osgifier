/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.context.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.modeling.common.impl.XAnnotatableImpl;
import org.sourcepit.osgify.context.BundleCandidate;
import org.sourcepit.osgify.context.BundleReference;
import org.sourcepit.osgify.context.ContextModelPackage;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleCandidateImpl#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleCandidateImpl#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleCandidateImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleCandidateImpl#getSymbolicName <em>Symbolic Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleCandidateImpl extends XAnnotatableImpl implements BundleCandidate
{
   /**
    * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getContent()
    * @generated
    * @ordered
    */
   protected JavaPackageBundle content;

   /**
    * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getDependencies()
    * @generated
    * @ordered
    */
   protected EList<BundleReference> dependencies;

   /**
    * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersion()
    * @generated
    * @ordered
    */
   protected static final Version VERSION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersion()
    * @generated
    * @ordered
    */
   protected Version version = VERSION_EDEFAULT;

   /**
    * The default value of the '{@link #getSymbolicName() <em>Symbolic Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSymbolicName()
    * @generated
    * @ordered
    */
   protected static final String SYMBOLIC_NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getSymbolicName() <em>Symbolic Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSymbolicName()
    * @generated
    * @ordered
    */
   protected String symbolicName = SYMBOLIC_NAME_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected BundleCandidateImpl()
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
      return ContextModelPackage.Literals.BUNDLE_CANDIDATE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageBundle getContent()
   {
      return content;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetContent(JavaPackageBundle newContent, NotificationChain msgs)
   {
      JavaPackageBundle oldContent = content;
      content = newContent;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            ContextModelPackage.BUNDLE_CANDIDATE__CONTENT, oldContent, newContent);
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
   public void setContent(JavaPackageBundle newContent)
   {
      if (newContent != content)
      {
         NotificationChain msgs = null;
         if (content != null)
            msgs = ((InternalEObject) content).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
               - ContextModelPackage.BUNDLE_CANDIDATE__CONTENT, null, msgs);
         if (newContent != null)
            msgs = ((InternalEObject) newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - ContextModelPackage.BUNDLE_CANDIDATE__CONTENT, null, msgs);
         msgs = basicSetContent(newContent, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__CONTENT,
            newContent, newContent));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<BundleReference> getDependencies()
   {
      if (dependencies == null)
      {
         dependencies = new EObjectContainmentEList<BundleReference>(BundleReference.class, this,
            ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES);
      }
      return dependencies;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public Version getVersion()
   {
      return version;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setVersion(Version newVersion)
   {
      Version oldVersion = version;
      version = newVersion;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__VERSION,
            oldVersion, version));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getSymbolicName()
   {
      return symbolicName;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setSymbolicName(String newSymbolicName)
   {
      String oldSymbolicName = symbolicName;
      symbolicName = newSymbolicName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__SYMBOLIC_NAME,
            oldSymbolicName, symbolicName));
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
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            return basicSetContent(null, msgs);
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
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
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            return getContent();
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
            return getDependencies();
         case ContextModelPackage.BUNDLE_CANDIDATE__VERSION :
            return getVersion();
         case ContextModelPackage.BUNDLE_CANDIDATE__SYMBOLIC_NAME :
            return getSymbolicName();
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
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            setContent((JavaPackageBundle) newValue);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
            getDependencies().clear();
            getDependencies().addAll((Collection<? extends BundleReference>) newValue);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__VERSION :
            setVersion((Version) newValue);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__SYMBOLIC_NAME :
            setSymbolicName((String) newValue);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            setContent((JavaPackageBundle) null);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
            getDependencies().clear();
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__VERSION :
            setVersion(VERSION_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__SYMBOLIC_NAME :
            setSymbolicName(SYMBOLIC_NAME_EDEFAULT);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            return content != null;
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
            return dependencies != null && !dependencies.isEmpty();
         case ContextModelPackage.BUNDLE_CANDIDATE__VERSION :
            return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
         case ContextModelPackage.BUNDLE_CANDIDATE__SYMBOLIC_NAME :
            return SYMBOLIC_NAME_EDEFAULT == null ? symbolicName != null : !SYMBOLIC_NAME_EDEFAULT.equals(symbolicName);
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
      result.append(" (version: ");
      result.append(version);
      result.append(", symbolicName: ");
      result.append(symbolicName);
      result.append(')');
      return result.toString();
   }

} // BundleNodeImpl
