/**
 * Copyright (c) 2014 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.manifest.builder;

import java.util.Date;
import java.util.Map;
import java.util.jar.Manifest;

import org.apache.maven.project.MavenProject;


/**
 * @author DerGilb
 *
 */
public interface MavenProjectManifestBuilder
{

   MavenProjectManifestBuilder setSymbolicName(String symbolicName);

   MavenProjectManifestBuilder setTimestamp(Date timestamp);

   MavenProjectManifestBuilder setAppendExecutionEnvironment(boolean append);

   MavenProjectManifestBuilder setAppendPackageExports(boolean append);

   MavenProjectManifestBuilder setAppendPackageImports(boolean append);

   MavenProjectManifestBuilder setAppendDynamicImports(boolean append);

   MavenProjectManifestBuilder attachSourceBundle(boolean withSources);

   MavenProjectManifestBuilder attachSources(String sourceClassifier);

   MavenProjectManifestBuilder attachOption(String key, String value);

   MavenProjectManifestBuilder attachOptions(Map<String, String> options);

   ManifestBuilderResult build();

   MavenProject getProject();

   boolean wasBuilt();

}
