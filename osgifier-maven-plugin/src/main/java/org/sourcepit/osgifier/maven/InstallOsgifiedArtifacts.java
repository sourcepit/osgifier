/*
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
