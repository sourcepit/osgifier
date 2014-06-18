/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgifier.maven.p2;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.eclipse.sisu.equinox.launching.internal.P2ApplicationLauncher;
import org.sourcepit.common.utils.lang.Exceptions;
import org.sourcepit.osgifier.p2.P2ApplicationLauncherFactory;

@Named
public class PlexusP2ApplicationLauncherFactory implements P2ApplicationLauncherFactory
{
   @Inject
   private PlexusContainer plexus;

   public P2ApplicationLauncher createP2ApplicationLauncher()
   {
      final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
      try
      {
         Thread.currentThread().setContextClassLoader(P2ApplicationLauncher.class.getClassLoader());

         return plexus.lookup(P2ApplicationLauncher.class);
      }
      catch (ComponentLookupException e)
      {
         throw Exceptions.pipe(e);
      }
      finally
      {
         Thread.currentThread().setContextClassLoader(ccl);
      }
   }

}
