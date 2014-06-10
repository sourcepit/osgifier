/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.common.utils.collections.MultiValueHashMap;
import org.sourcepit.common.utils.collections.MultiValueMap;
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
      return ee1.isCompatibleWith(ee2);
   }

   public List<ExecutionEnvironment> getExecutionEnvironments(@NotNull Collection<String> executionEnvironmentIds)
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
      return execEnvs;
   }

   public ExecutionEnvironment getExecutionEnvironment(@NotNull String executionEnvironmentId)
   {
      return executionEnvironmentsMap.get(executionEnvironmentId);
   }

   @Override
   public List<String> getIntersectingPackagesByIds(Collection<String> executionEnvironmentIds)
   {
      return getIntersectingPackages(getExecutionEnvironments(executionEnvironmentIds));
   }

   @Override
   public List<String> getIntersectingPackages(Collection<ExecutionEnvironment> executionEnvironments)
   {
      final List<Collection<String>> packages = new ArrayList<Collection<String>>(executionEnvironments.size());
      for (ExecutionEnvironment executionEnvironment : executionEnvironments)
      {
         packages.add(executionEnvironment.getPackages());
      }
      final List<String> result = new ArrayList<String>(newIntersection(packages));
      Collections.sort(result);
      return result;
   }

   static <T> Set<T> newIntersection(Collection<Collection<T>> collections)
   {
      final Set<T> intersection = new HashSet<T>();
      final Iterator<Collection<T>> it = collections.iterator();
      if (it.hasNext())
      {
         intersection.addAll(it.next());
         while (it.hasNext())
         {
            intersection.retainAll(it.next());
         }
      }
      return intersection;
   }
}
