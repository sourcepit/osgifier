/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.util;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.sourcepit.osgifier.core.inspect.ResourceVisitor;
import org.sourcepit.osgifier.core.util.RelativeDirectoryTraverser;

public class RelativeDirectoryTraverserTest extends AbstractTraverserTest
{
   @Override
   protected void travers(ResourceVisitor visitor)
   {
      File testResources = new File("target/testResources");
      assertTrue(testResources.exists());
      new RelativeDirectoryTraverser(testResources).travers(visitor);
   }
}
