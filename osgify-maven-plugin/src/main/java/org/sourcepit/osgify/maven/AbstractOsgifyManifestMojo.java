/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.osgify.core.bundle.BundleManifestAppender;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.maven.context.OsgifyContextBuilder;

public abstract class AbstractOsgifyManifestMojo extends AbstractGuplexedMojo
{
   private final Logger LOGGER = LoggerFactory.getLogger(AbstractOsgifyManifestMojo.class);

   @Inject
   private OsgifyContextBuilder builder;

   @Inject
   private BundleManifestAppender manifestAppender;

   /** @parameter default-value="${localRepository}" */
   protected ArtifactRepository localRepository;

   /** @parameter default-value="${project.build.directory}" */
   private File targetDir;

   /** @parameter default-value="${project}" */
   protected MavenProject project;

   protected void doExecute(Goal goal)
   {
      // TODO
      // Typo "Unambiguous referende"

      // ignore dependencies of native bundles !!! e.g.

      // Check if contents of unambiguous package references differs

      // make "main exporter" configurable for unambiguous package references. Solution for:
      // "[WARNING] Unambiguous reference to package org.hamcrest. Exporters: org.junit_4.9, org.hamcrest.core_1.1"

      // EE is strongest. Solution for:
      // "[WARNING] Unambiguous reference to package javax.annotation. Exporters: javax.annotation.jsr250.api_1.0, Execution Environment or Vendor (J2SE-1.5)"

      // support wrapping of multi jars into one bundle to solve:
      // "[WARNING] Unambiguous references to package org.sonatype.inject. Exporters: org.sonatype.sisu.inject.guice.bean.locators_2.3.0, org.sonatype.sisu.inject.guice.bean.binders_2.3.0 "
      // or
      // support merge of unambiguous references in a separate bundle (may cause cyclic imports)

      LOGGER.info("Building osgifier context");
      final OsgifyContext context = builder.build(project, goal, localRepository);
      manifestAppender.append(context);

      final File contextFile = getContextFile(goal);
      LOGGER.info("Writing osgifier context to " + contextFile.getAbsolutePath());
      writeContext(contextFile, context);

      final BundleManifest manifest = context.getBundles().get(0).getManifest();

      project.getProperties().setProperty("jar.useDefaultManifestFile", String.valueOf(true));
      File manifestFile = getManifestFile();
      LOGGER.info("Writing projects MANIFEST.MF to " + manifestFile.getAbsolutePath());
      writeManifest(manifest, manifestFile);
   }


   private void writeContext(File file, final OsgifyContext context)
   {
      XMLResourceImpl resource = new XMLResourceImpl();
      resource.getContents().add(context);

      OutputStream out = null;
      try
      {
         out = newOutStream(file);
         resource.save(out, null);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      finally
      {
         IOUtils.closeQuietly(out);
      }
   }

   protected abstract File getManifestFile();

   private void writeManifest(BundleManifest manifest, File manifestFile)
   {
      OutputStream out = null;
      try
      {
         out = newOutStream(manifestFile);
         writeManifest(manifest, out);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      finally
      {
         IOUtils.closeQuietly(out);
      }
   }

   private void writeManifest(BundleManifest manifest, OutputStream out) throws IOException
   {
      Resource manifestResource = new GenericManifestResourceImpl();
      manifestResource.getContents().add(EcoreUtil.copy(manifest));
      manifestResource.save(out, null);
   }


   private BufferedOutputStream newOutStream(File file) throws IOException
   {
      if (file.exists())
      {
         file.delete();
      }
      file.getParentFile().mkdirs();
      file.createNewFile();
      return new BufferedOutputStream(new FileOutputStream(file));
   }

   private File getContextFile(Goal goal)
   {
      return new File(targetDir, goal == Goal.OSGIFY ? "osgify-context.xml" : "osgify-tests-context.xml");
   }
}
