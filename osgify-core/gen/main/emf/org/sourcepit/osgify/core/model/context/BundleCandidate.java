/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.context;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.modeling.common.XAnnotatable;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Candidate</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getLocation <em>Location</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getSymbolicName <em>Symbolic Name</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.context.BundleCandidate#isNativeBundle <em>Native Bundle</em>}</li>
 * <li>{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getManifest <em>Manifest</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate()
 * @model
 * @generated
 */
public interface BundleCandidate extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Location</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Location</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Location</em>' attribute.
    * @see #setLocation(File)
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate_Location()
    * @model dataType="org.sourcepit.modeling.common.EFile" required="true"
    * @generated
    */
   File getLocation();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getLocation
    * <em>Location</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Location</em>' attribute.
    * @see #getLocation()
    * @generated
    */
   void setLocation(File value);

   /**
    * Returns the value of the '<em><b>Content</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Content</em>' containment reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Content</em>' containment reference.
    * @see #setContent(JavaResourceBundle)
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate_Content()
    * @model containment="true" required="true"
    * @generated
    */
   JavaResourceBundle getContent();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getContent <em>Content</em>}
    * ' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Content</em>' containment reference.
    * @see #getContent()
    * @generated
    */
   void setContent(JavaResourceBundle value);

   /**
    * Returns the value of the '<em><b>Dependencies</b></em>' containment reference list.
    * The list contents are of type {@link org.sourcepit.osgify.core.model.context.BundleReference}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Dependencies</em>' containment reference list.
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate_Dependencies()
    * @model containment="true"
    * @generated
    */
   EList<BundleReference> getDependencies();

   /**
    * Returns the value of the '<em><b>Version</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Version</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Version</em>' attribute.
    * @see #setVersion(Version)
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate_Version()
    * @model dataType="org.sourcepit.common.manifest.osgi.Version"
    * @generated
    */
   Version getVersion();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getVersion <em>Version</em>}
    * ' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Version</em>' attribute.
    * @see #getVersion()
    * @generated
    */
   void setVersion(Version value);

   /**
    * Returns the value of the '<em><b>Symbolic Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Symbolic Name</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Symbolic Name</em>' attribute.
    * @see #setSymbolicName(String)
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate_SymbolicName()
    * @model
    * @generated
    */
   String getSymbolicName();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getSymbolicName
    * <em>Symbolic Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Symbolic Name</em>' attribute.
    * @see #getSymbolicName()
    * @generated
    */
   void setSymbolicName(String value);

   /**
    * Returns the value of the '<em><b>Native Bundle</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Native Bundle</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Native Bundle</em>' attribute.
    * @see #setNativeBundle(boolean)
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate_NativeBundle()
    * @model
    * @generated
    */
   boolean isNativeBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.context.BundleCandidate#isNativeBundle
    * <em>Native Bundle</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Native Bundle</em>' attribute.
    * @see #isNativeBundle()
    * @generated
    */
   void setNativeBundle(boolean value);

   /**
    * Returns the value of the '<em><b>Manifest</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Manifest</em>' containment reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Manifest</em>' containment reference.
    * @see #setManifest(BundleManifest)
    * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getBundleCandidate_Manifest()
    * @model containment="true"
    * @generated
    */
   BundleManifest getManifest();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.core.model.context.BundleCandidate#getManifest
    * <em>Manifest</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Manifest</em>' containment reference.
    * @see #getManifest()
    * @generated
    */
   void setManifest(BundleManifest value);

} // BundleCandidate
