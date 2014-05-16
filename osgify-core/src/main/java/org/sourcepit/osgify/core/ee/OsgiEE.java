/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee;
public final class OsgiEE
{
   private final String name;
   private final String version;

   public OsgiEE(String name, String version)
   {
      this.name = name;
      this.version = version;
   }

   public String getName()
   {
      return name;
   }

   public String getVersion()
   {
      return version;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
      result = prime * result + ((version == null) ? 0 : version.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      OsgiEE other = (OsgiEE) obj;
      if (name == null)
      {
         if (other.name != null)
         {
            return false;
         }
      }
      else if (!name.equals(other.name))
      {
         return false;
      }
      if (version == null)
      {
         if (other.version != null)
         {
            return false;
         }
      }
      else if (!version.equals(other.version))
      {
         return false;
      }
      return true;
   }

   @Override
   public String toString()
   {
      final StringBuilder builder = new StringBuilder();
      builder.append("OsgiEE [name=");
      builder.append(name);
      builder.append(", version=");
      builder.append(version);
      builder.append("]");
      return builder.toString();
   }


}
