/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.maven;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @requiresDependencyResolution test
 * @goal osgify-tests
 * @phase test-compile
 * @author bernd
 */
public class OsgifyTestsMojo extends AbstractOsgifyManifestMojo
{
   /**
    * @parameter default-value="${project.build.testOutputDirectory}/META-INF/MANIFEST.MF"
    */
   private File manifestFile;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      doExecute(Goal.OSGIFY_TESTS);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   protected File getManifestFile()
   {
      return manifestFile;
   }
}
