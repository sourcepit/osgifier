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

package org.sourcepit.osgifier.core.bundle;

import static org.junit.Assert.*;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.DYNAMICIMPORT_PACKAGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.manifest.osgi.DynamicPackageImport;
import org.sourcepit.osgifier.core.bundle.PackageDeclarationCombiner;

public class PackageDeclarationCombinerTest
{


   @Test
   public void testCombineDynamicPackageImports() throws Exception
   {
      assertEquals("*", combineDynamicPackageImports("*", "*"));
      assertEquals("*,*;vendor=acme", combineDynamicPackageImports("*", "*;vendor=acme"));
      assertEquals("a;vendor=acme,z", combineDynamicPackageImports("z", "a;vendor=acme"));
      assertEquals("bar;foo;vendor=\"acme\"", combineDynamicPackageImports("foo;vendor=\"acme\"", "bar;vendor=acme"));
      assertEquals("acme;bar;foo;vendor=acme", combineDynamicPackageImports("acme;vendor=acme", "foo;bar;vendor=acme"));
      assertEquals("acme;bar;foo;vendor=acme",
         combineDynamicPackageImports("acme;vendor=acme", "foo;vendor=acme,bar;vendor=acme"));
      assertEquals(
         "*;vendor=acme,foo.*;version=1.0.0",
         combineDynamicPackageImports("*;vendor=acme", "foo;vendor=acme,bar;vendor=acme", "foo.*;version=1.0.0",
            "foo.bar;version=1.0.0"));
   }

   private String combineDynamicPackageImports(String... dynImports)
   {
      List<DynamicPackageImport> all = new ArrayList<DynamicPackageImport>();
      for (String dynImport : dynImports)
      {
         BundleManifest mf = BundleManifestFactory.eINSTANCE.createBundleManifest();
         mf.setHeader(DYNAMICIMPORT_PACKAGE, dynImport);
         all.addAll(mf.getDynamicImportPackage());
      }

      List<DynamicPackageImport> merge = PackageDeclarationCombiner.combineDynamicPackageImports(all);

      BundleManifest actual = BundleManifestFactory.eINSTANCE.createBundleManifest();
      actual.getDynamicImportPackage(true).addAll(merge);

      return actual.getHeaderValue(DYNAMICIMPORT_PACKAGE);
   }

   @Test
   public void testMergeDynamicPackageImportPatterns() throws Exception
   {
      List<String> result = PackageDeclarationCombiner.mergeDynamicPackageImportPatterns(Arrays.asList("*", "foo"));
      assertEquals("[*]", result.toString());

      result = PackageDeclarationCombiner.mergeDynamicPackageImportPatterns(Arrays.asList("bar", "foo"));
      assertEquals("[bar, foo]", result.toString());

      result = PackageDeclarationCombiner.mergeDynamicPackageImportPatterns(Arrays
         .asList("bar.*", "foo.*", "bar.foo.*"));
      assertEquals("[bar.*, foo.*]", result.toString());

      result = PackageDeclarationCombiner.mergeDynamicPackageImportPatterns(Arrays.asList("bar.foo.acme", "bar.*",
         "foo.*", "bar.foo.*"));
      assertEquals("[bar.*, foo.*]", result.toString());

      result = PackageDeclarationCombiner.mergeDynamicPackageImportPatterns(Arrays.asList("bar", "bar.*"));
      assertEquals("[bar, bar.*]", result.toString());
   }

}
