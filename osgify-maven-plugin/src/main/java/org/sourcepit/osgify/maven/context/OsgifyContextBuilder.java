/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.validation.constraints.NotNull;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.util.MavenModelUtils;
import org.sourcepit.common.maven.util.MavenProjectUtils;
import org.sourcepit.common.utils.path.PathUtils;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.VersionRangeResolver;
import org.sourcepit.osgify.maven.Goal;

/**
 * @author Bernd
 */
@Component(role = OsgifyContextBuilder.class, instantiationStrategy = "per-lookup")
public class OsgifyContextBuilder
{
   private final Map<String, BundleCandidate> mvnIdToBundleNode = new LinkedHashMap<String, BundleCandidate>();

   @Requirement
   private BundleCandidateScanner bundleCandidateScanner;

   @Requirement
   private VersionRangeResolver versionRangeResolver;

   @Requirement
   private RepositorySystem repositorySystem;

   private ArtifactRepository localRepository;

   private java.util.List<ArtifactRepository> remoteRepositories;

   private List<BundleScannerTask> bundleScannerTasks = new ArrayList<BundleScannerTask>();

   public OsgifyContext build(MavenProject project, Goal goal, ArtifactRepository localRepository)
   {
      this.remoteRepositories = project.getRemoteArtifactRepositories();
      this.localRepository = localRepository;

      final BundleCandidate bundleNode = newProjectBundleCandidate(goal, project);

      Artifact artifact = project.getArtifact();
      String id = artifact.getId();
      mvnIdToBundleNode.put(id, bundleNode);

      // we must re-resolve the artifacts... if we use the already resolved artifacts from the project, we'll lose
      // version ranges
      addBundleReferences(bundleNode, resolveDependencies(artifact, project.getDependencyArtifacts()));

      executeBundleScannerTasks();

      OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().addAll(mvnIdToBundleNode.values());

      alignBundlereferencesVersionRanges(context);

      return context;
   }

   private void alignBundlereferencesVersionRanges(OsgifyContext context)
   {
      for (BundleCandidate bundleCandidate : context.getBundles())
      {
         for (BundleReference bundleReference : bundleCandidate.getDependencies())
         {
            bundleReference.setVersionRange(versionRangeResolver.resolveVersionRange(bundleReference));
         }
      }
   }

   private void executeBundleScannerTasks()
   {
      Collections.sort(bundleScannerTasks);

      ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
      for (BundleScannerTask scan : bundleScannerTasks)
      {
         executor.execute(scan);
      }

      executor.shutdown();

      BlockingQueue<Runnable> queue = executor.getQueue();

      Runnable poll = queue.poll();
      while (poll != null)
      {
         poll.run();
         poll = queue.poll();
      }

      try
      {
         executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
      }
      catch (InterruptedException e)
      {
      }
   }

   private void addBundleReferences(final BundleCandidate parentNode, Set<Artifact> artifacts)
   {
      for (Artifact artifact : artifacts)
      {
         final String id = artifact.getId();
         BundleCandidate bundleNode = mvnIdToBundleNode.get(id);
         if (bundleNode == null)
         {
            bundleNode = newJarBundleCandidate(artifact);
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

   private BundleReference newBundleReference(BundleCandidate bundleNode, Artifact mappedArtifact)
   {
      final BundleReference bundleReference = ContextModelFactory.eINSTANCE.createBundleReference();
      bundleReference.addExtension(MavenModelUtils.toMavenDependecy(mappedArtifact));

      bundleReference.setTarget(bundleNode);
      bundleReference.setOptional(mappedArtifact.isOptional());
      bundleReference.setProvided(Artifact.SCOPE_PROVIDED.equals(mappedArtifact.getScope()));

      return bundleReference;
   }

   private BundleCandidate newProjectBundleCandidate(Goal goal, MavenProject project)
   {
      final BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.addExtension(MavenModelUtils.toMavenProject(project));

      bundleScannerTasks
         .add(new BundleScannerTask(bundleCandidate, project.getBasedir(), getPathsToScan(goal, project)));

      return bundleCandidate;
   }

   private BundleCandidate newJarBundleCandidate(Artifact artifact)
   {
      final MavenArtifact mArtifact = MavenModelUtils.toMavenArtifact(artifact);

      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.addExtension(mArtifact);

      bundleScannerTasks.add(new BundleScannerTask(bundleCandidate, mArtifact.getFile()));

      return bundleCandidate;
   }

   private String[] getPathsToScan(Goal goal, MavenProject project)
   {
      final File projectDir = project.getBasedir();
      final File outputDir = MavenProjectUtils.getOutputDir(project);

      File testOutputDir = null;

      final String[] paths;
      switch (goal)
      {
         case OSGIFY :
            paths = new String[1];
            paths[0] = PathUtils.getRelativePath(outputDir, projectDir, "/");
            break;
         case OSGIFY_TESTS :
            testOutputDir = MavenProjectUtils.getTestOutputDir(project);
            paths = new String[2];
            paths[0] = PathUtils.getRelativePath(outputDir, projectDir, "/");
            paths[1] = PathUtils.getRelativePath(testOutputDir, projectDir, "/");
            break;
         default :
            throw new IllegalStateException();
      }
      return paths;
   }

   private class BundleScannerTask implements Runnable, Comparable<BundleScannerTask>
   {
      private final BundleCandidate bundleCandidate;
      private final File jarFileOrProjectDir;
      private final String[] binDirPaths;

      public BundleScannerTask(@NotNull BundleCandidate bundleCandidate, @NotNull File projectDir,
         @NotNull String... binDirPaths)
      {
         this.bundleCandidate = bundleCandidate;
         this.jarFileOrProjectDir = projectDir;
         this.binDirPaths = binDirPaths;
      }

      public BundleScannerTask(@NotNull BundleCandidate bundleCandidate, @NotNull File jarFile)
      {
         this.bundleCandidate = bundleCandidate;
         this.jarFileOrProjectDir = jarFile;
         this.binDirPaths = null;
      }

      public void run()
      {
         if (jarFileOrProjectDir.isDirectory())
         {
            bundleCandidateScanner.scanProject(bundleCandidate, jarFileOrProjectDir, binDirPaths);
         }
         else
         {
            bundleCandidateScanner.scanJar(bundleCandidate, jarFileOrProjectDir);
         }
      }

      public int compareTo(BundleScannerTask other)
      {
         final File f1 = jarFileOrProjectDir;
         final File f2 = other.jarFileOrProjectDir;
         if (f1.isDirectory())
         {
            return f2.isDirectory() ? 0 : -1;
         }
         return (int) -(f1.length() - f2.length());
      }
   }
}
