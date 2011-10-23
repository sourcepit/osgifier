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
import org.sourcepit.osgifyme.core.java.DependencyNode;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dependency Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#getAnnotations <em>Annotations</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#isOptional <em>Optional</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#getParentNode <em>Parent Node</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#getPackageBundle <em>Package Bundle
 * </em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.internal.impl.DependencyNodeImpl#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DependencyNodeImpl extends EObjectImpl implements DependencyNode
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
    * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getTarget()
    * @generated
    * @ordered
    */
   protected JavaPackageBundle target;

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
    * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isEnabled()
    * @generated
    * @ordered
    */
   protected static final boolean ENABLED_EDEFAULT = false;

   /**
    * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #isEnabled()
    * @generated
    * @ordered
    */
   protected boolean enabled = ENABLED_EDEFAULT;

   /**
    * The default value of the '{@link #getScope() <em>Scope</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getScope()
    * @generated
    * @ordered
    */
   protected static final String SCOPE_EDEFAULT = "compile";

   /**
    * The cached value of the '{@link #getScope() <em>Scope</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getScope()
    * @generated
    * @ordered
    */
   protected String scope = SCOPE_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected DependencyNodeImpl()
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
      return JavaModelPackage.Literals.DEPENDENCY_NODE;
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
            JavaModelPackage.DEPENDENCY_NODE__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageBundle getTarget()
   {
      if (target != null && target.eIsProxy())
      {
         InternalEObject oldTarget = (InternalEObject) target;
         target = (JavaPackageBundle) eResolveProxy(oldTarget);
         if (target != oldTarget)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaModelPackage.DEPENDENCY_NODE__TARGET,
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
   public JavaPackageBundle basicGetTarget()
   {
      return target;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setTarget(JavaPackageBundle newTarget)
   {
      JavaPackageBundle oldTarget = target;
      target = newTarget;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.DEPENDENCY_NODE__TARGET, oldTarget,
            target));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isOptional()
   {
      return optional;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setOptional(boolean newOptional)
   {
      boolean oldOptional = optional;
      optional = newOptional;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.DEPENDENCY_NODE__OPTIONAL, oldOptional,
            optional));
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
            JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES, JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE);
      }
      return dependencies;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public boolean isEnabled()
   {
      return enabled;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setEnabled(boolean newEnabled)
   {
      boolean oldEnabled = enabled;
      enabled = newEnabled;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.DEPENDENCY_NODE__ENABLED, oldEnabled,
            enabled));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public DependencyNode getParentNode()
   {
      if (eContainerFeatureID() != JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE)
         return null;
      return (DependencyNode) eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetParentNode(DependencyNode newParentNode, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newParentNode, JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setParentNode(DependencyNode newParentNode)
   {
      if (newParentNode != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE && newParentNode != null))
      {
         if (EcoreUtil.isAncestor(this, newParentNode))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newParentNode != null)
            msgs = ((InternalEObject) newParentNode).eInverseAdd(this, JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES,
               DependencyNode.class, msgs);
         msgs = basicSetParentNode(newParentNode, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE,
            newParentNode, newParentNode));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaPackageBundle getPackageBundle()
   {
      if (eContainerFeatureID() != JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE)
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
      msgs = eBasicSetContainer((InternalEObject) newPackageBundle, JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE,
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
         || (eContainerFeatureID() != JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE && newPackageBundle != null))
      {
         if (EcoreUtil.isAncestor(this, newPackageBundle))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newPackageBundle != null)
            msgs = ((InternalEObject) newPackageBundle).eInverseAdd(this,
               JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES, JavaPackageBundle.class, msgs);
         msgs = basicSetPackageBundle(newPackageBundle, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE,
            newPackageBundle, newPackageBundle));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getScope()
   {
      return scope;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setScope(String newScope)
   {
      String oldScope = scope;
      scope = newScope;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.DEPENDENCY_NODE__SCOPE, oldScope, scope));
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
         case JavaModelPackage.DEPENDENCY_NODE__ANNOTATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getDependencies()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetParentNode((DependencyNode) otherEnd, msgs);
         case JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE :
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
         case JavaModelPackage.DEPENDENCY_NODE__ANNOTATIONS :
            return ((InternalEList<?>) getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES :
            return ((InternalEList<?>) getDependencies()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE :
            return basicSetParentNode(null, msgs);
         case JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE :
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
         case JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES,
               DependencyNode.class, msgs);
         case JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE :
            return eInternalContainer().eInverseRemove(this, JavaModelPackage.JAVA_PACKAGE_BUNDLE__DEPENDENCIES,
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
         case JavaModelPackage.DEPENDENCY_NODE__ANNOTATIONS :
            return getAnnotations();
         case JavaModelPackage.DEPENDENCY_NODE__TARGET :
            if (resolve)
               return getTarget();
            return basicGetTarget();
         case JavaModelPackage.DEPENDENCY_NODE__OPTIONAL :
            return isOptional();
         case JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES :
            return getDependencies();
         case JavaModelPackage.DEPENDENCY_NODE__ENABLED :
            return isEnabled();
         case JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE :
            return getParentNode();
         case JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE :
            return getPackageBundle();
         case JavaModelPackage.DEPENDENCY_NODE__SCOPE :
            return getScope();
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
         case JavaModelPackage.DEPENDENCY_NODE__ANNOTATIONS :
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>) newValue);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__TARGET :
            setTarget((JavaPackageBundle) newValue);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__OPTIONAL :
            setOptional((Boolean) newValue);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES :
            getDependencies().clear();
            getDependencies().addAll((Collection<? extends DependencyNode>) newValue);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__ENABLED :
            setEnabled((Boolean) newValue);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE :
            setParentNode((DependencyNode) newValue);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE :
            setPackageBundle((JavaPackageBundle) newValue);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__SCOPE :
            setScope((String) newValue);
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
         case JavaModelPackage.DEPENDENCY_NODE__ANNOTATIONS :
            getAnnotations().clear();
            return;
         case JavaModelPackage.DEPENDENCY_NODE__TARGET :
            setTarget((JavaPackageBundle) null);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__OPTIONAL :
            setOptional(OPTIONAL_EDEFAULT);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES :
            getDependencies().clear();
            return;
         case JavaModelPackage.DEPENDENCY_NODE__ENABLED :
            setEnabled(ENABLED_EDEFAULT);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE :
            setParentNode((DependencyNode) null);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE :
            setPackageBundle((JavaPackageBundle) null);
            return;
         case JavaModelPackage.DEPENDENCY_NODE__SCOPE :
            setScope(SCOPE_EDEFAULT);
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
         case JavaModelPackage.DEPENDENCY_NODE__ANNOTATIONS :
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.DEPENDENCY_NODE__TARGET :
            return target != null;
         case JavaModelPackage.DEPENDENCY_NODE__OPTIONAL :
            return optional != OPTIONAL_EDEFAULT;
         case JavaModelPackage.DEPENDENCY_NODE__DEPENDENCIES :
            return dependencies != null && !dependencies.isEmpty();
         case JavaModelPackage.DEPENDENCY_NODE__ENABLED :
            return enabled != ENABLED_EDEFAULT;
         case JavaModelPackage.DEPENDENCY_NODE__PARENT_NODE :
            return getParentNode() != null;
         case JavaModelPackage.DEPENDENCY_NODE__PACKAGE_BUNDLE :
            return getPackageBundle() != null;
         case JavaModelPackage.DEPENDENCY_NODE__SCOPE :
            return SCOPE_EDEFAULT == null ? scope != null : !SCOPE_EDEFAULT.equals(scope);
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
      result.append(" (optional: ");
      result.append(optional);
      result.append(", enabled: ");
      result.append(enabled);
      result.append(", scope: ");
      result.append(scope);
      result.append(')');
      return result.toString();
   }

} // DependencyNodeImpl
