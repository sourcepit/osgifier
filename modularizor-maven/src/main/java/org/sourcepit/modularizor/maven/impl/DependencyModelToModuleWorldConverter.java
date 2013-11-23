/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.maven.impl;

import static org.sourcepit.common.maven.model.util.MavenModelUtils.toArtifactKey;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.modularizor.moduleworlds.AbstractModule;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleReference;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;
import org.sourcepit.modularizor.moduleworlds.ModuleWorldsFactory;

@Named
public class DependencyModelToModuleWorldConverter
{
   public ModuleWorld toModuleWorld(DependencyModel dependencyModel)
   {
      final ModuleWorld moduleWorld = ModuleWorldsFactory.eINSTANCE.createModuleWorld();
      moduleWorld.addExtension(dependencyModel);

      final Map<MavenArtifact, AbstractModule> artifactToModule = new HashMap<MavenArtifact, AbstractModule>();
      for (MavenArtifact artifact : dependencyModel.getArtifacts())
      {
         AbstractModule module = toModule(artifact);
         moduleWorld.getModules().add(module);
         artifactToModule.put(artifact, module);
      }

      for (MavenArtifact artifact : dependencyModel.getArtifacts())
      {
         if ("java-source".equals(artifact.getType()))
         {
            final ArtifactKey jarKey = toArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), "jar", null,
               artifact.getVersion());

            final MavenArtifact jarArtifact = dependencyModel.getArtifact(jarKey);

            final AbstractModule sourceModule = artifactToModule.get(artifact);
            final AbstractModule module = artifactToModule.get(jarArtifact);
            if (module == null)
            {
               moduleWorld.getModules().remove(sourceModule);
            }
            else
            {
               module.setSourceAttachment(artifact.getFile());
            }
         }
      }

      for (DependencyTree dependencyTree : dependencyModel.getDependencyTrees())
      {
         if (dependencyTree.getArtifact() != null && dependencyTree.getArtifact().getFile() != null)
         {
            final AbstractModule module = artifactToModule.get(dependencyTree.getArtifact());

            final ModuleRealm moduleRealm = ModuleWorldsFactory.eINSTANCE.createModuleRealm();
            moduleRealm.setModule(module);

            for (DependencyNode dependencyNode : dependencyTree.getDependencyNodes())
            {
               addModuleReferences(artifactToModule, moduleRealm, dependencyNode);
            }

            moduleWorld.getModuleRealms().add(moduleRealm);
         }
      }
      return moduleWorld;
   }

   private void addModuleReferences(Map<MavenArtifact, AbstractModule> artifactToModule, ModuleRealm moduleRealm,
      DependencyNode dependencyNode)
   {
      if (dependencyNode.isSelected() && dependencyNode.getCycleNode() == null)
      {
         final AbstractModule module = artifactToModule.get(dependencyNode.getArtifact());
         final ModuleReference reference = toModuleReference(module, dependencyNode);
         moduleRealm.getReferencedModules().add(reference);

         for (DependencyNode childNode : dependencyNode.getChildren())
         {
            addModuleReferences(artifactToModule, moduleRealm, childNode);
         }
      }
   }

   private ModuleReference toModuleReference(AbstractModule module, DependencyNode dependencyNode)
   {
      final ModuleReference reference = ModuleWorldsFactory.eINSTANCE.createModuleReference();
      reference.setTargetModule(module);
      reference.setOptional(dependencyNode.isOptional());
      reference.setTransitive(dependencyNode.getParent() != null);

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

   private AbstractModule toModule(MavenArtifact artifact)
   {
      final AssembledModule bundle = ModuleWorldsFactory.eINSTANCE.createAssembledModule();
      bundle.setFile(artifact.getFile());
      bundle.addExtension(EcoreUtil.copy(artifact));
      return bundle;
   }
}
