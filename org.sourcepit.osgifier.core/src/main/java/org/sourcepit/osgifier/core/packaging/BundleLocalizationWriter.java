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

package org.sourcepit.osgifier.core.packaging;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import org.apache.commons.io.FileUtils;
import org.sourcepit.common.manifest.Manifest;
import org.sourcepit.common.manifest.osgi.BundleHeaderName;
import org.sourcepit.common.utils.lang.PipedException;
import org.sourcepit.common.utils.props.LinkedPropertiesMap;
import org.sourcepit.common.utils.props.PropertiesMap;
import org.sourcepit.osgifier.core.model.context.BundleLocalization;
import org.sourcepit.osgifier.core.model.context.LocalizedData;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
public abstract class BundleLocalizationWriter<O extends OutputStream> {

   public static Map<String, LocalizedData> write(final File dir, Manifest manifest, BundleLocalization localization)
      throws IOException {
      final BundleLocalizationWriter<OutputStream> writer = new BundleLocalizationWriter<OutputStream>() {
         @Override
         protected OutputStream openStream(String path) throws IOException {
            final File file = new File(dir, path);
            FileUtils.forceMkdir(file.getParentFile());
            file.createNewFile();
            return new BufferedOutputStream(new FileOutputStream(file));
         }

         @Override
         protected void closeStream(OutputStream out) throws IOException {
            out.close();
         }
      };
      return writer.write(manifest, localization);
   }

   public static Map<String, LocalizedData> write(final JarOutputStream out, Manifest manifest,
      BundleLocalization localization) throws IOException {
      final BundleLocalizationWriter<JarOutputStream> writer = new BundleLocalizationWriter<JarOutputStream>() {
         @Override
         protected JarOutputStream openStream(String path) throws IOException {
            out.putNextEntry(new JarEntry(path));
            return out;
         }

         @Override
         protected void closeStream(JarOutputStream out) throws IOException {
            out.closeEntry();
         }
      };
      return writer.write(manifest, localization);
   }

   public Map<String, LocalizedData> write(Manifest manifest, BundleLocalization localization) throws IOException {
      final Map<String, LocalizedData> result = new LinkedHashMap<String, LocalizedData>();

      final String prefix = getPathPrefix(manifest);
      for (LocalizedData localizedData : localization.getData()) {
         final List<Entry<String, String>> data = localizedData.getData();
         if (!data.isEmpty()) {
            final PropertiesMap properties = new LinkedPropertiesMap();
            for (Entry<String, String> entry : data) {
               properties.put(entry.getKey(), entry.getValue());
            }

            final String path = getPath(prefix, localizedData.getLocale());
            final O out = openStream(path);
            try {
               properties.store(out);
               result.put(path, localizedData);
            }
            catch (PipedException pipe) {
               pipe.adaptAndThrow(IOException.class);
               throw pipe;
            }
            finally {
               try {
                  closeStream(out);
               }
               catch (IOException ioe) { // noop
               }
            }
         }
      }
      return result;
   }

   private String getPathPrefix(Manifest manifest) {
      String prefix = manifest.getHeaderValue(BundleHeaderName.BUNDLE_LOCALIZATION.getLiteral());
      if (prefix == null) {
         prefix = "OSGI-INF/l10n/bundle";
      }
      return prefix;
   }

   private String getPath(String prefix, final String locale) {
      final StringBuilder name = new StringBuilder(prefix);
      if (!locale.isEmpty()) {
         name.append('_');
         name.append(locale);
      }
      name.append(".properties");
      String path = name.toString();
      return path;
   }

   protected abstract O openStream(String path) throws IOException;

   protected abstract void closeStream(O out) throws IOException;
}
