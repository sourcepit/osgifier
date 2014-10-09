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

package org.sourcepit.osgifier.core.model.context;

import java.io.File;

import org.eclipse.emf.common.util.EList;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.modeling.XAnnotatable;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Candidate</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocation <em>Location</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getContent <em>Content</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getDependencies <em>Dependencies</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getVersion <em>Version</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getSymbolicName <em>Symbolic Name</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#isNativeBundle <em>Native Bundle</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getManifest <em>Manifest</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocalization <em>Localization</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getSourceBundle <em>Source Bundle</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getTargetBundle <em>Target Bundle</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate()
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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_Location()
    * @model dataType="org.sourcepit.common.modeling.EFile" required="true"
    * @generated
    */
   File getLocation();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocation
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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_Content()
    * @model containment="true" required="true"
    * @generated
    */
   JavaResourceBundle getContent();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getContent
    * <em>Content</em>}' containment reference.
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
    * The list contents are of type {@link org.sourcepit.osgifier.core.model.context.BundleReference}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Dependencies</em>' containment reference list isn't clear, there really should be more
    * of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Dependencies</em>' containment reference list.
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_Dependencies()
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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_Version()
    * @model dataType="org.sourcepit.common.manifest.osgi.Version"
    * @generated
    */
   Version getVersion();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getVersion
    * <em>Version</em>}' attribute.
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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_SymbolicName()
    * @model
    * @generated
    */
   String getSymbolicName();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getSymbolicName
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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_NativeBundle()
    * @model
    * @generated
    */
   boolean isNativeBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#isNativeBundle
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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_Manifest()
    * @model containment="true"
    * @generated
    */
   BundleManifest getManifest();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getManifest
    * <em>Manifest</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Manifest</em>' containment reference.
    * @see #getManifest()
    * @generated
    */
   void setManifest(BundleManifest value);

   /**
    * Returns the value of the '<em><b>Localization</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Localization</em>' containment reference isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Localization</em>' containment reference.
    * @see #setLocalization(BundleLocalization)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_Localization()
    * @model containment="true"
    * @generated
    */
   BundleLocalization getLocalization();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getLocalization
    * <em>Localization</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Localization</em>' containment reference.
    * @see #getLocalization()
    * @generated
    */
   void setLocalization(BundleLocalization value);

   /**
    * Returns the value of the '<em><b>Source Bundle</b></em>' reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getTargetBundle <em>Target Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Source Bundle</em>' reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Source Bundle</em>' reference.
    * @see #setSourceBundle(BundleCandidate)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_SourceBundle()
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getTargetBundle
    * @model opposite="targetBundle"
    * @generated
    */
   BundleCandidate getSourceBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getSourceBundle
    * <em>Source Bundle</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Source Bundle</em>' reference.
    * @see #getSourceBundle()
    * @generated
    */
   void setSourceBundle(BundleCandidate value);

   /**
    * Returns the value of the '<em><b>Target Bundle</b></em>' reference.
    * It is bidirectional and its opposite is '
    * {@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getSourceBundle <em>Source Bundle</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Target Bundle</em>' reference isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Target Bundle</em>' reference.
    * @see #setTargetBundle(BundleCandidate)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleCandidate_TargetBundle()
    * @see org.sourcepit.osgifier.core.model.context.BundleCandidate#getSourceBundle
    * @model opposite="sourceBundle"
    * @generated
    */
   BundleCandidate getTargetBundle();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleCandidate#getTargetBundle
    * <em>Target Bundle</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Target Bundle</em>' reference.
    * @see #getTargetBundle()
    * @generated
    */
   void setTargetBundle(BundleCandidate value);

} // BundleCandidate
