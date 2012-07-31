/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.io.File;

import javax.inject.Inject;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.sourcepit.osgify.maven.p2.P2UpdateSiteGenerator;

/**
 * @phase package
 * @goal generate-p2-update-site
 * @requiresProject true
 * @requiresDependencyResolution compile
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class GenerateP2UpdateSiteMojo extends AbstractGuplexedMojo
{
   /** @parameter default-value="${localRepository}" */
   protected ArtifactRepository localRepository;

   /** @parameter default-value="${project.build.directory}" */
   private File targetDir;

   /** @parameter default-value="${project}" */
   protected MavenProject project;

   /**
    * Metadata repository name
    * 
    * @parameter default-value="${project.name}"
    * @required
    */
   protected String repositoryName;

   /**
    * Kill the forked test process after a certain number of seconds. If set to 0, wait forever for
    * the process, never timing out.
    * 
    * @parameter expression="${p2.timeout}"
    */
   private int forkedProcessTimeoutInSeconds;

   /** @parameter default-value="true" */
   private boolean compressRepository;

   @Inject
   private P2UpdateSiteGenerator siteGenerator;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      siteGenerator.generateUpdateSite(targetDir, project.getArtifact(), project.getRemoteArtifactRepositories(),
         localRepository, repositoryName, compressRepository, forkedProcessTimeoutInSeconds);
   }
}
