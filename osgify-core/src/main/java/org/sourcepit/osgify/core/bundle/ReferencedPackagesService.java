/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_REQUIREDEXECUTIONENVIRONMENT;
import static org.sourcepit.osgify.core.ee.AccessRule.ACCESSIBLE;
import static org.sourcepit.osgify.core.ee.AccessRule.NON_ACCESSIBLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgify.core.ee.AccessRule;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentService;
import org.sourcepit.osgify.core.java.inspect.ClassForNameDetector;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaType;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
@Singleton
public class ReferencedPackagesService
{
   private static final Logger LOGGER = LoggerFactory.getLogger(ReferencedPackagesService.class);

   private ExecutionEnvironmentService environmentService;

   private final WeakHashMap<JavaResourceBundle, Multimap<String, String>> cache = new WeakHashMap<JavaResourceBundle, Multimap<String, String>>();

   @Inject
   public ReferencedPackagesService(ExecutionEnvironmentService environmentService)
   {
      this.environmentService = environmentService;
   }

   public Set<String> getNamesOfReferencedPackages(JavaResourceBundle jBundle)
   {
      return getRequiredToDemandingPackages(jBundle).keySet();
   }

   public synchronized Multimap<String, String> getRequiredToDemandingPackages(JavaResourceBundle jBundle)
   {
      Multimap<String, String> requiredToConsumers = cache.get(jBundle);
      if (requiredToConsumers != null)
      {
         return requiredToConsumers;
      }

      requiredToConsumers = LinkedHashMultimap.create();
      collectPackageReferences(requiredToConsumers, jBundle);
      requiredToConsumers = Multimaps.unmodifiableMultimap(requiredToConsumers);

      cache.put(jBundle, requiredToConsumers);
      return requiredToConsumers;
   }

   private static void collectPackageReferences(final Multimap<String, String> requiredToConsumers,
      JavaResourceBundle jBundle)
   {
      jBundle.accept(new TypeReferenceVisitor()
      {
         @Override
         protected void foundTypeReference(JavaType jType, String qualifiedTypeReference)
         {
            final int idx = qualifiedTypeReference.lastIndexOf('.');
            final String packageName = idx > -1 ? qualifiedTypeReference.substring(0, idx) : null;
            if (packageName == null)
            {
               LOGGER.warn("Type " + qualifiedTypeReference + " in default package will be ignored (from "
                  + jType.getQualifiedName() + ")");
            }
            else
            {
               JavaPackage parentPackage = jType.getFile().getParentPackage();
               if (parentPackage == null)
               {
                  LOGGER.warn("Type " + qualifiedTypeReference + " in default package will be ignored (from "
                     + jType.getQualifiedName() + ")");
               }
               else
               {
                  final String consumerPackageName = parentPackage.getQualifiedName();
                  requiredToConsumers.put(packageName, consumerPackageName);
                  LOGGER.debug("Added ref to package " + packageName + " (from " + jType.getQualifiedName() + ")");
               }
            }
         }
      });
   }

   private final WeakHashMap<BundleCandidate, PackageResolver> resolersCache = new WeakHashMap<BundleCandidate, PackageResolver>();

   public ExportDescription determineExporter(BundleCandidate bundle, String packageName)
   {
      return getPackageResolver(bundle).determineExporter(packageName);
   }

   private synchronized PackageResolver getPackageResolver(BundleCandidate bundle)
   {
      PackageResolver packageResolver = resolersCache.get(bundle);
      if (packageResolver == null)
      {
         packageResolver = new PackageResolver(bundle, environmentService);
         resolersCache.put(bundle, packageResolver);
      }
      return packageResolver;
   }

   private static final class PackageResolver
   {
      private final ExecutionEnvironmentService environmentService;

      private final BundleCandidate bundle;

      private final Map<String, List<ExportDescription>> exportersCache = new HashMap<String, List<ExportDescription>>();

