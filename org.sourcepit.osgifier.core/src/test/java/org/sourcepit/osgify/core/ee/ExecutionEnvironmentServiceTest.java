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

import java.util.Arrays;
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
      assertThat(executionEnvironments.size(), Is.is(16));

      assertThat(executionEnvironments.get(0).getId(), IsEqual.equalTo("OSGi/Minimum-1.0"));
      assertThat(executionEnvironments.get(1).getId(), IsEqual.equalTo("CDC-1.0/Foundation-1.0"));
      assertThat(executionEnvironments.get(2).getId(), IsEqual.equalTo("JRE-1.1"));
      assertThat(executionEnvironments.get(3).getId(), IsEqual.equalTo("OSGi/Minimum-1.1"));
      assertThat(executionEnvironments.get(4).getId(), IsEqual.equalTo("OSGi/Minimum-1.2"));
      assertThat(executionEnvironments.get(5).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
      assertThat(executionEnvironments.get(6).getId(), IsEqual.equalTo("J2SE-1.2"));
      assertThat(executionEnvironments.get(7).getId(), IsEqual.equalTo("J2SE-1.3"));
      assertThat(executionEnvironments.get(8).getId(), IsEqual.equalTo("J2SE-1.4"));
      assertThat(executionEnvironments.get(9).getId(), IsEqual.equalTo("J2SE-1.5"));
      assertThat(executionEnvironments.get(10).getId(), IsEqual.equalTo("JavaSE-1.6"));
      assertThat(executionEnvironments.get(11).getId(), IsEqual.equalTo("JavaSE-1.7"));
      assertThat(executionEnvironments.get(12).getId(), IsEqual.equalTo("JavaSE/compact1-1.8"));
      assertThat(executionEnvironments.get(13).getId(), IsEqual.equalTo("JavaSE/compact2-1.8"));
      assertThat(executionEnvironments.get(14).getId(), IsEqual.equalTo("JavaSE/compact3-1.8"));
      assertThat(executionEnvironments.get(15).getId(), IsEqual.equalTo("JavaSE-1.8"));
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

      assertTrue(java7.isCompatibleWith(java6));
      assertTrue(java6.isCompatibleWith(java5));
      assertTrue(java5.isCompatibleWith(java4));
      assertTrue(java7.isCompatibleWith(java4));

      assertFalse(java6.isCompatibleWith(java7));
      assertFalse(java5.isCompatibleWith(java6));
      assertFalse(java4.isCompatibleWith(java5));
      assertFalse(java4.isCompatibleWith(java7));
   }

   @Test
   public void testGetCompatibles()
   {
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("OSGi/Minimum-1.0");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(14));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("CDC-1.0/Foundation-1.0"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("OSGi/Minimum-1.1"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("OSGi/Minimum-1.2"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
         assertThat(execEnvs.get(4).getId(), IsEqual.equalTo("J2SE-1.2"));
         assertThat(execEnvs.get(5).getId(), IsEqual.equalTo("J2SE-1.3"));
         assertThat(execEnvs.get(6).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(7).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(8).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(9).getId(), IsEqual.equalTo("JavaSE-1.7"));
         assertThat(execEnvs.get(10).getId(), IsEqual.equalTo("JavaSE/compact1-1.8"));
         assertThat(execEnvs.get(11).getId(), IsEqual.equalTo("JavaSE/compact2-1.8"));
         assertThat(execEnvs.get(12).getId(), IsEqual.equalTo("JavaSE/compact3-1.8"));
         assertThat(execEnvs.get(13).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("OSGi/Minimum-1.1");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(12));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("OSGi/Minimum-1.2"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("J2SE-1.2"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("J2SE-1.3"));
         assertThat(execEnvs.get(4).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(5).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(6).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(7).getId(), IsEqual.equalTo("JavaSE-1.7"));
         assertThat(execEnvs.get(8).getId(), IsEqual.equalTo("JavaSE/compact1-1.8"));
         assertThat(execEnvs.get(9).getId(), IsEqual.equalTo("JavaSE/compact2-1.8"));
         assertThat(execEnvs.get(10).getId(), IsEqual.equalTo("JavaSE/compact3-1.8"));
         assertThat(execEnvs.get(11).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("OSGi/Minimum-1.2");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(9));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("CDC-1.1/Foundation-1.1"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("J2SE-1.2"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("J2SE-1.3"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(4).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(5).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(6).getId(), IsEqual.equalTo("JavaSE-1.7"));
         assertThat(execEnvs.get(7).getId(), IsEqual.equalTo("JavaSE/compact3-1.8"));
         assertThat(execEnvs.get(8).getId(), IsEqual.equalTo("JavaSE-1.8"));
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
         assertThat(execEnvs.size(), Is.is(5));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("J2SE-1.4"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("JavaSE-1.7"));
         assertThat(execEnvs.get(4).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("J2SE-1.4");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(4));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("J2SE-1.5"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("JavaSE-1.7"));
         assertThat(execEnvs.get(3).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("J2SE-1.5");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(3));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE-1.6"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("JavaSE-1.7"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE-1.6");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(2));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE-1.7"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE-1.7");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(1));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE/compact1-1.8");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(3));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE/compact2-1.8"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("JavaSE/compact3-1.8"));
         assertThat(execEnvs.get(2).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE/compact2-1.8");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(2));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE/compact3-1.8"));
         assertThat(execEnvs.get(1).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE/compact3-1.8");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(1));
         assertThat(execEnvs.get(0).getId(), IsEqual.equalTo("JavaSE-1.8"));
      }
      {
         ExecutionEnvironment execEnv = environmentService.getExecutionEnvironment("JavaSE-1.8");
         List<ExecutionEnvironment> execEnvs = environmentService.getCompatibleExecutionEnvironments(execEnv);
         assertNotNull(execEnv);
         assertThat(execEnvs.size(), Is.is(0));
      }

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

   @Test
   public void testGetIntersectingPackages() throws Exception
   {
      ExecutionEnvironment java8 = environmentService.getExecutionEnvironment("JavaSE/compact3-1.8");
      assertTrue(java8.getPackages().contains("java.lang"));
      assertFalse(java8.getPackages().contains("java.applet"));
      assertTrue(java8.getPackages().contains("java.nio.file"));

      ExecutionEnvironment java4 = environmentService.getExecutionEnvironment("J2SE-1.4");
      assertTrue(java4.getPackages().contains("java.lang"));
      assertTrue(java4.getPackages().contains("java.applet"));
      assertFalse(java4.getPackages().contains("java.nio.file"));

      List<String> intersectedPackages = environmentService.getIntersectingPackagesByIds(Arrays.asList("J2SE-1.4",
         "JavaSE/compact3-1.8"));
      
      assertTrue(intersectedPackages.contains("java.lang"));
      assertFalse(intersectedPackages.contains("java.applet"));
      assertFalse(intersectedPackages.contains("java.nio.file"));
   }
}
