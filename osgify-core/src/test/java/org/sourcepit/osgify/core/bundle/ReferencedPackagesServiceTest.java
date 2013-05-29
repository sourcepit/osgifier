/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.junit.Assert.fail;
import static org.sourcepit.osgify.core.bundle.TestContextHelper.appendTypeWithReferences;

import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.sonatype.guice.bean.containers.InjectedTest;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.java.JavaArchive;
import org.sourcepit.osgify.core.model.java.JavaModelFactory;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ReferencedPackagesServiceTest extends InjectedTest
{
   @Inject
   private ReferencedPackagesService packagesService;

   @Test
   public void testUnmodifiable()
   {
      BundleCandidate bundle = ContextModelFactory.eINSTANCE.createBundleCandidate();
      
      JavaArchive jArchive = JavaModelFactory.eINSTANCE.createJavaArchive();
      appendTypeWithReferences(jArchive, "foo.Bar", 47, "java.lang.Object", "hans.Wurst");
      
      bundle.setContent(jArchive);

      Set<String> referencedPackages = packagesService.getNamesOfReferencedPackages(bundle);
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
