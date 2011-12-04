/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.its;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ProcessDestroyer;
import org.apache.commons.exec.PumpStreamHandler;
import org.codehaus.plexus.util.FileUtils;
import org.junit.After;
import org.junit.Test;

/**
 * @author Bernd
 */
public class FooTest
{
   private static final Properties props;

   static
   {
      props = new Properties();
      ClassLoader cl = AbstractB2ExamplesIT.class.getClassLoader();
      InputStream is = cl.getResourceAsStream("osgiy-its.properties");
      if (is != null)
      {
         try
         {
            try
            {
               props.load(is);
            }
            finally
            {
               is.close();
            }
         }
         catch (IOException e)
         {
            throw new RuntimeException(e);
         }
      }
   }

   static synchronized String getProperty(String key)
   {
      return props.getProperty(key);
   }

   protected TearDownProcessDestroyer processDestroyer = new TearDownProcessDestroyer();

   @Test
   public void testEnv() throws Exception
   {
      String projects = getProperty("projects-dir");
      String out = getProperty("output-dir");
      String maven = getProperty("maven-dir");

      File projectDir = getBasedir("osgify-test");

      DefaultExecutor executor = new DefaultExecutor();
      executor.setWorkingDirectory(projectDir);
      executor.setStreamHandler(new PumpStreamHandler(System.out, System.err));
      executor.setProcessDestroyer(processDestroyer);

      Map<String, String> environment = new HashMap<String, String>(System.getenv());
      environment.remove("M2_HOME");
      environment.put("M2_HOME", maven);

      environment.remove("JAVA_HOME");
      environment.put("JAVA_HOME", System.getProperty("java.home"));

      final String javaagent = System.getProperty("javaagent");
      if (javaagent != null)
      {
         String mvnOpts = environment.get("MAVEN_OPTS");
         if (mvnOpts == null)
         {
            mvnOpts = javaagent;
         }
         else
         {
            mvnOpts = (mvnOpts + " " + javaagent).trim();
         }
         environment.put("MAVEN_OPTS", mvnOpts);
      }

      CommandLine command = new CommandLine(new File(maven + "/bin/mvn.bat"));
      command.addArgument("-B");
      command.addArgument("clean");
      command.addArgument("package");
      
      executor.execute(command, environment);
   }

   @After
   public void tearDown()
   {
      processDestroyer.tearDown();
   }

   protected File getBasedir(String test) throws IOException
   {

      String projects = getProperty("projects-dir");
      String out = getProperty("output-dir");

      File src = new File(projects, test).getCanonicalFile();
      File dst = new File(out + "/projects", test).getCanonicalFile();

      if (dst.isDirectory())
      {
         FileUtils.deleteDirectory(dst);
      }
      else if (dst.isFile())
      {
         if (!dst.delete())
         {
            throw new IOException("Can't delete file " + dst.toString());
         }
      }

      FileUtils.copyDirectoryStructure(src, dst);

      return dst;
   }


   private static class TearDownProcessDestroyer implements ProcessDestroyer
   {
      private final List<Process> processes = new ArrayList<Process>();

      public synchronized boolean add(Process process)
      {
         return processes.add(process);
      }

      public synchronized boolean remove(Process process)
      {
         return processes.remove(process);
      }

      public synchronized int size()
      {
         return processes.size();
      }

      public synchronized void tearDown()
      {
         for (Process process : processes)
         {
            try
            {
               process.destroy();
            }
            catch (Throwable t)
            {
               System.err.println("Unable to terminate process during process shutdown");
            }
         }
      }
   }


}
