/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import java.io.File;
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
   private IJavaTypeAnalyzer typeAnalyzer;

   public void setJavaTypeAnalyzer(IJavaTypeAnalyzer typeAnalyzer)
   {
      this.typeAnalyzer = typeAnalyzer;
   }

   public JavaArchive scan(@NotNull File jarFile)
   {
      JavaArchive javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      scan(javaArchive, jarFile, typeAnalyzer);
      return javaArchive;
   }

   public JavaArchive scan(@NotNull File jarFile, final IJavaTypeAnalyzer typeAnalyzer)
   {
      JavaArchive javaArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      scan(javaArchive, jarFile, typeAnalyzer);
      return javaArchive;
   }

   public void scan(@NotNull final JavaArchive javaArchive, @NotNull File jarFile, final IJavaTypeAnalyzer typeAnalyzer)
   {
      new ZipTraverser(jarFile).travers(newJavaResourceVisitor(javaArchive, "", typeAnalyzer));
   }

   public JavaProject scan(@NotNull File projectDir, String... binDirPaths)
   {
      JavaProject javaProject = JavaModelFactory.eINSTANCE.createJavaProject();
      scan(javaProject, projectDir, typeAnalyzer, binDirPaths);
      return javaProject;
   }

   public JavaProject scan(@NotNull File projectDir, final IJavaTypeAnalyzer typeAnalyzer, String... binDirPaths)
   {
      JavaProject javaProject = JavaModelFactory.eINSTANCE.createJavaProject();
      scan(javaProject, projectDir, typeAnalyzer, binDirPaths);
      return javaProject;
   }

   public void scan(@NotNull final JavaProject javaProject, @NotNull File projectDir,
      final IJavaTypeAnalyzer typeAnalyzer, String... binDirPaths)
   {
      if (binDirPaths == null || binDirPaths.length == 0)
      {
         investigateBinDirectory(javaProject, "", projectDir, typeAnalyzer);
      }
      else
      {
         for (final String binDirPath : binDirPaths)
         {
            final File binDir = new File(projectDir, binDirPath);
            investigateBinDirectory(javaProject, binDirPath, binDir, typeAnalyzer);
         }
      }
   }

   private void investigateBinDirectory(final JavaProject javaProject, final String binDirPath, final File binDir,
      final IJavaTypeAnalyzer typeAnalyzer)
   {
      new RelativeDirectoryTraverser(binDir).travers(newJavaResourceVisitor(javaProject, binDirPath, typeAnalyzer));
   }

   protected JavaResourceVisitor newJavaResourceVisitor(final JavaResourceBundle javaBundle, String rootName,
      final IJavaTypeAnalyzer typeAnalyzer)
   {
      final ReadWriteLock rwLock = new ReentrantReadWriteLock(false);

      final JavaResourceVisitor visitor = new JavaResourceVisitor(javaBundle, rootName, rwLock);
      final JavaClassFileHandler classFileHandler = new JavaClassFileHandler();
      if (typeAnalyzer != null)
      {
         classFileHandler.getTypeAnalyzers().add(typeAnalyzer);
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
