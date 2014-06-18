/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven;

import java.io.File;

import javax.inject.Inject;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.maven.p2.P2UpdateSiteGenerator;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Mojo(name = "generate-p2-update-site", requiresProject = true, defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class GenerateP2UpdateSiteMojo extends AbstractOsgifierMojo
{
   @Parameter(defaultValue = "${localRepository}")
   protected ArtifactRepository localRepository;

   @Parameter(defaultValue = "${project.build.directory}/p2-update-site")
   private File siteDir;

   @Parameter(defaultValue = "${project}")
   protected MavenProject project;

   /**
    * Metadata repository name
    */
   @Parameter(defaultValue = "${project.name}", required = true)
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
      final PropertiesMap options = new LinkedPropertiesMap();
      options.setBoolean(P2UpdateSiteGenerator.OPTION_COMPRESS_REPOSITORY, compressRepository);
      options.setInt(P2UpdateSiteGenerator.OPTION_FORKED_PROCESS_TIMEOUT_IN_SECONDS, forkedProcessTimeoutInSeconds);
      siteGenerator.generateUpdateSite(siteDir, project, project.getRemoteArtifactRepositories(), localRepository,
         repositoryName, options);
   }
}
