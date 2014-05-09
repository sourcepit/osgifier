/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static org.sourcepit.common.utils.io.IO.osgiIn;
import static org.sourcepit.common.utils.io.IO.read;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.io.Read.FromStream;
import org.sourcepit.common.utils.lang.PipedException;
import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgify.core.bundle.DynamicPackageImportAppender;
import org.sourcepit.osgify.core.bundle.PackageExportAppender;
import org.sourcepit.osgify.core.bundle.PackageImportAppender;
import org.sourcepit.osgify.core.bundle.RequiredExecutionEnvironmentAppender;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.BundleContentAppender;
import org.sourcepit.osgify.core.resolve.SymbolicNameConflictResolver;
import org.sourcepit.osgify.core.resolve.SymbolicNameResolver;
import org.sourcepit.osgify.core.resolve.VersionResolver;
import org.sourcepit.osgify.core.util.OsgifyContextUtils;
import org.sourcepit.osgify.core.util.OsgifyContextUtils.BuildOrder;

@Named
public class OsgifyContextInflator
{
   @Inject
   private Logger log;

   @Inject
   private BundleContentAppender bundleContentAppender;

   @Inject
   private SymbolicNameResolver symbolicNameResolver;

   @Inject
   private VersionResolver versionResolver;

   @Inject
   private RequiredExecutionEnvironmentAppender environmentAppender;

   @Inject
   private PackageExportAppender packageExports;

   @Inject
   private DynamicPackageImportAppender dynamicImports;

   @Inject
   private PackageImportAppender packageImports;

   @Inject
   private SymbolicNameConflictResolver nameConflictResolver;

   public void infalte(ManifestGeneratorFilter generatorFilter, PropertiesSource options,
      final OsgifyContext osgifyModel, Date timestamp)
   {
      options = getOptions(options, timestamp);
      applyNativeBundles(osgifyModel, generatorFilter, options); // required to determine whether do scan java content
      bundleContentAppender.appendContents(osgifyModel); // required to determine bundle name
      applySymbolicNameAndVersion(generatorFilter, osgifyModel, options);
      applyBuildOrder(osgifyModel);
      applyManifests(generatorFilter, options, osgifyModel);
   }

   private PropertiesSource getOptions(final PropertiesSource options, Date timestamp)
   {
      final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
      format.setTimeZone(TimeZone.getTimeZone("UTC"));
      final String ctxQualifier = format.format(timestamp);
      return new AbstractPropertiesSource()
      {
         @Override
         public String get(String key)
         {
            if ("osgifier.forceContextQualifier".equals(key))
            {
               return ctxQualifier;
            }
            return options == null ? null : options.get(key);
         }
      };
   }

   private void applyBuildOrder(final OsgifyContext osgifyModel)
   {
      final EList<BundleCandidate> bundles = osgifyModel.getBundles();
      final BuildOrder buildOrder = OsgifyContextUtils.computeBuildOrder(osgifyModel);
      final List<BundleCandidate> orderedBundles = buildOrder.getOrderedBundles();
      for (int i = 0; i < orderedBundles.size(); i++)
      {
         bundles.move(i, orderedBundles.get(i));
      }

      boolean printBuildOrder = false;
      if (printBuildOrder)
      {
         log.info("");
         log.info("Bundeling order:");
         logBuildOrder(buildOrder);
      }
   }

   private void logBuildOrder(BuildOrder buildOrder)
   {
      final Set<BundleCandidate> cyclicBundles = new HashSet<BundleCandidate>();
      for (List<BundleCandidate> cycle : buildOrder.getCycles())
      {
         cyclicBundles.addAll(cycle);
      }
      for (BundleCandidate bundle : buildOrder.getOrderedBundles())
      {
         if (cyclicBundles.contains(bundle))
         {
            log.warn("* {} --- {} (cyclic requirements detected)", getName(bundle), getBundleKey(bundle));
         }
         else
         {
            log.info("* {} --- {}", getName(bundle), getBundleKey(bundle));
         }
      }
   }

   private String getName(BundleCandidate bundle)
   {
      return bundle.getExtension(MavenArtifact.class).getArtifactKey().toString();
   }

   private void applyManifests(ManifestGeneratorFilter generatorFilter, PropertiesSource options,
      OsgifyContext osgifyModel)
   {
      for (BundleCandidate bundle : osgifyModel.getBundles())
      {
         if (bundle.isNativeBundle())
         {
            log.info(">>> {} = {} (skipped native bundle)", getName(bundle), getBundleKey(bundle));
         }
         else
         {
            log.info(">>> {} -> {}", getName(bundle), getBundleKey(bundle));

            appendMavenHeaders(bundle);

            if (generatorFilter.isSourceBundle(bundle))
            {
               final BundleCandidate targetBundle = bundle.getTargetBundle();
               final Version version = targetBundle.getVersion();
               final BundleManifest manifest = bundle.getManifest();
               manifest.setHeader("Eclipse-SourceBundle", targetBundle.getSymbolicName() + ";version=\"" + version
                  + "\";roots:=\".\"");
            }
            else
            {
               environmentAppender.append(bundle);
               packageExports.append(options, bundle);
               packageImports.append(bundle);
               dynamicImports.append(bundle);
            }
         }
      }
   }

