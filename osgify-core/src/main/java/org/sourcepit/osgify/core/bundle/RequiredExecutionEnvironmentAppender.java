/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleHeaderName;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.JavaClass;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.Resource;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class RequiredExecutionEnvironmentAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(RequiredExecutionEnvironmentAppender.class);

   private ExecutionEnvironmentService execEnvService;

   private ReferencedPackagesService packagesService;

   @Inject
   public RequiredExecutionEnvironmentAppender(ExecutionEnvironmentService execEnvService,
      ReferencedPackagesService packagesService)
   {
      this.execEnvService = execEnvService;
      this.packagesService = packagesService;
   }

   public void append(@NotNull BundleCandidate bundle)
   {
      final BundleManifest manifest = bundle.getManifest();

      String execEnv = manifest.getHeaderValue(BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT);
      if (execEnv != null)
      {
         LOGGER.debug("Execution environment is already set to {}. Skipping automatic investigation.", execEnv);
         return;
      }

      final JavaResourceBundle jBundle = bundle.getContent();
      append(manifest, jBundle);
   }

   private void append(final BundleManifest manifest, final JavaResourceBundle jBundle)
   {
      final int major = resolveMajorClassVersion(jBundle);

      final List<ExecutionEnvironment> winners = determineExecutionEnvironments(jBundle, major);

      append(manifest, major, winners);
   }

   private List<ExecutionEnvironment> determineExecutionEnvironments(final JavaResourceBundle jBundle, final int major)
   {
      final List<ExecutionEnvironment> winners = new ArrayList<ExecutionEnvironment>();

      final List<String> packageNames = packagesService.getNamesOfReferencedPackages(jBundle);
      jBundle.accept(new ResourceVisitor()
      {
         public boolean visit(Resource resource)
         {
            if (resource instanceof JavaPackage)
            {
               packageNames.remove(((JavaPackage) resource).getQualifiedName());
            }
            return resource instanceof JavaResourceDirectory;
         }
      });


      long bestClassVersionRating = Long.MIN_VALUE;
      int bestPackageRating = Integer.MIN_VALUE;

      for (ExecutionEnvironment executionEnvironment : execEnvService.getExecutionEnvironments())
      {
         // bigger is better, 0 is best
         final int packageRating = getPackageRating(packageNames, executionEnvironment);
         if (packageRating >= bestPackageRating)
         {
            // bigger is better, 0 is best
            final long classVersionRating = getClassVersionRating(major, executionEnvironment);

            final boolean betterPackageRating = packageRating > bestPackageRating;
            final boolean betterClassVersionRating = classVersionRating > bestClassVersionRating;
            final boolean supportsClassVersion = major <= executionEnvironment.getMaxClassVersion();

            if (betterPackageRating && supportsClassVersion || betterClassVersionRating)
            {
               bestPackageRating = packageRating;
               bestClassVersionRating = classVersionRating;

               winners.clear();
               winners.add(executionEnvironment);
            }
            else if (classVersionRating == bestClassVersionRating)
            {
               if (betterPackageRating)
               {
                  bestPackageRating = packageRating;
                  winners.clear();
               }

               final ExecutionEnvironment prev = winners.size() > 0 ? winners.get(winners.size() - 1) : null;
               if (prev == null || !execEnvService.isCompatible(executionEnvironment, prev))
               {
                  winners.add(executionEnvironment);
               }
            }
         }
      }

      return winners;
   }

   private void append(final BundleManifest manifest, final int major, final List<ExecutionEnvironment> winners)
   {
      if (!winners.isEmpty())
      {
         Collections.reverse(winners);

         final List<String> execEnvIds = new ArrayList<String>();
         for (ExecutionEnvironment winner : winners)
         {
            execEnvIds.add(winner.getId());

            if (major > winner.getMaxClassVersion())
            {
               final Object[] args = new Object[3];
               args[0] = String.valueOf(major);
               args[1] = winner.getId();
               args[2] = String.valueOf(winner.getMaxClassVersion());
               LOGGER
                  .warn(
                     "Classes with major class version ({}) detected. Selected execution environment {} only allows versions less or equal {}.",
                     args);
            }
         }

         manifest.setBundleRequiredExecutionEnvironment(execEnvIds);
         LOGGER.info("Set Bundle-RequiredExecutionEnvironment to {}",
            manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT));
      }
   }

   private long getClassVersionRating(final int major, ExecutionEnvironment executionEnvironment)
   {
      final int diff = executionEnvironment.getMaxClassVersion() - major;

      return -1L * (Math.round(Math.pow(diff, 2)));
      // return diff == 0 ? Integer.MAX_VALUE : diff;
   }

   private int getPackageRating(final Collection<String> packageNames, ExecutionEnvironment executionEnvironment)
   {
      int packageHits = 0;
      for (String packageName : executionEnvironment.getPackages())
      {
         if (packageNames.contains(packageName))
         {
            packageHits++;
         }
      }
      return packageHits - packageNames.size();
   }

   private int resolveMajorClassVersion(JavaResourceBundle jBundle)
   {
      final int[] major = new int[1];
      jBundle.accept(new ResourceVisitor()
      {
         public boolean visit(Resource resource)
         {
            if (resource instanceof JavaClass)
            {
               major[0] = Math.max(major[0], ((JavaClass) resource).getMajor());
            }
            return resource instanceof JavaResourceDirectory;
         }
      });
      return major[0];
   }
}
