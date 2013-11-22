/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.maven;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @requiresDependencyResolution compile
 * @goal osgify
 * @phase compile
 * @author bernd
 */
public class OsgifyMojo extends AbstractOsgifyManifestMojo
{
   /** @parameter default-value="${project.build.outputDirectory}/META-INF/MANIFEST.MF" */
   private File manifestFile;

   /** @parameter default-value="false" */
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

   /**
    * {@inheritDoc}
    */
   @Override
   protected File getManifestFile()
   {
      return manifestFile;
   }
}
