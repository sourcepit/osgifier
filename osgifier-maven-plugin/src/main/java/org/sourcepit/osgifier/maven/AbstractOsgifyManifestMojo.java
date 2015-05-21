/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.maven;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.common.utils.props.AbstractPropertiesSource;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

public abstract class AbstractOsgifyManifestMojo extends AbstractOsgifierMojo {
   private static final Logger LOGGER = LoggerFactory.getLogger(AbstractOsgifyManifestMojo.class);

   @Inject
   private OsgifierModelBuilder modelBuilder;

   @Inject
   private LegacySupport buildContext;

   @Parameter(defaultValue = "${localRepository}")
   protected ArtifactRepository localRepository;

   @Parameter(defaultValue = "${project.build.directory}")
   private File targetDir;

   @Parameter(defaultValue = "${project}")
   protected MavenProject project;

   protected void doExecute(Goal goal) {
      if (goal == Goal.OSGIFY_TESTS) {
         throw new UnsupportedOperationException("Bundling test artifacts is currently not supported.");
      }
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


      final PropertiesSource options = createOptions();

      final Date startTime = buildContext.getSession().getStartTime();

      final OsgifierContext context = modelBuilder.build(new DefaultOsgifierContextInflatorFilter(), options, project,
         startTime);

      final File contextFile = getContextFile(goal);
      LOGGER.info("Writing osgifier context to " + contextFile.getAbsolutePath());
      writeContext(contextFile, context);

      final BundleManifest manifest = getProjectBundle(context).getManifest();

      project.getProperties().setProperty("jar.useDefaultManifestFile", String.valueOf(true));
      File manifestFile = getManifestFile();
      LOGGER.info("Writing projects MANIFEST.MF to " + manifestFile.getAbsolutePath());
      writeManifest(manifest, manifestFile);
   }

   private BundleCandidate getProjectBundle(final OsgifierContext context) {
      final String projectPath = project.getBasedir().getAbsolutePath();
      for (BundleCandidate bundle : context.getBundles()) {
         if (bundle.getLocation().getAbsolutePath().startsWith(projectPath)) {
            return bundle;
         }
      }
      return null;
   }

   private PropertiesSource createOptions() {
      final PropertiesSource options = new AbstractPropertiesSource() {
         @Override
         public String get(String key) {
            return project.getProperties().getProperty(key);
         }
      };
      return options;
   }

   private void writeContext(File file, final OsgifierContext context) {
      XMLResourceImpl resource = new XMLResourceImpl();
      resource.getContents().add(context);

      OutputStream out = null;
      try {
         out = newOutStream(file);
         resource.save(out, null);
      }
      catch (IOException e) {
         throw new IllegalStateException(e);
      }
      finally {
         IOUtils.closeQuietly(out);
      }
   }

   protected abstract File getManifestFile();

   private void writeManifest(BundleManifest manifest, File manifestFile) {
      OutputStream out = null;
      try {
         out = newOutStream(manifestFile);
         writeManifest(manifest, out);
      }
      catch (IOException e) {
         throw new IllegalStateException(e);
      }
      finally {
         IOUtils.closeQuietly(out);
      }
   }

   private void writeManifest(BundleManifest manifest, OutputStream out) throws IOException {
      Resource manifestResource = new GenericManifestResourceImpl();
      manifestResource.getContents().add(EcoreUtil.copy(manifest));
      manifestResource.save(out, null);
   }


   private BufferedOutputStream newOutStream(File file) throws IOException {
      if (file.exists()) {
         file.delete();
      }
      file.getParentFile().mkdirs();
      file.createNewFile();
      return new BufferedOutputStream(new FileOutputStream(file));
   }

   private File getContextFile(Goal goal) {
      return new File(targetDir, goal == Goal.OSGIFY ? "osgify-context.xml" : "osgify-tests-context.xml");
   }
}
