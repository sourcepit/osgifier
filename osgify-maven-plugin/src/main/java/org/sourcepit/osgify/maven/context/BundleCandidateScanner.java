/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.io.File;

import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.sourcepit.osgify.context.BundleCandidate;
import org.sourcepit.osgify.core.java.inspect.JavaPackageBundleScanner;
import org.sourcepit.osgify.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.osgify.core.resolve.SymbolicNameResolver;
import org.sourcepit.osgify.core.resolve.VersionResolver;

@Component(role = BundleCandidateScanner.class)
public class BundleCandidateScanner
{
   @Requirement
   private SymbolicNameResolver symbolicNameResolver;

   @Requirement
   private VersionResolver versionResolver;

   public void scanProject(BundleCandidate bundleCandidate, File projectDir, String... binDirPaths)
   {
      // scan jar contents
      final JavaPackageBundleScanner scanner = newScanner();
      bundleCandidate.setContent(scanner.scan(projectDir, binDirPaths));

      // resolve OSGi attributes
      resolveOSGiAttributes(bundleCandidate);
   }

   public void scanJar(BundleCandidate bundleCandidate, File jarFile)
   {
      // scan jar contents
      final JavaPackageBundleScanner scanner = newScanner();
      bundleCandidate.setContent(scanner.scan(jarFile));

      // resolve OSGi attributes
      resolveOSGiAttributes(bundleCandidate);
   }

   private void resolveOSGiAttributes(BundleCandidate bundleCandidate)
   {
      bundleCandidate.setSymbolicName(symbolicNameResolver.resolveSymbolicName(bundleCandidate));
      bundleCandidate.setVersion(versionResolver.resolveVersion(bundleCandidate));
   }

   private JavaPackageBundleScanner newScanner()
   {
      final JavaPackageBundleScanner scanner = new JavaPackageBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());
      return scanner;
   }
}
