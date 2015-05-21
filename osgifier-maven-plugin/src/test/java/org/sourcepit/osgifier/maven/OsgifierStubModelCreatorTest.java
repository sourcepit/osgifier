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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.File;

import javax.inject.Inject;

import org.eclipse.sisu.launch.InjectedTest;
import org.junit.Test;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.model.MavenDependency;
import org.sourcepit.common.maven.model.MavenModelFactory;
import org.sourcepit.common.maven.model.util.MavenModelUtils;
import org.sourcepit.maven.dependency.model.DependencyModel;
import org.sourcepit.maven.dependency.model.DependencyModelFactory;
import org.sourcepit.maven.dependency.model.DependencyNode;
import org.sourcepit.maven.dependency.model.DependencyTree;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;

public class OsgifierStubModelCreatorTest extends InjectedTest {
   @Inject
   private OsgifierStubModelCreator stubModelCreator;

   @Test
   public void testNull() {
      try {
         stubModelCreator.create(null);
         fail();
      }
      catch (RuntimeException e) {
      }
   }

   @Test
   public void testEmpty() {
      DependencyModel dependencyModel = DependencyModelFactory.eINSTANCE.createDependencyModel();
      OsgifierContext stubModel = stubModelCreator.create(dependencyModel);
      assertEquals(0, stubModel.getBundles().size());
   }

   @Test
   public void testBundleCandidate() {
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

      OsgifierContext stubModel = stubModelCreator.create(dependencyModel);
      assertEquals(1, stubModel.getBundles().size());

      BundleCandidate bundle = stubModel.getBundles().get(0);
      assertEquals(0, bundle.getDependencies().size());
      assertEquals(new File(""), bundle.getLocation());

      // assert following properties are not set for stub model
      assertFalse(bundle.isNativeBundle());
      assertNull(bundle.getContent());
      assertNull(bundle.getManifest());
      assertNull(bundle.getSymbolicName());
      assertNull(bundle.getVersion());

      MavenArtifact bundleArtifact = bundle.getExtension(MavenArtifact.class);
      assertNotNull(bundleArtifact);
      assertNotSame(artifact, bundleArtifact); // Ensure Ecoreutil.copy() is used
      assertEquals("group", bundleArtifact.getGroupId());
      assertEquals("artifact", bundleArtifact.getArtifactId());
      assertEquals("version", bundleArtifact.getVersion());
      assertEquals(new File(""), bundleArtifact.getFile());
   }

   @Test
   public void testBundleReference() {
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

      OsgifierContext stubModel = stubModelCreator.create(dependencyModel);
      assertEquals(4, stubModel.getBundles().size());
   }

   private DependencyNode toDependencyNode(MavenArtifact artifact) {
      DependencyNode node = DependencyModelFactory.eINSTANCE.createDependencyNode();
      node.setArtifact(artifact);
      node.setDeclaredDependency(toMavenDependency(artifact));
      return node;
   }

   private MavenDependency toMavenDependency(MavenArtifact artifact) {
      MavenDependency dependency = MavenModelFactory.eINSTANCE.createMavenDependency();
      dependency.setGroupId(artifact.getGroupId());
      dependency.setArtifactId(artifact.getArtifactId());
      dependency.setVersionConstraint(artifact.getVersion());
      dependency.setType(artifact.getType());
      dependency.setClassifier(artifact.getClassifier());
      return dependency;
   }
}
