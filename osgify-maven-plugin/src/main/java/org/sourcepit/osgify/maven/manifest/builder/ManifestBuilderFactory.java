/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.project.MavenProject;
import org.sourcepit.common.maven.aether.ArtifactFactory;
import org.sourcepit.osgify.core.resolve.VersionRangeResolver;
import org.sourcepit.osgify.maven.OsgifyContextInflator;
import org.sourcepit.osgify.maven.manifest.builder.impl.MavenProjectManifestBuilderImpl;

/**
 * @author DerGilb
 *
 */
@Named
public class ManifestBuilderFactory
{
   private ArtifactFactory artifactFactory;
   private VersionRangeResolver versionRangeResolver;
   private OsgifyContextInflator inflater;

   @Inject
   public ManifestBuilderFactory(ArtifactFactory artifactFactory, VersionRangeResolver versionRangeResolver,
      OsgifyContextInflator inflater)
   {
      super();
      this.artifactFactory = artifactFactory;
      this.versionRangeResolver = versionRangeResolver;
      this.inflater = inflater;
   }


   public MavenProjectManifestBuilder forProject(MavenProject project)
   {
      return new MavenProjectManifestBuilderImpl(project, artifactFactory, versionRangeResolver, inflater);
   }
}
