/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.resolve;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.sourcepit.modularizor.core.java.inspect.ClassForNameDetector;
import org.sourcepit.modularizor.core.java.inspect.IJavaTypeAnalyzer;
import org.sourcepit.modularizor.core.java.inspect.JavaResourcesBundleScanner;
import org.sourcepit.modularizor.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.modularizor.java.JavaArchive;
import org.sourcepit.modularizor.java.JavaProject;
import org.sourcepit.modularizor.moduleworlds.AssembledModule;
import org.sourcepit.modularizor.moduleworlds.ExplodedModule;

@Named
public class ModuleJavaResourcesScanner
{
   public JavaProject scan(ExplodedModule module)
   {
      final List<File> binDirs = module.getBinaryDirectories();
      return newScanner().scan(module.getDirectory(), binDirs.toArray(new File[binDirs.size()]));
   }

   public JavaArchive scan(AssembledModule module)
   {
      return newScanner().scan(module.getFile());
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
