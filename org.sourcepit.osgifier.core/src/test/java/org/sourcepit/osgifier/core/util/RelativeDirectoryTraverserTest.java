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
