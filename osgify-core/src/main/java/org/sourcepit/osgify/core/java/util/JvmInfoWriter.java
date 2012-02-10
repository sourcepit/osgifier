/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.java.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.utils.file.FileUtils;
import org.sourcepit.common.utils.file.FileVisitor;
import org.sourcepit.common.utils.path.PathUtils;
import org.sourcepit.common.utils.xml.XmlUtils;
import org.sourcepit.osgify.core.java.inspect.JavaResourcesBundleScanner;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgify.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgify.core.model.java.Resource;
import org.sourcepit.osgify.core.model.java.ResourceVisitor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;

import com.google.gson.stream.JsonWriter;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class JvmInfoWriter
{
   private static final Logger LOGGER = LoggerFactory.getLogger(JvmInfoWriter.class);

   public static final String UTF_8 = "UTF-8";

   public static void main(String[] args) throws IOException
   {
      new JvmInfoWriter().doMain(args);
   }

   public void doMain(String[] args) throws IOException
   {
      final List<File> javaHomes = new ArrayList<File>();
      final File out = parseArgs(args, javaHomes);
      OutputStream outputStream = null;
      try
      {
         if (out == null)
         {
            outputStream = System.out;
         }
         else
         {
            outputStream = new BufferedOutputStream(new FileOutputStream(out));
         }
         write(javaHomes, outputStream);
      }
      finally
      {
         IOUtils.closeQuietly(outputStream);
      }
   }

   private File parseArgs(String[] args, List<File> javaHomes)
   {
      if (args == null || args.length == 0)
      {
         return getDefaultArgs(javaHomes);
      }
      else
      {
         boolean h = false;
         boolean o = false;
         File out = null;
         for (String arg : args)
         {
            if ("--javaHomes".equals(arg) || "-h".equals(arg))
            {
               h = true;
               o = false;
            }
            else if ("--out".equals(arg) || "-o".equals(arg))
            {
               h = false;
               o = true;
            }
            else if (h)
            {
               javaHomes.add(newFile(arg));
            }
            else if (o)
            {
               out = newFile(arg);
            }
         }
         return out;
      }
   }

   private static File newFile(String arg)
   {
      final String path;
      if (arg.indexOf('"') == 1)
      {
         path = arg.substring(1, arg.length() - 1);
      }
      else
      {
         path = arg;
      }
      return new File(path);
   }

   private static File getDefaultArgs(List<File> javaHomes)
   {
      final String javaHome = System.getProperty("java.home");
      if (javaHome != null)
      {
         javaHomes.add(newFile(javaHome));
      }
      return null;
   }

   public void write(Iterable<File> javaHomes, OutputStream outputStream) throws IOException
   {
      write(javaHomes, new OutputStreamWriter(outputStream, UTF_8));
   }

   public void write(Iterable<File> javaHomes, Writer writer) throws IOException
   {
      final JsonWriter jsonWriter = new JsonWriter(writer);
      jsonWriter.setIndent("  ");
      jsonWriter.beginArray();
      for (File javaHome : javaHomes)
      {
         analyze(javaHome, jsonWriter);
      }
      jsonWriter.endArray();
      jsonWriter.flush();
   }

   private void analyze(File javaHome, JsonWriter jsonWriter) throws IOException
   {
      LOGGER.info("Starting to analyze " + javaHome);

      final List<File> jarFiles = new ArrayList<File>();
      collectLibraries(javaHome, jarFiles);

      final List<JavaArchive> jars = new ArrayList<JavaArchive>();
      for (File jarFile : jarFiles)
      {
         JavaArchive jar = JavaModelFactory.eINSTANCE.createJavaArchive();
         jar.setName(PathUtils.getRelativePath(jarFile, javaHome, "/"));
         jars.add(jar);
      }

      final List<String> vendorPackages = new ArrayList<String>();
      String vendor = null;
      String version = null;

      final JavaResourcesBundleScanner scanner = new JavaResourcesBundleScanner();
      for (JavaArchive jar : jars)
      {
         final String jarName = jar.getName();

         LOGGER.info("Scanning Java archive " + jarName);
         scanner.scan(jar, new File(javaHome, jarName), null);

         for (JavaResourcesRoot jRoot : jar.getResourcesRoots())
         {
            jRoot.accept(new ResourceVisitor()
            {
               public boolean visit(Resource resource)
               {
                  if (resource instanceof JavaPackage)
                  {
                     final JavaPackage jPackage = (JavaPackage) resource;
                     final String qualifiedName = jPackage.getQualifiedName();
                     if (!jPackage.getJavaFiles().isEmpty() && !vendorPackages.contains(qualifiedName))
                     {
                        vendorPackages.add(qualifiedName);
                     }
                  }
                  return resource instanceof JavaResourceDirectory;
               }
            });
         }

         if (jarName.endsWith("/rt.jar") || jarName.equals("rt.jar"))
         {
            Resource manifestFile = jar.getResource("META-INF/MANIFEST.MF");
            Manifest manifest = manifestFile.getExtension(Manifest.class);
            version = manifest.getHeaderValue("Implementation-Version");
            vendor = manifest.getHeaderValue("Implementation-Vendor");
         }
      }

      List<String> platformPackages = fetchJavaPlatformPackages(version);
      Collections.sort(platformPackages);

      vendorPackages.removeAll(platformPackages);

      Collections.sort(vendorPackages);
      write(jsonWriter, vendor, version, platformPackages, vendorPackages);
   }

   private void write(JsonWriter jsonWriter, String vendor, String version, final List<String> platformPackages,
      List<String> vendorPackages) throws IOException
   {
      jsonWriter.beginObject();
      jsonWriter.name("type");
      jsonWriter.value("jre");
      jsonWriter.name("vendor");
      jsonWriter.value(vendor);
      jsonWriter.name("version");
      jsonWriter.value(version);
      jsonWriter.name("platformPackages");
      jsonWriter.beginArray();
      for (String packageName : platformPackages)
      {
         jsonWriter.value(packageName);
      }
      jsonWriter.endArray();
      jsonWriter.name("vendorPackages");
      jsonWriter.beginArray();
      for (String packageName : vendorPackages)
      {
         jsonWriter.value(packageName);
      }
      jsonWriter.endArray();
      jsonWriter.endObject();
      jsonWriter.flush();
   }

   private void collectLibraries(File javaHome, final List<File> libFiles)
   {
      FileUtils.accept(new File(javaHome, "lib"), new FileVisitor()
      {
         public boolean visit(File file)
         {
            if (file.getName().endsWith(".jar"))
            {
               libFiles.add(file);
            }
            return true;
         }
      });
   }

   private List<String> fetchJavaPlatformPackages(String version)
   {
      final String apiVersion;
      int idx = version.indexOf('_');
      if (idx > -1)
      {
         apiVersion = version.substring(0, idx);
      }
      else
      {
         apiVersion = version;
      }

      InputStream openStream = null;
      try
      {
         final URL url = new URL("http://docs.oracle.com/javase/" + apiVersion + "/docs/api/overview-frame.html");
         LOGGER.info("Fetching Java platform packages from " + url.toString());

         final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         openStream = connection.getInputStream();

         final Tidy tidy = new Tidy();
         tidy.setQuiet(true);
         tidy.setShowErrors(0);
         tidy.setShowWarnings(false);
         tidy.setXmlOut(true);

         final Document dom = tidy.parseDOM(openStream, null);
         final String packagesQuery = "/html/body//a[@target='packageFrame']//text()";

         final List<String> platformPackages = new ArrayList<String>();
         for (Node node : XmlUtils.queryNodes(dom, packagesQuery))
         {
            final String packageName = node.getNodeValue().trim();
            if (JavaLangUtils.isFullyQuallifiedPackageName(packageName))
            {
               if (!platformPackages.contains(packageName))
               {
                  platformPackages.add(packageName);
               }
            }
         }
         return platformPackages;
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      finally
      {
         IOUtils.closeQuietly(openStream);
      }
   }
}
