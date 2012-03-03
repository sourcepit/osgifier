/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.it;

import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import junit.framework.AssertionFailedError;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.OS;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.hamcrest.core.IsNull;
import org.junit.Rule;
import org.junit.Test;
import org.sourcepit.common.maven.model.MavenModelPackage;
import org.sourcepit.common.testing.Environment;
import org.sourcepit.common.testing.ExternalProcess;
import org.sourcepit.common.testing.Workspace;
import org.sourcepit.osgify.core.model.context.ContextModelPackage;
import org.sourcepit.osgify.core.model.context.OsgifyContext;

public class OsgifyIT
{
   private final static boolean DEBUG = false;

   protected Environment environment = Environment.get("osgiy-its.properties");

   @Rule
   public ExternalProcess externalProcess = new ExternalProcess();

   @Rule
   public Workspace workspace = new Workspace(new File(environment.getBuildDir(), "ws"), false);

   @Test
   public void testOsgify() throws Exception
   {
      executeBuild("osgify-test");
   }

   protected void executeBuild(String projectName) throws IOException
   {
      final Map<String, String> envVars = environment.newEnvs();
      final File projectDir = getProjectDir(projectName);
      externalProcess.execute(envVars, projectDir, newMavenCmd("-B", "-e", "clean", "package"));

      File file = new File(projectDir, "target/osgify-context.xml");
      OsgifyContext ctx = loadModel(file);
      assertThat(ctx, IsNull.notNullValue());
   }

   protected OsgifyContext loadModel(File file) throws IOException
   {
      MavenModelPackage.eINSTANCE.eClass();
      ContextModelPackage.eINSTANCE.eClass();
      Resource resource = new XMLResourceImpl(URI.createFileURI(file.getAbsolutePath()));
      resource.load(null);

      OsgifyContext ctx = (OsgifyContext) resource.getContents().get(0);
      return ctx;
   }

   protected CommandLine newMavenCmd(String... arguments)
   {
      final String mvnExec = isDebug() ? "mvnDebug" : "mvn";

      final CommandLine cmd;
      final File mavenBinDir = new File(environment.getMavenHome(), "/bin");
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
      File projects = environment.getPropertyAsFile("it.resources", true);
      File src = new File(projects, projectName).getCanonicalFile();
      return workspace.importDir(src);
   }


}
