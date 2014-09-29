/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.resolve;

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

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

@Named
public class JavaContentAppender
{
   @Inject
   private BundleCandidateScanner bundleCandidateScanner;

   public OsgifierContext appendContents(OsgifierContext context, JavaContentAppenderFilter filter,
      PropertiesSource options)
   {
      final List<BundleScannerTask> bundleScannerTasks = new ArrayList<BundleScannerTask>();

      for (BundleCandidate candidate : context.getBundles())
      {
         if (candidate.getLocation() != null && filter.isAppendContent(candidate, options))
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
