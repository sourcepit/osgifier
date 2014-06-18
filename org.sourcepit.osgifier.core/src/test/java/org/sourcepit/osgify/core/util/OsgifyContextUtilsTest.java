/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import java.lang.IllegalArgumentException;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.core.util.OsgifyContextUtils.BuildOrder;

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
      catch (IllegalArgumentException e)
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

      List<BundleCandidate> orderedBundles = OsgifyContextUtils.computeBuildOrder(ctx).getOrderedBundles();
      assertThat(orderedBundles.get(0), IsEqual.equalTo(c));
      assertThat(orderedBundles.get(1), IsEqual.equalTo(b));
      assertThat(orderedBundles.get(2), IsEqual.equalTo(a));

      // tree a -> b -> c -> d and b -> d
      BundleCandidate d = addBundleCandidate(ctx, "D", "1");

      addBundleReference(c, d);
      addBundleReference(b, d);

      orderedBundles = OsgifyContextUtils.computeBuildOrder(ctx).getOrderedBundles();
      assertThat(orderedBundles.get(0), IsEqual.equalTo(d));
      assertThat(orderedBundles.get(1), IsEqual.equalTo(c));
      assertThat(orderedBundles.get(2), IsEqual.equalTo(b));
      assertThat(orderedBundles.get(3), IsEqual.equalTo(a));

      // recursion a -> b -> c -> a
      addBundleReference(c, a);

      BuildOrder buildOrder = OsgifyContextUtils.computeBuildOrder(ctx);
      orderedBundles = buildOrder.getOrderedBundles();
      assertThat(orderedBundles.get(1), IsEqual.equalTo(c));
      assertThat(orderedBundles.get(2), IsEqual.equalTo(b));
      assertThat(orderedBundles.get(3), IsEqual.equalTo(a));

      List<List<BundleCandidate>> cycles = buildOrder.getCycles();
      assertEquals(1, cycles.size());
      
      List<BundleCandidate> path = cycles.get(0);
      assertEquals(4, path.size());
      assertThat(path.get(0), IsEqual.equalTo(a));
      assertThat(path.get(1), IsEqual.equalTo(b));
      assertThat(path.get(2), IsEqual.equalTo(c));
      assertThat(path.get(3), IsEqual.equalTo(a));
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
