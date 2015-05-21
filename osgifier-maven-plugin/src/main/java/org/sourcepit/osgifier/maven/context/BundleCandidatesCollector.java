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

package org.sourcepit.osgifier.maven.context;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.util.ManifestUtils;
import org.sourcepit.common.maven.artifact.MavenArtifactUtils;
import org.sourcepit.common.maven.core.MavenProjectUtils;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class BundleCandidatesCollector implements MavenDependencyWalker.Handler {
   private final Map<String, BundleCandidate> mvnIdToBundleNode = new LinkedHashMap<String, BundleCandidate>();
   private final Map<BundleCandidate, File> bundleNodeToSourceJar = new LinkedHashMap<BundleCandidate, File>();

   private final boolean resolveDependenciesOfNativeBundles;

   public BundleCandidatesCollector() {
      this(false);
   }

   public BundleCandidatesCollector(boolean resolveDependenciesOfNativeBundles) {
      this.resolveDependenciesOfNativeBundles = resolveDependenciesOfNativeBundles;
   }

   public List<BundleCandidate> getBundleCandidates() {
      return new ArrayList<BundleCandidate>(mvnIdToBundleNode.values());
   }

   public Map<BundleCandidate, File> getBundleNodeToSourceJarMap() {
      return bundleNodeToSourceJar;
   }

   public boolean visitNode(Artifact artifact, MavenProject project) {
      final String id = artifact.getId();
      if (!mvnIdToBundleNode.containsKey(id)) {
         BundleCandidate bundleCandidate = newBundleCandidate(artifact, project);
         mvnIdToBundleNode.put(id, bundleCandidate);

         // no need to visit dependencies of native bundles
         final BundleManifest manifest = lookupBundleManifest(artifact, project);
         bundleCandidate.setManifest(manifest);

         if (isNativeBundle(artifact, project, bundleCandidate)) {
            bundleCandidate.setNativeBundle(true);
            return resolveDependenciesOfNativeBundles;
         }

         return true;
      }
      return false;
   }

   public void visitSourceNode(Artifact artifact, MavenProject project, Artifact sourceArtifact) {
      final BundleCandidate bundleCandidate = mvnIdToBundleNode.get(artifact.getId());
      bundleNodeToSourceJar.put(bundleCandidate, sourceArtifact.getFile());
   }

   protected boolean isNativeBundle(Artifact artifact, MavenProject project, BundleCandidate bundleCandidate) {
      return bundleCandidate.getManifest() != null;
   }

   protected BundleCandidate newBundleCandidate(Artifact artifact, MavenProject project) {
      final BundleCandidate node = ContextModelFactory.eINSTANCE.createBundleCandidate();
      if (project == null) {
         MavenArtifact mArtifact = MavenArtifactUtils.toMavenArtifact(artifact);
         node.setLocation(mArtifact.getFile());
         node.addExtension(mArtifact);
      }
      else {
         org.sourcepit.common.maven.model.MavenProject mProject = MavenProjectUtils.toMavenProject(project);
         node.setLocation(artifact.getFile());
         node.addExtension(mProject);
      }
      return node;
   }

   private BundleManifest lookupBundleManifest(Artifact artifact, MavenProject project) {
      if (project == null) {
         Manifest manifest = null;
         try {
            manifest = ManifestUtils.readJarManifest(artifact.getFile());
         }
         catch (Exception e) {
         }
         return (BundleManifest) (manifest instanceof BundleManifest ? manifest : null);
      }
      else {
         return null;
      }
   }

   public void handleDependency(Artifact fromArtifact, Artifact toArtifact) {
      BundleCandidate fromNode = mvnIdToBundleNode.get(fromArtifact.getId());
      BundleCandidate toNode = mvnIdToBundleNode.get(toArtifact.getId());
      fromNode.getDependencies().add(newBundleReference(toNode, toArtifact));
   }

   private BundleReference newBundleReference(BundleCandidate bundleNode, Artifact mappedArtifact) {
      final BundleReference bundleReference = ContextModelFactory.eINSTANCE.createBundleReference();
      bundleReference.addExtension(MavenArtifactUtils.toMavenDependecy(mappedArtifact));

      bundleReference.setTarget(bundleNode);
      bundleReference.setOptional(mappedArtifact.isOptional());
      bundleReference.setProvided(Artifact.SCOPE_PROVIDED.equals(mappedArtifact.getScope()));

      return bundleReference;
   }
}