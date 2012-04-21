/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.sourcepit.common.maven.model.util.MavenModelUtils;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class BundleCandidatesCollector implements MavenDependencyWalker.Handler
{
   private final Map<String, BundleCandidate> mvnIdToBundleNode = new LinkedHashMap<String, BundleCandidate>();

   public List<BundleCandidate> getBundleCandidates()
   {
      return new ArrayList<BundleCandidate>(mvnIdToBundleNode.values());
   }

   public boolean visitNode(Artifact artifact, MavenProject project)
   {
      final String id = artifact.getId();
      if (!mvnIdToBundleNode.containsKey(id))
      {
         mvnIdToBundleNode.put(id, newBundleCandidate(artifact, project));
         return true;
      }
      return false;
   }

   protected BundleCandidate newBundleCandidate(Artifact artifact, MavenProject project)
   {
      final BundleCandidate node = ContextModelFactory.eINSTANCE.createBundleCandidate();
      if (project == null)
      {
         node.addExtension(MavenModelUtils.toMavenArtifact(artifact));
      }
      else
      {
         node.addExtension(MavenModelUtils.toMavenProject(project));
      }
      return node;
   }

   public void handleDependency(Artifact fromArtifact, Artifact toArtifact)
   {
      String filename = toArtifact.getFile().getName();
      if (filename.contains("xml-apis") || filename.contains("ant-") || filename.contains("cglib-")
         || filename.contains("testng-"))
      {
         System.out.println("----> " + fromArtifact + " to " + toArtifact);
      }

      BundleCandidate fromNode = mvnIdToBundleNode.get(fromArtifact.getId());
      BundleCandidate toNode = mvnIdToBundleNode.get(toArtifact.getId());
      fromNode.getDependencies().add(newBundleReference(toNode, toArtifact));
   }

   private BundleReference newBundleReference(BundleCandidate bundleNode, Artifact mappedArtifact)
   {
      final BundleReference bundleReference = ContextModelFactory.eINSTANCE.createBundleReference();
      bundleReference.addExtension(MavenModelUtils.toMavenDependecy(mappedArtifact));

      bundleReference.setTarget(bundleNode);
      bundleReference.setOptional(mappedArtifact.isOptional());
      bundleReference.setProvided(Artifact.SCOPE_PROVIDED.equals(mappedArtifact.getScope()));

      return bundleReference;
   }
}