/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.sourcepit.common.utils.path.PathUtils;
import org.sourcepit.osgify.context.BundleNode;
import org.sourcepit.osgify.context.BundleReference;
import org.sourcepit.osgify.context.ContextModelFactory;
import org.sourcepit.osgify.context.OsgifyContext;
import org.sourcepit.osgify.core.java.inspect.JavaPackageBundleScanner;
import org.sourcepit.osgify.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.osgify.java.JavaArchive;
import org.sourcepit.osgify.java.JavaProject;
import org.sourcepit.osgify.maven.Goal;
import org.sourcepit.osgify.maven.model.maven.MavenArtifact;
import org.sourcepit.osgify.maven.model.maven.MavenDependency;

/**
 * @author Bernd
 */
@Component(role = OsgifyContextBuilder.class, instantiationStrategy = "per-lookup")
public class OsgifyContextBuilder
{
   private final Map<String, BundleNode> mvnIdToBundleNode = new LinkedHashMap<String, BundleNode>();

   @Requirement
   private RepositorySystem repositorySystem;

   private ArtifactRepository localRepository;

   private java.util.List<ArtifactRepository> remoteRepositories;

   public OsgifyContext build(MavenProject project, Goal goal, ArtifactRepository localRepository)
   {
      this.remoteRepositories = project.getRemoteArtifactRepositories();
      this.localRepository = localRepository;

      final BundleNode bundleNode = newNode(goal, project);

      Artifact artifact = project.getArtifact();
      String id = artifact.getId();
      mvnIdToBundleNode.put(id, bundleNode);

      // we must re-resolve the artifacts... if we use the already resolved artifacts from the project, we lose version
      // ranges
      addBundleReferences(bundleNode, resolveDependencies(artifact, project.getDependencyArtifacts()));

      OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().addAll(mvnIdToBundleNode.values());

      return context;
   }

   private void addBundleReferences(final BundleNode parentNode, Set<Artifact> artifacts)
   {
      for (Artifact artifact : artifacts)
      {
         final String id = artifact.getId();
         BundleNode bundleNode = mvnIdToBundleNode.get(id);
         if (bundleNode == null)
         {
            bundleNode = newNode(artifact);
            mvnIdToBundleNode.put(id, bundleNode);
            addBundleReferences(bundleNode, resolveDependencies(artifact, null));
         }
         parentNode.getDependencies().add(newBundleReference(bundleNode, artifact));
      }
   }

   private Set<Artifact> resolveDependencies(Artifact artifact, Set<Artifact> dependencyArtifacts)
   {
      final ArtifactResolutionRequest request = new ArtifactResolutionRequest();
      request.setArtifact(artifact);
      request.setArtifactDependencies(dependencyArtifacts);
      request.setResolveRoot(false);
      request.setResolveTransitively(true);
      request.setRemoteRepositories(remoteRepositories);
      request.setLocalRepository(localRepository);

      ArtifactResolutionResult result = repositorySystem.resolve(request);
      return result.getArtifacts();
   }

   private BundleReference newBundleReference(BundleNode bundleNode, Artifact mappedArtifact)
   {
      MavenDependency dependency = MavenModelUtils.toMavenDependecy(mappedArtifact);

      final BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.addExtension(dependency);

      reference.setTarget(bundleNode);
      reference.setOptional(mappedArtifact.isOptional());
      reference.setProvided(Artifact.SCOPE_PROVIDED.equals(mappedArtifact.getScope()));

      // TODO artifact artifact.getVersionRange() to OSGi range
      reference.setVersionRange(null);

      return reference;
   }

   private BundleNode newNode(Goal goal, MavenProject project)
   {
      JavaProject jProject = scanProject(goal, project);

      BundleNode node = ContextModelFactory.eINSTANCE.createBundleNode();
      node.setContent(jProject);
      // TODO converter
      node.setSymbolicName(null);
      node.setVersion(null);

      return node;
   }

   private BundleNode newNode(Artifact artifact)
   {
      final MavenArtifact mArtifact = MavenModelUtils.toMavenArtifact(artifact);

      JavaArchive jArchive = scanArtifact(mArtifact);

      BundleNode node = ContextModelFactory.eINSTANCE.createBundleNode();
      node.addExtension(mArtifact);

      node.setContent(jArchive);
      // TODO converter
      node.setSymbolicName(null);
      node.setVersion(null);

      return node;
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

   private JavaArchive scanArtifact(MavenArtifact artifact)
   {
      final JavaPackageBundleScanner scanner = new JavaPackageBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());
      return scanner.scan(artifact.getFile());
   }
}
