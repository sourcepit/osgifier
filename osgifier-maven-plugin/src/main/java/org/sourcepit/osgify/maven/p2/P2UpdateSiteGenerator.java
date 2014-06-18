/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.p2;

import static java.util.Collections.singletonList;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.sourcepit.common.utils.io.IO.buffOut;
import static org.sourcepit.common.utils.io.IO.fileOut;
import static org.sourcepit.common.utils.lang.Exceptions.pipe;
import static org.sourcepit.osgify.maven.p2.BundleSelectorUtils.selectBundles;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.model.Dependency;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleSymbolicName;
import org.sourcepit.common.utils.io.IOOperation;
import org.sourcepit.common.utils.lang.Exceptions;
import org.sourcepit.common.utils.path.PathMatcher;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifyContext;
import org.sourcepit.osgifier.core.packaging.Repackager;
import org.sourcepit.osgifier.maven.DefaultOsgifyContextInflatorFilter;
import org.sourcepit.osgify.maven.context.LegacyOsgifyModelBuilder;
import org.sourcepit.osgify.maven.context.LegacyOsgifyModelBuilder.NativeBundleStrategy;
import org.sourcepit.osgify.p2.P2Publisher;

@Named
public class P2UpdateSiteGenerator
{
   public static final String OPTION_FORCE_MANIFEST_GENERATION = "forceManifestGeneration";
   public static final String OPTION_COMPRESS_REPOSITORY = "compressRepository";
   public static final String OPTION_FORKED_PROCESS_TIMEOUT_IN_SECONDS = "forkedProcessTimeoutInSeconds";

   @Inject
   private LegacyOsgifyModelBuilder modelBuilder;

   @Inject
   private Repackager repackager;

   @Inject
   private P2Publisher p2Publisher;

   public OsgifyContext generateUpdateSite(File siteDir, MavenProject project,
      List<ArtifactRepository> remoteArtifactRepositories, ArtifactRepository localRepository, String repositoryName,
      PropertiesSource options)
   {
      return generateUpdateSite(siteDir, project.getArtifact(), remoteArtifactRepositories, localRepository,
         repositoryName, options);
   }

   public OsgifyContext generateUpdateSite(File siteDir, Artifact artifact,
      List<ArtifactRepository> remoteArtifactRepositories, ArtifactRepository localRepository, String repositoryName,
      PropertiesSource options)
   {
      final LegacyOsgifyModelBuilder.Request request = modelBuilder.createBundleRequest(artifact,
         Artifact.SCOPE_COMPILE, false, remoteArtifactRepositories, localRepository);

      return generateUpdateSite(request, siteDir, repositoryName,
         options == null ? new LinkedPropertiesMap() : options, BundleSelector.ALL);
   }

   @Inject
   private org.sourcepit.osgify.maven.OsgifyModelBuilder modelBuilder2;

   public OsgifyContext generateUpdateSite(File siteDir, List<Dependency> dependencies, boolean includeSources,
      List<ArtifactRepository> remoteArtifactRepositories, ArtifactRepository localRepository, String repositoryName,
      PropertiesSource options, Date startTime, BundleSelector bundleSelector)
   {
      final OsgifyContext model = modelBuilder2.build(new DefaultOsgifyContextInflatorFilter(), options == null
         ? new LinkedPropertiesMap()
         : options, dependencies, startTime);

      final Collection<BundleCandidate> selectedBundles = new LinkedHashSet<BundleCandidate>();
      selectBundles(selectedBundles, model, bundleSelector);

      final boolean compressRepository = options.getBoolean(OPTION_COMPRESS_REPOSITORY, true);
      final int forkedProcessTimeoutInSeconds = options.getInt(OPTION_FORKED_PROCESS_TIMEOUT_IN_SECONDS, 0);
      generateUpdateSite(selectedBundles, siteDir, repositoryName, compressRepository, forkedProcessTimeoutInSeconds);

      return model;
   }

   private OsgifyContext generateUpdateSite(final LegacyOsgifyModelBuilder.Request request, File siteDir,
      String repositoryName, PropertiesSource options, BundleSelector bundleSelector)
   {
      request.setNativeBundleStrategy(getNativeBundleStrategy(options));

      final OsgifyContext bundleContext = modelBuilder.build(request);

      final Collection<BundleCandidate> selectedBundles = new LinkedHashSet<BundleCandidate>();
      selectBundles(selectedBundles, bundleContext, bundleSelector);

      final boolean compressRepository = options.getBoolean(OPTION_COMPRESS_REPOSITORY, true);
      final int forkedProcessTimeoutInSeconds = options.getInt(OPTION_FORKED_PROCESS_TIMEOUT_IN_SECONDS, 0);
      generateUpdateSite(selectedBundles, siteDir, repositoryName, compressRepository, forkedProcessTimeoutInSeconds);

      return bundleContext;
   }


