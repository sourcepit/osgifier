/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.osgify.core.java.inspect.ClassForNameDetector;
import org.sourcepit.osgify.core.java.inspect.IJavaTypeAnalyzer;
import org.sourcepit.osgify.core.java.inspect.JavaResourcesBundleScanner;
import org.sourcepit.osgify.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.osgify.core.model.context.BundleCandidate;

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
      bundleCandidate.setVersion(versionResolver.resolveVersion(bundleCandidate, new LinkedPropertiesMap()));
   }

   private JavaResourcesBundleScanner newScanner()
   {
      final JavaResourcesBundleScanner scanner = new JavaResourcesBundleScanner();
      final List<IJavaTypeAnalyzer> typeAnalyzers = new ArrayList<IJavaTypeAnalyzer>();
      typeAnalyzers.add(new JavaTypeReferencesAnalyzer());
      typeAnalyzers.add(new ClassForNameDetector());
      scanner.setJavaTypeAnalyzer(typeAnalyzers);
      return scanner;
   }
}
