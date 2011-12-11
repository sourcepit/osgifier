/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.context.BundleCandidate;
import org.sourcepit.osgify.context.BundleReference;
import org.sourcepit.osgify.context.ContextModelFactory;
import org.sourcepit.osgify.context.OsgifyContext;
import org.sourcepit.osgify.core.java.inspect.JavaPackageBundleScanner;
import org.sourcepit.osgify.core.java.inspect.JavaTypeReferencesAnalyzer;
import org.sourcepit.osgify.java.JavaArchive;
import org.sourcepit.osgify.java.JavaPackageBundle;

/**
 * @author Bernd
 */
public class FooTest
{
   // osgify-test-0.1.0-SNAPSHOT.jar (root)
   // -> org.osgi.core-4.3.0.jar (optional)
   // -> stax-api-1.0.1.jar (provided)
   // -> aspectjrt-1.6.12.jar [1.6,1.7)
   // -> hamcrest-core-1.2.jar
   // -> junit-4.10.jar (test)
   // ----> hamcrest-core-1.1.jar (test) (disables)

   @Test
   public void testFoo() throws Exception, IOException
   {
      OsgifyContext model = newJavaArchiveModel();
      print(model);
   }

   protected void print(EObject eObject) throws IOException, UnsupportedEncodingException
   {
      XMLResourceImpl resource = new XMLResourceImpl();
      resource.getContents().add(eObject);

      File file = new File("target/javamodel.xml");
      file.createNewFile();

      FileOutputStream out = new FileOutputStream(file);
      resource.save(out, null);
      out.close();
   }

   protected OsgifyContext newJavaArchiveModel()
   {
      // osgify-test-0.1.0-SNAPSHOT.jar (root)
      // -> org.osgi.core-4.3.0.jar (optional)
      // -> stax-api-1.0.1.jar (provided)
      // -> aspectjrt-1.6.12.jar [1.6,1.7)
      // -> hamcrest-core-1.2.jar
      // -> junit-4.10.jar (test)
      // ----> hamcrest-core-1.1.jar (test) (disables)
      final Map<String, JavaPackageBundle> nameToBundle = new LinkedHashMap<String, JavaPackageBundle>();
      nameToBundle.put("osgify-test-0.1.0-SNAPSHOT.jar", null);
      nameToBundle.put("org.osgi.core-4.3.0.jar", null);
      nameToBundle.put("stax-api-1.0.1.jar", null);
      nameToBundle.put("aspectjrt-1.6.12.jar", null);
      nameToBundle.put("hamcrest-core-1.2.jar", null);
      nameToBundle.put("junit-4.10.jar", null);
      nameToBundle.put("hamcrest-core-1.1.jar", null);

      OsgifyContext tree = ContextModelFactory.eINSTANCE.createOsgifyContext();
      for (Entry<String, JavaPackageBundle> entry : nameToBundle.entrySet())
      {
         JavaArchive bundle = scan("/lib/" + entry.getKey());
         nameToBundle.put(entry.getKey(), bundle);
      }

      BundleCandidate bundle;

      bundle = newRootBundleNode("0.1.0.SNAPSHOT");
      bundle.setContent(nameToBundle.get("osgify-test-0.1.0-SNAPSHOT.jar"));
      tree.getBundles().add(bundle);

      bundle = newRootBundleNode("4.3.0");
      bundle.setContent(nameToBundle.get("org.osgi.core-4.3.0.jar"));
      tree.getBundles().add(bundle);

      bundle = newRootBundleNode("1.0.1");
      bundle.setContent(nameToBundle.get("stax-api-1.0.1.jar"));
      tree.getBundles().add(bundle);

      bundle = newRootBundleNode("1.6.12");
      bundle.setContent(nameToBundle.get("aspectjrt-1.6.12.jar"));
      tree.getBundles().add(bundle);

      bundle = newRootBundleNode("1.2");
      bundle.setContent(nameToBundle.get("hamcrest-core-1.2.jar"));
      tree.getBundles().add(bundle);

      bundle = newRootBundleNode("4.10");
      bundle.setContent(nameToBundle.get("junit-4.10.jar"));
      tree.getBundles().add(bundle);

      bundle = newRootBundleNode("1.1");
      bundle.setContent(nameToBundle.get("hamcrest-core-1.1.jar"));
      tree.getBundles().add(bundle);

      BundleReference reference;

      // (0) osgify-test-0.1.0-SNAPSHOT.jar
      // (1) org.osgi.core-4.3.0.jar
      // (2) stax-api-1.0.1.jar
      // (3) aspectjrt-1.6.12.jar
      // (4) hamcrest-core-1.2.jar
      // (5) junit-4.10.jar
      // (6) hamcrest-core-1.1.jar
      bundle = tree.getBundles().get(0);

      reference = newBundleReference("4.3.0", true, false);
      reference.setTarget(tree.getBundles().get(1));
      bundle.getDependencies().add(reference);

      reference = newBundleReference("1.0.1", false, true);
      reference.setTarget(tree.getBundles().get(2));
      bundle.getDependencies().add(reference);

      reference = newBundleNode("[1.6,1.7)", "1.6.12", false, false);
      reference.setTarget(tree.getBundles().get(3));
      bundle.getDependencies().add(reference);

      reference = newBundleNode("1.2");
      reference.setTarget(tree.getBundles().get(4));
      bundle.getDependencies().add(reference);

      bundle = tree.getBundles().get(5);

      reference = newBundleNode("1.1");
      reference.setTarget(tree.getBundles().get(6));
      bundle.getDependencies().add(reference);

      return tree;
   }

   protected BundleCandidate newRootBundleNode(String version)
   {
      final BundleCandidate root = ContextModelFactory.eINSTANCE.createBundleCandidate();
      root.setVersion(Version.parse(version));
      return root;
   }

   protected BundleReference newBundleNode(String versionRange, String version, boolean optional, boolean provided)
   {
      final BundleReference node = ContextModelFactory.eINSTANCE.createBundleReference();
      node.setVersionRange(VersionRange.parse(versionRange));
      node.setOptional(optional);
      node.setProvided(provided);
      return node;
   }

   protected BundleReference newBundleReference(String version, boolean optional, boolean provided)
   {
      return newBundleNode(version, version, optional, provided);
   }

   protected BundleReference newBundleNode(String version)
   {
      return newBundleNode(version, version, false, false);
   }

   protected JavaArchive scan(String name)
   {
      JavaPackageBundleScanner scanner = new JavaPackageBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());

      final InputStream in = getClass().getResourceAsStream(name);
      try
      {
         return scanner.scan(in);
      }
      finally
      {
         IOUtils.closeQuietly(in);
      }
   }

   protected JavaArchive scan(JavaPackageBundleScanner scanner, String name)
   {
      final InputStream in = getClass().getResourceAsStream(name);
      try
      {
         return scanner.scan(in);
      }
      finally
      {
         IOUtils.closeQuietly(in);
      }
   }
}
