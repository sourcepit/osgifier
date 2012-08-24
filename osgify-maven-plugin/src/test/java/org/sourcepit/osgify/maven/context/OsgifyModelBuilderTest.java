/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.EList;
import org.junit.Assert;
import org.junit.Test;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.maven.testing.EmbeddedMavenEnvironmentTest;
import org.sourcepit.common.maven.testing.MavenExecutionResult2;
import org.sourcepit.common.testing.Environment;
import org.sourcepit.common.utils.lang.PipedException;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.OsgifyContext;

public class OsgifyModelBuilderTest extends EmbeddedMavenEnvironmentTest
{
   @Inject
   private OsgifyModelBuilder builder;

   @Inject
   private LegacySupport legacySupport;

   @Override
   protected Environment newEnvironment()
   {
      return Environment.get("env-test.properties");
   }

   @Override
   protected File getResourcesDir()
   {
      return getEnvironment().getPropertyAsFile("test.resources", true);
   }

   @Test
   public void testProjectWithDependencies() throws Exception
   {
      final File projectDir = getResource("projects/project-with-dependencies");

      final MavenExecutionResult2 result = buildProject(new File(projectDir, "pom.xml"), true);
      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "project-with-dependencies";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = new OsgifyModelBuilder.Request();
         request.setArtifact(project.getArtifact());
         request.setLocalRepository(localRepository);

         OsgifyContext context = builder.build(request);

         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(4));

         BundleCandidate candidate = candidates.get(0);

