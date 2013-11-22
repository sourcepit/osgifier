/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.bundle;

import static org.junit.Assert.fail;
import static org.sourcepit.modularizor.core.bundle.TestContextHelper.appendTypeWithReferences;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.sonatype.guice.bean.containers.InjectedTest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.core.model.context.ContextModelFactory;
import org.sourcepit.modularizor.java.JavaArchive;
import org.sourcepit.modularizor.java.JavaModelFactory;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class BundleRequirementsServiceTest extends InjectedTest
{
   @Inject
   private BundleRequirementsService packagesService;

   @Test
   public void testUnmodifiable()
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bundle.setManifest(BundleManifestFactory.eINSTANCE.createBundleManifest());

      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "foo.Bar", 47, "java.lang.Object", "hans.Wurst");

      bundle.setContent(jArchive);

      List<String> referencedPackages = packagesService.getRequiredPackages(bundle);
      try
      {
         referencedPackages.add("foo");
         fail();
      }
      catch (UnsupportedOperationException e)
      {
      }
   }

}