   private void appendMavenHeaders(BundleCandidate bundle)
   {
      final MavenArtifact extension = bundle.getExtension(MavenArtifact.class);
      final BundleManifest manifest = bundle.getManifest();
      manifest.setHeader("Maven-GroupId", extension.getGroupId());
      manifest.setHeader("Maven-ArtifactId", extension.getArtifactId());
      manifest.setHeader("Maven-Type", extension.getType());
      final String classifier = extension.getClassifier();
      if (!isNullOrEmpty(classifier))
      {
         manifest.setHeader("Maven-Classifier", classifier);
      }
      manifest.setHeader("Maven-Version", extension.getVersion());
   }

   private void applySymbolicNameAndVersion(ManifestGeneratorFilter generatorFilter, OsgifyContext osgifyModel,
      PropertiesSource options)
   {
      final Map<String, BundleCandidate> keyToBundle = new HashMap<String, BundleCandidate>();
      final List<BundleCandidate> sourceBundles = new ArrayList<BundleCandidate>();

      final List<BundleCandidate> bundles = new ArrayList<BundleCandidate>();

      // register native keys we cannot override
      for (BundleCandidate bundle : osgifyModel.getBundles())
      {
         if (bundle.isNativeBundle())
         {
            keyToBundle.put(getBundleKey(bundle), bundle);
         }
         else
         {
            bundles.add(bundle);
         }
      }

      // for all non-native bundles
      for (BundleCandidate bundle : bundles)
      {
         if (generatorFilter.isSourceBundle(bundle))
         {
            sourceBundles.add(bundle);
         }
         else
         {
            final BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
            bundle.setManifest(manifest);

            final String symbolicName = symbolicNameResolver.resolveSymbolicName(bundle, options);
            checkState(symbolicName != null, "Failed to determine bundle symbolic name for %s", bundle.getLocation());
            manifest.setBundleSymbolicName(symbolicName);
            bundle.setSymbolicName(symbolicName);

            final Version version = versionResolver.resolveVersion(bundle, options);
            checkState(version != null, "Failed to determine bundle version for %s", bundle.getLocation());
            manifest.setBundleVersion(version);
            bundle.setVersion(version);

            final String bundleKey = getBundleKey(bundle);

            final BundleCandidate conflictBundle = keyToBundle.get(bundleKey);
            if (conflictBundle == null)
            {
               keyToBundle.put(bundleKey, bundle);
            }
            else
            {
               final List<String> conflictNames;
               if (conflictBundle.isNativeBundle())
               {
                  conflictNames = Collections.singletonList(conflictBundle.getSymbolicName());
               }
               else
               {
                  conflictNames = symbolicNameResolver.resolveSymbolicNames(conflictBundle, options);
               }

               final List<String> names = symbolicNameResolver.resolveSymbolicNames(bundle, options);
               if (nameConflictResolver.resolveNameConflict(conflictBundle, conflictNames, bundle, names))
               {
                  keyToBundle.remove(bundleKey);
                  keyToBundle.put(getBundleKey(conflictBundle), conflictBundle);
                  keyToBundle.put(getBundleKey(bundle), bundle);
               }
               else
               {
                  // TODO panic!
               }
            }
         }
      }

      for (BundleCandidate bundle : sourceBundles)
      {
         final BundleCandidate targetBundle = bundle.getTargetBundle();
         final String symbolicName = targetBundle.getSymbolicName();
         final Version version = targetBundle.getVersion();

         final BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
         bundle.setManifest(manifest);

         manifest.getBundleSymbolicName(true).setSymbolicName(symbolicName + ".source");
         bundle.setSymbolicName(symbolicName + ".source");

         manifest.setBundleVersion(version);
         bundle.setVersion(version);
      }
   }

   private static String getBundleKey(BundleCandidate bundle)
   {
      return bundle.getSymbolicName() + "_" + bundle.getVersion().toString();
   }

   private void applyNativeBundles(final OsgifyContext osgifyModel, ManifestGeneratorFilter generatorFilter,
      PropertiesSource options)
   {
      final List<BundleCandidate> overriddenNativeBundles = new ArrayList<BundleCandidate>();
      for (BundleCandidate bundle : osgifyModel.getBundles())
      {
         if (bundle.getLocation() != null)
         {
            final FromStream<Manifest> fromStream = new FromStream<Manifest>()
            {
               @Override
               public Manifest read(InputStream inputStream) throws Exception
               {
                  final Resource resource = new GenericManifestResourceImpl();
                  resource.load(inputStream, null);
                  return (Manifest) resource.getContents().get(0);
               }
            };

            try
            {
               final Manifest m = read(fromStream, osgiIn(bundle.getLocation(), "META-INF/MANIFEST.MF"));
               if (m instanceof BundleManifest)
               {
                  BundleManifest manifest = (BundleManifest) m;
                  if (generatorFilter.isOverrideNativeBundle(bundle, manifest, options))
                  {
                     overriddenNativeBundles.add(bundle);
                  }
                  else
                  {
                     bundle.setNativeBundle(true);
                     bundle.setManifest(manifest);

                     // TODO deprecate! replace with operation
                     bundle.setSymbolicName(manifest.getBundleSymbolicName().getSymbolicName());
                     bundle.setVersion(manifest.getBundleVersion());
                  }
               }
            }
            catch (PipedException e)
            {
               if (e.adapt(FileNotFoundException.class) == null)
               {
                  throw e;
               }
            }
         }
      }
   }
}
