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

import static com.google.common.base.Preconditions.checkNotNull;
import static org.sourcepit.common.maven.model.util.MavenModelUtils.toArtifactKey;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.common.maven.model.Scope;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.resolve.VersionRangeResolver;

@Named
public class OsgifierStubModelCreator {
   @Inject
   private VersionRangeResolver versionRangeResolver;

   public OsgifierContext create(DependencyModel dependencyModel) {
      final OsgifierContext osgifyModel = ContextModelFactory.eINSTANCE.createOsgifierContext();
      osgifyModel.addExtension(dependencyModel);

      final Map<MavenArtifact, BundleCandidate> artifactToBundle = new HashMap<MavenArtifact, BundleCandidate>();
      for (MavenArtifact artifact : dependencyModel.getArtifacts()) {
         BundleCandidate bundle = toBundleCandidate(artifact);
         osgifyModel.getBundles().add(bundle);
         artifactToBundle.put(artifact, bundle);
      }

      for (MavenArtifact artifact : dependencyModel.getArtifacts()) {
         if ("java-source".equals(artifact.getType())) {
            final ArtifactKey jarKey = toArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), "jar", null,
               artifact.getVersion());

            final MavenArtifact jarArtifact = dependencyModel.getArtifact(jarKey);

            final BundleCandidate sourceBundle = artifactToBundle.get(artifact);
            final BundleCandidate bundle = artifactToBundle.get(jarArtifact);
            if (bundle == null) {
               osgifyModel.getBundles().remove(sourceBundle);
            }
            else {
               bundle.setSourceBundle(sourceBundle);
            }
         }
      }

      for (DependencyTree dependencyTree : dependencyModel.getDependencyTrees()) {
         if (dependencyTree.getArtifact() != null && dependencyTree.getArtifact().getFile() != null) {
            final BundleCandidate bundle = artifactToBundle.get(dependencyTree.getArtifact());
            checkNotNull(bundle);

            for (DependencyNode dependencyNode : dependencyTree.getDependencyNodes()) {
               addBundleReferences(artifactToBundle, bundle, dependencyNode);
            }
         }
      }

      for (BundleCandidate bundle : osgifyModel.getBundles()) {
         for (BundleReference reference : bundle.getDependencies()) {
            reference.setVersionRange(versionRangeResolver.resolveVersionRange(reference));
            final MavenDependency mavenDependency = reference.getExtension(MavenDependency.class);
            if (mavenDependency != null) {
               reference.setOptional(mavenDependency.isOptional());
               reference.setProvided(mavenDependency.getScope() == Scope.PROVIDED);
            }
         }
      }

      return osgifyModel;
   }

   private void addBundleReferences(Map<MavenArtifact, BundleCandidate> artifactToBundle, BundleCandidate master,
      DependencyNode dependencyNode) {
      if (dependencyNode.isSelected() && dependencyNode.getCycleNode() == null) {
         final MavenArtifact artifact = dependencyNode.getArtifact();
         checkNotNull(artifact);

         final BundleCandidate bundle = artifactToBundle.get(artifact);
         checkNotNull(bundle);

         final BundleReference reference = toBundleReference(bundle, dependencyNode);
         master.getDependencies().add(reference);

         for (DependencyNode childNode : dependencyNode.getChildren()) {
            addBundleReferences(artifactToBundle, master, childNode);
         }
      }
   }

   private BundleReference toBundleReference(BundleCandidate bundle, DependencyNode dependencyNode) {
      final BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setTarget(bundle);

      final MavenDependency dependency = MavenModelFactory.eINSTANCE.createMavenDependency();
      dependency.setGroupId(dependencyNode.getGroupId());
      dependency.setArtifactId(dependencyNode.getArtifactId());
      dependency.setVersionConstraint(dependencyNode.getDeclaredDependency().getVersionConstraint());
      dependency.setType(dependencyNode.getType());
      dependency.setClassifier(dependencyNode.getClassifier());
      dependency.setScope(dependencyNode.getEffectiveScope());
      dependency.setOptional(dependencyNode.isOptional());

      reference.addExtension(dependency);

      return reference;
   }

   private BundleCandidate toBundleCandidate(MavenArtifact artifact) {
      final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setLocation(artifact.getFile());
      bundle.addExtension(EcoreUtil.copy(artifact));
      return bundle;
   }
}
