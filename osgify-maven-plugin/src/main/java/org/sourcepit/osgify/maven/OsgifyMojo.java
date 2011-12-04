/**
 * Copyright (c) 2011 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.repository.RepositorySystem;

/**
 * @requiresDependencyResolution compile
 * @goal osgify
 * @phase compile
 * @author bernd
 */
public class OsgifyMojo extends AbstractMojo
{
   /** @component */
   private RepositorySystem repositorySystem;

   public void execute() throws MojoExecutionException, MojoFailureException
   {
      System.out.println();
   }
}
