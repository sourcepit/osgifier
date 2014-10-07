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

import org.sourcepit.osgifier.core.java.internal.impl.JavaResourcesRootOperations;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.JavaType;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect JavaResourcesRootAspects
{
   pointcut getType(JavaResourcesRoot jRoot, String qualifiedPackageName, String typeName, boolean createOnDemand): target(jRoot) && args(qualifiedPackageName, typeName, createOnDemand) && execution(JavaType JavaResourcesRoot.getType(String, String, boolean));

   JavaType around(JavaResourcesRoot jRoot, String qualifiedPackageName, String typeName, boolean createOnDemand) : getType(jRoot, qualifiedPackageName, typeName, createOnDemand)
   {
      return JavaResourcesRootOperations.getType(jRoot, qualifiedPackageName, typeName, createOnDemand);
   }
}
