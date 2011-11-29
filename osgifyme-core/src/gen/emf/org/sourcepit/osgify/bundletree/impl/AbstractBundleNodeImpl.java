/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.bundletree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.osgify.bundletree.AbstractBundleNode;
import org.sourcepit.osgify.bundletree.BundleNode;
import org.sourcepit.osgify.bundletree.BundleTreeModelPackage;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Bundle Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl#getSymbolicName <em>Symbolic Name</em>}</li>
 * <li>{@link org.sourcepit.osgify.bundletree.impl.AbstractBundleNodeImpl#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class AbstractBundleNodeImpl extends EObjectImpl implements AbstractBundleNode
{
   /**
    * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getNodes()
    * @generated
    * @ordered
    */
   protected EList<BundleNode> nodes;

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
    * The default value of the '{@link #getScope() <em>Scope</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #getScope()
    * @generated
    * @ordered
    */
   protected static final String SCOPE_EDEFAULT = null;

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
   protected AbstractBundleNodeImpl()
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
      return BundleTreeModelPackage.Literals.ABSTRACT_BUNDLE_NODE;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EList<BundleNode> getNodes()
   {
      if (nodes == null)
      {
         nodes = new EObjectContainmentEList<BundleNode>(BundleNode.class, this,
            BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__NODES);
      }
      return nodes;
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
               eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                  BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__TARGET, oldTarget, target));
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__TARGET,
            oldTarget, target));
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__VERSION,
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
         eNotify(new ENotificationImpl(this, Notification.SET,
            BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME, oldSymbolicName, symbolicName));
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
         eNotify(new ENotificationImpl(this, Notification.SET, BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SCOPE,
            oldScope, scope));
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
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__NODES :
            return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
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
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__NODES :
            return getNodes();
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__TARGET :
            if (resolve)
               return getTarget();
            return basicGetTarget();
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__VERSION :
            return getVersion();
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME :
            return getSymbolicName();
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SCOPE :
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
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__NODES :
            getNodes().clear();
            getNodes().addAll((Collection<? extends BundleNode>) newValue);
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__TARGET :
            setTarget((JavaPackageBundle) newValue);
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__VERSION :
            setVersion((Version) newValue);
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME :
            setSymbolicName((String) newValue);
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SCOPE :
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
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__NODES :
            getNodes().clear();
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__TARGET :
            setTarget((JavaPackageBundle) null);
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__VERSION :
            setVersion(VERSION_EDEFAULT);
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME :
            setSymbolicName(SYMBOLIC_NAME_EDEFAULT);
            return;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SCOPE :
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
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__NODES :
            return nodes != null && !nodes.isEmpty();
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__TARGET :
            return target != null;
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__VERSION :
            return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SYMBOLIC_NAME :
            return SYMBOLIC_NAME_EDEFAULT == null ? symbolicName != null : !SYMBOLIC_NAME_EDEFAULT.equals(symbolicName);
         case BundleTreeModelPackage.ABSTRACT_BUNDLE_NODE__SCOPE :
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
      result.append(" (version: ");
      result.append(version);
      result.append(", symbolicName: ");
      result.append(symbolicName);
      result.append(", scope: ");
      result.append(scope);
      result.append(')');
      return result.toString();
   }

} // AbstractBundleNodeImpl
