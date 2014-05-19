/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

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
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.resolve.VersionRangeResolver;

@Named
public class OsgifyStubModelCreator
{
   @Inject
   private VersionRangeResolver versionRangeResolver;
   
   public OsgifyContext create(DependencyModel dependencyModel)
   {
      final OsgifyContext osgifyModel = ContextModelFactory.eINSTANCE.createOsgifyContext();
      osgifyModel.addExtension(dependencyModel);

      final Map<MavenArtifact, BundleCandidate> artifactToBundle = new HashMap<MavenArtifact, BundleCandidate>();
      for (MavenArtifact artifact : dependencyModel.getArtifacts())
      {
         BundleCandidate bundle = toBundleCandidate(artifact);
         osgifyModel.getBundles().add(bundle);
         artifactToBundle.put(artifact, bundle);
      }

      for (MavenArtifact artifact : dependencyModel.getArtifacts())
      {
         if ("java-source".equals(artifact.getType()))
         {
            final ArtifactKey jarKey = toArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), "jar", null,
               artifact.getVersion());

            final MavenArtifact jarArtifact = dependencyModel.getArtifact(jarKey);

            final BundleCandidate sourceBundle = artifactToBundle.get(artifact);
            final BundleCandidate bundle = artifactToBundle.get(jarArtifact);
            if (bundle == null)
            {
               osgifyModel.getBundles().remove(sourceBundle);
            }
            else
            {
               bundle.setSourceBundle(sourceBundle);
            }
         }
      }

      for (DependencyTree dependencyTree : dependencyModel.getDependencyTrees())
      {
         if (dependencyTree.getArtifact() != null && dependencyTree.getArtifact().getFile() != null)
         {
            final BundleCandidate bundle = artifactToBundle.get(dependencyTree.getArtifact());
            checkNotNull(bundle);
            
            for (DependencyNode dependencyNode : dependencyTree.getDependencyNodes())
            {
               addBundleReferences(artifactToBundle, bundle, dependencyNode);
            }
         }
      }
      
      for (BundleCandidate bundle : osgifyModel.getBundles())
      {
         for (BundleReference reference : bundle.getDependencies())
         {
            reference.setVersionRange(versionRangeResolver.resolveVersionRange(reference));
            final MavenDependency mavenDependency = reference.getExtension(MavenDependency.class);
            if (mavenDependency != null)
            {
               reference.setOptional(mavenDependency.isOptional());
               reference.setProvided(mavenDependency.getScope() == Scope.PROVIDED);
            }
         }
      }
      
      return osgifyModel;
   }

   private void addBundleReferences(Map<MavenArtifact, BundleCandidate> artifactToBundle, BundleCandidate master,
      DependencyNode dependencyNode)
   {
      if (dependencyNode.isSelected() && dependencyNode.getCycleNode() == null)
      {
         final MavenArtifact artifact = dependencyNode.getArtifact();
         checkNotNull(artifact);
         
         final BundleCandidate bundle = artifactToBundle.get(artifact);
         checkNotNull(bundle);
         
         final BundleReference reference = toBundleReference(bundle, dependencyNode);
         master.getDependencies().add(reference);

         for (DependencyNode childNode : dependencyNode.getChildren())
         {
            addBundleReferences(artifactToBundle, master, childNode);
         }
      }
   }

   private BundleReference toBundleReference(BundleCandidate bundle, DependencyNode dependencyNode)
   {
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

   private BundleCandidate toBundleCandidate(MavenArtifact artifact)
   {
      final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setLocation(artifact.getFile());
      bundle.addExtension(EcoreUtil.copy(artifact));
      return bundle;
   }
}
