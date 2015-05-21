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

package org.sourcepit.osgifier.maven.p2;

import javax.inject.Inject;
import javax.inject.Named;

import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.eclipse.sisu.equinox.launching.internal.P2ApplicationLauncher;
import org.sourcepit.common.utils.lang.Exceptions;
import org.sourcepit.osgifier.p2.P2ApplicationLauncherFactory;

@Named
public class PlexusP2ApplicationLauncherFactory implements P2ApplicationLauncherFactory {
   @Inject
   private PlexusContainer plexus;

   public P2ApplicationLauncher createP2ApplicationLauncher() {
      final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
      try {
         Thread.currentThread().setContextClassLoader(P2ApplicationLauncher.class.getClassLoader());

         return plexus.lookup(P2ApplicationLauncher.class);
      }
      catch (ComponentLookupException e) {
         throw Exceptions.pipe(e);
      }
      finally {
         Thread.currentThread().setContextClassLoader(ccl);
      }
   }

}
