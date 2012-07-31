/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.p2;

import static org.sourcepit.common.utils.io.IOResources.buffOut;
import static org.sourcepit.common.utils.io.IOResources.fileOut;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.sourcepit.common.utils.io.IOOperation;
import org.sourcepit.common.utils.lang.Exceptions;
import org.sourcepit.osgify.core.bundle.BundleManifestAppender;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.packaging.Repackager;
import org.sourcepit.osgify.maven.context.MavenBundleProjectClassDirectoryResolver;
import org.sourcepit.osgify.maven.context.OsgifyModelBuilder;
import org.sourcepit.osgify.p2.P2Publisher;

@Named
public class P2UpdateSiteGenerator
{
   @Inject
   private OsgifyModelBuilder modelBuilder;

   @Inject
   private BundleManifestAppender manifestAppender;

   @Inject
   private Repackager repackager;

   @Inject
   private P2Publisher p2Publisher;

   public void generateUpdateSite(File workDir, Artifact artifact, List<ArtifactRepository> remoteArtifactRepositories,
      ArtifactRepository localRepository, String repositoryName, boolean compressRepository,
      int forkedProcessTimeoutInSeconds)
   {
      OsgifyModelBuilder.Request request = modelBuilder.createRequest(artifact);
      request.setResolveDependenciesOfNativeBundles(true);
      request.setVirtualArtifact(false);
      request.setFatBundle(false);
      request.setLocalRepository(localRepository);
      request.setScope(Artifact.SCOPE_COMPILE);
      request.getRemoteRepositories().addAll(remoteArtifactRepositories);
      request.setAppendBundleContents(true);
      request.setBundleProjectClassDirectoryResolver(new MavenBundleProjectClassDirectoryResolver(
         Artifact.SCOPE_COMPILE));

      OsgifyContext model = modelBuilder.build(request);
      manifestAppender.append(model);

      final List<File> bundles = copyJarsAndInjectManifests(model, getCleanDir(workDir, "bundles"));

      final File repoDir = getCleanDir(workDir, "p2-udate-site");

      p2Publisher.publishBundles(repoDir, repositoryName, bundles, compressRepository, forkedProcessTimeoutInSeconds);

      final File categoryFile = new File(workDir, "category.xml");
      writeCategoryXml(categoryFile);

      p2Publisher.publishCategories(repoDir, categoryFile, compressRepository, forkedProcessTimeoutInSeconds);
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

   private List<File> copyJarsAndInjectManifests(OsgifyContext model, final File workDir)
   {
      List<File> bundleJars = new ArrayList<File>();
      for (BundleCandidate bundle : model.getBundles())
      {
         if (bundle.isNativeBundle())
         {
            bundleJars.add(bundle.getLocation());
         }
         else
         {
            final File bundleJar = new File(workDir, bundle.getSymbolicName() + "_"
               + bundle.getVersion().toFullString() + ".jar");
            repackager.copyJarAndInjectManifest(bundle.getLocation(), bundleJar, bundle.getManifest());
            bundleJars.add(bundleJar);
         }
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
