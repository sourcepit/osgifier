/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core.java.inspect;

import java.io.File;
import java.io.InputStream;

import javax.validation.constraints.NotNull;

import org.sourcepit.osgifyme.core.java.JavaArchive;
import org.sourcepit.osgifyme.core.java.JavaPackage;
import org.sourcepit.osgifyme.core.java.JavaProject;
import org.sourcepit.osgifyme.core.java.JavaType;
import org.sourcepit.tools.osgifyme.core.utils.RelativeDirectoryTraverser;
import org.sourcepit.tools.osgifyme.core.utils.ZipTraverser;

public class JavaTypeAndPackageInvestigator
{
   public void investigateJar(@NotNull final JavaArchive javaArchive, @NotNull File jarFile,
      final IJavaTypeAnalyzer typeAnalyzer)
   {
      new ZipTraverser(jarFile).travers(new JavaResourceVisitor()
      {
         @Override
         protected JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand)
         {
            return javaArchive.getPackage(fullyQualifiedName, createOnDemand);
         }

         @Override
         protected JavaType getType(String packageName, String typeName, boolean createOnDemand)
         {
            return javaArchive.getType(packageName, typeName, createOnDemand);
         }

         @Override
         protected void visitType(JavaType javaType, InputStream content)
         {
            if (typeAnalyzer != null)
            {
               typeAnalyzer.analyze(javaType, content);
            }
         }
      });
   }

   public void investigateProject(@NotNull final JavaProject javaProject, @NotNull File projectDir,
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
      new RelativeDirectoryTraverser(binDir).travers(new JavaResourceVisitor()
      {
         @Override
         protected JavaPackage getPackage(String fullyQualifiedName, boolean createOnDemand)
         {
            return javaProject.getPackage(binDirPath, fullyQualifiedName, createOnDemand);
         }

         @Override
         protected JavaType getType(String packageName, String typeName, boolean createOnDemand)
         {
            return javaProject.getType(binDirPath, packageName, typeName, createOnDemand);
         }

         @Override
         protected void visitType(JavaType javaType, InputStream content)
         {
            if (typeAnalyzer != null)
            {
               typeAnalyzer.analyze(javaType, content);
            }
         }
      });
   }

}
