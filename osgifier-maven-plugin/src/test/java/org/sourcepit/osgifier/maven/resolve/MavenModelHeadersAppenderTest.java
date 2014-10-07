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

package org.sourcepit.osgifier.maven.resolve;

import static org.junit.Assert.*;

import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.eclipse.emf.common.util.EList;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleLicense;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;

public class MavenModelHeadersAppenderTest
{

   @Test
   public void testAppend() throws Exception
   {
      final BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();

      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      new MavenModelHeadersAppender().append(bundle, null, null);
      assertNull(bundle.getManifest().getBundleLicense());

      final Model model = new Model();
      model.setVersion("4.0.0");

      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      bundle.setAnnotationData("maven", "model", new ModelToString().toString(model));
      new MavenModelHeadersAppender().append(bundle, null, null);

      assertNull(bundle.getManifest().getBundleLicense());


      License lic = new License();
      lic.setName("Apache License, Version 2.0");
      lic.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
      model.getLicenses().add(lic);

      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      bundle.setAnnotationData("maven", "model", new ModelToString().toString(model));
      new MavenModelHeadersAppender().append(bundle, null, null);

      EList<BundleLicense> bundleLicense = bundle.getManifest().getBundleLicense();
      assertEquals(1, bundleLicense.size());

      BundleLicense bundleLic = bundleLicense.get(0);
      assertEquals("Apache License Version 2.0", bundleLic.getName()); // ',' is an invalid char for bundle lic names
      assertEquals("http://www.apache.org/licenses/LICENSE-2.0", bundleLic.getLink());
      assertNull(bundleLic.getDescription());


      lic = new License();
      lic.setName("Eclipse Public License - v 1.0");
      lic.setUrl("https://www.eclipse.org/legal/epl-v10.html");
      model.getLicenses().add(lic);

      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());
      bundle.setAnnotationData("maven", "model", new ModelToString().toString(model));
      new MavenModelHeadersAppender().append(bundle, null, null);

      bundleLicense = bundle.getManifest().getBundleLicense();
      assertEquals(2, bundleLicense.size());

      bundleLic = bundleLicense.get(0);
      assertEquals("Apache License Version 2.0", bundleLic.getName()); // ',' is an invalid char for bundle lic names
      assertEquals("http://www.apache.org/licenses/LICENSE-2.0", bundleLic.getLink());
      assertNull(bundleLic.getDescription());

      bundleLic = bundleLicense.get(1);
      assertEquals("Eclipse Public License - v 1.0", bundleLic.getName());
      assertEquals("https://www.eclipse.org/legal/epl-v10.html", bundleLic.getLink());
      assertNull(bundleLic.getDescription());
   }

   @Test
   public void testToBundleLicense()
   {
      License lic = new License();
      BundleLicense bundleLic = MavenModelHeadersAppender.toBundleLicense(lic);
      assertNull(bundleLic);

      lic = new License();
      lic.setName("Apache License, Version 2.0");
      bundleLic = MavenModelHeadersAppender.toBundleLicense(lic);
      assertEquals("Apache License Version 2.0", bundleLic.getName()); // ',' is an invalid char for bundle lic names
      assertNull(bundleLic.getLink());
      assertNull(bundleLic.getDescription());

      lic = new License();
      lic.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
      bundleLic = MavenModelHeadersAppender.toBundleLicense(lic);
      assertEquals("http://www.apache.org/licenses/LICENSE-2.0", bundleLic.getName());
      assertNull(bundleLic.getLink());
      assertNull(bundleLic.getDescription());

      lic = new License();
      lic.setName("Apache License, Version 2.0");
      lic.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
      bundleLic = MavenModelHeadersAppender.toBundleLicense(lic);
      assertEquals("Apache License Version 2.0", bundleLic.getName()); // ',' is an invalid char for bundle lic names
      assertEquals("http://www.apache.org/licenses/LICENSE-2.0", bundleLic.getLink());
      assertNull(bundleLic.getDescription());

      lic = new License();
      lic.setName(";");
      lic.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
      bundleLic = MavenModelHeadersAppender.toBundleLicense(lic);
      assertEquals("http://www.apache.org/licenses/LICENSE-2.0", bundleLic.getName());
      assertNull(bundleLic.getLink());
      assertNull(bundleLic.getDescription());

      lic = new License();
      lic.setName(";");
      lic.setUrl(";");
      bundleLic = MavenModelHeadersAppender.toBundleLicense(lic);
      assertNull(bundleLic);
   }

}
