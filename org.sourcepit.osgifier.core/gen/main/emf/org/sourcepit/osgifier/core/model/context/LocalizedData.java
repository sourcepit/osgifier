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

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Localized Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.context.LocalizedData#getLocale <em>Locale</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.LocalizedData#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getLocalizedData()
 * @model
 * @generated
 */
public interface LocalizedData extends EObject
{
   /**
    * Returns the value of the '<em><b>Locale</b></em>' attribute.
    * The default value is <code>"\"\""</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Locale</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Locale</em>' attribute.
    * @see #setLocale(String)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getLocalizedData_Locale()
    * @model default="\"\"" required="true"
    * @generated
    */
   String getLocale();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.LocalizedData#getLocale <em>Locale</em>}'
    * attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Locale</em>' attribute.
    * @see #getLocale()
    * @generated
    */
   void setLocale(String value);

   /**
    * Returns the value of the '<em><b>Data</b></em>' map.
    * The key is of type {@link java.lang.String},
    * and the value is of type {@link java.lang.String},
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Data</em>' map isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Data</em>' map.
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getLocalizedData_Data()
    * @model mapType=
    *        "org.sourcepit.common.modeling.EStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
    * @generated
    */
   EMap<String, String> getData();

} // LocalizedData
