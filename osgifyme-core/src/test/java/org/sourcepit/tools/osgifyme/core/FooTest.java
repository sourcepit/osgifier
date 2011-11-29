/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.tools.osgifyme.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.bundletree.BundleNode;
import org.sourcepit.osgify.bundletree.BundleTree;
import org.sourcepit.osgify.bundletree.BundleTreeModelFactory;
import org.sourcepit.osgify.bundletree.RootBundleNode;
import org.sourcepit.osgify.java.JavaArchive;
import org.sourcepit.osgify.java.JavaPackageBundle;
import org.sourcepit.osgify.java.JavaProject;
import org.sourcepit.tools.osgifyme.core.java.inspect.JavaPackageBundleScanner;
import org.sourcepit.tools.osgifyme.core.java.inspect.JavaTypeReferencesAnalyzer;

/**
 * @author Bernd
 */
public class FooTest
{
   // osgify-test-0.1.0-SNAPSHOT.jar
   // org.osgi.core-4.3.0.jar optional
   // stax-api-1.0.1.jar provided
   // aspectjrt-1.6.12.jar [1.6,1.7)
   // hamcrest-core-1.2.jar
   // junit-4.10.jar test
   // hamcrest-core-1.1.jar test disables

   @Test
   public void testFoo() throws Exception, IOException
   {
      BundleTree model = newJavaProjectModel();
      print(model);
   }

   protected void print(EObject eObject) throws IOException, UnsupportedEncodingException
   {
      XMLResourceImpl resource = new XMLResourceImpl();
      resource.getContents().add(eObject);
      // ByteArrayOutputStream out = new ByteArrayOutputStream();

      File file = new File("target/javamodel.xml");
      file.createNewFile();

      FileOutputStream out = new FileOutputStream(file);
      resource.save(out, null);
      out.close();
      // System.out.println(new String(out.toByteArray(), "UTF-8"));
   }

   protected BundleTree newJavaProjectModel()
   {
      JavaPackageBundleScanner scanner = new JavaPackageBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());

      JavaProject project = scanner.scan(new File("projects/osgify-test"), "target/classes", "target/test-classes");
      project.setVersion("0.1.0-SNAPSHOT");

      BundleTree tree = BundleTreeModelFactory.eINSTANCE.createBundleTree();

      RootBundleNode root = BundleTreeModelFactory.eINSTANCE.createRootBundleNode();
      root.setVersion(Version.parse("0.1.0.SNAPSHOT"));
      tree.getNodes().add(root);
      tree.getBundles().add(project);

      addDependencies(root, scanner, project);
      return tree;
   }

   protected BundleTree newJavaArchiveModel()
   {
      JavaPackageBundleScanner scanner = new JavaPackageBundleScanner();
      scanner.setJavaTypeAnalyzer(new JavaTypeReferencesAnalyzer());

      JavaArchive archive = scan(scanner, "/lib/osgify-test-0.1.0-SNAPSHOT.jar", "0.1.0-SNAPSHOT");

      BundleTree tree = BundleTreeModelFactory.eINSTANCE.createBundleTree();

      RootBundleNode root = BundleTreeModelFactory.eINSTANCE.createRootBundleNode();
      root.setVersion(Version.parse("0.1.0.SNAPSHOT"));
      tree.getNodes().add(root);
      tree.getBundles().add(archive);

      addDependencies(root, scanner, archive);
      return tree;
   }

   protected void addDependencies(RootBundleNode model, JavaPackageBundleScanner scanner, JavaPackageBundle bundle)
   {
      BundleTree tree = (BundleTree) model.eContainer();

      BundleNode node = BundleTreeModelFactory.eINSTANCE.createBundleNode();
      node.setVersionRange(VersionRange.parse("4.3.0"));
      node.setVersion(Version.parse("4.3.0"));
      node.setEnabled(true);
      node.setOptional(true);
      node.setTarget(scan(scanner, "/lib/org.osgi.core-4.3.0.jar", "4.3.0"));
      model.getNodes().add(node);
      tree.getBundles().add(node.getTarget());

      node = BundleTreeModelFactory.eINSTANCE.createBundleNode();
      node.setVersionRange(VersionRange.parse("1.0.1"));
      node.setVersion(Version.parse("1.0.1"));
      node.setEnabled(true);
      node.setScope("provided");
      node.setTarget(scan(scanner, "/lib/stax-api-1.0.1.jar", "1.0.1"));
      model.getNodes().add(node);
      tree.getBundles().add(node.getTarget());

      node = BundleTreeModelFactory.eINSTANCE.createBundleNode();
      node.setVersionRange(VersionRange.parse("[1.6,1.7)"));
      node.setVersion(Version.parse("1.6.12"));
      node.setEnabled(true);
      node.setTarget(scan(scanner, "/lib/aspectjrt-1.6.12.jar", "1.6.12"));
      model.getNodes().add(node);
      tree.getBundles().add(node.getTarget());

      node = BundleTreeModelFactory.eINSTANCE.createBundleNode();
      node.setVersionRange(VersionRange.parse("1.2"));
      node.setVersion(Version.parse("1.2"));
      node.setEnabled(true);
      node.setTarget(scan(scanner, "/lib/hamcrest-core-1.2.jar", "1.2"));
      model.getNodes().add(node);
      tree.getBundles().add(node.getTarget());

      node = BundleTreeModelFactory.eINSTANCE.createBundleNode();
      node.setVersionRange(VersionRange.parse("4.10"));
      node.setVersion(Version.parse("4.10"));
      node.setEnabled(true);
      node.setScope("test");
      node.setTarget(scan(scanner, "/lib/junit-4.10.jar", "4.10"));
      model.getNodes().add(node);
      tree.getBundles().add(node.getTarget());

      node.getNodes().add(BundleTreeModelFactory.eINSTANCE.createBundleNode());

      node = node.getNodes().get(0);
      node.setVersionRange(VersionRange.parse("1.1"));
      node.setVersion(Version.parse("1.1"));
      node.setEnabled(true);
      node.setScope("test");
      node.setTarget(scan(scanner, "/lib/hamcrest-core-1.1.jar", "1.1"));
      tree.getBundles().add(node.getTarget());
   }

   protected JavaArchive scan(JavaPackageBundleScanner scanner, String name, String version)
   {
      final InputStream in = getClass().getResourceAsStream(name);
      try
      {
         JavaArchive archive = scanner.scan(in);
         archive.setVersion(version);
         return archive;
      }
      finally
      {
         IOUtils.closeQuietly(in);
      }
   }
}
