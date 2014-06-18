/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.ee;

import java.util.Collections;
import java.util.List;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.constraints.Pattern;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class ExecutionEnvironment implements Comparable<ExecutionEnvironment>
{
   private static final String RELEASE_DATE_FORMAT = "\\d\\d\\d\\d-\\d\\d-\\d\\d";

   private final String id;
   private final String releaseDate;
   private final float maxClassVersion;
   private final OsgiEE osgiEE;
   private final List<String> packages;

   public ExecutionEnvironment(@NotNull String id, @Pattern(regexp = RELEASE_DATE_FORMAT) String releaseDate,
      float maxClassVersion, @NotNull OsgiEE osgiEE, @NotNull List<String> packages)
   {
      this.id = id;
      this.releaseDate = releaseDate;
      this.maxClassVersion = maxClassVersion;
      this.osgiEE = osgiEE;
      this.packages = Collections.unmodifiableList(packages);
   }

   public String getId()
   {
      return id;
   }

   public String getReleaseDate()
   {
      return releaseDate;
   }

   public float getMaxClassVersion()
   {
      return maxClassVersion;
   }

   public OsgiEE getOsgiEE()
   {
      return osgiEE;
   }

   public List<String> getPackages()
   {
      return packages;
   }

   public boolean isCompatibleWith(@NotNull ExecutionEnvironment ee2)
   {
      final float max1 = getMaxClassVersion();
      final float max2 = ee2.getMaxClassVersion();
      if (max1 >= max2)
      {
         final List<String> p1 = getPackages();
         final List<String> p2 = ee2.getPackages();
         return p1.containsAll(p2);
      }
      return false;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + Float.floatToIntBits(maxClassVersion);
      result = prime * result + ((osgiEE == null) ? 0 : osgiEE.hashCode());
      result = prime * result + ((packages == null) ? 0 : packages.hashCode());
      result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
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
      ExecutionEnvironment other = (ExecutionEnvironment) obj;
      if (id == null)
      {
         if (other.id != null)
         {
            return false;
         }
      }
      else if (!id.equals(other.id))
      {
         return false;
      }
      if (Float.floatToIntBits(maxClassVersion) != Float.floatToIntBits(other.maxClassVersion))
      {
         return false;
      }
      if (osgiEE == null)
      {
         if (other.osgiEE != null)
         {
            return false;
         }
      }
      else if (!osgiEE.equals(other.osgiEE))
      {
         return false;
      }
      if (packages == null)
      {
         if (other.packages != null)
         {
            return false;
         }
      }
      else if (!packages.equals(other.packages))
      {
         return false;
      }
      if (releaseDate == null)
      {
         if (other.releaseDate != null)
         {
            return false;
         }
      }
      else if (!releaseDate.equals(other.releaseDate))
      {
         return false;
      }
      return true;
   }

   @Override
   public int compareTo(ExecutionEnvironment other)
   {
      final int maxClass = Float.compare(getMaxClassVersion(), other.getMaxClassVersion());
      if (maxClass != 0)
      {
         return maxClass;
      }

      final int packages1 = getPackages().size();
      final int packages2 = other.getPackages().size();
      final int packages = packages1 < packages2 ? -1 : (packages1 == packages2 ? 0 : 1);
      if (packages != 0)
      {
         return packages;
      }

      final int date = getReleaseDate().compareTo(other.getReleaseDate());
      if (date != 0)
      {
         return date;
      }

      return getId().compareTo(other.getId());
   }

   @Override
   public String toString()
   {
      return "ExecutionEnvironment [id=" + id + ", releaseDate=" + releaseDate + ", maxClassVersion=" + maxClassVersion
         + ", packages=" + packages + "]";
   }

}
