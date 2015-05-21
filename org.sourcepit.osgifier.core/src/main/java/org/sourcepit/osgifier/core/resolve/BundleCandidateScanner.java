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

package org.sourcepit.osgifier.core.resolve;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.osgifier.core.java.inspect.ClassForNameDetector;
import org.sourcepit.osgifier.core.java.inspect.IJavaTypeAnalyzer;
import org.sourcepit.osgifier.core.java.inspect.JavaResourcesBundleScanner;
import org.sourcepit.osgifier.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;

@Named
public class BundleCandidateScanner {
   @Inject
   private SymbolicNameResolver symbolicNameResolver;

   @Inject
   private VersionResolver versionResolver;

   public void scanProject(BundleCandidate bundleCandidate, File projectDir, String... binDirPaths) {
      // scan jar contents
      final JavaResourcesBundleScanner scanner = newScanner();
      bundleCandidate.setContent(scanner.scan(projectDir, binDirPaths));

      // resolve OSGi attributes
      resolveOSGiAttributes(bundleCandidate);
   }

   public void scanJar(BundleCandidate bundleCandidate, File jarFile) {
      // scan jar contents
      final JavaResourcesBundleScanner scanner = newScanner();
      bundleCandidate.setContent(scanner.scan(jarFile));

      // resolve OSGi attributes
      resolveOSGiAttributes(bundleCandidate);
   }

   private void resolveOSGiAttributes(BundleCandidate bundleCandidate) {
      bundleCandidate.setSymbolicName(symbolicNameResolver.resolveSymbolicName(bundleCandidate,
         new LinkedPropertiesMap()));
      bundleCandidate.setVersion(versionResolver.resolveVersion(bundleCandidate, new LinkedPropertiesMap()));
   }

   private JavaResourcesBundleScanner newScanner() {
      final JavaResourcesBundleScanner scanner = new JavaResourcesBundleScanner();
      final List<IJavaTypeAnalyzer> typeAnalyzers = new ArrayList<IJavaTypeAnalyzer>();
      typeAnalyzers.add(new JavaTypeReferencesAnalyzer());
      typeAnalyzers.add(new ClassForNameDetector());
      scanner.setJavaTypeAnalyzer(typeAnalyzers);
      return scanner;
   }
}
