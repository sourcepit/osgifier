/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.io.File;

import javax.validation.constraints.NotNull;

import org.sourcepit.osgify.core.model.context.BundleCandidate;

class BundleScannerTask implements Runnable, Comparable<BundleScannerTask>
{
   private final BundleCandidateScanner bundleCandidateScanner;
   private final BundleCandidate bundleCandidate;
   private final File jarFileOrProjectDir;
   private final String[] binDirPaths;

   public BundleScannerTask(BundleCandidateScanner bundleCandidateScanner, @NotNull BundleCandidate bundleCandidate,
      @NotNull File projectDir, @NotNull String... binDirPaths)
   {
      this.bundleCandidateScanner = bundleCandidateScanner;
      this.bundleCandidate = bundleCandidate;
      this.jarFileOrProjectDir = projectDir;
      this.binDirPaths = binDirPaths;
   }

   public BundleScannerTask(BundleCandidateScanner bundleCandidateScanner, @NotNull BundleCandidate bundleCandidate,
      @NotNull File jarFile)
   {
      this.bundleCandidateScanner = bundleCandidateScanner;
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