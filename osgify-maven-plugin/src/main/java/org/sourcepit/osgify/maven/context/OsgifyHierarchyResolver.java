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
import org.apache.maven.project.MavenProject;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.maven.Goal;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class OsgifyHierarchyResolver
{
   private final MavenDependencyWalker dependencyWalker;

   @Inject
   public OsgifyHierarchyResolver(MavenDependencyWalker dependencyWalker)
   {
      this.dependencyWalker = dependencyWalker;
   }

   public OsgifyContext resolve(MavenProject project, final Goal goal, ArtifactRepository localRepository)
   {
      final MavenDependencyWalker.Request request = new MavenDependencyWalker.Request();
      request.setProject(project);
      request.setArtifactFilter(newResolutionFilter(getMavenScope(goal)));
      request.setRemoteRepositories(project.getRemoteArtifactRepositories());
      request.setLocalRepository(localRepository);
      final BundleCandidatesCollector collector = new BundleCandidatesCollector();
      request.setHandler(collector);

      dependencyWalker.walk(request);

      final OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().addAll(collector.getBundleCandidates());
      
      return context;
   }

   private String getMavenScope(Goal goal)
   {
      final String scope;
      switch (goal)
      {
         case OSGIFY :
            scope = Artifact.SCOPE_COMPILE;
            break;
         case OSGIFY_TESTS :
            scope = Artifact.SCOPE_TEST;
            break;
         default :
            throw new IllegalStateException();
      }
      return scope;
   }

   private ArtifactFilter newResolutionFilter(String scope)
   {
      final List<ArtifactFilter> artifactFilters = new ArrayList<ArtifactFilter>(2);
      artifactFilters.add(new ScopeArtifactFilter(scope));
      artifactFilters.add(new TypeArtifactFilter("jar"));
      return new AndArtifactFilter(artifactFilters);
   }
}
