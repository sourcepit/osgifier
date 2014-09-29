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

import org.sourcepit.osgifier.core.java.internal.impl.ResourceOperations;
import org.sourcepit.osgifier.core.model.java.Resource;
import org.sourcepit.osgifier.core.model.java.ResourceVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect ResourceAspects
{
   pointcut accept(Resource resource, ResourceVisitor visitor): target(resource) && args(visitor) && execution(void Resource.accept(ResourceVisitor));

   void around(Resource resource, ResourceVisitor visitor) : accept(resource, visitor){
      ResourceOperations.accept(resource, visitor);
   }
}
