/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;
public enum PackageImportType
{
   PUBLIC_IMPORT("publicImport"), INTERNAL_IMPORT("internalImport"), SELF_IMPORT("selfImport");

   private final String literal;

   private PackageImportType(String literal)
   {
      this.literal = literal;
   }

   public String literal()
   {
      return literal;
   }

   public static PackageImportType parse(String literal)
   {
      for (PackageImportType type : values())
      {
         if (type.literal().equalsIgnoreCase(literal))
         {
            return type;
         }
      }
      throw new IllegalArgumentException("Unknown import type: " + literal);
   }

}