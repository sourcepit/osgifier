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

import org.sourcepit.osgifier.core.java.internal.impl.JavaResourceOperations;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResource;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;


/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect JavaResourceAspects
{
   pointcut getParentDirectory(JavaResource jResource) : target(jResource) && execution(JavaResourceDirectory JavaResource.getParentDirectory());

   pointcut getParentPackage(JavaResource jResource) : target(jResource) && execution(JavaPackage JavaResource.getParentPackage());

   pointcut getResourcesRoot(JavaResource jResource) : target(jResource) && execution(JavaResourcesRoot JavaResource.getResourcesRoot());

   JavaResourceDirectory around(JavaResource jResource) : getParentDirectory(jResource){
      return JavaResourceOperations.getParentDirectory(jResource);
   }

   JavaPackage around(JavaResource jResource) : getParentPackage(jResource){
      return JavaResourceOperations.getParentPackage(jResource);
   }

   JavaResourcesRoot around(JavaResource jResource) : getResourcesRoot(jResource){
      return JavaResourceOperations.getResourcesRoot(jResource);
   }
}
