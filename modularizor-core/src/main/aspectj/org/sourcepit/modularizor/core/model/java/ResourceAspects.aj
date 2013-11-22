/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;

import org.sourcepit.modularizor.core.java.internal.impl.ResourceOperations;
import org.sourcepit.modularizor.core.model.java.Resource;
import org.sourcepit.modularizor.core.model.java.ResourceVisitor;

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
