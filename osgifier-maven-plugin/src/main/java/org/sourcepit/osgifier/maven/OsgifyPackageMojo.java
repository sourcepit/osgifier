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

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
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
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.packaging.Repackager;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Mojo(name = "osgify-package", defaultPhase = LifecyclePhase.PACKAGE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class OsgifyPackageMojo extends AbstractOsgifierMojo {
   @Inject
   private Repackager repackager;

   @Inject
   private RepositorySystem repositorySystem;

   @Parameter(defaultValue = "${project}")
   protected MavenProject project;

   @Parameter(defaultValue = "${project.build.directory}")
   private File targetDir;

   @Parameter(defaultValue = "${localRepository}")
   protected ArtifactRepository localRepository;

   @Override
   protected void doExecute() throws MojoExecutionException, MojoFailureException {
      final OsgifierContext context = readContext();

      final File outDir = new File(targetDir, "osgified-dependencies");
      outDir.mkdirs();

      final Artifact sourcesArtifact = findAttachedArtifact(project, "sources", "java-source");
      if (sourcesArtifact != null) {
         final File sourcesJarFile = sourcesArtifact.getFile();
         if (sourcesJarFile != null && sourcesJarFile.exists() && !isEclipseSourceBundle(sourcesJarFile)) {
            final BundleCandidate candidate = context.getBundles().get(0);
            final BundleManifest manifest = createEclipseSourceBundleManifest(candidate);

            repackager.injectManifest(sourcesJarFile, manifest, null);
         }
      }

      repackageNonOsgiJars(outDir, context);

      repackageSourceJars(outDir, context);
   }

   public void repackageNonOsgiJars(File outDir, OsgifierContext context) {
      for (BundleCandidate candidate : context.getBundles()) {
         if (!candidate.isNativeBundle()) {
            final File srcJarFile = candidate.getLocation();
            if (srcJarFile.isFile() && srcJarFile.exists()) {
               final String bundleFileName = candidate.getSymbolicName() + "_" + candidate.getVersion().toFullString()
                  + ".jar";
               final File destJarFile = new File(outDir, bundleFileName);
               final BundleManifest manifest = candidate.getManifest();
               repackager.copyJarAndInjectManifest(srcJarFile, destJarFile, manifest, null);
            }
         }
      }
   }

   public void repackageSourceJars(File outDir, OsgifierContext context) {
      for (BundleCandidate candidate : context.getBundles()) {
         final MavenArtifact mArtifact = candidate.getExtension(MavenArtifact.class);
         if (mArtifact != null) {
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
            if (sourceJarFile != null && sourceJarFile.exists() && !isEclipseSourceBundle(sourceJarFile)) {
               repackageSourcesJar(outDir, candidate, sourceJarFile);
            }
         }
      }
   }

   private File repackageSourcesJar(File outDir, BundleCandidate candidate, File sourceJarFile) {
      BundleManifest manifest = createEclipseSourceBundleManifest(candidate);

      final File destJarFile = new File(outDir, manifest.getBundleSymbolicName().getSymbolicName() + "_"
         + manifest.getBundleVersion().toFullString() + ".jar");

      repackager.copyJarAndInjectManifest(sourceJarFile, destJarFile, manifest, null);

      return destJarFile;
   }

   private boolean isEclipseSourceBundle(File jarFile) {
      Manifest manifest = null;
      try {
         manifest = ManifestUtils.readJarManifest(jarFile);
      }
      catch (IOException e) {
      }
      return manifest != null && manifest.getHeader("Eclipse-SourceBundle") != null;
   }

   private BundleManifest createEclipseSourceBundleManifest(BundleCandidate candidate) {
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

   private Artifact findAttachedArtifact(MavenProject mavenProject, String classifier, String type) {
      for (Artifact artifact : project.getAttachedArtifacts()) {
         if (equals(classifier, artifact.getClassifier()) && equals(type, artifact.getType())) {
            return artifact;
         }
      }
      return null;
   }

   private boolean equals(Object o1, Object o2) {
      if (o1 == null) {
         return o2 == null;
      }
      return o1.equals(o2);
   }

   private OsgifierContext readContext() {
      try {
         final Resource resource = new XMIResourceImpl(URI.createFileURI(getContextFile(Goal.OSGIFY).getAbsolutePath()));
         resource.load(null);
         return (OsgifierContext) resource.getContents().get(0);
      }
      catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   private File getContextFile(Goal goal) {
      return new File(targetDir, goal == Goal.OSGIFY ? "osgify-context.xml" : "osgify-tests-context.xml");
   }
}
