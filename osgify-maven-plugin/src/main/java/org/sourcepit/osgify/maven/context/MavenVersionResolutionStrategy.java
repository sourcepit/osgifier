/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.maven.context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.plexus.component.annotations.Component;
import org.sourcepit.common.manifest.osgi.Version;
import org.sourcepit.common.maven.model.VersionedIdentifiable;
import org.sourcepit.common.utils.priority.Priority;
import org.sourcepit.osgify.context.BundleCandidate;
import org.sourcepit.osgify.core.resolve.AbstractVersionResolutionStrategy;

@Component(role = AbstractVersionResolutionStrategy.class, hint = "MavenVersionResolutionStrategy")
public class MavenVersionResolutionStrategy extends AbstractVersionResolutionStrategy
{
   /**
    * Clean up version parameters. Other builders use more fuzzy definitions of the version syntax. This method cleans
    * up such a version to match an OSGi version.
    * 
    * @param VERSION_STRING
    * @return
    */
   private static final Pattern FUZZY_VERSION = Pattern.compile("(\\d+)(\\.(\\d+)(\\.(\\d+))?)?([^a-zA-Z0-9](.*))?",
      Pattern.DOTALL);

   public Priority getPriority()
   {
      return Priority.NORMAL;
   }

   @Override
   public Version resolveVersion(BundleCandidate bundleCandidate)
   {
      final VersionedIdentifiable mavenArtifact = bundleCandidate.getExtension(VersionedIdentifiable.class);
      if (mavenArtifact != null)
      {
         final String mvnVersion = mavenArtifact.getVersion();
         if (mvnVersion != null)
         {
            return Version.parse(cleanupVersion(mvnVersion));
         }
      }
      return null;
   }

   private static String cleanupVersion(String version)
   {
      StringBuffer result = new StringBuffer();
      Matcher m = FUZZY_VERSION.matcher(version);
      if (m.matches())
      {
         String major = m.group(1);
         String minor = m.group(3);
         String micro = m.group(5);
         String qualifier = m.group(7);

         if (major != null)
         {
            result.append(major);
            if (minor != null)
            {
               result.append(".");
               result.append(minor);
               if (micro != null)
               {
                  result.append(".");
                  result.append(micro);
                  if (qualifier != null)
                  {
                     result.append(".");
                     cleanupModifier(result, qualifier);
                  }
               }
               else if (qualifier != null)
               {
                  result.append(".0.");
                  cleanupModifier(result, qualifier);
               }
               else
               {
                  result.append(".0");
               }
            }
            else if (qualifier != null)
            {
               result.append(".0.0.");
               cleanupModifier(result, qualifier);
            }
            else
            {
               result.append(".0.0");
            }
         }
      }
      else
      {
         result.append("0.0.0.");
         cleanupModifier(result, version);
      }
      return result.toString();
   }

   private static void cleanupModifier(StringBuffer result, String modifier)
   {
      for (int i = 0; i < modifier.length(); i++)
      {
         char c = modifier.charAt(i);
         if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || c == '-')
            result.append(c);
         else
            result.append('_');
      }
   }
}
