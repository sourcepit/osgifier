/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.packaging;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;

import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.resource.GenericManifestResourceImpl;
import org.sourcepit.common.utils.file.FileUtils;
import org.sourcepit.common.utils.path.PathMatcher;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class OsgifyDependenciesPackager
{
   private final static PathMatcher JAR_CONTENT_MATCHER = createJarContentMatcher();

   public void injectManifest(final File jarFile, final BundleManifest manifest)
   {
      try
      {
         final File tmpFile = File.createTempFile(jarFile.getName(), ".tmp", jarFile.getParentFile());
         org.apache.commons.io.FileUtils.moveFile(jarFile, tmpFile);
         try
         {
            copyJarAndInjectManifest(tmpFile, jarFile, manifest);
         }
         finally
         {
            org.apache.commons.io.FileUtils.forceDelete(tmpFile);
         }
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
   }

   public void copyJarAndInjectManifest(File srcJarFile, File destJarFile, Manifest manifest)
   {
      JarOutputStream destJarOut = null;
      try
      {
         destJarOut = new JarOutputStream(new BufferedOutputStream(FileUtils.openOutputStream(destJarFile, true)));
         rePackageJarFile(srcJarFile, manifest, destJarOut);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      finally
      {
         IOUtils.closeQuietly(destJarOut);
      }
   }

   private void rePackageJarFile(File srcJarFile, final Manifest manifest, JarOutputStream destJarOut)
      throws IOException
   {
      destJarOut.putNextEntry(new JarEntry(JarFile.MANIFEST_NAME));
      writeManifest(manifest, destJarOut);
      destJarOut.closeEntry();

      JarInputStream srcJarIn = null;
      try
      {
         srcJarIn = new JarInputStream(new BufferedInputStream(new FileInputStream(srcJarFile)));
         ZipEntry srcEntry = srcJarIn.getNextEntry();
         while (srcEntry != null)
         {
            if (JAR_CONTENT_MATCHER.isMatch(srcEntry.getName()))
            {
               destJarOut.putNextEntry(srcEntry);
               IOUtils.copy(srcJarIn, destJarOut);
               destJarOut.closeEntry();
            }
            srcJarIn.closeEntry();

            srcEntry = srcJarIn.getNextEntry();
         }
      }
      finally
      {
         IOUtils.closeQuietly(srcJarIn);
      }
   }

   private void writeManifest(Manifest manifest, OutputStream out) throws IOException
   {
      Resource manifestResource = new GenericManifestResourceImpl();
      manifestResource.getContents().add(EcoreUtil.copy(manifest));
      manifestResource.save(out, null);
   }

   private static PathMatcher createJarContentMatcher()
   {
      final Set<String> excludes = new HashSet<String>();
      excludes.add(JarFile.MANIFEST_NAME); // will be set manually
      excludes.add("META-INF/*.SF");
      excludes.add("META-INF/*.DSA");
      excludes.add("META-INF/*.RSA");

      final String matcherPattern = toPathMatcherPattern(excludes);

      return PathMatcher.parse(matcherPattern, "/", ",");
   }

   private static String toPathMatcherPattern(Set<String> excludes)
   {
      final StringBuilder sb = new StringBuilder();
      for (String exclude : excludes)
      {
         sb.append('!');
         sb.append(exclude);
         sb.append(',');
      }
      if (sb.length() > 0)
      {
         sb.deleteCharAt(sb.length() - 1);
      }
      return sb.toString();
   }
}
