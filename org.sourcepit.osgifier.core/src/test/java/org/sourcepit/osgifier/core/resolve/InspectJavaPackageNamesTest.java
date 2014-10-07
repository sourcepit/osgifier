/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.core.resolve;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.java.JavaArchive;
import org.sourcepit.osgifier.core.model.java.JavaModelFactory;
import org.sourcepit.osgifier.core.resolve.InspectJavaPackageNames;

/**
 * @author Bernd
 */
public class InspectJavaPackageNamesTest
{

   @Test
   public void testJaxmeApi() throws Exception
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      jArchive.getType("javax.xml", "XMLConstants", true);
      jArchive.getType("javax.xml.bind", "DatatypeConverter", true);
      jArchive.getType("javax.xml.bind", "DatatypeConverterInterface", true);
      jArchive.getType("javax.xml.bind", "TypeConstraintException", true);
      jArchive.getType("javax.xml.bind.helpers", "AbstractMarshallerImpl", true);
      jArchive.getType("javax.xml.bind.helpers", "AbstractUnmarshallerImpl", true);
      jArchive.getType("javax.xml.bind.util", "JAXBResult", true);
      jArchive.getType("javax.xml.bind.util", "JAXBSource", true);
      jArchive.getType("javax.xml.namespace", "NamespaceContext", true);
      jArchive.getType("javax.xml.namespace", "QName", true);

      String name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, equalTo("javax.xml.bind"));
   }

   @Test
   public void testJunit38() throws Exception
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      jArchive.getType("junit.awtui", "AboutDialog", true);
      jArchive.getType("junit.extensions", "ActiveTestSuite", true);
      jArchive.getType("junit.framework", "Assert", true);
      jArchive.getType("junit.runner", "BaseTestRunner", true);
      jArchive.getType("junit.swingui", "AboutDialog", true);
      jArchive.getPackage("junit.swingui.icons", true).getFile("ok.gif", true);
      jArchive.getType("junit.textui", "ResultPrinter", true);

      String name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, equalTo("junit"));
   }

   @Test
   public void testStaxApi() throws Exception
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);
      jArchive.getType("javax.xml", "XMLConstants", true);
      jArchive.getType("javax.xml.namespace", "NamespaceContext", true);
      jArchive.getType("javax.xml.namespace", "QName", true);
      jArchive.getType("javax.xml.stream", "EventFilter", true);
      jArchive.getType("javax.xml.stream", "FactoryConfigurationError", true);
      jArchive.getType("javax.xml.stream.events", "Attribute", true);
      jArchive.getType("javax.xml.stream.events", "Characters", true);
      jArchive.getType("javax.xml.stream.util", "EventReaderDelegate", true);

      String name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, equalTo("javax.xml.stream"));
   }

   @Test
   public void testPullParser() throws Exception
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("javax.xml.parsers", "DocumentBuilder", true);
      jArchive.getType("org.xml.sax", "AttributeList", true);
      jArchive.getType("org.xml.sax.ext", "DeclHandler", true);
      jArchive.getType("org.xml.sax.helpers", "AttributeListImpl", true);
      jArchive.getType("org.gjt.xpp", "XmlEndTag", true);
      jArchive.getType("org.gjt.xpp", "XmlFormatter", true);
      jArchive.getType("org.gjt.xpp.impl", "PullParserFactoryFullImpl", true);
      jArchive.getType("org.gjt.xpp.impl", "format.Formatter", true);
      jArchive.getType("org.gjt.xpp.impl.node", "EmptyEnumerator", true);
      jArchive.getType("org.gjt.xpp.impl.pullnode", "PullNode", true);
      jArchive.getType("org.gjt.xpp.impl.pullparser", "ElementContent", true);
      jArchive.getType("org.gjt.xpp.impl.tag", "Attribute", true);
      jArchive.getType("org.gjt.xpp.impl.tokenizer", "Tokenizer", true);
      jArchive.getType("org.gjt.xpp.jaxp11", "DefaultValidationErrorHandler", true);
      jArchive.getType("org.gjt.xpp.sax2", "Driver", true);

      String name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, equalTo("org.gjt.xpp"));
   }

   @Test
   public void testDom4j() throws Exception
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("org.dom4j", "Attribute", true);
      jArchive.getType("org.dom4j.bean", "BeanAttribute", true);
      jArchive.getType("org.dom4j.datatype", "DatatypeAttribute", true);
      jArchive.getType("org.dom4j.persistence", "DocumentMarshalling", true);
      jArchive.getType("org.dom4j.persistence.nativ", "XMLDBStrategy", true);

      String name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, equalTo("org.dom4j"));
   }

   @Test
   public void testXercesImpl() throws Exception
   {
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      BundleCandidate bundleCandidate = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundleCandidate.setContent(jArchive);

      jArchive.getType("org.apache.html.dom", "CollectionIndex", true);
      jArchive.getType("org.apache.wml", "WMLAccessElement", true);
      jArchive.getType("org.apache.wml.dom", "WMLAccessElementImpl", true);
      jArchive.getType("org.apache.xerces.dom", "ASDOMImplementationImpl", true);
      jArchive.getType("org.apache.xerces.dom.events", "EventImpl", true);
      jArchive.getType("org.apache.xerces.dom3", "DOMConfiguration", true);
      jArchive.getType("org.apache.xerces.dom3.as", "ASAttributeDeclaration", true);
      jArchive.getType("org.apache.xerces.dom3.bootstrap", "DOMImplementationListImpl", true);
      jArchive.getType("org.apache.xerces.impl", "Constants", true);
      jArchive.getType("org.apache.xerces.impl.dtd", "DTDGrammar", true);
      jArchive.getType("org.apache.xerces.impl.dtd.models", "CMAny", true);
      jArchive.getType("org.apache.xerces.impl.dv", "DatatypeException", true);
      jArchive.getType("org.apache.xml.serialize", "BaseMarkupSerializer", true);
      jArchive.getType("org.w3c.dom.html", "HTMLDOMImplementation", true);
      jArchive.getType("org.w3c.dom.ls", "DOMImplementationLS", true);

      String name = new InspectJavaPackageNames().resolveSymbolicName(bundleCandidate, null);
      assertThat(name, equalTo("org.apache.xerces"));
   }

}
