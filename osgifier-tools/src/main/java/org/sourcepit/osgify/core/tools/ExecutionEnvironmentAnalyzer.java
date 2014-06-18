/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.tools;

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
import org.sourcepit.osgifier.core.java.inspect.JavaResourcesBundleScanner;
import org.sourcepit.osgifier.core.java.util.JavaLangUtils;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaClass;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.model.java.JavaResourceDirectory;
import org.sourcepit.osgifier.core.model.java.JavaResourcesRoot;
import org.sourcepit.osgifier.core.model.java.Resource;
import org.sourcepit.osgifier.core.model.java.ResourceVisitor;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.tidy.Tidy;

import com.google.gson.stream.JsonWriter;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class ExecutionEnvironmentAnalyzer
{
   private static final Logger LOGGER = LoggerFactory.getLogger(ExecutionEnvironmentAnalyzer.class);

   public static final String UTF_8 = "UTF-8";

   public static void main(String[] args) throws IOException
   {
      new ExecutionEnvironmentAnalyzer().doMain(args);
   }

   public void doMain(String[] args) throws IOException
   {
      if (args.length > 0 && "--jdoc".equals(args[0]))
      {
         final JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(System.out, UTF_8));
         jsonWriter.setIndent("  ");
         jsonWriter.beginArray();

         List<String> platformPackages = parseJavaPlatformJDoc(args[1]);

         write(jsonWriter, null, null, -1, platformPackages, Collections.<String> emptyList());

         jsonWriter.endArray();

         return;
      }

      if (args.length > 0 && "--jar".equals(args[0]))
      {
         analyzeJar(args[2], args[3], args[4], new File(args[1]), new OutputStreamWriter(System.out, UTF_8));
         return;
      }

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
         analyze(javaHomes, outputStream);
      }
      finally
      {
         IOUtils.closeQuietly(outputStream);
      }
   }

   private void analyzeJar(String type, String version, String vendor, File jar, Writer writer) throws IOException
   {
      final JsonWriter jsonWriter = new JsonWriter(writer);
      jsonWriter.setIndent("  ");
      jsonWriter.beginArray();

      final JavaResourcesBundleScanner scanner = new JavaResourcesBundleScanner();
      JavaArchive javaArchive = scanner.scan(jar);
      javaArchive.setName(jar.getName());

      List<String> packages = new ArrayList<String>();
      int major = collectPackagesAndMaxMajor(javaArchive, packages);
      Collections.sort(packages);

      write(jsonWriter, vendor, version, major, packages, Collections.<String> emptyList());

      jsonWriter.endArray();
      jsonWriter.flush();
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

   public void analyze(Iterable<File> javaHomes, OutputStream outputStream) throws IOException
   {
      analyze(javaHomes, new OutputStreamWriter(outputStream, UTF_8));
   }

   public void analyze(Iterable<File> javaHomes, Writer writer) throws IOException
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

      int major = 0;
      final JavaResourcesBundleScanner scanner = new JavaResourcesBundleScanner();
      for (JavaArchive jar : jars)
      {
         final String jarName = jar.getName();

         LOGGER.info("Scanning Java archive " + jarName);
         scanner.scan(jar, new File(javaHome, jarName), null);

         major = Math.max(major, collectPackagesAndMaxMajor(jar, vendorPackages));

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
      write(jsonWriter, vendor, version, major, platformPackages, vendorPackages);
   }

   private int collectPackagesAndMaxMajor(JavaArchive jar, final List<String> packages)
   {
      final int[] major = new int[1];
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
                  if (!jPackage.getJavaFiles().isEmpty() && !packages.contains(qualifiedName))
                  {
                     packages.add(qualifiedName);
                  }
               }
               else if (resource instanceof JavaClass)
               {
                  major[0] = Math.max(major[0], ((JavaClass) resource).getMajor());
               }
               return resource instanceof JavaResourceDirectory;
            }
         });
      }
      return major[0];
   }

   private void write(JsonWriter jsonWriter, String vendor, String version, int major,
      final List<String> platformPackages, List<String> vendorPackages) throws IOException
   {
      jsonWriter.beginObject();
      jsonWriter.name("type");
      jsonWriter.value("jre");
      jsonWriter.name("vendor");
      jsonWriter.value(vendor);
      jsonWriter.name("version");
      jsonWriter.value(version);
      jsonWriter.name("classVersion");
      jsonWriter.value(major);
      jsonWriter.name("platformPackages");
      jsonWriter.beginArray();
      if (!platformPackages.isEmpty())
      {
         for (String packageName : platformPackages)
         {
            jsonWriter.value(packageName);
         }
      }
      jsonWriter.endArray();
      if (!vendorPackages.isEmpty())
      {
         jsonWriter.name("vendorPackages");
         jsonWriter.beginArray();
         for (String packageName : vendorPackages)
         {
            jsonWriter.value(packageName);
         }
         jsonWriter.endArray();
      }
      jsonWriter.endObject();
      jsonWriter.flush();
   }

   private void collectLibraries(File javaHome, final List<File> libFiles)
   {
      FileUtils.accept(new File(javaHome, "lib"), new FileVisitor()
      {
         public boolean visit(File file)
         {
            if (file.getName().endsWith(".jar") || file.getName().endsWith("zip"))
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
      try
      {
         String url = "http://docs.oracle.com/javase/" + apiVersion + "/docs/api/overview-frame.html";
         return parseJavaPlatformJDoc(url);
      }
      catch (Exception e)
      {
         String url = "http://docs.oracle.com/javase/" + apiVersion.substring(2, apiVersion.lastIndexOf('.')) + "/docs/api/overview-frame.html";
         return parseJavaPlatformJDoc(url);
      }
   }

   private List<String> parseJavaPlatformJDoc(String urlString)
   {
      InputStream openStream = null;
      try
      {
         final URL url = new URL(urlString);
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
