/*
 * Copyright (C) 2012 Bosch Software Innovations GmbH. All rights reserved.
 */

package org.sourcepit.osgify.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;


/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class AbstractOsgifyMojo extends AbstractMojo
{
   public final void execute() throws MojoExecutionException, MojoFailureException
   {
      doExecute();
   }

   protected abstract void doExecute() throws MojoExecutionException, MojoFailureException;
}
