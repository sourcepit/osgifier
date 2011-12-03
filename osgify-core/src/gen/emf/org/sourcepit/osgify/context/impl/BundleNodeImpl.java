/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
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
import org.sourcepit.osgify.context.BundleNode;
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
 * <li>{@link org.sourcepit.osgify.context.impl.BundleNodeImpl#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.osgify.context.impl.BundleNodeImpl#getDependencies <em>Dependencies</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundleNodeImpl extends AbstractBundleCoordinateImpl implements BundleNode
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
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected BundleNodeImpl()
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
      return ContextModelPackage.Literals.BUNDLE_NODE;
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
            ContextModelPackage.BUNDLE_NODE__CONTENT, oldContent, newContent);
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
               - ContextModelPackage.BUNDLE_NODE__CONTENT, null, msgs);
         if (newContent != null)
            msgs = ((InternalEObject) newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
               - ContextModelPackage.BUNDLE_NODE__CONTENT, null, msgs);
         msgs = basicSetContent(newContent, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, ContextModelPackage.BUNDLE_NODE__CONTENT, newContent,
            newContent));
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
            ContextModelPackage.BUNDLE_NODE__DEPENDENCIES);
      }
      return dependencies;
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
         case ContextModelPackage.BUNDLE_NODE__CONTENT :
            return basicSetContent(null, msgs);
         case ContextModelPackage.BUNDLE_NODE__DEPENDENCIES :
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
         case ContextModelPackage.BUNDLE_NODE__CONTENT :
            return getContent();
         case ContextModelPackage.BUNDLE_NODE__DEPENDENCIES :
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
         case ContextModelPackage.BUNDLE_NODE__CONTENT :
            setContent((JavaPackageBundle) newValue);
            return;
         case ContextModelPackage.BUNDLE_NODE__DEPENDENCIES :
            getDependencies().clear();
            getDependencies().addAll((Collection<? extends BundleReference>) newValue);
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
         case ContextModelPackage.BUNDLE_NODE__CONTENT :
            setContent((JavaPackageBundle) null);
            return;
         case ContextModelPackage.BUNDLE_NODE__DEPENDENCIES :
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
         case ContextModelPackage.BUNDLE_NODE__CONTENT :
            return content != null;
         case ContextModelPackage.BUNDLE_NODE__DEPENDENCIES :
            return dependencies != null && !dependencies.isEmpty();
      }
      return super.eIsSet(featureID);
   }

} // BundleNodeImpl
