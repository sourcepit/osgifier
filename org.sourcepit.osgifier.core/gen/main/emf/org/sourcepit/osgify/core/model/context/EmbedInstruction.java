/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.context;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Embed Instruction</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * 
 * @see org.sourcepit.osgify.core.model.context.ContextModelPackage#getEmbedInstruction()
 * @model
 * @generated
 */
public enum EmbedInstruction implements Enumerator
{
   /**
    * The '<em><b>NOT</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #NOT_VALUE
    * @generated
    * @ordered
    */
   NOT(0, "NOT", "not"),

   /**
    * The '<em><b>UNPACKED</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #UNPACKED_VALUE
    * @generated
    * @ordered
    */
   UNPACKED(1, "UNPACKED", "unpacked"),

   /**
    * The '<em><b>PACKED</b></em>' literal object.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @see #PACKED_VALUE
    * @generated
    * @ordered
    */
   PACKED(2, "PACKED", "packed");

   /**
    * The '<em><b>NOT</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>NOT</b></em>' literal object isn't clear, there really should be more of a description
    * here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @see #NOT
    * @model literal="not"
    * @generated
    * @ordered
    */
   public static final int NOT_VALUE = 0;

   /**
    * The '<em><b>UNPACKED</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>UNPACKED</b></em>' literal object isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @see #UNPACKED
    * @model literal="unpacked"
    * @generated
    * @ordered
    */
   public static final int UNPACKED_VALUE = 1;

   /**
    * The '<em><b>PACKED</b></em>' literal value.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of '<em><b>PACKED</b></em>' literal object isn't clear, there really should be more of a
    * description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @see #PACKED
    * @model literal="packed"
    * @generated
    * @ordered
    */
   public static final int PACKED_VALUE = 2;

   /**
    * An array of all the '<em><b>Embed Instruction</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private static final EmbedInstruction[] VALUES_ARRAY = new EmbedInstruction[] { NOT, UNPACKED, PACKED, };

   /**
    * A public read-only list of all the '<em><b>Embed Instruction</b></em>' enumerators.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static final List<EmbedInstruction> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

   /**
    * Returns the '<em><b>Embed Instruction</b></em>' literal with the specified literal value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static EmbedInstruction get(String literal)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         EmbedInstruction result = VALUES_ARRAY[i];
         if (result.toString().equals(literal))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Embed Instruction</b></em>' literal with the specified name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static EmbedInstruction getByName(String name)
   {
      for (int i = 0; i < VALUES_ARRAY.length; ++i)
      {
         EmbedInstruction result = VALUES_ARRAY[i];
         if (result.getName().equals(name))
         {
            return result;
         }
      }
      return null;
   }

   /**
    * Returns the '<em><b>Embed Instruction</b></em>' literal with the specified integer value.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public static EmbedInstruction get(int value)
   {
      switch (value)
      {
         case NOT_VALUE :
            return NOT;
         case UNPACKED_VALUE :
            return UNPACKED;
         case PACKED_VALUE :
            return PACKED;
      }
      return null;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private final int value;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private final String name;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private final String literal;

   /**
    * Only this class can construct instances.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   private EmbedInstruction(int value, String name, String literal)
   {
      this.value = value;
      this.name = name;
      this.literal = literal;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public int getValue()
   {
      return value;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   public String getLiteral()
   {
      return literal;
   }

   /**
    * Returns the literal value of the enumerator, which is its string representation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * 
    * @generated
    */
   @Override
   public String toString()
   {
      return literal;
   }

} // EmbedInstruction
