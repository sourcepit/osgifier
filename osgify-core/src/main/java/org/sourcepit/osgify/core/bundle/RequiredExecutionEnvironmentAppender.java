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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.manifest.osgi.BundleHeaderName;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.java.JavaClass;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.Resource;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;
import org.sourcepit.osgify.core.util.OptionsUtils;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class RequiredExecutionEnvironmentAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(RequiredExecutionEnvironmentAppender.class);

   private ExecutionEnvironmentService execEnvService;

   private BundleRequirementsService packagesService;

   @Inject
   public RequiredExecutionEnvironmentAppender(ExecutionEnvironmentService execEnvService,
      BundleRequirementsService packagesService)
   {
      this.execEnvService = execEnvService;
      this.packagesService = packagesService;
   }

   public void append(@NotNull BundleCandidate bundle, PropertiesSource options)
   {
      final BundleManifest manifest = bundle.getManifest();

      String execEnv = manifest.getHeaderValue(BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT);
      if (execEnv != null)
      {
         LOGGER.debug("Execution environment is already set to {}. Skipping automatic investigation.", execEnv);
         return;
      }

      final JavaResourceBundle jBundle = bundle.getContent();
      append(bundle, manifest, jBundle, options);
   }

   private void append(BundleCandidate bundle, final BundleManifest manifest, final JavaResourceBundle jBundle,
      PropertiesSource options)
   {
      final float requiredClassVersion = resolveMajorClassVersion(jBundle);
      final List<String> requiredPackages = getRequiredPackagesNotContainedInAnyDependency(bundle);

      List<ExecutionEnvironment> ees = getMappedEEs(bundle, options);
      if (ees.isEmpty())
      {
         ees = determineEEs(requiredClassVersion, requiredPackages, options);
      }

      append(manifest, requiredClassVersion, ees);
   }

   private List<ExecutionEnvironment> determineEEs(final float requiredClassVersion,
      final List<String> requiredPackages, PropertiesSource options)
   {
      final Set<String> excludes = getExcludedExecutionEnvironments(options);

      final Collection<ExecutionEnvironment> executionEnvironments = Collections2.filter(
         execEnvService.getExecutionEnvironments(), new Predicate<ExecutionEnvironment>()
         {
            @Override
            public boolean apply(ExecutionEnvironment input)
            {
               return !excludes.contains(input.getId());
            }
         });

      return determineCompatibleExecutionEnvironments(executionEnvironments, requiredClassVersion, requiredPackages);
   }

   private List<ExecutionEnvironment> getMappedEEs(BundleCandidate bundle, PropertiesSource options)
   {
      final List<String> mappedEEIds = getMappedEEIds(bundle, options);

      final List<ExecutionEnvironment> mappedEEs = new ArrayList<ExecutionEnvironment>(mappedEEIds.size());
      for (String eeId : mappedEEIds)
      {
         final ExecutionEnvironment ee = execEnvService.getExecutionEnvironment(eeId);
         if (ee == null)
         {
            throw new IllegalStateException("Unknown executionenvironment " + eeId);
         }
         mappedEEs.add(ee);
      }

      return mappedEEs;
   }

   private static List<String> getMappedEEIds(BundleCandidate bundle, PropertiesSource options)
   {
      final String symbolicName = bundle.getSymbolicName();
      final Version version = bundle.getVersion();
      return getMappedEEIds(options, symbolicName, version);
   }

   static List<String> getMappedEEIds(PropertiesSource options, final String symbolicName, final Version version)
   {
      final Map<String, String> eeMappings = OptionsUtils.parseMapValue(options.get(
         "osgifier.executionEnvironmentMappings", ""));

      String mapping = eeMappings.get(symbolicName + "_" + version);
      if (mapping == null)
      {
         mapping = eeMappings.get(symbolicName);
      }

      if (mapping == null)
      {
         return Collections.emptyList();
      }

      final String[] split = mapping.split("\\|");

      final List<String> mappedEEs = new ArrayList<String>(split.length);
      for (String ee : split)
      {
         ee = ee.trim();
         if (ee.length() > 0)
         {
            mappedEEs.add(ee);
         }
      }
      return mappedEEs;
   }

   private List<String> getRequiredPackagesNotContainedInAnyDependency(BundleCandidate bundle)
   {
      final List<String> requiredPackages = new ArrayList<String>(packagesService.getRequiredPackages(bundle));
      removeBundlePackages(requiredPackages, bundle);
      for (BundleReference bundleReference : bundle.getDependencies())
      {
         removeBundlePackages(requiredPackages, bundleReference.getTarget());
      }
      return requiredPackages;
   }

   private static void removeBundlePackages(final List<String> requiredPackages, BundleCandidate bundle)
   {
      JavaBundlePackagesCollector packagesCollector = new JavaBundlePackagesCollector(bundle.getContent());
      packagesCollector.collect();
      requiredPackages.removeAll(packagesCollector.getBundlePackages());
   }

   static final Set<String> getExcludedExecutionEnvironments(PropertiesSource options)
   {
      final String[] split = options.get("osgifier.excludedExecutionEnvironments", "").split(",");
      final Set<String> excludes = new HashSet<String>();
      for (String string : split)
      {
         final String exclude = string.trim();
         if (!exclude.isEmpty())
         {
            excludes.add(exclude);
         }
      }
      return excludes;
   }

   private static List<ExecutionEnvironment> determineCompatibleExecutionEnvironments(
      final Collection<ExecutionEnvironment> executionEnvironments, final float requiredClassVersion,
      final Collection<String> requiredPackages)
   {
      final Set<String> requiredEEPackages = new HashSet<String>(requiredPackages);
      requiredEEPackages.retainAll(getAllPackages(executionEnvironments));

      final List<ExecutionEnvironment> compatibleEEs = new ArrayList<ExecutionEnvironment>();
      for (ExecutionEnvironment ee : executionEnvironments)
      {
         if (isCompatible(ee, requiredClassVersion, requiredEEPackages) && !containsCompatible(compatibleEEs, ee))
         {
            compatibleEEs.add(ee);
         }
      }

      return compatibleEEs;
   }

   private static boolean containsCompatible(final List<ExecutionEnvironment> ees, ExecutionEnvironment ee)
   {
      for (ExecutionEnvironment e : ees)
      {
         if (ee.isCompatibleWith(e))
         {
            return true;
         }
      }
      return false;
   }

   private static boolean isCompatible(ExecutionEnvironment ee, final float classVersion, final Set<String> packages)
   {
      return classVersion <= ee.getMaxClassVersion() && ee.getPackages().containsAll(packages);
   }

   private static Set<String> getAllPackages(final Collection<ExecutionEnvironment> executionEnvironments)
   {
      final Set<String> eePackages = new HashSet<String>();
      for (ExecutionEnvironment ee : executionEnvironments)
      {
         eePackages.addAll(ee.getPackages());
      }
      return eePackages;
   }

   private void append(final BundleManifest manifest, final float major, final List<ExecutionEnvironment> ees)
   {
      if (!ees.isEmpty())
      {
         final List<String> execEnvIds = new ArrayList<String>();

         for (ExecutionEnvironment ee : ees)
         {
            execEnvIds.add(ee.getId());
         }

         manifest.setBundleRequiredExecutionEnvironment(execEnvIds);

         LOGGER.info("Set Bundle-RequiredExecutionEnvironment to {}",
            manifest.getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT));
      }
   }

   private float resolveMajorClassVersion(JavaResourceBundle jBundle)
   {
      final float[] major = new float[1];
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
