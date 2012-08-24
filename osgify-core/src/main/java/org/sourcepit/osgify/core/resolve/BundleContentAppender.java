/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

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

import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;

@Named
public class BundleContentAppender
{
   public static interface BundleProjectClassDirectoryResolver
   {
      File getProjectDirectory(BundleCandidate bundleCandidate);

      String[] getClassDirectoryPaths(BundleCandidate bundleCandidate);
   }

   @Inject
   private BundleCandidateScanner bundleCandidateScanner;

   public OsgifyContext appendContents(OsgifyContext context, BundleProjectClassDirectoryResolver classDirectoryResolver)
   {
      List<BundleScannerTask> bundleScannerTasks = new ArrayList<BundleScannerTask>();

      for (BundleCandidate candidate : context.getBundles())
      {
         final File projectDir = classDirectoryResolver == null ? null : classDirectoryResolver
            .getProjectDirectory(candidate);
         if (projectDir != null)
         {
            final String[] binDirPaths = classDirectoryResolver == null ? null : classDirectoryResolver
               .getClassDirectoryPaths(candidate);
            bundleScannerTasks.add(new BundleScannerTask(candidate, projectDir, binDirPaths));
         }
         else if (candidate.getLocation() != null)
         {
            bundleScannerTasks.add(new BundleScannerTask(candidate, candidate.getLocation()));
         }
      }

      executeBundleScannerTasks(bundleScannerTasks);

      return context;
   }

   private void executeBundleScannerTasks(List<BundleScannerTask> bundleScannerTasks)
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
         String... binDirPaths)
      {
         this.bundleCandidate = bundleCandidate;
         this.jarFileOrProjectDir = projectDir;
         this.binDirPaths = binDirPaths == null ? new String[0] : binDirPaths;
      }

      public BundleScannerTask(@NotNull BundleCandidate bundleCandidate, @NotNull File jarFile)
      {
         this.bundleCandidate = bundleCandidate;
         this.jarFileOrProjectDir = jarFile;
         this.binDirPaths = new String[0];
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
         final File f1 = bundleCandidate.getLocation();
         final File f2 = other.bundleCandidate.getLocation();
         if (f1.isDirectory())
         {
            return f2.isDirectory() ? 0 : -1;
         }
         return (int) -(f1.length() - f2.length());
      }
   }
}
