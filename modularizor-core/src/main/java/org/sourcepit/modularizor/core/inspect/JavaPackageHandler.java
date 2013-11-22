/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.inspect;

import static org.sourcepit.modularizor.core.inspect.JavaResourceType.PACKAGE;

import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.utils.path.Path;
import org.sourcepit.modularizor.java.JavaResourcesRoot;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class JavaPackageHandler extends AbstractJavaResourceHandler
{
   private final static Logger LOGGER = LoggerFactory.getLogger(JavaPackageHandler.class);

   public boolean handle(JavaResourcesRoot jResources, JavaResourceType type, ReadWriteLock modelLock, Path path,
      InputStream content)
   {
      if (PACKAGE != type)
      {
         return false;
      }
      try
      {
         getJavaPackage(jResources, modelLock, path);
      }
      catch (ClassCastException e)
      {
         LOGGER.error(path.toString(), e);
         throw e;
      }
      return true;
   }
}
