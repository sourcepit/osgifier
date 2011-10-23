/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifyme.core.java;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.modeling.common.Annotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency Node</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifyme.core.java.DependencyNode#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.DependencyNode#isOptional <em>Optional</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.DependencyNode#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.DependencyNode#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.DependencyNode#getParentNode <em>Parent Node</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.DependencyNode#getPackageBundle <em>Package Bundle</em>}</li>
 * <li>{@link org.sourcepit.osgifyme.core.java.DependencyNode#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode()
 * @model
 * @generated
 */
public interface DependencyNode extends Annotatable
{
   /**
    * Returns the value of the '<em><b>Target</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Target</em>' reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Target</em>' reference.
    * @see #setTarget(JavaPackageBundle)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode_Target()
    * @model
    * @generated
    */
   JavaPackageBundle getTarget();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getTarget <em>Target</em>}'
    * reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Target</em>' reference.
    * @see #getTarget()
    * @generated
    */
   void setTarget(JavaPackageBundle value);

   /**
    * Returns the value of the '<em><b>Optional</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Optional</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Optional</em>' attribute.
    * @see #setOptional(boolean)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode_Optional()
    * @model
    * @generated
    */
   boolean isOptional();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.DependencyNode#isOptional <em>Optional</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Optional</em>' attribute.
    * @see #isOptional()
    * @generated
    */
   void setOptional(boolean value);

   /**
    * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgifyme.core.java.DependencyNode}.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getParentNode
    * <em>Parent Node</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Dependencies</em>' containment reference list.
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode_Dependencies()
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getParentNode
    * @model opposite="parentNode" containment="true"
    * @generated
    */
   EList<DependencyNode> getDependencies();

   /**
    * Returns the value of the '<em><b>Enabled</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Enabled</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Enabled</em>' attribute.
    * @see #setEnabled(boolean)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode_Enabled()
    * @model
    * @generated
    */
   boolean isEnabled();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.DependencyNode#isEnabled <em>Enabled</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Enabled</em>' attribute.
    * @see #isEnabled()
    * @generated
    */
   void setEnabled(boolean value);

   /**
    * Returns the value of the '<em><b>Parent Node</b></em>' container reference.
    * It is bidirectional and its opposite is '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getDependencies
    * <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Parent Node</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Parent Node</em>' container reference.
    * @see #setParentNode(DependencyNode)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode_ParentNode()
    * @see org.sourcepit.osgifyme.core.java.DependencyNode#getDependencies
    * @model opposite="dependencies"
    * @generated
    */
   DependencyNode getParentNode();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getParentNode <em>Parent Node</em>}'
    * container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Parent Node</em>' container reference.
    * @see #getParentNode()
    * @generated
    */
   void setParentNode(DependencyNode value);

   /**
    * Returns the value of the '<em><b>Package Bundle</b></em>' container reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifyme.core.java.JavaPackageBundle#getDependencies <em>Dependencies</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Package Bundle</em>' container reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Package Bundle</em>' container reference.
    * @see #setPackageBundle(JavaPackageBundle)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode_PackageBundle()
    * @see org.sourcepit.osgifyme.core.java.JavaPackageBundle#getDependencies
    * @model opposite="dependencies"
    * @generated
    */
   JavaPackageBundle getPackageBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getPackageBundle
    * <em>Package Bundle</em>}' container reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Package Bundle</em>' container reference.
    * @see #getPackageBundle()
    * @generated
    */
   void setPackageBundle(JavaPackageBundle value);

   /**
    * Returns the value of the '<em><b>Scope</b></em>' attribute.
    * The default value is <code>"compile"</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Scope</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Scope</em>' attribute.
    * @see #setScope(String)
    * @see org.sourcepit.osgifyme.core.java.JavaModelPackage#getDependencyNode_Scope()
    * @model default="compile"
    * @generated
    */
   String getScope();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifyme.core.java.DependencyNode#getScope <em>Scope</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Scope</em>' attribute.
    * @see #getScope()
    * @generated
    */
   void setScope(String value);

} // DependencyNode
