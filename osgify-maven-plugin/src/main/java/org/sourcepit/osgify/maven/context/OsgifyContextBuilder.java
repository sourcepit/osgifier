/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProject;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.BundleContentAppender;

/*
 * - Artifact as root
 * - Project as root
 * - Resolve against "workspace" (know projects)
 * - resolve against remote repositories
 * - resolve against local repository
 * - support fat bundle and single bundles
 */

/**
 * @author Bernd
 */
@Named
public class OsgifyContextBuilder
{
   @Inject
   private ContextHierarchyBuilder hierarchyBuilder;

   @Inject
   private BundleContentAppender bundleContentAppender;

   public OsgifyContext build(MavenProject project, ArtifactRepository localRepository)
   {
      final ContextHierarchyBuilder.Request request = hierarchyBuilder.createRequest(project.getArtifact());
      request.setVirtualArtifact(false);
      request.setFatBundle(false);
      request.setLocalRepository(localRepository);
      request.setScope(Artifact.SCOPE_COMPILE);
      request.getRemoteRepositories().addAll(project.getRemoteArtifactRepositories());

      final OsgifyContext context = hierarchyBuilder.build(request);

      bundleContentAppender.appendContents(context,
         new MavenBundleProjectClassDirectoryResolver(Artifact.SCOPE_COMPILE));

      return context;
   }
}
