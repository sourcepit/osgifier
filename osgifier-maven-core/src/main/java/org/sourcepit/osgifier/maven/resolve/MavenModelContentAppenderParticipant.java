/*
 * Copyright 2014 Bernd Vogt and others.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sourcepit.osgifier.maven.resolve;

import static org.apache.maven.model.building.ModelBuildingRequest.VALIDATION_LEVEL_MINIMAL;
import static org.sourcepit.common.maven.model.util.MavenModelUtils.toArtifactKey;
import static org.sourcepit.common.utils.lang.Exceptions.pipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.maven.RepositoryUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Model;
import org.apache.maven.plugin.LegacySupport;
import org.apache.maven.project.DefaultProjectBuildingRequest;
import org.apache.maven.project.ProjectBuilder;
import org.apache.maven.project.ProjectBuildingException;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.maven.project.ProjectBuildingResult;
import org.sourcepit.common.maven.artifact.ArtifactFactory;
import org.sourcepit.common.maven.model.ArtifactKey;
import org.sourcepit.common.maven.model.MavenArtifact;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.OsgifierContext;
import org.sourcepit.osgifier.core.resolve.ContentAppenderFilter;
import org.sourcepit.osgifier.core.resolve.ContentAppenderParticipant;
import org.sourcepit.osgifier.maven.ModelToString;

/**
 * @author Bernd Vogt <Bernd.Vogt@bosch-si.com>
 */
@Named
public class MavenModelContentAppenderParticipant implements ContentAppenderParticipant {
   private final LegacySupport buildContext;

   private final ProjectBuilder projectBuilder;

   private final ArtifactFactory artifactFactory;

   @Inject
   public MavenModelContentAppenderParticipant(LegacySupport buildContext, ProjectBuilder projectBuilder,
      ArtifactFactory artifactFactory) {
      this.buildContext = buildContext;
      this.projectBuilder = projectBuilder;
      this.artifactFactory = artifactFactory;
   }

   @Override
   public void appendContents(OsgifierContext context, ContentAppenderFilter filter, PropertiesSource options) {
      final List<BundleCandidate> bundles = context.getBundles();

      final Map<ArtifactKey, String> cache = new HashMap<ArtifactKey, String>(bundles.size());

      for (BundleCandidate bundle : bundles) {
         if (filter.isAppendContent(bundle, options)) {
            final MavenArtifact artifact = bundle.getExtension(MavenArtifact.class);
            if (artifact != null) {
               final ArtifactKey pomKey = toArtifactKey(artifact.getGroupId(), artifact.getArtifactId(), "pom", null,
                  artifact.getVersion());

               String modelAsString = cache.get(pomKey);
               if (modelAsString == null) {
                  // the normalized model
                  final Model model = buildMavenModel(pomKey);
                  modelAsString = new ModelToString().toString(model);
                  cache.put(pomKey, modelAsString);
               }

               bundle.setAnnotationData("maven", "model", modelAsString);
            }
         }
      }
   }

   private Model buildMavenModel(final ArtifactKey pomKey) {
      final Artifact pomArtifact = RepositoryUtils.toArtifact(artifactFactory.createArtifact(pomKey));

      final ProjectBuildingRequest request = new DefaultProjectBuildingRequest(buildContext.getSession()
         .getProjectBuildingRequest());
      request.setProject(null);
      request.setResolveDependencies(false);
      request.setProcessPlugins(false);
      request.setValidationLevel(VALIDATION_LEVEL_MINIMAL);

      try {
         final ProjectBuildingResult result = projectBuilder.build(pomArtifact, true, request);
         return result.getProject().getModel();
      }
      catch (ProjectBuildingException e) {
         throw pipe(e);
      }
   }
}
