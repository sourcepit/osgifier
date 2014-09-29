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

package org.sourcepit.osgifier.core.model.java;

import org.sourcepit.osgifier.core.model.java.JavaFile;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaType;
import org.sourcepit.osgifier.core.model.java.Resource;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class JavaTypeVisitor implements ResourceVisitor
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
