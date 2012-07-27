/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.filter.AndArtifactFilter;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.artifact.resolver.filter.TypeArtifactFilter;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.BundleContentAppender;
import org.sourcepit.osgify.core.resolve.BundleContentAppender.BundleProjectClassDirectoryResolver;

/*
 * - Artifact as root
 * - Project as root
 * - Resolve against "workspace" (know projects)
 * - resolve against remote repositories
 * - resolve against local repository
 * - support fat bundle and single bundles
 */

@Named
public class OsgifyModelBuilder
{
   public static class Request
   {
      private Artifact artifact;

      private boolean fatBundle = false;

      private boolean resolveDependenciesOfNativeBundles = false;

      private boolean virtualArtifact = false;

      private final List<Dependency> virtualDependencies = new ArrayList<Dependency>();

      private String scope;

      private final List<MavenProject> reactorProjects = new ArrayList<MavenProject>();

      private ArtifactRepository localRepository;

      private final List<ArtifactRepository> remoteRepositories = new ArrayList<ArtifactRepository>();

      private boolean appendBundleContents = false;

      private BundleProjectClassDirectoryResolver bundleProjectClassDirectoryResolver;

      public Artifact getArtifact()
      {
         return artifact;
      }

      public void setArtifact(Artifact artifact)
      {
         this.artifact = artifact;
      }

      public boolean isFatBundle()
      {
         return fatBundle;
      }

      public void setFatBundle(boolean fatBundle)
      {
         this.fatBundle = fatBundle;
      }

      public boolean isVirtualArtifact()
      {
         return virtualArtifact;
      }

      public void setVirtualArtifact(boolean virtualArtifact)
      {
         this.virtualArtifact = virtualArtifact;
      }

      public List<Dependency> getVirtualDependencies()
      {
         return virtualDependencies;
      }

      public String getScope()
      {
         if (scope == null)
         {
            return Artifact.SCOPE_COMPILE;
         }
         return scope;
      }

      public void setScope(String scope)
      {
         this.scope = scope;
      }

      public List<MavenProject> getReactorProjects()
      {
         return reactorProjects;
      }

      public ArtifactRepository getLocalRepository()
      {
         return localRepository;
      }

      public void setLocalRepository(ArtifactRepository localRepository)
      {
         this.localRepository = localRepository;
      }

      public List<ArtifactRepository> getRemoteRepositories()
      {
         return remoteRepositories;
      }

      public void setResolveDependenciesOfNativeBundles(boolean resolveDependenciesOfNativeBundles)
      {
         this.resolveDependenciesOfNativeBundles = resolveDependenciesOfNativeBundles;
      }

      public boolean isResolveDependenciesOfNativeBundles()
      {
         return resolveDependenciesOfNativeBundles;
      }

      public boolean isAppendBundleContents()
      {
         return appendBundleContents;
      }

      public void setAppendBundleContents(boolean appendBundleContents)
      {
         this.appendBundleContents = appendBundleContents;
      }

      public void setBundleProjectClassDirectoryResolver(
         BundleProjectClassDirectoryResolver bundleProjectClassDirectoryResolver)
      {
         this.bundleProjectClassDirectoryResolver = bundleProjectClassDirectoryResolver;
      }

      public BundleProjectClassDirectoryResolver getBundleProjectClassDirectoryResolver()
      {
         return bundleProjectClassDirectoryResolver;
      }
   }

   @Inject
   private LegacySupport legacySupport;

   @Inject
   private RepositorySystem repositorySystem;

   @Inject
   private MavenDependencyWalker dependencyWalker;

   @Inject
   private BundleContentAppender bundleContentAppender;

   public Request createRequest(String groupId, String artifactId, String version, String classifier)
   {
      final Artifact artifact = repositorySystem.createArtifactWithClassifier(groupId, artifactId, version, "jar",
         classifier);
      return createRequest(artifact);
   }

   public Request createRequest(final Artifact artifact)
   {
      final Request request = new Request();
      request.setArtifact(artifact);
      return request;
   }

   public OsgifyContext build(final Request request)
   {
      final MavenDependencyWalker.Request walkerRequest = new MavenDependencyWalker.Request();
      final MavenSession currentSession = legacySupport.getSession();
      if (currentSession != null)
      {
         walkerRequest.setReactorProjects(currentSession.getProjects());
      }
      walkerRequest.setArtifactFilter(newResolutionFilter(request.getScope()));

      walkerRequest.setArtifact(request.getArtifact());
      walkerRequest.setResolveRoot(!request.isVirtualArtifact());
      walkerRequest.setDependencies(request.getVirtualDependencies());
      walkerRequest.setRemoteRepositories(request.getRemoteRepositories());
      walkerRequest.setLocalRepository(request.getLocalRepository());

      final BundleCandidatesCollector bundleCollector = new BundleCandidatesCollector(request.isFatBundle()
         || request.isResolveDependenciesOfNativeBundles())
      {
         private int calls = 0;

         public boolean visitNode(Artifact artifact, MavenProject project)
         {
            calls++;
            boolean visit = super.visitNode(artifact, project);
            if (visit)
            {
               visit = !request.isFatBundle() || calls < 2;
            }
            return visit;
         };
      };
      walkerRequest.setHandler(bundleCollector);

      dependencyWalker.walk(walkerRequest);

      final OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().addAll(bundleCollector.getBundleCandidates());

      if (request.isAppendBundleContents())
      {
         bundleContentAppender.appendContents(context, request.getBundleProjectClassDirectoryResolver());
      }

      return context;
   }

   private ArtifactFilter newResolutionFilter(String scope)
   {
      final List<ArtifactFilter> artifactFilters = new ArrayList<ArtifactFilter>(2);
      artifactFilters.add(new ScopeArtifactFilter(scope));
      artifactFilters.add(new TypeArtifactFilter("jar"));
      return new AndArtifactFilter(artifactFilters);
   }
}
