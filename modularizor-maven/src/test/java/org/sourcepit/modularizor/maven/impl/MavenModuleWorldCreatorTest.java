/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.maven.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;

import java.io.File;

import javax.inject.Inject;

import org.junit.Test;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.common.maven.model.util.MavenModelUtils;
import org.sourcepit.guplex.test.GuplexTest;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyModelFactory;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ModuleRealm;
import org.sourcepit.modularizor.moduleworlds.ModuleWorld;

public class MavenModuleWorldCreatorTest extends GuplexTest
{
   @Inject
   private DependencyModelToModuleWorldConverter creator;

   @Override
   protected boolean isUseIndex()
   {
      return true;
   }

   @Test
   public void testNull()
   {
      try
      {
         creator.toModuleWorld(null);
         fail();
      }
      catch (RuntimeException e)
      {
      }
   }

   @Test
   public void testEmpty()
   {
      DependencyModel dependencyModel = DependencyModelFactory.eINSTANCE.createDependencyModel();
      ModuleWorld moduleWorld = creator.toModuleWorld(dependencyModel);
      assertEquals(0, moduleWorld.getModules().size());
      assertEquals(0, moduleWorld.getModuleRealms().size());
   }

   @Test
   public void testBundleCandidate()
   {
      DependencyModel dependencyModel = DependencyModelFactory.eINSTANCE.createDependencyModel();

      MavenArtifact artifact = MavenModelFactory.eINSTANCE.createMavenArtifact();
      artifact.setGroupId("group");
      artifact.setArtifactId("artifact");
      artifact.setVersion("version");
      artifact.setFile(new File(""));

      final DependencyTree tree = DependencyModelFactory.eINSTANCE.createDependencyTree();
      tree.setArtifact(artifact);

      dependencyModel.getArtifacts().add(artifact);
      dependencyModel.getDependencyTrees().add(tree);

      ModuleWorld world = creator.toModuleWorld(dependencyModel);
      assertEquals(1, world.getModules().size());
      assertEquals(1, world.getModuleRealms().size());

      AssembledModule module = (AssembledModule) world.getModules().get(0);
      assertEquals(new File(""), module.getFile());

      MavenArtifact bundleArtifact = module.getExtension(MavenArtifact.class);
      assertNotNull(bundleArtifact);
      assertNotSame(artifact, bundleArtifact); // Ensure Ecoreutil.copy() is used
      assertEquals("group", bundleArtifact.getGroupId());
      assertEquals("artifact", bundleArtifact.getArtifactId());
      assertEquals("version", bundleArtifact.getVersion());
      assertEquals(new File(""), bundleArtifact.getFile());

      ModuleRealm bundle = world.getModuleRealms().get(0);
      assertEquals(0, bundle.getReferencedModules().size());
   }

   @Test
   public void testBundleReference()
   {
      MavenArtifact artifactA = MavenModelUtils.parseArtifactKey("a:A:jar:1");
      MavenArtifact artifactB = MavenModelUtils.parseArtifactKey("b:B:jar:1");
      MavenArtifact artifactC = MavenModelUtils.parseArtifactKey("c:C:jar:1");
      MavenArtifact artifactD = MavenModelUtils.parseArtifactKey("d:D:jar:1");

      DependencyNode nodeB = toDependencyNode(artifactB);
      DependencyNode nodeC = toDependencyNode(artifactC);
      DependencyNode nodeD = toDependencyNode(artifactD);

      final DependencyTree treeA = DependencyModelFactory.eINSTANCE.createDependencyTree();
      treeA.setArtifact(artifactA);
      treeA.getDependencyNodes().add(nodeB);
      nodeB.getChildren().add(nodeC);
      treeA.getDependencyNodes().add(nodeD);

      final DependencyTree treeB = DependencyModelFactory.eINSTANCE.createDependencyTree();
      treeB.setArtifact(artifactB);
      nodeB = toDependencyNode(artifactB);
      nodeC = toDependencyNode(artifactC);
      treeB.getDependencyNodes().add(nodeB);
      nodeB.getChildren().add(nodeC);

      final DependencyTree treeC = DependencyModelFactory.eINSTANCE.createDependencyTree();
      treeC.setArtifact(artifactC);

      final DependencyTree treeD = DependencyModelFactory.eINSTANCE.createDependencyTree();
      treeD.setArtifact(artifactD);

      DependencyModel dependencyModel = DependencyModelFactory.eINSTANCE.createDependencyModel();
      dependencyModel.getArtifacts().add(artifactA);
      dependencyModel.getArtifacts().add(artifactB);
      dependencyModel.getArtifacts().add(artifactC);
      dependencyModel.getArtifacts().add(artifactD);
      dependencyModel.getDependencyTrees().add(treeA);
      dependencyModel.getDependencyTrees().add(treeB);
      dependencyModel.getDependencyTrees().add(treeC);
      dependencyModel.getDependencyTrees().add(treeD);

      ModuleWorld world = creator.toModuleWorld(dependencyModel);
      assertEquals(4, world.getModules().size());
      assertEquals(4, world.getModuleRealms().size());
   }

   private DependencyNode toDependencyNode(MavenArtifact artifact)
   {
      DependencyNode node = DependencyModelFactory.eINSTANCE.createDependencyNode();
      node.setArtifact(artifact);
      node.setDeclaredDependency(toMavenDependency(artifact));
      return node;
   }

   private MavenDependency toMavenDependency(MavenArtifact artifact)
   {
      MavenDependency dependency = MavenModelFactory.eINSTANCE.createMavenDependency();
      dependency.setGroupId(artifact.getGroupId());
      dependency.setArtifactId(artifact.getArtifactId());
      dependency.setVersionConstraint(artifact.getVersion());
      dependency.setType(artifact.getType());
      dependency.setClassifier(artifact.getClassifier());
      return dependency;
   }
}
