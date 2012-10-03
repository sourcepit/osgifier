/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.internal.impl;

import org.sourcepit.osgify.core.model.java.JavaFile;
import org.sourcepit.osgify.core.model.java.JavaType;

public final class JavaTypeOperations
{
   private JavaTypeOperations()
   {
      super();
   }

   public static JavaFile getFile(JavaType type)
   {
      JavaType current = type;
      while (current.getOuterType() != null)
      {
         current = current.getOuterType();
      }
      return (JavaFile) current.eContainer();
   }
}
