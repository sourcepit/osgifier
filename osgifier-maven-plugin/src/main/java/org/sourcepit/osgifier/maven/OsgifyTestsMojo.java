/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * @author bernd
 */
@Mojo(name = "osgify-tests", defaultPhase = LifecyclePhase.TEST_COMPILE, requiresDependencyResolution = ResolutionScope.TEST)
public class OsgifyTestsMojo extends AbstractOsgifyManifestMojo
{
   @Parameter(defaultValue = "${project.build.testOutputDirectory}/META-INF/MANIFEST.MF")
   private File manifestFile;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      doExecute(Goal.OSGIFY_TESTS);
   }

   @Override
   protected File getManifestFile()
   {
      return manifestFile;
   }
}
