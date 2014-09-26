/*
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven;

import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.osgifier.core.packaging.Repackager;

/**
 * The goal <i>inject-manifest</i> can be used to inject the OSGi manifest (generated with the <i>generate-manifest</i>
 * goal) into packaged JAR. Reason: Lines are not wrapped after 72 bytes, so content of the manifest file is still human
 * readable (and parsable by each parser known to me).
 * 
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 *
 */
@Mojo(name = "inject-manifest", requiresProject = true, defaultPhase = LifecyclePhase.PACKAGE)
public class InjectManifestMojo extends AbstractOsgifierMojo
{
   /**
    * Classifier to identify this projects source artifact.
    */
   @Parameter(property = "maven.source.classifier", defaultValue = "sources")
   private String sourceClassifier;

   @Inject
   private LegacySupport buildContext;

   @Inject
   private Repackager repackager;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final MavenProject project = buildContext.getSession().getCurrentProject();

      final File manifestFile = (File) project.getContextValue("osgifier.manifestFile");
      if (manifestFile != null)
      {
         final Manifest manifest = readManifest(manifestFile);
         repackager.injectManifest(project.getArtifact().getFile(), manifest);
      }

      final File sourceManifestFile = (File) project.getContextValue("osgifier.sourceManifestFile");
      if (sourceManifestFile != null)
      {
         final Artifact sourceArtifact = getAttachedArtifact(project, sourceClassifier);
         if (sourceArtifact != null)
         {
            final Manifest sourceManifest = readManifest(sourceManifestFile);
            repackager.injectManifest(sourceArtifact.getFile(), sourceManifest);
         }
      }
   }

   private static Artifact getAttachedArtifact(MavenProject project, String classifier)
   {
      if (classifier == null)
      {
         return project.getArtifact();
      }
      else
      {
         for (Artifact artifact : project.getAttachedArtifacts())
         {
            if (classifier.equals(artifact.getClassifier()))
            {
               return artifact;
            }
         }
      }
      return null;
   }

   private static Manifest readManifest(File manifestFile)
   {
      final URI uri = URI.createFileURI(manifestFile.getAbsolutePath());
      final Resource resource = new GenericManifestResourceImpl(uri);
      try
      {
         resource.load(null);
      }
      catch (IOException e)
      {
         throw pipe(e);
      }
      return (Manifest) resource.getContents().get(0);
   }

}
