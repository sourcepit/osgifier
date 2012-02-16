/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.validation.constraints.NotNull;

import org.sourcepit.osgify.core.inspect.DefaultFileHandler;
import org.sourcepit.osgify.core.inspect.JavaClassFileHandler;
import org.sourcepit.osgify.core.inspect.JavaPackageHandler;
import org.sourcepit.osgify.core.inspect.JavaResourceVisitor;
import org.sourcepit.osgify.core.inspect.ManifestHandler;
import org.sourcepit.osgify.core.inspect.PackageInfoHandler;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaProject;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.util.RelativeDirectoryTraverser;
import org.sourcepit.osgify.core.util.ZipTraverser;

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
