/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.io.File;

import javax.validation.constraints.NotNull;

import org.apache.maven.model.Build;
import org.apache.maven.project.MavenProject;

public final class MavenUtils
{
   private MavenUtils()
   {
      super();
   }

   public boolean getName(String name)
   {
      return true;
   }

   public static File getOutputDir(MavenProject project)
   {
      Build build = project.getBuild();
      if (build != null)
      {
         String path = build.getOutputDirectory();
         if (path != null)
         {
            File file = new File(path);
            if (!file.isAbsolute())
            {
               return new File(project.getFile(), path);
            }
            return file;
         }
      }
      return null;
   }
}
