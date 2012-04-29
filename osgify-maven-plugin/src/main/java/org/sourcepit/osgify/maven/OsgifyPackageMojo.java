/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.repository.RepositorySystem;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.util.ManifestUtils;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.packaging.Repackager;

/**
 * @requiresDependencyResolution compile
 * @goal osgify-package
 * @phase package
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class OsgifyPackageMojo extends AbstractGuplexedMojo
{
   @Inject
   private Repackager repackager;

   @Inject
   private RepositorySystem repositorySystem;

   /** @parameter default-value="${project}" */
   protected MavenProject project;

   /** @parameter default-value="${project.build.directory}" */
   private File targetDir;

   /** @parameter default-value="${localRepository}" */
   protected ArtifactRepository localRepository;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException
   {
      final OsgifyContext context = readContext();

      final File outDir = new File(targetDir, "osgified-dependencies");
      outDir.mkdirs();

      final Artifact sourcesArtifact = findAttachedArtifact(project, "sources", "java-source");
      if (sourcesArtifact != null)
      {
         final File sourcesJarFile = sourcesArtifact.getFile();
         if (sourcesJarFile != null && sourcesJarFile.exists() && !isEclipseSourceBundle(sourcesJarFile))
         {
            final BundleCandidate candidate = context.getBundles().get(0);
            final BundleManifest manifest = createEclipseSourceBundleManifest(candidate);

            repackager.injectManifest(sourcesJarFile, manifest);
         }
      }

      repackageNonOsgiJars(outDir, context);

      repackageSourceJars(outDir, context);
   }

   public void repackageNonOsgiJars(File outDir, OsgifyContext context)
   {
      for (BundleCandidate candidate : context.getBundles())
      {
         if (!candidate.isNativeBundle())
         {
            final File srcJarFile = candidate.getLocation();
            if (srcJarFile.isFile())
            {
               final String bundleFileName = candidate.getSymbolicName() + "_" + candidate.getVersion().toFullString()
                  + ".jar";
               final File destJarFile = new File(outDir, bundleFileName);
               final BundleManifest manifest = candidate.getManifest();
               repackager.copyJarAndInjectManifest(srcJarFile, destJarFile, manifest);
            }
         }
      }
   }

   public void repackageSourceJars(File outDir, OsgifyContext context)
   {
      for (BundleCandidate candidate : context.getBundles())
      {
         final MavenArtifact mArtifact = candidate.getExtension(MavenArtifact.class);
         if (mArtifact != null)
         {
            Artifact sourcesArtifact = repositorySystem.createArtifactWithClassifier(mArtifact.getGroupId(),
               mArtifact.getArtifactId(), mArtifact.getVersion(), "java-source", "sources");

            final ArtifactResolutionRequest request = new ArtifactResolutionRequest();
            request.setArtifact(sourcesArtifact);
            request.setResolveRoot(true);
            request.setResolveTransitively(false);
            request.setRemoteRepositories(project.getRemoteArtifactRepositories());
            request.setLocalRepository(localRepository);

            repositorySystem.resolve(request);

            final File sourceJarFile = sourcesArtifact.getFile();
            if (sourceJarFile != null && sourceJarFile.exists() && !isEclipseSourceBundle(sourceJarFile))
            {
               repackageSourcesJar(outDir, candidate, sourceJarFile);
            }
         }
      }
   }

   private File repackageSourcesJar(File outDir, BundleCandidate candidate, File sourceJarFile)
   {
      BundleManifest manifest = createEclipseSourceBundleManifest(candidate);

      final File destJarFile = new File(outDir, manifest.getBundleSymbolicName().getSymbolicName() + "_"
         + manifest.getBundleVersion().toFullString() + ".jar");

      repackager.copyJarAndInjectManifest(sourceJarFile, destJarFile, manifest);

      return destJarFile;
   }

   private boolean isEclipseSourceBundle(File jarFile)
   {
      Manifest manifest = null;
      try
      {
         manifest = ManifestUtils.readJarManifest(jarFile);
      }
      catch (IOException e)
      {
      }
      return manifest != null && manifest.getHeader("Eclipse-SourceBundle") != null;
   }

   private BundleManifest createEclipseSourceBundleManifest(BundleCandidate candidate)
   {
      BundleManifest manifest = BundleManifestFactory.eINSTANCE.createBundleManifest();
      manifest.setBundleSymbolicName(candidate.getSymbolicName() + ".source"); // without 's' in eclipse
      manifest.setBundleVersion(candidate.getVersion());

      final StringBuilder sb = new StringBuilder();
      sb.append(candidate.getSymbolicName());
      sb.append(";version=\"");
      sb.append(candidate.getVersion());
      sb.append("\";roots:=\".\"");

      manifest.setHeader("Eclipse-SourceBundle", sb.toString());
      return manifest;
   }

   private Artifact findAttachedArtifact(MavenProject mavenProject, String classifier, String type)
   {
      for (Artifact artifact : project.getAttachedArtifacts())
      {
         if (equals(classifier, artifact.getClassifier()) && equals(type, artifact.getType()))
         {
            return artifact;
         }
      }
      return null;
   }

   private boolean equals(Object o1, Object o2)
   {
      if (o1 == null)
      {
         return o2 == null;
      }
      return o1.equals(o2);
   }

   private OsgifyContext readContext()
   {
      try
      {
         final Resource resource = new XMIResourceImpl(URI.createFileURI(getContextFile(Goal.OSGIFY).getAbsolutePath()));
         resource.load(null);
         return (OsgifyContext) resource.getContents().get(0);
      }
      catch (Exception e)
      {
         throw new IllegalStateException(e);
      }
   }

   private File getContextFile(Goal goal)
   {
      return new File(targetDir, goal == Goal.OSGIFY ? "osgify-context.xml" : "osgify-tests-context.xml");
   }
}
