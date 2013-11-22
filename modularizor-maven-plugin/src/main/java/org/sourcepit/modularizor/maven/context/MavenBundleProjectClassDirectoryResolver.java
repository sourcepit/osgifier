/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.maven.context;

import java.io.File;

import org.apache.maven.artifact.Artifact;
import org.sourcepit.common.maven.model.MavenProject;
import org.sourcepit.common.utils.path.PathUtils;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.core.resolve.BundleContentAppender.BundleProjectClassDirectoryResolver;

public class MavenBundleProjectClassDirectoryResolver implements BundleProjectClassDirectoryResolver
{
   private String scope;

   public MavenBundleProjectClassDirectoryResolver(String scope)
   {
      this.scope = scope;
   }

   public File getProjectDirectory(BundleCandidate bundleCandidate)
   {
      final MavenProject mavenProject = bundleCandidate.getExtension(MavenProject.class);
      return mavenProject == null ? null : mavenProject.getProjectDirectory();
   }

   public String[] getClassDirectoryPaths(BundleCandidate bundleCandidate)
   {
      final org.sourcepit.common.maven.model.MavenProject mProject = bundleCandidate
         .getExtension(org.sourcepit.common.maven.model.MavenProject.class);

      return mProject == null ? null : getPathsToScan(scope, mProject);
   }

   private String[] getPathsToScan(String scope, org.sourcepit.common.maven.model.MavenProject project)
   {
      final File projectDir = project.getProjectDirectory();
      final File outputDir = project.getOutputDirectory();

      File testOutputDir = null;

      final String[] paths;

      if (Artifact.SCOPE_TEST.equals(scope))
      {
         testOutputDir = project.getTestOutputDirectory();
         paths = new String[1];
         paths[0] = PathUtils.getRelativePath(testOutputDir, projectDir, "/");
      }
      else
      {
         paths = new String[1];
         paths[0] = PathUtils.getRelativePath(outputDir, projectDir, "/");
      }

      return paths;
   }
}