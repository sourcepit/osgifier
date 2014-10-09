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

package org.sourcepit.osgifier.core.model.context;

import static org.junit.Assert.*;

import org.junit.Test;

public class BundleLocalizationTest
{

   @Test
   public void test()
   {
      BundleLocalization localization = ContextModelFactory.eINSTANCE.createBundleLocalization();
      assertEquals(localization.getData().size(), 0);

      localization.set("", "foo", null);
      assertEquals(localization.getData().size(), 0);
      assertEquals(null, localization.get("", "foo"));

      localization.set("", "foo", "bar");
      assertEquals(localization.getData().size(), 1);
      assertEquals("bar", localization.get("", "foo"));
      
      localization.set("", "bar", "foo");
      assertEquals(localization.getData().size(), 1);
      assertEquals("bar", localization.get("", "foo"));
      assertEquals("foo", localization.get("", "bar"));
      
      localization.set("", "bar", "bar");
      assertEquals(localization.getData().size(), 1);
      assertEquals("bar", localization.get("", "foo"));
      assertEquals("bar", localization.get("", "bar"));
      
      localization.set("de", "foo", "bar");
      assertEquals(localization.getData().size(), 2);
      assertEquals("bar", localization.get("", "foo"));
      assertEquals("bar", localization.get("", "bar"));
      assertEquals("bar", localization.get("de", "foo"));
   }

}
