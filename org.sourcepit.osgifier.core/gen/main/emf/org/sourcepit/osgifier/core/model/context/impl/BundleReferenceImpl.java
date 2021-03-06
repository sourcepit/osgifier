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

package org.sourcepit.osgifier.core.model.context.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.modeling.impl.XAnnotatableImpl;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelPackage;
import org.sourcepit.osgifier.core.model.context.EmbedInstruction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bundle Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl#getVersionRange <em>Version Range</em>}
 * </li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl#isOptional <em>Optional</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl#isProvided <em>Provided</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.impl.BundleReferenceImpl#getEmbedInstruction <em>Embed
 * Instruction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BundleReferenceImpl extends XAnnotatableImpl implements BundleReference {
   /**
    * The default value of the '{@link #getVersionRange() <em>Version Range</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersionRange()
    * @generated
    * @ordered
    */
   protected static final VersionRange VERSION_RANGE_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getVersionRange() <em>Version Range</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getVersionRange()
    * @generated
    * @ordered
    */
   protected VersionRange versionRange = VERSION_RANGE_EDEFAULT;

   /**
    * The default value of the '{@link #isOptional() <em>Optional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isOptional()
    * @generated
    * @ordered
    */
   protected static final boolean OPTIONAL_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isOptional() <em>Optional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isOptional()
    * @generated
    * @ordered
    */
   protected boolean optional = OPTIONAL_EDEFAULT;

   /**
    * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTarget()
    * @generated
    * @ordered
    */
   protected BundleCandidate target;

   /**
    * The default value of the '{@link #isProvided() <em>Provided</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isProvided()
    * @generated
    * @ordered
    */
   protected static final boolean PROVIDED_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isProvided() <em>Provided</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isProvided()
    * @generated
    * @ordered
    */
   protected boolean provided = PROVIDED_EDEFAULT;

   /**
    * The default value of the '{@link #getEmbedInstruction() <em>Embed Instruction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getEmbedInstruction()
    * @generated
    * @ordered
    */
   protected static final EmbedInstruction EMBED_INSTRUCTION_EDEFAULT = EmbedInstruction.NOT;

   /**
    * The cached value of the '{@link #getEmbedInstruction() <em>Embed Instruction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getEmbedInstruction()
    * @generated
    * @ordered
    */
   protected EmbedInstruction embedInstruction = EMBED_INSTRUCTION_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected BundleReferenceImpl() {
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
      return ContextModelPackage.Literals.BUNDLE_REFERENCE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public VersionRange getVersionRange() {
      return versionRange;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setVersionRange(VersionRange newVersionRange) {
      VersionRange oldVersionRange = versionRange;
      versionRange = newVersionRange;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE,
            oldVersionRange, versionRange));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isOptional() {
      return optional;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setOptional(boolean newOptional) {
      boolean oldOptional = optional;
      optional = newOptional;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL,
            oldOptional, optional));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleCandidate getTarget() {
      if (target != null && target.eIsProxy()) {
         InternalEObject oldTarget = (InternalEObject) target;
         target = (BundleCandidate) eResolveProxy(oldTarget);
         if (target != oldTarget) {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContextModelPackage.BUNDLE_REFERENCE__TARGET,
                  oldTarget, target));
         }
      }
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleCandidate basicGetTarget() {
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTarget(BundleCandidate newTarget) {
      BundleCandidate oldTarget = target;
      target = newTarget;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__TARGET, oldTarget,
            target));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isProvided() {
      return provided;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setProvided(boolean newProvided) {
      boolean oldProvided = provided;
      provided = newProvided;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__PROVIDED,
            oldProvided, provided));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EmbedInstruction getEmbedInstruction() {
      return embedInstruction;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setEmbedInstruction(EmbedInstruction newEmbedInstruction) {
      EmbedInstruction oldEmbedInstruction = embedInstruction;
      embedInstruction = newEmbedInstruction == null ? EMBED_INSTRUCTION_EDEFAULT : newEmbedInstruction;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_REFERENCE__EMBED_INSTRUCTION,
            oldEmbedInstruction, embedInstruction));
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
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            return getVersionRange();
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            return isOptional();
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            if (resolve)
               return getTarget();
            return basicGetTarget();
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            return isProvided();
         case ContextModelPackage.BUNDLE_REFERENCE__EMBED_INSTRUCTION :
            return getEmbedInstruction();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public void eSet(int featureID, Object newValue) {
      switch (featureID) {
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            setVersionRange((VersionRange) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            setOptional((Boolean) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            setTarget((BundleCandidate) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            setProvided((Boolean) newValue);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__EMBED_INSTRUCTION :
            setEmbedInstruction((EmbedInstruction) newValue);
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
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            setVersionRange(VERSION_RANGE_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            setOptional(OPTIONAL_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            setTarget((BundleCandidate) null);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            setProvided(PROVIDED_EDEFAULT);
            return;
         case ContextModelPackage.BUNDLE_REFERENCE__EMBED_INSTRUCTION :
            setEmbedInstruction(EMBED_INSTRUCTION_EDEFAULT);
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
         case ContextModelPackage.BUNDLE_REFERENCE__VERSION_RANGE :
            return VERSION_RANGE_EDEFAULT == null ? versionRange != null : !VERSION_RANGE_EDEFAULT.equals(versionRange);
         case ContextModelPackage.BUNDLE_REFERENCE__OPTIONAL :
            return optional != OPTIONAL_EDEFAULT;
         case ContextModelPackage.BUNDLE_REFERENCE__TARGET :
            return target != null;
         case ContextModelPackage.BUNDLE_REFERENCE__PROVIDED :
            return provided != PROVIDED_EDEFAULT;
         case ContextModelPackage.BUNDLE_REFERENCE__EMBED_INSTRUCTION :
            return embedInstruction != EMBED_INSTRUCTION_EDEFAULT;
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
   public String toString() {
      if (eIsProxy())
         return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (versionRange: ");
      result.append(versionRange);
      result.append(", optional: ");
      result.append(optional);
      result.append(", provided: ");
      result.append(provided);
      result.append(", embedInstruction: ");
      result.append(embedInstruction);
      result.append(')');
      return result.toString();
   }

} // BundleReferenceImpl