      public PackageResolver(BundleCandidate bundle, ExecutionEnvironmentService environmentService)
      {
         this.environmentService = environmentService;
         this.bundle = bundle;
      }

      public synchronized ExportDescription determineExporter(String packageName)
      {
         List<ExportDescription> exportDescriptions = exportersCache.get(packageName);
         if (exportDescriptions == null)
         {
            exportDescriptions = resolvePackage(packageName);
            validate(packageName, exportDescriptions);
         }
         return exportDescriptions.isEmpty() ? null : exportDescriptions.get(0);
      }

      private void validate(String packageName, List<ExportDescription> exportDescriptions)
      {
         if (exportDescriptions.isEmpty())
         {
            LOGGER.warn("Unresolveable referende to package " + packageName);
         }
         else if (exportDescriptions.size() > 1)
         {
            final StringBuilder msg = new StringBuilder();
            msg.append("Unambiguous referende to package ");
            msg.append(packageName);
            msg.append(". Exporters: ");
            for (ExportDescription exportDescription : exportDescriptions)
            {
               appendExporter(msg, exportDescription);
               msg.append(", ");
            }
            msg.deleteCharAt(msg.length() - 2);
            LOGGER.warn(msg.toString());
         }
      }

      private void appendExporter(final StringBuilder msg, ExportDescription exportDescription)
      {
         if (exportDescription.isPackageOfExecutionEnvironment())
         {
            msg.append("Execution Environment or Vendor (");
            msg.append(exportDescription.getImportingBundle().getManifest()
               .getHeaderValue(BUNDLE_REQUIREDEXECUTIONENVIRONMENT));
            msg.append(")");
         }
         else
         {
            final BundleCandidate exportingBundle = exportDescription.getExportingBundle();
            msg.append(exportingBundle.getSymbolicName());
            msg.append('_');
            msg.append(exportingBundle.getVersion().toMinimalString());
         }
      }

      public synchronized List<ExportDescription> resolvePackage(String packageName)
      {
         List<ExportDescription> exportDescriptions = exportersCache.get(packageName);
         if (exportDescriptions == null)
         {
            exportDescriptions = new ArrayList<ExportDescription>();
            exportersCache.put(packageName, exportDescriptions);

            BundleManifest manifest = bundle.getManifest();

            PackageExport packageExport = resolvePackage(manifest.getExportPackage(), packageName);
            if (packageExport != null)
            {
               exportDescriptions.add(new ExportDescription(bundle, packageName, null, bundle, packageExport,
                  ACCESSIBLE));
            }

            final EList<String> executionEnvironments = bundle.getManifest().getBundleRequiredExecutionEnvironment();
            if (executionEnvironments != null)
            {
               final AccessRule accessRule = environmentService.getAccessRuleById(executionEnvironments, packageName);
               if (accessRule != NON_ACCESSIBLE)
               {
                  exportDescriptions.add(new ExportDescription(bundle, packageName, null, null, null, accessRule));
               }
            }

            for (BundleReference bundleReference : bundle.getDependencies())
            {
               final BundleCandidate target = bundleReference.getTarget();

               final BundleManifest targetManifest = target.getManifest();
               if (targetManifest == null) // HACK happens when we have cyclic references
               {
                  continue;
               }
               
               packageExport = resolvePackage(targetManifest.getExportPackage(), packageName);
               if (packageExport != null)
               {
                  exportDescriptions.add(new ExportDescription(bundle, packageName, bundleReference, target,
                     packageExport, ACCESSIBLE));
               }
            }
         }
         return exportDescriptions;
      }

      private PackageExport resolvePackage(EList<PackageExport> exportPackage, String packageName)
      {
         if (exportPackage != null)
         {
            for (PackageExport packageExport : exportPackage)
            {
               if (packageExport.getPackageNames().contains(packageName))
               {
                  return packageExport;
               }
            }
         }
         return null;
      }
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
