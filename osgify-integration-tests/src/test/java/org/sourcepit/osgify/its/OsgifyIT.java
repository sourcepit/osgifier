/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.its;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import junit.framework.AssertionFailedError;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.OS;
import org.junit.Rule;
import org.junit.Test;

public class OsgifyIT
{
   private final static boolean DEBUG = true;

   protected Environment environment = Environment.getInstance();

   @Rule
   public ExternalProcess externalProcess = new ExternalProcess();

   @Rule
   public Workspace workspace = new Workspace(environment.getOutputDir(), false);

   @Test
   public void testOsgify() throws Exception
   {
      executeBuild("osgify-test");
   }

   protected void executeBuild(String projectName) throws IOException
   {
      final Map<String, String> envVars = environment.newEnvironmentVariables();
      final File projectDir = getProjectDir(projectName);
      externalProcess.execute(envVars, projectDir, newMavenCmd("-B", "-e", "clean", "package"));
   }

   protected CommandLine newMavenCmd(String... arguments)
   {
      final String mvnExec = isDebug() ? "mvnDebug" : "mvn";

      final CommandLine cmd;
      final File mavenBinDir = new File(environment.getMavenDir(), "/bin");
      if (OS.isFamilyWindows() || OS.isFamilyWin9x())
      {
         cmd = externalProcess.newCommandLine(new File(mavenBinDir, mvnExec + ".bat"));
      }
      else if (OS.isFamilyUnix() || OS.isFamilyMac())
      {
         cmd = externalProcess.newCommandLine("sh", new File(mavenBinDir, mvnExec).getAbsolutePath());
      }
      else
      {
         throw new AssertionFailedError("Os family");
      }
      cmd.addArguments(arguments);
      return cmd;
   }

   protected boolean isDebug()
   {
      return environment.isDebugAllowed() && DEBUG;
   }

   protected File getProjectDir(String projectName) throws IOException
   {
      File projects = environment.getPropertyAsFile("projects-dir", true);
      File src = new File(projects, projectName).getCanonicalFile();
      return workspace.importDir(src);
   }


}
