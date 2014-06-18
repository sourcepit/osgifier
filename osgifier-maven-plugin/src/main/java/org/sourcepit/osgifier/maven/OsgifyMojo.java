/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

/**
 * @author bernd
 */
@Mojo(name = "osgify", defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class OsgifyMojo extends AbstractOsgifyManifestMojo
{
   @Parameter(defaultValue = "${project.build.outputDirectory}/META-INF/MANIFEST.MF")
   private File manifestFile;

   @Parameter(defaultValue = "false")
   private boolean enablePDEWorkaround;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      doExecute(Goal.OSGIFY);
      if (enablePDEWorkaround)
      {
         try
         {
            FileUtils.copyFile(manifestFile, new File(project.getBasedir(), "META-INF/MANIFEST.MF"));
         }
         catch (IOException e)
         {
            throw new IllegalStateException(e);
         }
      }
   }

   @Override
   protected File getManifestFile()
   {
      return manifestFile;
   }
}
