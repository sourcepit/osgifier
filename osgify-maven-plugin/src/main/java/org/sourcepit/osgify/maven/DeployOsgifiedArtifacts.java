/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.maven.model.Repository;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.deployment.DeployRequest;
import org.eclipse.aether.deployment.DeploymentException;
import org.eclipse.aether.repository.RemoteRepository;

@Mojo(name = "deploy-osgified-artifacts", defaultPhase = LifecyclePhase.DEPLOY)
public class DeployOsgifiedArtifacts extends AbstractOsgifyMojo
{
   private final LegacySupport buildContext;

   private final RepositorySystem repositorySystem;

   @Parameter(required = true)
   private Repository repository;

   @Inject
   public DeployOsgifiedArtifacts(LegacySupport buildContext, RepositorySystem repositorySystem)
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

      final String id = repository.getId();
      final String url = repository.getUrl();
      final String layout = repository.getLayout();

      final RemoteRepository repo = newRemoteRepository(session, id, url, layout);

      deploy(session, repo, artifacts);
   }

   private static RemoteRepository newRemoteRepository(final RepositorySystemSession session, String id, String url,
      String layout)
   {
      final RemoteRepository.Builder repoBuilder = new RemoteRepository.Builder(id, layout, url);
      RemoteRepository repo = repoBuilder.build();
      repoBuilder.setAuthentication(session.getAuthenticationSelector().getAuthentication(repo));
      repoBuilder.setProxy(session.getProxySelector().getProxy(repo));
      return repoBuilder.build();
   }

   private void deploy(RepositorySystemSession session, RemoteRepository repository, Collection<Artifact> artifacts)
   {
      final DeployRequest request = new DeployRequest();
      request.setArtifacts(artifacts);
      request.setRepository(repository);
      try
      {
         repositorySystem.deploy(session, request);
      }
      catch (DeploymentException e)
      {
         throw pipe(e);
      }
   }
}
