/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sourcepit.modeling.common.Annotation;
import org.sourcepit.modeling.common.CommonModelPackage;
import org.sourcepit.osgify.java.ImportDeclaration;
import org.sourcepit.osgify.java.JavaCompilationUnit;
import org.sourcepit.osgify.java.JavaModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.sourcepit.osgify.java.impl.ImportDeclarationImpl#getAnnotations <em>Annotations</em>}</li>
 * <li>{@link org.sourcepit.osgify.java.impl.ImportDeclarationImpl#getCompilationUnit <em>Compilation Unit</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ImportDeclarationImpl extends EObjectImpl implements ImportDeclaration
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
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected ImportDeclarationImpl()
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
      return JavaModelPackage.Literals.IMPORT_DECLARATION;
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
            JavaModelPackage.IMPORT_DECLARATION__ANNOTATIONS, CommonModelPackage.ANNOTATION__TARGET);
      }
      return annotations;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaCompilationUnit getCompilationUnit()
   {
      if (eContainerFeatureID() != JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT)
         return null;
      return (JavaCompilationUnit) eContainer();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public NotificationChain basicSetCompilationUnit(JavaCompilationUnit newCompilationUnit, NotificationChain msgs)
   {
      msgs = eBasicSetContainer((InternalEObject) newCompilationUnit,
         JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT, msgs);
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public void setCompilationUnit(JavaCompilationUnit newCompilationUnit)
   {
      if (newCompilationUnit != eInternalContainer()
         || (eContainerFeatureID() != JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT && newCompilationUnit != null))
      {
         if (EcoreUtil.isAncestor(this, newCompilationUnit))
            throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
         NotificationChain msgs = null;
         if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
         if (newCompilationUnit != null)
            msgs = ((InternalEObject) newCompilationUnit).eInverseAdd(this,
               JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS, JavaCompilationUnit.class, msgs);
         msgs = basicSetCompilationUnit(newCompilationUnit, msgs);
         if (msgs != null)
            msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT,
            newCompilationUnit, newCompilationUnit));
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
         case JavaModelPackage.IMPORT_DECLARATION__ANNOTATIONS :
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getAnnotations()).basicAdd(otherEnd, msgs);
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            if (eInternalContainer() != null)
               msgs = eBasicRemoveFromContainer(msgs);
            return basicSetCompilationUnit((JavaCompilationUnit) otherEnd, msgs);
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
         case JavaModelPackage.IMPORT_DECLARATION__ANNOTATIONS :
            return ((InternalEList<?>) getAnnotations()).basicRemove(otherEnd, msgs);
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return basicSetCompilationUnit(null, msgs);
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
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return eInternalContainer().eInverseRemove(this,
               JavaModelPackage.JAVA_COMPILATION_UNIT__IMPORT_DECLARATIONS, JavaCompilationUnit.class, msgs);
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
         case JavaModelPackage.IMPORT_DECLARATION__ANNOTATIONS :
            return getAnnotations();
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return getCompilationUnit();
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
         case JavaModelPackage.IMPORT_DECLARATION__ANNOTATIONS :
            getAnnotations().clear();
            getAnnotations().addAll((Collection<? extends Annotation>) newValue);
            return;
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            setCompilationUnit((JavaCompilationUnit) newValue);
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
         case JavaModelPackage.IMPORT_DECLARATION__ANNOTATIONS :
            getAnnotations().clear();
            return;
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            setCompilationUnit((JavaCompilationUnit) null);
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
         case JavaModelPackage.IMPORT_DECLARATION__ANNOTATIONS :
            return annotations != null && !annotations.isEmpty();
         case JavaModelPackage.IMPORT_DECLARATION__COMPILATION_UNIT :
            return getCompilationUnit() != null;
      }
      return super.eIsSet(featureID);
   }

} // ImportDeclarationImpl
