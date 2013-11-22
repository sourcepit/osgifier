/**
 * Copyright (c) 2013 Sourcepit.org contributors and others. All rights reserved. This program and the accompanying
 * materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.sourcepit.modularizor.core.bundle;

import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sourcepit.common.modeling.Annotation;
import org.sourcepit.modularizor.core.model.context.BundleCandidate;
import org.sourcepit.modularizor.core.model.context.BundleReference;
import org.sourcepit.modularizor.java.JavaFile;
import org.sourcepit.modularizor.java.JavaPackage;
import org.sourcepit.modularizor.java.JavaResourceBundle;
import org.sourcepit.modularizor.java.JavaResourceDirectory;
import org.sourcepit.modularizor.java.JavaResourcesRoot;
import org.sourcepit.modularizor.java.JavaType;
import org.sourcepit.modularizor.java.Resource;
import org.sourcepit.modularizor.java.ResourceVisitor;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class BundleRequiredPackagesCollector
{
   private static final Logger LOGGER = LoggerFactory.getLogger(BundleRequiredPackagesResolver.class);

   private final BundleCandidate bundle;

   private List<String> requiredPackages;

   private Multimap<String, String> requiredToDemandingPackagesMap;

   public BundleRequiredPackagesCollector(BundleCandidate bundle)
   {
      this.bundle = bundle;
   }

   public void collect()
   {
      // calculate required packages
      requiredToDemandingPackagesMap = LinkedHashMultimap.create();
      collectPackageReferences(requiredToDemandingPackagesMap, bundle);

      // add self references
      JavaBundlePackagesCollector packagesCollector = new JavaBundlePackagesCollector(bundle.getContent());
      packagesCollector.collect();
      for (String bundlePackage : packagesCollector.getBundlePackages())
      {
         requiredToDemandingPackagesMap.get(bundlePackage).add(bundlePackage);
      }

      requiredToDemandingPackagesMap = Multimaps.unmodifiableMultimap(requiredToDemandingPackagesMap);

      requiredPackages = new ArrayList<String>(requiredToDemandingPackagesMap.keySet());
      sort(requiredPackages);
      requiredPackages = unmodifiableList(requiredPackages);
   }

   public BundleCandidate getBundle()
   {
      return bundle;
   }

   public List<String> getRequiredPackages()
   {
      return requiredPackages;
   }

   public Multimap<String, String> getRequiredToDemandingPackages()
   {
      return requiredToDemandingPackagesMap;
   }

   private static void collectPackageReferences(final Multimap<String, String> requiredToConsumers,
      final BundleCandidate bundle)
   {
      final JavaResourceBundle jBundle = bundle.getContent();

      jBundle.accept(new ResourceVisitor()
      {
         @Override
         public boolean visit(Resource resource)
         {
            if (resource instanceof JavaFile)
            {
               final JavaFile jFile = (JavaFile) resource;
               final JavaPackage jPackage = jFile.getParentPackage();
               if (jPackage != null)
               {
                  visit(jPackage, jFile, jFile.getType());
               }
            }
            return resource instanceof JavaResourceDirectory;
         }

         private void visit(JavaPackage jPackage, JavaFile jFile, JavaType jType)
         {
            final Set<String> qualifiedTypeReferences = new HashSet<String>();
            collectReferencedTypes(bundle, jFile, qualifiedTypeReferences);

            // HACK must be recursive
            addSuperTypeAndInterfaces(jType, qualifiedTypeReferences);
            for (String qualifiedType : new HashSet<String>(qualifiedTypeReferences))
            {
               JavaType resolveJavaType = resolveJavaType(bundle, qualifiedType);
               if (resolveJavaType != null)
               {
                  addSuperTypeAndInterfaces(resolveJavaType, qualifiedTypeReferences);
               }
            }

            for (String qualifiedTypeReference : qualifiedTypeReferences)
            {
               final int idx = qualifiedTypeReference.lastIndexOf('.');
               final String packageName = idx > -1 ? qualifiedTypeReference.substring(0, idx) : null;
               if (packageName == null)
               {
                  LOGGER.warn("Type " + qualifiedTypeReference + " in default package will be ignored (from "
                     + jType.getQualifiedName() + ")");
               }
               else
               {
                  final String consumerPackageName = jPackage.getQualifiedName();
                  requiredToConsumers.put(packageName, consumerPackageName);
               }
            }
         }

         private void addSuperTypeAndInterfaces(JavaType jType, final Set<String> qualifiedTypeReferences)
         {
            Annotation annotation = jType.getAnnotation("superclassName");
            if (annotation != null)
            {
               qualifiedTypeReferences.addAll(annotation.getReferences().keySet());
            }

            annotation = jType.getAnnotation("interfaceNames");
            if (annotation != null)
            {
               qualifiedTypeReferences.addAll(annotation.getReferences().keySet());
            }
         }
      });
   }

   private static void collectReferencedTypes(final BundleCandidate bundle, final JavaFile jFile,
      final Set<String> qualifiedTypeReferences)
   {
      jFile.accept(new TypeReferenceVisitor()
      {
         @Override
         protected void foundTypeReference(JavaType jType, Set<String> qualifiedNames)
         {
            qualifiedTypeReferences.addAll(qualifiedNames);
         }
      });
   }

   private static JavaType resolveJavaType(BundleCandidate bundle, String name)
   {
      JavaResourceBundle jBundle = bundle.getContent();
      JavaType jType = resolveJavaType(jBundle, name);
      if (jType != null)
      {
         return jType;
      }
      for (BundleReference reference : bundle.getDependencies())
      {
         BundleCandidate target = reference.getTarget();
         if (target != null && target.getContent() != null)
         {
            jType = resolveJavaType(target.getContent(), name);
            if (jType != null)
            {
               return jType;
            }
         }
      }
      return null;
   }

   private static JavaType resolveJavaType(JavaResourceBundle jBundle, String name)
   {
      final String[] segments = name.replace('$', '.').split("\\.");
      for (JavaResourcesRoot javaResourcesRoot : jBundle.getResourcesRoots())
      {
         JavaResourceDirectory jPackage = javaResourcesRoot;
         JavaType type = null;
         for (int i = 0; i < segments.length; i++)
         {
            final String segment = segments[i];
            final JavaPackage nextPackage = jPackage.getPackage(segment);
            if (nextPackage != null)
            {
               jPackage = nextPackage;
               continue;
            }

            type = jPackage.getType(segment);
            if (type != null)
            {
               return type;
            }
         }
      }
      return null;
   }
}
