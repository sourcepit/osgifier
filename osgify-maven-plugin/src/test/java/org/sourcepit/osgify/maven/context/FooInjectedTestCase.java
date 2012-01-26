/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import javax.inject.Inject;

import org.sonatype.guice.bean.containers.InjectedTestCase;
import org.sourcepit.osgify.maven.IFoo;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class FooInjectedTestCase extends InjectedTestCase
{
   @Inject
   private IFoo goo;

   public void testFoo() throws Exception
   {
      // IFoo lookup = lookup(IFoo.class);

      System.out.println(goo);
   }
}
