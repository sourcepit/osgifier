/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.io.File;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.sourcepit.osgify.context.BundleNode;
import org.sourcepit.osgify.context.ContextModelFactory;
import org.sourcepit.osgify.context.OSGiFyContext;
import org.sourcepit.osgify.core.internal.utils.PathUtils;
import org.sourcepit.osgify.core.java.inspect.JavaPackageBundleScanner;
import org.sourcepit.osgify.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.osgify.java.JavaProject;

public abstract class AbstractOsgifyMojo extends AbstractMojo
{
   /** @component */
   private RepositorySystem repositorySystem;

   /**
    * @parameter default-value="${project}"
    * @required
    * @readonly
    */
   private MavenProject project;

   protected void doExecute(Goal goal)
   {
      OSGiFyContext context = ContextModelFactory.eINSTANCE.createOSGiFyContext();

      JavaProject jProject = scanProject(goal, project);

      BundleNode node = ContextModelFactory.eINSTANCE.createBundleNode();
      node.setContent(jProject);
      
      // TODO converter
      node.setSymbolicName(null);
      node.setVersion(null);

      Set<Artifact> artifacts = project.getDependencyArtifacts();
      for (Artifact artifact : artifacts)
      {

      }
   }

   private JavaProject scanProject(Goal goal, MavenProject project)
   {
      final JavaPackageBundleScanner scanner = new JavaPackageBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());

      final File projectDir = project.getBasedir();
      return scanner.scan(projectDir, getPathsToScan(goal, project));
   }

   private String[] getPathsToScan(Goal goal, MavenProject project)
   {
      final File projectDir = project.getBasedir();
      final File outputDir = MavenUtils.getOutputDir(project);

      File testOutputDir = null;

      final String[] paths;
      switch (goal)
      {
         case OSGIFY :
            paths = new String[1];
            paths[0] = PathUtils.getRelativePath(outputDir, projectDir, "/");
            break;
         case OSGIFY_TESTS :
            testOutputDir = MavenUtils.getTestOutputDir(project);
            paths = new String[2];
            paths[0] = PathUtils.getRelativePath(outputDir, projectDir, "/");
            paths[1] = PathUtils.getRelativePath(testOutputDir, projectDir, "/");
            break;
         default :
            throw new IllegalStateException();
      }
      return paths;
   }
}
