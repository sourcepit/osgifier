/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.ObjectUtils;
import org.apache.maven.artifact.Artifact;
import org.sourcepit.osgify.maven.model.maven.MavenArtifact;
import org.sourcepit.osgify.maven.model.maven.MavenDependency;
import org.sourcepit.osgify.maven.model.maven.MavenModelFactory;

public final class MavenModelUtils
{
   @NotNull
   public static MavenDependency toMavenDependecy(@NotNull Artifact artifact)
   {
      final MavenDependency dependency = MavenModelFactory.eINSTANCE.createMavenDependency();
      dependency.setGroupId(artifact.getGroupId());
      dependency.setArtifactId(artifact.getArtifactId());
      dependency.setVersionRange(artifact.getVersionRange() == null ? artifact.getVersion() : artifact
         .getVersionRange().toString());
      if (artifact.getClassifier() != null && !ObjectUtils.equals(dependency.getClassifier(), artifact.getClassifier()))
      {
         dependency.setClassifier(artifact.getClassifier());
      }
      if (artifact.getType() != null && !ObjectUtils.equals(dependency.getType(), artifact.getType()))
      {
         dependency.setType(artifact.getType());
      }
      if (artifact.getScope() != null && !ObjectUtils.equals(dependency.getScope(), artifact.getScope()))
      {
         dependency.setScope(artifact.getScope());
      }
      dependency.setOptional(artifact.isOptional());
      return dependency;
   }

   @NotNull
   public static MavenArtifact toMavenArtifact(@NotNull Artifact artifact)
   {
      final MavenArtifact mArtifact = MavenModelFactory.eINSTANCE.createMavenArtifact();
      mArtifact.setGroupId(artifact.getGroupId());
      mArtifact.setArtifactId(artifact.getArtifactId());
      mArtifact.setVersion(artifact.getVersion());
      if (artifact.getClassifier() != null && !ObjectUtils.equals(mArtifact.getClassifier(), artifact.getClassifier()))
      {
         mArtifact.setClassifier(artifact.getClassifier());
      }
      if (artifact.getType() != null && !ObjectUtils.equals(mArtifact.getType(), artifact.getType()))
      {
         mArtifact.setType(artifact.getType());
      }
      mArtifact.setFile(artifact.getFile());
      return mArtifact;
   }

}
