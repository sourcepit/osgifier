/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.headermod;
public class SetHeaderModification
{
   private String name, value, before, after;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getValue()
   {
      return value;
   }

   public void setValue(String value)
   {
      this.value = value;
   }

   public String getBefore()
   {
      return before;
   }

   public void setBefore(String before)
   {
      this.before = before;
   }

   public String getAfter()
   {
      return after;
   }

   public void setAfter(String after)
   {
      this.after = after;
   }
}
