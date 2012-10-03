/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.model.java;

import org.sourcepit.osgify.core.java.internal.impl.QualifiedJavaElementOperations;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public aspect QualifiedJavaElementAspects
{
   pointcut getQualifiedName(QualifiedJavaElement qualifiedNamed): target(qualifiedNamed) && args() && execution(String getQualifiedName());

   String around(QualifiedJavaElement qualifiedNamed) : getQualifiedName(qualifiedNamed){
      return QualifiedJavaElementOperations.getQualifiedName(qualifiedNamed);
   }

}
