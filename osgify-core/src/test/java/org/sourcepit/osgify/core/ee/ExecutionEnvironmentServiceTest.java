/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.ee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.sourcepit.osgify.core.ee.AccessRule.ACCESSIBLE;
import static org.sourcepit.osgify.core.ee.AccessRule.DISCOURAGED;
import static org.sourcepit.osgify.core.ee.AccessRule.NON_ACCESSIBLE;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.sisu.launch.InjectedTest;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Test;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class ExecutionEnvironmentServiceTest extends InjectedTest
{
   @Inject
   private ExecutionEnvironmentService environmentService;

   @Test
   public void testCompare()
   {
      ExecutionEnvironment cdc10 = environmentService.getExecutionEnvironment("CDC-1.0/Foundation-1.0");
      ExecutionEnvironment cdc11 = environmentService.getExecutionEnvironment("CDC-1.1/Foundation-1.1");
      ExecutionEnvironment j2se13 = environmentService.getExecutionEnvironment("J2SE-1.3");
      
      assertThat(cdc10.compareTo(cdc11), Is.is(-1));
      assertThat(cdc11.compareTo(cdc10), Is.is(1));
      
      assertThat(cdc11.compareTo(j2se13), Is.is(-1));
      assertThat(j2se13.compareTo(cdc11), Is.is(1));
      
      assertThat(cdc10.compareTo(j2se13), Is.is(-1));
      assertThat(j2se13.compareTo(cdc10), Is.is(1));
   }

   @Test
   public void testGetExecutionEnvironments()
   {
      final List<ExecutionEnvironment> executionEnvironments = environmentService.getExecutionEnvironments();
      assertThat(executionEnvironments.size(), Is.is(10));

      assertThat(executionEnvironments.get(0).getId(), IsEqual.equalTo("OSGi/Minimum-1.0"));
      assertThat(executionEnvironments.get(1).getId(), IsEqual.equalTo("OSGi/Minimum-1.1"));
      assertThat(executionEnvironments.get(2).getId(), IsEqual.equalTo("OSGi/Minimum-1.2"));
      assertThat(executionEnvironments.get(3).getId(), IsEqual.equalTo("CDC-1.0/Foundation-1.0"));
      assertThat(executionEnvironments.get(4).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
      assertThat(executionEnvironments.get(5).getId(), IsEqual.equalTo("J2SE-1.3"));
      assertThat(executionEnvironments.get(6).getId(), IsEqual.equalTo("J2SE-1.4"));
      assertThat(executionEnvironments.get(7).getId(), IsEqual.equalTo("J2SE-1.5"));
      assertThat(executionEnvironments.get(8).getId(), IsEqual.equalTo("JavaSE-1.6"));
      assertThat(executionEnvironments.get(9).getId(), IsEqual.equalTo("JavaSE-1.7"));
   }

   @Test
   public void isCompatible()
   {
      ExecutionEnvironment java7 = environmentService.getExecutionEnvironment("JavaSE-1.7");
      assertThat(java7, IsNull.notNullValue());

      ExecutionEnvironment java6 = environmentService.getExecutionEnvironment("JavaSE-1.6");
      assertThat(java6, IsNull.notNullValue());

      ExecutionEnvironment java5 = environmentService.getExecutionEnvironment("J2SE-1.5");
      assertThat(java5, IsNull.notNullValue());

      ExecutionEnvironment java4 = environmentService.getExecutionEnvironment("J2SE-1.4");
      assertThat(java4, IsNull.notNullValue());

      assertTrue(environmentService.isCompatible(java7, java6));
      assertTrue(environmentService.isCompatible(java6, java5));
      assertTrue(environmentService.isCompatible(java5, java4));
      assertTrue(environmentService.isCompatible(java7, java4));

      assertFalse(environmentService.isCompatible(java6, java7));
      assertFalse(environmentService.isCompatible(java5, java6));
      assertFalse(environmentService.isCompatible(java4, java5));
      assertFalse(environmentService.isCompatible(java4, java7));
   }

   @Test
   public void testGetCompatibles()
   {
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("OSGi/Minimum-1.0");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(9));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("OSGi/Minimum-1.1"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("OSGi/Minimum-1.2"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("CDC-1.0/Foundation-1.0"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
         assertThat(execEnvs.get(4).getId(), IsEqual.equalTo("J2SE-1.3"));
         assertThat(execEnvs.get(5).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(6).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(7).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(8).getId(), IsEqual.equalTo("JavaSE-1.7"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("OSGi/Minimum-1.1");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(9));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("OSGi/Minimum-1.0"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("OSGi/Minimum-1.2"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("CDC-1.0/Foundation-1.0"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
         assertThat(execEnvs.get(4).getId(), IsEqual.equalTo("J2SE-1.3"));
         assertThat(execEnvs.get(5).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(6).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(7).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(8).getId(), IsEqual.equalTo("JavaSE-1.7"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("OSGi/Minimum-1.2");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(7));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("CDC-1.0/Foundation-1.0"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("J2SE-1.3"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(4).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(5).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(6).getId(), IsEqual.equalTo("JavaSE-1.7"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("CDC-1.0/Foundation-1.0");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(1));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("CDC-1.1/Foundation-1.1");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(0));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("J2SE-1.3");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(4));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("JavaSE-1.7"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("J2SE-1.4");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(3));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("JavaSE-1.7"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("J2SE-1.5");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(2));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("JavaSE-1.7"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE-1.6");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(1));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE-1.7"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE-1.7");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(0));
      }

   }

   @Test
   public void testAccessRule()
   {
      try
      {
         environmentService.getAccessRuleById("murks", "java.lang");
         fail();
      }
      catch (IllegalArgumentException e)
      {
      }

      AccessRule accessRule = environmentService.getAccessRuleById("J2SE-1.4", "java.lang");
      assertThat(accessRule, Is.is(ACCESSIBLE));

      accessRule = environmentService.getAccessRuleById("J2SE-1.4", "com.sun.java.swing");
      assertThat(accessRule, Is.is(DISCOURAGED));

      accessRule = environmentService.getAccessRuleById("J2SE-1.4", "foo");
      assertThat(accessRule, Is.is(NON_ACCESSIBLE));

      final ExecutionEnvironment java4 = environmentService.getExecutionEnvironment("J2SE-1.4");
      for (ExecutionEnvironment executionEnvironment : environmentService.getExecutionEnvironments())
      {
         if (environmentService.isCompatible(executionEnvironment, java4))
         {
            accessRule = environmentService.getAccessRule(executionEnvironment, "java.lang");
            assertThat(accessRule, Is.is(ACCESSIBLE));

            accessRule = environmentService.getAccessRule(executionEnvironment, "com.sun.java.swing");
            assertThat(accessRule, Is.is(DISCOURAGED));

            accessRule = environmentService.getAccessRule(executionEnvironment, "foo");
            assertThat(accessRule, Is.is(NON_ACCESSIBLE));
         }
      }
   }

   @Test
   public void testAccessRuleWithIntersection()
   {
      List<String> execEnvs = new ArrayList<String>();
      execEnvs.add("J2SE-1.4");
      execEnvs.add("CDC-1.1/Foundation-1.1");

      AccessRule accessRule = environmentService.getAccessRuleById(execEnvs, "java.lang");
      assertThat(accessRule, Is.is(ACCESSIBLE));

      accessRule = environmentService.getAccessRuleById(execEnvs, "javax.microedition.io");
      assertThat(accessRule, Is.is(DISCOURAGED));

      accessRule = environmentService.getAccessRuleById(execEnvs, "com.sun.net.ssl");
      assertThat(accessRule, Is.is(DISCOURAGED));

      accessRule = environmentService.getAccessRuleById(execEnvs, "foo");
      assertThat(accessRule, Is.is(NON_ACCESSIBLE));
   }

   @Test
   public void getExecutionEnvironmentImplementations()
   {
      List<ExecutionEnvironmentImplementation> execEnvImpls = environmentService
         .getExecutionEnvironmentImplementations();
      assertThat(execEnvImpls.get(0).getExecutionEnvironmentId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
      assertThat(execEnvImpls.get(1).getExecutionEnvironmentId(), IsEqual.equalTo("J2SE-1.4"));
      assertThat(execEnvImpls.get(2).getExecutionEnvironmentId(), IsEqual.equalTo("J2SE-1.5"));
      assertThat(execEnvImpls.get(3).getExecutionEnvironmentId(), IsEqual.equalTo("JavaSE-1.6"));
      assertThat(execEnvImpls.get(4).getExecutionEnvironmentId(), IsEqual.equalTo("JavaSE-1.7"));
   }
}
