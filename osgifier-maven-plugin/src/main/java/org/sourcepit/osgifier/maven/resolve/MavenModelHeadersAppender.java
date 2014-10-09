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

import static org.apache.commons.lang.StringUtils.isEmpty;
import static org.apache.commons.lang.StringUtils.isNotEmpty;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_DESCRIPTION;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_DOCURL;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_NAME;
import static org.sourcepit.common.manifest.osgi.BundleHeaderName.BUNDLE_VENDOR;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.Organization;
import org.sourcepit.common.manifest.osgi.BundleHeaderName;
import org.sourcepit.common.manifest.osgi.BundleLicense;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.BundleManifestFactory;
import org.sourcepit.common.utils.props.PropertiesSource;
import org.sourcepit.osgifier.core.bundle.BundleHeadersAppender;
import org.sourcepit.osgifier.core.bundle.BundleManifestAppenderFilter;
import org.sourcepit.osgifier.core.model.context.BundleCandidate;
import org.sourcepit.osgifier.core.model.context.BundleLocalization;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class MavenModelHeadersAppender implements BundleHeadersAppender
{
   @Override
   public void append(BundleCandidate bundle, BundleManifestAppenderFilter filter, PropertiesSource options)
   {
      final String modelAsString = bundle.getAnnotationData("maven", "model");
      if (modelAsString != null)
      {
         final Model model = new ModelFromString().fromString(modelAsString);
         final BundleManifest manifest = bundle.getManifest();
         final BundleLocalization localization = bundle.getLocalization();
         appendBundleName(manifest, localization, model);
         appendBundleVendor(manifest, localization, model);
         appendBundleDocURL(manifest, localization, model);
         appendBundleDescription(manifest, localization, model);
         appendBundleLicense(manifest, model);
      }
   }

   private void appendBundleName(BundleManifest manifest, BundleLocalization localization, Model model)
   {
      final String name = model.getName();
      if (isNotEmpty(name))
      {
         append(manifest, localization, BUNDLE_NAME, "bundle.name", name);
      }
   }

   private void appendBundleVendor(BundleManifest manifest, BundleLocalization localization, Model model)
   {
      final Organization organization = model.getOrganization();
      if (organization != null)
      {
         String value = organization.getName();
         if (isEmpty(value))
         {
            value = organization.getUrl();
         }
         if (isNotEmpty(value))
         {
            append(manifest, localization, BUNDLE_VENDOR, "bundle.vendor", value);
         }
      }
   }

   private void appendBundleDocURL(BundleManifest manifest, BundleLocalization localization, Model model)
   {
      final String url = model.getUrl();
      if (isNotEmpty(url))
      {
         append(manifest, localization, BUNDLE_DOCURL, "bundle.docURL", url);
      }
   }

   private void appendBundleDescription(BundleManifest manifest, BundleLocalization localization, Model model)
   {
      final String description = model.getDescription();
      if (isNotEmpty(description))
      {
         append(manifest, localization, BUNDLE_DESCRIPTION, "bundle.description", description);
      }
   }

   private void appendBundleLicense(BundleManifest manifest, final Model model)
   {
      final List<BundleLicense> bundleLicenses = toBundleLicenses(model.getLicenses());
      if (!bundleLicenses.isEmpty())
      {
         manifest.setBundleLicense(bundleLicenses);
      }
   }

   private static void append(BundleManifest manifest, BundleLocalization localization, BundleHeaderName header,
      String key, final String value)
   {
      localization.set("", key, value);
      manifest.setHeader(header, "%" + key);
   }

   private List<BundleLicense> toBundleLicenses(List<License> licenses)
   {
      final List<BundleLicense> bundleLicenses = new ArrayList<BundleLicense>();
      for (License license : licenses)
      {
         BundleLicense bundleLicense = toBundleLicense(license);
         if (bundleLicense != null)
         {
            bundleLicenses.add(bundleLicense);
         }
      }
      return bundleLicenses;
   }


   static BundleLicense toBundleLicense(License license)
   {
      final BundleLicense bundleLicense = BundleManifestFactory.eINSTANCE.createBundleLicense();

      final String name = license.getName();
      if (isNotEmpty(name))
      {
         final String safeName = toBundleLicenseName(license.getName());
         if (isNotEmpty(safeName))
         {
            bundleLicense.setName(safeName);
         }
      }

      final String url = license.getUrl();
      if (isNotEmpty(url))
      {
         if (isNotEmpty(bundleLicense.getName()))
         {
            bundleLicense.setLink(url);
         }
         else
         {
            final String safeName = toBundleLicenseName(url);
            if (isNotEmpty(safeName))
            {
               bundleLicense.setName(safeName);
            }
         }
      }

      return isNotEmpty(bundleLicense.getName()) || isNotEmpty(bundleLicense.getLink()) ? bundleLicense : null;
   }

   private static String toBundleLicenseName(String name)
   {
      final StringBuilder sb = new StringBuilder(name.length());
      for (char c : name.toCharArray())
      {
         if (c == ',' || c == ';')
         {
            continue;
         }
         sb.append(c);
      }
      return sb.toString();
   }

}
