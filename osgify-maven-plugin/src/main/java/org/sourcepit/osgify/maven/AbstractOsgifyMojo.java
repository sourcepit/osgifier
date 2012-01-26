/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.PackageExport;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.utils.collections.CollectionUtils;
import org.sourcepit.common.utils.collections.ValueLookup;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.model.java.JavaPackage;
import org.sourcepit.osgify.core.model.java.JavaPackageBundle;
import org.sourcepit.osgify.core.model.java.JavaPackageRoot;
import org.sourcepit.osgify.core.util.OsgifyContextUtils;
import org.sourcepit.osgify.maven.context.OsgifyContextBuilder;

public abstract class AbstractOsgifyMojo extends AbstractInjectedMojo
{
   private final static ValueLookup<JavaPackageRoot, BundleManifest> BUNDLE_MANIFEST_LOOKUP = new ValueLookup<JavaPackageRoot, BundleManifest>()
   {
      public BundleManifest lookup(JavaPackageRoot javaPackageRoot)
      {
         return javaPackageRoot.getExtension(BundleManifest.class);
      }
   };

   /** @component */
   private OsgifyContextBuilder builder;

   /** @parameter default-value="${localRepository}" */
   protected ArtifactRepository localRepository;

   /** @parameter default-value="${project.build.directory}" */
   private File targetDir;

   /** @parameter default-value="${project}" */
   private MavenProject project;
   
   protected void doExecute(Goal goal)
   {
      final OsgifyContext context = builder.build(project, goal, localRepository);

      XMLResourceImpl resource = new XMLResourceImpl();
      resource.getContents().add(context);

      OutputStream out = null;
      try
      {
         out = newOutStream(goal);
         resource.save(out, null);
      }
      catch (IOException e)
      {
         throw new IllegalStateException(e);
      }
      finally
      {
         IOUtils.closeQuietly(out);
      }

      deriveBundleManifest(context);
   }

   private void deriveBundleManifest(OsgifyContext context)
   {
      final List<BundleCandidate> bundleCandidates = OsgifyContextUtils.computeBuildOrder(context);
      for (BundleCandidate bundleCandidate : bundleCandidates)
      {
         deriveBundleManifest(bundleCandidate);
      }
   }

   private BundleManifest deriveBundleManifest(BundleCandidate bundleCandidate)
   {
      final JavaPackageBundle jContents = bundleCandidate.getContent();
      BundleManifest bundleMF = CollectionUtils.getValue(jContents.getPackageRoots(), BUNDLE_MANIFEST_LOOKUP);
      if (bundleMF == null)
      {
         // [package export]
         // collect contained packages (with type or !resource! in it)
         // resolve package versions
         // filter / align packages

         bundleMF = BundleManifestFactory.eINSTANCE.createBundleManifest();
         bundleMF.setBundleSymbolicName(bundleCandidate.getSymbolicName());
         bundleMF.setBundleVersion(bundleCandidate.getVersion());

         final Map<String, List<JavaPackage>> nameToJPackagesMap = new HashMap<String, List<JavaPackage>>();
         collectPackagesToExport(jContents, nameToJPackagesMap);

         final Map<String, Version> nameToVersionMap = new HashMap<String, Version>();
         resolvePackageExportVersions(nameToJPackagesMap, nameToVersionMap);

         for (Entry<String, Version> entry : nameToVersionMap.entrySet())
         {
            final String packageName = entry.getKey();

            Version version = entry.getValue();
            if (version == null)
            {
               version = bundleCandidate.getVersion();
            }

            final PackageExport packageExport = BundleManifestFactory.eINSTANCE.createPackageExport();
            packageExport.getPackageNames().add(packageName);
            if (version != null)
            {
               packageExport.setVersion(version);
            }

            bundleMF.getExportPackage(true).add(packageExport);
         }


         // [package import]
      }
      return bundleMF;
   }

   private static void resolvePackageExportVersions(final Map<String, List<JavaPackage>> nameToJPackagesMap,
      final Map<String, Version> nameToVersionMap)
   {
      for (Entry<String, List<JavaPackage>> entry : nameToJPackagesMap.entrySet())
      {
         String fully = entry.getKey();

         List<JavaPackage> jPackages = entry.getValue();
         Version version = CollectionUtils.getValue(jPackages, new ValueLookup<JavaPackage, Version>()
         {
            public Version lookup(JavaPackage element)
            {
               final String versionString = element.getAnnotationData("packageinfo", "version");
               if (versionString != null)
               {
                  try
                  {
                     return Version.parse(versionString);
                  }
                  catch (IllegalArgumentException e)
                  { // TODO log
                  }
               }
               return null;
            }
         });

         nameToVersionMap.put(fully, version);
      }
   }

   private static void collectPackagesToExport(final JavaPackageBundle jContents,
      Map<String, List<JavaPackage>> nameToJPackagesMap)
   {
      EList<JavaPackageRoot> packageRoots = jContents.getPackageRoots();
      for (JavaPackageRoot packageRoot : packageRoots)
      {
         EList<JavaPackage> packages = packageRoot.getRootPackages();
         for (JavaPackage jPackage : packages)
         {
            collectPackagesToExport(nameToJPackagesMap, jPackage);
         }
      }
   }

   private static void collectPackagesToExport(Map<String, List<JavaPackage>> nameToJPackagesMap, JavaPackage jPackage)
   {
      if (!jPackage.getTypeRoots().isEmpty()) // TODO consider resources besides type roots to0!
      {
         final String fully = jPackage.getFullyQualifiedName();

         List<JavaPackage> jPackages = nameToJPackagesMap.get(fully);
         if (jPackages == null)
         {
            jPackages = new ArrayList<JavaPackage>();
            nameToJPackagesMap.put(fully, jPackages);
         }
         jPackages.add(jPackage);
      }
      for (JavaPackage javaPackage : jPackage.getPackages())
      {
         collectPackagesToExport(nameToJPackagesMap, javaPackage);
      }
   }

   private OutputStream newOutStream(Goal goal) throws IOException
   {
      File file = new File(targetDir, goal == Goal.OSGIFY ? "osgify-context.xml" : "osgify-tests-context.xml");
      if (file.exists())
      {
         file.delete();
      }
      file.getParentFile().mkdirs();
      file.createNewFile();
      return new FileOutputStream(file);
   }
}
