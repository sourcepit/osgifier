/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee.impl;

import static org.sourcepit.osgify.core.ee.AccessRule.ACCESSIBLE;
import static org.sourcepit.osgify.core.ee.AccessRule.DISCOURAGED;
import static org.sourcepit.osgify.core.ee.AccessRule.NON_ACCESSIBLE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.sourcepit.common.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.utils.collections.MultiValueHashMap;
import org.sourcepit.common.utils.collections.MultiValueMap;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentImplementation;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
@Singleton
public class ExecutionEnvironmentServiceImpl implements ExecutionEnvironmentService
{
   private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionEnvironmentServiceImpl.class);

   private final Map<String, ExecutionEnvironment> executionEnvironmentsMap = new HashMap<String, ExecutionEnvironment>();

   private final List<ExecutionEnvironment> executionEnvironmentsList = new ArrayList<ExecutionEnvironment>();

   private final MultiValueMap<String, ExecutionEnvironmentImplementation> executionEnvironmentImpls = new MultiValueHashMap<String, ExecutionEnvironmentImplementation>(
      ArrayList.class);

   private final Map<String, PackageAccess> packageAccessCache = new HashMap<String, PackageAccess>();

   @Inject
   public ExecutionEnvironmentServiceImpl(List<ExecutionEnvironmentContributor> contributors)
   {
      initializeExecutionEnvironments(contributors);
      initializeImplementations(contributors);
   }

   private void initializeExecutionEnvironments(List<ExecutionEnvironmentContributor> contributors)
   {
      for (ExecutionEnvironmentContributor contributor : contributors)
      {
         final List<ExecutionEnvironment> ees = contributor.getExecutionEnvironments();
         if (ees != null)
         {
            for (ExecutionEnvironment ee : ees)
            {
               final String eeId = ee.getId();
               if (executionEnvironmentsMap.containsKey(eeId))
               {
                  LOGGER.debug("ExecutionEnvironment {} is already registered", eeId);
               }
               else
               {
                  executionEnvironmentsMap.put(eeId, ee);
                  LOGGER.debug("Registered execution environment {}", eeId);
               }
            }
         }
      }
      executionEnvironmentsList.addAll(executionEnvironmentsMap.values());
      Collections.sort(executionEnvironmentsList);
   }

   private void initializeImplementations(List<ExecutionEnvironmentContributor> contributors)
   {
      for (ExecutionEnvironmentContributor contributor : contributors)
      {
         final List<ExecutionEnvironmentImplementation> eeImpls = contributor.getExecutionEnvironmentImplementations();
         if (eeImpls != null)
         {
            for (ExecutionEnvironmentImplementation eeImpl : eeImpls)
            {
               final String eeId = eeImpl.getExecutionEnvironmentId();
               if (executionEnvironmentsMap.containsKey(eeId))
               {
                  executionEnvironmentImpls.get(eeId, true).add(eeImpl);
               }
               else
               {
                  LOGGER.warn("Execution environment {} is not registered. Ignoring implementation of vendor {}", eeId,
                     eeImpl.getVendor());
               }
            }
         }
      }
   }

   public List<ExecutionEnvironment> getExecutionEnvironments()
   {
      return executionEnvironmentsList;
   }

   public List<ExecutionEnvironment> getCompatibleExecutionEnvironments(ExecutionEnvironment executionEnvironment)
   {
      final List<ExecutionEnvironment> compatibles = new ArrayList<ExecutionEnvironment>();
      collectCompatibleExecutionEnvironments(compatibles, executionEnvironment);
      return compatibles;
   }

   private void collectCompatibleExecutionEnvironments(final List<ExecutionEnvironment> compatibles,
      ExecutionEnvironment executionEnvironment)
   {
      for (ExecutionEnvironment candidateExecEnv : getExecutionEnvironments())
      {
         final String targetId = candidateExecEnv.getId();
         final String candidateId = executionEnvironment.getId();

         if (!targetId.equals(candidateId) && isCompatible(candidateExecEnv, executionEnvironment))
         {
            compatibles.add(candidateExecEnv);
         }
      }
   }

   public List<ExecutionEnvironmentImplementation> getExecutionEnvironmentImplementations()
   {
      final List<ExecutionEnvironmentImplementation> result = new ArrayList<ExecutionEnvironmentImplementation>();
      for (ExecutionEnvironment execEnv : getExecutionEnvironments())
      {
         result.addAll(executionEnvironmentImpls.get(execEnv.getId(), true));
      }
      return result;
   }

   public boolean isCompatible(@NotNull ExecutionEnvironment ee1, @NotNull ExecutionEnvironment ee2)
   {
      final float max1 = ee1.getMaxClassVersion();
      final float max2 = ee2.getMaxClassVersion();
      if (max1 >= max2)
      {
         final List<String> p1 = ee1.getPackages();
         final List<String> p2 = ee2.getPackages();
         return p1.containsAll(p2);
      }
      return false;
   }

   public ExecutionEnvironment getExecutionEnvironment(@NotNull String executionEnvironmentId)
   {
      return executionEnvironmentsMap.get(executionEnvironmentId);
   }

   public AccessRule getAccessRuleById(@NotNull String executionEnvironmentId, @NotNull String packageName)
   {
      return getAccessRuleById(Collections.singleton(executionEnvironmentId), packageName);
   }

   public AccessRule getAccessRuleById(@NotNull Collection<String> executionEnvironmentIds, @NotNull String packageName)
   {
      return getPackageAccessById(executionEnvironmentIds).getAccessRule(packageName);
   }

   public AccessRule getAccessRule(@NotNull ExecutionEnvironment executionEnvironment, @NotNull String packageName)
   {
      return getAccessRule(Collections.singleton(executionEnvironment), packageName);
   }

   public AccessRule getAccessRule(@NotNull Collection<ExecutionEnvironment> executionEnvironments,
      @NotNull String packageName)
   {
      return getPackageAccess(executionEnvironments).getAccessRule(packageName);
   }

   private synchronized PackageAccess getPackageAccessById(Collection<String> executionEnvironmentIds)
   {
      final String cacheKey = getPackageAccessCacheKeyById(executionEnvironmentIds);
      PackageAccess packageAccess = packageAccessCache.get(cacheKey);
      if (packageAccess == null)
      {
         List<ExecutionEnvironment> execEnvs = new ArrayList<ExecutionEnvironment>();
         for (String id : executionEnvironmentIds)
         {
            ExecutionEnvironment execEnv = getExecutionEnvironment(id);
            if (execEnv == null)
            {
               throw new IllegalArgumentException("no execution environment registered for id " + id);
            }
            execEnvs.add(execEnv);
         }
         packageAccess = newPackageAccess(execEnvs);
         packageAccessCache.put(cacheKey, packageAccess);
      }
      return packageAccess;
   }

   private synchronized PackageAccess getPackageAccess(Collection<ExecutionEnvironment> executionEnvironments)
   {
      final String cacheKey = getPackageAccessCacheKey(executionEnvironments);
      PackageAccess packageAccess = packageAccessCache.get(cacheKey);
      if (packageAccess == null)
      {
         packageAccess = newPackageAccess(executionEnvironments);
         packageAccessCache.put(cacheKey, packageAccess);
      }
      return packageAccess;
   }

   private String getPackageAccessCacheKey(Collection<ExecutionEnvironment> executionEnvironments)
   {
      final StringBuilder sb = new StringBuilder();
      for (ExecutionEnvironment execEnv : executionEnvironments)
      {
         sb.append(execEnv.getId());
      }
      return sb.toString();
   }

   private String getPackageAccessCacheKeyById(Collection<String> executionEnvironmentIdS)
   {
      final StringBuilder sb = new StringBuilder();
      for (String id : executionEnvironmentIdS)
      {
         sb.append(id);
      }
      return sb.toString();
   }

   // TODO intersect! not accumulate!
   private PackageAccess newPackageAccess(Collection<ExecutionEnvironment> executionEnvironments)
   {
      final Collection<String> accessible = Collections.unmodifiableCollection(intersect(executionEnvironments));
      final Collection<String> discouraged = new HashSet<String>();
      for (ExecutionEnvironment executionEnvironment : executionEnvironments)
      {
         discouraged.addAll(executionEnvironment.getPackages());
         final Collection<ExecutionEnvironmentImplementation> implementations = getImplementations(
            executionEnvironment, true);
         for (ExecutionEnvironmentImplementation implementation : implementations)
         {
            discouraged.addAll(getExecutionEnvironment(implementation.getExecutionEnvironmentId()).getPackages());
            discouraged.addAll(implementation.getVendorPackages());
         }
      }
      discouraged.removeAll(accessible);
      return new PackageAccess(accessible, discouraged);
   }

   private synchronized Collection<String> intersect(Collection<ExecutionEnvironment> executionEnvironments)
   {
      if (executionEnvironments.size() == 1)
      {
         return executionEnvironments.iterator().next().getPackages();
      }

      final Set<String> intersection = new HashSet<String>();
      for (ExecutionEnvironment execEnv : executionEnvironments)
      {
         intersection.addAll(execEnv.getPackages());
      }
      for (ExecutionEnvironment execEnv : executionEnvironments)
      {
         intersection.retainAll(execEnv.getPackages());
      }

      return intersection;
   }

   private Collection<ExecutionEnvironmentImplementation> getImplementations(
      final ExecutionEnvironment executionEnvironment, boolean considerCompatibles)
   {
      final List<ExecutionEnvironment> scope = new ArrayList<ExecutionEnvironment>();
      scope.add(executionEnvironment);
      if (considerCompatibles)
      {
         collectCompatibleExecutionEnvironments(scope, executionEnvironment);
      }
      final List<ExecutionEnvironmentImplementation> result = new ArrayList<ExecutionEnvironmentImplementation>();
      for (ExecutionEnvironment execEnv : scope)
      {
         result.addAll(executionEnvironmentImpls.get(execEnv.getId(), true));
      }
      return result;
   }

   private static final class PackageAccess
   {
      private final Collection<String> accessible, discouraged;

      public PackageAccess(Collection<String> accessible, Collection<String> discouraged)
      {
         this.accessible = accessible;
         this.discouraged = discouraged;
      }

      public AccessRule getAccessRule(String packageName)
      {
         if (getAccessible().contains(packageName))
         {
            return ACCESSIBLE;
         }

         if (getDiscouraged().contains(packageName))
         {
            return DISCOURAGED;
         }

         return NON_ACCESSIBLE;
      }

      public Collection<String> getAccessible()
      {
         return accessible;
      }

      public Collection<String> getDiscouraged()
      {
         return discouraged;
      }
   }
}
