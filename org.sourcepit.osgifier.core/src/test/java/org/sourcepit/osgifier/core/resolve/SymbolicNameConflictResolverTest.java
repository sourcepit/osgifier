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

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.ContextModelFactory;

public class SymbolicNameConflictResolverTest {
   private SymbolicNameConflictResolver conflictResolver;

   private BundleCandidate bundle1;
   private BundleCandidate bundle2;

   @Before
   public void setUp() {
      conflictResolver = new SymbolicNameConflictResolver();

      ContextModelFactory contextFactory = ContextModelFactory.eINSTANCE;
      BundleManifestFactory manifestFactory = BundleManifestFactory.eINSTANCE;

      bundle1 = contextFactory.createBundleCandidate();
      bundle1.setManifest(manifestFactory.createBundleManifest());
      bundle2 = contextFactory.createBundleCandidate();
      bundle2.setManifest(manifestFactory.createBundleManifest());
   }

   @Test
   public void testResolveParallel() {
      List<String> names1 = asList("foo", "bar1");
      List<String> names2 = asList("foo", "bar2");

      assertTrue(conflictResolver.resolveNameConflict(bundle1, names1, bundle2, names2));
      assertEquals("bar1", bundle1.getSymbolicName());
      assertEquals("bar1", bundle1.getManifest().getBundleSymbolicName().getSymbolicName());
      assertEquals("bar2", bundle2.getSymbolicName());
      assertEquals("bar2", bundle2.getManifest().getBundleSymbolicName().getSymbolicName());
   }

   @Test
   public void testAsync() {
      List<String> names1 = asList("foo", "bar1");
      List<String> names2 = asList("foo");

      assertTrue(conflictResolver.resolveNameConflict(bundle1, names1, bundle2, names2));
      assertEquals("bar1", bundle1.getSymbolicName());
      assertEquals("bar1", bundle1.getManifest().getBundleSymbolicName().getSymbolicName());
      assertEquals("foo", bundle2.getSymbolicName());
      assertEquals("foo", bundle2.getManifest().getBundleSymbolicName().getSymbolicName());

      names1 = asList("foo", "foo2", "bar1");
      names2 = asList("foo", "foo2");

      // left is dominant
      assertTrue(conflictResolver.resolveNameConflict(bundle1, names1, bundle2, names2));
      assertEquals("foo", bundle1.getSymbolicName());
      assertEquals("foo", bundle1.getManifest().getBundleSymbolicName().getSymbolicName());
      assertEquals("foo2", bundle2.getSymbolicName());
      assertEquals("foo2", bundle2.getManifest().getBundleSymbolicName().getSymbolicName());
   }

   @Test
   public void testUnresolveable() {
      List<String> names1 = asList("foo");
      List<String> names2 = asList("foo");

      assertFalse(conflictResolver.resolveNameConflict(bundle1, names1, bundle2, names2));

      names1 = asList("foo");
      names2 = asList();

      assertFalse(conflictResolver.resolveNameConflict(bundle1, names1, bundle2, names2));

      names1 = asList("foo", "bar");
      names2 = asList();

      assertFalse(conflictResolver.resolveNameConflict(bundle1, names1, bundle2, names2));
   }
}
