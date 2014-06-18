/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.model.context.impl;

import java.io.File;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Candidate</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getLocation <em>Location</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getSymbolicName <em>Symbolic Name</em>}
 * </li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#isNativeBundle <em>Native Bundle</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getManifest <em>Manifest</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getSourceBundle <em>Source Bundle</em>}
 * </li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleCandidateImpl#getTargetBundle <em>Target Bundle</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BundleCandidateImpl extends XAnnotatableImpl implements BundleCandidate
{
   /**
    * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getLocation()
    * @generated
    * @ordered
    */
   protected static final File LOCATION_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getLocation()
    * @generated
    * @ordered
    */
   protected File location = LOCATION_EDEFAULT;

   /**
    * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getContent()
    * @generated
    * @ordered
    */
   protected JavaResourceBundle content;

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
    * The default value of the '{@link #isNativeBundle() <em>Native Bundle</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isNativeBundle()
    * @generated
    * @ordered
    */
   protected static final boolean NATIVE_BUNDLE_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isNativeBundle() <em>Native Bundle</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isNativeBundle()
    * @generated
    * @ordered
    */
   protected boolean nativeBundle = NATIVE_BUNDLE_EDEFAULT;

   /**
    * The cached value of the '{@link #getManifest() <em>Manifest</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getManifest()
    * @generated
    * @ordered
    */
   protected BundleManifest manifest;

   /**
    * The cached value of the '{@link #getSourceBundle() <em>Source Bundle</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getSourceBundle()
    * @generated
    * @ordered
    */
   protected BundleCandidate sourceBundle;

   /**
    * The cached value of the '{@link #getTargetBundle() <em>Target Bundle</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTargetBundle()
    * @generated
    * @ordered
    */
   protected BundleCandidate targetBundle;

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
   public File getLocation()
   {
      return location;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setLocation(File newLocation)
   {
      File oldLocation = location;
      location = newLocation;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__LOCATION,
            oldLocation, location));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaResourceBundle getContent()
   {
      return content;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetContent(JavaResourceBundle newContent, NotificationChain msgs)
   {
      JavaResourceBundle oldContent = content;
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
   public void setContent(JavaResourceBundle newContent)
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
   public boolean isNativeBundle()
   {
      return nativeBundle;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setNativeBundle(boolean newNativeBundle)
   {
      boolean oldNativeBundle = nativeBundle;
      nativeBundle = newNativeBundle;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__NATIVE_BUNDLE,
            oldNativeBundle, nativeBundle));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleManifest getManifest()
   {
      return manifest;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetManifest(BundleManifest newManifest, NotificationChain msgs)
   {
      BundleManifest oldManifest = manifest;
      manifest = newManifest;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST, oldManifest, newManifest);
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
   public void setManifest(BundleManifest newManifest)
   {
      if (newManifest != manifest)
      {
         NotificationChain msgs = null;
         if (manifest != null)
            msgs = ((InternalEObject) manifest).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
               - ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST, null, msgs);
         if (newManifest != null)
            msgs = ((InternalEObject) newManifest).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST, null, msgs);
         msgs = basicSetManifest(newManifest, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST,
            newManifest, newManifest));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleCandidate getSourceBundle()
   {
      if (sourceBundle != null && sourceBundle.eIsProxy())
      {
         InternalEObject oldSourceBundle = (InternalEObject) sourceBundle;
         sourceBundle = (BundleCandidate) eResolveProxy(oldSourceBundle);
         if (sourceBundle != oldSourceBundle)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                  ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE, oldSourceBundle, sourceBundle));
         }
      }
      return sourceBundle;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleCandidate basicGetSourceBundle()
   {
      return sourceBundle;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetSourceBundle(BundleCandidate newSourceBundle, NotificationChain msgs)
   {
      BundleCandidate oldSourceBundle = sourceBundle;
      sourceBundle = newSourceBundle;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE, oldSourceBundle, newSourceBundle);
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
   public void setSourceBundle(BundleCandidate newSourceBundle)
   {
      if (newSourceBundle != sourceBundle)
      {
         NotificationChain msgs = null;
         if (sourceBundle != null)
            msgs = ((InternalEObject) sourceBundle).eInverseRemove(this,
               ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE, BundleCandidate.class, msgs);
         if (newSourceBundle != null)
            msgs = ((InternalEObject) newSourceBundle).eInverseAdd(this,
               ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE, BundleCandidate.class, msgs);
         msgs = basicSetSourceBundle(newSourceBundle, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE,
            newSourceBundle, newSourceBundle));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleCandidate getTargetBundle()
   {
      if (targetBundle != null && targetBundle.eIsProxy())
      {
         InternalEObject oldTargetBundle = (InternalEObject) targetBundle;
         targetBundle = (BundleCandidate) eResolveProxy(oldTargetBundle);
         if (targetBundle != oldTargetBundle)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                  ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE, oldTargetBundle, targetBundle));
         }
      }
      return targetBundle;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleCandidate basicGetTargetBundle()
   {
      return targetBundle;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetTargetBundle(BundleCandidate newTargetBundle, NotificationChain msgs)
   {
      BundleCandidate oldTargetBundle = targetBundle;
      targetBundle = newTargetBundle;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
            ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE, oldTargetBundle, newTargetBundle);
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
   public void setTargetBundle(BundleCandidate newTargetBundle)
   {
      if (newTargetBundle != targetBundle)
      {
         NotificationChain msgs = null;
         if (targetBundle != null)
            msgs = ((InternalEObject) targetBundle).eInverseRemove(this,
               ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE, BundleCandidate.class, msgs);
         if (newTargetBundle != null)
            msgs = ((InternalEObject) newTargetBundle).eInverseAdd(this,
               ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE, BundleCandidate.class, msgs);
         msgs = basicSetTargetBundle(newTargetBundle, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE,
            newTargetBundle, newTargetBundle));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE :
            if (sourceBundle != null)
               msgs = ((InternalEObject) sourceBundle).eInverseRemove(this,
                  ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE, BundleCandidate.class, msgs);
            return basicSetSourceBundle((BundleCandidate) otherEnd, msgs);
         case ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE :
            if (targetBundle != null)
               msgs = ((InternalEObject) targetBundle).eInverseRemove(this,
                  ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE, BundleCandidate.class, msgs);
            return basicSetTargetBundle((BundleCandidate) otherEnd, msgs);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            return basicSetContent(null, msgs);
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
            return ((InternalEList<?>) getDependencies()).basicRemove(otherEnd, msgs);
         case ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST :
            return basicSetManifest(null, msgs);
         case ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE :
            return basicSetSourceBundle(null, msgs);
         case ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE :
            return basicSetTargetBundle(null, msgs);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__LOCATION :
            return getLocation();
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            return getContent();
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
            return getDependencies();
         case ContextModelPackage.BUNDLE_CANDIDATE__VERSION :
            return getVersion();
         case ContextModelPackage.BUNDLE_CANDIDATE__SYMBOLIC_NAME :
            return getSymbolicName();
         case ContextModelPackage.BUNDLE_CANDIDATE__NATIVE_BUNDLE :
            return isNativeBundle();
         case ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST :
            return getManifest();
         case ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE :
            if (resolve)
               return getSourceBundle();
            return basicGetSourceBundle();
         case ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE :
            if (resolve)
               return getTargetBundle();
            return basicGetTargetBundle();
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
         case ContextModelPackage.BUNDLE_CANDIDATE__LOCATION :
            setLocation((File) newValue);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            setContent((JavaResourceBundle) newValue);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__NATIVE_BUNDLE :
            setNativeBundle((Boolean) newValue);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST :
            setManifest((BundleManifest) newValue);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE :
            setSourceBundle((BundleCandidate) newValue);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE :
            setTargetBundle((BundleCandidate) newValue);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__LOCATION :
            setLocation(LOCATION_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            setContent((JavaResourceBundle) null);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__NATIVE_BUNDLE :
            setNativeBundle(NATIVE_BUNDLE_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST :
            setManifest((BundleManifest) null);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE :
            setSourceBundle((BundleCandidate) null);
            return;
         case ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE :
            setTargetBundle((BundleCandidate) null);
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
         case ContextModelPackage.BUNDLE_CANDIDATE__LOCATION :
            return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
         case ContextModelPackage.BUNDLE_CANDIDATE__CONTENT :
            return content != null;
         case ContextModelPackage.BUNDLE_CANDIDATE__DEPENDENCIES :
            return dependencies != null && !dependencies.isEmpty();
         case ContextModelPackage.BUNDLE_CANDIDATE__VERSION :
            return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
         case ContextModelPackage.BUNDLE_CANDIDATE__SYMBOLIC_NAME :
            return SYMBOLIC_NAME_EDEFAULT == null ? symbolicName != null : !SYMBOLIC_NAME_EDEFAULT.equals(symbolicName);
         case ContextModelPackage.BUNDLE_CANDIDATE__NATIVE_BUNDLE :
            return nativeBundle != NATIVE_BUNDLE_EDEFAULT;
         case ContextModelPackage.BUNDLE_CANDIDATE__MANIFEST :
            return manifest != null;
         case ContextModelPackage.BUNDLE_CANDIDATE__SOURCE_BUNDLE :
            return sourceBundle != null;
         case ContextModelPackage.BUNDLE_CANDIDATE__TARGET_BUNDLE :
            return targetBundle != null;
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
      result.append(" (location: ");
      result.append(location);
      result.append(", version: ");
      result.append(version);
      result.append(", symbolicName: ");
      result.append(symbolicName);
      result.append(", nativeBundle: ");
      result.append(nativeBundle);
      result.append(')');
      return result.toString();
   }

} // BundleCandidateImpl
