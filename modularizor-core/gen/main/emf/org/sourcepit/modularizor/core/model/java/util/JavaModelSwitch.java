/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.sourcepit.common.modeling.Annotatable;
import org.sourcepit.common.modeling.Extendable;
import org.sourcepit.common.modeling.XAnnotatable;
import org.sourcepit.modularizor.core.model.java.Directory;
import org.sourcepit.modularizor.core.model.java.File;
import org.sourcepit.modularizor.core.model.java.ImportDeclaration;
import org.sourcepit.modularizor.core.model.java.JavaArchive;
import org.sourcepit.modularizor.core.model.java.JavaClass;
import org.sourcepit.modularizor.core.model.java.JavaCompilationUnit;
import org.sourcepit.modularizor.core.model.java.JavaElement;
import org.sourcepit.modularizor.core.model.java.JavaFile;
import org.sourcepit.modularizor.core.model.java.JavaModelPackage;
import org.sourcepit.modularizor.core.model.java.JavaPackage;
import org.sourcepit.modularizor.core.model.java.JavaProject;
import org.sourcepit.modularizor.core.model.java.JavaResource;
import org.sourcepit.modularizor.core.model.java.JavaResourceBundle;
import org.sourcepit.modularizor.core.model.java.JavaResourceDirectory;
import org.sourcepit.modularizor.core.model.java.JavaResourcesRoot;
import org.sourcepit.modularizor.core.model.java.JavaType;
import org.sourcepit.modularizor.core.model.java.Named;
import org.sourcepit.modularizor.core.model.java.QualifiedJavaElement;
import org.sourcepit.modularizor.core.model.java.Resource;
import org.sourcepit.modularizor.core.model.java.ResourceVisitor;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each
 * class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.modularizor.core.model.java.JavaModelPackage
 * @generated
 */
