/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.sourcepit.common.maven.util.MavenProjectUtils;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class MavenDependencyWalker
{
   private RepositorySystem repositorySystem;

   @Inject
   public MavenDependencyWalker(RepositorySystem repositorySystem)
   {
      this.repositorySystem = repositorySystem;
   }

   public void walk(Request request)
   {
      final MavenProject project = request.getProject();
      final Artifact artifact = project.getArtifact();
      if (request.getHandler().visitNode(artifact, project))
      {
         final Set<MavenProject> context = new LinkedHashSet<MavenProject>();
         context.add(project);

         final Set<String> resolvedIds = new HashSet<String>();
         resolveAndWalk(request, context, artifact, resolvedIds);
      }
   }

   private void resolveAndWalk(Request request, Set<MavenProject> context, final Artifact parentArtifact,
      Set<String> resolvedIds)
   {
      if (resolvedIds.add(parentArtifact.getId()))
      {
         for (Artifact dependencyArtifact : resolveDependencies(parentArtifact, request))
         {
            walk(request, context, parentArtifact, dependencyArtifact, resolvedIds);
         }
      }
   }

   private void walk(Request request, Set<MavenProject> context, Artifact parentArtifact, Artifact artifact,
      Set<String> resolvedIds)
   {
      final MavenProject project = findOriginatingProject(context, artifact);
      if (request.getHandler().visitNode(artifact, project))
      {
         resolveAndWalk(request, context, artifact, resolvedIds);
      }
      request.getHandler().handleDependency(parentArtifact, artifact);
   }

   private MavenProject findOriginatingProject(Set<MavenProject> context, Artifact artifact)
   {
      for (MavenProject ctxProject : context)
      {
         final MavenProject project = MavenProjectUtils.findReferencedProject(ctxProject, artifact);
         if (project != null)
         {
            context.add(project);
            return project;
         }
      }
      return null;
   }

   private Set<Artifact> resolveDependencies(Artifact artifact, Request walkerRequest)
   {
      final ArtifactResolutionRequest request = new ArtifactResolutionRequest();
      request.setArtifact(artifact);
      request.setResolveRoot(false);
      request.setResolveTransitively(true);
      request.setRemoteRepositories(walkerRequest.getRemoteRepositories());
      request.setLocalRepository(walkerRequest.getLocalRepository());

      final ArtifactFilter artifactFilter = walkerRequest.getArtifactFilter();

      request.setCollectionFilter(artifactFilter);
      request.setResolutionFilter(artifactFilter);

      ArtifactResolutionResult result = repositorySystem.resolve(request);

      final Exception ex;
      if (result.hasExceptions())
      {
         ex = result.getExceptions().get(0);
      }
      else if (result.hasCircularDependencyExceptions())
      {
         ex = result.getCircularDependencyException(0);
      }
      else if (result.hasErrorArtifactExceptions())
      {
         ex = result.getErrorArtifactExceptions().get(0);
      }
      else if (result.hasMetadataResolutionExceptions())
      {
         ex = result.getMetadataResolutionException(0);
      }
      else
      {
         ex = null;
      }
      if (ex != null)
      {
         throw new IllegalStateException(ex);
      }

      return result.getArtifacts();
   }

   public interface Handler
   {
      boolean visitNode(Artifact artifact, MavenProject project);

      void handleDependency(Artifact fromArtifact, Artifact toArtifact);
   }

   public static class Request
   {
      private List<ArtifactRepository> remoteRepositories;
      private ArtifactRepository localRepository;
      private ArtifactFilter artifactFilter;
      private MavenProject project;

      private Handler handler;

      public List<ArtifactRepository> getRemoteRepositories()
      {
         return remoteRepositories;
      }

      public void setRemoteRepositories(List<ArtifactRepository> remoteRepositories)
      {
         this.remoteRepositories = remoteRepositories;
      }

      public ArtifactRepository getLocalRepository()
      {
         return localRepository;
      }

      public void setLocalRepository(ArtifactRepository localRepository)
      {
         this.localRepository = localRepository;
      }

      public ArtifactFilter getArtifactFilter()
      {
         return artifactFilter;
      }

      public void setArtifactFilter(ArtifactFilter artifactFilter)
      {
         this.artifactFilter = artifactFilter;
      }

      public MavenProject getProject()
      {
         return project;
      }

      public void setProject(MavenProject project)
      {
         this.project = project;
      }

      public Handler getHandler()
      {
         return handler;
      }

      public void setHandler(Handler handler)
      {
         this.handler = handler;
      }
   }
}
