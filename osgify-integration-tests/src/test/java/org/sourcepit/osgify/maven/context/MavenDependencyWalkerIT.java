/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.project.MavenProject;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.sourcepit.common.maven.testing.EmbeddedMavenEnvironmentTest;
import org.sourcepit.common.maven.testing.MavenExecutionResult2;
import org.sourcepit.common.testing.Environment;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public class MavenDependencyWalkerIT extends EmbeddedMavenEnvironmentTest
{
   @Override
   protected Environment newEnvironment()
   {
      return Environment.get("osgiy-its.properties");
   }

   @Override
   protected File getUserHome()
   {
      return getEnvironment().getUserHome();
   }

   @Inject
   private LegacySupport legacySupport;

   @Test
   public void testWalk() throws Exception
   {
      File projectDir = getResource("reactor-project");

      final MavenExecutionResult2 result = buildProject(new File(projectDir, "pom.xml"), true);

      final MavenSession session = result.getSession();

      legacySupport.setSession(session);

      final MavenProject project = getProject(result.getTopologicallySortedProjects(), "module-a");

      final MavenDependencyWalker.Request request = new MavenDependencyWalker.Request();
      request.setArtifact(project.getArtifact());
      request.setReactorProjects(session.getProjects());
      request.setLocalRepository(session.getLocalRepository());
      request.setRemoteRepositories(project.getRemoteArtifactRepositories());

      final LinkedHashMap<String, String> artifactToProjectMap = new LinkedHashMap<String, String>();
      final LinkedHashMap<String, List<String>> artifactToDependenciesMap = new LinkedHashMap<String, List<String>>();

      final MavenDependencyWalker dependencyWalker = gLookup(MavenDependencyWalker.class);
      request.setHandler(new MavenDependencyWalker.Handler()
      {
         public boolean visitNode(Artifact artifact, MavenProject project)
         {
            List<String> dependencies = artifactToDependenciesMap.get(artifact.getArtifactId());
            if (dependencies == null)
            {
               dependencies = new ArrayList<String>();
               artifactToDependenciesMap.put(artifact.getArtifactId(), dependencies);
            }
            if (project != null)
            {
               artifactToProjectMap.put(artifact.getArtifactId(), project.getArtifactId());
            }
            return "module-a".equals(artifact.getArtifactId());
         }

         public void handleDependency(Artifact fromArtifact, Artifact toArtifact)
         {
            artifactToDependenciesMap.get(fromArtifact.getArtifactId()).add(toArtifact.getArtifactId());
         }
      });
      
      session.setCurrentProject(project);
      dependencyWalker.walk(request);

      assertThat(artifactToProjectMap.size(), Is.is(3));
      assertNotNull(artifactToProjectMap.get("module-a"));
      assertNotNull(artifactToProjectMap.get("module-b"));
      assertNotNull(artifactToProjectMap.get("module-c"));
      assertNull(artifactToProjectMap.get("junit"));
      assertNull(artifactToProjectMap.get("hamcrest-core"));

      Set<String> artifactIds = artifactToDependenciesMap.keySet();
      assertThat(artifactIds.size(), Is.is(5));

      Iterator<String> it = artifactIds.iterator();
      assertThat(it.next(), IsEqual.equalTo("module-a"));
      assertThat(it.next(), IsEqual.equalTo("module-b"));
      assertThat(it.next(), IsEqual.equalTo("module-c"));
      assertThat(it.next(), IsEqual.equalTo("junit"));
      assertThat(it.next(), IsEqual.equalTo("hamcrest-core"));

      List<String> deps = artifactToDependenciesMap.get("module-a");
      assertThat(deps.size(), Is.is(4));

      it = deps.iterator();
      assertThat(it.next(), IsEqual.equalTo("module-b"));
      assertThat(it.next(), IsEqual.equalTo("module-c"));
      assertThat(it.next(), IsEqual.equalTo("junit"));
      assertThat(it.next(), IsEqual.equalTo("hamcrest-core"));

      deps = artifactToDependenciesMap.get("module-b");
      assertThat(deps.size(), Is.is(0));

      deps = artifactToDependenciesMap.get("module-c");
      assertThat(deps.size(), Is.is(0));

      deps = artifactToDependenciesMap.get("junit");
      assertThat(deps.size(), Is.is(0));

      deps = artifactToDependenciesMap.get("hamcrest-core");
      assertThat(deps.size(), Is.is(0));

      // reset
      artifactToProjectMap.clear();
      artifactToDependenciesMap.clear();

      request.setHandler(new MavenDependencyWalker.Handler()
      {
         public boolean visitNode(Artifact artifact, MavenProject project)
         {
            List<String> dependencies = artifactToDependenciesMap.get(artifact.getArtifactId());
            if (dependencies == null)
            {
               dependencies = new ArrayList<String>();
               artifactToDependenciesMap.put(artifact.getArtifactId(), dependencies);
            }
            if (project != null)
            {
               artifactToProjectMap.put(artifact.getArtifactId(), project.getArtifactId());
            }
            return true;
         }

         public void handleDependency(Artifact fromArtifact, Artifact toArtifact)
         {
            artifactToDependenciesMap.get(fromArtifact.getArtifactId()).add(toArtifact.getArtifactId());
         }
      });
      dependencyWalker.walk(request);

      assertThat(artifactToProjectMap.size(), Is.is(3));
      assertNotNull(artifactToProjectMap.get("module-a"));
      assertNotNull(artifactToProjectMap.get("module-b"));
      assertNotNull(artifactToProjectMap.get("module-c"));
      assertNull(artifactToProjectMap.get("junit"));
      assertNull(artifactToProjectMap.get("hamcrest-core"));

      artifactIds = artifactToDependenciesMap.keySet();
      assertThat(artifactIds.size(), Is.is(5));

      it = artifactIds.iterator();
      assertThat(it.next(), IsEqual.equalTo("module-a"));
      assertThat(it.next(), IsEqual.equalTo("module-b"));
      assertThat(it.next(), IsEqual.equalTo("module-c"));
      assertThat(it.next(), IsEqual.equalTo("junit"));
      assertThat(it.next(), IsEqual.equalTo("hamcrest-core"));

      deps = artifactToDependenciesMap.get("module-a");
      assertThat(deps.size(), Is.is(4));

      it = deps.iterator();
      assertThat(it.next(), IsEqual.equalTo("module-b"));
      assertThat(it.next(), IsEqual.equalTo("module-c"));
      assertThat(it.next(), IsEqual.equalTo("junit"));
      assertThat(it.next(), IsEqual.equalTo("hamcrest-core"));

      deps = artifactToDependenciesMap.get("module-b");
      assertThat(deps.size(), Is.is(3));

      it = deps.iterator();
      assertThat(it.next(), IsEqual.equalTo("module-c"));
      assertThat(it.next(), IsEqual.equalTo("junit"));
      assertThat(it.next(), IsEqual.equalTo("hamcrest-core"));

      deps = artifactToDependenciesMap.get("module-c");
      assertThat(deps.size(), Is.is(2));

      it = deps.iterator();
      assertThat(it.next(), IsEqual.equalTo("junit"));
      assertThat(it.next(), IsEqual.equalTo("hamcrest-core"));

      deps = artifactToDependenciesMap.get("junit");
      assertThat(deps.size(), Is.is(1));

      it = deps.iterator();
      assertThat(it.next(), IsEqual.equalTo("hamcrest-core"));

      deps = artifactToDependenciesMap.get("hamcrest-core");
      assertThat(deps.size(), Is.is(0));

      legacySupport.setSession(null);
   }
}
