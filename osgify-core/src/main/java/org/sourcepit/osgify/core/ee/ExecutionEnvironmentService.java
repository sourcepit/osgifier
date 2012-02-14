/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee;

import java.util.Collection;
import java.util.List;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public interface ExecutionEnvironmentService
{
   ExecutionEnvironment getExecutionEnvironment(String executionEnvironmentId);

   List<ExecutionEnvironment> getExecutionEnvironments();

   List<ExecutionEnvironmentImplementation> getExecutionEnvironmentImplementations();

   List<ExecutionEnvironment> getCompatibleExecutionEnvironments(ExecutionEnvironment executionEnvironment);

   boolean isCompatible(ExecutionEnvironment executionEnvironment1, ExecutionEnvironment executionEnvironment2);

   AccessRule getAccessRuleById(String executionEnvironmentId, String packageName);
   
   AccessRule getAccessRuleById(Collection<String> executionEnvironmentIds, String packageName);

   AccessRule getAccessRule(ExecutionEnvironment executionEnvironment, String packageName);

   AccessRule getAccessRule(Collection<ExecutionEnvironment> executionEnvironments, String packageName);
}
