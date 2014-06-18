/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
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
