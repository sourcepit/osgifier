/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import static org.sourcepit.common.utils.io.IO.buffOut;
import static org.sourcepit.common.utils.io.IO.fileOut;
import static org.sourcepit.common.utils.io.IO.write;

import java.io.File;
import java.io.OutputStream;

import javax.inject.Inject;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.resource.BundleManifestResourceImpl;
import org.sourcepit.common.utils.io.Write.ToStream;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.maven.OsgifyModelBuilder.Result;

/**
 * @goal osgify-dependencies
 * @requiresProject true
 * @requiresDependencyResolution compile
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class OsgifyProjectDependenciesMojo extends AbstractGuplexedMojo
{
   /** @parameter default-value="${project.build.directory}" */
   private File targetDir;

   /** @parameter default-value="${project}" */
   private MavenProject project;

   @Inject
   private OsgifyModelBuilder modelBuilder;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final Result result = modelBuilder.build(project.getDependencies());
      writeModel(new File(targetDir, "dependencyModel.xml"), result.dependencyModel);
      writeModel(new File(targetDir, "osgifyModel.xml"), result.osgifyModel);


      EList<BundleCandidate> bundles = result.osgifyModel.getBundles();
      for (BundleCandidate bundle : bundles)
      {
         writeModel(new File(targetDir, bundle.getSymbolicName() + "_" + bundle.getVersion() + ".MF"),
            bundle.getManifest());
      }

   }

   private void writeModel(File file, BundleManifest model)
   {
      final ToStream<EObject> toStream = new ToStream<EObject>()
      {
         @Override
         public void write(OutputStream out, EObject model) throws Exception
         {
            final BundleManifestResourceImpl resource = new BundleManifestResourceImpl();
            resource.getContents().add(model);
            resource.save(out, null);
         }
      };
      write(toStream, buffOut(fileOut(file, true)), model);
   }

   private void writeModel(File file, EObject model)
   {
      final ToStream<EObject> toStream = new ToStream<EObject>()
      {
         @Override
         public void write(OutputStream out, EObject model) throws Exception
         {
            final XMLResourceImpl resource = new XMLResourceImpl();
            resource.getContents().add(model);
            resource.save(out, null);
         }
      };
      write(toStream, buffOut(fileOut(file, true)), model);
   }
}
