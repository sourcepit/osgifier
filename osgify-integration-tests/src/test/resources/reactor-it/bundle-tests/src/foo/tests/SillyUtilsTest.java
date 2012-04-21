/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package foo.tests;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import foo.SillyUtils;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class SillyUtilsTest
{

   @Test
   public void testPing()
   {
      assertThat(SillyUtils.ping(), IsEqual.equalTo("pong"));
   }

}
