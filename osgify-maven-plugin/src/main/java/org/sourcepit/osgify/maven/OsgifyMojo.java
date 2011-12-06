/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.io.File;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.sourcepit.osgify.context.BundleNode;
import org.sourcepit.osgify.context.ContextModelFactory;
import org.sourcepit.osgify.context.OSGiFyContext;
import org.sourcepit.osgify.core.internal.utils.PathUtils;
import org.sourcepit.osgify.core.java.inspect.JavaPackageBundleScanner;
import org.sourcepit.osgify.core.java.inspect.JavaTypeReferencesAnalyzer;

/**
 * @requiresDependencyResolution compile
 * @goal osgify
 * @phase compile
 * @author bernd
 */
public class OsgifyMojo extends AbstractMojo
{
   /** @component */
   private RepositorySystem repositorySystem;

   /**
    * @parameter default-value="${project}"
    * @required
    * @readonly
    */
   private MavenProject project;

   public void execute() throws MojoExecutionException, MojoFailureException
   {
      OSGiFyContext context = ContextModelFactory.eINSTANCE.createOSGiFyContext();
      
      BundleNode node = ContextModelFactory.eINSTANCE.createBundleNode();
      
      JavaPackageBundleScanner scanner = new JavaPackageBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());
      
      Build build = project.getBuild();
      
      String outDir = build.getOutputDirectory();
      String testOutDir = build.getTestOutputDirectory();
      
      String relativePath = PathUtils.getRelativePath(new File(outDir), project.getFile(), "/");
      String relativePath2 = PathUtils.getRelativePath(new File(testOutDir), project.getFile(), "/");
      
      
      Set<Artifact> artifacts = project.getDependencyArtifacts();
      for (Artifact artifact : artifacts)
      {
         
      }
   }
   
   
}

