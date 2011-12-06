/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.mockito.Mockito.mock;

import org.apache.maven.project.MavenProject;
import org.junit.Test;

public class MavenUtilsTest
{
   @Test
   public void testGetOutputDir()
   {
      MavenProject mock = mock(MavenProject.class);

      // MavenUtils.getOutputDir(null);

      MavenUtils.getOutputDir(null);
   }
}
