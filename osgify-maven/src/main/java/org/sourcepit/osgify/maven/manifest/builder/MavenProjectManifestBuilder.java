/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.utils.props.PropertiesSource;


/**
 * @author DerGilb
 *
 */
public interface MavenProjectManifestBuilder
{
   MavenProjectManifestBuilder project(Artifact project);

   MavenProjectManifestBuilder withDependency(Artifact dependency);
   
   MavenProjectManifestBuilder withDependencies(Collection<Artifact> dependencies);
   
   MavenProjectManifestBuilder setSymbolicName(String symbolicName);

   MavenProjectManifestBuilder setTimestamp(Date timestamp);

   MavenProjectManifestBuilder appendExecutionEnvironment(boolean append);

   MavenProjectManifestBuilder appendPackageExports(boolean append);

   MavenProjectManifestBuilder appendPackageImports(boolean append);

   MavenProjectManifestBuilder appendDynamicImports(boolean append);

   MavenProjectManifestBuilder withOptions(PropertiesSource options);

   MavenProjectManifestBuilder mergeWith(java.util.jar.Manifest manifest);

   MavenProjectManifestBuilder mergeWith(Manifest manifest);

   ManifestBuilderResult build();

   MavenProjectManifestBuilder withProjectProperties(Map<?, ?> properties);

   MavenProjectManifestBuilder withSourceArtifact(Artifact sourceArtifact);


}
