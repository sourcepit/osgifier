/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.osgify.core.java.inspect.JavaResourcesBundleScanner;
import org.sourcepit.osgify.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.resolve.SymbolicNameResolver;
import org.sourcepit.osgify.core.resolve.VersionResolver;

@Named
public class BundleCandidateScanner
{
   @Inject
   private SymbolicNameResolver symbolicNameResolver;

   @Inject
   private VersionResolver versionResolver;

   public void scanProject(BundleCandidate bundleCandidate, File projectDir, String... binDirPaths)
   {
      // scan jar contents
      final JavaResourcesBundleScanner scanner = newScanner();
      bundleCandidate.setContent(scanner.scan(projectDir, binDirPaths));

      // resolve OSGi attributes
      resolveOSGiAttributes(bundleCandidate);
   }

   public void scanJar(BundleCandidate bundleCandidate, File jarFile)
   {
      // scan jar contents
      final JavaResourcesBundleScanner scanner = newScanner();
      bundleCandidate.setContent(scanner.scan(jarFile));

      // resolve OSGi attributes
      resolveOSGiAttributes(bundleCandidate);
   }

   private void resolveOSGiAttributes(BundleCandidate bundleCandidate)
   {
      bundleCandidate.setSymbolicName(symbolicNameResolver.resolveSymbolicName(bundleCandidate));
      bundleCandidate.setVersion(versionResolver.resolveVersion(bundleCandidate));
   }

   private JavaResourcesBundleScanner newScanner()
   {
      final JavaResourcesBundleScanner scanner = new JavaResourcesBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());
      return scanner;
   }
}
