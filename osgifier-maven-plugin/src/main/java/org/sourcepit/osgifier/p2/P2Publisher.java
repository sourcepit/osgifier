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

package org.sourcepit.osgifier.p2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.plugin.MojoFailureException;
import org.eclipse.sisu.equinox.launching.internal.P2ApplicationLauncher;
import org.sourcepit.common.utils.lang.Exceptions;

@Named
public class P2Publisher {
   @Inject
   private P2ApplicationLauncherFactory launcherFactory;

   public void publishBundles(File repoDir, String repositoryName, List<File> bundles, boolean compressRepository,
      int forkedProcessTimeoutInSeconds) {
      final String repoDirURI = repoDir.toURI().toString();

      final StringBuilder sb = new StringBuilder();
      for (File file : bundles) {
         sb.append(file.getAbsolutePath());
         sb.append(',');
      }
      if (sb.length() > 0) {
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
      if (compressRepository) {
         launcherArgs.add("-compress");
      }

      final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
      try {
         Thread.currentThread().setContextClassLoader(P2ApplicationLauncher.class.getClassLoader());

         final P2ApplicationLauncher launcher = launcherFactory.createP2ApplicationLauncher();
         launcher.setWorkingDirectory(repoDir);
         launcher.setApplicationName("org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher");
         launcher.addArguments(launcherArgs.toArray(new String[launcherArgs.size()]));

         int result = launcher.execute(forkedProcessTimeoutInSeconds);
         if (result != 0) {
            throw Exceptions.pipe(new MojoFailureException("P2 publisher return code was " + result));
         }
      }
      finally {
         Thread.currentThread().setContextClassLoader(ccl);
      }
   }

   public void publishCategories(File repoDir, File categoryFile, boolean compressRepository,
      int forkedProcessTimeoutInSeconds) {
      final List<String> launcherArgs = new ArrayList<String>();
      launcherArgs.add("-metadataRepository");
      launcherArgs.add(repoDir.toURI().toString());
      launcherArgs.add("-categoryDefinition");
      launcherArgs.add(categoryFile.toURI().toString());
      launcherArgs.add("-categoryQualifier");
      if (compressRepository) {
         launcherArgs.add("-compress");
      }

      final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
      try {
         Thread.currentThread().setContextClassLoader(P2ApplicationLauncher.class.getClassLoader());

         final P2ApplicationLauncher launcher = launcherFactory.createP2ApplicationLauncher();
         launcher.setWorkingDirectory(repoDir);
         launcher.setApplicationName("org.eclipse.equinox.p2.publisher.CategoryPublisher");
         launcher.addArguments(launcherArgs.toArray(new String[launcherArgs.size()]));

         int result = launcher.execute(forkedProcessTimeoutInSeconds);
         if (result != 0) {
            throw Exceptions.pipe(new MojoFailureException("P2 publisher return code was " + result));
         }
      }
      finally {
         Thread.currentThread().setContextClassLoader(ccl);
      }
   }
}
