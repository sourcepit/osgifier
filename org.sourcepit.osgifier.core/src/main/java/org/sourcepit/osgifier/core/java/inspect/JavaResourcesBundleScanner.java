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

package org.sourcepit.osgifier.core.java.inspect;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.sourcepit.common.constraints.NotNull;
import org.sourcepit.osgifier.core.inspect.DefaultFileHandler;
import org.sourcepit.osgifier.core.inspect.JavaClassFileHandler;
import org.sourcepit.osgifier.core.inspect.JavaPackageHandler;
import org.sourcepit.osgifier.core.inspect.JavaResourceVisitor;
import org.sourcepit.osgifier.core.inspect.ManifestHandler;
import org.sourcepit.osgifier.core.inspect.PackageInfoHandler;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaProject;
import org.sourcepit.osgifier.core.model.java.JavaResourceBundle;
import org.sourcepit.osgifier.core.util.RelativeDirectoryTraverser;
import org.sourcepit.osgifier.core.util.ZipTraverser;

public class JavaResourcesBundleScanner
{
   private Collection<? extends IJavaTypeAnalyzer> typeAnalyzers;

   public void setJavaTypeAnalyzer(Collection<? extends IJavaTypeAnalyzer> typeAnalyzers)
   {
      this.typeAnalyzers = typeAnalyzers;
   }

   public JavaArchive scan(@NotNull File jarFile)
   {
      JavaArchive javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      scan(javaArchive, jarFile, typeAnalyzers);
      return javaArchive;
   }

   public JavaArchive scan(@NotNull File jarFile, final Collection<? extends IJavaTypeAnalyzer> typeAnalyzers)
   {
      JavaArchive javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      scan(javaArchive, jarFile, typeAnalyzers);
      return javaArchive;
   }

   public void scan(@NotNull final JavaArchive javaArchive, @NotNull File jarFile,
      final Collection<? extends IJavaTypeAnalyzer> typeAnalyzers)
   {
      new ZipTraverser(jarFile).travers(newJavaResourceVisitor(javaArchive, "", typeAnalyzers));
   }

   public JavaProject scan(@NotNull File projectDir, String... binDirPaths)
   {
      JavaProject javaProject = JavaModelFactory.eINSTANCE.createJavaProject();
      scan(javaProject, projectDir, typeAnalyzers, binDirPaths);
      return javaProject;
   }

   public JavaProject scan(@NotNull File projectDir, final Collection<IJavaTypeAnalyzer> typeAnalyzers,
      String... binDirPaths)
   {
      JavaProject javaProject = JavaModelFactory.eINSTANCE.createJavaProject();
      scan(javaProject, projectDir, typeAnalyzers, binDirPaths);
      return javaProject;
   }

   public void scan(@NotNull final JavaProject javaProject, @NotNull File projectDir,
      final Collection<? extends IJavaTypeAnalyzer> typeAnalyzers, String... binDirPaths)
   {
      if (binDirPaths == null || binDirPaths.length == 0)
      {
         investigateBinDirectory(javaProject, "", projectDir, typeAnalyzers);
      }
      else
      {
         for (final String binDirPath : binDirPaths)
         {
            final File binDir = new File(projectDir, binDirPath);
            investigateBinDirectory(javaProject, binDirPath, binDir, typeAnalyzers);
         }
      }
   }

   private void investigateBinDirectory(final JavaProject javaProject, final String binDirPath, final File binDir,
      final Collection<? extends IJavaTypeAnalyzer> typeAnalyzers)
   {
      new RelativeDirectoryTraverser(binDir).travers(newJavaResourceVisitor(javaProject, binDirPath, typeAnalyzers));
   }

   protected JavaResourceVisitor newJavaResourceVisitor(final JavaResourceBundle javaBundle, String rootName,
      final Collection<? extends IJavaTypeAnalyzer> typeAnalyzers)
   {
      final ReadWriteLock rwLock = new ReentrantReadWriteLock(false);

      final JavaResourceVisitor visitor = new JavaResourceVisitor(javaBundle, rootName, rwLock);
      final JavaClassFileHandler classFileHandler = new JavaClassFileHandler();
      if (typeAnalyzers != null)
      {
         classFileHandler.getTypeAnalyzers().addAll(typeAnalyzers);
      }
      visitor.getResourceHandlers().add(classFileHandler);
      visitor.getResourceHandlers().add(new JavaPackageHandler());
      visitor.getResourceHandlers().add(new PackageInfoHandler());
      visitor.getResourceHandlers().add(new ManifestHandler());
      final DefaultFileHandler defaulFileHandler = new DefaultFileHandler();
      defaulFileHandler.setCreateEmptyDirectories(false);
      defaulFileHandler.setCreateEmptyFiles(true);
      visitor.getResourceHandlers().add(defaulFileHandler);
      return visitor;
   }
}
