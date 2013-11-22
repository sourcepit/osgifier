/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.p2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.sisu.equinox.launching.internal.P2ApplicationLauncher;
import org.sourcepit.common.utils.lang.Exceptions;

@Named
public class P2Publisher
{
   @Inject
   private P2ApplicationLauncherFactory launcherFactory;

   public void publishBundles(File repoDir, String repositoryName, List<File> bundles, boolean compressRepository,
      int forkedProcessTimeoutInSeconds)
   {
      final String repoDirURI = repoDir.toURI().toString();

      final StringBuilder sb = new StringBuilder();
      for (File file : bundles)
      {
         sb.append(file.getAbsolutePath());
         sb.append(',');
      }
      if (sb.length() > 0)
      {
         sb.deleteCharAt(sb.length() - 1);
      }

      final List<String> launcherArgs = new ArrayList<String>();
      launcherArgs.add("-bundles");
      launcherArgs.add(sb.toString());
      launcherArgs.add("-metadataRepository");
      launcherArgs.add(repoDirURI);
      launcherArgs.add("-metadataRepositoryName");
      launcherArgs.add(repositoryName);
      launcherArgs.add("-artifactRepository");
      launcherArgs.add(repoDirURI);
      launcherArgs.add("-artifactRepositoryName");
      launcherArgs.add(repositoryName);
      launcherArgs.add("-publishArtifacts");
      if (compressRepository)
      {
         launcherArgs.add("-compress");
      }

      final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
      try
      {
         Thread.currentThread().setContextClassLoader(P2ApplicationLauncher.class.getClassLoader());

         final P2ApplicationLauncher launcher = launcherFactory.createP2ApplicationLauncher();
         launcher.setWorkingDirectory(repoDir);
         launcher.setApplicationName("org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher");
         launcher.addArguments(launcherArgs.toArray(new String[launcherArgs.size()]));

         int result = launcher.execute(forkedProcessTimeoutInSeconds);
         if (result != 0)
         {
            throw Exceptions.pipe(new MojoFailureException("P2 publisher return code was " + result));
         }
      }
      finally
      {
         Thread.currentThread().setContextClassLoader(ccl);
      }
   }

   public void publishCategories(File repoDir, File categoryFile, boolean compressRepository,
      int forkedProcessTimeoutInSeconds)
   {
      final List<String> launcherArgs = new ArrayList<String>();
      launcherArgs.add("-metadataRepository");
      launcherArgs.add(repoDir.toURI().toString());
      launcherArgs.add("-categoryDefinition");
      launcherArgs.add(categoryFile.toURI().toString());
      launcherArgs.add("-categoryQualifier");
      if (compressRepository)
      {
         launcherArgs.add("-compress");
      }

      final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
      try
      {
         Thread.currentThread().setContextClassLoader(P2ApplicationLauncher.class.getClassLoader());

         final P2ApplicationLauncher launcher = launcherFactory.createP2ApplicationLauncher();
         launcher.setWorkingDirectory(repoDir);
         launcher.setApplicationName("org.eclipse.equinox.p2.publisher.CategoryPublisher");
         launcher.addArguments(launcherArgs.toArray(new String[launcherArgs.size()]));

         int result = launcher.execute(forkedProcessTimeoutInSeconds);
         if (result != 0)
         {
            throw Exceptions.pipe(new MojoFailureException("P2 publisher return code was " + result));
         }
      }
      finally
      {
         Thread.currentThread().setContextClassLoader(ccl);
      }
   }
}
