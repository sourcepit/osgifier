/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.util;

import static org.junit.Assert.*;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class OsgifyContextUtilsTest
{
   @Test
   public void testComputeBuildOrder()
   {
      try
      {
         OsgifyContextUtils.computeBuildOrder(null);
         fail();
      }
      catch (ConstraintViolationException e)
      {
      }

      // empty
      OsgifyContext ctx = ContextModelFactory.eINSTANCE.createOsgifyContext();
      assertThat(OsgifyContextUtils.computeBuildOrder(ctx), IsNull.notNullValue());

      // simple a -> b -> c
      BundleCandidate a = addBundleCandidate(ctx, "A", "1");
      BundleCandidate b = addBundleCandidate(ctx, "B", "1");
      BundleCandidate c = addBundleCandidate(ctx, "C", "1");

      addBundleReference(a, b);
      addBundleReference(b, c);

      List<BundleCandidate> buildOrder = OsgifyContextUtils.computeBuildOrder(ctx);
      assertThat(buildOrder.get(0), IsEqual.equalTo(c));
      assertThat(buildOrder.get(1), IsEqual.equalTo(b));
      assertThat(buildOrder.get(2), IsEqual.equalTo(a));

      // tree a -> b -> c -> d and b -> d
      BundleCandidate d = addBundleCandidate(ctx, "D", "1");

      addBundleReference(c, d);
      addBundleReference(b, d);

      buildOrder = OsgifyContextUtils.computeBuildOrder(ctx);
      assertThat(buildOrder.get(0), IsEqual.equalTo(d));
      assertThat(buildOrder.get(1), IsEqual.equalTo(c));
      assertThat(buildOrder.get(2), IsEqual.equalTo(b));
      assertThat(buildOrder.get(3), IsEqual.equalTo(a));

      // recursion a -> b -> c -> a
      addBundleReference(c, a);

      try
      {
         OsgifyContextUtils.computeBuildOrder(ctx);
         fail();
      }
      catch (IllegalStateException e)
      {
      }
   }

   private BundleReference addBundleReference(BundleCandidate from, BundleCandidate to)
   {
      final BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setTarget(to);
      ref.setVersionRange(VersionRange.parse(to.getVersion().toMinimalString()));
      from.getDependencies().add(ref);
      return ref;
   }

   private BundleCandidate addBundleCandidate(OsgifyContext ctx, String symbolicName, String version)
   {
      final BundleCandidate bc = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bc.setSymbolicName(symbolicName);
      bc.setVersion(Version.parse(version));
      ctx.getBundles().add(bc);
      return bc;
   }
}
