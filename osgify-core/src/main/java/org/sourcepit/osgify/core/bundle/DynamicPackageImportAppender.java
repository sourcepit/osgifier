/**
 * Copyright (c) 2012 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.osgify.core.bundle;

import static org.sourcepit.common.manifest.osgi.BundleHeaderName.DYNAMICIMPORT_PACKAGE;

import javax.inject.Named;

import org.eclipse.emf.common.util.EList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.manifest.osgi.BundleManifest;
import org.sourcepit.common.manifest.osgi.DynamicPackageImport;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.osgify.core.java.inspect.ClassForNameDetector;
import org.sourcepit.osgify.core.model.context.BundleCandidate;
import org.sourcepit.osgify.core.model.java.JavaResourceBundle;
import org.sourcepit.osgify.core.model.java.JavaType;
import org.sourcepit.osgify.core.model.java.JavaTypeVisitor;

/**
 * @author Bernd Vogt <bernd.vogt@sourcepit.org>
 */
@Named
public class DynamicPackageImportAppender
{
   private static final Logger LOGGER = LoggerFactory.getLogger(DynamicPackageImportAppender.class);

   public void append(BundleCandidate bundle)
   {
      final BundleManifest manifest = bundle.getManifest();
      if (!hasDynamicImportPackage(manifest, "*") && usesClassForName(bundle.getContent()))
      {
         LOGGER
            .warn("Detected usage of Class.forName(String). The behaviour of this method differs between OSGi and pure Java. Setting the 'DynamicImport-Package: *' header to workaround this problem. ");
         manifest.setHeader(DYNAMICIMPORT_PACKAGE, "*");
      }
   }

   public boolean usesClassForName(JavaResourceBundle jBundle)
   {
      try
      {
         jBundle.accept(new JavaTypeVisitor()
         {
            @Override
            protected void visit(JavaType jType)
            {
               final Annotation annotation = jType.getAnnotation(ClassForNameDetector.SOURCE);
               if (annotation != null)
               {
                  if (annotation.getData(ClassForNameDetector.CLASS_FOR_NAME, false))
                  {
                     throw new IllegalStateException("break");
                  }
               }
            }
         });
      }
      catch (IllegalStateException e)
      {
         if ("break".equals(e.getMessage()))
         {
            return true;
         }
         throw e;
      }
      return false;
   }

   private boolean hasDynamicImportPackage(BundleManifest manifest, String pattern)
   {
      EList<DynamicPackageImport> dynamicImportPackage = manifest.getDynamicImportPackage();
      if (dynamicImportPackage != null)
      {
         for (DynamicPackageImport dynamicPackageImport : dynamicImportPackage)
         {
            if (dynamicPackageImport.getPackageNames().contains(pattern))
            {
               return true;
            }
         }
      }
      return false;
   }
}
