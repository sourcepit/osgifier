/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.bundle;


import java.util.List;
import java.util.WeakHashMap;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.modularizor.core.ee.ExecutionEnvironmentService;
import org.sourcepit.modularizor.core.java.inspect.ClassForNameDetector;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.java.JavaType;

import com.google.common.collect.Multimap;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
@Singleton
public class BundleRequirementsService
{
   private ExecutionEnvironmentService environmentService;

   private volatile WeakHashMap<BundleCandidate, BundleRequiredPackagesCollector> collectorsCache = new WeakHashMap<BundleCandidate, BundleRequiredPackagesCollector>();

   private volatile WeakHashMap<BundleCandidate, BundleRequiredPackagesResolver> resolversCache = new WeakHashMap<BundleCandidate, BundleRequiredPackagesResolver>();

   @Inject
   public BundleRequirementsService(ExecutionEnvironmentService environmentService)
   {
      this.environmentService = environmentService;
   }

   private BundleRequiredPackagesCollector getRequirementsCollector(BundleCandidate bundle)
   {
      BundleRequiredPackagesCollector requirementsCollector = collectorsCache.get(bundle);
      if (requirementsCollector == null)
      {
         synchronized (collectorsCache)
         {
            requirementsCollector = collectorsCache.get(bundle);
            if (requirementsCollector == null)
            {
               requirementsCollector = new BundleRequiredPackagesCollector(bundle);
               requirementsCollector.collect();
               collectorsCache.put(bundle, requirementsCollector);
            }
         }
      }
      return requirementsCollector;
   }

   private BundleRequiredPackagesResolver getPackageResolver(BundleCandidate bundle)
   {
      BundleRequiredPackagesResolver packageResolver = resolversCache.get(bundle);
      if (packageResolver == null)
      {
         synchronized (resolversCache)
         {
            packageResolver = resolversCache.get(bundle);
            if (packageResolver == null)
            {
               packageResolver = new BundleRequiredPackagesResolver(bundle, environmentService);
               packageResolver.resolve(getRequiredPackages(bundle));
               resolversCache.put(bundle, packageResolver);
            }
         }
      }
      return packageResolver;
   }

   public List<String> getRequiredPackages(BundleCandidate bundle)
   {
      return getRequirementsCollector(bundle).getRequiredPackages();
   }

   public Multimap<String, String> getRequiredToDemandingPackages(BundleCandidate bundle)
   {
      return getRequirementsCollector(bundle).getRequiredToDemandingPackages();
   }

   public List<PackageReference> getPossiblePackageReferences(BundleCandidate bundle, String packageName)
   {
      return getPackageResolver(bundle).getPossiblePackageReferences(packageName);
   }

   public boolean usesClassForName(JavaResourceBundle jBundle)
   {
      try
      {
         jBundle.accept(new TypeVisitor()
         {
            @Override
            protected void visit(JavaType jType)
            {
               final Annotation annotation = jType.getAnnotation(ClassForNameDetector.SOURCE);
               if (annotation != null)
               {
                  if (annotation.getData(ClassForNameDetector.CLASS_FOR_NAME, false))
                  {
                     throw new IllegalStateException("break");
                  }
               }
            }
         });
      }
      catch (IllegalStateException e)
      {
         if ("break".equals(e.getMessage()))
         {
            return true;
         }
         throw e;
      }
      return false;
   }
}
