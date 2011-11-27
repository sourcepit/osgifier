/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java.internal.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.sourcepit.modeling.common.Annotatable;
import org.sourcepit.osgifyme.core.java.DependencyNode;
import org.sourcepit.osgifyme.core.java.FullyQualified;
import org.sourcepit.osgifyme.core.java.ImportDeclaration;
import org.sourcepit.osgifyme.core.java.JavaArchive;
import org.sourcepit.osgifyme.core.java.JavaClass;
import org.sourcepit.osgifyme.core.java.JavaCompilationUnit;
import org.sourcepit.osgifyme.core.java.JavaModel;
import org.sourcepit.osgifyme.core.java.JavaModelPackage;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaPackageBundle;
import org.sourcepit.osgifyme.core.java.JavaPackageRoot;
import org.sourcepit.osgifyme.core.java.JavaProject;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.osgifyme.core.java.JavaTypeRoot;

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
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage
 * @generated
 */
public class JavaModelSwitch<T> extends Switch<T>
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
    * Checks whether this is a switch for the given package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @parameter ePackage the package in question.
    * @return whether this is a switch for the given package.
    * @generated
    */
   @Override
   protected boolean isSwitchFor(EPackage ePackage)
   {
      return ePackage == modelPackage;
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   @Override
   protected T doSwitch(int classifierID, EObject theEObject)
   {
      switch (classifierID)
      {
         case JavaModelPackage.JAVA_TYPE :
         {
            JavaType javaType = (JavaType) theEObject;
            T result = caseJavaType(javaType);
            if (result == null)
               result = caseAnnotatable(javaType);
            if (result == null)
               result = caseFullyQualified(javaType);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_COMPILATION_UNIT :
         {
            JavaCompilationUnit javaCompilationUnit = (JavaCompilationUnit) theEObject;
            T result = caseJavaCompilationUnit(javaCompilationUnit);
            if (result == null)
               result = caseJavaTypeRoot(javaCompilationUnit);
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
               result = caseAnnotatable(importDeclaration);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_TYPE_ROOT :
         {
            JavaTypeRoot javaTypeRoot = (JavaTypeRoot) theEObject;
            T result = caseJavaTypeRoot(javaTypeRoot);
            if (result == null)
               result = caseAnnotatable(javaTypeRoot);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_CLASS :
         {
            JavaClass javaClass = (JavaClass) theEObject;
            T result = caseJavaClass(javaClass);
            if (result == null)
               result = caseJavaTypeRoot(javaClass);
            if (result == null)
               result = caseAnnotatable(javaClass);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_PACKAGE :
         {
            JavaPackage javaPackage = (JavaPackage) theEObject;
            T result = caseJavaPackage(javaPackage);
            if (result == null)
               result = caseAnnotatable(javaPackage);
            if (result == null)
               result = caseFullyQualified(javaPackage);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_PACKAGE_BUNDLE :
         {
            JavaPackageBundle javaPackageBundle = (JavaPackageBundle) theEObject;
            T result = caseJavaPackageBundle(javaPackageBundle);
            if (result == null)
               result = caseAnnotatable(javaPackageBundle);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_ARCHIVE :
         {
            JavaArchive javaArchive = (JavaArchive) theEObject;
            T result = caseJavaArchive(javaArchive);
            if (result == null)
               result = caseJavaPackageBundle(javaArchive);
            if (result == null)
               result = caseAnnotatable(javaArchive);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_PROJECT :
         {
            JavaProject javaProject = (JavaProject) theEObject;
            T result = caseJavaProject(javaProject);
            if (result == null)
               result = caseJavaPackageBundle(javaProject);
            if (result == null)
               result = caseAnnotatable(javaProject);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_MODEL :
         {
            JavaModel javaModel = (JavaModel) theEObject;
            T result = caseJavaModel(javaModel);
            if (result == null)
               result = caseAnnotatable(javaModel);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.DEPENDENCY_NODE :
         {
            DependencyNode dependencyNode = (DependencyNode) theEObject;
            T result = caseDependencyNode(dependencyNode);
            if (result == null)
               result = caseAnnotatable(dependencyNode);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.FULLY_QUALIFIED :
         {
            FullyQualified fullyQualified = (FullyQualified) theEObject;
            T result = caseFullyQualified(fullyQualified);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         case JavaModelPackage.JAVA_PACKAGE_ROOT :
         {
            JavaPackageRoot javaPackageRoot = (JavaPackageRoot) theEObject;
            T result = caseJavaPackageRoot(javaPackageRoot);
            if (result == null)
               result = caseAnnotatable(javaPackageRoot);
            if (result == null)
               result = defaultCase(theEObject);
            return result;
         }
         default :
            return defaultCase(theEObject);
      }
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
    * Returns the result of interpreting the object as an instance of '<em>Java Type Root</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Type Root</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaTypeRoot(JavaTypeRoot object)
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
    * Returns the result of interpreting the object as an instance of '<em>Java Package Bundle</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Package Bundle</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaPackageBundle(JavaPackageBundle object)
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
    * Returns the result of interpreting the object as an instance of '<em>Java Model</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Model</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaModel(JavaModel object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Dependency Node</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Dependency Node</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseDependencyNode(DependencyNode object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Fully Qualified</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Fully Qualified</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseFullyQualified(FullyQualified object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Java Package Root</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * 
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Java Package Root</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseJavaPackageRoot(JavaPackageRoot object)
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
   @Override
   public T defaultCase(EObject object)
   {
      return null;
   }

} // JavaModelSwitch
