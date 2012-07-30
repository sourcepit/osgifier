/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.sourcepit.common.utils.io.IOResources.buffOut;
import static org.sourcepit.common.utils.io.IOResources.fileOut;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.sisu.equinox.launching.internal.P2ApplicationLauncher;
import org.sourcepit.common.utils.io.IOOperation;
import org.sourcepit.common.utils.lang.Exceptions;
import org.sourcepit.osgify.core.bundle.BundleManifestAppender;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.packaging.Repackager;
import org.sourcepit.osgify.maven.context.MavenBundleProjectClassDirectoryResolver;
import org.sourcepit.osgify.maven.context.OsgifyModelBuilder;

/**
 * @phase package
 * @goal generate-p2-update-site
 * @requiresProject true
 * @requiresDependencyResolution compile
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class GenerateP2UpdateSiteMojo extends AbstractGuplexedMojo
{
   /** @parameter default-value="${localRepository}" */
   protected ArtifactRepository localRepository;

   /** @parameter default-value="${project.build.directory}" */
   private File targetDir;

   /** @parameter default-value="${project}" */
   protected MavenProject project;

   /**
    * Metadata repository name
    * 
    * @parameter default-value="${project.name}"
    * @required
    */
   protected String metadataRepositoryName;

   /**
    * Artifact repository name
    * 
    * @parameter default-value="${project.name} Artifacts"
    * @required
    */
   protected String artifactRepositoryName;

   /**
    * Kill the forked test process after a certain number of seconds. If set to 0, wait forever for
    * the process, never timing out.
    * 
    * @parameter expression="${p2.timeout}"
    */
   private int forkedProcessTimeoutInSeconds;

   /**
    * Arbitrary JVM options to set on the command line.
    * 
    * @parameter
    */
   private String argLine;

   /**
    * @parameter default-value="true"
    */
   protected boolean generateP2Metadata;

   /**
    * @parameter default-value="true"
    */
   private boolean compressRepository;

   @Inject
   private OsgifyModelBuilder modelBuilder;

   @Inject
   private BundleManifestAppender manifestAppender;

   @Inject
   private Repackager repackager;

   @Inject
   private PlexusContainer plexus;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      OsgifyModelBuilder.Request request = modelBuilder.createRequest(project.getArtifact());
      request.setResolveDependenciesOfNativeBundles(true);
      request.setVirtualArtifact(false);
      request.setFatBundle(false);
      request.setLocalRepository(localRepository);
      request.setScope(Artifact.SCOPE_COMPILE);
      request.getRemoteRepositories().addAll(project.getRemoteArtifactRepositories());
      request.setAppendBundleContents(true);
      request.setBundleProjectClassDirectoryResolver(new MavenBundleProjectClassDirectoryResolver(
         Artifact.SCOPE_COMPILE));

      OsgifyContext model = modelBuilder.build(request);

      manifestAppender.append(model);

      final File siteDir = getCleanSiteDir();
      copyJarsAndInjectManifests(model, siteDir);

      writeSiteXml(siteDir);

      generateMetadata(siteDir);

      generateCategory(siteDir);
   }

   private final <T> T gLookup(Class<T> clazz)
   {
      try
      {
         return plexus.lookup(clazz);
      }
      catch (ComponentLookupException e)
      {
         throw Exceptions.pipe(e);
      }
   }

   private void generateMetadata(File siteDir)
   {
      final P2ApplicationLauncher launcher = gLookup(P2ApplicationLauncher.class);
      launcher.setWorkingDirectory(project.getBasedir());
      launcher.setApplicationName("org.eclipse.equinox.p2.publisher.FeaturesAndBundlesPublisher");

      try
      {
         addArguments(launcher, siteDir);
      }
      catch (MalformedURLException e)
      {
         throw Exceptions.pipe(e);
      }
      catch (IOException e)
      {
         throw Exceptions.pipe(e);
      }

      if (argLine != null && argLine.trim().length() > 0)
      {
         // TODO does this really do anything???
         launcher.addArguments("-vmargs", argLine);
      }

      int result = launcher.execute(forkedProcessTimeoutInSeconds);
      if (result != 0)
      {
         throw Exceptions.pipe(new MojoFailureException("P2 publisher return code was " + result));
      }
   }

   private void generateCategory(File siteDir)
   {
      final P2ApplicationLauncher launcher = gLookup(P2ApplicationLauncher.class);
      launcher.setWorkingDirectory(project.getBasedir());
      launcher.setApplicationName("org.eclipse.equinox.p2.publisher.CategoryPublisher");

      try
      {

         // -console -consolelog -application org.eclipse.equinox.p2.publisher.CategoryPublisher
         // -metadataRepository file:/<repo location>/repository
         // -categoryDefinition file:/home/irbull/workspaces/p2/mail/category.xml
         // -categoryQualifier
         // -compress
         launcher.addArguments("-metadataRepository", siteDir.toURL().toExternalForm(), //
            "-categoryDefinition", siteDir.toURL().toExternalForm() + "/site.xml", //
            "-categoryQualifier");
         if (compressRepository)
         {
            launcher.addArguments(new String[] { "-compress" });
         }
      }
      catch (MalformedURLException e)
      {
         throw Exceptions.pipe(e);
      }
      catch (IOException e)
      {
         throw Exceptions.pipe(e);
      }

      if (argLine != null && argLine.trim().length() > 0)
      {
         // TODO does this really do anything???
         launcher.addArguments("-vmargs", argLine);
      }

      int result = launcher.execute(forkedProcessTimeoutInSeconds);
      if (result != 0)
      {
         throw Exceptions.pipe(new MojoFailureException("P2 publisher return code was " + result));
      }
   }


   protected void addArguments(P2ApplicationLauncher launcher, File siteDir) throws IOException, MalformedURLException
   {
      launcher.addArguments("-source", siteDir.getCanonicalPath(), //
         "-metadataRepository", siteDir.toURL().toExternalForm(), //
         "-metadataRepositoryName", metadataRepositoryName, //
         "-artifactRepository", siteDir.toURL().toExternalForm(), //
         "-artifactRepositoryName", artifactRepositoryName, "-publishArtifacts");
      if (compressRepository)
      {
         launcher.addArguments(new String[] { "-compress" });
      }
   }

   private void writeSiteXml(File siteDir)
   {
      final StringBuilder sb = new StringBuilder();
      sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
      sb.append("<site>\n");
      sb.append("   <category-def name=\"all\" label=\"All Bundles\"/>\n");
      sb.append("   <iu>");
      sb.append("      <category name=\"all\"/>\n");
      sb.append("      <query><expression type=\"match\">providedCapabilities.exists(p | p.namespace == 'osgi.bundle')</expression></query>\n");
      sb.append("   </iu>\n");
      sb.append("</site>\n");

      new IOOperation<OutputStream>(buffOut(fileOut(new File(siteDir, "site.xml"), true)))
      {
         @Override
         protected void run(OutputStream output) throws IOException
         {
            IOUtils.copy(new ByteArrayInputStream(sb.toString().getBytes("UTF-8")), output);
         }
      }.run();
   }

   private void copyJarsAndInjectManifests(OsgifyContext model, final File siteDir)
   {
      List<File> bundleJars = new ArrayList<File>();

      EList<BundleCandidate> bundles = model.getBundles();
      for (BundleCandidate bundle : bundles)
      {
         final File bundleJar = new File(siteDir, "plugins/" + bundle.getSymbolicName() + "_"
            + bundle.getVersion().toFullString() + ".jar");
         if (bundle.isNativeBundle())
         {
            try
            {
               FileUtils.copyFile(bundle.getLocation(), bundleJar);
            }
            catch (IOException e)
            {
               throw Exceptions.pipe(e);
            }
         }
         else
         {
            repackager.copyJarAndInjectManifest(bundle.getLocation(), bundleJar, bundle.getManifest());
         }
         bundleJars.add(bundleJar);
      }
   }

   private File getCleanSiteDir()
   {
      final File siteDir = new File(targetDir, "site");
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
