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

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.util.MavenModelUtils;
import org.sourcepit.common.maven.util.MavenProjectUtils;
import org.sourcepit.common.utils.path.PathUtils;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.resolve.VersionRangeResolver;
import org.sourcepit.osgify.maven.Goal;

/**
 * @author Bernd
 */
@Named
public class OsgifyContextBuilder
{
   private final Map<String, BundleCandidate> mvnIdToBundleNode = new LinkedHashMap<String, BundleCandidate>();

   @Inject
   private BundleCandidateScanner bundleCandidateScanner;

   @Inject
   private VersionRangeResolver versionRangeResolver;

   @Inject
   private RepositorySystem repositorySystem;

   private ArtifactRepository localRepository;

   private java.util.List<ArtifactRepository> remoteRepositories;

   private List<BundleScannerTask> bundleScannerTasks = new ArrayList<BundleScannerTask>();

   public OsgifyContext build(MavenProject project, Goal goal, ArtifactRepository localRepository)
   {
      if (goal == Goal.OSGIFY_TESTS)
      {
         throw new UnsupportedOperationException("Bundling test artifacts is currently not supported.");
      }

      this.remoteRepositories = project.getRemoteArtifactRepositories();
      this.localRepository = localRepository;

      final String scope = getMavenScope(goal);

      final BundleCandidate bundleNode = newProjectBundleCandidate(goal, project);
      mvnIdToBundleNode.put(getProjectId(project, goal), bundleNode);

      // we must re-resolve the artifacts... if we use the already resolved artifacts from the project, we'll lose
      // version ranges
      final Set<Artifact> dependencies = resolveDependencies(project.getArtifact(), scope,
         project.getDependencyArtifacts());
      addBundleReferences(bundleNode, scope, dependencies);

      executeBundleScannerTasks();

      OsgifyContext context = ContextModelFactory.eINSTANCE.createOsgifyContext();
      context.getBundles().addAll(mvnIdToBundleNode.values());

      postprocessContext(context);

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

   private String getProjectId(MavenProject project, Goal goal)
   {
      Artifact artifact = project.getArtifact();
      String id = artifact.getId();
      if (goal == Goal.OSGIFY_TESTS)
      {
         return id + ":tests";
      }
      return id;
   }

   private void postprocessContext(OsgifyContext context)
   {
      for (BundleCandidate bundleCandidate : context.getBundles())
      {
         JavaResourceBundle content = bundleCandidate.getContent();
         EList<JavaResourcesRoot> jRoots = content.getResourcesRoots();
         for (JavaResourcesRoot jRoot : jRoots)
         {
            org.sourcepit.osgify.core.model.java.File manifestFile = jRoot.getFile("META-INF/MANIFEST.MF");
            if (manifestFile != null)
            {
               final BundleManifest manifest = manifestFile.getExtension(BundleManifest.class);
               if (manifest != null)
               {
                  bundleCandidate.setNativeBundle(true);
                  bundleCandidate.setManifest(EcoreUtil.copy(manifest));
                  break;
               }
            }
         }
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

   private void addBundleReferences(final BundleCandidate parentNode, String scope, Set<Artifact> artifacts)
   {
      for (Artifact artifact : artifacts)
      {
         final String id = artifact.getId();
         BundleCandidate bundleNode = mvnIdToBundleNode.get(id);
         if (bundleNode == null)
         {
            bundleNode = newJarBundleCandidate(artifact);
            mvnIdToBundleNode.put(id, bundleNode);
            addBundleReferences(bundleNode, scope, resolveDependencies(artifact, scope, null));
         }
         parentNode.getDependencies().add(newBundleReference(bundleNode, artifact));
      }
   }

   private Set<Artifact> resolveDependencies(Artifact artifact, String scope, Set<Artifact> dependencyArtifacts)
   {
      final ArtifactResolutionRequest request = new ArtifactResolutionRequest();
      request.setArtifact(artifact);
      request.setArtifactDependencies(dependencyArtifacts);
      request.setResolveRoot(false);
      request.setResolveTransitively(true);
      request.setRemoteRepositories(remoteRepositories);
      request.setLocalRepository(localRepository);

      ScopeArtifactFilter scopeFilter = new ScopeArtifactFilter(scope);
      request.setCollectionFilter(scopeFilter);
      request.setResolutionFilter(scopeFilter);

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
      org.sourcepit.common.maven.model.MavenProject mProject = MavenModelUtils.toMavenProject(project);

      if (goal == Goal.OSGIFY_TESTS)
      {
         mProject.setArtifactId(mProject.getArtifactId() + ".tests");
      }

      final BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.addExtension(mProject);

      bundleScannerTasks
         .add(new BundleScannerTask(bundleCandidate, project.getBasedir(), getPathsToScan(goal, project)));

      return bundleCandidate;
   }

   private BundleCandidate newJarBundleCandidate(Artifact artifact)
   {
      // TODO lookup for related project in reactor
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
            paths = new String[1];
            // paths[0] = PathUtils.getRelativePath(outputDir, projectDir, "/");
            paths[0] = PathUtils.getRelativePath(testOutputDir, projectDir, "/");
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
