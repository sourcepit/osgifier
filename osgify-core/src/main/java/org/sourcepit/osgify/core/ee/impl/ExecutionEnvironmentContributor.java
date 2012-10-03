/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee.impl;

import java.util.List;

import org.sourcepit.osgify.core.ee.ExecutionEnvironment;
import org.sourcepit.osgify.core.ee.ExecutionEnvironmentImplementation;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public interface ExecutionEnvironmentContributor
{
   List<ExecutionEnvironment> getExecutionEnvironments();

   List<ExecutionEnvironmentImplementation> getExecutionEnvironmentImplementations();
}
