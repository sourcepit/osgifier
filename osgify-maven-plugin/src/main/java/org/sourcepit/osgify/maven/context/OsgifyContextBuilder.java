/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.path.PathUtils;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
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
   @Inject
   private BundleCandidateScanner bundleCandidateScanner;

   @Inject
   private VersionRangeResolver versionRangeResolver;

   @Inject
   private OsgifyHierarchyResolver hierarchyResolver;

   private List<BundleScannerTask> bundleScannerTasks = new ArrayList<BundleScannerTask>();

   public OsgifyContext build(MavenProject project, final Goal goal, ArtifactRepository localRepository)
   {
      if (goal == Goal.OSGIFY_TESTS)
      {
         throw new UnsupportedOperationException("Bundling test artifacts is currently not supported.");
      }

      final OsgifyContext context = hierarchyResolver.resolve(project, goal, localRepository);

      for (BundleCandidate candidate : context.getBundles())
      {
         final org.sourcepit.common.maven.model.MavenProject mProject = candidate
            .getExtension(org.sourcepit.common.maven.model.MavenProject.class);
         if (mProject == null)
         {
            final MavenArtifact mArtifact = candidate.getExtension(MavenArtifact.class);
            bundleScannerTasks.add(new BundleScannerTask(candidate, mArtifact.getFile()));
         }
         else
         {
            final String[] binDirPaths = getPathsToScan(goal, mProject);
            bundleScannerTasks.add(new BundleScannerTask(candidate, project.getBasedir(), binDirPaths));
         }
      }

      executeBundleScannerTasks();

      postprocessContext(context);

      return context;
   }

   private String[] getPathsToScan(Goal goal, org.sourcepit.common.maven.model.MavenProject project)
   {
      final File projectDir = project.getProjectDirectory();
      final File outputDir = project.getOutputDirectory();

      File testOutputDir = null;

      final String[] paths;
      switch (goal)
      {
         case OSGIFY :
            paths = new String[1];
            paths[0] = PathUtils.getRelativePath(outputDir, projectDir, "/");
            break;
         case OSGIFY_TESTS :
            testOutputDir = project.getTestOutputDirectory();
            paths = new String[1];
            paths[0] = PathUtils.getRelativePath(testOutputDir, projectDir, "/");
            break;
         default :
            throw new IllegalStateException();
      }
      return paths;
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


   class BundleScannerTask implements Runnable, Comparable<BundleScannerTask>
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
            // TODO try to re-load already build contexts from reactor projects
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
