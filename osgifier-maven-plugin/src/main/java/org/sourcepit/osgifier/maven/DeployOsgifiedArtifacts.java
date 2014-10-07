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

/**
 * Use this goal to deploy artifacts which previously was osgified via goal <i>osgify-artifacts</i>.
 * 
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Mojo(name = "deploy-osgified-artifacts", defaultPhase = LifecyclePhase.DEPLOY)
public class DeployOsgifiedArtifacts extends AbstractOsgifierMojo
{
   private final LegacySupport buildContext;

   private final RepositorySystem repositorySystem;

   /**
    * Target repository.
    * 
    * <pre>
    * &lt;repository&gt;
    *   &lt;id&gt;repo-id&lt;/id&gt;
    *   &lt;url&gt;http://foo.bar/repo&lt;/url&gt;
    * &lt;/repository&gt;
    * </pre>
    */
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
