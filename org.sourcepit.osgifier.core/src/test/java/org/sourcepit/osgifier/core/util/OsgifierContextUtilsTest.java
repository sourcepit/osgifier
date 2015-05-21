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

package org.sourcepit.osgifier.core.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleReference;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.util.OsgifierContextUtils.BuildOrder;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class OsgifierContextUtilsTest {
   @Test
   public void testComputeBuildOrder() {
      try {
         OsgifierContextUtils.computeBuildOrder(null);
         fail();
      }
      catch (IllegalArgumentException e) {
      }

      // empty
      OsgifierContext ctx = ContextModelFactory.eINSTANCE.createOsgifierContext();
      assertThat(OsgifierContextUtils.computeBuildOrder(ctx), IsNull.notNullValue());

      // simple a -> b -> c
      BundleCandidate a = addBundleCandidate(ctx, "A", "1");
      BundleCandidate b = addBundleCandidate(ctx, "B", "1");
      BundleCandidate c = addBundleCandidate(ctx, "C", "1");

      addBundleReference(a, b);
      addBundleReference(b, c);

      List<BundleCandidate> orderedBundles = OsgifierContextUtils.computeBuildOrder(ctx).getOrderedBundles();
      assertThat(orderedBundles.get(0), IsEqual.equalTo(c));
      assertThat(orderedBundles.get(1), IsEqual.equalTo(b));
      assertThat(orderedBundles.get(2), IsEqual.equalTo(a));

      // tree a -> b -> c -> d and b -> d
      BundleCandidate d = addBundleCandidate(ctx, "D", "1");

      addBundleReference(c, d);
      addBundleReference(b, d);

      orderedBundles = OsgifierContextUtils.computeBuildOrder(ctx).getOrderedBundles();
      assertThat(orderedBundles.get(0), IsEqual.equalTo(d));
      assertThat(orderedBundles.get(1), IsEqual.equalTo(c));
      assertThat(orderedBundles.get(2), IsEqual.equalTo(b));
      assertThat(orderedBundles.get(3), IsEqual.equalTo(a));

      // recursion a -> b -> c -> a
      addBundleReference(c, a);

      BuildOrder buildOrder = OsgifierContextUtils.computeBuildOrder(ctx);
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

   private BundleReference addBundleReference(BundleCandidate from, BundleCandidate to) {
      final BundleReference ref = ContextModelFactory.eINSTANCE.createBundleReference();
      ref.setTarget(to);
      ref.setVersionRange(VersionRange.parse(to.getVersion().toMinimalString()));
      from.getDependencies().add(ref);
      return ref;
   }

   private BundleCandidate addBundleCandidate(OsgifierContext ctx, String symbolicName, String version) {
      final BundleCandidate bc = ContextModelFactory.eINSTANCE.createBundleCandidate();
      bc.setSymbolicName(symbolicName);
      bc.setVersion(Version.parse(version));
      ctx.getBundles().add(bc);
      return bc;
   }
}
