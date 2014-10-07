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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;
import org.sourcepit.common.manifest.osgi.VersionRange;
import org.sourcepit.osgifier.core.bundle.BundleUtils;

public class BundleUtilsTest
{
   @Test
   public void testTrimQualifierFromRange() throws Exception
   {
      VersionRange range;

      range = VersionRange.parse("0.0.0");
      assertThat(BundleUtils.trimQualifiers(range).toString(), equalTo("0.0.0"));

      range = VersionRange.parse("[0,1.0.0.rc4)");
      assertThat(BundleUtils.trimQualifiers(range).toString(), equalTo("[0,1)"));

      range = VersionRange.parse("[0.0.0.murks,1.0.0.rc4)");
      assertThat(BundleUtils.trimQualifiers(range).toString(), equalTo("[0,1)"));

      range = VersionRange.parse("[0.0.1.murks,0.0.1.rc4)");
      assertThat(BundleUtils.trimQualifiers(range).toString(), equalTo("[0.0.1,0.0.1)"));
   }
}
