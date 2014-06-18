/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.core.resolve;

import static org.junit.Assert.assertEquals;
import static org.sourcepit.osgifier.core.resolve.PackageCollector.firstWithTypesOrSubPackages;

import java.util.List;

import org.junit.Test;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaPackage;
import org.sourcepit.osgifier.core.resolve.PackageCollector;

public class PackageCollectorTest
{
   @Test
   public void testFirstWithTypes()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      jArchive.getType("org.sourcepit.foo", "Bar", true);
      jArchive.getType("org.lulu", "Util", true);

      List<JavaPackage> result = PackageCollector.firstWithTypes(jArchive, 1, 3);
      assertEquals(2, result.size());
      assertEquals("org.sourcepit.foo", result.get(0).getQualifiedName());
      assertEquals("org.lulu", result.get(1).getQualifiedName());

      result = PackageCollector.firstWithTypes(jArchive, 2, 3);
      assertEquals(2, result.size());
      assertEquals("org.sourcepit.foo", result.get(0).getQualifiedName());
      assertEquals("org.lulu", result.get(1).getQualifiedName());

      result = PackageCollector.firstWithTypes(jArchive, 3, 3);
      assertEquals(1, result.size());
      assertEquals("org.sourcepit.foo", result.get(0).getQualifiedName());

      result = PackageCollector.firstWithTypes(jArchive, 1, 2);
      assertEquals(1, result.size());
      assertEquals("org.lulu", result.get(0).getQualifiedName());
   }

   @Test
   public void testFirstWithTypesOrSubPackages()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("org.dom4j", "Attribute", true);
      jArchive.getType("org.dom4j.bean", "BeanAttribute", true);
      jArchive.getType("org.dom4j.datatype", "DatatypeAttribute", true);
      jArchive.getType("org.dom4j.persistence", "DocumentMarshalling", true);
      jArchive.getType("org.dom4j.persistence.nativ", "XMLDBStrategy", true);

      List<JavaPackage> result = firstWithTypesOrSubPackages(jArchive, 1, 3);
      assertEquals(1, result.size());
      assertEquals("org.dom4j", result.get(0).getQualifiedName());

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("org.sourcepit.foo", "Bar", true);
      jArchive.getType("org.lulu", "Util", true);

      result = firstWithTypesOrSubPackages(jArchive, 1, 3);
      assertEquals(1, result.size());
      assertEquals("org", result.get(0).getQualifiedName());

      result = firstWithTypesOrSubPackages(jArchive, 2, 3);
      assertEquals(2, result.size());
      assertEquals("org.sourcepit.foo", result.get(0).getQualifiedName());
      assertEquals("org.lulu", result.get(1).getQualifiedName());

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      jArchive.getType("junit.awtui", "AboutDialog", true);
      jArchive.getType("junit.extensions", "ActiveTestSuite", true);
      jArchive.getType("junit.framework", "Assert", true);
      jArchive.getType("junit.runner", "BaseTestRunner", true);
      jArchive.getType("junit.swingui", "AboutDialog", true);
      jArchive.getPackage("junit.swingui.icons", true).getFile("ok.gif", true);
      jArchive.getType("junit.textui", "ResultPrinter", true);

      result = firstWithTypesOrSubPackages(jArchive, 1, 3);
      assertEquals(1, result.size());
      assertEquals("junit", result.get(0).getQualifiedName());

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("javax.xml.parsers", "DocumentBuilder", true);
      jArchive.getType("org.xml.sax", "AttributeList", true);
      jArchive.getType("org.xml.sax.ext", "DeclHandler", true);
      jArchive.getType("org.xml.sax.helpers", "AttributeListImpl", true);
      jArchive.getType("org.gjt.xpp", "XmlEndTag", true);
      jArchive.getType("org.gjt.xpp.impl", "PullParserFactoryFullImpl", true);
      jArchive.getType("org.gjt.xpp.impl", "format.Formatter", true);
      jArchive.getType("org.gjt.xpp.impl.node", "EmptyEnumerator", true);
      jArchive.getType("org.gjt.xpp.impl.pullnode", "PullNode", true);
      jArchive.getType("org.gjt.xpp.impl.pullparser", "ElementContent", true);
      jArchive.getType("org.gjt.xpp.impl.tag", "Attribute", true);
      jArchive.getType("org.gjt.xpp.impl.tokenizer", "Tokenizer", true);
      jArchive.getType("org.gjt.xpp.jaxp11", "DefaultValidationErrorHandler", true);
      jArchive.getType("org.gjt.xpp.sax2", "Driver", true);

      result = firstWithTypesOrSubPackages(jArchive, 2, 3);
      assertEquals(3, result.size());
      assertEquals("javax.xml.parsers", result.get(0).getQualifiedName());
      assertEquals("org.xml.sax", result.get(1).getQualifiedName());
      assertEquals("org.gjt.xpp", result.get(2).getQualifiedName());
   }

   @Test
   public void testGetDepthOfFirstPackageWithTypes()
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("org.dom4j", "Attribute", true);
      jArchive.getType("org.dom4j.bean", "BeanAttribute", true);
      jArchive.getType("org.dom4j.datatype", "DatatypeAttribute", true);
      jArchive.getType("org.dom4j.persistence", "DocumentMarshalling", true);
      jArchive.getType("org.dom4j.persistence.nativ", "XMLDBStrategy", true);

      int depth = PackageCollector.getDepthOfFirstPackageWithTypes(jArchive, 1, 6);
      assertEquals(2, depth);

      jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("javax.xml.parsers", "DocumentBuilder", true);
      jArchive.getType("org.xml.sax", "AttributeList", true);
      jArchive.getType("org.xml.sax.ext", "DeclHandler", true);
      jArchive.getType("org.xml.sax.helpers", "AttributeListImpl", true);
      jArchive.getType("org.gjt.xpp", "XmlEndTag", true);
      jArchive.getType("org.gjt.xpp.impl", "PullParserFactoryFullImpl", true);
      jArchive.getType("org.gjt.xpp.impl", "format.Formatter", true);
      jArchive.getType("org.gjt.xpp.impl.node", "EmptyEnumerator", true);
      jArchive.getType("org.gjt.xpp.impl.pullnode", "PullNode", true);
      jArchive.getType("org.gjt.xpp.impl.pullparser", "ElementContent", true);
      jArchive.getType("org.gjt.xpp.impl.tag", "Attribute", true);
      jArchive.getType("org.gjt.xpp.impl.tokenizer", "Tokenizer", true);
      jArchive.getType("org.gjt.xpp.jaxp11", "DefaultValidationErrorHandler", true);
      jArchive.getType("org.gjt.xpp.sax2", "Driver", true);

      depth = PackageCollector.getDepthOfFirstPackageWithTypes(jArchive, 1, 6);
      assertEquals(3, depth);
   }

}