   private NativeBundleStrategy getNativeBundleStrategy(PropertiesSource options)
   {
      final String forceMfPatterns = options.get(OPTION_FORCE_MANIFEST_GENERATION);
      if (forceMfPatterns != null)
      {
         final PathMatcher matcher = PathMatcher.parse(forceMfPatterns, ".", ",");

         return new LegacyOsgifyModelBuilder.NativeBundleStrategy()
         {
            public boolean isNativeBundle(Artifact artifact, MavenProject project, BundleCandidate bundleCandidate)
            {
               final String symbolicName = getBundleSymbolicName(bundleCandidate);
               if (symbolicName != null && matcher.isMatch(symbolicName))
               {
                  return false;
               }
               return LegacyOsgifyModelBuilder.NativeBundleStrategy.DEFAULT.isNativeBundle(artifact, project,
                  bundleCandidate);
            }

            private String getBundleSymbolicName(BundleCandidate bundleCandidate)
            {
               BundleManifest manifest = bundleCandidate.getManifest();
               if (manifest != null)
               {
                  BundleSymbolicName bundleSymbolicName = manifest.getBundleSymbolicName();
                  if (bundleSymbolicName != null)
                  {
                     return bundleSymbolicName.getSymbolicName();
                  }
               }
               return null;
            }
         };
      }

      return LegacyOsgifyModelBuilder.NativeBundleStrategy.DEFAULT;
   }

   private void generateUpdateSite(Collection<BundleCandidate> bundles, File siteDir, String repositoryName,
      boolean compressRepository, int forkedProcessTimeoutInSeconds)
   {
      final File bundlesDir = getCleanDir(siteDir, "plugins");

      copyJarsAndInjectManifests(bundles, bundlesDir);

      p2Publisher.publishBundles(siteDir, repositoryName, singletonList(bundlesDir), compressRepository,
         forkedProcessTimeoutInSeconds);

      final File categoryFile = new File(siteDir, "category.xml");
      writeCategoryXml(categoryFile);

      p2Publisher.publishCategories(siteDir, categoryFile, compressRepository, forkedProcessTimeoutInSeconds);
   }

   private static void writeCategoryXml(File categoryFile)
   {
      final StringBuilder sb = new StringBuilder();
      sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      sb.append("<site>\n");
      sb.append("   <category-def name=\"all\" label=\"All Bundles\"/>\n");
      sb.append("   <iu>\n");
      sb.append("      <category name=\"all\"/>\n");
      sb.append("      <query>\n");
      sb.append("         <expression type=\"match\">providedCapabilities.exists(p | p.namespace == 'osgi.bundle')</expression>\n");
      sb.append("      </query>\n");
      sb.append("   </iu>\n");
      sb.append("</site>\n");

      new IOOperation<OutputStream>(buffOut(fileOut(categoryFile, true)))
      {
         @Override
         protected void run(OutputStream output) throws IOException
         {
            IOUtils.copy(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")), output);
         }
      }.run();
   }

   private List<File> copyJarsAndInjectManifests(Collection<BundleCandidate> bundles, final File workDir)
   {
      List<File> bundleJars = new ArrayList<File>();
      for (BundleCandidate bundle : bundles)
      {
         final File bundleJar = new File(workDir, bundle.getSymbolicName() + "_" + bundle.getVersion().toFullString()
            + ".jar");

         if (bundle.isNativeBundle())
         {
            try
            {
               copyFile(bundle.getLocation(), bundleJar);
            }
            catch (IOException e)
            {
               throw pipe(e);
            }
         }
         else
         {
            repackager.copyJarAndInjectManifest(bundle.getLocation(), bundleJar, bundle.getManifest());
         }
         bundle.setLocation(bundleJar);
         bundleJars.add(bundleJar);
      }
      return bundleJars;
   }

   private static File getCleanDir(File workDir, String name)
   {
      final File siteDir = new File(workDir, name);
      if (siteDir.exists())
      {
         try
         {
            FileUtils.forceDelete(siteDir);
         }
         catch (IOException e)
         {
            throw Exceptions.pipe(e);
         }
      }
      siteDir.mkdirs();
      return siteDir;
   }
}
