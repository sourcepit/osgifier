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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleLocalization;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.ContextModelPackage;
import org.sourcepit.osgifier.core.model.context.EmbedInstruction;
import org.sourcepit.osgifier.core.model.context.LocalizedData;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContextModelFactoryImpl extends EFactoryImpl implements ContextModelFactory {
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static ContextModelFactory init() {
      try {
         ContextModelFactory theContextModelFactory = (ContextModelFactory) EPackage.Registry.INSTANCE.getEFactory(ContextModelPackage.eNS_URI);
         if (theContextModelFactory != null) {
            return theContextModelFactory;
         }
      }
      catch (Exception exception) {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new ContextModelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ContextModelFactoryImpl() {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public EObject create(EClass eClass) {
      switch (eClass.getClassifierID()) {
         case ContextModelPackage.OSGIFIER_CONTEXT :
            return createOsgifierContext();
         case ContextModelPackage.BUNDLE_CANDIDATE :
            return createBundleCandidate();
         case ContextModelPackage.BUNDLE_REFERENCE :
            return createBundleReference();
         case ContextModelPackage.BUNDLE_LOCALIZATION :
            return createBundleLocalization();
         case ContextModelPackage.LOCALIZED_DATA :
            return createLocalizedData();
         default :
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public Object createFromString(EDataType eDataType, String initialValue) {
      switch (eDataType.getClassifierID()) {
         case ContextModelPackage.EMBED_INSTRUCTION :
            return createEmbedInstructionFromString(eDataType, initialValue);
         default :
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String convertToString(EDataType eDataType, Object instanceValue) {
      switch (eDataType.getClassifierID()) {
         case ContextModelPackage.EMBED_INSTRUCTION :
            return convertEmbedInstructionToString(eDataType, instanceValue);
         default :
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public OsgifierContext createOsgifierContext() {
      OsgifierContextImpl osgifierContext = new OsgifierContextImpl();
      return osgifierContext;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleCandidate createBundleCandidate() {
      BundleCandidateImpl bundleCandidate = new BundleCandidateImpl();
      return bundleCandidate;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleReference createBundleReference() {
      BundleReferenceImpl bundleReference = new BundleReferenceImpl();
      return bundleReference;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public BundleLocalization createBundleLocalization() {
      BundleLocalizationImpl bundleLocalization = new BundleLocalizationImpl();
      return bundleLocalization;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public LocalizedData createLocalizedData() {
      LocalizedDataImpl localizedData = new LocalizedDataImpl();
      return localizedData;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public EmbedInstruction createEmbedInstructionFromString(EDataType eDataType, String initialValue) {
      EmbedInstruction result = EmbedInstruction.get(initialValue);
      if (result == null)
         throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
            + eDataType.getName() + "'");
      return result;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String convertEmbedInstructionToString(EDataType eDataType, Object instanceValue) {
      return instanceValue == null ? null : instanceValue.toString();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public ContextModelPackage getContextModelPackage() {
      return (ContextModelPackage) getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @deprecated
    * @generated
    */
   @Deprecated
   public static ContextModelPackage getPackage() {
      return ContextModelPackage.eINSTANCE;
   }

} // ContextModelFactoryImpl
