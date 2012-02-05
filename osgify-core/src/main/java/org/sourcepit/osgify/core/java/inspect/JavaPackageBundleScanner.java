/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.inspect;

import java.io.File;
import java.io.InputStream;

import javax.validation.constraints.NotNull;

import org.sourcepit.osgify.core.inspect.JavaResourceVisitor;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaProject;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.util.RelativeDirectoryTraverser;
import org.sourcepit.osgify.core.util.ZipTraverser;

public class JavaPackageBundleScanner
{
   protected IJavaTypeAnalyzer typeAnalyzer;

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
      new ZipTraverser(jarFile).travers(newJavaResourceVisitor(javaArchive, typeAnalyzer));
   }

   protected JavaResourceVisitor newJavaResourceVisitor(final JavaArchive javaArchive,
      final IJavaTypeAnalyzer typeAnalyzer)
   {
      return new JavaResourceVisitor()
      {
         @Override
         protected synchronized JavaResourcesRoot getPackageRoot(boolean createOnDemand)
         {
            return javaArchive.getResourcesRoot("", createOnDemand);
         }

         @Override
         protected synchronized JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand)
         {
            return javaArchive.getPackage(fullyQualifiedName, createOnDemand);
         }

         @Override
         protected synchronized JavaType getType(String packageName, String typeName, boolean createOnDemand)
         {
            return javaArchive.getType(packageName, typeName, createOnDemand);
         }

         @Override
         protected void visitJType(JavaType javaType, InputStream content)
         {
            if (typeAnalyzer != null)
            {
               typeAnalyzer.analyze(javaType, content);
            }
         }
      };
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

   protected JavaResourceVisitor newJavaResourceVisitor(final JavaProject javaProject, final String binDirPath,
      final IJavaTypeAnalyzer typeAnalyzer)
   {
      return new JavaResourceVisitor()
      {
         @Override
         protected synchronized JavaResourcesRoot getPackageRoot(boolean createOnDemand)
         {
            return javaProject.getResourcesRoot(binDirPath, createOnDemand);
         }

         @Override
         protected synchronized JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand)
         {
            return javaProject.getPackage(binDirPath, fullyQualifiedName, createOnDemand);
         }

         @Override
         protected synchronized JavaType getType(String packageName, String typeName, boolean createOnDemand)
         {
            return javaProject.getType(binDirPath, packageName, typeName, createOnDemand);
         }

         @Override
         protected void visitJType(JavaType javaType, InputStream content)
         {
            if (typeAnalyzer != null)
            {
               typeAnalyzer.analyze(javaType, content);
            }
         }
      };
   }
}
