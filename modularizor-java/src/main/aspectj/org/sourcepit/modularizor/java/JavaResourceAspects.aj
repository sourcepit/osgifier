/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.java;

import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResource;
import org.sourcepit.modularizor.java.JavaResourceDirectory;
import org.sourcepit.modularizor.java.JavaResourceOperations;
import org.sourcepit.modularizor.java.JavaResourcesRoot;


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
