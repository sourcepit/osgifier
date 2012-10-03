/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import org.sourcepit.osgify.core.model.java.JavaFile;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.Resource;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class TypeVisitor implements ResourceVisitor
{
   public boolean visit(Resource resource)
   {
      if (resource instanceof JavaFile)
      {
         final JavaType jType = ((JavaFile) resource).getType();
         if (jType != null)
         {
            visit(jType);
         }
      }
      return resource instanceof JavaResourceDirectory;
   }

   protected abstract void visit(JavaType jType);
}
