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

import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.installation.InstallRequest;
import org.eclipse.aether.installation.InstallationException;


/**
 * Use this goal to install artifacts which previously was osgified via goal <i>osgify-artifacts</i> to your local
 * repository.
 * 
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Mojo(name = "install-osgified-artifacts", defaultPhase = LifecyclePhase.INSTALL)
public class InstallOsgifiedArtifacts extends AbstractOsgifierMojo
{
   private final LegacySupport buildContext;

   private final RepositorySystem repositorySystem;

   @Inject
   public InstallOsgifiedArtifacts(LegacySupport buildContext, RepositorySystem repositorySystem)
   {
      this.buildContext = buildContext;
      this.repositorySystem = repositorySystem;
   }

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final MavenProject project = buildContext.getSession().getCurrentProject();

      @SuppressWarnings("unchecked")
      final Collection<Artifact> artifacts = (Collection<Artifact>) project.getContextValue("osgified-artifacts");

      final RepositorySystemSession session = buildContext.getRepositorySession();

      final InstallRequest installRequest = new InstallRequest();
      installRequest.setArtifacts(artifacts);
      try
      {
         repositorySystem.install(session, installRequest);
      }
      catch (InstallationException e)
      {
         throw pipe(e);
      }
   }
}
