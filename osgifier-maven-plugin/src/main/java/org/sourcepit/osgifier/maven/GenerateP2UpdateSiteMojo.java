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
public class GenerateP2UpdateSiteMojo extends AbstractOsgifierMojo {
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
   protected void doExecute() throws MojoExecutionException, MojoFailureException {
      final PropertiesMap options = new LinkedPropertiesMap();
      options.setBoolean(P2UpdateSiteGenerator.OPTION_COMPRESS_REPOSITORY, compressRepository);
      options.setInt(P2UpdateSiteGenerator.OPTION_FORKED_PROCESS_TIMEOUT_IN_SECONDS, forkedProcessTimeoutInSeconds);
      siteGenerator.generateUpdateSite(siteDir, project, project.getRemoteArtifactRepositories(), localRepository,
         repositoryName, options);
   }
}
