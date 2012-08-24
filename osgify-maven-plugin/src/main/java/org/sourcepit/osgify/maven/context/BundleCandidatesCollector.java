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
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.util.ManifestUtils;
import org.sourcepit.common.maven.model.MavenArtifact;
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

   private final boolean resolveDependenciesOfNativeBundles;

   public BundleCandidatesCollector()
   {
      this(false);
   }

   public BundleCandidatesCollector(boolean resolveDependenciesOfNativeBundles)
   {
      this.resolveDependenciesOfNativeBundles = resolveDependenciesOfNativeBundles;
   }

   public List<BundleCandidate> getBundleCandidates()
   {
      return new ArrayList<BundleCandidate>(mvnIdToBundleNode.values());
   }

   public boolean visitNode(Artifact artifact, MavenProject project)
   {
      final String id = artifact.getId();
      if (!mvnIdToBundleNode.containsKey(id))
      {
         BundleCandidate bundleCandidate = newBundleCandidate(artifact, project);
         mvnIdToBundleNode.put(id, bundleCandidate);

         // no need to visit dependencies of native bundles
         final BundleManifest manifest = lookupBundleManifest(artifact, project);
         bundleCandidate.setManifest(manifest);

         if (isNativeBundle(artifact, project, bundleCandidate))
         {
            bundleCandidate.setNativeBundle(true);
            return resolveDependenciesOfNativeBundles;
         }

         return true;
      }
      return false;
   }

   protected boolean isNativeBundle(Artifact artifact, MavenProject project, BundleCandidate bundleCandidate)
   {
      return bundleCandidate.getManifest() != null;
   }

   protected BundleCandidate newBundleCandidate(Artifact artifact, MavenProject project)
   {
      final BundleCandidate node = ContextModelFactory.eINSTANCE.createBundleCandidate();
      if (project == null)
      {
         MavenArtifact mArtifact = MavenModelUtils.toMavenArtifact(artifact);
         node.setLocation(mArtifact.getFile());
         node.addExtension(mArtifact);
      }
      else
      {
         org.sourcepit.common.maven.model.MavenProject mProject = MavenModelUtils.toMavenProject(project);
         node.setLocation(artifact.getFile());
         node.addExtension(mProject);
      }
      return node;
   }

   private BundleManifest lookupBundleManifest(Artifact artifact, MavenProject project)
   {
      if (project == null)
      {
         Manifest manifest = null;
         try
         {
            manifest = ManifestUtils.readJarManifest(artifact.getFile());
         }
         catch (Exception e)
         {
         }
         return (BundleManifest) (manifest instanceof BundleManifest ? manifest : null);
      }
      else
      {
         return null;
      }
   }

   public void handleDependency(Artifact fromArtifact, Artifact toArtifact)
   {
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