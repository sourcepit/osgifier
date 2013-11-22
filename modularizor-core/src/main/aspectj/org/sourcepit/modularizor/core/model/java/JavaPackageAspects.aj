/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.model.java;

import org.sourcepit.modularizor.core.model.java.JavaPackage;
import org.sourcepit.modularizor.core.model.java.JavaPackageOperations;
import org.sourcepit.modularizor.core.model.java.JavaResourcesType;


/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect JavaPackageAspects
{
   pointcut getResourcesType(JavaPackage jPackage) : target(jPackage) && execution(JavaResourcesType JavaPackage.getResourcesType());

   JavaResourcesType around(JavaPackage jPackage) : getResourcesType(jPackage)
   {
      return JavaPackageOperations.getResourcesType(jPackage);
   }
}