public class JavaModelSwitch<T>
{
   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   protected static JavaModelPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public JavaModelSwitch()
   {
      if (modelPackage == null)
      {
         modelPackage = JavaModelPackage.eINSTANCE;
      }
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   public T doSwitch(EObject theEObject)
   {
      return doSwitch(theEObject.eClass(), theEObject);
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   protected T doSwitch(EClass theEClass, EObject theEObject)
   {
      if (theEClass.eContainer() == modelPackage)
      {
         return doSwitch(theEClass.getClassifierID(), theEObject);
      }
      else
      {
         List<EClass> eSuperTypes = theEClass.getESuperTypes();
         return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
      }
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   protected T doSwitch(int classifierID, EObject theEObject)
   {
      switch (classifierID)
      {
         case JavaModelPackage.NAMED :
         {
            Named named = (Named) theEObject;
            T result = caseNamed(named);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.RESOURCE :
         {
            Resource resource = (Resource) theEObject;
            T result = caseResource(resource);
            if (result == null)
               result = caseNamed(resource);
            if (result == null)
               result = caseXAnnotatable(resource);
            if (result == null)
               result = caseExtendable(resource);
            if (result == null)
               result = caseAnnotatable(resource);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.DIRECTORY :
         {
            Directory directory = (Directory) theEObject;
            T result = caseDirectory(directory);
            if (result == null)
               result = caseResource(directory);
            if (result == null)
               result = caseNamed(directory);
            if (result == null)
               result = caseXAnnotatable(directory);
            if (result == null)
               result = caseExtendable(directory);
            if (result == null)
               result = caseAnnotatable(directory);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.FILE :
         {
            File file = (File) theEObject;
            T result = caseFile(file);
            if (result == null)
               result = caseResource(file);
            if (result == null)
               result = caseNamed(file);
            if (result == null)
               result = caseXAnnotatable(file);
            if (result == null)
               result = caseExtendable(file);
            if (result == null)
               result = caseAnnotatable(file);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_ELEMENT :
         {
            JavaElement javaElement = (JavaElement) theEObject;
            T result = caseJavaElement(javaElement);
            if (result == null)
               result = caseNamed(javaElement);
            if (result == null)
               result = caseXAnnotatable(javaElement);
            if (result == null)
               result = caseExtendable(javaElement);
            if (result == null)
               result = caseAnnotatable(javaElement);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.QUALIFIED_JAVA_ELEMENT :
         {
            QualifiedJavaElement qualifiedJavaElement = (QualifiedJavaElement) theEObject;
            T result = caseQualifiedJavaElement(qualifiedJavaElement);
            if (result == null)
               result = caseJavaElement(qualifiedJavaElement);
            if (result == null)
               result = caseNamed(qualifiedJavaElement);
            if (result == null)
               result = caseXAnnotatable(qualifiedJavaElement);
            if (result == null)
               result = caseExtendable(qualifiedJavaElement);
            if (result == null)
               result = caseAnnotatable(qualifiedJavaElement);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_RESOURCE_BUNDLE :
         {
            JavaResourceBundle javaResourceBundle = (JavaResourceBundle) theEObject;
            T result = caseJavaResourceBundle(javaResourceBundle);
            if (result == null)
               result = caseJavaElement(javaResourceBundle);
            if (result == null)
               result = caseNamed(javaResourceBundle);
            if (result == null)
               result = caseXAnnotatable(javaResourceBundle);
            if (result == null)
               result = caseExtendable(javaResourceBundle);
            if (result == null)
               result = caseAnnotatable(javaResourceBundle);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_PROJECT :
         {
            JavaProject javaProject = (JavaProject) theEObject;
            T result = caseJavaProject(javaProject);
            if (result == null)
               result = caseJavaResourceBundle(javaProject);
            if (result == null)
               result = caseJavaElement(javaProject);
            if (result == null)
               result = caseNamed(javaProject);
            if (result == null)
               result = caseXAnnotatable(javaProject);
            if (result == null)
               result = caseExtendable(javaProject);
            if (result == null)
               result = caseAnnotatable(javaProject);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_ARCHIVE :
         {
            JavaArchive javaArchive = (JavaArchive) theEObject;
            T result = caseJavaArchive(javaArchive);
            if (result == null)
               result = caseJavaResourceBundle(javaArchive);
            if (result == null)
               result = caseJavaElement(javaArchive);
            if (result == null)
               result = caseNamed(javaArchive);
            if (result == null)
               result = caseXAnnotatable(javaArchive);
            if (result == null)
               result = caseExtendable(javaArchive);
            if (result == null)
               result = caseAnnotatable(javaArchive);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_RESOURCE_DIRECTORY :
         {
            JavaResourceDirectory javaResourceDirectory = (JavaResourceDirectory) theEObject;
            T result = caseJavaResourceDirectory(javaResourceDirectory);
            if (result == null)
               result = caseJavaElement(javaResourceDirectory);
            if (result == null)
               result = caseDirectory(javaResourceDirectory);
            if (result == null)
               result = caseResource(javaResourceDirectory);
            if (result == null)
               result = caseNamed(javaResourceDirectory);
            if (result == null)
               result = caseXAnnotatable(javaResourceDirectory);
            if (result == null)
               result = caseExtendable(javaResourceDirectory);
            if (result == null)
               result = caseAnnotatable(javaResourceDirectory);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_RESOURCES_ROOT :
         {
            JavaResourcesRoot javaResourcesRoot = (JavaResourcesRoot) theEObject;
            T result = caseJavaResourcesRoot(javaResourcesRoot);
            if (result == null)
               result = caseJavaResourceDirectory(javaResourcesRoot);
            if (result == null)
               result = caseJavaElement(javaResourcesRoot);
            if (result == null)
               result = caseDirectory(javaResourcesRoot);
            if (result == null)
               result = caseResource(javaResourcesRoot);
            if (result == null)
               result = caseNamed(javaResourcesRoot);
            if (result == null)
               result = caseXAnnotatable(javaResourcesRoot);
            if (result == null)
               result = caseExtendable(javaResourcesRoot);
            if (result == null)
               result = caseAnnotatable(javaResourcesRoot);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_PACKAGE :
         {
            JavaPackage javaPackage = (JavaPackage) theEObject;
            T result = caseJavaPackage(javaPackage);
            if (result == null)
               result = caseJavaResource(javaPackage);
            if (result == null)
               result = caseQualifiedJavaElement(javaPackage);
            if (result == null)
               result = caseJavaResourceDirectory(javaPackage);
            if (result == null)
               result = caseJavaElement(javaPackage);
            if (result == null)
               result = caseDirectory(javaPackage);
            if (result == null)
               result = caseResource(javaPackage);
            if (result == null)
               result = caseNamed(javaPackage);
            if (result == null)
               result = caseXAnnotatable(javaPackage);
            if (result == null)
               result = caseExtendable(javaPackage);
            if (result == null)
               result = caseAnnotatable(javaPackage);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_FILE :
         {
            JavaFile javaFile = (JavaFile) theEObject;
            T result = caseJavaFile(javaFile);
            if (result == null)
               result = caseJavaResource(javaFile);
            if (result == null)
               result = caseFile(javaFile);
            if (result == null)
               result = caseResource(javaFile);
            if (result == null)
               result = caseJavaElement(javaFile);
            if (result == null)
               result = caseNamed(javaFile);
            if (result == null)
               result = caseXAnnotatable(javaFile);
            if (result == null)
               result = caseExtendable(javaFile);
            if (result == null)
               result = caseAnnotatable(javaFile);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_CLASS :
         {
            JavaClass javaClass = (JavaClass) theEObject;
            T result = caseJavaClass(javaClass);
            if (result == null)
               result = caseJavaFile(javaClass);
            if (result == null)
               result = caseJavaResource(javaClass);
            if (result == null)
               result = caseFile(javaClass);
            if (result == null)
               result = caseResource(javaClass);
            if (result == null)
               result = caseJavaElement(javaClass);
            if (result == null)
               result = caseNamed(javaClass);
            if (result == null)
               result = caseXAnnotatable(javaClass);
            if (result == null)
               result = caseExtendable(javaClass);
            if (result == null)
               result = caseAnnotatable(javaClass);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_COMPILATION_UNIT :
         {
            JavaCompilationUnit javaCompilationUnit = (JavaCompilationUnit) theEObject;
            T result = caseJavaCompilationUnit(javaCompilationUnit);
            if (result == null)
               result = caseJavaFile(javaCompilationUnit);
            if (result == null)
               result = caseJavaResource(javaCompilationUnit);
            if (result == null)
               result = caseFile(javaCompilationUnit);
            if (result == null)
               result = caseResource(javaCompilationUnit);
            if (result == null)
               result = caseJavaElement(javaCompilationUnit);
            if (result == null)
               result = caseNamed(javaCompilationUnit);
            if (result == null)
               result = caseXAnnotatable(javaCompilationUnit);
            if (result == null)
               result = caseExtendable(javaCompilationUnit);
            if (result == null)
               result = caseAnnotatable(javaCompilationUnit);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.IMPORT_DECLARATION :
         {
            ImportDeclaration importDeclaration = (ImportDeclaration) theEObject;
            T result = caseImportDeclaration(importDeclaration);
            if (result == null)
               result = caseJavaElement(importDeclaration);
            if (result == null)
               result = caseNamed(importDeclaration);
            if (result == null)
               result = caseXAnnotatable(importDeclaration);
            if (result == null)
               result = caseExtendable(importDeclaration);
            if (result == null)
               result = caseAnnotatable(importDeclaration);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_TYPE :
         {
            JavaType javaType = (JavaType) theEObject;
            T result = caseJavaType(javaType);
            if (result == null)
               result = caseQualifiedJavaElement(javaType);
            if (result == null)
               result = caseJavaElement(javaType);
            if (result == null)
               result = caseNamed(javaType);
            if (result == null)
               result = caseXAnnotatable(javaType);
            if (result == null)
               result = caseExtendable(javaType);
            if (result == null)
               result = caseAnnotatable(javaType);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_RESOURCE :
         {
            JavaResource javaResource = (JavaResource) theEObject;
            T result = caseJavaResource(javaResource);
            if (result == null)
               result = caseResource(javaResource);
            if (result == null)
               result = caseJavaElement(javaResource);
            if (result == null)
               result = caseNamed(javaResource);
            if (result == null)
               result = caseXAnnotatable(javaResource);
            if (result == null)
               result = caseExtendable(javaResource);
            if (result == null)
               result = caseAnnotatable(javaResource);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.RESOURCE_VISITOR :
         {
            ResourceVisitor resourceVisitor = (ResourceVisitor) theEObject;
            T result = caseResourceVisitor(resourceVisitor);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         default :
            return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Named</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Named</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseNamed(Named object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Resource</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Resource</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseResource(Resource object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Directory</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Directory</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseDirectory(Directory object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>File</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>File</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseFile(File object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Element</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Element</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaElement(JavaElement object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Qualified Java Element</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Qualified Java Element</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseQualifiedJavaElement(QualifiedJavaElement object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Resource Bundle</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Resource Bundle</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaResourceBundle(JavaResourceBundle object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Project</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Project</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaProject(JavaProject object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Archive</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Archive</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaArchive(JavaArchive object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Resource Directory</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Resource Directory</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaResourceDirectory(JavaResourceDirectory object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Resources Root</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Resources Root</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaResourcesRoot(JavaResourcesRoot object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Package</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Package</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaPackage(JavaPackage object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java File</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java File</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaFile(JavaFile object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Class</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Class</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaClass(JavaClass object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Compilation Unit</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Compilation Unit</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaCompilationUnit(JavaCompilationUnit object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Import Declaration</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Import Declaration</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseImportDeclaration(ImportDeclaration object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Type</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Type</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaType(JavaType object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Resource</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Resource</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaResource(JavaResource object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Resource Visitor</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Resource Visitor</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseResourceVisitor(ResourceVisitor object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Extendable</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Extendable</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseExtendable(Extendable object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Annotatable</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Annotatable</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseAnnotatable(Annotatable object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>XAnnotatable</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>XAnnotatable</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseXAnnotatable(XAnnotatable object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch, but this is the last case anyway.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject)
    * @generated
    */
   public T defaultCase(EObject object)
   {
      return null;
   }

} // JavaModelSwitch