         assertIsMavenProject(candidate, artifactId);
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(3));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "org.osgi.core");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));

         candidate = candidates.get(2);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));

         candidate = candidates.get(3);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testProjectWithDependencies_ResolveDependenciesOfNativeBundles() throws Exception
   {
      final File projectDir = getResource("projects/project-with-dependencies");

      final MavenExecutionResult2 result = buildProject(new File(projectDir, "pom.xml"), true);
      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "project-with-dependencies";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = new OsgifyModelBuilder.Request();
         request.setArtifact(project.getArtifact());
         request.setLocalRepository(localRepository);
         request.setResolveDependenciesOfNativeBundles(true);

         OsgifyContext context = builder.build(request);

         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(4));

         BundleCandidate candidate = candidates.get(0);

         assertIsMavenProject(candidate, artifactId);
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(3));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "org.osgi.core");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));

         candidate = candidates.get(2);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(1));

         candidate = candidates.get(3);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   private void assertIsMavenProject(BundleCandidate candidate, String artifactId)
   {
      assertThat(candidate, notNullValue());
      org.sourcepit.common.maven.model.MavenProject mProject = candidate
         .getExtension(org.sourcepit.common.maven.model.MavenProject.class);
      assertThat(mProject, notNullValue());
      assertThat(mProject.getArtifactId(), equalTo(artifactId));
      assertThat(mProject.getPackaging(), equalTo("jar"));
   }

   private void assertIsMavenArtifact(BundleCandidate candidate, String artifactId)
   {
      MavenArtifact mArtifact = candidate.getExtension(MavenArtifact.class);
      assertThat(mArtifact, notNullValue());
      assertThat(mArtifact.getArtifactId(), equalTo(artifactId));
   }

   @Test
   public void testProjectWithDependencies_FatBundle() throws Exception
   {
      final File projectDir = getResource("projects/project-with-dependencies");

      final MavenExecutionResult2 result = buildProject(new File(projectDir, "pom.xml"), true);
      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         assertThat(project.getArtifactId(), equalTo("project-with-dependencies"));

         OsgifyModelBuilder.Request request = new OsgifyModelBuilder.Request();
         request.setArtifact(project.getArtifact());
         request.setLocalRepository(localRepository);
         request.setFatBundle(true);
         request.setResolveDependenciesOfNativeBundles(true); // assure that resolution of dependencies of dependencies
                                                              // is suppressed by the 'fat bundle' flag

         OsgifyContext context = builder.build(request);

         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(4));

         BundleCandidate candidate = candidates.get(0);

         assertIsMavenProject(candidate, "project-with-dependencies");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(3));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "org.osgi.core");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));

         candidate = candidates.get(2);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));

         candidate = candidates.get(3);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testArtifactWithDependencies() throws Exception
   {
      MavenExecutionRequest mavenRequest = newMavenExecutionRequest(null);
      mavenRequest.setProjectPresent(false);

      MavenExecutionResult2 result = execute(mavenRequest);

      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "standalone-pom";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = builder.createBundleRequest("javax.mail", "mail", "1.4.5", null, null,
            false, null, localRepository);
         request.setResolveDependenciesOfNativeBundles(false);

         assertThat(request.getArtifact(), notNullValue());

         OsgifyContext context = builder.build(request);

         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(1));

         BundleCandidate candidate = candidates.get(0);

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testArtifactWithDependencies_ResolveDependenciesOfNativeBundles() throws Exception
   {
      MavenExecutionRequest mavenRequest = newMavenExecutionRequest(null);
      mavenRequest.setProjectPresent(false);

      MavenExecutionResult2 result = execute(mavenRequest);

      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "standalone-pom";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = builder.createBundleRequest("javax.mail", "mail", "1.4.5", null, null,
            false, null, localRepository);
         assertThat(request.getArtifact(), notNullValue());

         OsgifyContext context = builder.build(request);

         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(2));

         BundleCandidate candidate = candidates.get(0);

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(1));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testArtifactWithDependencies_FatBundle() throws Exception
   {
      MavenExecutionRequest mavenRequest = newMavenExecutionRequest(null);
      mavenRequest.setProjectPresent(false);

      MavenExecutionResult2 result = execute(mavenRequest);

      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "standalone-pom";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = builder.createBundleRequest("javax.mail", "mail", "1.4.5", null, null,
            true, null, localRepository);
         assertThat(request.getArtifact(), notNullValue());

         OsgifyContext context = builder.build(request);

         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(2));

         BundleCandidate candidate = candidates.get(0);

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(1));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testVirtualArtifactWithDependencies() throws Exception
   {
      MavenExecutionRequest mavenRequest = newMavenExecutionRequest(null);
      mavenRequest.setProjectPresent(false);

      MavenExecutionResult2 result = execute(mavenRequest);

      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "standalone-pom";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = builder.createVirtualBundleRequest("virtual-artifact",
            "virtual-artifact", "1.2.3", null, null, null, false, null, localRepository);
         assertThat(request.getArtifact(), notNullValue());

         request.setVirtualArtifact(false);
         request.setResolveDependenciesOfNativeBundles(false);
         
         try
         {
            builder.build(request);
            Assert.fail();
         }
         catch (PipedException e)
         {
            assertThat(e.adapt(ArtifactNotFoundException.class), notNullValue());
         }

         request.setVirtualArtifact(true);

         OsgifyContext context = builder.build(request);
         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(1));

         BundleCandidate candidate = candidates.get(0);

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "virtual-artifact");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));

         Dependency dep = new Dependency();
         dep.setGroupId("javax.mail");
         dep.setArtifactId("mail");
         dep.setVersion("1.4.5");
         request.getDependencies().add(dep);

         dep = new Dependency();
         dep.setGroupId("org.osgi");
         dep.setArtifactId("org.osgi.core");
         dep.setVersion("4.3.0");
         request.getDependencies().add(dep);

         context = builder.build(request);
         assertThat(context, notNullValue());

         candidates = context.getBundles();
         assertThat(candidates.size(), is(4));

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "virtual-artifact");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(3));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));

         candidate = candidates.get(2);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testVirtualArtifactWithDependencies_ResolveDependenciesOfNativeBundles() throws Exception
   {
      MavenExecutionRequest mavenRequest = newMavenExecutionRequest(null);
      mavenRequest.setProjectPresent(false);

      MavenExecutionResult2 result = execute(mavenRequest);

      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "standalone-pom";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = builder.createVirtualBundleRequest("virtual-artifact",
            "virtual-artifact", "1.2.3", null, null, null, false, null, localRepository);
         assertThat(request.getArtifact(), notNullValue());

         request.setVirtualArtifact(false);
         
         try
         {
            builder.build(request);
            Assert.fail();
         }
         catch (PipedException e)
         {
            assertThat(e.adapt(ArtifactNotFoundException.class), notNullValue());
         }

         request.setVirtualArtifact(true);

         OsgifyContext context = builder.build(request);
         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(1));

         BundleCandidate candidate = candidates.get(0);

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "virtual-artifact");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));

         Dependency dep = new Dependency();
         dep.setGroupId("javax.mail");
         dep.setArtifactId("mail");
         dep.setVersion("1.4.5");
         request.getDependencies().add(dep);

         dep = new Dependency();
         dep.setGroupId("org.osgi");
         dep.setArtifactId("org.osgi.core");
         dep.setVersion("4.3.0");
         request.getDependencies().add(dep);

         context = builder.build(request);
         assertThat(context, notNullValue());

         candidates = context.getBundles();
         assertThat(candidates.size(), is(4));

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "virtual-artifact");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(3));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(1));

         candidate = candidates.get(2);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testVirtualArtifactWithDependencies_FatBundle() throws Exception
   {
      MavenExecutionRequest mavenRequest = newMavenExecutionRequest(null);
      mavenRequest.setProjectPresent(false);

      MavenExecutionResult2 result = execute(mavenRequest);

      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(1));

         // reactor project
         final MavenProject project = projects.get(0);
         String artifactId = "standalone-pom";
         assertThat(project.getArtifactId(), equalTo(artifactId));

         OsgifyModelBuilder.Request request = builder.createVirtualBundleRequest("virtual-artifact",
            "virtual-artifact", "1.2.3", null, null, null, true, null, localRepository);
         assertThat(request.getArtifact(), notNullValue());

         request.setResolveDependenciesOfNativeBundles(true); // assure that resolution of dependencies of dependencies
                                                              // is suppressed by the 'fat bundle' flag

         request.setVirtualArtifact(false);
         
         try
         {
            builder.build(request);
            Assert.fail();
         }
         catch (PipedException e)
         {
            assertThat(e.adapt(ArtifactNotFoundException.class), notNullValue());
         }

         request.setVirtualArtifact(true);

         OsgifyContext context = builder.build(request);
         assertThat(context, notNullValue());

         EList<BundleCandidate> candidates = context.getBundles();
         assertThat(candidates.size(), is(1));

         BundleCandidate candidate = candidates.get(0);

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "virtual-artifact");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));

         Dependency dep = new Dependency();
         dep.setGroupId("javax.mail");
         dep.setArtifactId("mail");
         dep.setVersion("1.4.5");
         request.getDependencies().add(dep);

         dep = new Dependency();
         dep.setGroupId("org.osgi");
         dep.setArtifactId("org.osgi.core");
         dep.setVersion("4.3.0");
         request.getDependencies().add(dep);

         context = builder.build(request);
         assertThat(context, notNullValue());

         candidates = context.getBundles();
         assertThat(candidates.size(), is(4));

         candidate = candidates.get(0);
         assertIsMavenArtifact(candidate, "virtual-artifact");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(3));

         candidate = candidates.get(1);
         assertIsMavenArtifact(candidate, "mail");
         assertThat(candidate.isNativeBundle(), is(true));
         assertThat(candidate.getDependencies().size(), is(0));

         candidate = candidates.get(2);
         assertIsMavenArtifact(candidate, "activation");
         assertThat(candidate.isNativeBundle(), is(false));
         assertThat(candidate.getDependencies().size(), is(0));
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }

   @Test
   public void testReactorBuild() throws Exception
   {
      final File projectDir = getResource("projects/bundle-reactor");

      final MavenExecutionResult2 result = buildProject(new File(projectDir, "pom.xml"), true);
      MavenSession session = result.getSession();
      final ArtifactRepository localRepository = session.getLocalRepository();

      legacySupport.setSession(session);
      try
      {
         final List<MavenProject> projects = result.getTopologicallySortedProjects();
         assertThat(projects.size(), is(3));

         // reactor project
         final MavenProject reactorProject = projects.get(0);
         assertThat(reactorProject.getArtifactId(), equalTo("bundle-reactor"));

         OsgifyContext context = buildContext(session, reactorProject, localRepository);
         assertBundleReactor(context);

         // bundle
         final MavenProject bundleProject = projects.get(1);
         assertThat(bundleProject.getArtifactId(), equalTo("org.sourcepit.test.bundle"));

         context = buildContext(session, bundleProject, localRepository);
         assertBundleProject(context);

         // test bundle
         final MavenProject testBundleProject = projects.get(2);
         assertThat(testBundleProject.getArtifactId(), equalTo("org.sourcepit.test.bundle.tests"));

         context = buildContext(session, testBundleProject, localRepository);
         assertTestBundleProject(context);
      }
      finally
      {
         legacySupport.setSession(null);
      }
   }


   private void assertTestBundleProject(OsgifyContext context)
   {
      assertThat(context, notNullValue());

      EList<BundleCandidate> candidates = context.getBundles();
      assertThat(candidates.size(), is(2));

      BundleCandidate candidate = candidates.get(0);
      assertTestBundleCandidate(candidate);

      candidate = candidates.get(1);
      assertBundleCandidate(candidate);
   }

   private void assertTestBundleCandidate(BundleCandidate candidate)
   {
      assertThat(candidate, notNullValue());
      assertThat(candidate.isNativeBundle(), is(false));

      org.sourcepit.common.maven.model.MavenProject mProject = candidate
         .getExtension(org.sourcepit.common.maven.model.MavenProject.class);
      assertThat(mProject, notNullValue());
      assertThat(mProject.getArtifactId(), equalTo("org.sourcepit.test.bundle.tests"));
      assertThat(mProject.getPackaging(), equalTo("jar"));

      EList<BundleReference> dependencies = candidate.getDependencies();
      assertThat(dependencies.size(), is(1));

      BundleReference requirement = dependencies.get(0);

      BundleCandidate requiredCandidate = requirement.getTarget();
      assertBundleCandidate(requiredCandidate);
   }

   private void assertBundleProject(OsgifyContext context)
   {
      assertThat(context, notNullValue());

      EList<BundleCandidate> candidates = context.getBundles();
      assertThat(candidates.size(), is(1));

      BundleCandidate candidate = candidates.get(0);
      assertBundleCandidate(candidate);
   }

   private void assertBundleCandidate(BundleCandidate candidate)
   {
      assertThat(candidate, notNullValue());
      assertThat(candidate.isNativeBundle(), is(false));

      org.sourcepit.common.maven.model.MavenProject mProject = candidate
         .getExtension(org.sourcepit.common.maven.model.MavenProject.class);
      assertThat(mProject, notNullValue());
      assertThat(mProject.getArtifactId(), equalTo("org.sourcepit.test.bundle"));
      assertThat(mProject.getPackaging(), equalTo("jar"));

      EList<BundleReference> dependencies = candidate.getDependencies();
      assertThat(dependencies.size(), is(0));
   }

   private void assertBundleReactor(OsgifyContext context)
   {
      assertThat(context, notNullValue());

      EList<BundleCandidate> candidates = context.getBundles();
      assertThat(candidates.size(), is(1));

      BundleCandidate candidate = candidates.get(0);
      assertThat(candidate.isNativeBundle(), is(false));

      org.sourcepit.common.maven.model.MavenProject mProject = candidate
         .getExtension(org.sourcepit.common.maven.model.MavenProject.class);
      assertThat(mProject, notNullValue());
      assertThat(mProject.getPackaging(), equalTo("pom"));

      EList<BundleReference> dependencies = candidate.getDependencies();
      assertThat(dependencies.size(), is(0));
   }

   private OsgifyContext buildContext(MavenSession session, final MavenProject project,
      final ArtifactRepository localRepository)
   {
      session.setCurrentProject(project);

      OsgifyModelBuilder.Request request = new OsgifyModelBuilder.Request();
      request.setArtifact(project.getArtifact());
      request.setLocalRepository(localRepository);
      request.setResolveDependenciesOfNativeBundles(true);

      return builder.build(request);
   }
}
