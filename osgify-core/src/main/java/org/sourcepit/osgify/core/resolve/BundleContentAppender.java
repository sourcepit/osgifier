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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;

@Named
public class BundleContentAppender
{
   public static interface BundleProjectClassDirectoryResolver
   {
      String[] getClassDirectoryPaths(BundleCandidate bundleCandidate);
   }

   @Inject
   private BundleCandidateScanner bundleCandidateScanner;

   @Inject
   private VersionRangeResolver versionRangeResolver;

   public OsgifyContext appendContents(OsgifyContext context, BundleProjectClassDirectoryResolver classDirectoryResolver)
   {
      List<BundleScannerTask> bundleScannerTasks = new ArrayList<BundleScannerTask>();

      for (BundleCandidate candidate : context.getBundles())
      {
         final String[] binDirPaths = classDirectoryResolver.getClassDirectoryPaths(candidate);
         bundleScannerTasks.add(new BundleScannerTask(candidate, binDirPaths));
      }

      executeBundleScannerTasks(bundleScannerTasks);

      postprocessContext(context);

      return context;
   }

   private void postprocessContext(OsgifyContext context)
   {
      for (BundleCandidate bundleCandidate : context.getBundles())
      {
         if (!bundleCandidate.isNativeBundle())
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
         }
         for (BundleReference bundleReference : bundleCandidate.getDependencies())
         {
            bundleReference.setVersionRange(versionRangeResolver.resolveVersionRange(bundleReference));
         }
      }
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
      private final String[] binDirPaths;

      public BundleScannerTask(@NotNull BundleCandidate bundleCandidate, @NotNull String... binDirPaths)
      {
         this.bundleCandidate = bundleCandidate;
         this.binDirPaths = binDirPaths;
      }

      public BundleScannerTask(@NotNull BundleCandidate bundleCandidate)
      {
         this.bundleCandidate = bundleCandidate;
         this.binDirPaths = null;
      }

      public void run()
      {
         File jarFileOrProjectDir = bundleCandidate.getLocation();
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
