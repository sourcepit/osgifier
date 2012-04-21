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
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.project.MavenProject;
import org.eclipse.emf.common.util.EList;
import org.junit.Test;
import org.sourcepit.common.maven.testing.EmbeddedMavenEnvironmentTest;
import org.sourcepit.common.maven.testing.MavenExecutionResult2;
import org.sourcepit.common.testing.Environment;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.context.BundleReference;
import org.sourcepit.osgify.core.model.context.OsgifyContext;
import org.sourcepit.osgify.maven.Goal;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class OsgifyHierarchyResolverTest extends EmbeddedMavenEnvironmentTest
{
   @Inject
   private OsgifyHierarchyResolver builder;

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
   public void test() throws Exception
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
      OsgifyContext context = builder.resolve(project, Goal.OSGIFY, localRepository);
      return context;
   }

}
