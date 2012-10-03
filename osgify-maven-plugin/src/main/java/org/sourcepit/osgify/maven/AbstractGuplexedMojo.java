/*
 * Copyright (C) 2012 Bosch Software Innovations GmbH. All rights reserved.
 */

package org.sourcepit.osgify.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.sourcepit.guplex.Guplex;


/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class AbstractGuplexedMojo extends AbstractMojo
{
   /** @component */
   private Guplex guplex;

   public final void execute() throws MojoExecutionException, MojoFailureException
   {
      guplex.inject(this, true);
      doExecute();
   }

   protected abstract void doExecute() throws MojoExecutionException, MojoFailureException;
}
