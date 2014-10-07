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

import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.common.modeling.XAnnotatable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bundle Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleReference#getVersionRange <em>Version Range</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleReference#isOptional <em>Optional</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleReference#getTarget <em>Target</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleReference#isProvided <em>Provided</em>}</li>
 * <li>{@link org.sourcepit.osgifier.core.model.context.BundleReference#getEmbedInstruction <em>Embed Instruction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleReference()
 * @model
 * @generated
 */
public interface BundleReference extends XAnnotatable
{
   /**
    * Returns the value of the '<em><b>Version Range</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Version Range</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Version Range</em>' attribute.
    * @see #setVersionRange(VersionRange)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleReference_VersionRange()
    * @model dataType="org.sourcepit.common.manifest.osgi.VersionRange"
    * @generated
    */
   VersionRange getVersionRange();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleReference#getVersionRange
    * <em>Version Range</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Version Range</em>' attribute.
    * @see #getVersionRange()
    * @generated
    */
   void setVersionRange(VersionRange value);

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
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleReference_Optional()
    * @model
    * @generated
    */
   boolean isOptional();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleReference#isOptional
    * <em>Optional</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Optional</em>' attribute.
    * @see #isOptional()
    * @generated
    */
   void setOptional(boolean value);

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
    * @see #setTarget(BundleCandidate)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleReference_Target()
    * @model
    * @generated
    */
   BundleCandidate getTarget();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleReference#getTarget <em>Target</em>}
    * ' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Target</em>' reference.
    * @see #getTarget()
    * @generated
    */
   void setTarget(BundleCandidate value);

   /**
    * Returns the value of the '<em><b>Provided</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Provided</em>' attribute isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Provided</em>' attribute.
    * @see #setProvided(boolean)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleReference_Provided()
    * @model
    * @generated
    */
   boolean isProvided();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleReference#isProvided
    * <em>Provided</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Provided</em>' attribute.
    * @see #isProvided()
    * @generated
    */
   void setProvided(boolean value);

   /**
    * Returns the value of the '<em><b>Embed Instruction</b></em>' attribute.
    * The literals are from the enumeration {@link org.sourcepit.osgifier.core.model.context.EmbedInstruction}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Embed Instruction</em>' attribute isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Embed Instruction</em>' attribute.
    * @see org.sourcepit.osgifier.core.model.context.EmbedInstruction
    * @see #setEmbedInstruction(EmbedInstruction)
    * @see org.sourcepit.osgifier.core.model.context.ContextModelPackage#getBundleReference_EmbedInstruction()
    * @model required="true"
    * @generated
    */
   EmbedInstruction getEmbedInstruction();

   /**
    * Sets the value of the '{@link org.sourcepit.osgifier.core.model.context.BundleReference#getEmbedInstruction
    * <em>Embed Instruction</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @param value the new value of the '<em>Embed Instruction</em>' attribute.
    * @see org.sourcepit.osgifier.core.model.context.EmbedInstruction
    * @see #getEmbedInstruction()
    * @generated
    */
   void setEmbedInstruction(EmbedInstruction value);

} // BundleReference
