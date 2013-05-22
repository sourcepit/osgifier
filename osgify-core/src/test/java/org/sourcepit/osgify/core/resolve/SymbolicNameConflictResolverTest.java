/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.resolve;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.ContextModelFactory;

public class SymbolicNameConflictResolverTest
{
   private SymbolicNameConflictResolver conflictResolver;

   private BundleCandidate bundle1;
   private BundleCandidate bundle2;

   @Before
   public void setUp()
   {
      conflictResolver = new SymbolicNameConflictResolver();

      ContextModelFactory contextFactory = ContextModelFactory.eINSTANCE;
      BundleManifestFactory manifestFactory = BundleManifestFactory.eINSTANCE;

      bundle1 = contextFactory.createBundleCandidate();
      bundle1.setManifest(manifestFactory.createBundleManifest());
      bundle2 = contextFactory.createBundleCandidate();
      bundle2.setManifest(manifestFactory.createBundleManifest());
   }

   @Test
   public void testResolveParallel()
   {
      List<String> names1 = asList("foo", "bar1");
      List<String> names2 = asList("foo", "bar2");

      assertTrue(conflictResolver.resolveNameConflict(bundle1, names1, bundle2, names2));
      assertEquals("bar1", bundle1.getSymbolicName());
      assertEquals("bar1", bundle1.getManifest().getBundleSymbolicName().getSymbolicName());
      assertEquals("bar2", bundle2.getSymbolicName());
      assertEquals("bar2", bundle2.getManifest().getBundleSymbolicName().getSymbolicName());
   }

   @Test
   public void testAsync()
   {
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
   public void testUnresolveable()
   {
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
