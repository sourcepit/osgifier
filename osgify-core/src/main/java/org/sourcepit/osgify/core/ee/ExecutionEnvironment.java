/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee;

import java.util.Collections;
import java.util.List;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.constraints.Pattern;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.common.utils.props.PropertiesUtils;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public final class ExecutionEnvironment implements Comparable<ExecutionEnvironment>
{
   private static final String RELEASE_DATE_FORMAT = "\\d\\d\\d\\d-\\d\\d-\\d\\d";

   private final String id;
   private final String releaseDate;
   private final int maxClassVersion;
   private final PropertiesMap properties;
   private final List<String> packages;

   public ExecutionEnvironment(@NotNull String id, @Pattern(regexp = RELEASE_DATE_FORMAT) String releaseDate,
      int maxClassVersion, @NotNull PropertiesMap properties, @NotNull List<String> packages)
   {
      this.id = id;
      this.releaseDate = releaseDate;
      this.maxClassVersion = maxClassVersion;
      this.properties = PropertiesUtils.unmodifiablePropertiesMap(properties);
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

   public int getMaxClassVersion()
   {
      return maxClassVersion;
   }

   public PropertiesMap getProperties()
   {
      return properties;
   }

   public List<String> getPackages()
   {
      return packages;
   }


   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + maxClassVersion;
      result = prime * result + ((packages == null) ? 0 : packages.hashCode());
      result = prime * result + ((properties == null) ? 0 : properties.hashCode());
      result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
      return result;
   }

   // CSOFF
   @Override
   public boolean equals(Object obj)
   // CSON
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ExecutionEnvironment other = (ExecutionEnvironment) obj;
      if (id == null)
      {
         if (other.id != null)
            return false;
      }
      else if (!id.equals(other.id))
         return false;
      if (maxClassVersion != other.maxClassVersion)
         return false;
      if (packages == null)
      {
         if (other.packages != null)
            return false;
      }
      else if (!packages.equals(other.packages))
         return false;
      if (properties == null)
      {
         if (other.properties != null)
            return false;
      }
      else if (!properties.equals(other.properties))
         return false;
      if (releaseDate == null)
      {
         if (other.releaseDate != null)
            return false;
      }
      else if (!releaseDate.equals(other.releaseDate))
         return false;
      return true;
   }

   public int compareTo(ExecutionEnvironment other)
   {
      final int delta1 = computeDelta(this, other);
      final int delta2 = computeDelta(other, this);
      if (delta1 == delta2)
      {
         return 0;
      }
      return delta1 > delta2 ? 1 : -1;
   }

   private static int computeDelta(ExecutionEnvironment _this, ExecutionEnvironment other)
   {
      final int max1 = _this.getMaxClassVersion();
      final int max2 = other.getMaxClassVersion();
      if (max1 >= max2)
      {
         final List<String> p1 = _this.getPackages();
         final List<String> p2 = other.getPackages();

         final int pDiff = p1.size() - p2.size();
         if (pDiff != 0)
         {
            return pDiff;
         }

         final String r1 = _this.getReleaseDate();
         final String r2 = other.getReleaseDate();
         return r1.compareTo(r2);
      }
      return -1;
   }

   @Override
   public String toString()
   {
      return "ExecutionEnvironment [id=" + id + ", releaseDate=" + releaseDate + ", maxClassVersion=" + maxClassVersion
         + ", properties=" + properties + ", packages=" + packages + "]";
   }

}
