/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.sourcepit.common.maven.util.MavenProjectUtils;
import org.sourcepit.common.utils.lang.Exceptions;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class MavenDependencyWalker
{
   private RepositorySystem repositorySystem;

   @Inject
   public MavenDependencyWalker(RepositorySystem repositorySystem, LegacySupport legacySupport)
   {
      this.repositorySystem = repositorySystem;
   }

   public void walk(Request request)
   {
      final Map<String, MavenProject> reactorProjects = new LinkedHashMap<String, MavenProject>();
      if (request.getReactorProjects() != null)
      {
         for (MavenProject project : request.getReactorProjects())
         {
            final String projectId = MavenProjectUtils.getProjectReferenceId(project);
            reactorProjects.put(projectId, project);
         }
      }
      final Stack<MavenProject> currentResolutionContext = new Stack<MavenProject>();

      final Artifact artifact = request.getArtifact();
      final MavenProject project = findOriginatingProject(reactorProjects, artifact);

      final boolean resolveRoot = !artifact.isResolved() && request.isResolveRoot();
      if (resolveRoot)
      {
         resolve(request, artifact, project, resolveRoot, null);
      }

      if (request.getHandler().visitNode(artifact, project))
      {
         final Set<String> resolvedIds = new HashSet<String>();
         if (!request.isResolveRoot())
         {
            if (project != null)
            {
               currentResolutionContext.push(project);
            }
            
            for (Artifact dependencyArtifact : resolve(request, artifact, project, false, request.getDependencies()))
            {
               walk(request, reactorProjects, currentResolutionContext, artifact, dependencyArtifact, resolvedIds);
            }
            
            if (project != null)
            {
               currentResolutionContext.pop();
            }
         }
         else
         {
            resolveAndWalk(request, reactorProjects, currentResolutionContext, artifact, project, resolvedIds);
         }
      }
   }

   private void resolveAndWalk(Request request, Map<String, MavenProject> reactorProjects,
      Stack<MavenProject> resolutionContext, final Artifact parentArtifact, MavenProject project,
      Set<String> resolvedIds)
   {
      if (resolvedIds.add(parentArtifact.getId()))
      {
         if (project != null)
         {
            resolutionContext.push(project);
         }

         final MavenProject currentProject = resolutionContext.isEmpty() ? null : resolutionContext.peek();

         for (Artifact dependencyArtifact : resolveDependencies(parentArtifact, request, currentProject))
         {
            walk(request, reactorProjects, resolutionContext, parentArtifact, dependencyArtifact, resolvedIds);
         }

         if (project != null)
         {
            resolutionContext.pop();
         }
      }
   }

   private void walk(Request request, Map<String, MavenProject> reactorProjects, Stack<MavenProject> resolutionContext,
      Artifact parentArtifact, Artifact artifact, Set<String> resolvedIds)
   {
      final MavenProject project = findOriginatingProject(reactorProjects, artifact);
      if (request.getHandler().visitNode(artifact, project))
      {
         resolveAndWalk(request, reactorProjects, resolutionContext, artifact, project, resolvedIds);
      }
      request.getHandler().handleDependency(parentArtifact, artifact);
   }

   private MavenProject findOriginatingProject(Map<String, MavenProject> context, Artifact artifact)
   {
      final String projectId = MavenProjectUtils.getProjectReferenceId(artifact);
      return context.get(projectId);
   }

   private Set<Artifact> resolveDependencies(Artifact artifact, Request walkerRequest, MavenProject resolutionContext)
   {
      return resolve(walkerRequest, artifact, resolutionContext, false, null);
   }

   private Set<Artifact> resolve(Request walkerRequest, Artifact artifact, MavenProject resolutionContext,
      boolean resolveRoot, List<Dependency> dependencies)
   {
      final ArtifactResolutionRequest request = new ArtifactResolutionRequest();
      request.setArtifact(artifact);
      request.setResolveRoot(resolveRoot);
      request.setResolveTransitively(!resolveRoot);

      if (dependencies != null)
      {
         final Set<Artifact> artifactDependencies = new LinkedHashSet<Artifact>();
         for (Dependency dependency : dependencies)
         {
            artifactDependencies.add(repositorySystem.createArtifactWithClassifier(dependency.getGroupId(),
               dependency.getArtifactId(), dependency.getVersion(), dependency.getType(), dependency.getClassifier()));
         }
         request.setArtifactDependencies(artifactDependencies);
      }

      request.setLocalRepository(walkerRequest.getLocalRepository());

      final List<ArtifactRepository> remoteRepositories = new ArrayList<ArtifactRepository>();
      final Set<String> repoIds = new HashSet<String>();
      final List<ArtifactRepository> requestRepos = walkerRequest.getRemoteRepositories();
      if (requestRepos != null)
      {
         for (ArtifactRepository artifactRepository : requestRepos)
         {
            repoIds.add(artifactRepository.getId());
            remoteRepositories.add(artifactRepository);
         }
      }

      if (resolutionContext != null)
      {
         for (ArtifactRepository artifactRepository : resolutionContext.getRemoteArtifactRepositories())
         {
            if (repoIds.add(artifactRepository.getId()))
            {
               remoteRepositories.add(artifactRepository);
            }
         }
         request.setManagedVersionMap(resolutionContext.getManagedVersionMap());
      }

      request.setRemoteRepositories(remoteRepositories);

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

      for (Artifact missingArtifact : result.getMissingArtifacts())
      {
         throw Exceptions.pipe(new ArtifactNotFoundException("Unable to resolve artifact", missingArtifact));
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
      private Artifact artifact;
      private boolean resolveRoot = true;
      private List<Dependency> dependencies;
      private List<MavenProject> reactorProjects;

      public void setArtifact(Artifact artifact)
      {
         this.artifact = artifact;
      }

      public Artifact getArtifact()
      {
         return artifact;
      }

      public List<MavenProject> getReactorProjects()
      {
         return reactorProjects;
      }

      public void setReactorProjects(List<MavenProject> reactorProjects)
      {
         this.reactorProjects = reactorProjects;
      }

      public boolean isResolveRoot()
      {
         return resolveRoot;
      }

      public void setResolveRoot(boolean resolveRoot)
      {
         this.resolveRoot = resolveRoot;
      }

      public List<Dependency> getDependencies()
      {
         return dependencies;
      }

      public void setDependencies(List<Dependency> dependencies)
      {
         this.dependencies = dependencies;
      }

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
