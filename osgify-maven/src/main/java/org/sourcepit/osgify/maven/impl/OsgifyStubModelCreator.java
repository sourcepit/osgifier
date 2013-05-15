/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;

@Named
public class OsgifyStubModelCreator
{
   public OsgifyContext create(DependencyModel dependencyModel)
   {
      final OsgifyContext osgifyModel = ContextModelFactory.eINSTANCE.createOsgifyContext();
      
      final Map<MavenArtifact, BundleCandidate> artifactToBundle = new HashMap<MavenArtifact, BundleCandidate>();
      for (MavenArtifact artifact : dependencyModel.getArtifacts())
      {
         BundleCandidate bundle = toBundleCandidate(artifact);
         osgifyModel.getBundles().add(bundle);
         artifactToBundle.put(artifact, bundle);
      }

      for (DependencyTree dependencyTree : dependencyModel.getDependencyTrees())
      {
         final BundleCandidate bundle = artifactToBundle.get(dependencyTree.getArtifact());
         for (DependencyNode dependencyNode : dependencyTree.getDependencyNodes())
         {
            addBundleReferences(artifactToBundle, bundle, dependencyNode);
         }
      }
      return osgifyModel;
   }

   private void addBundleReferences(Map<MavenArtifact, BundleCandidate> artifactToBundle, BundleCandidate bundle,
      DependencyNode dependencyNode)
   {
      if (dependencyNode.isSelected())
      {
         final BundleReference reference = toBundleReference(artifactToBundle, dependencyNode);
         bundle.getDependencies().add(reference);

         for (DependencyNode childNode : dependencyNode.getChildren())
         {
            addBundleReferences(artifactToBundle, bundle, childNode);
         }
      }
   }

   private BundleReference toBundleReference(Map<MavenArtifact, BundleCandidate> artifactToBundle,
      DependencyNode dependencyNode)
   {
      final BundleReference reference = ContextModelFactory.eINSTANCE.createBundleReference();
      reference.setTarget(artifactToBundle.get(dependencyNode.getArtifact()));

      final MavenDependency dependency = MavenModelFactory.eINSTANCE.createMavenDependency();
      dependency.setGroupId(dependencyNode.getGroupId());
      dependency.setArtifactId(dependencyNode.getArtifactId());
      dependency.setVersionConstraint(dependencyNode.getEffectiveVersionConstraint());
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
